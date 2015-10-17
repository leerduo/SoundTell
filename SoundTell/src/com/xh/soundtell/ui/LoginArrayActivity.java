package com.xh.soundtell.ui;

import com.xh.soundtell.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class LoginArrayActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginarray);
		findViewById(R.id.loginarray_return).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						LoginArrayActivity.this.finish();

					};
				});
	}

}
