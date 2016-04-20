package com.sz.db.models;

public class Place extends Entity{
	private int idScoution;
	private String lac = "";
	private String cid = "";
	private String place = "";
	private boolean operatorVodafone;
	private boolean operatorKyivstar;
	private boolean operatorLifecell;
	
	public Place() {
		// TODO Auto-generated constructor stub
	}
	
	public Place(String place, String lac, String cid, boolean vodafone, boolean kyivstar, boolean lifecell){
		setPlace(place);
		setLac(lac);
		setCid(cid);
		setOperatorVodafone(vodafone);
		setOperatorKyivstar(kyivstar);
		setOperatorLifecell(lifecell);
	}
	
	public String getOperator(){
		if(operatorVodafone){
			return "Vodafone";
		} else if(operatorKyivstar){
			return "Київстар";
		} else if(operatorLifecell){
			return "Lifecell";
		}
		return "";
	}
	
	public boolean isEmpty(){
		return idScoution == 0
				|| lac == null
				|| lac.isEmpty()
				|| cid == null
				|| cid.isEmpty()
				|| place == null
				|| place.isEmpty();
	}
	
	public int getIdScoution() {
		return idScoution;
	}
	public void setIdScoution(int idScoution) {
		this.idScoution = idScoution;
	}
	public String getLac() {
		return lac;
	}
	public void setLac(String lac) {
		this.lac = lac;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public boolean isOperatorVodafone() {
		return operatorVodafone;
	}

	public void setOperatorVodafone(boolean operatorVodafone) {
		this.operatorVodafone = operatorVodafone;
	}

	public boolean isOperatorKyivstar() {
		return operatorKyivstar;
	}

	public void setOperatorKyivstar(boolean operatorKyivstar) {
		this.operatorKyivstar = operatorKyivstar;
	}

	public boolean isOperatorLifecell() {
		return operatorLifecell;
	}

	public void setOperatorLifecell(boolean operatorLifecell) {
		this.operatorLifecell = operatorLifecell;
	}

	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
}
