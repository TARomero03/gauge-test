package com.companyname.automation.commontools;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * The EventCSVLogging class allows you to write events to a CSV file
 * @author Timothy.Romero
 *
 */
public class EventCSVLogging {
	private File flLogFile = null;
	private FileWriter fw99 = null;
	private PrintWriter pw99 = null;

	NonObjectMethods nom99 = new NonObjectMethods();
	Globals gbl99 = new Globals();
	
	/**
	 *  Constructor - Instantiates an EventCSVLogging object with the log in the folder specified by the sPath parameter
	 * @param sPath - A String that is the path of the Folder the log will be placed in
	 */
	public EventCSVLogging(String sPath,boolean bAppend) {
		super();
		// TODO Auto-generated constructor stub
		String sEventLogFile="";
			File flPath = new File(sPath);
			flPath.mkdirs();
			
			sEventLogFile = sPath + File.separator + "EventLog.csv";
			File flLog = new File(sEventLogFile);
			flLogFile = flLog;
			boolean bFileThere = flLog.exists();
			if(!bAppend) bFileThere = false;
		try {
			fw99 = new FileWriter(sEventLogFile,bAppend);
			pw99 = new PrintWriter(fw99);
			if(bFileThere)
			{
			pw99.println("Event,Start,End,Elapsed,Maximum");
			}
			
			
		} catch (Exception e88) {
			System.out.println("Problem Event Log Start");
		}

		
	}

	/**
	 *  Constructor - Instantiates an EventCSVLogging object with the log in the folder the same as the HtmlResultsLog
	 * @param sPath - A String that is the path of the Folder the log will be placed in
	 */

	public EventCSVLogging() {
		super();
		// TODO Auto-generated constructor stub
		String sEventLogFile="";
		if(gbl99.getHtmlResultsLog()==null)
		{
			System.err.println("Log is null");
		}
		if(gbl99.getHtmlResultsLog().getsResultsFldr().length()>0)
		{
			sEventLogFile = gbl99.getHtmlResultsLog().getsResultsFldr() + "\\EventLog.csv";
		}
		else
		{
			System.err.println("HTML Results Folder does not exist");
		}
		try {
			fw99 = new FileWriter(sEventLogFile);
			pw99 = new PrintWriter(fw99);
			pw99.println("Event,Start,End,Elapsed,Maximum");
		} catch (Exception e88) {
			System.out.println("Problem Event Log Start");
		}

		
	}

	/**
	 *  Record the Event specified by the evnt99 parameter to the EventCSVLog
	 * @param evnt99 - An Event to be written to the log
	 */
	public void LogEvent(Event evnt99)
	{
		String sLog = evnt99.getsEvent() + "," + evnt99.getsStartDateTime();
		sLog = sLog + "," + evnt99.getsEndDateTime() + "," + evnt99.getdActualTime();
		sLog = sLog + "," + evnt99.getdMaxTime();
		pw99.println(sLog);
		
	}
	
	/**
	 *  Record the Event specified by the evnt99 parameter to the EventCSVLog
	 * @param evnt99 - An Event to be written to the log
	 */
	public void LogEvent(Event evnt99,double dAdjustment)
	{
		double dAdustedTime = evnt99.getdActualTime() - dAdjustment;
		String sLog = evnt99.getsEvent() + "," + evnt99.getsStartDateTime();
		sLog = sLog + "," + evnt99.getsEndDateTime() + "," + String.valueOf(dAdustedTime);
		sLog = sLog + "," + evnt99.getdMaxTime();
		pw99.println(sLog);
		
	}
	/**
	 *  Close the Log
	 * @return a Boolean 
	 *  true - log Closed
	 *  false - log did not close
	 */
	public boolean CloseLog()
	{
		try
		{
			pw99.close();
			fw99.close();
			return true;
		}
		catch(Exception e8)
		{
			
			return false;
		}
	}

	/**
	 * @return the flLogFile
	 */
	public File getFlLogFile() {
		return flLogFile;
	}

}
