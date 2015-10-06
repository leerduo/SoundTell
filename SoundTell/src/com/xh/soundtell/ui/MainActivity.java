package com.xh.soundtell.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.xh.soundtell.R;
import com.xh.soundtell.setting.SettingHelper;
import com.xh.soundtell.ui.fragment.ActivityFragment;
import com.xh.soundtell.ui.fragment.FriendsFragment;
import com.xh.soundtell.ui.fragment.HotFragment;
import com.xh.soundtell.ui.fragment.MyFragment;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private View currentView;

	private TextView tv1, tv2, tv3, tv4;
	private ImageView iv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SettingHelper.getInstance().setCurrentActivity(this);
		SettingHelper.getInstance().setApplicationContext(this);
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
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		switch (v.getId()) {
		case R.id.tv1:
			MyFragment myFragment = new MyFragment();
			ft.replace(R.id.frame_layout, myFragment);
			ft.commit();
			break;
		case R.id.tv2:
			HotFragment hotFragment = new HotFragment();
			ft.replace(R.id.frame_layout, hotFragment);
			ft.commit();
			break;
		case R.id.tv3:
			ActivityFragment activityFragment = new ActivityFragment();
			ft.replace(R.id.frame_layout, activityFragment);
			ft.commit();
			break;
		case R.id.tv4:
			FriendsFragment friendsFragment = new FriendsFragment();
			ft.replace(R.id.frame_layout, friendsFragment);
			ft.commit();
			break;
		default:
			break;
		}
		setButton(v);
	}

	private void setButton(View v) {
		if (currentView != null && currentView != v) {
			currentView.setSelected(false);
		}
		v.setSelected(true);
		currentView = v;
	}

}