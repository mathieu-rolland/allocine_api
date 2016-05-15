package com.api.allocine.model;

import java.util.Collection;

public interface IMovie {

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

	public String getSynospis();

	public void setYear(int asInt);
	
}
