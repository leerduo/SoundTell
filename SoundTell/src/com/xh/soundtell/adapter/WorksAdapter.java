package com.xh.soundtell.adapter;

import java.util.List;

import com.xh.soundtell.R;
import com.xh.soundtell.model.Works;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WorksAdapter extends BaseAdapter {
	private Context context;
	private List<Works> works;

	public WorksAdapter(Context context, List<Works> works) {
		this.context = context;
		this.works = works;
	}

	@Override
	public int getCount() {
		return works.size();
	}

	@Override
	public Object getItem(int position) {
		return works.get(position);
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
		Works item = (Works) getItem(position);
		viewHolder.list_item_works_name.setText(item.getName());
		viewHolder.list_item_works_time.setText(item.getTime());
		return convertView;
	}

	class ViewHolder {
		private TextView list_item_works_name, list_item_works_time;
	}
}
