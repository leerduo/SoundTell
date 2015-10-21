/*************************************************************************************************
 * 版权所有 (C)2015,  成都市商联汇通技术有限公司
 * 
 * 文件名称：UploadImageActivity.java
 * 内容摘要：升级服务
 * 当前版本： TODO
 * 作        者： 李加蒙
 * 完成日期：2015-10-21 下午2:23:12
 * 修改记录：
 * 修改日期：2015-10-21 下午2:23:12
 * 版   本  号：
 * 修   改  人：
 * 修改内容：
 ************************************************************************************************/

package com.xh.soundtell.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xh.soundtell.R;
import com.xh.soundtell.ui.pickphoto.AlbumActivity;
import com.xh.soundtell.ui.pickphoto.AlbumBimpUtil;
import com.xh.soundtell.ui.pickphoto.GalleryActivity;
import com.xh.soundtell.view.GridViewForScrollView;

/**
 * 文件名称：UploadImageActivity.java
 */
public class UploadImageActivity extends Activity implements OnClickListener {
	private TextView head_centertext;
	private ImageView head_leftimage, head_rightimage;

	private ImageView uploadimage_iv;
	private GridViewForScrollView uploadimage_gv;
	private GridAdapter adapter;

	public static Bitmap bimap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uploadimage);
		findView();
	}

	private void findView() {
		head_centertext = (TextView) findViewById(R.id.head_centertext);
		head_centertext.setVisibility(View.VISIBLE);
		head_centertext.setText("意见反馈");

		head_leftimage = (ImageView) findViewById(R.id.head_leftimage);
		head_leftimage.setVisibility(View.VISIBLE);
		head_leftimage.setImageResource(R.drawable.back_wihte);
		head_leftimage.setOnClickListener(this);

		head_rightimage = (ImageView) findViewById(R.id.head_rightimage);
		head_rightimage.setVisibility(View.VISIBLE);
		head_rightimage.setImageResource(R.drawable.chat_carmer);
		head_rightimage.setOnClickListener(this);

		uploadimage_iv = (ImageView) findViewById(R.id.uploadimage_iv);
		uploadimage_gv = (GridViewForScrollView) findViewById(R.id.uploadimage_gv);

		uploadimage_gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new GridAdapter(this);
		// adapter.update();
		// adapter.update();
		uploadimage_gv.setAdapter(adapter);
		uploadimage_gv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				System.out.println("arg2:" + arg2);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.head_leftimage:
			UploadImageActivity.this.finish();
			break;
		case R.id.head_rightimage:
			Intent intent = new Intent(UploadImageActivity.this,
					AlbumActivity.class);
			intent.putExtra("position", "1");
			intent.putExtra("ID", 0);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

	public class GridAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private int selectedPosition = -1;
		private boolean shape;

		public boolean isShape() {
			return shape;
		}

		public void setShape(boolean shape) {
			this.shape = shape;
		}

		public GridAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		public void update() {
			loading();
		}

		public int getCount() {
			if (AlbumBimpUtil.tempSelectBitmap.size() == 8) {
				return 8;
			}
			return (AlbumBimpUtil.tempSelectBitmap.size() + 1);
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int arg0) {
			return 0;
		}

		public void setSelectedPosition(int position) {
			selectedPosition = position;
		}

		public int getSelectedPosition() {
			return selectedPosition;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item_published_grida,
						parent, false);
				holder = new ViewHolder();
				holder.image = (ImageView) convertView
						.findViewById(R.id.item_grida_image);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			System.out.println("position:" + position);
			System.out.println("AlbumBimpUtil.tempSelectBitmap.size():"
					+ AlbumBimpUtil.tempSelectBitmap.size());
			if (position == AlbumBimpUtil.tempSelectBitmap.size()) {
				// holder.image.setImageBitmap(BitmapFactory.decodeResource(
				// getResources(), R.drawable.icon_addpic_focused));
				// if (position == 9) {
				// holder.image.setVisibility(View.GONE);
				// }
			} else {
				holder.image.setImageBitmap(AlbumBimpUtil.tempSelectBitmap.get(
						position).getBitmap());
			}

			return convertView;
		}

		public class ViewHolder {
			public ImageView image;
		}

		@SuppressLint("HandlerLeak")
		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					adapter.notifyDataSetChanged();
					break;
				}
				super.handleMessage(msg);
			}
		};

		public void loading() {
			System.out.println("loading");
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						if (AlbumBimpUtil.max == AlbumBimpUtil.tempSelectBitmap
								.size()) {
							Message message = new Message();
							message.what = 1;
							handler.sendMessage(message);
							break;
						} else {
							AlbumBimpUtil.max += 1;
							Message message = new Message();
							message.what = 1;
							handler.sendMessage(message);
						}
					}
				}
			}).start();
		}
	}

}
