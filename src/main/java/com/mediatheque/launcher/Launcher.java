package com.mediatheque.launcher;

import java.io.UnsupportedEncodingException;

import com.api.allocine.IAllocineAPI;
import com.api.allocine.factory.IFactory;
import com.api.allocine.factory.impl.AllocineFactory;
import com.api.allocine.model.IFeed;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IMovieResponse;
import com.api.allocine.model.ISearchResponse;

public class Launcher {

	public static void main(String[] args) {
		
		IFactory factory = new AllocineFactory();
		IAllocineAPI api = factory.createSimpleAllocineAPI();
		
		try{
			ISearchResponse response =  api.searchMovies( "minority report" );
			IFeed content = response.getFeed();
			
			if( content.getMovies() != null ){
				for( IMovie m : content.getMovies() ){
					IMovieResponse movieResponse = api.getMovieDetails( m );
					System.out.println( movieResponse.getMovie().getSynospis() );
				}
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
	}
	
}
