package com.companyname.automation.commontools;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
//import java.text.DecimalFormat;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestRunner;
//import org.uncommons.reportng.*;
//import org.uncommons.reportng.messages.*;



public class HtmlEventsLogging<escape_output> {
	
	
	//AbstractReporter ar88;
	//HTMLReporter hr88 = new HTMLReporter();
	//JUnitXMLReporter jx88 = new JUnitXMLReporter();
	//ReportMetadata	rm88 = new ReportMetadata();
	private FileWriter fw99 = null;
	//ReportNGUtils ru99 = new ReportNGUtils();
	
	private PrintWriter pw99 = null;
	private int iPictureCnt = 0;
	private String sResultsFldr = null;
	private String sScriptName = null;
	
	RemoteWebDriver rLogDriver = null;
	NonObjectMethods nom99 = new NonObjectMethods();
	Globals gbl99 = new Globals();
	double TotUserTime=0.0000;
	public void LogStarter(String sScriptName, String sResultsFldr) {
		
        System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		String sScriptResultsLocation = sResultsFldr + File.separator + sScriptName + "_"
				+ nom99.ReplaceHardTags("[~UserID~]") + "_"
				+ nom99.ReplaceHardTags("[~Machine~]") + "_"
				+ nom99.ReplaceHardTags("[~Dateyyyymmddhhmmssmm~]");


		if(gbl99.getHtmlResultsLog() != null)
		{
			sScriptResultsLocation = gbl99.getHtmlResultsLog().getsResultsFldr();
		}
		else
		{
			if (!new File(sScriptResultsLocation).exists()) {

				new File(sScriptResultsLocation).mkdirs();
			}
		
		}
		this.sResultsFldr = sScriptResultsLocation;
		this.sScriptName = sScriptName;
		File fLogo = new File(gbl99.getsLogoLocation());
		if(fLogo.exists())
		{
		File fLogo2 = new File(this.getsResultsFldr() + File.separator + "Logo200.png");
		if(!fLogo2.exists())
		{
			nom99.copyDirectory(fLogo, fLogo2);
		}
		}
		String resultHtmlFileName = sScriptResultsLocation + File.separator + "Events.html";

		try {
			fw99 = new FileWriter(resultHtmlFileName);
			pw99 = new PrintWriter(fw99);
		} catch (Exception e88) {
			System.out.println("Problem Log Start");
		}

		NonObjectMethods nom99 = new NonObjectMethods();
		RemoteWebDriver rwe99 =  null;
				rwe99 = gbl99.getExtendedDriver().remoteDriver;
				rLogDriver = rwe99;
		String sBrowser = "";
		if(rwe99!=null)
		{
		Capabilities cp = rwe99.getCapabilities();
		sBrowser = "Name: " + cp.getBrowserName() + " Ver: "
				+ cp.getVersion() + " On: " + nom99.GetOS();
		

/*				+ cp.getVersion() + " On: " + cp.getPlatform() + " "
			+ cp.getPlatform().getMajorVersion() + "."
			+ cp.getPlatform().getMinorVersion();
*/
		// String sBrowser =(String)
		// ((JavascriptExecutor)gbl99.getDriver()).executeScript("navigator.appName;");
		// sBrowser = sBrowser + "." + (String)
		// ((JavascriptExecutor)gbl99.getDriver()).executeScript("navigator.appVersion;");
		}
		else
		{
			sBrowser = "";
		}

		System.out.println("1");
		TotUserTime=0.0000;
		String sQt = "" + (char) 34;
		// Start Results File
		try {
			pw99.println("<html xmlns=" + sQt + "http://www.w3.org/1999/xhtml"
					+ sQt + ">");
			pw99.println("<head>");
			pw99.println("<title> Events Logs</title>");
			pw99.println("<meta content=" + sQt + "text/html;charset=UTF-8"
					+ sQt + " http-equiv=" + sQt + "Content-Type" + sQt + "/>");
			pw99.println("<meta content=" + sQt + "en" + sQt + " http-equiv="
					+ sQt + "Content-Language" + sQt + "/>");
			pw99.println("<meta content=" + sQt + "public" + sQt + " name="
					+ sQt + "Security" + sQt + "/>");
			pw99.println("<meta content=" + sQt
					+ "Copyright (c) 2013 by " + gbl99.getsCompanyName() + sQt + " name=" + sQt
					+ "Copyright" + sQt + "/>");

			pw99.println("<body>");
			pw99.println("<div id=header>");
			if(gbl99.getsLogoLocation().length()>0)
			{
			pw99.println("<img id="
					+ sQt
					+ "printlogo"
					+ sQt
					+ " width="
					+ sQt
					+ "734"
					+ sQt
					+ " height="
					+ sQt
					+ "120"
					+ sQt
					+ " alt="
					+ sQt
					+ gbl99.getsCompanyName() + " Quality Assurance Verification and Validation - Test Results"
					+ sQt + " src=" + sQt + "file:Logo200.png" + sQt
					+ "/>");
			}
			else
			{
				pw99.println("<h1>" + gbl99.getsCompanyName() + "</h1");
				pw99.println("<BR>");
				
			}
			pw99.println("</div>");

			pw99.println("<div id=ScriptResultsheader>");
			pw99.println("<FONT FACE" + sQt + "Times New Roman" + sQt
					+ "Size=5> Script: " + sScriptName);
			pw99.println("<BR>");
			System.err.println("sBrowser: " + sBrowser);
			pw99.println("<FONT FACE" + sQt + "Times New Roman" + sQt
					+ "Size=4> Browser: " + sBrowser);
			pw99.println("<BR>");
			pw99.println("</div>");
			
			pw99.println("<div id=Events>");
			pw99.println("<table border=" + sQt + "2" + sQt	+   "style=" + sQt + "background-color:grey;" + sQt + ">");
			
			pw99.println("<tr>");
			pw99.println("<th>Event</th>");
			pw99.println("<th>Started</th>");
			pw99.println("<th>Ended</th>");
			pw99.println("<th>Time in Seconds</th>");
			pw99.println("<th>Maximum</th>");
		} catch (Exception e9) {
			System.out.println(e9.getMessage());
		}

	}

	public void AppendToExistingLog(String sScriptResultsLocation) {
		
        System.setProperty("org.uncommons.reportng.escape-output", "false");
		


		this.sResultsFldr = sScriptResultsLocation;
		String resultHtmlFileName = sScriptResultsLocation + File.separator + "Events.html";

		try {
			fw99 = new FileWriter(resultHtmlFileName,true);
			pw99 = new PrintWriter(new BufferedWriter(fw99));
		} catch (Exception e88) {
			System.out.println("Problem Log Start");
		}

		NonObjectMethods nom99 = new NonObjectMethods();
		RemoteWebDriver rwe99 =  null;
				rwe99 = gbl99.getExtendedDriver().remoteDriver;
				rLogDriver = rwe99;
		String sBrowser = "";
		if(rwe99!=null)
		{
		Capabilities cp = rwe99.getCapabilities();
		sBrowser = "Name: " + cp.getBrowserName() + " Ver: "
				+ cp.getVersion() + " On: " + nom99.GetOS();
		

/*				+ cp.getVersion() + " On: " + cp.getPlatform() + " "
			+ cp.getPlatform().getMajorVersion() + "."
			+ cp.getPlatform().getMinorVersion();
*/
		// String sBrowser =(String)
		// ((JavascriptExecutor)gbl99.getDriver()).executeScript("navigator.appName;");
		// sBrowser = sBrowser + "." + (String)
		// ((JavascriptExecutor)gbl99.getDriver()).executeScript("navigator.appVersion;");
		}
		else
		{
			sBrowser = "";
		}

		System.out.println("1");
		TotUserTime=0.0000;
		String sQt = "" + (char) 34;
		// Start Results File

	}


	/**
	 * 
	 */
	public HtmlEventsLogging() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void LogTestEvent(Event ev99) {
		TotUserTime = TotUserTime + ev99.getdActualTime();
		String sQt = "" + (char) 34;
		if(ev99.getdActualTime()>ev99.getdMaxTime())
		{
	        pw99.println("<tr bgcolor=" + sQt + "red" + sQt + ">");
		}
		else
		{
	        pw99.println("<tr bgcolor=" + sQt + "green" + sQt + ">");
		}
		pw99.println("<td><font color=" + sQt + "white" + sQt + ">"   + ev99.getsEvent() +  "</font></td>");
		String sTmp = String.valueOf(ev99.getsStartDateTime());
		sTmp = sTmp.substring(0, 4) + "-" + sTmp.substring(4, 6) + "-" + sTmp.substring(6, 8) + "_" + sTmp.substring(8, 10) + ":" + sTmp.substring(10, 12) +  ":" + sTmp.substring(12, 14) + "." + sTmp.substring(14, 17); 
		System.err.println("Started: " + sTmp);
		String sTmp2 = String.valueOf(ev99.getsEndDateTime());
		sTmp2 = sTmp2.substring(0, 4) + "-" + sTmp2.substring(4, 6) + "-" + sTmp2.substring(6, 8) + "_" + sTmp2.substring(8, 10) + ":" + sTmp2.substring(10, 12) +  ":" + sTmp2.substring(12, 14) + "." + sTmp2.substring(14, 17); 
		System.err.println("Started: " + sTmp);
		pw99.println("<td><font color=" + sQt + "white" + sQt + ">"   + sTmp +  "</font></td>");
		pw99.println("<td><font color=" + sQt + "white" + sQt + ">"   + sTmp2 +  "</font></td>");
		pw99.println("<td><font color=" + sQt + "white" + sQt + ">"   + String.valueOf(ev99.getdActualTime()) +  "</font></td>");
		pw99.println("<td><font color=" + sQt + "white" + sQt + ">"   + String.valueOf(ev99.getdMaxTime()) +  "</font></td>");
		pw99.println("</tr>");
		
		pw99.flush();
		try {
			fw99.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}

	}

	public void LogTestEvent(Event ev99, double dAdjust) {
		TotUserTime = TotUserTime + ev99.getdActualTime() - dAdjust;
		String sQt = "" + (char) 34;
		String sColor = "style=" + sQt + "background-color:green;" + sQt;
		if((ev99.getdActualTime() - dAdjust) >ev99.getdMaxTime())
		{
			sColor = "style=" + sQt + "background-color:red;" + sQt;
		}
		pw99.println("<tr " + sColor + ">");
		pw99.println("<td>" + ev99.getsEvent()  + "</td>");
		String sTmp = String.valueOf(ev99.getsStartDateTime());
		sTmp = sTmp.substring(0, 4) + "-" + sTmp.substring(4, 6) + "-" + sTmp.substring(6, 8) + "_" + sTmp.substring(8, 10) + ":" + sTmp.substring(10, 12) +  ":" + sTmp.substring(12, 14) + "." + sTmp.substring(14, 17); 
		System.err.println("Started: " + sTmp);
		String sTmp2 = String.valueOf(ev99.getsEndDateTime());
		sTmp2 = sTmp2.substring(0, 4) + "-" + sTmp2.substring(4, 6) + "-" + sTmp2.substring(6, 8) + "_" + sTmp2.substring(8, 10) + ":" + sTmp2.substring(10, 12) +  ":" + sTmp2.substring(12, 14) + "." + sTmp2.substring(14, 17); 
		System.err.println("Started: " + sTmp);
		pw99.println("<td>" + sTmp  + "</td>");
		pw99.println("<td>" + sTmp2  + "</td>");
		pw99.println("<td>" + String.valueOf(ev99.getdActualTime() - dAdjust)  + "</td>");
		pw99.println("<td>" + String.valueOf(ev99.getdMaxTime())  + "</td>");
		pw99.println("</tr>");
		
		pw99.flush();
		try {
			fw99.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}

	}

	
	public void LogStopper() {

		String sQt = "" + (char) 34;
		String sColor = "style=" + sQt + "background-color:lightgray;" + sQt;
		pw99.println("<tr " + sColor + ">");
		pw99.println("<td>Total User Experience</td>");
		DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.000" );
		double dd = TotUserTime;
		double dd2dec=0;
		try {
			dd2dec = Double.valueOf(df2.format(dd));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw99.println("<td></td>");
		pw99.println("<td></td>");
		pw99.println("<td>" + String.valueOf(dd2dec)  + "</td>");
		pw99.println("<td></td>");
		pw99.println("</tr>");


		pw99.println("</table>");
		pw99.println("</body>");
		pw99.println("</div>");
		pw99.println("</html>");
		pw99.flush();
		pw99.close();
		try {
			// fw99.flush();
			fw99.close();
		} catch (Exception e99) {
			System.out.println("A3: " + e99.getMessage());
		}
		
		String sBrowser = "";
		try {
			Capabilities cp = rLogDriver.getCapabilities();

			sBrowser = cp.getBrowserName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		try {
			String sRsColor = " COLOR=RED";
			
			int iLst1 = this.sResultsFldr.lastIndexOf(File.separator);
			System.err.println("ResultsVV: " + this.sResultsFldr);
			String sLstF = this.sResultsFldr.substring(0,iLst1);
			System.err.println("ResultsRF: " + sLstF);
					
			int iLst = sLstF.lastIndexOf(File.separator);
			sLstF = sLstF.substring(iLst,sLstF.length());
			
			String sRF = "..////" + sLstF + sResultsFldr.substring(iLst1);
			sRF = sRF.replace("\\", "/");
			System.err.println("Rel: " + sRF);
					
			String sLog = "<a href=" + sQt + "file:" + sRF
					+ "/Events.html" + sQt  + ">" + this.sScriptName + " Event Log";

				sLog = sLog + "<br></a>";

			
				gbl99.getAlReporterEntries().add(sLog);


		} catch (Exception e8) {

		}
		

	}

	public String getsResultsFldr() {
		return sResultsFldr;
	}


	
}
