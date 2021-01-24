package com.companyname.automation.commontools;
import java.text.Format;
import java.text.SimpleDateFormat;


/**
 * The Event class allows you to hold information pertaining to any given event
 * String Event - the name of the event
 * String StartDateTime - When the event started in YYYYMMDDHHMMSSmm format
 * String EndDateTime - When the event ended in YYYYMMDDHHMMSSmm format
 * long Start - the current system time in milliseconds when the event started
 * long End - the current system time in milliseconds when the event ended 
 * double MaxTime - the maximum time, in seconds, the event should take
 * double ActualTime - the actual time , in seconds, the event took
 * 
 * @author Timothy.Romero
 *
 */
public class Event {
	
	NonObjectMethods nom99 = new NonObjectMethods();
	private String sEvent;
	private String sStartDateTime;
	private String sEndDateTime="";
	private long lStart;
	private long lEnd=0;
	private double dMaxTime;
	private double dActualTime;
	/**
	 *  Constructor - Instantiates an event object and sets Start and StartDateTime to current datetime 
	 * @param sEvent - a String the name of the Event
	 * @param dMaxTime - a double the maximum time, in seconds, the event should take
	 */
	public Event(String sEvent,double dMaxTime) {
		super();
		this.sEvent = sEvent;
		this.dMaxTime = dMaxTime;
//		sStartDateTime = nom99.ReplaceHardTags("[~Dateyyyymmddhhmmssmm~]");
		lStart = System.currentTimeMillis();
		sStartDateTime = this.DateTime(lStart);
		System.err.println("Start: " + sStartDateTime);
	}
	
	/**
	 * Starts the Event Timer and sets Start and StartDateTime to current datetime 
	 */
	public void Start()
	{
		lStart = System.currentTimeMillis();
		sStartDateTime = this.DateTime(lStart);
		System.err.println("Start: " + sStartDateTime);
		
	}
	
	/**
	 * Stops the Event Timer and sets End and EndDateTime to current datetime 
	 */

	public void Stop()
	{
		lEnd=System.currentTimeMillis() ;

		sEndDateTime=this.DateTime(lEnd);
		System.err.println("End: " + sEndDateTime);
	}
	/**
	 *  Get the name of the Event
	 * @return - A String that is the name of the event
	 */
	public String getsEvent() {
		return sEvent;
	}
	
	/**
	 * Sets the name of the Event
	 * @param sEvent - a string that is the name of the event
	 */
	public void setsEvent(String sEvent) {
		this.sEvent = sEvent;
	}

	/**
	 *  Get the event StartDateTime in YYYYMMDDHHMMSSmm fornat
	 * @return - A String that is the StartDateTime in YYYYMMDDHHMMSSmm fornat
	 */
	public String getsStartDateTime() {
		return sStartDateTime;
	}
	
	
	/**
	 *  Get the event EndDateTime in YYYYMMDDHHMMSSmm fornat
	 *  if Event has not been stopped it will be
	 * @return - A String that is the EndDateTime in YYYYMMDDHHMMSSmm fornat
	 */
	public String getsEndDateTime() {
		if(lEnd==0)
		{
			lEnd=System.currentTimeMillis() ;
		}
		if(sEndDateTime.length()==0)
		{
//			sEndDateTime=nom99.ReplaceHardTags("[~Dateyyyymmddhhmmssmm~]");
			sEndDateTime=this.DateTime(lEnd);
		}

		return sEndDateTime;
	}
	
	/**
	 *  Get the event Start Time in milliseconds
	 * @return - A long that is the StartTime in milliseconds
	 */

	public long getlStart() {
		return lStart;
	}
	
	/**
	 *  Get the maximum time in seconds the event should take
	 * @return - A double that is the maximum time in seconds the event should take
	 */
	public double getdMaxTime() {
		return dMaxTime;
	}
	
	
	public void setdMaxTime(double dMaxTime) {
		this.dMaxTime = dMaxTime;
	}

	/**
	 *  Get the actual time in seconds the event took
	 *  if Event has not been stopped it will be
	 * @return - A double that is the actual time in seconds the event took
	 */
	
	public double getdActualTime() {
		if(lEnd==0)
		{
			lEnd=System.currentTimeMillis() ;
		}
		if(sEndDateTime.length()==0)
		{
//			sEndDateTime=nom99.ReplaceHardTags("[~Dateyyyymmddhhmmssmm~]");
			sEndDateTime=this.DateTime(lEnd);
		}
		System.out.println("Start at: " + lStart + " End at: " + lEnd);
		return ((lEnd- lStart)/1000.0000);
	}
	
	/**
	 *  Get the actual time in seconds the event has taken thus far
	 * @return - A double that is the actual time in seconds the event has taken thus far
	 */
	
	public double getdElapsedTime() {
		return (System.currentTimeMillis()- lStart)/1000.0000;
	}

	private String DateTime(long lDt)
	{
		Format sdfYYYYMMDDHHMMSSMM;

		String sdf3a = "yyyyMMddHHmmssSSS";
		sdfYYYYMMDDHHMMSSMM = new SimpleDateFormat(sdf3a);
		return sdfYYYYMMDDHHMMSSMM.format(lDt);
	}
}
