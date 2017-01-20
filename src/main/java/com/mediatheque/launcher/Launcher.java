package com.mediatheque.launcher;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

import com.api.allocine.IAllocineAPI;
import com.api.allocine.factory.IFactory;
import com.api.allocine.factory.impl.AllocineFactory;
import com.api.allocine.model.IFeed;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IMovieResponse;
import com.api.allocine.model.ISearchResponse;
import com.api.allocine.model.ISerie;
import com.api.allocine.model.ISerieResponse;

public class Launcher {

	public static void main(String[] args) {
		
		IFactory factory = new AllocineFactory();
		IAllocineAPI api = factory.createSimpleAllocineAPI();
		
		try{
			ISearchResponse<IMovie> response = api.searchMovies( "minority" );
			IFeed<IMovie> content = response.getFeed();
			System.out.println( content );
			if( content.getApiAllocineObject() != null ){
				Iterator<IMovie> movies = ((ArrayList<IMovie>) content.getApiAllocineObject()).iterator();
				while( movies.hasNext() ){
					IMovie m = movies.next();
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
		
		try{
			ISearchResponse<ISerie> response = api.searchSeries( "malcom" );
			IFeed<ISerie> content = response.getFeed();
			System.out.println( content );
			if( content.getApiAllocineObject() != null ){
				Iterator<ISerie> series = ((ArrayList<ISerie>) content.getApiAllocineObject()).iterator();
				while( series.hasNext() ){
					ISerie s = series.next();
					System.out.println(s);
					ISerieResponse serieResponse = api.getSerieDetails(s);
					if( serieResponse != null && serieResponse.getSerie() != null ){
						System.out.println( "Movie " + serieResponse.getSerie().getTitle() + " fetch OK" );
					}else{
						System.out.println("Failed to get movie " + s.getCode() );
					}
				}
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
	
}
