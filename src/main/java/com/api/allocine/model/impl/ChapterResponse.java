package com.api.allocine.model.impl;

import com.api.allocine.model.IChapter;
import com.api.allocine.model.IChapterResponse;

public class ChapterResponse implements IChapterResponse {

	private IChapter episode;

	public IChapter getChapter() {
		return episode;
	}

	public void setChapter(IChapter chapter) {
		this.episode = chapter;
	}
	
}
