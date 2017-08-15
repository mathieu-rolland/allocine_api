package com.api.allocine;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.api.allocine.model.IChapter;
import com.api.allocine.model.IChapterResponse;
import com.api.allocine.model.IJsonResponse;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IMovieResponse;
import com.api.allocine.model.ISearchResponse;
import com.api.allocine.model.ISeason;
import com.api.allocine.model.ISeasonResponse;
import com.api.allocine.model.ISerie;
import com.api.allocine.model.ISerieResponse;

public interface IAllocineAPI {

	public enum ALLO_CINE_METHOD{
		SEARCH, MOVIE, TVSERIES, SEASON, EPISODE
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
		},
		SEASON{
			public String toString(){
				return "season";
			}
		},
		CHAPTER{
			public String toString(){
				return "episode";
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
	public ISeasonResponse getSeasonDetails( ISeason serie ) throws UnsupportedEncodingException;
	public IChapterResponse getChapterDetails( IChapter serie ) throws UnsupportedEncodingException;
	public ISearchResponse<ISerie> searchSeries(String search) throws UnsupportedEncodingException;
	public ISearchResponse<IMovie> searchMovies(String search) throws UnsupportedEncodingException;
	
}
