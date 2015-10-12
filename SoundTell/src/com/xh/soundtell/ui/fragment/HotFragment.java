package com.xh.soundtell.ui.fragment;

import java.util.ArrayList;

import com.xh.soundtell.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class HotFragment extends Fragment {
	
	Activity act;
	public HotFragment(){}
	
	 @Override
	public void onAttach(Activity activity) {
     	act=activity;
		super.onAttach(activity);
	}
	 View view;
	private ViewPager pager;
	private LinearLayout llContainer;
  @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	  view=inflater.inflate(R.layout.fragment_hot, null);
	  init();
	return view;
}
  ArrayList<ImageView> imageList;
  int lastPagePosition=0;
  int [] vpImgIds={R.drawable.demo_0,R.drawable.demo_1,R.drawable.demo_2};
private void init() {
	pager = (ViewPager) view.findViewById(R.id.fraghot_vp);
	llContainer = (LinearLayout) view.findViewById(R.id.fraghot_container);
	llContainer.removeAllViews();
	// 初始化集合
	imageList = new ArrayList<ImageView>();
	for (int i = 0; i < vpImgIds.length; i++) {
		// 初始化图片资源
		ImageView iv = new ImageView(act);
		iv.setImageResource(vpImgIds[i]);
		iv.setScaleType(ScaleType.FIT_XY);
		imageList.add(iv);
		// 初始化point,放几个点到title的下面。
		ImageView point = new ImageView(act);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.rightMargin = 10;
		point.setLayoutParams(params);
		if (i == 0) {
			point.setImageResource(R.drawable.hot_point_enable);
		} else {
			point.setImageResource(R.drawable.hot_point_normal);
		}
		llContainer.addView(point);
	}
	pager.setAdapter(new PagerAdapter(){
		@Override
		public int getCount() {
			return imageList.size();
		}
		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
//			super.destroyItem(container, position, object);
			// object就是 view。（ImageView）
			container.removeView((View) object);
			object = null;
		}
		/**
		 * 获得相应位置上的view
		 * container  view的容器，其实就是viewpager自身
		 * position 	相应的位置
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// 将图片添加到container中即ViewPager中。
//			container.addView(imageList.get(position));
			// 这是要频繁加载数据。
			ImageView imageView = imageList.get(position);
			container.addView(imageView);
			// 返回一个view
			return imageList.get(position);
		}
		});
	pager.setOnPageChangeListener(new OnPageChangeListener() {
		@Override
		public void onPageSelected(int position) {
			// 把当前点enbale 为true //改变指示点的状态
			((ImageView)llContainer.getChildAt(position)).setImageResource(R.drawable.hot_point_enable);
			((ImageView)llContainer.getChildAt(lastPagePosition)).setImageResource(R.drawable.hot_point_normal);
			lastPagePosition = position;
		}
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	});
	
}





}
