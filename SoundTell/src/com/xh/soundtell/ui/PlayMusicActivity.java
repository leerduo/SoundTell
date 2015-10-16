package com.xh.soundtell.ui;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.xh.soundtell.R;
import com.xh.soundtell.adapter.CommentAdapter;
import com.xh.soundtell.adapter.PageAdapter;
import com.xh.soundtell.listview.XListView;
import com.xh.soundtell.listview.XListView.IXListViewListener;
import com.xh.soundtell.model.Comment;
import com.xh.soundtell.model.MusicInfomation;
import com.xh.soundtell.music.MusicHelper;
import com.xh.soundtell.service.ControlPlay;
import com.xh.soundtell.util.DensityUtil;
import com.xh.soundtell.util.MusicUtil;
import com.xh.soundtell.view.InnerScrollView;

public class PlayMusicActivity extends Activity implements OnClickListener,
		IXListViewListener {
	private ImageView singrecord_back;
	private TextView singrecord_title, playmusic_right;

	private Intent intent;
	public static MusicInfomation musicInfomation;

	private ImageView playmusic_logo;

	private TextView playmusic_name, playmusic_username;

	public static TextView playmusic_start, playmusic_time;
	public static SeekBar playmusic_seeker;
	public static ImageView playmusic_play;
	private TextView playmusic_comment, playmusic_meto;

	private XListView xListView;
	private Handler handler = new Handler();
	private int start = 0;
	private static int refreshCnt = 0;
	private List<Comment> comments;
	private CommentAdapter commentAdapter;

	// 为歌曲时间和播放时间定义静态变量
	public static int song_time = 0;
	public static int play_time = 0;

	private ViewPager viewPager;
	private TextView[] pointTextViews;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playmusic);
		getData();
		findView();
		setData();
	}
  private boolean isSong;
	private void getData() {
		intent = getIntent();
		if(!TextUtils.isEmpty(intent.getStringExtra("hotfragment"))){
			isSong=true;
		}else{
			musicInfomation = (MusicInfomation) intent
					.getSerializableExtra("musicInfomation");
			isSong=false;
		}
	}
	private void getComments() {
		comments = new ArrayList<Comment>();
		Comment comment = null;
		for (int i = 0; i < 10; i++) {
			comment = new Comment(i + "", R.drawable.console_corridor, "张亮",
					"唱的很好1", "10-14 10:1" + i);
			comments.add(comment);
		}
	}
	private View view1, view2;
	private InnerScrollView view3;
	
	;
	private List<View> views = new ArrayList<View>();
	private int[] screenWidthAndHeight;
	
	private void findView() {
		
		ScrollView  svPerant=(ScrollView) findViewById(R.id.playmusic_sv);
		LinearLayout   playmusic_rl=(LinearLayout) findViewById(R.id.playmusic_rl);
		screenWidthAndHeight = DensityUtil.getScreenWidthAndHeight(this);
		android.view.ViewGroup.LayoutParams layoutParams = playmusic_rl.getLayoutParams();
		layoutParams.height=screenWidthAndHeight[1]*2/3;
		playmusic_rl.setLayoutParams(layoutParams);
		
		LayoutInflater lf = LayoutInflater.from(this);
		view1 = lf.inflate(R.layout.layout_musicview1, null);
		view2 = lf.inflate(R.layout.layout_musicview2, null);
		view3 = new InnerScrollView(this);
        
		view3.parentScrollView = svPerant; 
		LinearLayout.LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		LinearLayout lin=new LinearLayout(this);
		lin.setOrientation(LinearLayout.VERTICAL);
		
		
		TextView tv3=new TextView(this);
		tv3.setText(R.string.exist_for_you);
		tv3.setTextColor(Color.parseColor("#ffffff"));
		tv3.setGravity(Gravity.CENTER_HORIZONTAL);
		tv3.setLayoutParams(params);
		lin.addView(tv3);
		lin.setBackgroundColor(Color.parseColor("#40000000"));
		lin.setPadding(100, DensityUtil.dip2px(this, 50), 100, 0);
		lin.setLayoutParams(params);
		
		view3.addView(lin);
		
		views.add(view1);
		views.add(view2);
		views.add(view3);
		
		singrecord_title = (TextView) findViewById(R.id.singrecord_title);
		singrecord_title.setFocusable(true);
		singrecord_title.setFocusableInTouchMode(true);
		singrecord_title.requestFocus();

		singrecord_back = (ImageView) findViewById(R.id.singrecord_back);
		singrecord_back.setOnClickListener(this);

		playmusic_right = (TextView) findViewById(R.id.playmusic_right);

		playmusic_logo = (ImageView) findViewById(R.id.playmusic_logo);

		viewPager = (ViewPager) findViewById(R.id.playmusic_viewPager);
		LinearLayout pointLayout = (LinearLayout) findViewById(R.id.layout_point);

		pointTextViews = new TextView[3];
		for (int i = 0; i < 3; i++) {
			TextView textView = (TextView) LayoutInflater.from(this).inflate(
					R.layout.textview_point, null);
			pointTextViews[i] = textView;
			pointLayout.addView(textView);
		}
		// pointTextViews[currentIndex].setTextColor(getResources().getColor(
		// R.color.white));
		viewPager.setAdapter(new PageAdapter());
		viewPager.setCurrentItem(1);
		// viewPager.setOnPageChangeListener(new MyPageChangeListener());

		playmusic_name = (TextView) findViewById(R.id.playmusic_name);
		playmusic_username = (TextView) findViewById(R.id.playmusic_username);
		playmusic_start = (TextView) findViewById(R.id.playmusic_start);
		playmusic_time = (TextView) findViewById(R.id.playmusic_time);

		playmusic_seeker = (SeekBar) findViewById(R.id.playmusic_seeker);
		playmusic_seeker
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						
						playmusic_start.setText(MusicUtil.toTime(progress));
						if(!isSong)
						{
						playmusic_time.setText("-"
								+ MusicUtil.toTime(musicInfomation
										.getMusicTime() - progress));
						// 判断用户是否触拖SeekBar并且不为空才执行
						if (fromUser && ControlPlay.myMediaPlayer != null) {
							ControlPlay.myMediaPlayer.seekTo(progress);
						}
					}
					else{
						if(MusicHelper.mediaPlayer==null)
						MusicHelper.mediaPlayer=MediaPlayer.create(PlayMusicActivity.this, R.raw.exist_foryou);
							playmusic_time.setText("-"+MusicUtil.toTime(MusicHelper.mediaPlayer.getDuration() - progress));
						}
					}
				});

		playmusic_play = (ImageView) findViewById(R.id.playmusic_play);
		playmusic_play.setOnClickListener(this);

		playmusic_comment = (TextView) findViewById(R.id.playmusic_comment);
		playmusic_meto = (TextView) findViewById(R.id.playmusic_meto);

		playmusic_comment.setOnClickListener(this);
		playmusic_meto.setOnClickListener(this);

		xListView = (XListView) findViewById(R.id.playmusic_xListView);
		// xListView.setVisibility(View.g);
		xListView.setPullRefreshEnable(true);
		// findViewById(R.id.playmusic_l).setVisibility(View.GONE);
		
		   getComments();
			commentAdapter = new CommentAdapter(this, comments);
			xListView.setAdapter(commentAdapter);
		xListView.setXListViewListener(this);
	}

	private void setData() {
		if(!isSong){
		playmusic_name.setText(musicInfomation.getMusicName());
		playmusic_time.setText(MusicUtil.toTime(musicInfomation.getMusicTime())
				+ "");
		}else{
			playmusic_name.setText("因你而在");
//			if(MusicHelper.mediaPlayer==null)
//				MusicHelper.mediaPlayer=MediaPlayer.create(PlayMusicActivity.this, R.raw.exist_foryou);
//		   	  playmusic_time.setText(MusicUtil.toTime(MusicHelper.mediaPlayer.getDuration())
//					+ "");
		}
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.singrecord_back:
//			if (xListView.getVisibility() == View.VISIBLE) {
//				System.out.println("VISIBLE");
//				xListView.setVisibility(View.GONE);
//				findViewById(R.id.playmusic_l).setVisibility(View.VISIBLE);
//				playmusic_right.setText("举报");
//			} else {
//				System.out.println("GONE");
				PlayMusicActivity.this.finish();
//			}
			break;
		case R.id.playmusic_play:
			if(!isSong){
			Intent play_center = new Intent(PlayMusicActivity.this,
					ControlPlay.class);
			play_center.putExtra("musicInfomation", musicInfomation);
			play_center.putExtra("control", "play");
			startService(play_center);}
			else{
				if(MusicHelper.mediaPlayer.isPlaying())
					MusicHelper.stopMusic();
				 else{
				MusicHelper.startMusic(this, R.raw.exist_foryou, false);
				songHandler.sendEmptyMessageDelayed(1111, 1000);
				}
			}
			break;
		case R.id.playmusic_comment:
//			xListView.setVisibility(View.VISIBLE);
//			findViewById(R.id.playmusic_l).setVisibility(View.GONE);
//			playmusic_right.setText("回复");
			break;
		case R.id.playmusic_meto:
			Intent intent=new Intent(this,SingRecordActivity.class);
			startActivity(intent);
			break;
		}
	}
	@Override
	public void onBackPressed() {
		if (xListView.getVisibility() == View.VISIBLE) {
			System.out.println("VISIBLE");
			xListView.setVisibility(View.GONE);
			findViewById(R.id.playmusic_l).setVisibility(View.VISIBLE);
			playmusic_right.setText("举报");
		} else {
			System.out.println("GONE");
			PlayMusicActivity.this.finish();
		}
	}
	
	@Override
	protected void onDestroy() {
		MusicHelper.stopMusic();
		super.onDestroy();
	}
	
	class PageAdapter extends PagerAdapter {
		public PageAdapter() {
		}
		@Override
		public int getCount() {
			return 3;
		}
		@SuppressLint("InflateParams")
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(views.get(position));
			return views.get(position);
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(views.get(position));

		}
	}
	
 Handler	songHandler=new Handler(){
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		if(playmusic_seeker.getMax()!=MusicHelper.mediaPlayer.getDuration())
		playmusic_seeker.setMax(MusicHelper.mediaPlayer.getDuration());
		
		playmusic_seeker.setProgress(MusicHelper.mediaPlayer.getCurrentPosition());
		songHandler.sendEmptyMessageDelayed(1111, 1000);
	} 
 };
	
	@Override
	public void onRefresh() {
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				start = ++refreshCnt;
				getComments();
				commentAdapter = new CommentAdapter(PlayMusicActivity.this,
						comments);
				xListView.setAdapter(commentAdapter);
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				getComments();
				commentAdapter.notifyDataSetChanged();
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
