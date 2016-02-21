package com.api.allocine.model;

import java.util.ArrayList;

public interface IFeed {

	public void setPage(int page);
	public int getCount();
	public void setCount(int count);
	public ArrayList<IResult> getResults();
	public void setResults(ArrayList<IResult> results);
	public int getTotalResults();
	public void setTotalResults(int totalResults);
	public ArrayList<IMovie> getMovies();
	public void setMovies(ArrayList<IMovie> movie);
	
}
