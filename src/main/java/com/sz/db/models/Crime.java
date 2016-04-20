package com.sz.db.models;

import java.sql.Date;

public class Crime extends Entity{
	private static final int LENGHT_STORY = 950;
	private String type;
	private String number;
	private String story;
	private Date registrationDate;
	private Date crimeDate;
	private String article;
	
	public Crime() {
	}
	
	public Crime(int id, String type, String number, String story, Date registrationDate, Date crimeDate, String article){
		setId(id);
		setType(type);
		setNumber(number);
		setStory(story);
		setRegistrationDate(registrationDate);
		setCrimeDate(crimeDate);
		setArticle(article);
	}
	
	public String toString(){
		return getId() + "\t" 
				+ getType() + "\t"
				+ getNumber() + "\t"
				+ getRegistrationDate() + "\t"
				+ getCrimeDate() + "\t"
				+ getArticle() + "\t"
				+ getStory() + "\t";
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		if(story != null && story.length() > 1000){
			story = story.substring(0, LENGHT_STORY);
		}
		this.story = story;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Date getCrimeDate() {
		return crimeDate;
	}
	public void setCrimeDate(Date crimeDate) {
		this.crimeDate = crimeDate;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	
}
