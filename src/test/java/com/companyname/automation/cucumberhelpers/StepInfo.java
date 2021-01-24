package com.companyname.automation.cucumberhelpers;

public class StepInfo {
	private String sStepType="";
	private String sStepDescription="";
	public StepInfo(String sStepType, String sStepDescription) {
		super();
		this.sStepType = sStepType;
		this.sStepDescription = sStepDescription;
	}
	public StepInfo() {
		super();
	}
	public String getsStepType() {
		return sStepType;
	}
	public void setsStepType(String sStepType) {
		this.sStepType = sStepType;
	}
	public String getsStepDescription() {
		return sStepDescription;
	}
	public void setsStepDescription(String sStepDescription) {
		this.sStepDescription = sStepDescription;
	}

	

}
