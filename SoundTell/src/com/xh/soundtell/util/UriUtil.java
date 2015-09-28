package com.xh.soundtell.util;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
/**
 * 获取本地图片 工具
 * @author 李加蒙
 * Create-time 2015.07.06
 */
public class UriUtil {
	public static String getAbsoluteImagePath(Uri uri, Context context) {

		String[] proj = { MediaStore.Images.Media.DATA };

		@SuppressWarnings("deprecation")
		Cursor actualimagecursor = ((Activity) context).managedQuery(uri, proj,
				null, null, null);

		int actual_image_column_index = actualimagecursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

		actualimagecursor.moveToFirst();

		String img_path = actualimagecursor
				.getString(actual_image_column_index);

		return img_path;

	}
}
