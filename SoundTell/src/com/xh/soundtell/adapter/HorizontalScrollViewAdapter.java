package com.xh.soundtell.adapter;

import java.util.List;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xh.soundtell.R;

public class HorizontalScrollViewAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private List<Integer> iamges;

	public HorizontalScrollViewAdapter(Context context, List<Integer> iamges) {
		this.mContext = context;
		mInflater = LayoutInflater.from(mContext);
		this.iamges = iamges;
	}

	public int getCount() {
		return iamges.size();
	}

	public Object getItem(int position) {
		return iamges.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.list_item_horizontal,
					parent, false);
			viewHolder.list_item_horizontal_iv = (ImageView) convertView
					.findViewById(R.id.list_item_horizontal_iv);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// viewHolder.list_item_horizontal_iv.setImageDrawable(Constant.domain
		// + iamges[position]);
		int item = (Integer) getItem(position);
		System.out.println("item" + item);

		viewHolder.list_item_horizontal_iv.setImageResource(item);
		return convertView;
	}

	private class ViewHolder {
		ImageView list_item_horizontal_iv;
	}

}
