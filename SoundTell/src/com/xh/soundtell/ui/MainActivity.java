package com.xh.soundtell.ui;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.xh.soundtell.R;
import com.xh.soundtell.setting.SettingHelper;
import com.xh.soundtell.ui.fragment.ActivityFragment;
import com.xh.soundtell.ui.fragment.FriendsFragment;
import com.xh.soundtell.ui.fragment.HotFragment;
import com.xh.soundtell.ui.fragment.MyFragment;
import com.xh.soundtell.util.DensityUtil;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private View currentView;

	private TextView tv1, tv2, tv3, tv4;
	private ImageView iv1;

	private PopupWindow popupWindow;

	private Handler mHandler = new Handler();
	private boolean isExit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
	}

	private void findView() {
		tv1 = (TextView) findViewById(R.id.tv1);
		tv2 = (TextView) findViewById(R.id.tv2);
		tv3 = (TextView) findViewById(R.id.tv3);
		tv4 = (TextView) findViewById(R.id.tv4);
		iv1 = (ImageView) findViewById(R.id.iv1);

		tv1.setOnClickListener(this);
		tv2.setOnClickListener(this);
		tv3.setOnClickListener(this);
		tv4.setOnClickListener(this);
		iv1.setOnClickListener(this);

		tv1.performClick();
	}

	@Override
	public void onClick(View v) {
		if (currentView != null && currentView.equals(v)) {
			return;
		}
		Intent intent = null;
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		switch (v.getId()) {
		case R.id.tv1:
			MyFragment myFragment = new MyFragment();
			ft.replace(R.id.frame_layout, myFragment);
			ft.commit();
			setButton(v);
			break;
		case R.id.tv2:
			HotFragment hotFragment = new HotFragment();
			ft.replace(R.id.frame_layout, hotFragment);
			ft.commit();
			setButton(v);
			break;
		case R.id.tv3:
			ActivityFragment activityFragment = new ActivityFragment();
			ft.replace(R.id.frame_layout, activityFragment);
			ft.commit();
			setButton(v);
			break;
		case R.id.tv4:
			FriendsFragment friendsFragment = new FriendsFragment();
			ft.replace(R.id.frame_layout, friendsFragment);
			ft.commit();
			setButton(v);
			break;
		case R.id.iv1:
			// show popupwindow
			getPopwindow();
			break;

		/******* popupWindow ******/
		case R.id.popupsing_ll1:
			intent = new Intent(this, SingRecordActivity.class);
			showDisPopWindow();
			break;
		case R.id.popupsing_ll2:
			intent = new Intent(this, SingRecordActivity.class);
			showDisPopWindow();
			break;
		case R.id.popupsing_ll3:
			intent = new Intent(this, PersonalOptionActivity.class);
			showDisPopWindow();
			break;
		case R.id.popupsing_back:
			showDisPopWindow();
			break;
		}
		if (intent != null) {
			startActivity(intent);
		}
	}

	private void setButton(View v) {
		if (currentView != null && currentView != v) {
			currentView.setSelected(false);
		}
		v.setSelected(true);
		currentView = v;
	}

	private void getPopwindow() {
		final View popView = LayoutInflater.from(this).inflate(
				R.layout.popup_sing, null);
		View llView1 = popView.findViewById(R.id.popupsing_ll1);
		View llView2 = popView.findViewById(R.id.popupsing_ll2);
		View llView3 = popView.findViewById(R.id.popupsing_ll3);
		View backView = popView.findViewById(R.id.popupsing_back);

		llView1.setOnClickListener(this);
		llView2.setOnClickListener(this);
		llView3.setOnClickListener(this);
		backView.setOnClickListener(this);

		popupWindow = new PopupWindow(popView,
				DensityUtil.getScreenWidthAndHeight(this)[0],
				DensityUtil.getScreenWidthAndHeight(this)[1]
						- DensityUtil.dip2px(this, 50));
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow
				.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

		// popView.setOnTouchListener(new OnTouchListener() {
		// public boolean onTouch(View v, MotionEvent event) {
		// int height = popView.findViewById(R.id.pop_layout).getTop();
		// int y = (int) event.getY();
		// if (event.getAction() == MotionEvent.ACTION_UP) {
		// if (y < height) {
		// popupWindow.dismiss();
		// }
		// }
		// return true;
		// }
		// });
		showDisPopWindow();
	}

	public void showDisPopWindow() {
		if (popupWindow != null) {
			if (popupWindow.isShowing()) {
				popupWindow.dismiss();
			} else {
				popupWindow.showAtLocation(getWindow().getDecorView(),
						Gravity.TOP, 0, DensityUtil.dip2px(this, 50));
			}
		}
	}

	/**
	 * 监听返回--是否退出程序
	 */
	@Override
	public void onBackPressed() {
		if (isExit) {
			exit();
		} else {
			isExit = true;
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					isExit = false;
				}
			}, 2000);
			Toast.makeText(getApplicationContext(), "再按一次 退出程序",
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 退出程序
	 */
	private void exit() {
		finish();
	}

}