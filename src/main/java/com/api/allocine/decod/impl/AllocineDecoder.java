package com.api.allocine.decod.impl;

import java.lang.reflect.Type;

import com.api.allocine.decod.IDecoder;
import com.api.allocine.factory.IFactory;
import com.api.allocine.model.IAllocineLink;
import com.api.allocine.model.IAllocineObject;
import com.api.allocine.model.ICasting;
import com.api.allocine.model.IChapter;
import com.api.allocine.model.IFeed;
import com.api.allocine.model.IGenre;
import com.api.allocine.model.IJsonResponse;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IPoster;
import com.api.allocine.model.IRelease;
import com.api.allocine.model.IResult;
import com.api.allocine.model.ISearchResponse;
import com.api.allocine.model.ISeason;
import com.api.allocine.model.ISerie;
import com.api.allocine.model.IStats;
import com.api.allocine.model.impl.ChapterResponse;
import com.api.allocine.model.impl.MovieResponse;
import com.api.allocine.model.impl.SearchResponse;
import com.api.allocine.model.impl.SeasonResponse;
import com.api.allocine.model.impl.SerieResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;

public class AllocineDecoder implements IDecoder{

	private Gson jsonParser;
	private GsonBuilder builder;
	
	public AllocineDecoder(IFactory factory){
	
		builder = new GsonBuilder();
		
		//Register class generator :
		builder.registerTypeAdapter( ISearchResponse.class , new AllocineInstanceCreator<ISearchResponse<IAllocineObject>>(factory));
		builder.registerTypeAdapter( IFeed.class , new AllocineInstanceCreator<IFeed<IAllocineObject>>(factory));
		builder.registerTypeAdapter( IMovie.class , new AllocineInstanceCreator<IMovie>(factory));
		builder.registerTypeAdapter( IRelease.class , new AllocineInstanceCreator<IRelease>(factory));
		builder.registerTypeAdapter( IResult.class , new AllocineInstanceCreator<IResult>(factory));
		builder.registerTypeAdapter( ICasting.class , new AllocineInstanceCreator<ICasting>(factory));
		builder.registerTypeAdapter( IPoster.class , new AllocineInstanceCreator<IPoster>(factory));
		builder.registerTypeAdapter( IJsonResponse.class , new AllocineInstanceCreator<IJsonResponse>(factory));
		builder.registerTypeAdapter( IStats.class , new AllocineInstanceCreator<IStats>(factory));
		builder.registerTypeAdapter( IAllocineLink.class , new AllocineInstanceCreator<IAllocineLink>(factory));
		builder.registerTypeAdapter( ISearchResponse.class , new AllocineInstanceCreator<ISearchResponse<IAllocineObject>>(factory));
		builder.registerTypeAdapter( IGenre.class , new AllocineInstanceCreator<IGenre>(factory));
		builder.registerTypeAdapter( ISerie.class , new AllocineInstanceCreator<ISerie>(factory));
		builder.registerTypeAdapter( ISeason.class , new AllocineInstanceCreator<ISeason>(factory));
		builder.registerTypeAdapter( IChapter.class , new AllocineInstanceCreator<IChapter>(factory));
		
		//register parsers :
		builder.registerTypeAdapter( IFeed.class , new FeedDecoder<IAllocineObject>(factory));
		builder.registerTypeAdapter( IMovie.class , new MovieDecoder( factory ));
		builder.registerTypeAdapter( ICasting.class , new CastingDecoder( factory ));
		builder.registerTypeAdapter( IAllocineLink.class , new LinkDecoder( factory ));
		builder.registerTypeAdapter( IPoster.class, new PosterDecoder(factory));
		builder.registerTypeAdapter( IStats.class , new StatsDecoder(factory));
		builder.registerTypeAdapter( IRelease.class , new ReleaseDecoder(factory));
		builder.registerTypeAdapter( IGenre.class , new GenreDecoder(factory));
		builder.registerTypeAdapter( ISerie.class , new SerieDecoder(factory));
		builder.registerTypeAdapter( ISeason.class , new SeasonDecoder(factory));
		builder.registerTypeAdapter( IChapter.class , new ChapterDecoder(factory));
		
		
		jsonParser = builder.create();
		
	}
	
	public void addTypeAdapter( Type deserializeClass ,  JsonDeserializer<?> deserializer ){
		builder.registerTypeAdapter( deserializeClass , deserializer );
		jsonParser = builder.create();
	}
	
	public void addTypeAdapter( Type deserializeClass ,  InstanceCreator<?> instanceCreator ){
		builder.registerTypeAdapter( deserializeClass , instanceCreator );
		jsonParser = builder.create();
	}
	
	public void registerTypeAdapterFactory( TypeAdapterFactory factory ){
		builder.registerTypeAdapterFactory( factory );
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

	public IJsonResponse decodeSeasonResponse(String json){
		return jsonParser.fromJson(json, SeasonResponse.class );
	}
	
	public IJsonResponse decodeChapterResponse(String json){
		return jsonParser.fromJson(json, ChapterResponse.class );
	}
	
	@Override
	public Gson getGson() {
		return jsonParser;
	}

	@Override
	public void addTypeAdapter(Type serializeClass, JsonSerializer<?> serializer) {
		builder.registerTypeAdapter( serializeClass , serializer );
		jsonParser = builder.create();
	}

	@Override
	public IJsonResponse decodeSerieResponse(String json) {
		return jsonParser.fromJson(json, SerieResponse.class);
	}
	
}
