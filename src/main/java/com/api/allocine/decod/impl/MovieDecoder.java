package com.api.allocine.decod.impl;

import java.lang.reflect.Type;
import java.util.Collection;

import com.api.allocine.factory.IFactory;
import com.api.allocine.model.IAllocineLink;
import com.api.allocine.model.ICasting;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IPoster;
import com.api.allocine.model.IRelease;
import com.api.allocine.model.IStats;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class MovieDecoder implements JsonDeserializer<IMovie>{

	private IFactory factory;
	
	public MovieDecoder(IFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public IMovie deserialize(JsonElement json, Type type,
			JsonDeserializationContext context) throws JsonParseException {

		IMovie m = factory.createMovie();
		
		JsonObject obj = json.getAsJsonObject();
		
		JsonElement el = obj.get("code");
		m.setCode( el.getAsInt() );
		
		el = obj.get("originalTitle");
		m.setTitle( el.getAsString() );
		
		IRelease r = context.deserialize(obj.get("release"), IRelease.class);
		ICasting c = context.deserialize( obj.get("castingShort") , ICasting.class);
		IStats s   = context.deserialize(obj.get("statistics"), IStats.class);
		IPoster p  = context.deserialize(obj.get("poster"), IPoster.class);
		Type collectionType = new TypeToken<Collection<IAllocineLink>>(){}.getType();
		Collection<IAllocineLink> link = context.deserialize(obj.get("link"), collectionType	);
		
		if( r != null ) m.setRelease( r );
		if( c != null ) m.setCasting(c);
		if( s != null ) m.setStatistiques(s);
		if( p != null ) m.setPoster(p);
		if( link != null )  m.setLink(link);
		
		System.out.println( m );
		
		return m;
	}

}
