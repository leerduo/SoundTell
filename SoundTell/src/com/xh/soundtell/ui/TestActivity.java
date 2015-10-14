package com.xh.soundtell.ui;

import com.xh.soundtell.R;
import com.xh.soundtell.adapter.PageAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class TestActivity extends Activity {

	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		viewPager = (ViewPager) findViewById(R.id.playmusic_viewPager);
		viewPager.setAdapter(new PageAdapter(this));
		viewPager.setCurrentItem(1);
	}

}
