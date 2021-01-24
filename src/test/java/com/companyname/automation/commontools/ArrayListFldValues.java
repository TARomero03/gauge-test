package com.companyname.automation.commontools;
import java.util.ArrayList;

/**
 * The ArrayListFldValues class allows for the creation of two arraylists of Name/Value pairs
 * @author Timothy.Romero
 *
 */

public class ArrayListFldValues {
	private ArrayList<String> alFlds;

	public ArrayListFldValues() {
	}

	private ArrayList<String> alValues;
	public ArrayListFldValues(ArrayList<String> alFlds, ArrayList<String> alValues) {
		super();
		this.alFlds = alFlds;
		this.alValues = alValues;
	}
	public ArrayList<String> getAlFlds() {
		return alFlds;
	}
	public void setAlFlds(ArrayList<String> alFlds) {
		this.alFlds = alFlds;
	}
	public ArrayList<String> getAlValues() {
		return alValues;
	}
	public void setAlValues(ArrayList<String> alValues) {
		this.alValues = alValues;
	}

	public String GetValue(String sType)
	{
		int idx = getAlFlds().indexOf(sType);
		return getAlValues().get(idx);
	}



}
