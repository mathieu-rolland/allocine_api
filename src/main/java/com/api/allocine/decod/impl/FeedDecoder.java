package com.api.allocine.decod.impl;

import java.lang.reflect.Type;
import java.util.Collection;

import com.api.allocine.factory.IFactory;
import com.api.allocine.model.IAllocineLink;
import com.api.allocine.model.IFeed;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IResult;
import com.api.allocine.model.ISerie;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class FeedDecoder<IAllocineObject> implements JsonDeserializer<IFeed<IAllocineObject>>{

	private IFactory factory;
	
	public FeedDecoder(IFactory factory){
		this.factory = factory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public IFeed<IAllocineObject> deserialize(JsonElement json, Type type,
			JsonDeserializationContext context) throws JsonParseException {
		
		IFeed<IAllocineObject> feed = (IFeed<IAllocineObject>) factory.createFeed();
		
		JsonObject object = json.getAsJsonObject();
		JsonElement el;
		
		el = object.get("page");
		if( el != null ){
			feed.setPage( el.getAsInt() );
		}
		
		el = object.get("count");
		if( el != null ){
			feed.setCount( el.getAsInt() );
		}
		
		el = object.get("results");
		if( el != null ){
			Type collectionType = new TypeToken<Collection<IAllocineLink>>(){}.getType();
			Collection<IResult> results = context.deserialize( el , collectionType );
			
			if( results != null ){
				feed.setResults( results );
			}
		}
		
		el = object.get("tvseries");
		Type collectionTypeContent = null; 
		if( el != null ){
			collectionTypeContent = new TypeToken<Collection<ISerie>>(){}.getType();
			Collection<com.api.allocine.model.IAllocineObject> allocineContent = context.deserialize( el , collectionTypeContent );
			if( allocineContent != null ){
				feed.setApiAllocineObject( (Collection<IAllocineObject>) allocineContent );
			}
		}
		
		el = object.get("movie");
		if( el != null ){
			collectionTypeContent = new TypeToken<Collection<IMovie>>(){}.getType();
			Collection<com.api.allocine.model.IAllocineObject> allocineContent = context.deserialize( el , collectionTypeContent );
			if( allocineContent != null ){
				feed.setApiAllocineObject( (Collection<IAllocineObject>) allocineContent );
			}
		}
		
		return feed;
		
	}
		
}
