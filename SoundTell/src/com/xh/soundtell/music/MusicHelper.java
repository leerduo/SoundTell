package com.xh.soundtell.music;



import com.xh.soundtell.R;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicHelper {
	//新建对象
	public static MediaPlayer mediaPlayer;
	
	public static int musicId = -1;
	
	public static boolean musicState = true;
	/**
	 * @param context 
	 * @param id     音乐 id
	 * @param looping  是否循环
	 */
//	public static void startMusic(Context context, int id, Boolean looping) {
		public static void startMusic(Context context,int id, Boolean looping) {
		//判断状态
		if (!musicState) {
			return;
		}
		//判断id和是否播放
//		if (musicId == id && mediaPlayer.isPlaying()) {
//			return;
//		}
//		musicId = id;
       //判断是否为空
		if (mediaPlayer != null) {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
			}
			
			mediaPlayer.reset();
			mediaPlayer = null;
		}
		//实例化
		mediaPlayer = MediaPlayer.create(context, id);
		
//		mediaPlayer = MediaPlayer.create(context, musicIds);
		//设置循环
		mediaPlayer.setLooping(looping);
		//开启
		mediaPlayer.start()
		;
	}
	/**
	 * 关闭音乐
	 * */
	public static void stopMusic() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}

	}
	
	
//	public void startSeekBarUpdate() {
//		handler.post(start);
//	}
//
//	Runnable start = new Runnable() {
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			handler.post(updatesb);
//		}
//	};
//
//	Runnable updatesb = new Runnable() {
//
//		@Override
//		public void run() {
//			// 获取SeekBar走动到那的时间
//			MusicPlayerActivity.play_time = myMediaPlayer
//					.getCurrentPosition();
//
//			// 设置填充当前获取的进度
//			MusicPlayerActivity.seekbar
//					.setProgress(MusicPlayerActivity.play_time);
//			// SeekBar的最大值填充歌曲时间
//			MusicPlayerActivity.seekbar.setMax(Music_infoAdapter.musicList
//					.get(playing_id).getMusicTime());
//
//			// 线程延迟1000毫秒启动
//			handler.postDelayed(updatesb, 1000);
//		}
//	};
}
