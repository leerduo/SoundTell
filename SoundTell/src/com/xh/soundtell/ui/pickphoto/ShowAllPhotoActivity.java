package com.xh.soundtell.ui.pickphoto;

import java.util.ArrayList;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.xh.soundtell.R;
import com.xh.soundtell.adapter.AlbumGridViewAdapter;

/**
 * 这个是显示一个文件夹里面的所有图片时的界面
 * 
 * @author 陈宝林
 * @version 2015年08月13日 下午11:49:10
 */
public class ShowAllPhotoActivity extends Activity {
	private GridView gridView;
	private ProgressBar progressBar;
	private AlbumGridViewAdapter gridImageAdapter;
	// 完成按钮
	private Button okButton;
	// 预览按钮
	private Button preview;
	// 返回按钮
	private Button back;
	// 取消按钮
	private Button cancel;
	// 标题
	private TextView headTitle;
	private Intent intent;
	private Context mContext;
	private int check = 0;
	public static ArrayList<ImageItem> dataList = new ArrayList<ImageItem>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plugin_camera_show_all_photo);
		AlbumBimpUtil.putActivty(this);
		mContext = this;
		back = (Button) findViewById(R.id.showallphoto_back);
		cancel = (Button) findViewById(R.id.showallphoto_cancel);
		preview = (Button) findViewById(R.id.showallphoto_preview);
		okButton = (Button) findViewById(R.id.showallphoto_ok_button);
		headTitle = (TextView) findViewById(R.id.showallphoto_headtitle);
		this.intent = getIntent();
		String folderName = intent.getStringExtra("folderName");
		if (folderName.length() > 8) {
			folderName = folderName.substring(0, 9) + "...";
		}
		headTitle.setText(folderName);
		cancel.setOnClickListener(new CancelListener());
		back.setOnClickListener(new BackListener(intent));
		preview.setOnClickListener(new PreviewListener());
		init();
		initListener();
		isShowOkBt();
	}

	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			gridImageAdapter.notifyDataSetChanged();
		}
	};

	private class PreviewListener implements OnClickListener {
		public void onClick(View v) {
			if (AlbumBimpUtil.tempSelectBitmap.size() > 0) {
				intent.putExtra("position", "2");
				intent.setClass(ShowAllPhotoActivity.this,
						GalleryActivity.class);
				startActivity(intent);
			}
		}

	}

	private class BackListener implements OnClickListener {// 返回按钮监听
		Intent intent;

		public BackListener(Intent intent) {
			this.intent = intent;
		}

		public void onClick(View v) {
			intent.setClass(ShowAllPhotoActivity.this, ImageFileActivity.class);
			startActivity(intent);
			ShowAllPhotoActivity.this.finish();
		}

	}

	private class CancelListener implements OnClickListener {// 取消按钮的监听
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

	private void init() {
		IntentFilter filter = new IntentFilter("data.broadcast.action");
		registerReceiver(broadcastReceiver, filter);
		progressBar = (ProgressBar) findViewById(R.id.showallphoto_progressbar);
		progressBar.setVisibility(View.GONE);
		gridView = (GridView) findViewById(R.id.showallphoto_myGrid);
		gridImageAdapter = new AlbumGridViewAdapter(this, dataList,
				AlbumBimpUtil.tempSelectBitmap);
		gridView.setAdapter(gridImageAdapter);
		okButton = (Button) findViewById(R.id.showallphoto_ok_button);
	}

	private void initListener() {

		gridImageAdapter
				.setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {
					public void onItemClick(final ToggleButton toggleButton,
							int position, boolean isChecked, Button button) {
						if (AlbumBimpUtil.tempSelectBitmap.size() >= AlbumBimpUtil.num
								&& isChecked) {
							button.setVisibility(View.GONE);
							toggleButton.setChecked(false);
							Toast.makeText(ShowAllPhotoActivity.this,
									R.string.only_choose_num, 200).show();
							return;
						}

						if (isChecked) {
							++check;
							button.setVisibility(View.VISIBLE);
							AlbumBimpUtil.tempSelectBitmap.add(dataList
									.get(position));
							okButton.setText("完成" + "("
									+ AlbumBimpUtil.tempSelectBitmap.size()
									+ "/" + AlbumBimpUtil.num + ")");
						} else {
							--check;
							button.setVisibility(View.GONE);
							AlbumBimpUtil.tempSelectBitmap.remove(dataList
									.get(position));
							okButton.setText("完成" + "("
									+ AlbumBimpUtil.tempSelectBitmap.size()
									+ "/" + AlbumBimpUtil.num + ")");
						}
						isShowOkBt();
					}
				});

		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				okButton.setClickable(false);
				AlbumBimpUtil.finshActivities(ShowAllPhotoActivity.this);
				ShowAllPhotoActivity.this.finish();
			}
		});

	}

	public void isShowOkBt() {
		if (AlbumBimpUtil.tempSelectBitmap.size() > 0) {
			okButton.setText("完成" + "(" + AlbumBimpUtil.tempSelectBitmap.size()
					+ "/" + AlbumBimpUtil.num + ")");
			preview.setPressed(true);
			okButton.setPressed(true);
			preview.setClickable(true);
			okButton.setClickable(true);
			okButton.setTextColor(Color.WHITE);
			preview.setTextColor(Color.WHITE);
		} else {
			okButton.setText("完成" + "(" + AlbumBimpUtil.tempSelectBitmap.size()
					+ "/" + AlbumBimpUtil.num + ")");
			preview.setPressed(false);
			preview.setClickable(false);
			okButton.setPressed(false);
			okButton.setClickable(false);
			okButton.setTextColor(Color.parseColor("#E1E0DE"));
			preview.setTextColor(Color.parseColor("#E1E0DE"));
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ShowAllPhotoActivity.this.finish();
			// intent.setClass(ShowAllPhoto.this, ImageFile.class);
			// startActivity(intent);
		}

		return false;

	}

	@Override
	protected void onRestart() {
		isShowOkBt();
		super.onRestart();
	}

}
