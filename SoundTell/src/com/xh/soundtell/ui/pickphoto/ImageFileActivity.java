package com.xh.soundtell.ui.pickphoto;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.xh.soundtell.R;
import com.xh.soundtell.adapter.FolderAdapter;

/**
 * 这个类主要是用来进行显示包含图片的文件夹
 * @author 陈宝林
 * @version 2015年8月13日  下午13:48:06
 */
public class ImageFileActivity extends Activity {

	private FolderAdapter folderAdapter;
	private Button bt_cancel;
	private Context mContext;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plugin_camera_image_file);
		AlbumBimpUtil.putActivty(this);
		mContext = this;
		bt_cancel = (Button) findViewById(R.id.cancel);
		bt_cancel.setOnClickListener(new CancelListener());
		GridView gridView = (GridView) findViewById(R.id.fileGridView);
		TextView textView = (TextView) findViewById(R.id.headerTitle);
		textView.setText(R.string.photo);
		folderAdapter = new FolderAdapter(this);
		gridView.setAdapter(folderAdapter);
	}

	private class CancelListener implements OnClickListener {// 取消按钮的监听
		public void onClick(View v) {
//				Intent intent = new Intent(getApplicationContext(),PointsmanActivity.class);
//				startActivity(intent);
				ImageFileActivity.this.finish();
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ImageFileActivity.this.finish();
//			Intent intent = new Intent();
//			intent.setClass(getApplicationContext(), PointsmanActivity.class);
//			startActivity(intent);
		}
		
		return true;
	}

}
