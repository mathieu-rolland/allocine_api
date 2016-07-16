package com.api.allocine.decod.impl;

import java.lang.reflect.Type;

import com.api.allocine.factory.IFactory;
import com.api.allocine.model.IGenre;
import com.api.allocine.model.IMovie;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class GenreDecoder implements JsonDeserializer<IGenre>{

	private IFactory factory;

	public GenreDecoder(IFactory factory) {
		this.factory = factory;
	}

	@Override
	public IGenre deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		IGenre genre = factory.createGenre();
		
		JsonObject obj = json.getAsJsonObject();
		
		JsonElement el = obj.get("code");
		if( el != null ) genre.setCode( el.getAsInt() );
		
		el = obj.get("$");
		if( el != null ) genre.setName( el.getAsString() );
		
		return genre;
	}

}
