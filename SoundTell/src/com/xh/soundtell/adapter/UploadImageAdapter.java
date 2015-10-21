///*************************************************************************************************
// * 版权所有 (C)2015,  成都市商联汇通技术有限公司
// * 
// * 文件名称：UploadImageAdapter.java
// * 内容摘要：升级服务
// * 当前版本： TODO
// * 作        者： 李加蒙
// * 完成日期：2015-10-21 下午3:18:27
// * 修改记录：
// * 修改日期：2015-10-21 下午3:18:27
// * 版   本  号：
// * 修   改  人：
// * 修改内容：
// ************************************************************************************************/
//
//package com.xh.soundtell.adapter;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.graphics.BitmapFactory;
//import android.os.Handler;
//import android.os.Message;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//
//import com.xh.soundtell.R;
//import com.xh.soundtell.ui.pickphoto.AlbumBimpUtil;
//
///**
// * 文件名称：UploadImageAdapter.java
// */
//public class UploadImageAdapter extends BaseAdapter {
//	private LayoutInflater inflater;
//	private int selectedPosition = -1;
//	private boolean shape;
//
//	public boolean isShape() {
//		return shape;
//	}
//
//	public void setShape(boolean shape) {
//		this.shape = shape;
//	}
//
//	public GridAdapter(Context context) {
//		inflater = LayoutInflater.from(context);
//	}
//
//	public void update() {
//		loading();
//	}
//
//	public int getCount() {
//		if (AlbumBimpUtil.tempSelectBitmap.size() == 8) {
//			return 8;
//		}
//		return (AlbumBimpUtil.tempSelectBitmap.size() + 1);
//	}
//
//	public Object getItem(int arg0) {
//		return null;
//	}
//
//	public long getItemId(int arg0) {
//		return 0;
//	}
//
//	public void setSelectedPosition(int position) {
//		selectedPosition = position;
//	}
//
//	public int getSelectedPosition() {
//		return selectedPosition;
//	}
//
//	public View getView(int position, View convertView, ViewGroup parent) {
//		ViewHolder holder = null;
//		if (convertView == null) {
//			convertView = inflater.inflate(R.layout.item_published_grida,
//					parent, false);
//			holder = new ViewHolder();
//			holder.image = (ImageView) convertView
//					.findViewById(R.id.item_grida_image);
//			convertView.setTag(holder);
//		} else {
//			holder = (ViewHolder) convertView.getTag();
//		}
//		System.out.println("position:" + position);
//		System.out.println("AlbumBimpUtil.tempSelectBitmap.size():"
//				+ AlbumBimpUtil.tempSelectBitmap.size());
//		if (position == AlbumBimpUtil.tempSelectBitmap.size()) {
//			holder.image.setImageBitmap(BitmapFactory.decodeResource(
//					getResources(), R.drawable.icon_addpic_focused));
//			if (position == 9) {
//				holder.image.setVisibility(View.GONE);
//			}
//		} else {
//			holder.image.setImageBitmap(AlbumBimpUtil.tempSelectBitmap.get(
//					position).getBitmap());
//		}
//
//		return convertView;
//	}
//
//	public class ViewHolder {
//		public ImageView image;
//	}
//
//	@SuppressLint("HandlerLeak")
//	Handler handler = new Handler() {
//		public void handleMessage(Message msg) {
//			switch (msg.what) {
//			case 1:
//				adapter.notifyDataSetChanged();
//				break;
//			}
//			super.handleMessage(msg);
//		}
//	};
//
//	public void loading() {
//		new Thread(new Runnable() {
//			public void run() {
//				while (true) {
//					if (AlbumBimpUtil.max == AlbumBimpUtil.tempSelectBitmap
//							.size()) {
//						Message message = new Message();
//						message.what = 1;
//						handler.sendMessage(message);
//						break;
//					} else {
//						AlbumBimpUtil.max += 1;
//						Message message = new Message();
//						message.what = 1;
//						handler.sendMessage(message);
//					}
//				}
//			}
//		}).start();
//	}
//}
