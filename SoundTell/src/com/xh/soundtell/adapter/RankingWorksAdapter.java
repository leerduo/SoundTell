/*************************************************************************************************
 * 版权所有 (C)2015,  成都市商联汇通技术有限公司
 * 
 * 文件名称：RankingWorks.java
 * 内容摘要：升级服务
 * 当前版本： TODO
 * 作        者： 李加蒙
 * 完成日期：2015-10-21 上午10:56:21
 * 修改记录：
 * 修改日期：2015-10-21 上午10:56:21
 * 版   本  号：
 * 修   改  人：
 * 修改内容：
 ************************************************************************************************/

package com.xh.soundtell.adapter;

import java.util.List;

import com.xh.soundtell.R;
import com.xh.soundtell.model.Ranking;
import com.xh.soundtell.ui.ActivityInfoActivity;
import com.xh.soundtell.view.CircleImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 文件名称：RankingWorks.java
 */
public class RankingWorksAdapter extends BaseAdapter {
	private Context context;
	private List<Ranking> rankings;

	public RankingWorksAdapter(Context context, List<Ranking> rankings) {
		this.context = context;
		this.rankings = rankings;
	}

	@Override
	public int getCount() {
		return rankings.size();
	}

	@Override
	public Object getItem(int position) {
		return rankings.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_item_rankingworks, null);
			viewHolder = new ViewHolder();
			viewHolder.rankingworks_level = (TextView) convertView
					.findViewById(R.id.rankingworks_level);
			viewHolder.rankingworks_worksname = (TextView) convertView
					.findViewById(R.id.rankingworks_worksname);
			viewHolder.rankingworks_concern = (TextView) convertView
					.findViewById(R.id.rankingworks_concern);
			viewHolder.rankingworks_time = (TextView) convertView
					.findViewById(R.id.rankingworks_time);
			viewHolder.rankingworks_username = (TextView) convertView
					.findViewById(R.id.rankingworks_username);
			viewHolder.rankingworks_image = (CircleImageView) convertView
					.findViewById(R.id.rankingworks_image);
			convertView.setTag(viewHolder);
		}
		viewHolder = (ViewHolder) convertView.getTag();
		Ranking ranking = (Ranking) getItem(position);

		if (ActivityInfoActivity.rankingworks.equals("1")) {
			if (ranking.getLevel() == 0) {
				viewHolder.rankingworks_level
						.setBackgroundResource(R.drawable.popular_singer_number_1);
				viewHolder.rankingworks_level.setText("");
				System.out.println("ranking.getLevel():" + ranking.getLevel());
			} else if (ranking.getLevel() == 1) {
				viewHolder.rankingworks_level
						.setBackgroundResource(R.drawable.popular_singer_number_2);
			} else if (ranking.getLevel() == 2) {
				viewHolder.rankingworks_level
						.setBackgroundResource(R.drawable.popular_singer_number_3);
			} else {
				viewHolder.rankingworks_level
						.setBackgroundResource(R.drawable.popular_singer_number_shell);
				viewHolder.rankingworks_level.setText((position + 1) + "");
			}
		} else {
			viewHolder.rankingworks_level
					.setBackgroundResource(R.drawable.music_song_note);
		}

		viewHolder.rankingworks_worksname.setText(ranking.getSongName());
		viewHolder.rankingworks_time.setText(ranking.getTime());
		viewHolder.rankingworks_username.setText(ranking.getUserName());
		viewHolder.rankingworks_image.setImageResource(ranking.getImgId());
		return convertView;
	}

	class ViewHolder {
		TextView rankingworks_level, rankingworks_worksname,
				rankingworks_concern, rankingworks_time, rankingworks_username;
		CircleImageView rankingworks_image;

	}

}
