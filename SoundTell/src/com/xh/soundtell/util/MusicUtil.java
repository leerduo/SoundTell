/*************************************************************************************************
 * 版权所有 (C)2015,  成都市商联汇通技术有限公司
 * 
 * 文件名称：MusicUtil.java
 * 内容摘要：升级服务
 * 当前版本： TODO
 * 作        者： 李加蒙
 * 完成日期：2015-10-14 下午4:22:08
 * 修改记录：
 * 修改日期：2015-10-14 下午4:22:08
 * 版   本  号：
 * 修   改  人：
 * 修改内容：
 ************************************************************************************************/

package com.xh.soundtell.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * 文件名称：MusicUtil.java
 */
public class MusicUtil {

	/**
	 * 时间转化处理
	 */
	public static String toTime(int time) {
		time /= 1000;
		int minute = time / 60;
		int second = time % 60;
		minute %= 60;
		return String.format(" %02d:%02d ", minute, second);
	}

	/**
	 * 文件大小转换，将B转换为MB
	 */
	public String toMB(int size) {
		float a = (float) size / (float) (1024 * 1024);
		String b = Float.toString(a);
		int c = b.indexOf(".");
		String fileSize = "";
		fileSize += b.substring(0, c + 2);
		return fileSize;
	}

	/**
	 * 歌曲专辑图片显示,如果有歌曲图片，才会返回，否则为null，要注意判断
	 * 
	 * @param trackId是音乐的id
	 * @return 返回类型是String 类型的图片地址，也就是uri
	 */
	public String getAlbumArt(Context mContext, int trackId) {

		String mUriTrack = "content://media/external/audio/media/#";
		String[] projection = new String[] { "album_id" };
		String selection = "_id = ?";
		String[] selectionArgs = new String[] { Integer.toString(trackId) };
		Cursor mcCursor = mContext.getContentResolver().query(
				Uri.parse(mUriTrack), projection, selection, selectionArgs,
				null);
		int album_id = 0;
		if (mcCursor.getCount() > 0 && mcCursor.getColumnCount() > 0) {
			mcCursor.moveToNext();
			album_id = mcCursor.getInt(0);
		}
		mcCursor.close();
		mcCursor = null;

		if (album_id < 0) {
			return null;
		}
		String mUriAlbums = "content://media/external/audio/albums";
		projection = new String[] { "album_art" };
		mcCursor = mContext.getContentResolver().query(
				Uri.parse(mUriAlbums + "/" + Integer.toString(album_id)),
				projection, null, null, null);

		String album_art = null;
		if (mcCursor.getCount() > 0 && mcCursor.getColumnCount() > 0) {
			mcCursor.moveToNext();
			album_art = mcCursor.getString(0);
		}
		mcCursor.close();
		mcCursor = null;

		return album_art;
	}
}
