package com.xh.soundtell.model;

import java.io.Serializable;

public class Friends implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private int image;
	private String name;
	private String intro;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Friends(String id, int image, String name, String intro) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
		this.intro = intro;
	}

}
