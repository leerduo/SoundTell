package com.xh.soundtell.model;

public class Ranking {

	public Ranking(int imgId, String userName, String songName, String time,
			int level) {
		super();
		this.imgId = imgId;
		this.userName = userName;
		this.songName = songName;
		this.time = time;
		this.level = level;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	int imgId;
	String userName;
	String songName;
	String time;
	int level;

}
