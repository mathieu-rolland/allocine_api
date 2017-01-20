package com.api.allocine.model.impl;

import com.api.allocine.model.ISerie;
import com.api.allocine.model.ISerieResponse;

public class SerieResponse implements ISerieResponse {

	private ISerie serie;
	
	@Override
	public ISerie getSerie() {
		return serie;
	}

	@Override
	public void setSerie(ISerie serie) {
		this.serie = serie;
	}

}
