package com.xh.soundtell.ui;

import com.xh.soundtell.R;
import com.xh.soundtell.util.PrefUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

	private PrefUtil prefUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setuser);
		prefUtil = PrefUtil.getInstance();
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
		setuser_collect.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.length() > 70) {
					setuser_collect.setText(s.subSequence(0, 70));
					setuser_collect.setSelection(setuser_collect.length());
				}
			}
		});
		setuser_wordcount = (TextView) findViewById(R.id.setuser_wordcount);

		if (st.equals("name")) {
			head_centertext.setText("姓名");
			setuser_name.setVisibility(View.VISIBLE);

			if (prefUtil.getUserName() != null
					&& !prefUtil.getUserName().equals("0")) {
				setuser_name.setText(prefUtil.getUserName());
			}

		} else if (st.equals("sex")) {
			head_rightimage.setVisibility(View.GONE);
			head_centertext.setText("性别");
			setuser_man.setVisibility(View.VISIBLE);
			setuser_woman.setVisibility(View.VISIBLE);
		} else if (st.equals("collect")) {
			head_centertext.setText("简介");
			setuser_collect.setVisibility(View.VISIBLE);
			setuser_wordcount.setVisibility(View.VISIBLE);
			if (prefUtil.getIntro() != null && !prefUtil.getIntro().equals("0")) {
				setuser_collect.setText(prefUtil.getIntro());
				setuser_collect.setSelection(setuser_collect.length());
			}
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
