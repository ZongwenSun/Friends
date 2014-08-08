package com.szw.friends.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import com.szw.friends.R;
import com.szw.friends.config.BaseActivity;
import com.szw.friends.config.MyApplication;

public class MainActivity extends BaseActivity implements OnViewChangeListener, OnClickListener{
	private MyScrollLayout mMyScrollLayout = null;
	private LinearLayout[] mImageViews = null;
	private int mViewCount;
	private int mCurSel;// 当前选择的界面
	private TextView mFirstView = null;
	private TextView mSecondView = null;
	private TextView mThirdView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	private void init(){
		mMyScrollLayout = (MyScrollLayout)findViewById(R.id.ScrollLayout);
		mViewCount = mMyScrollLayout.getChildCount();
		mCurSel = 0;
		
		mFirstView = (TextView)findViewById(R.id.textView1);
		mSecondView = (TextView)findViewById(R.id.textView2);
		mThirdView = (TextView)findViewById(R.id.textView3);
		
		mMyScrollLayout.setOnViewChangeListener(this);
	LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lllayout);
		mImageViews = new LinearLayout[mViewCount];
		for(int i = 0; i < mViewCount; i ++){
			mImageViews[i] = (LinearLayout) linearLayout.getChildAt(i);
			mImageViews[i].setEnabled(true);
			mImageViews[i].setOnClickListener(this);
			mImageViews[i].setTag(i);
		}
		
		mImageViews[mCurSel].setEnabled(false);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_search:
	        	
	            openSearch();
	            return true;
	        case R.id.action_settings:
	            openSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	private void openSearch(){}
	private void openSettings(){
		SettingsPopupWindow settingsPopupWindow = new SettingsPopupWindow((Activity) context);
		// 显示窗口
		View view = MainActivity.this.findViewById(R.id.action_settings);
		// 计算坐标的偏移量
		int xoffInPixels = settingsPopupWindow.getWidth() - view.getWidth() + 10;
		settingsPopupWindow.showAsDropDown(view, -xoffInPixels, 0);
	}

	@Override
	public void OnViewChange(int view) {
		setCurPoint(view);
	}
	private void setCurPoint(int index) {
		if (index < 0 || index > mViewCount - 1 || mCurSel == index) {
			return;
		}
		mImageViews[mCurSel].setEnabled(true);
		mImageViews[index].setEnabled(false);
		mCurSel = index;

		if (index == 0) {
			mFirstView.setTextColor(0xff228B22);
			mSecondView.setTextColor(Color.BLACK);
			mThirdView.setTextColor(Color.BLACK);
		} else if (index == 1) {
			mFirstView.setTextColor(Color.BLACK);
			mSecondView.setTextColor(0xff228B22);
			mThirdView.setTextColor(Color.BLACK);
		} else {
			mFirstView.setTextColor(Color.BLACK);
			mSecondView.setTextColor(Color.BLACK);
			mThirdView.setTextColor(0xff228B22);
		}
	}

	@Override
	public void onClick(View view) {
		int index = (Integer)view.getTag();
		setCurPoint(index);
		mMyScrollLayout.snapToScreen(index);
		
	}
}
