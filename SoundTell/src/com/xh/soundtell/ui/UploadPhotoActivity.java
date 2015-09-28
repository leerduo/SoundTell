package com.xh.soundtell.ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Telephony.Mms.Part;

import com.xh.soundtell.R;
import com.xh.soundtell.util.ImageHelper;
import com.xh.soundtell.util.ToastUtil;
import com.xh.soundtell.util.UriUtil;
import com.xh.soundtell.view.PhotoActionDialog;
import com.xh.soundtell.view.PhotoActionDialog.CaptureImagePathDelegate;

public class UploadPhotoActivity extends Activity implements
		CaptureImagePathDelegate {

	private static final int PHOTORESOULT = 0x3452;
	/**
	 * 图片选择成功以后返回来的URIcom.ttuhui.ttyh.util.iosdate.PhotoAction.
	 * CaptureImagePathDelegate为
	 */
	protected Uri imageUri = null;
	protected List<String> imagePathList = new ArrayList<String>();
	protected String lastImageFilePath = null;
	private Uri uri;

	// private ShopEditInfoService shEditService;

	public static int i = 0;
	public static String str = "";
	private Intent intent;
	private String service;
	private int theme;
	private ArrayList<Part> multipart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		intent = getIntent();
		service = intent.getStringExtra("service");
		theme = intent.getIntExtra("theme", 1);
		choosePicture();
	}

	/**
	 * 选择图片
	 */
	protected void choosePicture() {
		if (checkPictureNumber()) {
			PhotoActionDialog photoAction = new PhotoActionDialog(
					UploadPhotoActivity.this, theme);
			photoAction.setPathDelegate(this);
			if (photoAction != null && !photoAction.isShowing()) {
				photoAction.show();
			}
		} else {
			ToastUtil.makeToast(UploadPhotoActivity.this, R.string.images_size);
		}
	}

	private boolean checkPictureNumber() {
		if (imagePathList == null || imagePathList.size() < 9) {
			return true;
		}
		return false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 0) {
			i = 2;
			UploadPhotoActivity.this.finish();
		}
		/**
		 * If the request code is from requesting photo library or image
		 * capture, we get the file path of image and then start ScaleImage
		 * Activity to scale the image. If the request code is from scale image,
		 * then it means we could get scaled image and do the rest of work
		 * 如果请求从请求代码库或照片图像捕获,我们得到图像的文件路径,然后开始ScaleImage
		 * *活动规模的形象。如果请求代码从规模图像,*就意味着我们会按比例缩小的形象和做其他的工作
		 */
		if (requestCode == PhotoActionDialog.REQUEST_PHOTO_LIBRARY
				&& resultCode == RESULT_OK) {
			imageUri = data.getData();
			if (imageUri.getScheme().equals("content")) {
				lastImageFilePath = UriUtil.getAbsoluteImagePath(imageUri,
						UploadPhotoActivity.this);
				System.out.println(lastImageFilePath + "          图库图片路径11");
			} else {
				lastImageFilePath = imageUri.getPath();
				System.out.println(lastImageFilePath + "          图库图片路径22");
			}
			lastImageFilePath = ImageHelper
					.generateCompressedImage(lastImageFilePath);
			uri = Uri.fromFile(new File(lastImageFilePath));
			startPhotoZoom(uri);
			imagePathList.add(lastImageFilePath);
			System.out.println(lastImageFilePath + "          图库图片路径33");
		} else if (requestCode == PhotoActionDialog.REQUEST_IMAGE_CAPTURE
				&& resultCode == RESULT_OK) {
			uri = Uri.fromFile(new File(lastImageFilePath));
			startPhotoZoom(uri);
			imagePathList.add(lastImageFilePath);
			System.out.println(lastImageFilePath + "          拍照图片路径");
		} else if (requestCode == PHOTORESOULT && resultCode != 0) {
			if (uri != null) {
				Bitmap photo = decodeUriAsBitmap(uri);
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				photo.compress(Bitmap.CompressFormat.JPEG, 70, stream);
				byte[] bytes = stream.toByteArray();
				FileOutputStream fileOutputStream = null;
				File file = null;
				String string = this.getApplication().getCacheDir() + "/"
						+ System.currentTimeMillis() + ".jpg";
				try {
					file = new File(string);
					fileOutputStream = new FileOutputStream(file);
					fileOutputStream.write(bytes);
					fileOutputStream.flush();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (fileOutputStream != null) {
							fileOutputStream.close();
						}
					} catch (Exception e2) {
					}
				}
				if (photo != null && !photo.isRecycled()) {
					// 回收并且置为null
					photo.recycle();
					photo = null;
				}
				imagePathList.add(string);
				System.out.println(string + "          图库图片路径");
				postHandlePicture();
			}

		}
	}

	protected void postHandlePicture() {
		if (!service.equals("service")) {
			i = 3;
			setResult(RESULT_OK);
			str = imagePathList.get(imagePathList.size() - 1);
			this.finish();
			return;
		}
		// editLoadImageData();
	}

	@Override
	public void setImagePaht(String path) {
		this.lastImageFilePath = path;
	}

	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 200);
		intent.putExtra("outputY", 200);
		intent.putExtra("scale", true);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		intent.putExtra("return-data", true);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true); // no face detection
		startActivityForResult(intent, PHOTORESOULT);
	}

	private Bitmap decodeUriAsBitmap(Uri uri) {
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(getContentResolver()
					.openInputStream(uri));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}

	private void editLoadImageData() {
		// UIHelper.showProgressDialog(this, R.string.loading);
		// multipart = new ArrayList<Part>();
		// try {
		// multipart.add(new FilePart("store_logo", new File(imagePathList
		// .get(imagePathList.size() - 1)), "image/gif", "UTF-8"));
		// multipart.add(new StringPart("id", SettingHelper.getInstance()
		// .getUserData().getStore_id()));
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		// shEditService = new ShopEditInfoService();
		// shEditService.setAuth(true);
		// shEditService.setDelegate(this);
		// shEditService.setForceRefresh(true);
		// shEditService.setMultipart(multipart);
		// shEditService.start();
	}

	// @Override
	// public void onServiceSuccess(DataService service, Object result) {
	// UIHelper.dismissProgressDialog();
	// // if (service instanceof ShopEditInfoService) {
	// // runOnUiThread(new Runnable() {
	// // @Override
	// // public void run() {
	// // System.out.println("上传数据： "
	// // + imagePathList.get(imagePathList.size() - 1));
	// // i = 1;
	// // str = imagePathList.get(imagePathList.size() - 1);
	// // ToastUtil.makeToast(UploadPhotoActivity.this,
	// // R.string.upload_success);
	// // UploadPhotoActivity.this.finish();
	// // }
	// // });
	// // }
	// }
	//
	// @Override
	// public void onServiceFailure(DataService service, final Exception
	// exception) {
	// UIHelper.onServiceFailure(this, exception, null);
	// UploadPhotoActivity.this.finish();
	// }
	//
	// @Override
	// public void onServiceFailure(DataService service, Exception exception,
	// String ret) {
	// UIHelper.onServiceFailure(this, exception, ret);
	// UploadPhotoActivity.this.finish();
	// }

}
