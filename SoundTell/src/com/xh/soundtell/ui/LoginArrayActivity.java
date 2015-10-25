package com.xh.soundtell.ui;

import com.xh.soundtell.R;
import com.xh.soundtell.RegisterActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class LoginArrayActivity extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginarray);
		findViewById(R.id.loginarray_login).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(LoginArrayActivity.this,
								LoginActivity.class));
						LoginArrayActivity.this.finish();
					};
				});
		findViewById(R.id.login_back).setOnClickListener(this);
		findViewById(R.id.login_register).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_back:
			onBackPressed();
			break;
		case R.id.login_register:
			Intent intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			break;
		}
	}
}
