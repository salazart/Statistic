package com.sz.db.models;

public class Target extends Entity{
	private int idResolution;
	private String target = "";
	private boolean operatorVodafone;
	private boolean operatorKyivstar;
	private boolean operatorLifecell;
	private boolean sendTarget;
	private int idTraffic;
	
	public int getIdTraffic() {
		return idTraffic;
	}

	public void setIdTraffic(int idTraffic) {
		this.idTraffic = idTraffic;
	}

	public boolean isEmpty(){
		return target == null
				|| target.isEmpty()
				|| isEmptyOperators();
	}
	
	private boolean isEmptyOperators(){
		return !operatorVodafone && !operatorKyivstar && !operatorLifecell;
	}
	
	public int getIdResolution() {
		return idResolution;
	}
	public void setIdResolution(int idResolution) {
		this.idResolution = idResolution;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
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

	public boolean isSendTarget() {
		return sendTarget;
	}
	public void setSendTarget(boolean sendTarget) {
		this.sendTarget = sendTarget;
	}
}
