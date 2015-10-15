package com.xh.soundtell.ui;

import com.xh.soundtell.AuditionActivity;
import com.xh.soundtell.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TextView;

public class PersonalOptionActivity extends Activity implements OnClickListener{
	
 private	TextView banzou,geci;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_personal_option);
      isBuZou=true;
      banzou=(TextView) findViewById(R.id.personaloption_banzou);
      geci=(TextView) findViewById(R.id.personaloption_geci);
      banzou.setOnClickListener(this);
      geci.setOnClickListener(this);
      scrollView = (ScrollView) findViewById(R.id.personaloption_sv);
	  findViewById(R.id.peraloption_back).setOnClickListener(this);
	  findViewById(R.id.personaloption_zhuti).setOnClickListener(this);
	}
	private boolean isBuZou;
	private ScrollView scrollView;
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.peraloption_back:
			onBackPressed();
			break;
		case R.id.personaloption_zhuti:
         Intent intent=new Intent(this,AuditionActivity.class);
		startActivity(intent);	
         break;
		case R.id.personaloption_banzou:
			if(!isBuZou){
				isBuZou=true;
				banzou.setTextColor(Color.parseColor("#ffffff"));	
				banzou.setBackgroundResource(R.drawable.shape_bg_personaloption_sel_left);
				
				geci.setTextColor(getResources().getColor(R.color.app_red));	
				geci.setBackgroundResource(R.drawable.shape_bg_personaloption_right);
				scrollView.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.personaloption_geci:
			if(isBuZou){
				isBuZou=false;
				scrollView.setVisibility(View.GONE);
				geci.setTextColor(Color.parseColor("#ffffff"));	
				geci.setBackgroundResource(R.drawable.shape_bg_personaloption_sel_right);
				
				banzou.setTextColor(getResources().getColor(R.color.app_red));	
				banzou.setBackgroundResource(R.drawable.shape_bg_personaloption_left);
			}
			break;

		}
	}
}
