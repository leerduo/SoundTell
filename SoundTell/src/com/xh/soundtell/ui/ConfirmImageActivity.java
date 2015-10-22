package com.xh.soundtell.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.xh.soundtell.R;
import com.xh.soundtell.ui.pickphoto.AlbumActivity;
import com.xh.soundtell.ui.pickphoto.AlbumBimpUtil;
import com.xh.soundtell.ui.pickphoto.GalleryActivity;
import com.xh.soundtell.ui.pickphoto.ImageItem;
import com.xh.soundtell.util.ImageHelper;
import com.xh.soundtell.util.PrefUtil;

/**
 * 
 * @author Administrator 确认上传界面
 */
public class ConfirmImageActivity extends Activity implements OnClickListener {
	// 字体 以及返回按钮
	private TextView head_centertext;
	private ImageView head_leftimage;

	private Button upload, cancel;

	private GridView gridView;
	private GridImageAdapter adapter;

	private PrefUtil prefUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmimage);
		findView();
		prefUtil = PrefUtil.getInstance();
	}

	private void findView() {
		head_centertext = (TextView) findViewById(R.id.head_centertext);
		head_centertext.setVisibility(View.VISIBLE);
		head_centertext.setText("手机相册");

		head_leftimage = (ImageView) findViewById(R.id.head_leftimage);
		head_leftimage.setVisibility(View.VISIBLE);
		head_leftimage.setImageResource(R.drawable.back_wihte);
		head_leftimage.setOnClickListener(this);

		upload = (Button) findViewById(R.id.upload);
		cancel = (Button) findViewById(R.id.cancel);
		upload.setOnClickListener(this);
		cancel.setOnClickListener(this);

		gridView = (GridView) findViewById(R.id.gridview);
		setImageAdapter();
	}

	private void setImageAdapter() {
		adapter = new GridImageAdapter(this);
		// adapter.update();
		// adapter.update();
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (arg2 == AlbumBimpUtil.tempSelectBitmap.size()) {
					// System.out.println("添加图片");
					Intent intent = new Intent(ConfirmImageActivity.this,
							AlbumActivity.class);
					intent.putExtra("position", "1");
					intent.putExtra("upimage", "2");
					startActivity(intent);
					ConfirmImageActivity.this.finish();
				} else {
					Intent intent = new Intent(ConfirmImageActivity.this,
							GalleryActivity.class);
					intent.putExtra("position", "1");
					intent.putExtra("iamge", "2");
					intent.putExtra("ID", arg2);
					startActivityForResult(intent, 105);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.head_leftimage:
			ConfirmImageActivity.this.finish();
			break;
		case R.id.upload:
			System.out.println("tempSelectBitmap.size()"
					+ AlbumBimpUtil.tempSelectBitmap.size());
			for (int i = 0; i < AlbumBimpUtil.tempSelectBitmap.size(); i++) {
				String imagePath = AlbumBimpUtil.tempSelectBitmap.get(i)
						.getImagePath();
				String string = this.getApplication().getCacheDir() + "/"
						+ System.currentTimeMillis() + i + ".jpg";
				String images = ImageHelper.generateCompressedImage(imagePath,
						string);
				// System.out.println(generateCompressedImage
				// + "generateCompressedImage");
				// if (UploadImageActivity.image.length() > 0) {
				// UploadImageActivity.image.append(";" + images);
				// } else {
				// if (i == AlbumBimpUtil.tempSelectBitmap.size()) {
				// UploadImageActivity.image.append(images);
				// } else {
				// UploadImageActivity.image.append(images + ";");
				// }
				// }
				UploadImageActivity.list.add(images);
			}
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < UploadImageActivity.list.size(); i++) {
				buffer.append(UploadImageActivity.list.get(i) + ";");
			}

			for (int i = 0; i < UploadImageActivity.list.size(); i++) {
				System.out.println("确认list.size()" + i + "："
						+ UploadImageActivity.list.size());
			}

			// if (buffer.length() > 0) {
			// String upload = buffer.subSequence(0, buffer.length() - 1)
			// .toString();
			// System.out.println("upload:" + upload);
			// prefUtil.setUpload(upload);
			// }

			// for (int i = 0; i < AlbumBimpUtil.tempSelectBitmap.size(); i++) {
			// AlbumBimpUtil.tempSelectBitmap.clear();
			// }
			AlbumBimpUtil.tempSelectBitmap = new ArrayList<ImageItem>();

			setResult(RESULT_OK);
			finish();
			break;
		case R.id.cancel:
			ConfirmImageActivity.this.finish();
			break;

		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		System.out.println("ConfirmImageActivity onActivityResult");
		if (resultCode != RESULT_OK) {
			System.out.println("ConfirmImageActivity onActivityResult2"
					+ requestCode);
			return;
		}
		System.out.println("ConfirmImageActivity onActivityResult1"
				+ (requestCode == 105) + requestCode);
		if (requestCode == 105) {
			System.out.println("ConfirmImageActivity onActivityResult1");
			setImageAdapter();
		}
	}

	public class GridImageAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public GridImageAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// System.out.println(AlbumBimpUtil.tempSelectBitmap.size()
			// + "AlbumBimpUtil.tempSelectBitmap.size()");
			return (AlbumBimpUtil.tempSelectBitmap.size() + 1);
		}

		@Override
		public Object getItem(int position) {
			return AlbumBimpUtil.tempSelectBitmap.get(position);
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

			if (position == AlbumBimpUtil.tempSelectBitmap.size()) {
				holder.image.setImageBitmap(BitmapFactory.decodeResource(
						getResources(), R.drawable.add_img));
			} else {
				holder.image.setImageBitmap(AlbumBimpUtil.tempSelectBitmap.get(
						position).getBitmap());
			}

			return convertView;
		}

		class ViewHolder {
			public ImageView image;
		}

	}

}
