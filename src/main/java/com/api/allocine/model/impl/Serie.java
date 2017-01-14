package com.api.allocine.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.api.allocine.model.ICasting;
import com.api.allocine.model.IChapter;
import com.api.allocine.model.IPoster;
import com.api.allocine.model.ISerie;
import com.api.allocine.model.IStats;

public class Serie implements ISerie {

	private int code;
	private int seasonCount;
	private IStats stats;
	private IPoster poster;
	private ICasting casting;
	private String title;
	private String description;
	private List<IChapter> chapters;
	private int year;
	
	public Serie(){
		this.chapters = new ArrayList<IChapter>();
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<IChapter> getChapters() {
		return chapters;
	}
	
	public void setChapters(List<IChapter> chapters) {
		this.chapters = chapters;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public void addChapter(IChapter chapter) {
		chapters.add(chapter);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public IStats getIStats() {
		return stats;
	}

	public void setIStats(IStats stats) {
		this.stats = stats;
	}

	public IPoster getPoster() {
		return poster;
	}

	public void setPoster(IPoster poster) {
		this.poster = poster;
	}

	public ICasting getCasting() {
		return casting;
	}

	public void setCasting(ICasting casting) {
		this.casting = casting;
	}

	@Override
	public int getSeasonCount() {
		return seasonCount;
	}
	
	public void setSeasonCount(int seasonCount) {
		this.seasonCount = seasonCount;
	}

	@Override
	public String toString() {
		return "Serie [code=" + code + ", seasonCount=" + seasonCount + ", stats=" + stats + ", poster=" + poster
				+ ", casting=" + casting + ", title=" + title + ", description=" + description + ", chapters="
				+ chapters + ", year=" + year + "]";
	}
	
}
