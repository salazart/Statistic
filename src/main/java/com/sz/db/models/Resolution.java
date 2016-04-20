package com.sz.db.models;

import java.sql.Date;

public class Resolution extends Entity{

	private Date dateAdd;
	private int idDepartament;
	private int idCrime;
	private Date dateResolution;
	private String numberResolution;
	private int idEmploye;
	private Date dateValid;
	private Date dateSubmission;
	private String note;

	public Date getDateAdd() {
		return dateAdd;
	}
	public void setDateAdd(Date dateAdd) {
		this.dateAdd = dateAdd;
	}
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
	public Date getDateResolution() {
		return dateResolution;
	}
	public void setDateResolution(Date dateResolution) {
		this.dateResolution = dateResolution;
	}
	public String getNumberResolution() {
		return numberResolution;
	}
	public void setNumberResolution(String numberResolution) {
		this.numberResolution = numberResolution;
	}
	public int getIdEmploye() {
		return idEmploye;
	}
	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}
	public Date getDateValid() {
		return dateValid;
	}
	public void setDateValid(Date dateValid) {
		this.dateValid = dateValid;
	}
	public Date getDateSubmission() {
		return dateSubmission;
	}
	public void setDateSubmission(Date dateSubmission) {
		this.dateSubmission = dateSubmission;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
