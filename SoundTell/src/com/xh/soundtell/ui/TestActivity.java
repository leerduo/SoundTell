package com.xh.soundtell.ui;

import java.util.ArrayList;
import java.util.List;

import com.xh.soundtell.R;
import com.xh.soundtell.adapter.PageAdapter;
import com.xh.soundtell.view.MyScrollView;
import com.xh.soundtell.view.ScrollView1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

public class TestActivity extends Activity {

	private ViewPager viewPager;

	private ScrollView1 myScrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

//		viewPager = (ViewPager) findViewById(R.id.playmusic_viewPager);
//		viewPager.setAdapter(new PageAdapter(this));
//		viewPager.setCurrentItem(2);
	}

	class PageAdapter extends PagerAdapter {
		private Context context;
		private View view1, view2, view3;
		private List<View> views = new ArrayList<View>();

		public PageAdapter(Context context) {
			this.context = context;
			LayoutInflater lf = LayoutInflater.from(context);
			view1 = lf.inflate(R.layout.layout_musicview1, null);
			view2 = lf.inflate(R.layout.layout_musicview2, null);
			view3 = lf.inflate(R.layout.layout_musicview3, null);

			views.add(view1);
			views.add(view2);
			views.add(view3);
		}

		@Override
		public int getCount() {
			return 3;
		}

		@SuppressLint("InflateParams")
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(views.get(position));

//			myScrollView = (ScrollView1) findViewById(R.id.myScrollView);
//			myScrollView.setFocusable(true);
//			myScrollView.setFocusableInTouchMode(true);
//			Button button1 = (Button) findViewById(R.id.button1);
//			button1.setFocusable(true);
//			button1.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					System.out
//							.println("11111111111111111111111111111000000000000000");
//				}
//			});
			return views.get(position);
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(views.get(position));

		}
	}
}
