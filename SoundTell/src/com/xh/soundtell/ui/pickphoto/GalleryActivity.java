package com.xh.soundtell.ui.pickphoto;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xh.soundtell.R;
import com.xh.soundtell.ui.UploadImageActivity;
import com.xh.soundtell.util.ImageHelper;
import com.xh.soundtell.util.PrefUtil;

/**
 * 这个是用于进行图片浏览时的界面
 * 
 * @author 陈宝林
 * @version 2015年08月13日 下午13:48:53
 */
public class GalleryActivity extends Activity {
	private Intent intent;
	// 返回按钮
	private ImageView back_bt;
	// 中部图片的多少
	private TextView gallery_num;
	// 发送按钮
	private Button send_bt;
	// 删除按钮
	private Button del_bt;
	// 顶部显示预览图片位置的textview
	private TextView positionTextView;
	// 获取前一个activity传过来的position
	private int position;
	// 当前的位置
	private int location = 0;

	private ArrayList<View> listViews = null;
	private ViewPagerFixed pager;
	private MyPageAdapter adapter;

	public List<Bitmap> bmp = new ArrayList<Bitmap>();
	public List<String> drr = new ArrayList<String>();
	public List<String> del = new ArrayList<String>();

	private Context mContext;

	RelativeLayout photo_relativeLayout;

	private int size;
	private String iamge;
	private PrefUtil prefUtil;

	private int imagesize;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plugin_camera_gallery);// 切屏到主界面
		prefUtil = PrefUtil.getInstance();
		AlbumBimpUtil.putActivty(this);
		mContext = this;
		gallery_num = (TextView) findViewById(R.id.gallery_num);
		back_bt = (ImageView) findViewById(R.id.gallery_back);
		send_bt = (Button) findViewById(R.id.send_button);
		del_bt = (Button) findViewById(R.id.gallery_del);
		back_bt.setOnClickListener(new CancelListener());
		send_bt.setOnClickListener(new GallerySendListener());
		del_bt.setOnClickListener(new DelListener());
		intent = getIntent();
		position = Integer.parseInt(intent.getStringExtra("position"));
		iamge = intent.getStringExtra("iamge");
		isShowOkBt();
		// 为发送按钮设置文字
		pager = (ViewPagerFixed) findViewById(R.id.gallery01);
		pager.setOnPageChangeListener(pageChangeListener);
		if (iamge.equals("2")) {
			for (int i = 0; i < AlbumBimpUtil.tempSelectBitmap.size(); i++) {
				initListViews(AlbumBimpUtil.tempSelectBitmap.get(i).getBitmap());
			}
			imagesize = AlbumBimpUtil.tempSelectBitmap.size();
		} else {
			for (int i = 0; i < UploadImageActivity.list.size(); i++) {
				initListViews(ImageHelper.readBitMap(UploadImageActivity.list
						.get(i)));
			}
			imagesize = AlbumBimpUtil.tempSelectBitmap.size();
		}

		adapter = new MyPageAdapter(listViews);
		pager.setAdapter(adapter);
		pager.setPageMargin((int) getResources().getDimensionPixelOffset(
				R.dimen.ui_10_dip));
		pager.setOnPageChangeListener(pageChangeListener);
		int id = intent.getIntExtra("ID", 0);
		pager.setCurrentItem(id);

		gallery_num.setText((id + 1) + "/" + size);

	}

	@Override
	protected void onRestart() {
		super.onRestart();
		listViews.clear();
		for (int i = 0; i < AlbumBimpUtil.tempSelectBitmap.size(); i++) {
			initListViews(AlbumBimpUtil.tempSelectBitmap.get(i).getBitmap());
		}
		if (adapter != null) {
			adapter.notifyDataSetChanged();
		}
	}

	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		public void onPageSelected(int arg0) {
			location = arg0;
			gallery_num.setText((location + 1) + "/" + size);
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageScrollStateChanged(int arg0) {

		}
	};

	private void initListViews(Bitmap bm) {
		if (listViews == null)
			listViews = new ArrayList<View>();
		PhotoView img = new PhotoView(this);
		img.setBackgroundColor(0xff000000);
		img.setImageBitmap(bm);
		img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		listViews.add(img);
	}

	// 返回按钮添加的监听器 去相册
	private class BackListener implements OnClickListener {

		public void onClick(View v) {
			intent.setClass(GalleryActivity.this, ImageFileActivity.class);
			startActivity(intent);
		}
	}

	// 取消按钮的监听
	private class CancelListener implements OnClickListener {
		public void onClick(View v) {
			// cancel.setClickable(false);
			// intent.setClass(mContext, PointsmanActivity.class);
			// startActivity(intent);
			// for (int i = 0; i < check; i++) {
			// AlbumBimpUtil.tempSelectBitmap.remove(AlbumBimpUtil.tempSelectBitmap.size()-1);
			// }
			GalleryActivity.this.finish();
			setResult(RESULT_OK);
		}
	}

	// 删除按钮添加的监听器
	private class DelListener implements OnClickListener {

		public void onClick(View v) {
			if (iamge.equals("2")) {
				if (listViews.size() == 1) {
					AlbumBimpUtil.tempSelectBitmap.clear();
					AlbumBimpUtil.max = 0;
					send_bt.setText("完成" + "("
							+ AlbumBimpUtil.tempSelectBitmap.size() + "/"
							+ AlbumBimpUtil.num + ")");
					Intent intent = new Intent("data.broadcast.action");
					sendBroadcast(intent);
					AlbumBimpUtil.finshActivities(GalleryActivity.this);
					setResult(RESULT_OK);
					finish();
				} else {
					AlbumBimpUtil.tempSelectBitmap.remove(location);
					AlbumBimpUtil.max--;
					pager.removeAllViews();
					listViews.remove(location);
					adapter.setListViews(listViews);
					send_bt.setText("完成" + "("
							+ AlbumBimpUtil.tempSelectBitmap.size() + "/"
							+ AlbumBimpUtil.num + ")");
					adapter.notifyDataSetChanged();
					gallery_num.setText((location + 1) + "/"
							+ AlbumBimpUtil.tempSelectBitmap.size());
				}
			} else {
				if (listViews.size() == 1) {
					UploadImageActivity.list = new ArrayList<String>();
					AlbumBimpUtil.max = 0;
					send_bt.setText("完成" + "("
							+ AlbumBimpUtil.tempSelectBitmap.size() + "/"
							+ AlbumBimpUtil.num + ")");
					Intent intent = new Intent("data.broadcast.action");
					sendBroadcast(intent);
					AlbumBimpUtil.finshActivities(GalleryActivity.this);
					// prefUtil.setUpload("");
					setResult(RESULT_OK);
					finish();
				} else {
					UploadImageActivity.list.remove(location);

					// prefUtil.setUpload("");
					// UploadImageActivity.images.AlbumBimpUtil.max--;
					pager.removeAllViews();
					listViews.remove(location);
					adapter.setListViews(listViews);
					send_bt.setText("完成" + "("
							+ AlbumBimpUtil.tempSelectBitmap.size() + "/"
							+ AlbumBimpUtil.num + ")");
					adapter.notifyDataSetChanged();
					gallery_num.setText((location + 1) + "/"
							+ UploadImageActivity.list.size());
				}
			}

		}
	}

	// 完成按钮的监听
	private class GallerySendListener implements OnClickListener {
		public void onClick(View v) {
			AlbumBimpUtil.finshActivities(GalleryActivity.this);
			GalleryActivity.this.finish();
		}

	}

	public void isShowOkBt() {
		if (AlbumBimpUtil.tempSelectBitmap.size() > 0) {
			send_bt.setText("完成" + "(" + AlbumBimpUtil.tempSelectBitmap.size()
					+ "/" + AlbumBimpUtil.num + ")");
			send_bt.setPressed(true);
			send_bt.setClickable(true);
			send_bt.setTextColor(Color.WHITE);
		} else {
			send_bt.setPressed(false);
			send_bt.setClickable(false);
			send_bt.setTextColor(Color.parseColor("#E1E0DE"));
		}
	}

	/**
	 * 监听返回按钮
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (position == 1) {
				setResult(RESULT_OK);
				this.finish();
				// intent.setClass(GalleryActivity.this, AlbumActivity.class);
				// startActivity(intent);
			} else if (position == 2) {
				this.finish();
				intent.setClass(GalleryActivity.this,
						ShowAllPhotoActivity.class);
				startActivity(intent);
			}
		}
		return true;
	}

	class MyPageAdapter extends PagerAdapter {

		private ArrayList<View> listViews;

		public MyPageAdapter(ArrayList<View> listViews) {
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public void setListViews(ArrayList<View> listViews) {
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public int getCount() {
			return size;
		}

		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPagerFixed) arg0).removeView(listViews.get(arg1 % size));
		}

		public void finishUpdate(View arg0) {
		}

		public Object instantiateItem(View arg0, int arg1) {
			// System.out.println("arg1:" + arg1 + "br/" + "arg1 % size:" + arg1
			// % size);
			try {
				((ViewPagerFixed) arg0).addView(listViews.get(arg1 % size), 0);

			} catch (Exception e) {
			}
			return listViews.get(arg1 % size);
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}
}
