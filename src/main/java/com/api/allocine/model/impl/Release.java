package com.api.allocine.model.impl;

import com.api.allocine.model.IRelease;

public class Release implements IRelease {

	private String releaseDate;
	
	public String toString(){
		return "releaseDate : " +  releaseDate;
	}
	
}
