package com.api.allocine.decod.impl;

import java.lang.reflect.Type;

import com.api.allocine.factory.IFactory;
import com.api.allocine.model.ICasting;
import com.api.allocine.model.IPoster;
import com.api.allocine.model.ISerie;
import com.api.allocine.model.IStats;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class SerieDecoder implements JsonDeserializer<ISerie>{

	private IFactory factory;

	public SerieDecoder(IFactory factory) {
		this.factory = factory;
	}

	@Override
	public ISerie deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		
		ISerie serie = factory.createSerie();
		
		JsonObject obj = element.getAsJsonObject();
		
		JsonElement e = obj.get("code");
		serie.setCode( e.getAsInt() );
		
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
		
		return serie;
	}

	
	
}
