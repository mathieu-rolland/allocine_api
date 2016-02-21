package com.api.allocine;

import java.util.Map;

import com.api.allocine.model.IJsonResponse;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IMovieResponse;
import com.api.allocine.model.ISearchResponse;

public interface IAllocineAPI {

	public enum ALLO_CINE_METHOD{
		SEARCH, MOVIE
	}
	
	public enum ALLO_CINE_PARAMS{
		SEARCH, FILTER, PARTNER, SED, SIG, FORMAT, CODE
	}

	public enum FILTER{
		MOVIE{
			public String toString(){
				return "movie";
			}
		}
	}
	
	public enum RESPONSE_FORMAT{
		JSON{
			public String toString(){
				return "json";
			}
		}
	}
	
	public IJsonResponse httpQuery(ALLO_CINE_METHOD search, Map<ALLO_CINE_PARAMS, String> params);
	public IMovieResponse getMovieDetails( IMovie movie );
	public ISearchResponse searchMovies(String search);
	
}
