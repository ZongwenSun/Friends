package com.szw.friends.tools;

import java.util.Stack;

import android.app.Activity;

public class AppManager {
	private static AppManager instance = null;
	private Stack<Activity> activityStack = null;
	private AppManager(){
		activityStack = new Stack<Activity>();
	}
	public static AppManager getAppManager() {
		if(instance == null){
			instance = new AppManager();
		}
		return instance;
	}
	
	public void addActivity(Activity obj){
		activityStack.add(obj);
	}
	
	public Activity currentActivity(){
		return activityStack.lastElement();
	}
	public void finishActivity(){
		Activity activity = activityStack.pop();
		activity.finish();
	}
	public void finishAllActivities(){
		while(!activityStack.isEmpty()){
			activityStack.pop().finish();
		}
	}
}
