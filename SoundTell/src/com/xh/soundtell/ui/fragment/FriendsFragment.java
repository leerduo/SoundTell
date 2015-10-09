package com.xh.soundtell.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import com.xh.soundtell.R;
import com.xh.soundtell.adapter.ActivityAdapter;
import com.xh.soundtell.adapter.FriendsAdapter;
import com.xh.soundtell.model.FActivity;
import com.xh.soundtell.model.Friends;
import com.xh.soundtell.ui.FriendsInfoActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FriendsFragment extends Fragment {
	private Activity activity;
	private View parent;

	private TextView friends_search_tv;
	private ImageView friends_iv;
	private TextView friends_search_number;

	private ListView friends_lv;
	private List<Friends> friends;

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
		View view = inflater.inflate(R.layout.fragment_friends, null);
		return view;
	}

	// friends_search_tv
	// friends_iv
	// friends_search_number

	private void findView() {
		friends_search_tv = (TextView) parent
				.findViewById(R.id.friends_search_tv);
		friends_search_tv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				friends_iv.setVisibility(View.GONE);
				friends_lv.setVisibility(View.VISIBLE);
				friends_search_number.setVisibility(View.VISIBLE);
			}
		});
		friends_iv = (ImageView) parent.findViewById(R.id.friends_iv);
		friends_search_number = (TextView) parent
				.findViewById(R.id.friends_search_number);

		friends_lv = (ListView) parent.findViewById(R.id.friends_lv);
		friends = new ArrayList<Friends>();
		getItem();
		FriendsAdapter friendsAdapter = new FriendsAdapter(activity, friends);
		friends_lv.setAdapter(friendsAdapter);

		friends_lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position >= 0) {
					Friends friends = (Friends) parent.getAdapter().getItem(position);
					Intent intent = new Intent(activity,
							FriendsInfoActivity.class);
					intent.putExtra("friendsinfo", friends);
					startActivity(intent);
				}
			}
		});
	}

	public void getItem() {
		Friends friends1 = new Friends("1", R.drawable.activity_1, "天蓝蓝",
				"天空很蓝、也非常美丽");
		Friends friends2 = new Friends("2", R.drawable.activity_2, "天色",
				"这个人很懒，什么也没有留下");
		Friends friends3 = new Friends("3", R.drawable.activity_3, "天谕",
				"这个人很懒，什么也没有留下");

		friends.add(friends1);
		friends.add(friends2);
		friends.add(friends3);
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
