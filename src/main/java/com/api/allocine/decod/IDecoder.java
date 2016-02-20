package com.api.allocine.decod;

import com.api.allocine.model.IJsonResponse;

public interface IDecoder {

	public IJsonResponse decodeSearchResponse(String json);
	public IJsonResponse decodeMovieResponse(String json);
	
}
