package com.api.allocine.decod.impl;

import java.lang.reflect.Type;

import com.api.allocine.factory.IFactory;
import com.api.allocine.model.IPoster;
import com.api.allocine.model.IStats;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class StatsDecoder implements JsonDeserializer<IStats>{

	private IFactory factory;

	public StatsDecoder(IFactory factory) {
		this.factory = factory;
	}

	@Override
	public IStats deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		IStats stats = factory.createStats();

		JsonObject obj = json.getAsJsonObject();

		JsonElement el = obj.get("pressRating");
		stats.setPressRating( el.getAsDouble() );


		el = obj.get("userRating");
		stats.setUserRating( el.getAsDouble() );
		
		return stats;
	}


}
