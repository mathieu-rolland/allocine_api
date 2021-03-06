package com.api.allocine.factory;

import java.lang.reflect.Type;

import com.api.allocine.IAllocineAPI;
import com.api.allocine.model.IAllocineLink;
import com.api.allocine.model.IAllocineObject;
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
import com.api.allocine.model.ISeason;
import com.api.allocine.model.ISerie;
import com.api.allocine.model.IStats;

public interface IFactory {

	public IMovie createMovie();
	public ICasting createCasting();
	public IPoster createPoster();
	public IAllocineLink createLink();
	public IJsonResponse createJsonResponse();
	public IRelease createRelease();
	public IResult createResult();
	public IStats createStats();
	public IGenre createGenre();
	public <T> T create(Type type);
	public IAllocineAPI createSimpleAllocineAPI();
	public ISearchResponse<IAllocineObject> createSearchResponse();
	public ISerie createSerie();
	public IChapter createChapter();
	public IFeed<IAllocineObject> createFeed();
	public ISeason createSeason();
}
