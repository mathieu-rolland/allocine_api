package com.api.allocine.decod.impl;

import java.lang.reflect.Type;

import com.api.allocine.factory.IFactory;
import com.api.allocine.model.IAllocineLink;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class LinkDecoder implements JsonDeserializer<IAllocineLink>{ 

	private IFactory factory;
	
	public LinkDecoder( IFactory factory ) {
		this.factory = factory;
	}


	@Override
	public IAllocineLink deserialize(JsonElement json, Type type,
			JsonDeserializationContext context) throws JsonParseException {

		IAllocineLink link = factory.createLink();
		
		JsonObject obj = json.getAsJsonObject();
		
		JsonElement el = obj.get("rel");
		if( el != null ) link.setRel( el.getAsString() );
		
		el = obj.get("href");
		if( el != null ) link.setHref(el.getAsString());
		
		el = obj.get("name");
		if( el != null ) link.setName(el.getAsString());
		
		return link;
	}

}