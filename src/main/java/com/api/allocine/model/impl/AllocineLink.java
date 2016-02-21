package com.api.allocine.model.impl;

import com.api.allocine.model.IAllocineLink;

public class AllocineLink implements IAllocineLink{

	private String rel;
	private String href;
	private String name;
	
	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public String toString() {
		return "AllocineLink [rel=" + rel + ", href=" + href + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
