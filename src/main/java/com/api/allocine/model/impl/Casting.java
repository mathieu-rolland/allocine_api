package com.api.allocine.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.api.allocine.model.ICasting;

public class Casting implements ICasting {

	private List<String> actors;
	private List<String> directors;
	
	public Casting(){
		actors = new ArrayList<String>();
		directors = new ArrayList<String>();
	}

	public List<String> getActeurs() {
		return actors;
	}

	public void setActeurs(List<String> acteurs) {
		this.actors = acteurs;
	}

	public void addActor(String actor){
		actors.add( actor );
	}

	public void addDirector(String director){
		directors.add( director );
	}
	
	public List<String> getDirectors() {
		return directors;
	}

	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}

	@Override
	public String toString() {
		return "Casting [actors=" + actors + ", directors=" + directors + "]";
	}
	
}
