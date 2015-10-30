package com.xh.soundtell.ui.fragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xh.soundtell.R;
import com.xh.soundtell.adapter.WorksAdapter;
import com.xh.soundtell.ios.view.MyAlertDialog;
import com.xh.soundtell.listview.XListView;
import com.xh.soundtell.listview.XListView.IXListViewListener;
import com.xh.soundtell.model.MusicInfomation;
import com.xh.soundtell.model.Works;
import com.xh.soundtell.ui.PlayMusicActivity;
import com.xh.soundtell.ui.SetActivity;
import com.xh.soundtell.ui.UserInfoActivity;
import com.xh.soundtell.util.ImageHelper;
import com.xh.soundtell.util.PrefUtil;
import com.xh.soundtell.util.ToastUtil;

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

	private List<MusicInfomation> mis;
	private MusicInfomation mi;
	private boolean isLongClick;
	private String date;

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
		// ShowMp3List();
		WavList();
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
		if (mis != null) {
			System.out.println("111111111111111111");
			worksAdapter = new WorksAdapter(activity, mis);
			xListView.setAdapter(worksAdapter);
		}
		xListView.setXListViewListener(this);
		isLongClick = false;
		xListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (!isLongClick) {
					System.out.println("position" + position + "<br/>"
							+ "mis.size()" + mis.size());
					if (position <= mis.size()) {
						System.out.println("position" + position);
						MusicInfomation musicInfomation = (MusicInfomation) parent
								.getAdapter().getItem(position);
						Intent intent = new Intent(activity,
								PlayMusicActivity.class);
						intent.putExtra("musicInfomation", musicInfomation);
						startActivity(intent);
					} else {
						onLoadMore();
					}
				} else {
					isLongClick = false;
				}
			}
		});

		xListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				isLongClick = true;
				if (position <= mis.size()) {
					System.out.println("position" + position);
					MusicInfomation musicInfomation = (MusicInfomation) parent
							.getAdapter().getItem(position);
					final String musicPath = musicInfomation.getMusicPath();
					String musicName = musicInfomation.getMusicName();
					MyAlertDialog alertDialog = new MyAlertDialog(activity)
							.builder().setTitle("你确定要删除歌曲" + musicName)
							.setPositiveButton("确定", new OnClickListener() {
								@Override
								public void onClick(View v) {
									File file = new File(musicPath);
									if (file.exists()) {
										file.delete();
										WavList();
										worksAdapter = new WorksAdapter(
												activity, mis);
										xListView.setAdapter(worksAdapter);
									}
								}
							});
					alertDialog.setNegativeButton("取消", null).show();
				} else {
					onLoadMore();
				}
				return false;
			}
		});
	}

	@Override
	public void onResume() {
		isLongClick = false;
		WavList();

		xListView = (XListView) parent.findViewById(R.id.xListView);
		xListView.setPullRefreshEnable(true);
		if (mis != null) {
			System.out.println("111111111111111111");
			worksAdapter = new WorksAdapter(activity, mis);
			xListView.setAdapter(worksAdapter);
		}
		xListView.setXListViewListener(this);

		super.onResume();
	}

	private void getItem() {

	}

	private void setData() {
		if (prefUtil.getImageLogo() != null
				&& !prefUtil.getImageLogo().equals("0")) {
			// Bitmap bitmap = ImageHelper.getBitmap(prefUtil.getImageLogo());
			// if (bitmap.getWidth() < 699) {
			// System.out.println("进入页面是否已经输小了");
			// try {
			// Bitmap thumbUploadPath = ImageHelper.getThumbUploadPath(
			// prefUtil.getImageLogo(), 700800,
			// prefUtil.getImageLogo());
			// my_iv.setImageBitmap(thumbUploadPath);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// } else {
			my_iv.setImageBitmap(ImageHelper.getBitmap(prefUtil.getImageLogo()));
			// }

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
		List<MusicInfomation> infomations = new ArrayList<MusicInfomation>();
		switch (v.getId()) {
		case R.id.my_userlogo:
			Intent intent = new Intent(activity, UserInfoActivity.class);
			startActivityForResult(intent, 100);
			break;
		case R.id.my_set:
			Intent intent1 = new Intent(activity, SetActivity.class);
			startActivityForResult(intent1, 100);

			break;
		case R.id.my_works_r:
			WavList();
			worksAdapter = new WorksAdapter(activity, mis);
			xListView.setAdapter(worksAdapter);
			xListView.setVisibility(View.VISIBLE);
			hideView(my_works_iv);
			break;
		case R.id.my_fans_r:
			worksAdapter = new WorksAdapter(activity, infomations);
			xListView.setAdapter(worksAdapter);
			xListView.setVisibility(View.VISIBLE);
			hideView(my_fans_iv);
			break;
		case R.id.my_watch_r:
			worksAdapter = new WorksAdapter(activity, infomations);
			xListView.setAdapter(worksAdapter);
			xListView.setVisibility(View.VISIBLE);
			hideView(my_watch_iv);
			break;
		case R.id.my_info_r:
			worksAdapter = new WorksAdapter(activity, infomations);
			xListView.setAdapter(worksAdapter);
			xListView.setVisibility(View.VISIBLE);
			hideView(my_info_iv);
			break;
		case R.id.my_collect_r:
			worksAdapter = new WorksAdapter(activity, infomations);
			xListView.setAdapter(worksAdapter);
			xListView.setVisibility(View.VISIBLE);
			hideView(my_collect_iv);
			break;
		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		System.out.println("requestCode" + requestCode + "resultCode:"
				+ resultCode);
		if (resultCode == activity.RESULT_CANCELED) {
			// startActivity(new Intent(activity, MainActivity.class));
			// activity.finish();
		}
		if (resultCode != activity.RESULT_OK) {
			return;
		}
		if (requestCode == 100) {
			// System.out.println("name" + "111111111111111111");
			// String image = data.getStringExtra("image");
			// String name = data.getStringExtra("name");
			// String collect = data.getStringExtra("collect");
			// if (!image.equals("123456")) {
			//
			// // Bitmap bitmap =
			// // ImageHelper.getBitmap(prefUtil.getImageLogo());
			// // if (bitmap.getWidth() < 699) {
			// // System.out.println("进入页面是否已经输小了");
			// // try {
			// // Bitmap thumbUploadPath = ImageHelper
			// // .getThumbUploadPath(prefUtil.getImageLogo(),
			// // 700800, prefUtil.getImageLogo());
			// // my_iv.setImageBitmap(thumbUploadPath);
			// // } catch (Exception e) {
			// // e.printStackTrace();
			// // }
			// // } else {
			// my_iv.setImageBitmap(ImageHelper.getBitmap(prefUtil
			// .getImageLogo()));
			// // }
			//
			// my_userlogo.setImageBitmap(ImageHelper.getBitmap(image));
			// }
			// System.out.println("name" + name);
			// my_username.setText(name);
			// my_intro.setText(collect);
			if (prefUtil.getImageLogo() != null
					&& !prefUtil.getImageLogo().equals("0")) {
				// Bitmap bitmap =
				// ImageHelper.getBitmap(prefUtil.getImageLogo());
				// if (bitmap.getWidth() < 699) {
				// System.out.println("进入页面是否已经输小了");
				// try {
				// Bitmap thumbUploadPath = ImageHelper.getThumbUploadPath(
				// prefUtil.getImageLogo(), 700800,
				// prefUtil.getImageLogo());
				// my_iv.setImageBitmap(thumbUploadPath);
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
				// } else {
				my_iv.setImageBitmap(ImageHelper.getBitmap(prefUtil
						.getImageLogo()));
				// }

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
				if (mis != null) {
					worksAdapter = new WorksAdapter(activity, mis);
					xListView.setAdapter(worksAdapter);
				}
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		System.out.println("gengduo");
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				getItem();
				if (mis != null) {
					worksAdapter.notifyDataSetChanged();
				}
				onLoad();
			}
		}, 2000);
	}

	protected void onLoad() {
		xListView.stopRefresh();
		xListView.stopLoadMore();
		xListView.setRefreshTime("2015:10:08 20:10:11");
	}

	private void WavList() {
		System.out.println("WavList");
		mis = new ArrayList<MusicInfomation>();
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File path = Environment.getExternalStorageDirectory();// 获得SD卡路径
			File Stringpath = new File(path, "/音诉音乐");
			File[] files = Stringpath.listFiles();// 读取
			getFileName(files);
		}
	}

	@SuppressLint("SimpleDateFormat")
	private void getFileName(File[] files) {
		System.out.println("getFileName");
		if (files != null) {// 先判断目录是否为空，否则会报空指针
			System.out.println("files");
			for (File file : files) {
				if (file.isDirectory()) {
					// Log.i("zeng", "若是文件目录。继续读1" + file.getName().toString()
					// + file.getPath().toString());
					//
					// getFileName(file.listFiles());
					// Log.i("zeng", "若是文件目录。继续读2" + file.getName().toString()
					// + file.getPath().toString());
				} else {
					String fileName = file.getName();
					System.out.println("ap" + file.getAbsolutePath());

					if (fileName.endsWith(".wav")) {
						String s = fileName.substring(0,
								fileName.lastIndexOf(".")).toString();
						MusicInfomation infomation = new MusicInfomation();
						infomation.setMusicName(s);
						infomation.setMusicPath(file.getAbsolutePath());

						try {
							String time = (String) s.subSequence(
									s.length() - 13, s.length());
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd");
							date = sdf.format(new Date(Long.valueOf(time)));

						} catch (Exception e) {
							ToastUtil.makeToast(activity, "文件夹内有非法文件");
						}
						infomation.setMusicAlbum(date);
						System.out.println("date:" + date + "n/" + "s" + s);

						mis.add(infomation);
					}
				}
			}
		}
		
		xListView = (XListView) parent.findViewById(R.id.xListView);
		xListView.setPullRefreshEnable(true);
		if (mis != null) {
			System.out.println("111111111111111111");
			worksAdapter = new WorksAdapter(activity, mis);
			xListView.setAdapter(worksAdapter);
		}
		xListView.setXListViewListener(this);
	}

	/**
	 * 显示MP3信息,其中_ids保存了所有音乐文件的_ID，用来确定到底要播放哪一首歌曲，_titles存放音乐名，用来显示在播放界面，
	 * 而_path存放音乐文件的路径（删除文件时会用到）。
	 */
	// @SuppressLint("SimpleDateFormat")
	// private void ShowMp3List() {
	// mis = new ArrayList<MusicInfomation>();
	// // 用游标查找媒体详细信息
	// Cursor cursor = activity.getContentResolver().query(
	// MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
	// new String[] { MediaStore.Audio.Media.TITLE,// 标题，游标从0读取
	// MediaStore.Audio.Media.DURATION,// 持续时间,1
	// MediaStore.Audio.Media.ARTIST,// 艺术家,2
	// MediaStore.Audio.Media._ID,// id,3
	// MediaStore.Audio.Media.DISPLAY_NAME,// 显示名称,4
	// MediaStore.Audio.Media.DATA,// 数据，5
	// MediaStore.Audio.Media.ALBUM_ID,// 专辑名称ID,6
	// MediaStore.Audio.Media.ALBUM,// 专辑,7
	// MediaStore.Audio.Media.SIZE }, null, null,
	// MediaStore.Audio.Media.ARTIST);// 大小,8
	// /**
	// * 判断游标是否为空，有些地方即使没有音乐也会报异常。而且游标不稳定。稍有不慎就出错了,其次，如果用户没有音乐的话，
	// * 不妨可以告知用户没有音乐让用户添加进去
	// */
	// if (cursor != null && cursor.getCount() == 0) {
	// Toast.makeText(activity, "没有录制歌曲", Toast.LENGTH_LONG).show();
	// return;
	// }
	// System.out.println(" cursor size " + cursor.getCount());
	// // music_info = new Music_infoAdapter(this, cursor);
	// // new MusicListView().disPlayList(musicListView, this, cursor);
	//
	// System.out.println("111111111111");
	// /** 将游标移到第一位 **/
	// cursor.moveToFirst();
	// if (cursor != null) {
	// // 移动到第一个
	// cursor.moveToFirst();
	// // 获得歌曲的各种属性
	// for (int i = 0; i < cursor.getCount(); i++) {
	// // 过滤mp3文件
	// if (cursor.getString(5).endsWith(".wav")) {
	// mi = new MusicInfomation();
	// String name = cursor.getString(0);
	// try {
	// String time = (String) name.subSequence(
	// name.length() - 13, name.length());
	// SimpleDateFormat sdf = new SimpleDateFormat(
	// "yyyy-MM-dd");
	// date = sdf.format(new Date(Long.valueOf(time)));
	//
	// } catch (Exception e) {
	// ToastUtil.makeToast(activity, "文件夹内有非法文件");
	// }
	// System.out.println("date:" + date + "n/" + "name" + name);
	// mi.setMusicName(cursor.getString(0));// 歌曲名称
	// mi.setMusicTime(cursor.getInt(1));// 歌曲时间长度
	// mi.setMusicAlbum(date);// 专辑
	// mi.setMusicSinger(cursor.getString(3));// 歌手
	// mi.setMusicSize(cursor.getInt(4));// 大小
	// mi.setMusicPath(cursor.getString(5));// 路径
	// mi.set_id(cursor.getInt(6));// 歌曲id
	//
	// System.out.println("歌名：" + cursor.getString(0) + "<br/>"
	// + "长度：" + cursor.getInt(1) + "<br/>" + "专辑："
	// + cursor.getString(2) + "<br/>" + "歌手："
	// + cursor.getString(3) + "<br/>" + "大小："
	// + cursor.getInt(4) + "<br/>" + "路径："
	// + cursor.getString(5) + "<br/>" + "歌曲id："
	// + cursor.getInt(6));
	//
	// // System.out.println("-----------------------");
	// // Nothing In The World
	// // 238971
	// // Atomic Kitten
	// // 849
	// // Atomic Kitten - Nothing In The World.mp3
	// // /storage/emulated/0/kgmusic/download/Atomic Kitten -
	// // Nothing In The World.mp3
	// // 6
	// // com.android.cwd.Music_infoAdapter$MusicInfomation@42dcae18
	// // System.out.println(" "+ mCursor.getString(0));
	// // System.out.println(" "+ mCursor.getString(1));
	// // System.out.println(" "+ mCursor.getString(2));
	// // System.out.println(" "+ mCursor.getString(3));
	// // System.out.println(" "+ mCursor.getString(4));
	// // System.out.println(" "+ mCursor.getString(5));
	// // System.out.println(" "+ mCursor.getString(6));
	// // System.out.println(" "+ mi);
	// // System.out.println("-----------------------");
	// // 装载到列表中
	// mis.add(mi);
	// }
	// // 移动到下一个
	// cursor.moveToNext();
	// }
	// for (int i = 0; i < mis.size(); i++) {
	// System.out.println("musicList size " + mis.size()
	// + mis.get(i).getMusicName());
	// }
	//
	// }
	// }
}
