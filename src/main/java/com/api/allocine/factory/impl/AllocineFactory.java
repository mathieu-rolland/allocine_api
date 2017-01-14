package com.api.allocine.factory.impl;

import java.lang.reflect.Type;

import com.api.allocine.IAllocineAPI;
import com.api.allocine.IAllocineAPI.RESPONSE_FORMAT;
import com.api.allocine.decod.IDecoder;
import com.api.allocine.decod.impl.AllocineDecoder;
import com.api.allocine.factory.IFactory;
import com.api.allocine.impl.AllocineAPI;
import com.api.allocine.model.IAllocineLink;
import com.api.allocine.model.ICasting;
import com.api.allocine.model.IChapter;
import com.api.allocine.model.IFeed;
import com.api.allocine.model.IGenre;
import com.api.allocine.model.IJsonResponse;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IPoster;
import com.api.allocine.model.IRelease;
import com.api.allocine.model.IResult;
import com.api.allocine.model.ISearchResponse;
import com.api.allocine.model.ISerie;
import com.api.allocine.model.IStats;
import com.api.allocine.model.impl.AllocineLink;
import com.api.allocine.model.impl.Casting;
import com.api.allocine.model.impl.Chapter;
import com.api.allocine.model.impl.Feed;
import com.api.allocine.model.impl.Genre;
import com.api.allocine.model.impl.SearchResponse;
import com.api.allocine.model.impl.Serie;
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
		return new Poster();
	}

	@Override
	public IAllocineLink createLink() {
		return new AllocineLink();
	}

	@Override
	public IFeed createFeed() {
		return new Feed();
	}

	@Override
	public IJsonResponse createJsonResponse() {
		return new SearchResponse();
	}

	@Override
	public IRelease createRelease() {
		return new Release();
	}

	@Override
	public IResult createResult() {
		return new Result();
	}

	@Override
	public IStats createStats() {
		return new Stats();
	}
	
	@Override
	public ICasting createCasting() {
		return new Casting();
	}

	@Override
	public ISearchResponse createSearchResponse(){
		return new SearchResponse();
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
		if( ISearchResponse.class.equals(type)) return (T) createSearchResponse();
		if( IGenre.class.equals(type)) return (T) createGenre();
		if( ISerie.class.equals(type)) return (T) createSerie();
		if( IChapter.class.equals(type)) return (T) createChapter();
		return null;
	}

	public IChapter createChapter() {
		return new Chapter();
	}

	public ISerie createSerie() {
		return new Serie();
	}

	public IDecoder createAllocineDecoder(){
		return new AllocineDecoder(this);
	}
	
	public IAllocineAPI createSimpleAllocineAPI(){
		return new AllocineAPI( createAllocineDecoder() , RESPONSE_FORMAT.JSON );
	}

	@Override
	public IGenre createGenre() {
		return new Genre();
	}
	
}
