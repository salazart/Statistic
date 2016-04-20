package com.sz.docx.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sz.db.models.Crime;
import com.sz.db.models.Departament;
import com.sz.db.models.Place;
import com.sz.db.models.Scouting;

public class ReferenceData {
	private String section = "";
	private String rang = "";
	private String nameOfBoss = "";
	private String numberCrime = "";
	private String dateCrime = "";
	private String numberRaport = "";
	private String dateRaport = "";
	private String numberReference = "";
	private String dateReference = "";
	private String article = "";
	private List<Place> places = new ArrayList<Place>();
	
	public ReferenceData() {
	}
	
	public ReferenceData(Departament departament, Crime crime, Scouting scouting, List<Place> places) {
		setSection(departament.getNameForReference());
		setRang(departament.getRank());
		setNameOfBoss(departament.getBoss());
		setNumberCrime(crime.getNumber());
		setNumberReference(scouting.getNumberReference());
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		dateCrime = simpleDateFormat.format(crime.getRegistrationDate());
		
		setNumberRaport(scouting.getNumberRaport());
		
		dateRaport = simpleDateFormat.format(scouting.getDateRaport());
		
		if(scouting.getDateReference() != null){
			dateReference = simpleDateFormat.format(scouting.getDateReference());
		}
		
		String article = crime.getArticle();
		article = article.replace("-", " ч.");
		setArticle(article);
		
		setPlaces(places);
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getRang() {
		return rang;
	}

	public void setRang(String rang) {
		this.rang = rang;
	}

	public String getNameOfBoss() {
		return nameOfBoss;
	}

	public void setNameOfBoss(String nameOfBoss) {
		this.nameOfBoss = nameOfBoss;
	}

	public String getNumberCrime() {
		return numberCrime;
	}

	public void setNumberCrime(String numberCrime) {
		this.numberCrime = numberCrime;
	}

	public String getNumberRaport() {
		return numberRaport;
	}

	public void setNumberRaport(String numberRaport) {
		this.numberRaport = numberRaport;
	}

	public String getDateRaport() {
		return dateRaport;
	}

	public void setDateRaport(String dateRaport) {
		this.dateRaport = dateRaport;
	}

	public String getDateCrime() {
		return dateCrime;
	}

	public void setDateCrime(String dateCrime) {
		this.dateCrime = dateCrime;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public String getNumberReference() {
		return numberReference;
	}

	public void setNumberReference(String numberReference) {
		this.numberReference = numberReference;
	}

	public String getDateReference() {
		return dateReference;
	}

	public void setDateReference(String dateReference) {
		this.dateReference = dateReference;
	}
}
