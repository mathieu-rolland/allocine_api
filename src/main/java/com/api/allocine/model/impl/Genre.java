package com.api.allocine.model.impl;

import com.api.allocine.model.IGenre;

public class Genre implements IGenre {

	private int code;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
