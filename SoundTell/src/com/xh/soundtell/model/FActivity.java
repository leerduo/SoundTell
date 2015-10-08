package com.xh.soundtell.model;

import java.io.Serializable;

public class FActivity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private int image_id;
	private String activity_name;
	private String activity_state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getImage_id() {
		return image_id;
	}

	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public String getActivity_state() {
		return activity_state;
	}

	public void setActivity_state(String activity_state) {
		this.activity_state = activity_state;
	}

	public FActivity(String id, int image_id, String activity_name,
			String activity_state) {
		super();
		this.id = id;
		this.image_id = image_id;
		this.activity_name = activity_name;
		this.activity_state = activity_state;
	}

}
