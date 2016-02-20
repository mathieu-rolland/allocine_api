package com.api.allocine.decod.impl;

import java.lang.reflect.Type;

import com.api.allocine.factory.IFactory;
import com.api.allocine.model.ICasting;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class CastingDecoder  implements JsonDeserializer<ICasting>{

	private IFactory factory;
	
	public CastingDecoder(IFactory factory){
		this.factory = factory;
	}
	
	@Override
	public ICasting deserialize(JsonElement json, Type type,
			JsonDeserializationContext context) throws JsonParseException {

		ICasting c = factory.createCasting();

		JsonObject obj = json.getAsJsonObject();
		JsonElement el = obj.get("directors");
		if( el != null ){
			String directors = el.getAsString();

			String[] splitedDirector = directors.split(",");
			for( String s : splitedDirector ){
				c.addDirector( s.trim() );
			}
		}
		
		el = obj.get("actors");
		if( el != null ){
			String actors = el.getAsString(); 
			String[] splittedActors = actors.split(",");

			for( String s : splittedActors ){
				c.addActor( s.trim() );
			}
		}
		return c;
	}

}
