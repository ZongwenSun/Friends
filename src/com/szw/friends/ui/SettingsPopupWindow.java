package com.szw.friends.ui;

import java.util.zip.Inflater;

import com.szw.friends.R;
import com.szw.friends.config.MyApplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class SettingsPopupWindow extends PopupWindow {
	private View mMenuView;
	private LinearLayout mAccountLayout = null;
	private LinearLayout mSettingLayout = null;
	private LinearLayout mExitLayout = null;
	public SettingsPopupWindow(Activity context) {
		super(context);
		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.popup_settings_layout, null);
		
		mAccountLayout = (LinearLayout)mMenuView.findViewById(R.id.layout_account);
		mSettingLayout = (LinearLayout)mMenuView.findViewById(R.id.layout_setting);
		mExitLayout = (LinearLayout)mMenuView.findViewById(R.id.layout_exit);
		mExitLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				MyApplication.getInstance().exit();
			}
		});
		//���ð�ť����
		//����SelectPicPopupWindow��View
		this.setContentView(mMenuView);
		//����SelectPicPopupWindow��������Ŀ�
		this.setWidth(w/2+50);
		//����SelectPicPopupWindow��������ĸ�
		this.setHeight(LayoutParams.WRAP_CONTENT);
		//����SelectPicPopupWindow��������ɵ��
		this.setFocusable(true);
		//����SelectPicPopupWindow�������嶯��Ч��
		this.setAnimationStyle(R.style.PopwindowStyle);
		//ʵ����һ��ColorDrawable��ɫΪ��͸��
		ColorDrawable dw = new ColorDrawable(0xdd000000);
		//����SelectPicPopupWindow��������ı���
		this.setBackgroundDrawable(dw);
	}
}
