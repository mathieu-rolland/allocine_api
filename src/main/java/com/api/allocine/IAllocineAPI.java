package com.api.allocine;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.api.allocine.model.IJsonResponse;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IMovieResponse;
import com.api.allocine.model.ISearchResponse;
import com.api.allocine.model.ISerie;
import com.api.allocine.model.ISerieResponse;

public interface IAllocineAPI {

	public enum ALLO_CINE_METHOD{
		SEARCH, MOVIE, TVSERIES
	}
	
	public enum ALLO_CINE_PARAMS{
		SEARCH, FILTER, PARTNER, SED, SIG, FORMAT, CODE, PROFILE
	}

	public enum FILTER{
		MOVIE{
			public String toString(){
				return "movie";
			}
		},
		SERIE{
			public String toString(){
				return "tvseries";
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
	
	public enum PROFILE{
		SMALL, MEDIUM, LARGE
	}
	
	public IJsonResponse httpQuery(ALLO_CINE_METHOD search, Map<ALLO_CINE_PARAMS, String> params) throws UnsupportedEncodingException;
	public IMovieResponse getMovieDetails( IMovie movie ) throws UnsupportedEncodingException;
	public ISerieResponse getSerieDetails( ISerie serie ) throws UnsupportedEncodingException;
	public ISearchResponse<ISerie> searchSeries(String search) throws UnsupportedEncodingException;
	public ISearchResponse<IMovie> searchMovies(String search) throws UnsupportedEncodingException;
	
}
