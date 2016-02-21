package com.api.allocine.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.api.allocine.model.IAllocineLink;
import com.api.allocine.model.ICasting;
import com.api.allocine.model.IGenre;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IPoster;
import com.api.allocine.model.IRelease;
import com.api.allocine.model.IStats;

public class Movie implements IMovie{

	private int code;
	private String originalTitle;
	private String title;
	private List<String> keywords;
	
	private int year;
	private IRelease release;
	private ICasting casting;
	private IStats statistiques;
	private IPoster poster;
	private Collection<IAllocineLink> links;
	private IGenre genre;
	private String synospis;
	private int duration;
	
	public Movie(){
		links = new ArrayList<IAllocineLink>();
		release = new Release();
//		casting = new Casting();
		statistiques = new Stats();
		poster = new Poster();
		setKeywords(new ArrayList<String>());
	}
	
	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public IRelease getRelease() {
		return release;
	}
	
	public void setRelease(IRelease r) {
		this.release = r;
	}
	
	public ICasting getCasting() {
		return casting;
	}
	
	public void setCasting(ICasting casting) {
		this.casting = casting;
	}
	
	public IStats getStatistiques() {
		return statistiques;
	}
	
	public void setStatistiques(IStats s) {
		this.statistiques = s;
	}
	
	public IPoster getPoster() {
		return poster;
	}
	
	public void setPoster(IPoster p) {
		this.poster = p;
	}
	
	public Collection<IAllocineLink> getLink() {
		return links;
	}
	
	public void setLink(Collection<IAllocineLink> link) {
		this.links = link;
	}
	
	public IGenre getGenre() {
		return genre;
	}

	public void setGenre(IGenre genre) {
		this.genre = genre;
	}

	public String getSynospis() {
		return synospis;
	}

	public void setSynospis(String synospis) {
		this.synospis = synospis;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void addLink( IAllocineLink link ){
		this.links.add(link);
	}

	@Override
	public String toString() {
		return "Movie [code=" + code + ", originalTitle=" + originalTitle
				+ ", title=" + title + ", keywords=" + keywords + ", year="
				+ year + ", release=" + release + ", casting=" + casting
				+ ", statistiques=" + statistiques + ", poster=" + poster
				+ ", links=" + links + ", genre=" + genre + ", synospis="
				+ synospis + ", duration=" + duration + "]";
	}
	
}
