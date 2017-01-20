package com.api.allocine.decod.impl;

import java.lang.reflect.Type;

import com.api.allocine.factory.IFactory;
import com.api.allocine.model.IAllocineObject;
import com.api.allocine.model.IFeed;
import com.api.allocine.model.ISearchResponse;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class SearchResponseDecode implements JsonDeserializer<ISearchResponse<IAllocineObject>>{

	private IFactory factory;
	
	public SearchResponseDecode( IFactory factory ){
		this.factory = factory;
	}
	
	@Override
	public ISearchResponse<IAllocineObject> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		ISearchResponse<IAllocineObject> response = factory.createSearchResponse();
		
		JsonObject object = json.getAsJsonObject();
		JsonElement element = object.get("feed");
		
		if( element != null ){
			IFeed<IAllocineObject> feed = context.deserialize( element , IFeed.class );
			if( feed != null ) response.setFeed(feed);
		}
		
		return response;
	}

}
