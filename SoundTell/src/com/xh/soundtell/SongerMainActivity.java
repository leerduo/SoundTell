package com.xh.soundtell;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class SongerMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_songer_main);
        findViewById(R.id.songer_back).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
           		}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
	
	
}
