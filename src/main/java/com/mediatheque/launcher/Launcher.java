package com.mediatheque.launcher;

import com.api.allocine.IAllocineAPI;
import com.api.allocine.factory.IFactory;
import com.api.allocine.factory.impl.AllocineFactory;
import com.api.allocine.model.IFeed;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.ISearchResponse;

public class Launcher {

	public static void main(String[] args) {
		
		IFactory factory = new AllocineFactory();
		IAllocineAPI api = factory.createSimpleAllocineAPI();
		
		ISearchResponse response =  api.searchMovies( "potter" );
		IFeed content = response.getFeed();
		
		if( content.getMovie() != null ){
			for( IMovie m : content.getMovie() ){
				api.getMovieDetails(m);
			}
		}
		
//		Map<AllocineAPI.ALLO_CINE_PARAMS, String> params = new HashMap<AllocineAPI.ALLO_CINE_PARAMS, String>();
//
//		params = new HashMap<AllocineAPI.ALLO_CINE_PARAMS, String>();
//		
//		params.put(ALLO_CINE_PARAMS.CODE, "58608");
//		params.put(ALLO_CINE_PARAMS.FORMAT, "json" );
//		params.put(ALLO_CINE_PARAMS.FILTER, "movie");
//		
//		api.httpQuery( ALLO_CINE_METHOD.MOVIE , params );
		
	}
	
}
