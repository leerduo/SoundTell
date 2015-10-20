package com.xh.soundtell.adapter;

import java.util.ArrayList;

import com.xh.soundtell.R;
import com.xh.soundtell.model.Ranking;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RankingAdapter extends BaseAdapter{

	@Override
	public int getCount() {
		return ranks.size();
	}
    ArrayList<Ranking> ranks;
	public void setInfo( ArrayList<Ranking> ranks){
		this.ranks=ranks;
	}
	LayoutInflater inflater;
	 public RankingAdapter(Activity act,ArrayList<Ranking> ranks){
		 inflater=LayoutInflater.from(act);
		 this.ranks=ranks;
	 }
	 
	 boolean isSonger;
	 public void setSonger(boolean isSonger){
		 this.isSonger=isSonger;
	 }
	@Override
	public Object getItem(int position) {
		return null;
	}
	@Override
	public long getItemId(int position) {
		return 0;
	}
	class ViewHolder{
		 TextView level;
		 ImageView img;
		 TextView songName;
		 TextView userName;
		 TextView time;
		 RelativeLayout followLayout;
		 TextView follow;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView=inflater.inflate(R.layout.ranking_item, null);
			viewHolder.img=(ImageView) convertView.findViewById(R.id.ranking_img);
			viewHolder.level=(TextView) convertView.findViewById(R.id.ranking_level);
			viewHolder.songName=(TextView) convertView.findViewById(R.id.ranking_songname);
			viewHolder.userName=(TextView) convertView.findViewById(R.id.ranking_username);
			viewHolder.time=(TextView) convertView.findViewById(R.id.ranking_time);
			viewHolder.follow=(TextView) convertView.findViewById(R.id.ranking_follow);
			viewHolder.followLayout=(RelativeLayout) convertView.findViewById(R.id.ranking_follow_rl);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.img.setImageResource(ranks.get(position).getImgId());
		viewHolder.level.setText(ranks.get(position).getLevel()+"");
		viewHolder.songName.setText(ranks.get(position).getSongName());
		viewHolder.userName.setText(ranks.get(position).getUserName());
		viewHolder.time.setText(ranks.get(position).getTime());
		if(position<3){
			viewHolder.level.setTextColor(Color.parseColor("#ff0000"));
		}else
		viewHolder.level.setTextColor(Color.parseColor("#000000"));
		
			viewHolder.follow.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if(((TextView)v).getText().toString().equals("+关注")){
						v.setBackgroundResource(R.drawable.shape_follow_gray);
						((TextView)v).setText("取消关注");
						((TextView)v).setTextColor(Color.parseColor("#BABABA"));
					}else{
						v.setBackgroundResource(R.drawable.shape_follow_yellow);
						((TextView)v).setText("+关注");
						((TextView)v).setTextColor(Color.parseColor("#FF9E14"));
						}
				}
			});
			if(isSonger){
				viewHolder.time.setVisibility(View.GONE);
				viewHolder.followLayout.setVisibility(View.VISIBLE);
	              	}else{
	              		viewHolder.followLayout.setVisibility(View.GONE);
	              		viewHolder.time.setVisibility(View.VISIBLE);
	              	}
			
		return convertView;
	}

	
}
