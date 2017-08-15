package com.api.allocine.decod.impl;

import java.lang.reflect.Type;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api.allocine.factory.IFactory;
import com.api.allocine.model.ICasting;
import com.api.allocine.model.IChapter;
import com.api.allocine.model.IPoster;
import com.api.allocine.model.ISeason;
import com.api.allocine.model.ISerie;
import com.api.allocine.model.IStats;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class SerieDecoder implements JsonDeserializer<ISerie>{

	private IFactory factory;

	Logger logger = LoggerFactory.getLogger( SerieDecoder.class );
	
	public SerieDecoder(IFactory factory) {
		this.factory = factory;
	}

	@Override
	public ISerie deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		
		logger.debug( "Input : " + element.toString());
		
		ISerie serie = factory.createSerie();
		
		JsonObject obj = element.getAsJsonObject();
		
		JsonElement e = obj.get("code");
		if( e != null ){
			serie.setCode( e.getAsInt() );
		}
		
		e = obj.get("originalTitle");
		if( e != null ){
			serie.setTitle( e.getAsString() );
		}
		
		e = obj.get("yearStart");
		if( e != null ){
			serie.setYear( e.getAsInt() );
		}
		
		e = obj.get("seasonCount");
		if( e != null ){
			serie.setSeasonCount( e.getAsInt() );
		}
		
		e = obj.get("castingShort");
		if( e != null ){
			ICasting casting = context.deserialize(e, ICasting.class );
			if( casting != null ) serie.setCasting( casting );
		}
		
		e = obj.get("statistics");
		if( e != null ){
			IStats stats = context.deserialize( e , IStats.class );
			if( stats != null ) serie.setIStats( stats );
		}
		
		e = obj.get("poster");
		if( e != null ){
			IPoster poster = context.deserialize( e ,  IPoster.class );
			if( poster != null ) serie.setPoster( poster );
		}
		
		e = obj.get("productionStatus");
		if( e != null ){
			//TODO
		}
		
		JsonArray array = obj.getAsJsonArray("season");
		if( array != null ){
			Type collectionType = new TypeToken<Collection<ISeason>>(){}.getType();
			Collection<ISeason> seasons = context.deserialize( array , collectionType);
			serie.setSeasons(seasons);
		}
		
		array = obj.getAsJsonArray("broadcast");

		if( array != null ){
			System.out.println(array.toString());
			for( int i = 0 ; i < array.size() ; i++ ){
				JsonObject broadcastObject = array.get(i).getAsJsonObject();
				JsonObject onShow = broadcastObject.getAsJsonObject("onShow");
				IChapter chapter = context.deserialize(onShow.get("episode") , IChapter.class );
				addToSeason(serie, chapter);
			}
		}
		
		return serie;
	}

	//TODO : algo a refaire...
	private void addToSeason( ISerie serie , IChapter chapter )
	{
		Collection<ISeason> seasons = serie.getSeasons();
		for(ISeason s : seasons){
			if( s.getCode() == chapter.getParentSeason()){
				Collection<IChapter> chapters = s.getChapters();
				boolean found = false;
				for(IChapter c : chapters){
					if( c.getCode() == chapter.getCode() ) found = true;
				}
				if( !found ) s.addChapter( chapter );
			}
		}
	}
	
}
