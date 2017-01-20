package com.api.allocine.model;

import java.util.Collection;

public interface IFeed<T> {

	public void setPage(int page);
	public int getCount();
	public void setCount(int count);
	public Collection<IResult> getResults();
	public void setResults(Collection<IResult> results);
	public int getTotalResults();
	public void setTotalResults(int totalResults);
	public Collection<T> getApiAllocineObject();
	public void setApiAllocineObject(Collection<T> apiAllocineObject);
	
}
