package com.xh.soundtell.ui;

import java.util.ArrayList;
import java.util.List;

import com.xh.soundtell.R;
import com.xh.soundtell.adapter.WorksAdapter;
import com.xh.soundtell.listview.XListView;
import com.xh.soundtell.listview.XListView.IXListViewListener;
import com.xh.soundtell.model.Friends;
import com.xh.soundtell.model.Works;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FriendsInfoActivity extends Activity implements OnClickListener,
		IXListViewListener {
	private Intent intent;
	private Friends friends;

	private RelativeLayout friendsinfo_works_r, friendsinfo_fans_r,
			friendsinfo_photo_r;
	private TextView friendsinfo_username, friendsinfo_intro;
	private ImageView friendsinfo_works_iv, friendsinfo_fans_iv,
			friendsinfo_photo_iv;

	private ImageView currentiv;
	private List<ImageView> imageViews = new ArrayList<ImageView>();

	private XListView xListView;
	private Handler handler = new Handler();
	private int start = 0;
	private static int refreshCnt = 0;
	private List<Works> mWorks;
	private WorksAdapter worksAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friendsinfo);
		intent = getIntent();
		friends = (Friends) intent.getSerializableExtra("friendsinfo");
		findView();
	}

	private void findView() {
		friendsinfo_works_r = (RelativeLayout) findViewById(R.id.friendsinfo_works_r);
		friendsinfo_fans_r = (RelativeLayout) findViewById(R.id.friendsinfo_fans_r);
		friendsinfo_photo_r = (RelativeLayout) findViewById(R.id.friendsinfo_photo_r);

		friendsinfo_works_r.setOnClickListener(this);
		friendsinfo_fans_r.setOnClickListener(this);
		friendsinfo_photo_r.setOnClickListener(this);

		friendsinfo_username = (TextView) findViewById(R.id.friendsinfo_username);
		friendsinfo_intro = (TextView) findViewById(R.id.friendsinfo_intro);
		friendsinfo_username.setText(friends.getName());
		friendsinfo_intro.setText(friends.getIntro());

		friendsinfo_works_iv = (ImageView) findViewById(R.id.friendsinfo_works_iv);
		friendsinfo_fans_iv = (ImageView) findViewById(R.id.friendsinfo_fans_iv);
		friendsinfo_photo_iv = (ImageView) findViewById(R.id.friendsinfo_photo_iv);

		imageViews.add(friendsinfo_works_iv);
		imageViews.add(friendsinfo_fans_iv);
		imageViews.add(friendsinfo_photo_iv);

		xListView = (XListView) findViewById(R.id.friendsinfo_xListView);
		xListView.setPullRefreshEnable(true);
		mWorks = new ArrayList<Works>();
		// worksAdapter = new WorksAdapter(FriendsInfoActivity.this, null);
		// xListView.setAdapter(worksAdapter);
		xListView.setXListViewListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.friendsinfo_works_r:
			hideView(friendsinfo_works_iv);
			break;
		case R.id.friendsinfo_fans_r:
			hideView(friendsinfo_fans_iv);
			break;
		case R.id.friendsinfo_photo_r:
			hideView(friendsinfo_photo_iv);
			break;
		default:
			break;
		}
	}

	private void hideView(ImageView iv) {
		for (int i = 0; i < imageViews.size(); i++) {
			if (iv.equals(imageViews.get(i))) {
				imageViews.get(i).setVisibility(View.VISIBLE);
			} else {
				imageViews.get(i).setVisibility(View.GONE);
			}
		}
	}

	@Override
	public void onRefresh() {
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				start = ++refreshCnt;
				// worksAdapter = new WorksAdapter(FriendsInfoActivity.this,
				// null);
				// xListView.setAdapter(worksAdapter);
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// worksAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}

	protected void onLoad() {
		xListView.stopRefresh();
		xListView.stopLoadMore();
		xListView.setRefreshTime("2015:10:08 20:10:11");
	}
}
