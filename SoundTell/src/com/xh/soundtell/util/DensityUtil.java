package com.xh.soundtell.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class DensityUtil {

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;

		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;

		return (int) (pxValue / scale + 0.5f);
	}

	public static int[] getScreenWidthAndHeight(Activity activity) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		int[] result = new int[2];
		result[0] = displayMetrics.widthPixels;
		result[1] = displayMetrics.heightPixels;
		return result;
	}

	public static int[] getScreenWidthAndHeight(Context context) {

		DisplayMetrics displayMetrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		int[] result = new int[2];
		result[0] = displayMetrics.widthPixels;
		result[1] = displayMetrics.heightPixels;
		return result;
	}
}
