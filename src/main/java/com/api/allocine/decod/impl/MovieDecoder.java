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

		el = obj.get("title");
		m.setTitle( el.getAsString() );

		el = obj.get("synopsis");
		if( el != null ) m.setSynospis( el.getAsString() );
		
		el = obj.get("runtime");
		if( el != null ) m.setDuration( el.getAsInt() );
		
		el = obj.get("release");
		if(el != null ){
			IRelease r = context.deserialize(el, IRelease.class);
			if( r != null ) m.setRelease( r );
		}

		el = obj.get("castingShort");
		if( el != null ){
			ICasting c = context.deserialize( el , ICasting.class);
			if( c != null ) m.setCasting(c);
		}

		el = obj.get("statistics");
		if( el != null ){
			IStats s   = context.deserialize( el , IStats.class);
			if( s != null ) m.setStatistiques(s);
		}

		el = obj.get("poster");
		if( el != null ){
			IPoster p  = context.deserialize( el , IPoster.class);
			if( p != null ) m.setPoster(p);
		}

		el = obj.get("link");
		if( el != null ){
			Type collectionType = new TypeToken<Collection<IAllocineLink>>(){}.getType();
			Collection<IAllocineLink> link = context.deserialize( el , collectionType	);
			if( link != null )  m.setLink(link);
		}
		
		el = obj.get("productionYear");
		if( el != null ){
			m.setYear( el.getAsInt() );
		}
		
		System.out.println( m );

		return m;
	}

}
