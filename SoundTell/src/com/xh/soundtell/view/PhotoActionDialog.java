package com.xh.soundtell.view;

import java.io.File;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.xh.soundtell.R;
import com.xh.soundtell.util.FileHelper;
import com.xh.soundtell.util.ToastUtil;

/**
 * 选择图库/照相页面
 * 
 * @author: 李加蒙
 * @create-time: 2015-02-27 14:00:38
 * 
 */
public class PhotoActionDialog extends Dialog {

	private Context mContext;
	private int theme;
	private CaptureImagePathDelegate pathDelegate;
	public static final int REQUEST_IMAGE_CAPTURE = 2;
	public static final int REQUEST_PHOTO_LIBRARY = 3;

	public PhotoActionDialog(Context context) {
		super(context);
		mContext = context;
	}

	public PhotoActionDialog(Context context, int theme) {
		super(context, theme);
		mContext = context;
		this.theme = theme;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.layout_head_red);
		findViewById(R.id.layout_head_red).setVisibility(View.GONE);
		if (theme == 2) {
			System.out.println("theme="+theme);
			choosePhotoActionDialog();
		} else {
			System.out.println("theme="+theme);
			takePhotoActionDialog();
		}
	}

	/**
	 * 选择图库图片
	 */
	public void choosePhotoActionDialog() {
		PhotoActionDialog.this.cancel();
		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		photoPickerIntent.setType("image/*");
		((Activity) mContext).startActivityForResult(photoPickerIntent,
				REQUEST_PHOTO_LIBRARY);

	}

	/**
	 * 照相机照相
	 */
	public void takePhotoActionDialog() {
		Boolean isSDPresent = android.os.Environment.getExternalStorageState()
				.equals(android.os.Environment.MEDIA_MOUNTED);

		if (isSDPresent) {
			PhotoActionDialog.this.cancel();
			try {
				String imagePath = FileHelper.getBasePath().getAbsolutePath()
						+ "/" + System.currentTimeMillis() + ".jpg";
				if (pathDelegate != null) {
					pathDelegate.setImagePaht(imagePath);
				}
				File imageFile = new File(imagePath);
				Uri imageUri = Uri.fromFile(imageFile);
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				((Activity) mContext).startActivityForResult(intent,
						REQUEST_IMAGE_CAPTURE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			((Activity) mContext).runOnUiThread(new Runnable() {
				public void run() {
					ToastUtil.makeToast(mContext, R.string.no_use_sd_card);
				}
			});
			PhotoActionDialog.this.cancel();
			try {
				String imagePath = FileHelper.getPhoneBasePath()
						.getAbsolutePath()
						+ "/"
						+ System.currentTimeMillis()
						+ ".jpg";
				if (pathDelegate != null) {
					pathDelegate.setImagePaht(imagePath);
				}
				File imageFile = new File(imagePath);
				Uri imageUri = Uri.fromFile(imageFile);
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				((Activity) mContext).startActivityForResult(intent,
						REQUEST_IMAGE_CAPTURE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取图片的路径接口
	 */
	public interface CaptureImagePathDelegate {
		void setImagePaht(String path);
	}

	/**
	 * 获取CaptureImagePathDelegate 实例
	 */
	public CaptureImagePathDelegate getPathDelegate() {
		return pathDelegate;
	}

	/**
	 * 设置CaptureImagePathDelegate 实例
	 */
	public void setPathDelegate(CaptureImagePathDelegate pathDelegate) {
		this.pathDelegate = pathDelegate;
	}

	@Override
	public void onBackPressed() {
		// super.onBackPressed();
		PhotoActionDialog.this.cancel();
		((Activity) mContext).finish();
	}

	

}
