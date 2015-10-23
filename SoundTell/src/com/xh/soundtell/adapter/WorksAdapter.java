package com.xh.soundtell.adapter;

import java.util.List;

import com.xh.soundtell.R;
import com.xh.soundtell.model.MusicInfomation;
import com.xh.soundtell.model.Works;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WorksAdapter extends BaseAdapter {
	private Context context;
	private List<MusicInfomation> mis;

	public WorksAdapter(Context context, List<MusicInfomation> mis) {
		this.context = context;
		this.mis = mis;
	}

	@Override
	public int getCount() {
		return mis.size();
	}

	@Override
	public Object getItem(int position) {
		return mis.get(position);
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
					R.layout.list_item_works, null);
			viewHolder = new ViewHolder();
			viewHolder.list_item_works_name = (TextView) convertView
					.findViewById(R.id.list_item_works_name);
			viewHolder.list_item_works_time = (TextView) convertView
					.findViewById(R.id.list_item_works_time);
			convertView.setTag(viewHolder);
		}
		viewHolder = (ViewHolder) convertView.getTag();
		MusicInfomation mi = (MusicInfomation) getItem(position);
		viewHolder.list_item_works_name.setText(mi.getMusicName());
		viewHolder.list_item_works_time.setText(mi.getMusicAlbum());
		return convertView;
	}

	class ViewHolder {
		private TextView list_item_works_name, list_item_works_time;
	}
}
