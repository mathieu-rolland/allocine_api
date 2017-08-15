package com.api.allocine.model.impl;

import com.api.allocine.model.ICasting;
import com.api.allocine.model.IChapter;
import com.api.allocine.model.IPoster;

public class Chapter implements IChapter {

	private int number;
	private String title;
	private String synopsis;
	private ICasting actors;
	private int code;
	private int parentSeasonCode;
	private IPoster poster;
	private String date;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public ICasting getActors() {
		return actors;
	}
	public void setActors(ICasting actors) {
		this.actors = actors;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	@Override
	public int getParentSeason() {
		return parentSeasonCode;
	}
	@Override
	public void setParentSeason(int code) {
		this.parentSeasonCode = code;
	}
	
	public int getParentSeasonCode() {
		return parentSeasonCode;
	}
	public void setParentSeasonCode(int parentSeasonCode) {
		this.parentSeasonCode = parentSeasonCode;
	}
	public IPoster getPoster() {
		return poster;
	}
	public void setPoster(IPoster poster) {
		this.poster = poster;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean equals(Chapter obj) {
		System.out.println("Compare chapter : " + obj.getCode() + " / " + this.getCode());
		return obj == null ? false : obj.getCode() == this.code;
	}
	
	/*public boolean equals( Object chapter ){
		return chapter == null ? false : this.code == chapter.getCode();
	}*/
	
	@Override
	public String toString() {
		return "Chapter [number=" + number + ", title=" + title + ", synopsis=" + synopsis + ", actors=" + actors
				+ ", code=" + code + ", parentSeasonCode=" + parentSeasonCode + ", poster=" + poster + ", date=" + date
				+ "]";
	}
	
}
