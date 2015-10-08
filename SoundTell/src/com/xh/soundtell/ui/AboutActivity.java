/*************************************************************************************************
 * 版权所有 (C)2015,  成都市商联汇通技术有限公司
 * 
 * 文件名称：AboutActivity.java
 * 内容摘要：升级服务
 * 当前版本： TODO
 * 作        者： 李加蒙
 * 完成日期：2015-10-8 下午4:26:44
 * 修改记录：
 * 修改日期：2015-10-8 下午4:26:44
 * 版   本  号：
 * 修   改  人：
 * 修改内容：
 ************************************************************************************************/

package com.xh.soundtell.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xh.soundtell.R;
import com.xh.soundtell.adapter.HorizontalScrollViewAdapter;
import com.xh.soundtell.view.MyGallery;
import com.xh.soundtell.view.MyHorizontalScrollView;
import com.xh.soundtell.view.MyHorizontalScrollView.CurrentImageChangeListener;
import com.xh.soundtell.view.MyHorizontalScrollView.OnItemClickListener;

/**
 * 文件名称：AboutActivity.java
 */
public class AboutActivity extends Activity implements OnClickListener {
	private TextView head_centertext;
	private ImageView head_leftimage;

	private RelativeLayout about_weibo_r, about_course_r;

	private MyHorizontalScrollView mHorizontalScrollView;
	private HorizontalScrollViewAdapter mAdapter;

	public static int[] picture = { R.drawable.welcome1, R.drawable.welcome2,
			R.drawable.welcome3, R.drawable.welcome4, R.drawable.welcome5 };
	public static MyGallery pictureGallery = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		findView();
	}

	private void findView() {
		head_centertext = (TextView) findViewById(R.id.head_centertext);
		head_centertext.setVisibility(View.VISIBLE);
		head_centertext.setText("关于音诉音乐");

		head_leftimage = (ImageView) findViewById(R.id.head_leftimage);
		head_leftimage.setVisibility(View.VISIBLE);
		head_leftimage.setImageResource(R.drawable.back_wihte);
		head_leftimage.setOnClickListener(this);

		about_weibo_r = (RelativeLayout) findViewById(R.id.about_weibo_r);
		about_course_r = (RelativeLayout) findViewById(R.id.about_course_r);

		about_weibo_r.setOnClickListener(this);
		about_course_r.setOnClickListener(this);

		List<Integer> image = new ArrayList<Integer>();
		image.add(R.drawable.welcome1);
		image.add(R.drawable.welcome2);
		image.add(R.drawable.welcome3);
		image.add(R.drawable.welcome4);
		image.add(R.drawable.welcome5);
		mHorizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.id_horizontalScrollView);
		mAdapter = new HorizontalScrollViewAdapter(this, image);
		// 添加滚动回调
		mHorizontalScrollView
				.setCurrentImageChangeListener(new CurrentImageChangeListener() {
					@Override
					public void onCurrentImgChanged(int position,
							View viewIndicator) {
						// mImg.setImageResource(mDatas.get(position));
						// viewIndicator.setBackgroundColor(Color
						// .parseColor("#AA024DA4"));
						System.out.println("position" + position);
					}
				});
		// 添加点击回调
		mHorizontalScrollView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onClick(View view, int position) {
				// mImg.setImageResource(mDatas.get(position));
				// view.setBackgroundColor(Color.parseColor("#AA024DA4"));
				// Intent i = new Intent(FSReportInfoActivity.this,
				// ShowImgsActivity.class);
				// i.putExtra("ID", position);
				// i.putStringArrayListExtra("imgUrls", reportr.getImgUrl());
				// startActivity(i);
			}
		});
		// 设置适配器
		mHorizontalScrollView.initDatas(mAdapter);

		this.pictureGallery = (MyGallery) findViewById(R.id.mygallery);
		ImageAdapter adapter = new ImageAdapter(this);
		this.pictureGallery.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.head_leftimage:
			AboutActivity.this.finish();
			break;
		case R.id.about_weibo_r:
			break;
		case R.id.about_course_r:
			// findViewById(R.id.about_l).setVisibility(View.GONE);
			pictureGallery.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

	/**
	 * 自定义图片显示适配器
	 * 
	 */
	class ImageAdapter extends BaseAdapter {
		private Context context;

		public ImageAdapter(Context context) {
			this.context = context;
		}

		public int getCount() {
			return Integer.MAX_VALUE;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(picture[position % picture.length]);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setLayoutParams(new Gallery.LayoutParams(
					Gallery.LayoutParams.MATCH_PARENT,
					Gallery.LayoutParams.MATCH_PARENT));
			return imageView;
		}
	}

}
