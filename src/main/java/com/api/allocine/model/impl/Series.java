package com.api.allocine.model.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.api.allocine.model.ICasting;
import com.api.allocine.model.IPoster;
import com.api.allocine.model.ISeason;
import com.api.allocine.model.ISerie;
import com.api.allocine.model.IStats;

public class Series implements ISerie {

	private int code;
	private int seasonCount;
	private IStats stats;
	private IPoster poster;
	private ICasting casting;
	private String title;
	private String description;
	private Collection<ISeason> seasons;
	private int year;
	private String productionStatus;
	
	public Series(){
		this.seasons = new ArrayList<ISeason>();
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
		
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
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

	public String getProductionStatus() {
		return productionStatus;
	}

	public void setProductionStatus(String productionStatus) {
		this.productionStatus = productionStatus;
	}

	public Collection<ISeason> getSeasons() {
		return seasons;
	}

	public void setSeasons(Collection<ISeason> seasons) {
		this.seasons = seasons;
	}

	@Override
	public String toString() {
		return "Series [code=" + code + ", seasonCount=" + seasonCount + ", stats=" + stats + ", poster=" + poster
				+ ", casting=" + casting + ", title=" + title + ", description=" + description + ", seasons=" + seasons
				+ ", year=" + year + ", productionStatus=" + productionStatus + "]";
	}
	
}
