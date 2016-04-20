package com.sz.db.models;

import java.sql.Date;

public class Scouting extends Entity{

	private int idDepartament;
	private int idCrime;
	private Date dateAdd;
	private String numberRaport;
	private Date dateRaport;
	private String numberReference;
	private Date dateReference;
	private int idEmploye;
	private String note;
	
	public int getIdDepartament() {
		return idDepartament;
	}
	public void setIdDepartament(int idDepartament) {
		this.idDepartament = idDepartament;
	}
	public int getIdCrime() {
		return idCrime;
	}
	public void setIdCrime(int idCrime) {
		this.idCrime = idCrime;
	}
	public Date getDateAdd() {
		return dateAdd;
	}
	public void setDateAdd(Date dateAdd) {
		this.dateAdd = dateAdd;
	}
	public String getNumberRaport() {
		return numberRaport;
	}
	public void setNumberRaport(String numberRaport) {
		this.numberRaport = numberRaport;
	}
	public Date getDateRaport() {
		return dateRaport;
	}
	public void setDateRaport(Date dateRaport) {
		this.dateRaport = dateRaport;
	}
	public String getNumberReference() {
		return numberReference;
	}
	public void setNumberReference(String numberReference) {
		this.numberReference = numberReference;
	}
	public Date getDateReference() {
		return dateReference;
	}
	public void setDateReference(Date dateReference) {
		this.dateReference = dateReference;
	}
	public int getIdEmploye() {
		return idEmploye;
	}
	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
