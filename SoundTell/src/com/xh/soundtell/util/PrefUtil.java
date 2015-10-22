package com.xh.soundtell.util;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;

import com.xh.soundtell.setting.SettingHelper;
import com.xh.soundtell.ui.pickphoto.ImageItem;

public class PrefUtil {
	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	private static PrefUtil instance;

	// private String mainVersion;

	private PrefUtil() {
		Context context = SettingHelper.getInstance().getApplicationContext();
		if (context == null) {
			System.out.println("111111111111111");
		}
		sharedPreferences = context.getSharedPreferences("yinshu",
				Context.MODE_PRIVATE);
		if (sharedPreferences != null) {
			editor = sharedPreferences.edit();
			editor.commit();
		}
	}

	public static PrefUtil getInstance() {
		if (instance == null) {
			instance = new PrefUtil();
		}
		return instance;
	}

	public String getImageLogo() {
		return sharedPreferences.getString("ImageLogo", "0");
	}

	public void setImageLogo(String ImageLogo) {
		editor.putString("ImageLogo", ImageLogo);
		editor.commit();
	}

	public String getUserName() {
		return sharedPreferences.getString("UserName", "0");
	}

	public void setUserName(String UserName) {
		editor.putString("UserName", UserName);
		editor.commit();
	}

	public String getUserSex() {
		return sharedPreferences.getString("UserSex", "0");
	}

	public void setUserSex(String UserSex) {
		editor.putString("UserSex", UserSex);
		editor.commit();
	}

	public String getAreaID() {
		return sharedPreferences.getString("AreaID", "0");
	}

	public void setAreaID(String AreaID) {
		editor.putString("AreaID", AreaID);
		editor.commit();
	}

	public String getArea() {
		return sharedPreferences.getString("Area", "0");
	}

	public void setArea(String Area) {
		editor.putString("Area", Area);
		editor.commit();
	}

	public String getIntro() {
		return sharedPreferences.getString("Intro", "0");
	}

	public void setIntro(String Intro) {
		editor.putString("Intro", Intro);
		editor.commit();
	}

	public String getUpload() {
		return sharedPreferences.getString("Upload", "0");
	}

	public void setUpload(String upload) {
		editor.putString("Upload", upload);
		editor.commit();
	}
}
