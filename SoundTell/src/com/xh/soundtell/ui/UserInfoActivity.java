/*************************************************************************************************
 * 版权所有 (C)2015,  成都市商联汇通技术有限公司
 * 
 * 文件名称：UserInfoActivity.java
 * 内容摘要：升级服务
 * 当前版本： TODO
 * 作        者： 李加蒙
 * 完成日期：2015-9-28 上午11:34:57
 * 修改记录：
 * 修改日期：2015-9-28 上午11:34:57
 * 版   本  号：
 * 修   改  人：
 * 修改内容：
 ************************************************************************************************/

package com.xh.soundtell.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xh.soundtell.R;
import com.xh.soundtell.ios.data.AddressData;
import com.xh.soundtell.ios.listener.OnWheelChangedListener;
import com.xh.soundtell.ios.view.ArrayWheelAdapter;
import com.xh.soundtell.ios.view.CountryAdapter;
import com.xh.soundtell.ios.view.MyAlertDialog;
import com.xh.soundtell.ios.view.SheetDialog;
import com.xh.soundtell.ios.view.SheetDialog.OnSheetItemClickListener;
import com.xh.soundtell.ios.view.SheetDialog.SheetItemColor;
import com.xh.soundtell.ios.view.WheelView;
import com.xh.soundtell.util.ImageHelper;

/**
 * 文件名称：UserInfoActivity.java
 */
public class UserInfoActivity extends Activity implements OnClickListener {
	private TextView head_centertext;
	private TextView head_righttext;
	private ImageView head_leftimage;

	private RelativeLayout userinfo_logo_r, userinfo_name_r, userinfo_sex_r,
			userinfo_area_r, userinfo_collect_r;

	private ImageView userinfo_logo;

	private TextView userinfo_name, userinfo_sex, userinfo_area,
			userinfo_collect;

	private String strimage = "123456";

	private WheelView city, ccity, country;
	private String regionId;
	private String cityTxt = AddressData.PROVINCES_BACK[0] + " | "
			+ AddressData.CITIES_BACK[0][0] + " | "
			+ AddressData.COUNTIES_BACK[0][0][0];
	/**
	 * 省、市、县（区）、是否点击了确定(0:没点1:点了)
	 */
	private int[] ctCache = { 0, 0, 0 };// 省、市、县（区）

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userinfo);
		findView();
	}

	// userinfo_logo_r
	// userinfo_name_r
	// userinfo_sex_r
	// userinfo_area_r
	// userinfo_collect_r
	//
	// userinfo_logo
	// userinfo_name
	// userinfo_sex
	// userinfo_area
	// userinfo_collect

	private void findView() {
		head_centertext = (TextView) findViewById(R.id.head_centertext);
		head_centertext.setVisibility(View.VISIBLE);
		head_centertext.setText("编辑资料");
		head_righttext = (TextView) findViewById(R.id.head_righttext);
		head_righttext.setVisibility(View.VISIBLE);
		head_righttext.setText("保存");
		head_righttext.setOnClickListener(this);
		head_leftimage = (ImageView) findViewById(R.id.head_leftimage);
		head_leftimage.setVisibility(View.VISIBLE);
		head_leftimage.setImageResource(R.drawable.back_wihte);
		head_leftimage.setOnClickListener(this);

		userinfo_logo_r = (RelativeLayout) findViewById(R.id.userinfo_logo_r);
		userinfo_name_r = (RelativeLayout) findViewById(R.id.userinfo_name_r);
		userinfo_sex_r = (RelativeLayout) findViewById(R.id.userinfo_sex_r);
		userinfo_area_r = (RelativeLayout) findViewById(R.id.userinfo_area_r);
		userinfo_collect_r = (RelativeLayout) findViewById(R.id.userinfo_collect_r);
		userinfo_logo_r.setOnClickListener(this);
		userinfo_name_r.setOnClickListener(this);
		userinfo_sex_r.setOnClickListener(this);
		userinfo_area_r.setOnClickListener(this);
		userinfo_collect_r.setOnClickListener(this);

		userinfo_logo = (ImageView) findViewById(R.id.userinfo_logo);

		userinfo_name = (TextView) findViewById(R.id.userinfo_name);
		userinfo_sex = (TextView) findViewById(R.id.userinfo_sex);
		userinfo_area = (TextView) findViewById(R.id.userinfo_area);
		userinfo_collect = (TextView) findViewById(R.id.userinfo_collect);
	}

	// userinfo_logo_r
	// userinfo_name_r
	// userinfo_sex_r
	// userinfo_area_r
	// userinfo_collect_r
	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.head_leftimage:
			UserInfoActivity.this.finish();
			break;
		case R.id.head_righttext:
			intent = new Intent();
			intent.putExtra("image", strimage);
			intent.putExtra("name", userinfo_name.getText().toString().trim());
			intent.putExtra("collect", userinfo_collect.getText().toString()
					.trim());
			setResult(RESULT_OK, intent);
			UserInfoActivity.this.finish();
			break;
		case R.id.userinfo_logo_r:
			new SheetDialog(UserInfoActivity.this)
					.builder()
					.setCancelable(true)
					.setCanceledOnTouchOutside(true)
					.addSheetItem("用相机更换头像", SheetItemColor.Blue,
							new OnSheetItemClickListener() {
								@Override
								public void onClick(int which) {
									Intent intent1 = new Intent(
											UserInfoActivity.this,
											UploadPhotoActivity.class);
									intent1.putExtra("service", "service1");
									intent1.putExtra("theme", 1);
									startActivityForResult(intent1, 102);
								}
							})
					.addSheetItem("去相册选择头像", SheetItemColor.Blue,
							new OnSheetItemClickListener() {
								@Override
								public void onClick(int which) {
									Intent intent1 = new Intent(
											UserInfoActivity.this,
											UploadPhotoActivity.class);
									intent1.putExtra("service", "service1");
									intent1.putExtra("theme", 2);
									startActivityForResult(intent1, 102);
								}
							}).show();

			break;
		case R.id.userinfo_name_r:
			intent = new Intent(UserInfoActivity.this, SetUserActivity.class);
			intent.putExtra("data", "name");
			startActivityForResult(intent, 103);
			break;
		case R.id.userinfo_sex_r:
			intent = new Intent(UserInfoActivity.this, SetUserActivity.class);
			intent.putExtra("data", "sex");
			startActivityForResult(intent, 103);
			break;
		case R.id.userinfo_area_r:
			showRegion();
			break;
		case R.id.userinfo_collect_r:
			intent = new Intent(UserInfoActivity.this, SetUserActivity.class);
			intent.putExtra("data", "collect");
			startActivityForResult(intent, 103);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK) {
			return;
		}
		if (requestCode == 102) {
			if (UploadPhotoActivity.i == 3) {
				userinfo_logo.setImageBitmap(ImageHelper
						.getBitmap(UploadPhotoActivity.str));
				strimage = UploadPhotoActivity.str;
			}
		}

		// userinfo_name, userinfo_sex, userinfo_collect;
		if (requestCode == 103) {
			String st = data.getStringExtra("data");
			String info = data.getStringExtra("info");
			if (st.equals("name")) {
				userinfo_name.setText(info);
			} else if (st.equals("sex")) {
				userinfo_sex.setText(info);
			} else if (st.equals("collect")) {
				userinfo_collect.setText(info);
			}

		}

	}

	/**
	 * 显示地区 Dialog
	 */
	private void showRegion() {
		View view = dialogm();
		final MyAlertDialog dialog1 = new MyAlertDialog(UserInfoActivity.this)
				.builder()
				.setTitle(getResources().getString(R.string.region_selector))
				// .setMsg("再连续登陆15天，就可变身为QQ达人。退出QQ可能会使你现有记录归零，确定退出？")
				// .setEditText("1111111111111")
				.setView(view)
				.setNegativeButton(getResources().getString(R.string.cancel),
						new OnClickListener() {
							@Override
							public void onClick(View v) {
							}
						});
		dialog1.setPositiveButton(getResources().getString(R.string.confirm),
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// city,ccity,country
						if (city == null || country == null || country == null) {
							return;
						}
						int c1 = country.getCurrentItem();
						int c2 = city.getCurrentItem();
						int c3 = ccity.getCurrentItem();
						ctCache[0] = c1;
						ctCache[1] = c2;
						ctCache[2] = c3;
						cityTxt = AddressData.PROVINCES_BACK[c1] + " | "
								+ AddressData.CITIES_BACK[c1][c2] + " | "
								+ AddressData.COUNTIES_BACK[c1][c2][c3];
						regionId = AddressData.C_C_ID_BACK[ctCache[0]][ctCache[1]][ctCache[2]]
								+ "";
						if (ctCache[2] == 0) {
							regionId = AddressData.C_ID_BACK[ctCache[0]][ctCache[1]]
									+ "";
						}
						if (ctCache[1] == 0) {
							regionId = AddressData.P_ID_BACK[ctCache[0]] + "";
						}
						userinfo_area.setText(cityTxt);
						// ToastUtil.makeToast(RegisterActivity.this, cityTxt);

					}
				});
		dialog1.show();
	}

	/**
	 * 加载地区dialog数据
	 * 
	 * @return
	 */
	private View dialogm() {
		View contentView = LayoutInflater.from(this).inflate(
				R.layout.layout_wheel_city, null);
		country = (WheelView) contentView.findViewById(R.id.wheelcity_country);
		country.setVisibleItems(5);
		country.setViewAdapter(new CountryAdapter(this));

		final String cities[][] = AddressData.CITIES_BACK;
		final String ccities[][][] = AddressData.COUNTIES_BACK;

		city = (WheelView) contentView.findViewById(R.id.wheelcity_city);
		city.setVisibleItems(5);

		// 地区选择
		ccity = (WheelView) contentView.findViewById(R.id.wheelcity_ccity);
		ccity.setVisibleItems(5);// 不限城市

		country.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				updateCities(city, cities, newValue, 0);
				cityTxt = AddressData.PROVINCES_BACK[country.getCurrentItem()]
						+ " | "
						+ AddressData.CITIES_BACK[country.getCurrentItem()][city
								.getCurrentItem()]
						+ " | "
						+ AddressData.COUNTIES_BACK[country.getCurrentItem()][city
								.getCurrentItem()][ccity.getCurrentItem()];

			}
		});

		city.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				updatecCities(ccity, ccities, country.getCurrentItem(),
						newValue, 0);
				cityTxt = AddressData.PROVINCES_BACK[country.getCurrentItem()]
						+ " | "
						+ AddressData.CITIES_BACK[country.getCurrentItem()][city
								.getCurrentItem()]
						+ " | "
						+ AddressData.COUNTIES_BACK[country.getCurrentItem()][city
								.getCurrentItem()][ccity.getCurrentItem()];
			}
		});

		ccity.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				cityTxt = AddressData.PROVINCES_BACK[country.getCurrentItem()]
						+ " | "
						+ AddressData.CITIES_BACK[country.getCurrentItem()][city
								.getCurrentItem()]
						+ " | "
						+ AddressData.COUNTIES_BACK[country.getCurrentItem()][city
								.getCurrentItem()][ccity.getCurrentItem()];
			}
		});
		country.setCurrentItem(ctCache[0]);// 设置四川
		updateCities(city, cities, ctCache[0], ctCache[1]);
		updatecCities(ccity, ccities, ctCache[0], ctCache[1], ctCache[2]);
		return contentView;
	}

	/**
	 * Updates the city wheel （市） 城市选择
	 */
	private void updateCities(WheelView city, String cities[][], int index,
			int loc) {
		ArrayWheelAdapter<String> adapter = new ArrayWheelAdapter<String>(this,
				cities[index]);
		adapter.setTextSize(18);
		city.setViewAdapter(adapter);
		city.setCurrentItem(loc);
	}

	/**
	 * Updates the ccity wheel （县） 城市选择
	 */
	private void updatecCities(WheelView city, String ccities[][][], int index,
			int index2, int loc) {
		// if (ct[4] == 0) {
		// if (ct[5] == 1) {
		// loc = ct[2];
		// ct[4] = 1;
		// }
		// ct[5] = 1;
		// }
		ArrayWheelAdapter<String> adapter = new ArrayWheelAdapter<String>(this,
				ccities[index][index2]);
		adapter.setTextSize(18);
		city.setViewAdapter(adapter);
		city.setCurrentItem(loc);
	}

}
