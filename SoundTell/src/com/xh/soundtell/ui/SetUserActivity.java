package com.xh.soundtell.ui;

import com.xh.soundtell.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SetUserActivity extends Activity implements OnClickListener {
	private TextView head_centertext;
	private ImageView head_rightimage, head_leftimage;

	private EditText setuser_name, setuser_collect;
	private TextView setuser_man, setuser_woman, setuser_wordcount;

	private Intent intent;
	private String st;
	private String setuser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setuser);
		intent = getIntent();
		st = intent.getStringExtra("data");
		findView();
	}

	private void findView() {
		head_centertext = (TextView) findViewById(R.id.head_centertext);
		head_centertext.setVisibility(View.VISIBLE);

		head_rightimage = (ImageView) findViewById(R.id.head_rightimage);
		head_rightimage.setVisibility(View.VISIBLE);
		head_rightimage.setImageResource(R.drawable.modify_finish);
		head_rightimage.setOnClickListener(this);
		head_leftimage = (ImageView) findViewById(R.id.head_leftimage);
		head_leftimage.setVisibility(View.VISIBLE);
		head_leftimage.setImageResource(R.drawable.back_wihte);
		head_leftimage.setOnClickListener(this);

		setuser_name = (EditText) findViewById(R.id.setuser_name);

		setuser_man = (TextView) findViewById(R.id.setuser_man);
		setuser_woman = (TextView) findViewById(R.id.setuser_woman);
		setuser_man.setOnClickListener(this);
		setuser_woman.setOnClickListener(this);

		setuser_collect = (EditText) findViewById(R.id.setuser_collect);
		setuser_wordcount = (TextView) findViewById(R.id.setuser_wordcount);

		if (st.equals("name")) {
			head_centertext.setText("姓名");
			setuser_name.setVisibility(View.VISIBLE);
		} else if (st.equals("sex")) {
			head_rightimage.setVisibility(View.GONE);
			head_centertext.setText("性别");
			setuser_man.setVisibility(View.VISIBLE);
			setuser_woman.setVisibility(View.VISIBLE);
		} else if (st.equals("collect")) {
			head_centertext.setText("简介");
			setuser_collect.setVisibility(View.VISIBLE);
			setuser_wordcount.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onClick(View v) {
		Intent data;
		switch (v.getId()) {
		case R.id.head_leftimage:
			SetUserActivity.this.finish();
			break;
		case R.id.head_rightimage:
			if (st.equals("name")) {
				setuser = setuser_name.getText().toString().trim();
			} else if (st.equals("collect")) {
				setuser = setuser_collect.getText().toString().trim();
			}
			data = new Intent();
			data.putExtra("data", st);
			data.putExtra("info", setuser);
			setResult(RESULT_OK, data);
			SetUserActivity.this.finish();
			break;
		case R.id.setuser_man:
			setuser = setuser_man.getText().toString().trim();
			data = new Intent();
			data.putExtra("data", st);
			data.putExtra("info", setuser);
			setResult(RESULT_OK, data);
			SetUserActivity.this.finish();
			break;
		case R.id.setuser_woman:
			setuser = setuser_woman.getText().toString().trim();
			data = new Intent();
			data.putExtra("data", st);
			data.putExtra("info", setuser);
			setResult(RESULT_OK, data);
			SetUserActivity.this.finish();
			break;
		default:
			break;
		}
	}
}
