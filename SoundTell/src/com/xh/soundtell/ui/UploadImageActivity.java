/*************************************************************************************************
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

import java.util.ArrayList;
import java.util.List;

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
import com.xh.soundtell.ios.view.SheetDialog;
import com.xh.soundtell.ios.view.SheetDialog.OnSheetItemClickListener;
import com.xh.soundtell.ios.view.SheetDialog.SheetItemColor;
import com.xh.soundtell.ui.pickphoto.AlbumActivity;
import com.xh.soundtell.ui.pickphoto.AlbumBimpUtil;
import com.xh.soundtell.ui.pickphoto.GalleryActivity;
import com.xh.soundtell.ui.pickphoto.ImageItem;
import com.xh.soundtell.util.ImageHelper;
import com.xh.soundtell.util.PrefUtil;
import com.xh.soundtell.util.ToastUtil;
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
	private GridImageAdapter gridImageAdapter;

	public static Bitmap bimap;

	private PrefUtil prefUtil;
	// public static StringBuffer image = new StringBuffer();
	public static String[] images = null;

	public static ArrayList<String> list = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uploadimage);
		prefUtil = PrefUtil.getInstance();
		list = new ArrayList<String>();
		findView();

		setData();
		System.out.println(prefUtil.getUpload() + ":prefUtil.getUpload()");

		// for (int i = 0; i < list.size(); i++) {
		// System.out.println("list.size()" + i + "i" + list.size());
		// }

	}

	private void setData() {
		if (prefUtil.getUpload() != null && !prefUtil.getUpload().equals("0")) {
			System.out.println("prefUtil.getUpload:" + prefUtil.getUpload());
			images = prefUtil.getUpload().split(";");
			for (int i = 0; i < images.length; i++) {
				list.add(images[i]);
				System.out.println("i" + i);
			}
			System.out.println("images.length:" + images.length + "/n"
					+ "list:" + list.size());
		}

		if (list != null && list.size() > 0) {
			uploadimage_iv.setVisibility(View.GONE);
		}

		adapter = new GridAdapter(this);
		if (list != null) {
			gridImageAdapter = new GridImageAdapter(this);
			// adapter.update();
			// adapter.update();
			uploadimage_gv.setAdapter(gridImageAdapter);
		}
		uploadimage_gv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(UploadImageActivity.this,
						GalleryActivity.class);
				intent.putExtra("position", "1");
				intent.putExtra("iamge", "1");
				intent.putExtra("ID", arg2);
				startActivityForResult(intent, 102);
			}
		});

	}

	private void findView() {
		head_centertext = (TextView) findViewById(R.id.head_centertext);
		head_centertext.setVisibility(View.VISIBLE);
		head_centertext.setText("我的相册");

		head_leftimage = (ImageView) findViewById(R.id.head_leftimage);
		head_leftimage.setVisibility(View.VISIBLE);
		head_leftimage.setImageResource(R.drawable.back_wihte);
		head_leftimage.setOnClickListener(this);

		head_rightimage = (ImageView) findViewById(R.id.head_rightimage);
		head_rightimage.setVisibility(View.VISIBLE);
		head_rightimage.setImageResource(R.drawable.uploadimage);
		head_rightimage.setOnClickListener(this);

		uploadimage_iv = (ImageView) findViewById(R.id.uploadimage_iv);

		uploadimage_gv = (GridViewForScrollView) findViewById(R.id.uploadimage_gv);

		// uploadimage_gv.setSelector(new ColorDrawable(Color.TRANSPARENT));

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.head_leftimage:
			UploadImageActivity.this.finish();
			break;
		case R.id.head_rightimage:
			// Intent intent = new Intent(UploadImageActivity.this,
			// AlbumActivity.class);
			// intent.putExtra("position", "1");
			// intent.putExtra("upimage", "1");
			// intent.putExtra("ID", 0);
			// startActivity(intent);
			if (list.size() < 12) {
				new SheetDialog(UploadImageActivity.this)
						.builder()
						.setCancelable(true)
						.setCanceledOnTouchOutside(true)
						.addSheetItem("拍照", SheetItemColor.Blue,
								new OnSheetItemClickListener() {
									@Override
									public void onClick(int which) {
										Intent intent1 = new Intent(
												UploadImageActivity.this,
												UploadPhotoActivity.class);
										intent1.putExtra("service",
												"UploadImage");
										intent1.putExtra("theme", 1);
										startActivityForResult(intent1, 102);
									}
								})
						.addSheetItem("从相册选取照片", SheetItemColor.Blue,
								new OnSheetItemClickListener() {
									@Override
									public void onClick(int which) {
										Intent intent1 = new Intent(
												UploadImageActivity.this,
												AlbumActivity.class);
										intent1.putExtra("position", "1");
										intent1.putExtra("upimage", "1");
										intent1.putExtra("ID", 0);
										startActivityForResult(intent1, 102);
									}
								}).show();
			} else {
				ToastUtil.makeToast(this, R.string.only_choose_num);
			}

			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		System.out.println("UploadImageActivity onActivityResult" + resultCode
				+ ":" + requestCode);
		if (resultCode != RESULT_OK) {
			return;
		}
		if (requestCode == 102) {
			// list = new ArrayList<String>();
			// if (prefUtil.getUpload() != null
			// && !prefUtil.getUpload().equals("0")) {
			// System.out.println("服prefUtil.getUpload："
			// + prefUtil.getUpload());
			// images = prefUtil.getUpload().split(";");
			// for (int i = 0; i < images.length; i++) {
			// list.add(images[i]);
			// }
			// }
			System.out.println("00000000000000000");

			StringBuffer buffer = new StringBuffer();

			for (int i = 0; i < UploadImageActivity.list.size(); i++) {
				buffer.append(UploadImageActivity.list.get(i) + ";");
				System.out.println("服list.size()" + i + "：" + list.get(i));
			}

			if (buffer.length() > 0) {
				String upload = buffer.subSequence(0, buffer.length() - 1)
						.toString();
				System.out.println("upload:" + upload);
				prefUtil.setUpload(upload);
			} else {
				prefUtil.setUpload("0");
			}

			if (list != null && list.size() > 0) {
				uploadimage_iv.setVisibility(View.GONE);
			}
			if (list != null) {
				gridImageAdapter = new GridImageAdapter(this);
				// adapter.update();
				// adapter.update();
				uploadimage_gv.setAdapter(gridImageAdapter);
			}
		}
	}

	public class GridImageAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public GridImageAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item_published_grida,
						parent, false);
				holder = new ViewHolder();
				holder.image = (ImageView) convertView
						.findViewById(R.id.item_grida_image);
				convertView.setTag(holder);
			}
			holder = (ViewHolder) convertView.getTag();
			holder.image.setImageBitmap(ImageHelper.getBitmapCompressed(list
					.get(position)));
			return convertView;
		}

		class ViewHolder {
			public ImageView image;
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
