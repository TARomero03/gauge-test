package com.companyname.automation.commontools;

/***
 * The booleanMessage class allows you simultaneously return from a method both a boolean and a message
 * @author Timothy.Romero
 *
 */
public class BooleanMessage {

	boolean bSuccess=false;
	String sMessage="";

	public BooleanMessage() {
	}

	/**
	 * Constructor
	 * @param bPass - a boolean used to signify the success of the method
	 * @param sMsg  - a String that can hold a message
	 */
	public BooleanMessage(boolean bPass, String sMsg) {
		bSuccess = bPass;
		sMessage = sMsg;
		
	}
	
	/**
	 *  Get the boolean value of the object
	 * @return a boolean that signifies the success or failure of the method
	 */
	public boolean isbSuccess() {
		return bSuccess;
	}
	
	/**
	 *  Set the boolean value of the object
	 * @param bSuccess a boolean to set the success or failure of the method
	 */
	public void setbSuccess(boolean bSuccess) {
		this.bSuccess = bSuccess;
	}
	
	
	/**
	 * Get the message being returned by the method
	 *  
	 * @return a String that holds the message being returned by the method
	 */
	public String getsMessage() {
		return sMessage;
	}
	
	/**
	 *  Set the message being returned by the method
	 * @param sMessage a String that sets the message being returned by the method
	 */
	public void setsMessage(String sMessage) {
		this.sMessage = sMessage;
	}

	
}
