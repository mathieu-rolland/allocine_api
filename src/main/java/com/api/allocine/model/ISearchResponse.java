package com.api.allocine.model;

public interface ISearchResponse<T> extends IJsonResponse{

	public IFeed<T> getFeed();
	public void setFeed(IFeed<IAllocineObject> feed);
	
}
