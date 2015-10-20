package com.xh.soundtell.ui;

import java.util.ArrayList;

import com.xh.soundtell.ListenCreateActivity;
import com.xh.soundtell.R;
import com.xh.soundtell.R.id;
import com.xh.soundtell.R.layout;
import com.xh.soundtell.R.menu;
import com.xh.soundtell.WriteCreateActivity;
import com.xh.soundtell.adapter.RankingAdapter;
import com.xh.soundtell.model.Ranking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class NewCiActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_ci);
		findViewById(R.id.newci_back).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) { 
             onBackPressed();				
			}
		});
		 ListView  lv=(ListView) findViewById(R.id.newci_lv);
		 ArrayList<Ranking> ranks=new ArrayList<Ranking>();
		 Ranking ranking1=new Ranking(R.drawable.hotrank_default, "一卡拉", "我们的Show", "2015-10-17", 1);
		 Ranking ranking2=new Ranking(R.drawable.hotrank_default, "我爱", "我们的Show", "2015-10-17", 2);
		 Ranking ranking3=new Ranking(R.drawable.console_recording_room, "一卡拉", "我们的Show", "2015-10-17", 3);
		 Ranking ranking4=new Ranking(R.drawable.console_bedroom, "一嘻嘻", "我们的Show", "2015-10-17", 4);
		 Ranking ranking5=new Ranking(R.drawable.hotrank_default, "卡拉", "我们的Show", "2015-10-17", 5);
		 Ranking ranking6=new Ranking(R.drawable.ic_launcher, "乎卡拉", "我们的Show", "2015-10-17", 6);
		 Ranking ranking7=new Ranking(R.drawable.hotrank_default, "一都撒拉", "我们的Show", "2015-10-17", 7);
		 ranks.add(ranking1);
		 ranks.add(ranking2);
		 ranks.add(ranking3);
		 ranks.add(ranking4);
		 ranks.add(ranking5);
		 ranks.add(ranking6);
		 ranks.add(ranking7);
		 RankingAdapter  adapter=new RankingAdapter(this, ranks);
		 lv.setAdapter(adapter);
		 findViewById(R.id.newci_create).setOnClickListener(this);
		 findViewById(R.id.newci_listen).setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		Intent intent=null;
		switch (v.getId()) {
		case R.id.newci_create://直接创作
		intent=new Intent(this,ListenCreateActivity.class);	
			break;
		case R.id.newci_listen://听歌写词
			intent=new Intent(this,WriteCreateActivity.class);	
			break;
		}
		if(intent!=null){
			startActivity(intent);
		}
	}
}
