package com.xh.soundtell.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xh.soundtell.R;
import com.xh.soundtell.ui.UserInfoActivity;

public class MyFragment extends Fragment implements OnClickListener {
	private Activity activity;
	private View parent;

	private ImageView my_userlogo, my_set;
	private RelativeLayout my_works_r, my_fans_r, my_watch_r, my_info_r,
			my_collect_r;
	private TextView my_works, my_fans, my_watch, my_info, my_collect;
	private ImageView my_works_iv, my_fans_iv, my_watch_iv, my_info_iv,
			my_collect_iv;

	private ImageView currentiv;

	private List<ImageView> imageViews = new ArrayList<ImageView>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		activity = getActivity();
		parent = getView();
		findView();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_my, null);
		return view;
	}

	// my_userlogo
	// my_set
	//
	// my_works_r
	// my_fans_r
	// my_watch_r
	// my_info_r
	// my_collect_r
	//
	// my_works
	// my_fans
	// my_watch
	// my_info
	// my_collect
	//
	// my_works_iv
	// my_fans_iv
	// my_watch_iv
	// my_info_iv
	// my_collect_iv

	private void findView() {
		my_userlogo = (ImageView) parent.findViewById(R.id.my_userlogo);
		my_set = (ImageView) parent.findViewById(R.id.my_set);
		my_userlogo.setOnClickListener(this);
		my_set.setOnClickListener(this);

		my_works_r = (RelativeLayout) parent.findViewById(R.id.my_works_r);
		my_fans_r = (RelativeLayout) parent.findViewById(R.id.my_fans_r);
		my_watch_r = (RelativeLayout) parent.findViewById(R.id.my_watch_r);
		my_info_r = (RelativeLayout) parent.findViewById(R.id.my_info_r);
		my_collect_r = (RelativeLayout) parent.findViewById(R.id.my_collect_r);
		my_works_r.setOnClickListener(this);
		my_fans_r.setOnClickListener(this);
		my_watch_r.setOnClickListener(this);
		my_info_r.setOnClickListener(this);
		my_collect_r.setOnClickListener(this);

		my_works = (TextView) parent.findViewById(R.id.my_works);
		my_fans = (TextView) parent.findViewById(R.id.my_fans);
		my_watch = (TextView) parent.findViewById(R.id.my_watch);
		my_info = (TextView) parent.findViewById(R.id.my_info);
		my_collect = (TextView) parent.findViewById(R.id.my_collect);

		my_works_iv = (ImageView) parent.findViewById(R.id.my_works_iv);
		my_fans_iv = (ImageView) parent.findViewById(R.id.my_fans_iv);
		my_watch_iv = (ImageView) parent.findViewById(R.id.my_watch_iv);
		my_info_iv = (ImageView) parent.findViewById(R.id.my_info_iv);
		my_collect_iv = (ImageView) parent.findViewById(R.id.my_collect_iv);
		imageViews.add(my_works_iv);
		imageViews.add(my_fans_iv);
		imageViews.add(my_watch_iv);
		imageViews.add(my_info_iv);
		imageViews.add(my_collect_iv);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_userlogo:
			Intent intent = new Intent(activity, UserInfoActivity.class);
			startActivityForResult(intent, 100);
			System.out.println("1111");
			break;
		case R.id.my_set:

			break;
		case R.id.my_works_r:
			hideView(my_works_iv);
			break;
		case R.id.my_fans_r:
			hideView(my_fans_iv);
			break;
		case R.id.my_watch_r:
			hideView(my_watch_iv);
			break;
		case R.id.my_info_r:
			hideView(my_info_iv);
			break;
		case R.id.my_collect_r:
			hideView(my_collect_iv);
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
		// my_works_iv.setVisibility(View.GONE);
		// my_fans_iv.setVisibility(View.GONE);
		// my_watch_iv.setVisibility(View.GONE);
		// my_info_iv.setVisibility(View.GONE);
		// my_collect_iv.setVisibility(View.GONE);
		// if (my_works_iv.) {
		//
		// }
		//
		// if (currentiv != null && currentiv != iv) {
		// iv.setVisibility(View.VISIBLE);
		// }
		// currentiv = iv;

	}
}
