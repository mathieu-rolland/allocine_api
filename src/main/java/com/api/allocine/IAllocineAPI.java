package com.api.allocine;

import java.util.Map;

import com.api.allocine.model.IJsonResponse;

public interface IAllocineAPI {

	public enum ALLO_CINE_METHOD{
		SEARCH, MOVIE
	}
	
	public enum ALLO_CINE_PARAMS{
		SEARCH, FILTER, PARTNER, SED, SIG, FORMAT, CODE
	}

	public IJsonResponse httpQuery(ALLO_CINE_METHOD search, Map<ALLO_CINE_PARAMS, String> params);
	
}
