package com.xh.soundtell.setting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;

import com.xh.soundtell.model.UserInfo;

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

	private UserInfo mUserInfo;
	private static final String USER_INFO_FILE = "com.xh.soundtell.userInfo.dat";
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

	/**
	 * 获取登陆用户信息
	 * 
	 * @return
	 */
	public UserInfo getUserInfo() {
		if (mUserInfo == null) {
			try {
				File file = getApplicationContext().getFileStreamPath(
						USER_INFO_FILE);
				if (file.exists()) {
					FileInputStream fis = getApplicationContext()
							.openFileInput(USER_INFO_FILE);
					ObjectInputStream ois = new ObjectInputStream(fis);
					Object object = ois.readObject();
					if (object != null && object instanceof UserInfo) {
						mUserInfo = (UserInfo) object;
					}
					ois.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mUserInfo;
	}

	/**
	 * 保存登陆用户信息
	 * 
	 * @return
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.mUserInfo = userInfo;
		if (userInfo == null) {
			File file = getApplicationContext().getFileStreamPath(
					USER_INFO_FILE);
			if (file.exists()) {
				file.delete();
			}
		} else {
			try {
				FileOutputStream fos = getApplicationContext().openFileOutput(
						USER_INFO_FILE, Context.MODE_PRIVATE);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(userInfo);
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
