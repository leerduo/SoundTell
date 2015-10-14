/*************************************************************************************************
 * 版权所有 (C)2015,  成都市商联汇通技术有限公司
 * 
 * 文件名称：CommentAdapter.java
 * 内容摘要：升级服务
 * 当前版本： TODO
 * 作        者： 李加蒙
 * 完成日期：2015-10-14 上午10:03:17
 * 修改记录：
 * 修改日期：2015-10-14 上午10:03:17
 * 版   本  号：
 * 修   改  人：
 * 修改内容：
 ************************************************************************************************/

package com.xh.soundtell.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xh.soundtell.R;
import com.xh.soundtell.model.Comment;

/**
 * 文件名称：CommentAdapter.java
 */
public class CommentAdapter extends BaseAdapter {
	private Context context;
	private List<Comment> comments;

	public CommentAdapter(Context context, List<Comment> comments) {
		this.context = context;
		this.comments = comments;
	}

	@Override
	public int getCount() {
		return comments.size();
	}

	@Override
	public Object getItem(int position) {
		return comments.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_item_comment, null);
			viewHolder.list_itme_comment_iv = (ImageView) convertView
					.findViewById(R.id.list_itme_comment_iv);
			viewHolder.list_itme_comment_name = (TextView) convertView
					.findViewById(R.id.list_itme_comment_name);
			viewHolder.list_itme_comment_time = (TextView) convertView
					.findViewById(R.id.list_itme_comment_time);
			viewHolder.list_itme_comment_content = (TextView) convertView
					.findViewById(R.id.list_itme_comment_content);
			convertView.setTag(viewHolder);
		}
		viewHolder = (ViewHolder) convertView.getTag();
		Comment comment = (Comment) getItem(position);
		System.out.println("comment.getUsername()" + comment.getUsername());
		viewHolder.list_itme_comment_iv.setImageResource(comment
				.getImageid());
		viewHolder.list_itme_comment_name.setText(comment.getUsername());
		viewHolder.list_itme_comment_time.setText(comment.getTime());
		viewHolder.list_itme_comment_content.setText(comment.getContent());
		return convertView;
	}

	class ViewHolder {
		private ImageView list_itme_comment_iv;
		private TextView list_itme_comment_name, list_itme_comment_time,
				list_itme_comment_content;
	}

}
