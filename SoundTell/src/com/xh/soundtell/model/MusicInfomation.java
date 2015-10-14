/*************************************************************************************************
 * 版权所有 (C)2015,  成都市商联汇通技术有限公司
 * 
 * 文件名称：MusicInfomation.java
 * 内容摘要：升级服务
 * 当前版本： TODO
 * 作        者： 李加蒙
 * 完成日期：2015-10-14 下午1:05:48
 * 修改记录：
 * 修改日期：2015-10-14 下午1:05:48
 * 版   本  号：
 * 修   改  人：
 * 修改内容：
 ************************************************************************************************/

package com.xh.soundtell.model;

import java.io.Serializable;

/**
 * 文件名称：MusicInfomation.java
 */
public class MusicInfomation implements Serializable {

	/**
	 * 1、歌曲名 2、歌手 3、歌曲时间 4、专辑(专辑图片，专辑名称，专辑ID[用来获取图片]) 5、歌曲大小
	 */
	private static final long serialVersionUID = 1L;

	private int _id;
	private String musicName;
	private String musicSinger;
	private int musicTime;
	private String musicAlbum;
	private int musicSize;
	private String musicPath;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public String getMusicSinger() {
		return musicSinger;
	}

	public void setMusicSinger(String musicSinger) {
		this.musicSinger = musicSinger;
	}

	public int getMusicTime() {
		return musicTime;
	}

	public void setMusicTime(int musicTime) {
		this.musicTime = musicTime;
	}

	public String getMusicAlbum() {
		return musicAlbum;
	}

	public void setMusicAlbum(String musicAlbum) {
		this.musicAlbum = musicAlbum;
	}

	public int getMusicSize() {
		return musicSize;
	}

	public void setMusicSize(int musicSize) {
		this.musicSize = musicSize;
	}

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}

}
