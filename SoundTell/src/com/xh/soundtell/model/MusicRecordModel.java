package com.xh.soundtell.model;

public class MusicRecordModel {

	private int musicId;//音乐ID
	
	private  String musicTitle;//歌名
	
	private  String musicPath;//歌曲路径
	
	private  String musicSinger;//歌手
	
	private  String musicLyric;//歌词

	private  String musicType;//种类+品质   流行-A-2-17-B（普通音质）
	
	public MusicRecordModel(int musicId, String musicTitle, String musicPath,
			String musicSinger, String musicLyric, String musicType) {
		super();
		this.musicId = musicId;
		this.musicTitle = musicTitle;
		this.musicPath = musicPath;
		this.musicSinger = musicSinger;
		this.musicLyric = musicLyric;
		this.musicType = musicType;
	}

	public int getMusicId() {
		return musicId;
	}
	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}

	public String getMusicTitle() {
		return musicTitle;
	}

	public void setMusicTitle(String musicTitle) {
		this.musicTitle = musicTitle;
	}

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}

	public String getMusicSinger() {
		return musicSinger;
	}

	public void setMusicSinger(String musicSinger) {
		this.musicSinger = musicSinger;
	}

	public String getMusicLyric() {
		return musicLyric;
	}

	public void setMusicLyric(String musicLyric) {
		this.musicLyric = musicLyric;
	}

	public String getMusicType() {
		return musicType;
	}

	public void setMusicType(String musicType) {
		this.musicType = musicType;
	}

	
}
