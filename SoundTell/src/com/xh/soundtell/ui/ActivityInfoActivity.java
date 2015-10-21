package com.xh.soundtell.ui;

import java.util.ArrayList;
import java.util.List;

import com.xh.soundtell.R;
import com.xh.soundtell.adapter.RankingWorksAdapter;
import com.xh.soundtell.model.Ranking;
import com.xh.soundtell.view.MyListView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityInfoActivity extends Activity implements OnClickListener {
	private ImageView head_leftimage, head_rightimage;
	private TextView head_centertext;

	private TextView infoactivity_info, infoactivity_infotv;
	private Button infoactivity_ranking, infoactivity_works;
	private MyListView infoactivity_lv;
	private TextView infoactivity_number;

	private Intent intent;
	private String Activity_name;

	public static String rankingworks = "1";
	private RankingWorksAdapter worksAdapter;
	private List<Ranking> rankings = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_infoactivity);
		intent = getIntent();
		Activity_name = intent.getStringExtra("Activity_name");
		getItem();
		findView();
	}

	private void getItem() {
		rankings = new ArrayList<Ranking>();
		for (int i = 0; i < 10; i++) {
			Ranking ranking = new Ranking(R.drawable.ll, "音诉" + i, "歌曲" + i,
					"10-21 11:3" + i, i);
			rankings.add(ranking);
		}
	}

	private void findView() {
		head_centertext = (TextView) findViewById(R.id.head_centertext);
		head_centertext.setVisibility(View.VISIBLE);
		head_centertext.setTextColor(getResources().getColor(R.color.app_red));
		head_centertext.setText(Activity_name);
		System.out.println("Activity_name" + Activity_name);

		head_leftimage = (ImageView) findViewById(R.id.head_leftimage);
		head_leftimage.setImageResource(R.drawable.back_black);
		head_leftimage.setVisibility(View.VISIBLE);
		head_leftimage.setOnClickListener(this);

		head_rightimage = (ImageView) findViewById(R.id.head_rightimage);
		head_rightimage.setImageResource(R.drawable.huodong_share_btn);
		head_rightimage.setVisibility(View.VISIBLE);

		infoactivity_info = (TextView) findViewById(R.id.infoactivity_info);
		infoactivity_info.setOnClickListener(this);
		infoactivity_infotv = (TextView) findViewById(R.id.infoactivity_infotv);
		if (Activity_name.equals("一首歌证明我是小清新")) {
			infoactivity_infotv.setText(R.string.infoactivity3);
		} else if (Activity_name.equals("唱首情歌给闺蜜")) {
			infoactivity_infotv.setText(R.string.infoactivity4);
		} else if (Activity_name.equals("青春一起歌唱")) {
			infoactivity_infotv.setText(R.string.infoactivity5);
		} else if (Activity_name.equals("这是唱给爸爸的歌")) {
			infoactivity_infotv.setText(R.string.infoactivity6);
		} else if (Activity_name.equals("有一种声音叫做夏天")) {
			infoactivity_infotv.setText(R.string.infoactivity7);
		}

		infoactivity_ranking = (Button) findViewById(R.id.infoactivity_ranking);
		infoactivity_works = (Button) findViewById(R.id.infoactivity_works);
		infoactivity_ranking.setOnClickListener(this);
		infoactivity_works.setOnClickListener(this);

		infoactivity_lv = (MyListView) findViewById(R.id.infoactivity_lv);
		if (rankings != null) {
			worksAdapter = new RankingWorksAdapter(this, rankings);
			infoactivity_lv.setAdapter(worksAdapter);
		}
		infoactivity_lv.setFocusable(false);
		infoactivity_number = (TextView) findViewById(R.id.infoactivity_number);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.head_leftimage:
			ActivityInfoActivity.this.finish();
			break;
		case R.id.infoactivity_ranking:
			rankingworks = "1";
			infoactivity_ranking
					.setBackgroundResource(R.drawable.shape_rectangle_ranking_pressed);
			infoactivity_works
					.setBackgroundResource(R.drawable.shape_rectangle_works_normal);
			infoactivity_ranking.setText("排行榜");
			infoactivity_works.setText("最新作品");
			infoactivity_ranking.setTextColor(getResources().getColor(
					R.color.white));
			infoactivity_works.setTextColor(getResources().getColor(
					R.color.ranking_info));
			if (rankings != null) {
				worksAdapter = new RankingWorksAdapter(this, rankings);
				infoactivity_lv.setAdapter(worksAdapter);
			}
			break;
		case R.id.infoactivity_works:
			rankingworks = "2";
			infoactivity_ranking
					.setBackgroundResource(R.drawable.shape_rectangle_ranking_normal);
			infoactivity_works
					.setBackgroundResource(R.drawable.shape_rectangle_works_pressed);
			infoactivity_ranking.setText("排行榜");
			infoactivity_works.setText("最新作品");
			infoactivity_ranking.setTextColor(getResources().getColor(
					R.color.ranking_info));
			infoactivity_works.setTextColor(getResources().getColor(
					R.color.white));

			if (rankings != null) {
				worksAdapter = new RankingWorksAdapter(this, rankings);
				infoactivity_lv.setAdapter(worksAdapter);
			}
			break;
		case R.id.infoactivity_info:
			Intent intent = new Intent(ActivityInfoActivity.this,
					RuleActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

}
