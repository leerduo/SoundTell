package com.xh.soundtell.ui;

import com.xh.soundtell.R;
import com.xh.soundtell.setting.SettingHelper;
import com.xh.soundtell.util.ToastUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SetActivity extends Activity implements OnClickListener {
	private TextView head_centertext;
	private ImageView head_leftimage;

	private RelativeLayout set_data, set_image, set_friend, set_feedback,
			set_update, set_cache, set_about, set_exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set);
		findView();
	}

	// set_data
	// set_image
	// set_friend
	// set_feedback
	// set_update
	// set_cache
	// set_about
	// set_exit
	private void findView() {
		head_centertext = (TextView) findViewById(R.id.head_centertext);
		head_centertext.setVisibility(View.VISIBLE);
		head_centertext.setText("设置");

		head_leftimage = (ImageView) findViewById(R.id.head_leftimage);
		head_leftimage.setVisibility(View.VISIBLE);
		head_leftimage.setImageResource(R.drawable.back_wihte);
		head_leftimage.setOnClickListener(this);

		set_data = (RelativeLayout) findViewById(R.id.set_data);
		set_image = (RelativeLayout) findViewById(R.id.set_image);
		set_friend = (RelativeLayout) findViewById(R.id.set_friend);
		set_feedback = (RelativeLayout) findViewById(R.id.set_feedback);
		set_update = (RelativeLayout) findViewById(R.id.set_update);
		set_cache = (RelativeLayout) findViewById(R.id.set_cache);
		set_about = (RelativeLayout) findViewById(R.id.set_about);
		set_exit = (RelativeLayout) findViewById(R.id.set_exit);

		set_data.setOnClickListener(this);
		set_image.setOnClickListener(this);
		set_friend.setOnClickListener(this);
		set_feedback.setOnClickListener(this);
		set_update.setOnClickListener(this);
		set_cache.setOnClickListener(this);
		set_about.setOnClickListener(this);
		set_exit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.head_leftimage:
			onBackPressed();
			break;
		case R.id.set_data:
			intent = new Intent(SetActivity.this, UserInfoActivity.class);
			break;
		case R.id.set_image:
			intent = new Intent(SetActivity.this, UploadImageActivity.class);
			break;
		case R.id.set_friend:
			intent = new Intent(SetActivity.this, FriendActivity.class);
			break;
		case R.id.set_feedback:
			intent = new Intent(SetActivity.this, ActivityFeedBack.class);
			break;
		case R.id.set_update:
			ToastUtil.makeToast(SetActivity.this, "当前版本是最新版本");
			break;
		case R.id.set_cache:
			ToastUtil.makeToast(SetActivity.this, "清除缓存成功");
			break;
		case R.id.set_about:
			intent = new Intent(SetActivity.this, AboutActivity.class);
			break;
		case R.id.set_exit:
			// intent = new Intent(SetActivity.this, LoginArrayActivity.class);
			SettingHelper.getInstance().setUserInfo(null);
			// startActivity(new Intent(SetActivity.this, MainActivity.class));
			Intent intent2 = new Intent();
			intent2.putExtra("my", "200");
			setResult(200, intent2);
			finish();
			break;

		default:
			break;
		}
		if (intent != null) {
			startActivity(intent);
		}
	}

	@Override
	public void onBackPressed() {
		setResult(RESULT_OK);
		super.onBackPressed();
	}
}
