package com.api.allocine.decod.impl;

import java.lang.reflect.Type;

import com.api.allocine.factory.IFactory;
import com.api.allocine.model.IPoster;
import com.api.allocine.model.IRelease;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ReleaseDecoder implements JsonDeserializer<IRelease>{

	private IFactory factory;
	
	public ReleaseDecoder( IFactory factory ){
		this.factory = factory;
	}
	
	@Override
	public IRelease deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		IRelease release = factory.createRelease();
		
		return release;
	}

}
