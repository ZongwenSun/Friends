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
		//设置按钮监听
		//设置SelectPicPopupWindow的View
		this.setContentView(mMenuView);
		//设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(w/2+50);
		//设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		//设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		//设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.PopwindowStyle);
		//实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xdd000000);
		//设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);
	}
}
