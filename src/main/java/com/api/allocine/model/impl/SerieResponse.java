package com.api.allocine.model.impl;

import com.api.allocine.model.ISerie;
import com.api.allocine.model.ISerieResponse;

public class SerieResponse implements ISerieResponse {

	private ISerie tvseries;
	
	@Override
	public ISerie getSerie() {
		return tvseries;
	}

	@Override
	public void setSerie(ISerie serie) {
		this.tvseries = serie;
	}

}
