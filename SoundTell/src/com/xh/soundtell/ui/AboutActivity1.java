/*************************************************************************************************
 * 版权所有 (C)2015,  成都市商联汇通技术有限公司
 * 
 * 文件名称：AboutActivity1.java
 * 内容摘要：升级服务
 * 当前版本： TODO
 * 作        者： 李加蒙
 * 完成日期：2015-10-30 上午10:17:08
 * 修改记录：
 * 修改日期：2015-10-30 上午10:17:08
 * 版   本  号：
 * 修   改  人：
 * 修改内容：
 ************************************************************************************************/

package com.xh.soundtell.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.xh.soundtell.R;
import com.xh.soundtell.setting.SettingHelper;
import com.xh.soundtell.util.PrefUtil;
import com.xh.soundtell.view.StartGallery;

/**
 * 文件名称：AboutActivity1.java
 */
public class AboutActivity1 extends Activity {
	public static int[] picture = { R.drawable.welcome1, R.drawable.welcome2,
			R.drawable.welcome3, R.drawable.welcome4, R.drawable.welcome5 };
	public static StartGallery pictureGallery = null;
	private PrefUtil prefUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about1);
		// prefUtil = PrefUtil.getInstance();
		this.pictureGallery = (StartGallery) findViewById(R.id.start_gallery);
		ImageAdapter adapter = new ImageAdapter(this);
		this.pictureGallery.setAdapter(adapter);
		pictureGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("onItemSelected" + position);
				if (picture.length == position) {
					// startActivity(new Intent(AboutActivity1.this,
					// MainActivity.class));
					// // prefUtil.setFirst("1");
					// AboutActivity1.this.finish();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				System.out.println("onNothingSelected");
			}
		});

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

	public static void goNeix() {
	}

}
