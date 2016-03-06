package com.api.allocine.decod.impl;

import java.lang.reflect.Type;

import com.api.allocine.factory.IFactory;
import com.api.allocine.model.IPoster;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class PosterDecoder implements JsonDeserializer<IPoster>{

	private IFactory factory;

	public PosterDecoder(IFactory factory) {
		this.factory = factory;
	}

	@Override
	public IPoster deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		IPoster poster = factory.createPoster();
		
		JsonObject obj = json.getAsJsonObject();

		JsonElement el = obj.get("href");
		poster.setHref( el.getAsString() );
		
		return poster;
	}

}
