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
					if( movieResponse != null && movieResponse.getMovie() != null ){
						System.out.println( "Movie " + movieResponse.getMovie().getTitle() + " fetch OK" );
					}else{
						System.out.println("Failed to get movie " + m.getCode() );
					}
				}
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
	}
	
}
