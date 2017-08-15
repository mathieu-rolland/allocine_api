package com.api.allocine.decod.impl;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api.allocine.factory.IFactory;
import com.api.allocine.model.ISeason;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class SeasonDecoder implements JsonDeserializer<ISeason>{

	private IFactory factory;
	Logger logger = LoggerFactory.getLogger( SeasonDecoder.class );
	
	public SeasonDecoder(IFactory factory){
		this.factory = factory;
	}
	
	@Override
	public ISeason deserialize(JsonElement json, Type type,
			JsonDeserializationContext context) throws JsonParseException {
		
		logger.debug( "Input : {} " , json );
		
		ISeason season = factory.createSeason();
		
		JsonObject obj = json.getAsJsonObject();

		JsonElement el = obj.get("code");
		if( el != null ) season.setCode( el.getAsInt() );
		
		el = obj.get("episodeCount");
		if( el != null ) season.setEpisodeCount( el.getAsInt() );
		
		el = obj.get("seasonNumber");
		if( el != null ) season.setSeasonNumber( el.getAsInt() );
		
		return season;
		
	}

}
