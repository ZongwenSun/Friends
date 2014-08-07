package com.szw.friends.ui;


import com.szw.friends.R;
import com.szw.friends.config.BaseActivity;
import com.szw.friends.tools.AppManager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class FlashActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View view = View.inflate(this, R.layout.activity_flash, null);
		setContentView(view);
		//ÉèÖÃ¹ý¶ÉÌø×ª
		AlphaAnimation aa = new AlphaAnimation(0.3f,1.0f);
		aa.setDuration(2000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener()
		{
			public void onAnimationEnd(Animation arg0) {
				redirectTo();
			}
			public void onAnimationRepeat(Animation animation) {}
			public void onAnimationStart(Animation animation) {}
			
		});
	}
	
	private void redirectTo(){     
		Intent intent = new Intent(context, MainActivity.class);
		context.startActivity(intent);
		AppManager.getAppManager().finishActivity();
    }
}
	
