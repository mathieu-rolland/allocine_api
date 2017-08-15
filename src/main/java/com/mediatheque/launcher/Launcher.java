package com.mediatheque.launcher;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

import com.api.allocine.IAllocineAPI;
import com.api.allocine.factory.IFactory;
import com.api.allocine.factory.impl.AllocineFactory;
import com.api.allocine.model.IChapter;
import com.api.allocine.model.IChapterResponse;
import com.api.allocine.model.IFeed;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IMovieResponse;
import com.api.allocine.model.ISearchResponse;
import com.api.allocine.model.ISeason;
import com.api.allocine.model.ISeasonResponse;
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
			ISearchResponse<ISerie> response = api.searchSeries( "Game of throne" );
			IFeed<ISerie> content = response.getFeed();
			System.out.println( "Search gives : " + content );
			if( content.getApiAllocineObject() != null ){
				Iterator<ISerie> series = ((ArrayList<ISerie>) content.getApiAllocineObject()).iterator();
				while( series.hasNext() ){
					
					ISerie searchSerie = series.next();
					
					System.out.println(searchSerie);
					
					ISerieResponse serieResponse = api.getSerieDetails(searchSerie);
					System.out.println("Loaded serie : " + serieResponse.getSerie());
					if( serieResponse != null && serieResponse.getSerie() != null ){
						
						System.out.println( "Serie " + serieResponse.getSerie().getTitle() + " fetch OK" );
						System.out.println( "Number of loaded seasons : " + serieResponse.getSerie().getSeasonCount() + " fetch OK" );
						
						//Season details : 
						Iterator<ISeason> iterator = serieResponse.getSerie().getSeasons().iterator();
						iterator.next();
						ISeason season = iterator.next();
						//Get Season details :
						ISeasonResponse seasonResponse = api.getSeasonDetails( season );
						System.out.println( seasonResponse == null ? "null" : seasonResponse.getSeason() );
						
						//Fetch for the first episode : 
						if( season.getChapters().size() > 0 ){
							System.out.println("call get episode");
							IChapter chapter = season.getChapters().iterator().next();
							IChapterResponse chapterResponse = api.getChapterDetails( chapter );
							System.out.println( chapterResponse.getChapter() );
						}
						
					}else{
						System.out.println("Failed to get movie " + searchSerie.getCode() );
					}
				}
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
	
}
