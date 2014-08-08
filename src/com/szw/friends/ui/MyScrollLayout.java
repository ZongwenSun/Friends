package com.szw.friends.ui;


import android.R.integer;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.widget.Scroller;

public class MyScrollLayout extends ViewGroup{
	private final String TAG="MyScrollLayout";
	private Scroller mScroller = null;
    private VelocityTracker mVelocityTracker = null;//�ٶȸ���
    private static final int SNAP_VELOCITY = 1000;//�ٶ��ٽ�ֵ��ÿ����������������400ʱ�����һ���С��-400ʱ������
	private int mCurScreen = 0;
	private int mDefaultScreen = 0;
    private float mLastMotionX;       //����� ������
    private float mLastMotionY; 
    private boolean isPass = false;
	private OnViewChangeListener mOnViewChangeListener = null;//��Activity�����ģ�����ͼ�л�ʱ����
	public MyScrollLayout(Context context) {
		super(context);
		init(context);
	}
	
	public MyScrollLayout(Context context,AttributeSet attrs){
		super(context, attrs);
		init(context);
	}
	public MyScrollLayout(Context context,AttributeSet attrs,int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	public void init(Context context) {
		mScroller = new Scroller(context);
		mCurScreen = mDefaultScreen;
	}
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		if(changed){
			final int count = getChildCount();
			int childLeft = 0;
			for(int i = 0; i < count; i ++){
				View childView = getChildAt(i);
				if(childView.getVisibility() != GONE){
					final int childWidth = childView.getMeasuredWidth();
					childView.layout(childLeft, 0, childLeft+childWidth, childView.getMeasuredHeight());
					childLeft += childWidth;
				}
			}
		}
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);		
		final int width = MeasureSpec.getSize(widthMeasureSpec);             
	    		
		final int count = getChildCount();      
        for (int i = 0; i < count; i++) {       
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);       
        }                
        scrollTo(mCurScreen * width, 0);		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		final int action = event.getAction();
        final float x = event.getX();    
        final float y = event.getY();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.i(TAG,"action Down");
			//��ʼ������ָ�ƶ��ٶ�
			if (mVelocityTracker == null) {    
	            mVelocityTracker = VelocityTracker.obtain();    
	            mVelocityTracker.addMovement(event); 
			}     
			if (!mScroller.isFinished()){    
                mScroller.abortAnimation();    
            }
			mLastMotionX = x;
			mLastMotionY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i(TAG,"action Move");
			int deltaX = (int)(mLastMotionX - x);
			if(IsCanMove(deltaX) == true){
				if (mVelocityTracker != null)
 		         {
 		            mVelocityTracker.addMovement(event); 
 		         }   
 	            mLastMotionX = x;     
 	            scrollBy(deltaX, 0);
			}
			break;
		case MotionEvent.ACTION_UP:
			int velocityX = 0;
			Log.i(TAG,"action UP");
			if(mVelocityTracker != null){
				mVelocityTracker.addMovement(event);
				mVelocityTracker.computeCurrentVelocity(1000);
				velocityX = (int) mVelocityTracker.getXVelocity();
				
			}
			Log.i(TAG,"�����ٶ�="+velocityX);
			if(velocityX > SNAP_VELOCITY && mCurScreen > 0){
				snapToScreen(mCurScreen - 1); 
			}
			else if(velocityX < -SNAP_VELOCITY && mCurScreen < getChildCount() - 1){
				snapToScreen(mCurScreen + 1);
			}
			else{
				snapToTargetScreen();
			}
			if(mVelocityTracker != null){
				mVelocityTracker.recycle();
				mVelocityTracker = null;
			}
			break;
		}
		return true;
	}
	
	private boolean IsCanMove(int deltaX)
	{
		if (getScrollX() <= 0 && deltaX < 0 ){
			return false;
		}	
		if  (getScrollX() >=  (getChildCount() - 1) * getWidth() && deltaX > 0){
			return false;
		}		
		return true;
	}
	
	public void snapToScreen(int targetScreen){
		Log.i(TAG,"snapToScreen "+targetScreen);
		targetScreen = Math.max(0, Math.min(getChildCount(), targetScreen));
		if(getScrollX() != (targetScreen*getWidth())){
			int curScrollX = getScrollX();
			int deltaX = targetScreen*getWidth() - curScrollX;
			mScroller.startScroll(curScrollX, 0, deltaX, 0, 300);
			mCurScreen = targetScreen;
			invalidate();
			if(mOnViewChangeListener != null){
				mOnViewChangeListener.OnViewChange(mCurScreen);
			}
		}
	}
	//���ݻ����ľ��룬�����������Ļ���򻬶�
	private void snapToTargetScreen(){
        final int screenWidth = getWidth();    
        final int destScreen = (getScrollX()+ screenWidth/2)/screenWidth;    
        snapToScreen(destScreen);   
	}
	
	public void setOnViewChangeListener(OnViewChangeListener listener) {
		mOnViewChangeListener = listener;
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: 
			System.out.println("������onInterceptTouchEvent");
			if(isPass){
				return true;
			}
			break;
		case MotionEvent.ACTION_MOVE: 
			System.out.println("���໬��onInterceptTouchEvent");
			if(isPass){
				return true;
			}
			break;
		case MotionEvent.ACTION_UP:
			System.out.println("����ſ�onInterceptTouchEvent");
			break;
		}
		return super.onInterceptTouchEvent(event);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: 
			mLastMotionX = event.getX();	           
            mLastMotionY = event.getY();
			System.out.println("������dispatchTouchEvent");
			break;
		case MotionEvent.ACTION_MOVE: 
			System.out.println(Math.abs(event.getX()- mLastMotionX));
			System.out.println(Math.abs(event.getY()- mLastMotionY));
			double tanNum = Math.atan(Math.abs(event.getY()-mLastMotionY)/Math.abs(event.getX()- mLastMotionX));
			double retote = tanNum/3.14*180;
			System.out.println("�Ƕ�:"+retote);
			if (retote<20) {
				System.out.println("---------���໬��dispatchTouchEvent");
				isPass= true;
			}else{
				isPass = false;
			}
			onInterceptTouchEvent(event);
			System.out.println("***************"+isPass);
			break;
		case MotionEvent.ACTION_UP:
			System.out.println("����ſ�dispatchTouchEvent");
			break;
		}
		return super.dispatchTouchEvent(event);
	}
	@Override
	public void computeScroll() {
		// TODO Auto-generated method stub
		if (mScroller.computeScrollOffset()) {    
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());  
            postInvalidate();    
        }   
	}
}
