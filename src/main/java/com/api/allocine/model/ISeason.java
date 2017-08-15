package com.api.allocine.model;

import java.util.Collection;
import java.util.List;

public interface ISeason {

	public int getCode();
	public void setCode( int code );
	
	public String getLink();
	public IStats getStats();
	
	public Collection<IChapter> getChapters();
	public void setChapters( List<IChapter> chapters );
	public void addChapter( IChapter chapter );
	
	public int getEpisodeCount();
	public void setEpisodeCount(int episodeCount);
	public int getSeasonNumber();
	public void setSeasonNumber(int seasonNumber);
	
}
