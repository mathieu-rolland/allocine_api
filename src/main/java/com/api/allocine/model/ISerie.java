package com.api.allocine.model;

import java.util.List;

public interface ISerie {

	public int getCode();
	public ICasting getCasting();
	public void setCasting(ICasting casting);
	public String getTitle();
	public IPoster getPoster();
	public void setPoster( IPoster poster );
	public void setIStats(IStats stats);
	public IStats getIStats();
	public String getDescription();
	public int getSeasonCount();
	public List<IChapter> getChapters();
	public void addChapter(IChapter chapter);
	public int getYear();
	public void setCode(int asInt);
	public void setTitle(String asString);
	public void setYear(int asInt);
	public void setSeasonCount(int asInt);
	
}
