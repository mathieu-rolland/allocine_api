package com.api.allocine.model.impl;

import com.api.allocine.model.IMovie;
import com.api.allocine.model.IMovieResponse;

public class MovieResponse implements IMovieResponse {

	private IMovie movie;

	public IMovie getMovie() {
		return movie;
	}

	public void setMovie(IMovie movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "MovieResponse [movie=" + movie + ", getMovie()=" + getMovie() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
