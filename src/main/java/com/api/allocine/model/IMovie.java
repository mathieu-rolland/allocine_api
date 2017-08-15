package com.api.allocine.model;

import java.util.Collection;

public interface IMovie extends IAllocineObject {

	void setTitle(String asString);
	public String getTitle();
	
	void setCode(int asInt);

	void setRelease(IRelease r);

	void setStatistiques(IStats s);

	void setCasting(ICasting c);

	void setPoster(IPoster p);

	void setLink(Collection<IAllocineLink> link);

	public int getCode();

	public void setSynospis(String asString);

	public int getDuration();
	
	public void setDuration(int duration);
	
	public void addLink( IAllocineLink link );
	
	public Collection<IAllocineLink> getLinks();
	
	public String getSynospis();

	public void setYear(int asInt);
	
	public Collection<IGenre> getGenre();
	public void setGenre(Collection<IGenre> genre);
	public IPoster getPoster();
	public ICasting getCasting();
}
