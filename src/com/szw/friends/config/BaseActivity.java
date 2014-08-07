package com.szw.friends.config;

import com.szw.friends.tools.AppManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;


public class BaseActivity extends Activity{
	protected MyApplication appContext;
	protected Context context = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appContext =  (MyApplication)getApplication();
		context = this;
		AppManager.getAppManager().addActivity(this);
	}

	public MyApplication getWcApplication() {
		return appContext;
	}
}
