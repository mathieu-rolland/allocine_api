package com.api.allocine.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.api.allocine.model.IChapter;
import com.api.allocine.model.ISeason;
import com.api.allocine.model.IStats;

public class Season implements ISeason {

	private int code;
	private String link;
	private IStats stats;
	private Collection<IChapter> chapters;
	private int episodeCount;
	private int seasonNumber;
	
	public Season(){
		chapters = new ArrayList<IChapter>();
	}
	
	@Override
	public int getCode() {
		return code;
	}

	@Override
	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String getLink() {
		return link;
	}

	@Override
	public IStats getStats() {
		return stats;
	}

	@Override
	public Collection<IChapter> getChapters() {
		return chapters;
	}

	@Override
	public void setChapters(List<IChapter> chapters) {
		this.chapters = chapters;
	}

	@Override
	public void addChapter(IChapter chapter) {
		this.chapters.add(chapter);
	}

	public int getEpisodeCount() {
		return episodeCount;
	}

	public void setEpisodeCount(int episodeCount) {
		this.episodeCount = episodeCount;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setStats(IStats stats) {
		this.stats = stats;
	}

	public void setChapters(Collection<IChapter> chapters) {
		this.chapters = chapters;
	}

	@Override
	public String toString() {
		return "Season [code=" + code + ", link=" + link + ", stats=" + stats + ", chapters=" + chapters
				+ ", episodeCount=" + episodeCount + ", seasonNumber=" + seasonNumber + "]";
	}

}
