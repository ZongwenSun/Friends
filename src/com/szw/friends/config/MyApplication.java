package com.szw.friends.config;

import com.szw.friends.tools.AppManager;

import android.app.Application;


public class MyApplication extends Application{
	private static MyApplication mApplication = null;
	@Override
	public void onCreate() {
		super.onCreate();
	}
	public static MyApplication getInstance() {
		if(mApplication == null){
			mApplication = new MyApplication();
		}
		return mApplication;
	}
	public void exit(){
		AppManager.getAppManager().finishAllActivities();
	}
}
