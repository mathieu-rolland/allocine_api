package com.api.allocine.decod.impl;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api.allocine.factory.IFactory;
import com.api.allocine.model.IChapter;
import com.api.allocine.model.IPoster;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class ChapterDecoder implements JsonDeserializer<IChapter>{

	private IFactory factory;
	Logger logger = LoggerFactory.getLogger( IChapter.class );
	
	public ChapterDecoder(IFactory factory) {
		this.factory = factory;
	}

	@Override
	public IChapter deserialize(JsonElement json, Type type,
			JsonDeserializationContext context) throws JsonParseException {
		
		logger.debug("input {}" , json.toString());
		
		IChapter chapter = factory.createChapter();
		
		//TODO
		JsonObject obj = json.getAsJsonObject();
		
		JsonElement el = obj.get("code");
		if( el != null ){
			chapter.setCode( el.getAsInt() );
		}
		
		el = obj.get("originalTitle");
		if( el != null ){
			chapter.setTitle( el.getAsString() );
		}
		
		el = obj.get("synopsis");
		if( el != null ){
			chapter.setSynopsis( el.getAsString() );
		}
		
		el = obj.get("originalBroadcastDate");
		if( el != null ){
			chapter.setDate( el.getAsString() );
		}
		
		el = obj.get("picture");
		if( el != null ){
			IPoster poster = context.deserialize(el, IPoster.class);
			chapter.setPoster( poster );
		}
		
		el = obj.get("picture");
		if( el != null ){
			IPoster poster = context.deserialize(el, IPoster.class);
			chapter.setPoster( poster );
		}
		
		el = obj.get("parentSeason");
		if( el != null ){
			JsonObject objectSeason = el.getAsJsonObject();
			if( objectSeason != null ){
				el = objectSeason.get("code");
				if( el != null ) chapter.setParentSeason( el.getAsInt() );
			}
		}
		
		return chapter;
		
	}

}
