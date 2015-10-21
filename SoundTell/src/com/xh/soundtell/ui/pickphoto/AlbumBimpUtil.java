package com.xh.soundtell.ui.pickphoto;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AlbumBimpUtil {
	public static int max = 0;
	public static int num = 8;
	public static HashSet<Activity> activities = new HashSet<Activity>();
	public static ArrayList<ImageItem> tempSelectBitmap = new ArrayList<ImageItem>();   //选择的图片的临时列表
	public static Bitmap revitionImageSize(String path) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				new File(path)));
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(in, null, options);
		in.close();
		int i = 0;
		Bitmap bitmap = null;
		while (true) {
			if ((options.outWidth >> i <= 1000)
					&& (options.outHeight >> i <= 1000)) {
				in = new BufferedInputStream(
						new FileInputStream(new File(path)));
				options.inSampleSize = (int) Math.pow(2.0D, i);
				options.inJustDecodeBounds = false;
				bitmap = BitmapFactory.decodeStream(in, null, options);
				break;
			}
			i += 1;
		}
		return bitmap;
	}
	public static void putActivty(Activity activity) {
		if (activity==null) {
			return;
		}
		activities.add(activity);
	}
	public static void finshActivities(Activity activity) {
		if (activity==null) {
			return;
		}
		for (Activity ac : activities) {
			if (ac!=activity) {
				ac.finish();
			}
		}
		activities.clear();
	}
}
