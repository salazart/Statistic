package com.sz.db.models;

import java.sql.Date;

public class Traffic extends Entity{
	private Date dateAdd;
	private int idDepartament;
	private int idCrime;
	
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

}
