/*************************************************************************************************
 * 版权所有 (C)2015,  成都市商联汇通技术有限公司
 * 
 * 文件名称：PageAdapter.java
 * 内容摘要：升级服务
 * 当前版本： TODO
 * 作        者： 李加蒙
 * 完成日期：2015-10-14 下午5:37:25
 * 修改记录：
 * 修改日期：2015-10-14 下午5:37:25
 * 版   本  号：
 * 修   改  人：
 * 修改内容：
 ************************************************************************************************/

package com.xh.soundtell.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.xh.soundtell.R;

/**
 * 文件名称：PageAdapter.java
 */
public class PageAdapter extends PagerAdapter {
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

//		ScrollView scrollView=(ScrollView) container.findViewById(R.id.myScrollView);
//		scrollView.setFocusable(true);
//		scrollView.setFocusableInTouchMode(true);
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
