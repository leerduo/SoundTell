package com.xh.soundtell.adapter;

import java.util.List;

import com.xh.soundtell.R;
import com.xh.soundtell.adapter.WorksAdapter.ViewHolder;
import com.xh.soundtell.model.FActivity;
import com.xh.soundtell.model.Works;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityAdapter extends BaseAdapter {
	private Context context;
	private List<FActivity> fActivities;

	public ActivityAdapter(Context context, List<FActivity> fActivities) {
		this.context = context;
		this.fActivities = fActivities;
	}

	@Override
	public int getCount() {
		return fActivities.size();
	}

	@Override
	public Object getItem(int position) {
		return fActivities.get(position);
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
					R.layout.list_item_activity, null);
			viewHolder = new ViewHolder();
			viewHolder.list_itme_activity_iv = (ImageView) convertView
					.findViewById(R.id.list_itme_activity_iv);
			viewHolder.list_itme_activity_name = (TextView) convertView
					.findViewById(R.id.list_itme_activity_name);
			viewHolder.list_itme_activity_state = (TextView) convertView
					.findViewById(R.id.list_itme_activity_state);
			convertView.setTag(viewHolder);
		}
		viewHolder = (ViewHolder) convertView.getTag();
		FActivity item = (FActivity) getItem(position);
		viewHolder.list_itme_activity_iv.setBackgroundResource(item
				.getImage_id());
		viewHolder.list_itme_activity_name.setText(item.getActivity_name());
		viewHolder.list_itme_activity_state.setText(item.getActivity_state());
		return convertView;
	}

	class ViewHolder {
		private ImageView list_itme_activity_iv;
		private TextView list_itme_activity_name, list_itme_activity_state;
	}

}
