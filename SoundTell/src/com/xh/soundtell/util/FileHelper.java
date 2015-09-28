package com.xh.soundtell.util;

import java.io.File;
import java.io.IOException;

import android.os.Environment;

/**
 * 操纵数据
 *
 */
public class FileHelper {
	private static final String BASE_PATH = "/wtlxfzx/capture";

	/**
	 * 创建一个文件 在sd卡中
	 */
	public static File getBasePath() {
		File basePath = new File(Environment.getExternalStorageDirectory(),
				BASE_PATH);

		if (!basePath.exists()) {
			if (!basePath.mkdirs()) {
				try {
					throw new IOException(String.format(
							"%s cannot be created!", basePath.toString()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		if (!basePath.isDirectory()) {
			try {
				throw new IOException(String.format("%s is not a directory!",
						basePath.toString()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return basePath;
	}

	/**
	 * 创建一个文件 在data中
	 */
	public static File getPhoneBasePath() {
		File basePath = new File(Environment.getDataDirectory(), BASE_PATH);

		if (!basePath.exists()) {
			if (!basePath.mkdirs()) {
				try {
					throw new IOException(String.format(
							"%s cannot be created!", basePath.toString()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		if (!basePath.isDirectory()) {
			try {
				throw new IOException(String.format("%s is not a directory!",
						basePath.toString()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return basePath;
	}

	/**
	 * 删除文件
	 */
	public static boolean removeFile(String filePath) {
		File file = new File(filePath);
		return file.delete();
	}

	public static File updateDir = null;
	public static File updateFile = null;
	/*********** 保存升级APK的目录 ***********/
	public static final String XFZXApplication = "XFZXdateApplication";

	public static boolean isCreateFileSucess;

	/**
	 * 方法描述：createFile方法
	 * 
	 * @param String
	 *            app_name
	 * @return
	 * @see FileUtil
	 */
	public static void createFile(String app_name) {

		if (android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment
				.getExternalStorageState())) {
			isCreateFileSucess = true;

			updateDir = new File(Environment.getExternalStorageDirectory()
					+ "/" + XFZXApplication + "/");
			updateFile = new File(updateDir + "/" + app_name + ".apk");

			if (!updateDir.exists()) {
				updateDir.mkdirs();
			}
			if (!updateFile.exists()) {
				try {
					updateFile.createNewFile();
				} catch (IOException e) {
					isCreateFileSucess = false;
					e.printStackTrace();
				}
			}

		} else {
			isCreateFileSucess = false;
		}
	}

}
