package com.xh.soundtell.util;

import android.content.Context;

/**
 * 显示Toast信息，便于管理Toast样式
 * 
 * @author 李加蒙
 * @create-time 2015.4.10 14:01
 */

public class ToastUtil {
	public static void makeToast(Context context, String title) {
		if (context != null) {
			if (!StringUtils.isEmpty(title)) {
				android.widget.Toast.makeText(context, title,
						android.widget.Toast.LENGTH_SHORT).show();
			}
		}
	}

	public static void makeToast(Context context, int rid) {
		if (context != null) {
			android.widget.Toast.makeText(context, rid,
					android.widget.Toast.LENGTH_SHORT).show();
		}
	}

	public static void makeToast(Context context, int rid, int time) {
		if (context != null) {
			android.widget.Toast.makeText(context, rid, time).show();
		}
	}
}
