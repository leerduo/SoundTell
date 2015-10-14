package com.xh.soundtell.service;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.xh.soundtell.R;
import com.xh.soundtell.model.MusicInfomation;
import com.xh.soundtell.ui.PlayMusicActivity;
import com.xh.soundtell.util.MusicUtil;

/**
 * 类说明：音乐播放服务类
 * 
 * @author LiangAn
 * @version 创建时间：2015年3月18日 上午10:24:17
 */
public class ControlPlay extends Service {

	public static MediaPlayer myMediaPlayer;

	public static PlayMusicActivity c_ma;
	// public LrcProcess mLrcProcess;
	// public LrcView mLrcView;

	public static int playing_id = 0;

	// 初始化歌词检索值
	private int index = 0;
	// 初始化歌曲播放时间的变量
	private int CurrentTime = 0;
	// 初始化歌曲总时间的变量
	private int CountTime = 0;
	// 创建对象
	// private List<LrcContent> lrcList = new ArrayList<LrcContent>();

	// public static Music_infoAdapter m_in;

	public Thread thread;
	Handler handler = new Handler();
	public boolean playFlag = true;
	public int vTemp = 0;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		initMediaSource(initMusicUri(0));
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (myMediaPlayer != null) {
			myMediaPlayer.stop();
			myMediaPlayer.release();
			myMediaPlayer = null;
		}
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		// musicInfomation = (MusicInfomation)
		// intent.getExtras().getSerializable(
		// "musicInfomation");
		// System.out.println("mp1Path:" + musicInfomation.getMusicPath());

		String playFlag = intent.getExtras().getString("control");
		if ("play".equals(playFlag)) {
			playMusic();
		}
		// else if ("next".equals(playFlag)) {
		// playNext();
		// } else if ("front".equals(playFlag)) {
		// playFront();
		// } else if ("listClick".equals(playFlag)) {
		// playing_id = intent.getExtras().getInt("musicId_1");
		// initMediaSource(initMusicUri(playing_id));
		// playMusic();
		// } else if ("gridClick".equals(playFlag)) {
		// playing_id = intent.getExtras().getInt("musicId_2");
		// initMediaSource(initMusicUri(playing_id));
		// playMusic();
		// }
	}

	/**
	 * 初始化媒体对象
	 * 
	 * @param mp3Path
	 *            mp3路径
	 */
	public void initMediaSource(String mp3Path) {
		System.out.println("mp3Path:" + mp3Path);
		Uri mp3Uri = Uri.parse(mp3Path);
		if (myMediaPlayer != null) {
			myMediaPlayer.stop();
			myMediaPlayer.reset();
			myMediaPlayer = null;
		}

		// 为myMediaPlayer创建对象
		myMediaPlayer = MediaPlayer.create(this, mp3Uri);
		// myMediaPlayer = MediaPlayer.create(this, R.raw.aaaa);
		// 律动 c_ma.startRhythm(myMediaPlayer);
	}

	/**
	 * 返回列表第几行的歌曲路径
	 * 
	 * @param _id
	 *            表示歌曲序号，从0开始
	 */
	public String initMusicUri(int _id) {
		playing_id = _id;
		String s;
		// 判断列表和列表长度不为空时才获取
		if (PlayMusicActivity.musicInfomation != null) {
			s = PlayMusicActivity.musicInfomation.getMusicPath();
			return s;
		} else {
			// 否则返回空字符串
			return "";
		}
	}

	/**
	 * 音乐播放方法，并且带有暂停方法
	 */
	public void playMusic() {

		// 判断歌曲不能为空

		if (PlayMusicActivity.musicInfomation != null) {
			if (myMediaPlayer.isPlaying()) {
				// 歌曲暂停
				myMediaPlayer.pause();
				// 更换播放按钮背景
				PlayMusicActivity.playmusic_play
						.setImageResource(R.drawable.song_detail_play_btn);
				// 取消通知
				// MusicPlayerActivity.mNotificationManager.cancel(1);
			} else {
				myMediaPlayer.start();
				PlayMusicActivity.playmusic_play
						.setImageResource(R.drawable.record_pause_ib);
				// // /////////////////////// 初始化歌词配置 /////////////////////// //
				// mLrcProcess = new LrcProcess();
				// // 读取歌词文件
				// mLrcProcess.readLRC(Music_infoAdapter.musicList.get(playing_id)
				// .getMusicPath());
				// // 传回处理后的歌词文件
				// lrcList = mLrcProcess.getLrcContent();
				// MusicPlayerActivity.lrc_view.setSentenceEntities(lrcList);
				// // 切换带动画显示歌词
				// MusicPlayerActivity.lrc_view.setAnimation(AnimationUtils
				// .loadAnimation(ControlPlay.this, R.anim.alpha_z));
				// // 启动线程
				// mHandler.post(mRunnable);
				// // /////////////////////// 初始化歌词配置 /////////////////////// //
				//
				// // 更换背景
				// MusicPlayerActivity.play_ImageButton
				// .setBackgroundResource(R.drawable.pause_button);
				// 启动线程更新SeekBar
				startSeekBarUpdate();

				// // 更新歌曲播放第几首
				// int x = playing_id + 1;
				// MusicPlayerActivity.music_number.setText("" + x + "/"
				// + Music_infoAdapter.musicList.size());
				//
				// // 截取.mp3字符串
				// String a = Music_infoAdapter.musicList.get(playing_id)
				// .getMusicName();
				// int b = a.indexOf(".mp3");
				// String c = a.substring(0, b);
				// 切换带动画更新歌曲名
				// MusicPlayerActivity.music_Name.setText(a);
				// MusicPlayerActivity.music_Name.setAnimation(AnimationUtils
				// .loadAnimation(ControlPlay.this, R.anim.translate_z));
				//
				// // 带动画更新专辑名
				// MusicPlayerActivity.music_Album
				// .setText(Music_infoAdapter.musicList.get(playing_id)
				// .getMusicAlbum());
				// MusicPlayerActivity.music_Album.setAnimation(AnimationUtils
				// .loadAnimation(ControlPlay.this, R.anim.alpha_y));

				// 更新歌曲时间
				// PlayMusicActivity.playmusic_start.setText(MusicUtil
				// .toTime(PlayMusicActivity.musicInfomation
				// .getMusicTime())
				// + "");

				// // 获取专辑图片路径
				// String url = MusicPlayerActivity.music_info
				// .getAlbumArt(Music_infoAdapter.musicList.get(
				// ControlPlay.playing_id).get_id());
				// if (url != null) {
				//
				// // 显示获取的专辑图片
				// MusicPlayerActivity.music_AlbumArt.setImageURI(Uri
				// .parse(url));
				// MusicPlayerActivity.music_AlbumArt
				// .setAnimation(AnimationUtils.loadAnimation(
				// MusicPlayerActivity.mContext,
				// R.anim.alpha_z));

				// // 开启通知,传入歌曲名和艺术家及通知图标
				// MusicPlayerActivity.setNotice(a, a,
				// Music_infoAdapter.musicList.get(playing_id)
				// .getMusicAlbum(), R.drawable.notice_icon);

				// try {
				// /* 为倒影创建位图 */
				// Bitmap mBitmap = BitmapFactory.decodeFile(url);
				// MusicPlayerActivity.reflaction
				// .setImageBitmap(MusicPlayerActivity
				// .createReflectedImage(mBitmap));
				// MusicPlayerActivity.reflaction
				// .setAnimation(AnimationUtils.loadAnimation(
				// MusicPlayerActivity.mContext,
				// R.anim.alpha_z));
				// } catch (Exception e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }

			}
			// else {
			// MusicPlayerActivity.music_AlbumArt
			// .setImageResource(R.drawable.album);
			// MusicPlayerActivity.music_AlbumArt
			// .setAnimation(AnimationUtils.loadAnimation(
			// MusicPlayerActivity.mContext,
			// R.anim.alpha_y));
			//
			// // // 开启通知,传入歌曲名和艺术家及通知图标
			// // MusicPlayerActivity.setNotice(a, a,
			// // Music_infoAdapter.musicList.get(playing_id)
			// // .getMusicAlbum(), R.drawable.notice_icon);
			//
			// try {
			// /* 为倒影创建位图 */
			// Bitmap mBitmap = ((BitmapDrawable) getResources()
			// .getDrawable(R.drawable.album)).getBitmap();
			// MusicPlayerActivity.reflaction
			// .setImageBitmap(MusicPlayerActivity
			// .createReflectedImage(mBitmap));
			// MusicPlayerActivity.reflaction
			// .setAnimation(AnimationUtils.loadAnimation(
			// MusicPlayerActivity.mContext,
			// R.anim.alpha_z));
			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// }

			// }

			/**
			 * 监听播放是否完成
			 */
			myMediaPlayer.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
					PlayMusicActivity.playmusic_play
							.setImageResource(R.drawable.song_detail_play_btn);
					// 播放完当前歌曲，自动播放下一首
					// playNext();
				}
			});

		} else {
			Toast.makeText(ControlPlay.this, "木有在手机里找到歌曲啊...",
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 播放下一首
	 */
	// public void playNext() {
	//
	// // 判断歌曲不能为空
	// if (Music_infoAdapter.musicList.size() != 0) {
	// // 如果到了最后一首则一直播放最后一首
	// if (playing_id == Music_infoAdapter.musicList.size() - 1) {
	// playing_id = Music_infoAdapter.musicList.size() - 1;
	// Toast.makeText(ControlPlay.this, "已经是最后一首啦！",
	// Toast.LENGTH_SHORT).show();
	//
	// MusicPlayerActivity.play_ImageButton
	// .setBackgroundResource(R.drawable.play_button);
	// MusicPlayerActivity.mNotificationManager.cancel(1);
	//
	// } else {
	// initMediaSource(initMusicUri(++playing_id));
	// playMusic();
	// }
	// } else {
	// Toast.makeText(ControlPlay.this, "木有找到歌曲啊！", Toast.LENGTH_SHORT)
	// .show();
	// }
	// }

	/**
	 * 播放上一首
	 */
	// public void playFront() {
	//
	// // 判断歌曲不能为空
	// if (Music_infoAdapter.musicList.size() != 0) {
	// // 如果到了第一首则一直播放第一首
	// if (playing_id == 0) {
	// playing_id = 0;
	// Toast.makeText(ControlPlay.this, "现在就是第一首哦！",
	// Toast.LENGTH_SHORT).show();
	// } else {
	// initMediaSource(initMusicUri(--playing_id));
	// playMusic();
	// }
	// } else {
	// Toast.makeText(ControlPlay.this, "木有找到歌曲啊！", Toast.LENGTH_SHORT)
	// .show();
	// }
	// }

	public void startSeekBarUpdate() {
		handler.post(start);
	}

	Runnable start = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			handler.post(updatesb);
		}

	};

	Runnable updatesb = new Runnable() {

		@Override
		public void run() {
			// 获取SeekBar走动到那的时间
			PlayMusicActivity.play_time = myMediaPlayer.getCurrentPosition();
			System.out.println("myMediaPlayer.getCurrentPosition():"
					+ myMediaPlayer.getCurrentPosition());
			// 设置填充当前获取的进度
			PlayMusicActivity.playmusic_seeker
					.setProgress(PlayMusicActivity.play_time);
			// SeekBar的最大值填充歌曲时间
			PlayMusicActivity.playmusic_seeker
					.setMax(PlayMusicActivity.musicInfomation.getMusicTime());

			// 线程延迟1000毫秒启动
			handler.postDelayed(updatesb, 1000);
		}
	};

	Handler mHandler = new Handler();
	// 歌词滚动线程
	// Runnable mRunnable = new Runnable() {
	//
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// MusicPlayerActivity.lrc_view.SetIndex(LrcIndex());
	// MusicPlayerActivity.lrc_view.invalidate();
	// mHandler.postDelayed(mRunnable, 100);
	// }
	// };

	/**
	 * 歌词同步处理类
	 */
	// public int LrcIndex() {
	// if (myMediaPlayer.isPlaying()) {
	// // 获得歌曲播放在哪的时间
	// CurrentTime = myMediaPlayer.getCurrentPosition();
	// // 获得歌曲总时间长度
	// CountTime = myMediaPlayer.getDuration();
	// }
	// if (CurrentTime < CountTime) {
	//
	// for (int i = 0; i < lrcList.size(); i++) {
	// if (i < lrcList.size() - 1) {
	// if (CurrentTime < lrcList.get(i).getLrc_time() && i == 0) {
	// index = i;
	// }
	// if (CurrentTime > lrcList.get(i).getLrc_time()
	// && CurrentTime < lrcList.get(i + 1).getLrc_time()) {
	// index = i;
	// }
	// }
	// if (i == lrcList.size() - 1
	// && CurrentTime > lrcList.get(i).getLrc_time()) {
	// index = i;
	// }
	// }
	// }
	// return index;
	// }
}
