package com.xh.soundtell.model;

import java.io.Serializable;

public class Works implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private int iamgeid;
	private String time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIamgeid() {
		return iamgeid;
	}

	public void setIamgeid(int iamgeid) {
		this.iamgeid = iamgeid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Works(String id, String name, int iamgeid, String time) {
		this.id = id;
		this.name = name;
		this.iamgeid = iamgeid;
		this.time = time;
	}
}
