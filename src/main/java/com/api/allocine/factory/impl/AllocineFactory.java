package com.api.allocine.factory.impl;

import java.lang.reflect.Type;

import com.api.allocine.IAllocineAPI;
import com.api.allocine.IAllocineAPI.RESPONSE_FORMAT;
import com.api.allocine.decod.impl.AllocineDecoder;
import com.api.allocine.factory.IFactory;
import com.api.allocine.impl.AllocineAPI;
import com.api.allocine.model.IAllocineLink;
import com.api.allocine.model.ICasting;
import com.api.allocine.model.IFeed;
import com.api.allocine.model.IJsonResponse;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IPoster;
import com.api.allocine.model.IRelease;
import com.api.allocine.model.IResult;
import com.api.allocine.model.IStats;
import com.api.allocine.model.impl.AllocineLink;
import com.api.allocine.model.impl.Casting;
import com.api.allocine.model.impl.Feed;
import com.api.allocine.model.impl.SearchResponse;
import com.api.allocine.model.impl.Movie;
import com.api.allocine.model.impl.Poster;
import com.api.allocine.model.impl.Release;
import com.api.allocine.model.impl.Result;
import com.api.allocine.model.impl.Stats;

public class AllocineFactory implements IFactory{

	@Override
	public IMovie createMovie() {
		return new Movie();
	}

	@Override
	public IPoster createPoster() {
		// TODO Auto-generated method stub
		return new Poster();
	}

	@Override
	public IAllocineLink createLink() {
		// TODO Auto-generated method stub
		return new AllocineLink();
	}

	@Override
	public IFeed createFeed() {
		// TODO Auto-generated method stub
		return new Feed();
	}

	@Override
	public IJsonResponse createJsonResponse() {
		// TODO Auto-generated method stub
		return new SearchResponse();
	}

	@Override
	public IRelease createRelease() {
		// TODO Auto-generated method stub
		return new Release();
	}

	@Override
	public IResult createResult() {
		// TODO Auto-generated method stub
		return new Result();
	}

	@Override
	public IStats createStats() {
		// TODO Auto-generated method stub
		return new Stats();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T create(Type type) {
		if( IMovie.class.equals(type) ) return (T) createMovie();
		if( IPoster.class.equals(type)) return (T) createPoster();
		if( IAllocineLink.class.equals(type)) return (T) createLink();
		if( ICasting.class.equals(type)) return (T) createCasting();
		if( IFeed.class.equals(type)) return (T) createFeed();
		if( IJsonResponse.class.equals(type)) return (T) createJsonResponse();
		if( IRelease.class.equals(type)) return (T) createRelease();
		if( IResult.class.equals(type) ) return (T) createResult();
		if( IStats.class.equals(type)) return (T) createStats();
		System.out.println("Class " + type + " not defined");
		return null;
	}

	public IAllocineAPI createSimpleAllocineAPI(){
		return new AllocineAPI( new AllocineDecoder(this) , RESPONSE_FORMAT.JSON );
	}
	
	@Override
	public ICasting createCasting() {
		// TODO Auto-generated method stub
		return new Casting();
	}

}
