package com.api.allocine.model.impl;

import com.api.allocine.model.ISeason;
import com.api.allocine.model.ISeasonResponse;

public class SeasonResponse implements ISeasonResponse {

	private ISeason season;

	public ISeason getSeason() {
		return season;
	}

	public void setSeason(ISeason season) {
		this.season = season;
	}
	
}
