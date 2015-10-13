package com.xh.soundtell.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xh.soundtell.R;
import com.xh.soundtell.adapter.WorksAdapter;
import com.xh.soundtell.listview.XListView;
import com.xh.soundtell.listview.XListView.IXListViewListener;
import com.xh.soundtell.model.Works;
import com.xh.soundtell.ui.SetActivity;
import com.xh.soundtell.ui.UploadPhotoActivity;
import com.xh.soundtell.ui.UserInfoActivity;
import com.xh.soundtell.util.DensityUtil;
import com.xh.soundtell.util.ImageHelper;
import com.xh.soundtell.util.PrefUtil;

public class MyFragment extends Fragment implements OnClickListener,
		IXListViewListener {
	private Activity activity;
	private View parent;

	private ImageView my_iv, my_userlogo, my_set;
	private RelativeLayout my_works_r, my_fans_r, my_watch_r, my_info_r,
			my_collect_r;
	private TextView my_username, my_intro;
	private TextView my_works, my_fans, my_watch, my_info, my_collect;
	private ImageView my_works_iv, my_fans_iv, my_watch_iv, my_info_iv,
			my_collect_iv;

	private ImageView currentiv;

	private List<ImageView> imageViews = new ArrayList<ImageView>();

	private XListView xListView;
	private Handler handler = new Handler();
	private int start = 0;
	private static int refreshCnt = 0;
	private List<Works> mWorks;
	private WorksAdapter worksAdapter;
	private PrefUtil prefUtil;

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
		prefUtil = PrefUtil.getInstance();
		setData();
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
	// my_info _iv
	// my_collect_iv

	private void findView() {
		my_iv = (ImageView) parent.findViewById(R.id.my_iv);
		my_userlogo = (ImageView) parent.findViewById(R.id.my_userlogo);
		my_set = (ImageView) parent.findViewById(R.id.my_set);

		my_userlogo.setOnClickListener(this);
		my_set.setOnClickListener(this);

		my_username = (TextView) parent.findViewById(R.id.my_username);
		my_intro = (TextView) parent.findViewById(R.id.my_intro);

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
		getItem();
		xListView = (XListView) parent.findViewById(R.id.xListView);
		xListView.setPullRefreshEnable(true);
		worksAdapter = new WorksAdapter(activity, mWorks);
		xListView.setAdapter(worksAdapter);
		xListView.setXListViewListener(this);
	}

	private void getItem() {
		mWorks = new ArrayList<Works>();
		Works works = new Works("1", "歌唱祖国", "04:10");
		mWorks.add(works);
	}

	private void setData() {
		if (prefUtil.getImageLogo() != null
				&& !prefUtil.getImageLogo().equals("0")) {
			Bitmap bitmap = ImageHelper.getBitmap(prefUtil.getImageLogo());
			if (bitmap.getWidth() < 699) {
				System.out.println("进入页面是否已经输小了");
				try {
					Bitmap thumbUploadPath = ImageHelper.getThumbUploadPath(
							prefUtil.getImageLogo(), 700800,
							prefUtil.getImageLogo());
					my_iv.setImageBitmap(thumbUploadPath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				my_iv.setImageBitmap(ImageHelper.getBitmap(prefUtil
						.getImageLogo()));
			}

			my_userlogo.setImageBitmap(ImageHelper.getBitmap(prefUtil
					.getImageLogo()));
		}

		if (prefUtil.getUserName() != null
				&& !prefUtil.getUserName().equals("0")) {
			my_username.setText(prefUtil.getUserName());
		}

		if (prefUtil.getIntro() != null && !prefUtil.getIntro().equals("0")) {
			my_intro.setText(prefUtil.getIntro());
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_userlogo:
			Intent intent = new Intent(activity, UserInfoActivity.class);
			startActivityForResult(intent, 100);
			break;
		case R.id.my_set:
			Intent intent1 = new Intent(activity, SetActivity.class);
			startActivityForResult(intent1, 101);
			break;
		case R.id.my_works_r:
			xListView.setVisibility(View.VISIBLE);
			hideView(my_works_iv);
			break;
		case R.id.my_fans_r:
			xListView.setVisibility(View.GONE);
			hideView(my_fans_iv);
			break;
		case R.id.my_watch_r:
			xListView.setVisibility(View.GONE);
			hideView(my_watch_iv);
			break;
		case R.id.my_info_r:
			xListView.setVisibility(View.GONE);
			hideView(my_info_iv);
			break;
		case R.id.my_collect_r:
			xListView.setVisibility(View.GONE);
			hideView(my_collect_iv);
			break;
		default:
			break;
		}
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
			if (!image.equals("123456")) {

				Bitmap bitmap = ImageHelper.getBitmap(prefUtil.getImageLogo());
				if (bitmap.getWidth() < 699) {
					System.out.println("进入页面是否已经输小了");
					try {
						Bitmap thumbUploadPath = ImageHelper
								.getThumbUploadPath(prefUtil.getImageLogo(),
										700800, prefUtil.getImageLogo());
						my_iv.setImageBitmap(thumbUploadPath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					my_iv.setImageBitmap(ImageHelper.getBitmap(prefUtil
							.getImageLogo()));
				}

				my_userlogo.setImageBitmap(ImageHelper.getBitmap(image));
			}
			my_username.setText(name);
			my_intro.setText(collect);
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
				getItem();
				worksAdapter = new WorksAdapter(activity, mWorks);
				xListView.setAdapter(worksAdapter);
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				getItem();
				worksAdapter.notifyDataSetChanged();
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
