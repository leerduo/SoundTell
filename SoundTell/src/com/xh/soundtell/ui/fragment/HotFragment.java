package com.xh.soundtell.ui.fragment;

import java.util.ArrayList;

import com.xh.soundtell.R;
import com.xh.soundtell.ui.ActivityWeb;
import com.xh.soundtell.ui.HotActivity;
import com.xh.soundtell.ui.NewCiActivity;
import com.xh.soundtell.ui.PlayMusicActivity;
import com.xh.soundtell.ui.RenQiActivity;
import com.xh.soundtell.ui.TeacherActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class HotFragment extends Fragment implements OnClickListener {

	Activity act;

	public HotFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		act = activity;
		super.onAttach(activity);
	}

	View view;
	private ViewPager pager;
	private LinearLayout llContainer;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_hot, null);
		init();
		return view;
	}

	ArrayList<ImageView> imageList;
	int lastPagePosition = 0;
	int[] vpImgIds = { R.drawable.hot_guanggao1, R.drawable.hot_guanggao2,
			R.drawable.hot_guanggao3 };

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
		pager.setAdapter(new PagerAdapter() {
			@Override
			public int getCount() {
				return imageList.size();
			}

			@Override
			public boolean isViewFromObject(View view, Object obj) {
				return view == obj;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// super.destroyItem(container, position, object);
				// object就是 view。（ImageView）
				container.removeView((View) object);
				object = null;
			}
			/**
			 * 获得相应位置上的view container view的容器，其实就是viewpager自身 position 相应的位置
			 */
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// 将图片添加到container中即ViewPager中。
				// container.addView(imageList.get(position));
				// 这是要频繁加载数据。
				ImageView imageView = imageList.get(position);
				container.addView(imageView);
				imageView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(act, ActivityWeb.class);
						intent.putExtra("web", "0");
						startActivity(intent);
					}
				});
				// 返回一个view
				return imageList.get(position);
			}
		});
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// 把当前点enbale 为true //改变指示点的状态
				((ImageView) llContainer.getChildAt(position))
						.setImageResource(R.drawable.hot_point_enable);
				((ImageView) llContainer.getChildAt(lastPagePosition))
						.setImageResource(R.drawable.hot_point_normal);
				lastPagePosition = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		view.findViewById(R.id.fraghot_hot11).setOnClickListener(this);
		view.findViewById(R.id.fraghot_hot1).setOnClickListener(this);
		view.findViewById(R.id.fraghot_hot22).setOnClickListener(this);
		view.findViewById(R.id.fraghot_hot2).setOnClickListener(this);
		view.findViewById(R.id.fraghot_hot33).setOnClickListener(this);
		view.findViewById(R.id.fraghot_hot3).setOnClickListener(this);
		view.findViewById(R.id.fraghot_hotmore).setOnClickListener(this);
		view.findViewById(R.id.fraghot_hotmore_new).setOnClickListener(this);
		view.findViewById(R.id.fraghot_newci).setOnClickListener(this);
		view.findViewById(R.id.fraghot_newhot).setOnClickListener(this);
		view.findViewById(R.id.fraghot_teacher).setOnClickListener(this);
		view.findViewById(R.id.fraghot_renqi).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.fraghot_hot11:// 热歌
		case R.id.fraghot_hot1:// 热歌
			intent = new Intent(act, PlayMusicActivity.class);
			intent.putExtra("hotfragment", "hotfragment");
            intent.putExtra("songId", R.raw.exist_for_you_song);
            intent.putExtra("songName", "因你而在");
            intent.putExtra("songBody", getResources().getString(R.string.exist_for_you));
            break;
		case R.id.fraghot_hot22:// 热歌
		case R.id.fraghot_hot2:// 热歌
			intent = new Intent(act, PlayMusicActivity.class);
			intent.putExtra("hotfragment", "hotfragment");
			intent.putExtra("songId", R.raw.li_byebye_song);
			  intent.putExtra("songName", "再见 再见");
	            intent.putExtra("songBody", getResources().getString(R.string.li_byebye));
	            break;
			
		case R.id.fraghot_hot33:// 热歌
		case R.id.fraghot_hot3:// 热歌
			intent = new Intent(act, PlayMusicActivity.class);
			intent.putExtra("hotfragment", "hotfragment");
			intent.putExtra("songId", R.raw.w_nightdj_song);
			  intent.putExtra("songName", "午夜DJ");
	            intent.putExtra("songBody", getResources().getString(R.string.w_nightdj));
			break;
		case R.id.fraghot_hotmore:// 更多
		case R.id.fraghot_hotmore_new:// 更多
			intent = new Intent(act, HotActivity.class);
			break;
		case R.id.fraghot_newci:// 新词
		case R.id.fraghot_newhot:// 新词
			intent = new Intent(act, NewCiActivity.class);
			break;
		case R.id.fraghot_teacher:// 导师
			intent = new Intent(act, TeacherActivity.class);
			break;
		case R.id.fraghot_renqi:// 人气
			intent = new Intent(act, RenQiActivity.class);
			break;
		}
		if (intent != null) {
			startActivity(intent);
		}
	}

}
