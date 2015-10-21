package com.xh.soundtell.ui.pickphoto;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.xh.soundtell.R;
import com.xh.soundtell.adapter.AlbumGridViewAdapter;
import com.xh.soundtell.util.AlbumHelper;
import com.xh.soundtell.util.ImageBucket;

/**
 * 这个是进入相册显示所有图片的界面
 * 
 * @author 陈宝林
 * @version 2015年08月14日 下午11:47:15
 */
public class AlbumActivity extends Activity {
	// 字体 以及返回按钮
	private TextView head_centertext;
	private ImageView head_leftimage;

	// 显示手机里的所有图片的列表控件
	private GridView gridView;
	// 当手机里没有图片时，提示用户没有图片的控件
	private TextView tv;
	// gridView的adapter
	private com.xh.soundtell.adapter.AlbumGridViewAdapter gridImageAdapter;
	// 完成按钮
	private Button okButton;
	// 返回按钮
	private Button back;
	// 取消按钮
	private Button cancel;
	private Intent intent;
	// 是从上传图片进还是继续添加
	private String upimage;

	// 预览按钮
	private Button preview;
	private Context mContext;
	private ArrayList<ImageItem> dataList;
	private AlbumHelper helper;
	private int check = 0;
	public static List<ImageBucket> contentList;
	public static Bitmap bitmap;

	// 取消按钮
	// private

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plugin_camera_album);
		AlbumBimpUtil.putActivty(this);
		mContext = this;
		// 注册一个广播，这个广播主要是用于在GalleryActivity进行预览时，防止当所有图片都删除完后，再回到该页面时被取消选中的图片仍处于选中状态
		IntentFilter filter = new IntentFilter("data.broadcast.action");
		registerReceiver(broadcastReceiver, filter);
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.plugin_camera_no_pictures);
		init();
		initListener();
		// 这个函数主要用来控制预览和完成按钮的状态
		isShowOkBt();
	}

	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// mContext.unregisterReceiver(this);
			// TODO Auto-generated method stub
			gridImageAdapter.notifyDataSetChanged();
		}
	};

	// 预览按钮的监听
	private class PreviewListener implements OnClickListener {
		public void onClick(View v) {
			if (AlbumBimpUtil.tempSelectBitmap.size() > 0) {
				intent.putExtra("position", "1");
				intent.setClass(AlbumActivity.this, GalleryActivity.class);
				startActivity(intent);
			}
		}

	}

	// 完成按钮的监听
	private class AlbumSendListener implements OnClickListener {
		public void onClick(View v) {
			overridePendingTransition(R.anim.activity_translate_in,
					R.anim.activity_translate_out);
			AlbumBimpUtil.finshActivities(AlbumActivity.this);
			finish();
		}

	}

	// 返回按钮监听
	private class BackListener implements OnClickListener {
		public void onClick(View v) {
			intent.setClass(AlbumActivity.this, ImageFileActivity.class);
			startActivity(intent);
		}
	}

	// 取消按钮的监听
	private class CancelListener implements OnClickListener {
		public void onClick(View v) {
			cancel.setClickable(false);
			// intent.setClass(mContext, PointsmanActivity.class);
			// startActivity(intent);
			for (int i = 0; i < check; i++) {
				AlbumBimpUtil.tempSelectBitmap
						.remove(AlbumBimpUtil.tempSelectBitmap.size() - 1);
			}
			finish();
		}
	}

	// 初始化，给一些对象赋值
	private void init() {
		head_centertext = (TextView) findViewById(R.id.head_centertext);
		head_centertext.setVisibility(View.VISIBLE);
		head_centertext.setText("手机相册");

		head_leftimage = (ImageView) findViewById(R.id.head_leftimage);
		head_leftimage.setVisibility(View.VISIBLE);
		head_leftimage.setImageResource(R.drawable.back_wihte);
		head_leftimage.setOnClickListener(new CancelListener());

		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());

		contentList = helper.getImagesBucketList(false);
		dataList = new ArrayList<ImageItem>();
		for (int i = 0; i < contentList.size(); i++) {
			dataList.addAll(contentList.get(i).imageList);
		}

		back = (Button) findViewById(R.id.back);
		cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(new CancelListener());
		back.setOnClickListener(new BackListener());
		preview = (Button) findViewById(R.id.preview);
		preview.setOnClickListener(new CancelListener());
		intent = getIntent();
		upimage = intent.getStringExtra("upimage");
		Bundle bundle = intent.getExtras();
		gridView = (GridView) findViewById(R.id.myGrid);
		gridImageAdapter = new AlbumGridViewAdapter(this, dataList,
				AlbumBimpUtil.tempSelectBitmap);
		gridView.setAdapter(gridImageAdapter);
		tv = (TextView) findViewById(R.id.myText);
		gridView.setEmptyView(tv);
		okButton = (Button) findViewById(R.id.ok_button);

		if (upimage.equals("1")) {
			okButton.setText("选择");
		} else if (upimage.equals("2")) {
			okButton.setText("选择");
		} else {
			okButton.setText("完成" + "(" + AlbumBimpUtil.tempSelectBitmap.size()
					+ "/" + AlbumBimpUtil.num + ")");
		}

	}

	private void initListener() {

		gridImageAdapter
				.setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {

					@Override
					public void onItemClick(final ToggleButton toggleButton,
							int position, boolean isChecked, Button chooseBt) {
						// 现在不用超过张数了
						// if (AlbumBimpUtil.tempSelectBitmap.size() >=
						// AlbumBimpUtil.num) {
						// toggleButton.setChecked(false);
						// chooseBt.setVisibility(View.GONE);
						// if (!removeOneData(dataList.get(position))) {
						// Toast.makeText(AlbumActivity.this,
						// R.string.only_choose_num, 200).show();
						// }
						// return;
						// }
						if (isChecked) {
							++check;
							chooseBt.setVisibility(View.VISIBLE);
							AlbumBimpUtil.tempSelectBitmap.add(dataList
									.get(position));
							// okButton.setText("完成" + "("
							// + AlbumBimpUtil.tempSelectBitmap.size()
							// + "/" + AlbumBimpUtil.num + ")");
							okButton.setText("已选" + "("
									+ AlbumBimpUtil.tempSelectBitmap.size()
									+ "张" + ")");
						} else {
							--check;
							AlbumBimpUtil.tempSelectBitmap.remove(dataList
									.get(position));
							chooseBt.setVisibility(View.GONE);
							okButton.setText("已选" + "("
									+ AlbumBimpUtil.tempSelectBitmap.size()
									+ "张" + ")");
						}
						isShowOkBt();
					}
				});

		okButton.setOnClickListener(new AlbumSendListener());

	}

	private boolean removeOneData(ImageItem imageItem) {
		if (AlbumBimpUtil.tempSelectBitmap.contains(imageItem)) {
			AlbumBimpUtil.tempSelectBitmap.remove(imageItem);
			okButton.setText("完成" + "(" + AlbumBimpUtil.tempSelectBitmap.size()
					+ "/" + AlbumBimpUtil.num + ")");
			return true;
		}
		return false;
	}

	public void isShowOkBt() {
		if (AlbumBimpUtil.tempSelectBitmap.size() > 0) {
			okButton.setText("已选" + "(" + AlbumBimpUtil.tempSelectBitmap.size()
					+ "张" + ")");
			preview.setPressed(true);
			okButton.setPressed(true);
			preview.setClickable(true);
			okButton.setClickable(true);
		} else {
			okButton.setText("选择");
			preview.setPressed(false);
			preview.setClickable(false);
			okButton.setPressed(false);
			okButton.setClickable(false);
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// intent.setClass(AlbumActivity.this, ImageFile.class);
			// startActivity(intent);
			for (int i = 0; i < check; i++) {
				AlbumBimpUtil.tempSelectBitmap
						.remove(AlbumBimpUtil.tempSelectBitmap.size() - 1);
			}
			finish();
		}
		return false;
	}

	@Override
	protected void onRestart() {
		isShowOkBt();
		super.onRestart();
	}
}
