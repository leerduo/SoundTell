package com.xh.soundtell.setting;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;

/**
 * Setting类
 * 
 * @author: 李加蒙
 * @create-time:2015.4.7 15：40
 */
public class SettingHelper {
	private static SettingHelper singleton = new SettingHelper();
	private static Context mApplicationContext;
	private static Activity mCurrentActivity;
	// private ArrayList<GoodsCategory> goodsCategories;

	private static final String USER_INFO_FILE = "com.ttuhui.ttyh.userInfo.dat";
	private static final String GOODS_CATEGORY = "com.ttuhui.ttyh.goodscategory.dat";
	private static final String USER_NAME_HIS = "com.ttuhui.ttyh.namehis.dat";
	// 用户名历史
	private ArrayList<String> nameHis;

	/**
	 * @return 获取SettingHelper类的对象
	 */
	public static SettingHelper getInstance() {
		return singleton;
	}

	/**
	 * @return 获取本APP的context
	 */
	public synchronized Context getApplicationContext() {
		return mApplicationContext;
	}

	/**
	 * @param applicationContext
	 *            设置本APP的context
	 */
	public synchronized void setApplicationContext(Context applicationContext) {
		mApplicationContext = applicationContext;
	};

	/**
	 * @return 获取当前Activity
	 */
	public synchronized Activity getCurrentActivity() {
		return mCurrentActivity;
	}

	/**
	 * 
	 * @param currentActivity
	 *            设置当前Activity
	 */
	public synchronized void setCurrentActivity(Activity currentActivity) {
		mCurrentActivity = currentActivity;
	}

	/**
	 * @return 证书检查是否启用
	 */
	public boolean isCertificateCheckEnabled() {
		return false;
	}

	/**
	 * @return socket链接超时时间
	 */
	public int getSocketTimeout() {
		return 30000;
	}

	/**
	 * @return Cache超时时间
	 */
	public long getDataCacheTimeoutMillis() {
		return 300000;
	}

	/**
	 * @return 图片Cache超时时间
	 */
	public long getImageCacheTimeoutMillis() {
		return 604800000;
	}
}
