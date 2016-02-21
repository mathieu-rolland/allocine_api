package com.api.allocine.decod.impl;

import com.api.allocine.decod.IDecoder;
import com.api.allocine.factory.IFactory;
import com.api.allocine.model.IAllocineLink;
import com.api.allocine.model.ICasting;
import com.api.allocine.model.IFeed;
import com.api.allocine.model.IJsonResponse;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IMovieResponse;
import com.api.allocine.model.IPoster;
import com.api.allocine.model.IRelease;
import com.api.allocine.model.IResult;
import com.api.allocine.model.ISearchResponse;
import com.api.allocine.model.IStats;
import com.api.allocine.model.impl.MovieResponse;
import com.api.allocine.model.impl.SearchResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AllocineDecoder implements IDecoder{

	private Gson jsonParser;
	
	public AllocineDecoder(IFactory factory){
	
		GsonBuilder builder = new GsonBuilder();
		
		//Register class generator :
		builder.registerTypeAdapter( IMovie.class , new AllocineInstanceCreator<IMovie>(factory));
		builder.registerTypeAdapter( IRelease.class , new AllocineInstanceCreator<IRelease>(factory));
		builder.registerTypeAdapter( IResult.class , new AllocineInstanceCreator<IResult>(factory));
		builder.registerTypeAdapter( ICasting.class , new AllocineInstanceCreator<ICasting>(factory));
		builder.registerTypeAdapter( IPoster.class , new AllocineInstanceCreator<IPoster>(factory));
		builder.registerTypeAdapter( IJsonResponse.class , new AllocineInstanceCreator<IJsonResponse>(factory));
		builder.registerTypeAdapter( IStats.class , new AllocineInstanceCreator<IStats>(factory));
		builder.registerTypeAdapter( IAllocineLink.class , new AllocineInstanceCreator<IAllocineLink>(factory));
		builder.registerTypeAdapter( IFeed.class , new AllocineInstanceCreator<IFeed>(factory));
		builder.registerTypeAdapter( ISearchResponse.class , new AllocineInstanceCreator<ISearchResponse>(factory));
		
		//register parsers :
		builder.registerTypeAdapter( IMovie.class , new MovieDecoder( factory ));
		builder.registerTypeAdapter( ICasting.class , new CastingDecoder( factory ));
		builder.registerTypeAdapter( IAllocineLink.class , new LinkDecoder( factory ));
		
		
		jsonParser = builder.create();
		
	}
	
	/**
	 * Parse json with search format and return result as Java object
	 */
	public IJsonResponse decodeSearchResponse ( String json ){
		return jsonParser.fromJson(json, SearchResponse.class);
	}

	/**
	 * Decod a json string using the movie query format.
	 * Return a MovieResponse hidden by a JSonResponse.
	 */
	@Override
	public IJsonResponse decodeMovieResponse(String json) {
		return jsonParser.fromJson(json, MovieResponse.class);
	}
	
}
