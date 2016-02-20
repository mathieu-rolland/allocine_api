package com.api.allocine.model.impl;

import com.api.allocine.model.IStats;

public class Stats implements IStats{

	private double userRating;
	private double pressRating;
	
	public double getUserRating() {
		return userRating;
	}
	public void setUserRating(double userRating) {
		this.userRating = userRating;
	}
	public double getPressRating() {
		return pressRating;
	}
	public void setPressRating(double pressRating) {
		this.pressRating = pressRating;
	}
	
	@Override
	public String toString() {
		return "Stats [userRating=" + userRating + ", pressRating="
				+ pressRating + "]";
	}
	
}
