package com.api.allocine.model;

public interface IChapter {

	public int getNumber();
	public String getTitle();
	public String getSynopsis();
	public void setSynopsis(String synopsis);
	public ICasting getActors();
	public int getCode();
	public void setCode(int code);
	public int getParentSeason();
	public void setParentSeason(int code);
	public void setTitle(String asString);
	public IPoster getPoster();
	public void setPoster(IPoster poster);
	public String getDate();
	public void setDate(String date);
	
}
