package com.companyname.automation.commontools;
/**
 * The FldValue class allows for the creation of Name/Value pairs
 * @author Timothy.Romero
 *
 */
public class FldValue {
	private String sName = "";
	private String sValue = "";

	/**
	 *  Constructor - Instantiates FldValue Object for the given Name/Value pair 
	 * @param sName - a String - the name of the Value Pair
	 * @param sValue = a String - the value of the pair 
	 */
	public FldValue(String sName, String sValue) {
		super();
		this.sName = sName;
		this.sValue = sValue;
	}

	
	/**
	 *  Constructor - Instantiates FldValue Object 
	 * @param sName - a String - the name of the Value Pair
	 * @param sValue = a String - the value of the pair 
	 */
	public FldValue() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * Get the name of the Name/Value pair
	 * @return a string that is the name of the Name/Value pair
	 */
	public String getSName() {
		return sName;
	}

	/**
	 * Set the name of the Name/Value pair
	 * @param name - a string that will be used to set the name of the Name/Value pair
	 */
	public void setSName(String name) {
		sName = name;
	}

	/**
	 * Get the value of the Name/Value pair
	 * @return a string that is the value of the Name/Value pair
	 */
	public String getSValue() {
		return sValue;
	}

	/**
	 * Set the value of the Name/Value pair
	 * @param name - a string that will be used to set the value of the Name/Value pair
	 */
	public void setSValue(String value) {
		sValue = value;
	}
}
