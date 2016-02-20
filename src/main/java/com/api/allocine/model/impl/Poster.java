package com.api.allocine.model.impl;

import com.api.allocine.model.IPoster;

public class Poster implements IPoster {

	private String path;
	private String href;
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getHref() {
		return href;
	}
	
	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public String toString() {
		return "Poster [path=" + path + ", href=" + href + "]";
	}
	
}
