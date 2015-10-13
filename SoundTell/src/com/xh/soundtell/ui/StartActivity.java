/*************************************************************************************************
 * 版权所有 (C)2015,  成都市商联汇通技术有限公司
 * 
 * 文件名称：StartActivity.java
 * 内容摘要：升级服务
 * 当前版本： TODO
 * 作        者： 李加蒙
 * 完成日期：2015-10-13 下午4:22:23
 * 修改记录：
 * 修改日期：2015-10-13 下午4:22:23
 * 版   本  号：
 * 修   改  人：
 * 修改内容：
 ************************************************************************************************/

package com.xh.soundtell.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.xh.soundtell.R;

/**
 * 文件名称：StartActivity.java
 */
public class StartActivity extends Activity implements AnimationListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		View view = findViewById(R.id.splash);
		AlphaAnimation anim = new AlphaAnimation(0.5f, 1.0f);
		anim.setDuration(1000);
		// anim.setDuration(3000);
		anim.setRepeatCount(0);
		anim.setAnimationListener(this);
		view.startAnimation(anim);
	}

	private void goNext() {
		// UIHelper.show(this, R.layout.layout_login);
		startActivity(new Intent(this, MainActivity.class));
		this.finish();
	}

	@Override
	public void onAnimationStart(Animation animation) {
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		goNext();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
	}
	
}
