package com.xh.soundtell;

import com.xh.soundtell.music.MusicHelper;
import com.xh.soundtell.ui.SingRecordActivity;
import com.xh.soundtell.ui.sound.RecordActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AuditionActivity extends Activity implements OnClickListener{

	
	TextView tvAudition1,tvAudition2;
	TextView tvUse1,tvUse2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_audition);
		
		tvAudition1=(TextView) findViewById(R.id.audition_1);
		tvAudition2=(TextView) findViewById(R.id.audition_2);
		tvUse1=(TextView) findViewById(R.id.audition_use1);
		tvUse2=(TextView) findViewById(R.id.audition_use2);
		tvAudition1.setOnClickListener(this);
		tvAudition2.setOnClickListener(this);
		tvUse1.setOnClickListener(this);
		tvUse2.setOnClickListener(this);
		findViewById(R.id.audition_back).setOnClickListener(this);
	}

	
	String bgColor1="#EAE8E8";
	String tvColor1="#898989";
	
	String bgColor2="#A4E6FE";
	String tvColor2="#5499B2";
	 boolean isPlay1,isPlay2;
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.audition_back:
			onBackPressed();
			break;
		case R.id.audition_1:
			if(isPlay2){
				isPlay2=false;
				isPlay1=true;
				
				tvAudition2.setTextColor(Color.parseColor(tvColor1));
				tvAudition2.setBackgroundColor(Color.parseColor(bgColor1));
				tvAudition2.setText("试听");
				MusicHelper.stopMusic();
				
				tvAudition1.setTextColor(Color.parseColor(tvColor2));
				tvAudition1.setBackgroundColor(Color.parseColor(bgColor2));
				tvAudition1.setText("取消试听");
			    MusicHelper.startMusic(this, R.raw.exist_for_you_song, false);
			 return;
			}
			if(isPlay1){
				isPlay1=false;
				tvAudition1.setTextColor(Color.parseColor(tvColor1));
				tvAudition1.setBackgroundColor(Color.parseColor(bgColor1));
				tvAudition1.setText("试听");
				MusicHelper.stopMusic();
			}else{
				isPlay1=true;	
				tvAudition1.setTextColor(Color.parseColor(tvColor2));
				tvAudition1.setBackgroundColor(Color.parseColor(bgColor2));
				tvAudition1.setText("取消试听");
			    MusicHelper.startMusic(this, R.raw.exist_for_you_song, false);
			}
			break;
		case R.id.audition_2:
			if(isPlay1){
				isPlay2=true;
				
				isPlay1=false;
				tvAudition1.setTextColor(Color.parseColor(tvColor1));
				tvAudition1.setBackgroundColor(Color.parseColor(bgColor1));
				tvAudition1.setText("试听");
				MusicHelper.stopMusic();
				
				tvAudition2.setTextColor(Color.parseColor(tvColor2));
				tvAudition2.setBackgroundColor(Color.parseColor(bgColor2));
				tvAudition2.setText("取消试听");
			    MusicHelper.startMusic(this, R.raw.exist_for_you_song, false);
			 return;
			}
			if(isPlay2){
				isPlay2=false;
				tvAudition2.setTextColor(Color.parseColor(tvColor1));
				tvAudition2.setBackgroundColor(Color.parseColor(bgColor1));
				tvAudition2.setText("试听");
				MusicHelper.stopMusic();
			}else{
				isPlay2=true;	
				tvAudition2.setTextColor(Color.parseColor(tvColor2));
				tvAudition2.setBackgroundColor(Color.parseColor(bgColor2));
				tvAudition2.setText("取消试听");
			    MusicHelper.startMusic(this, R.raw.exist_for_you_song, false);
			}
			
			break;
		case R.id.audition_use1:
		case R.id.audition_use2:
			Intent intent=new Intent(this,SingRecordActivity.class);
			startActivity(intent);
			MusicHelper.stopMusic();
			
			isPlay2=false;
			tvAudition2.setTextColor(Color.parseColor(tvColor1));
			tvAudition2.setBackgroundColor(Color.parseColor(bgColor1));
			tvAudition2.setText("试听");
			isPlay1=false;
			tvAudition1.setTextColor(Color.parseColor(tvColor1));
			tvAudition1.setBackgroundColor(Color.parseColor(bgColor1));
			tvAudition1.setText("试听");
			break;
		}
	}
	
	
}
