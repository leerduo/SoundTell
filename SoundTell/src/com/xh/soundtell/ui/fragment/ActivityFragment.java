package com.xh.soundtell.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import com.xh.soundtell.R;
import com.xh.soundtell.adapter.ActivityAdapter;
import com.xh.soundtell.adapter.WorksAdapter;
import com.xh.soundtell.listview.XListView;
import com.xh.soundtell.model.FActivity;
import com.xh.soundtell.model.Works;
import com.xh.soundtell.ui.SetActivity;
import com.xh.soundtell.ui.UserInfoActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ActivityFragment extends Fragment {
	private Activity activity;
	private View parent;

	private ListView activity_lv;
	private List<FActivity> fActivities;

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
		View view = inflater.inflate(R.layout.fragment_activity, null);
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
		activity_lv = (ListView) parent.findViewById(R.id.activity_lv);
		fActivities = new ArrayList<FActivity>();
		getItem();
		ActivityAdapter activityAdapter = new ActivityAdapter(activity,
				fActivities);
		activity_lv.setAdapter(activityAdapter);
	}

	public void getItem() {
		FActivity fActivity1 = new FActivity("1", R.drawable.activity_1,
				"两小无猜专辑成长计划", "正在进行");
		FActivity fActivity2 = new FActivity("2", R.drawable.activity_2,
				"两小无猜", "已结束");
		FActivity fActivity3 = new FActivity("3", R.drawable.activity_3,
				"通缉令，赏金丰厚", "正在进行");

		fActivities.add(fActivity1);
		fActivities.add(fActivity2);
		fActivities.add(fActivity3);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != activity.RESULT_OK) {
			return;
		}
		if (requestCode == 100) {
			String image = data.getStringExtra("image");
			String name = data.getStringExtra("name");
			String collect = data.getStringExtra("collect");
			// if (!image.equals("123456")) {
			// my_iv.setImageBitmap(ImageHelper.getBitmap(image));
			// my_userlogo.setImageBitmap(ImageHelper.getBitmap(image));
			// }
			// my_username.setText(name);
			// my_intro.setText(collect);
		}
	}

}
