package com.api.allocine.model;

import java.util.Collection;
import java.util.List;

public interface ISerie extends IAllocineObject {

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
	public int getYear();
	public void setCode(int asInt);
	public void setTitle(String asString);
	public void setYear(int asInt);
	public void setSeasonCount(int asInt);
	public String getProductionStatus();
	public void setProductionStatus(String productionStatus);
	public Collection<ISeason> getSeasons();
	public void setSeasons( Collection<ISeason> seasons );
	
}
