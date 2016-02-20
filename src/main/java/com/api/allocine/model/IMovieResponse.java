package com.api.allocine.model;

public interface IMovieResponse extends IJsonResponse {

	public IMovie getMovie();
	public void setMovie( IMovie movie );
	
}
