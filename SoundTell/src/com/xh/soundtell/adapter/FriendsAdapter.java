package com.xh.soundtell.adapter;

import java.util.List;

import com.xh.soundtell.R;
import com.xh.soundtell.adapter.ActivityAdapter.ViewHolder;
import com.xh.soundtell.model.FActivity;
import com.xh.soundtell.model.Friends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendsAdapter extends BaseAdapter {
	private Context context;
	private List<Friends> friends;

	public FriendsAdapter(Context context, List<Friends> friends) {
		this.context = context;
		this.friends = friends;
	}

	@Override
	public int getCount() {
		return friends.size();
	}

	@Override
	public Object getItem(int position) {
		return friends.get(position);
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
					R.layout.list_item_friends, null);
			viewHolder = new ViewHolder();
			viewHolder.list_itme_activity_iv = (ImageView) convertView
					.findViewById(R.id.list_itme_activity_iv);
			viewHolder.list_itme_friends_name = (TextView) convertView
					.findViewById(R.id.list_itme_friends_name);
			viewHolder.list_itme_friends_intro = (TextView) convertView
					.findViewById(R.id.list_itme_friends_intro);
			convertView.setTag(viewHolder);
		}
		viewHolder = (ViewHolder) convertView.getTag();
		Friends item = (Friends) getItem(position);
		viewHolder.list_itme_activity_iv.setBackgroundResource(item.getImage());
		viewHolder.list_itme_friends_name.setText(item.getName());
		viewHolder.list_itme_friends_intro.setText(item.getIntro());
		return convertView;
	}

	class ViewHolder {
		private ImageView list_itme_activity_iv;
		private TextView list_itme_friends_name, list_itme_friends_intro;
	}

}
