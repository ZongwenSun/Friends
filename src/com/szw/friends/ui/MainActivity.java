package com.szw.friends.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

import com.szw.friends.R;
import com.szw.friends.config.BaseActivity;
import com.szw.friends.config.MyApplication;

public class MainActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
}
