package com.xh.soundtell.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import com.xh.soundtell.R;
import com.xh.soundtell.adapter.ActivityAdapter;
import com.xh.soundtell.adapter.WorksAdapter;
import com.xh.soundtell.listview.XListView;
import com.xh.soundtell.model.FActivity;
import com.xh.soundtell.model.Works;
import com.xh.soundtell.ui.ActivityWeb;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
		activity_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(activity, ActivityWeb.class);
				intent.putExtra("web", position + "");
				startActivity(intent);
			}
		});
	}

	public void getItem() {
		FActivity fActivity2 = new FActivity("2", R.drawable.activity_2,
				"两小无猜", "正在进行");
		FActivity fActivity3 = new FActivity("3", R.drawable.activity_3,
				"通缉令，赏金丰厚", "正在进行");
		FActivity fActivity4 = new FActivity("4", R.drawable.activity_4,
				"一首歌证明我是小清新", "已结束");
		FActivity fActivity5 = new FActivity("5", R.drawable.activity_5,
				"唱首情歌给闺蜜", "已结束");
		FActivity fActivity6 = new FActivity("6", R.drawable.activity_6,
				"青春一起歌唱", "已结束");
		FActivity fActivity7 = new FActivity("7", R.drawable.activity_7,
				"这是唱给爸爸的歌", "已结束");
		FActivity fActivity8 = new FActivity("8", R.drawable.activity_8,
				"有一种声音叫做夏天", "已结束");

		fActivities.add(fActivity2);
		fActivities.add(fActivity3);
		fActivities.add(fActivity4);
		fActivities.add(fActivity5);
		fActivities.add(fActivity6);
		fActivities.add(fActivity7);
		fActivities.add(fActivity8);
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
