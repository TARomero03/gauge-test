package com.companyname.automation.cucumberhelpers;

import com.companyname.automation.commontools.ArrayListFldValues;

public class ScenarioObject {
	String sFeature;
	String sScenario;
	
	ArrayListFldValues alfvArguments;

	public ScenarioObject(String sFeature, String sScenario, ArrayListFldValues alfvArguments) {
		super();
		this.sFeature = sFeature;
		this.sScenario = sScenario;
		this.alfvArguments = alfvArguments;
		
		
	}

	public String getsFeature() {
		return sFeature;
	}

	public void setsFeature(String sFeature) {
		this.sFeature = sFeature;
	}

	public String getsScenario() {
		return sScenario;
	}

	public void setsScenario(String sScenario) {
		this.sScenario = sScenario;
	}

	public ArrayListFldValues getAlfvArguments() {
		return alfvArguments;
	}

	public void setAlfvArguments(ArrayListFldValues alfvArguments) {
		this.alfvArguments = alfvArguments;
	}
	
	

}
