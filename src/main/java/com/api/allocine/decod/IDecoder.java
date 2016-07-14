package com.api.allocine.decod;

import java.lang.reflect.Type;

import com.api.allocine.model.IJsonResponse;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;

public interface IDecoder {

	public IJsonResponse decodeSearchResponse(String json);
	public IJsonResponse decodeMovieResponse(String json);
	
	public void addTypeAdapter( Type deserializeClass ,  InstanceCreator<?> instanceCreator );
	public void addTypeAdapter( Type deserializeClass ,  JsonDeserializer<?> deserializer );
	public void addTypeAdapter( Type deserializeClass ,  JsonSerializer<?> deserializer );
	public void registerTypeAdapterFactory( TypeAdapterFactory factory );
	
	public Gson getGson();
	
}
