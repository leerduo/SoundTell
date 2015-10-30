package com.xh.soundtell.ui;

import java.util.ArrayList;

import com.xh.soundtell.R;
import com.xh.soundtell.R.id;
import com.xh.soundtell.R.layout;
import com.xh.soundtell.R.menu;
import com.xh.soundtell.adapter.RankingAdapter;
import com.xh.soundtell.model.Ranking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class HotActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hot_activity);
		findViewById(R.id.hot_back).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		ListView lv = (ListView) findViewById(R.id.newci_lv);
		ArrayList<Ranking> ranks = new ArrayList<Ranking>();
		Ranking ranking1 = new Ranking(R.drawable.hotrank_default, "林肯公园里的钟声",
				"因你而在", "2015-08-16", 1);
		Ranking ranking2 = new Ranking(R.drawable.hotrank_default, "奋斗的刚子",
				"再见再见", "2015-02-07", 2);
		Ranking ranking3 = new Ranking(R.drawable.console_recording_room,
				"啊·原来是小佟", "午夜DJ", "2015-02-08", 3);
		Ranking ranking4 = new Ranking(R.drawable.console_bedroom, "徐嘉苇Jovi",
				"滴答答", "2015-08-18", 4);
		Ranking ranking5 = new Ranking(R.drawable.hotrank_default, "Beo tea",
				"不说", "2015-08-18", 5);
		Ranking ranking6 = new Ranking(R.drawable.ic_launcher, "西粒子黄了", "镜花水月",
				"2015-09-03", 6);
		Ranking ranking7 = new Ranking(R.drawable.hotrank_default, "一都撒拉",
				"我们的Show", "2015-10-17", 7);
		ranks.add(ranking1);
		ranks.add(ranking2);
		ranks.add(ranking3);
		ranks.add(ranking4);
		ranks.add(ranking5);
		ranks.add(ranking6);
		ranks.add(ranking7);
		RankingAdapter adapter = new RankingAdapter(this, ranks);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = null;
//				position = position % 3;
				switch (position) {
				case 0:
					intent = new Intent(HotActivity.this,
							PlayMusicActivity.class);
					intent.putExtra("hotfragment", "hotfragment");
					intent.putExtra("songId", R.raw.exist_for_you_song);
					intent.putExtra("songName", "因你而在");
					intent.putExtra("songSonger", "林肯公园里的钟声");
					intent.putExtra("songImg", R.drawable.demof0);
					intent.putExtra("songBody",
					getResources().getString(R.string.exist_for_you));
					break;
				case 1:
					intent = new Intent(HotActivity.this,
							PlayMusicActivity.class);
					intent.putExtra("hotfragment", "hotfragment");
					intent.putExtra("songImg", R.drawable.demof1);
					intent.putExtra("songId", R.raw.li_byebye_song);
					intent.putExtra("songName", "再见 再见");
					intent.putExtra("songSonger", "奋斗的刚子");
					intent.putExtra("songBody",
					getResources().getString(R.string.li_byebye));
					break;
				case 2:
					intent = new Intent(HotActivity.this,
							PlayMusicActivity.class);
					intent.putExtra("hotfragment", "hotfragment");
					intent.putExtra("songImg", R.drawable.demof2);
					intent.putExtra("songId", R.raw.w_nightdj_song);
					intent.putExtra("songName", "午夜DJ");
					intent.putExtra("songSonger", "啊·原来是小佟");
					intent.putExtra("songBody",
							getResources().getString(R.string.w_nightdj));
					break;
				}
				if (intent != null) {
					startActivity(intent);
				}
			}
		});

	}

}
