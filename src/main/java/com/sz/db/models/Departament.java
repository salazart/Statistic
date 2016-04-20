package com.sz.db.models;

public class Departament extends Entity{

	private String name;
	private String nameForReference;
	private String rank;
	private String boss;
	
	public String getNameForReference() {
		return nameForReference;
	}

	public void setNameForReference(String nameForReference) {
		this.nameForReference = nameForReference;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getBoss() {
		return boss;
	}

	public void setBoss(String boss) {
		this.boss = boss;
	}

	public Departament() {
	}
	
	public Departament(int id, String name){
		setId(id);
		setName(name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
