package com.xh.soundtell;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class SongStyleSearch extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.song_style_search);
		findViewById(R.id.search_cancel).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			onBackPressed();
			}
		});
		
	}

}
