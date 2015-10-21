package com.xh.soundtell.ui;

import com.xh.soundtell.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author Administrator 确认上传界面
 */
public class ConfirmImageActivity extends Activity implements OnClickListener {
	// 字体 以及返回按钮
	private TextView head_centertext;
	private ImageView head_leftimage;

	private Button upload, cancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmimage);
		findView();
	}

	private void findView() {
		head_centertext = (TextView) findViewById(R.id.head_centertext);
		head_centertext.setVisibility(View.VISIBLE);
		head_centertext.setText("手机相册");

		head_leftimage = (ImageView) findViewById(R.id.head_leftimage);
		head_leftimage.setVisibility(View.VISIBLE);
		head_leftimage.setImageResource(R.drawable.back_wihte);
		head_leftimage.setOnClickListener(this);

		upload = (Button) findViewById(R.id.upload);
		cancel = (Button) findViewById(R.id.cancel);
		upload.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.head_leftimage:
			ConfirmImageActivity.this.finish();
			break;
		case R.id.upload:
			// ConfirmImageActivity.this.finish();
			break;
		case R.id.cancel:
			ConfirmImageActivity.this.finish();
			break;

		default:
			break;
		}

	}
}
