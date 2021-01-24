package com.companyname.automation.commontools;
import java.util.ArrayList;
import java.util.Set;

import com.companyname.automation.cucumberhelpers.ScenarioObject;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.companyname.automation.webdriverextensions.ExtendedDriver;
import org.testng.ITestContext;


public class Globals {
	
	private static String sBrowser = "";
	
	
	public String getsBrowser() {
		return sBrowser;
	}


	public void setsBrowser(String sBrowser) {
		this.sBrowser = sBrowser;
	}

	private static String sResultsFolder = "";
	
	
	public String getsResultsFolder() {
		return sResultsFolder;
	}


	public void setsResultsFolder(String sResultsFolder) {
		this.sResultsFolder = sResultsFolder;
	}

	private static String sEventsLogFolder = "";
	
	
	public String getsEventsLogFolder() {
		return sEventsLogFolder;
	}


	public void setsEventsLogFolder(String sEventsLogFolder) {
		this.sEventsLogFolder = sEventsLogFolder;
	}

	private static double dDefaultWaitTime = 10;
	
	
	public double getdDefaultWaitTime() {
		return dDefaultWaitTime;
	}


	public void setdDefaultWaitTime(double dDefaultWaitTime) {
		this.dDefaultWaitTime = dDefaultWaitTime;
	}

	private static String sCompanyName;
	
	public String getsCompanyName() {
		return sCompanyName;
	}


	public void setsCompanyName(String sCompanyName) {
		
		this.sCompanyName = sCompanyName;
	}

	private static String sLogoLocation;
	
	
	public String getsLogoLocation() {
		return sLogoLocation;
	}


	public void setsLogoLocation(String sLogoLocation) {
		this.sLogoLocation = sLogoLocation;
	}

	private static String sLocation;
	
	public String getsLocation() {
		return sLocation;
	}


	public void setsLocation(String sLocation) {
		this.sLocation = sLocation;
	}



	
	private static ExtendedDriver extendedDriver;

	
	public ExtendedDriver getExtendedDriver() {
		return extendedDriver;
	}


	public void setExtendedDriver(ExtendedDriver extendedDriver) {
		this.extendedDriver = extendedDriver;
	}

	



	private static String sCurrentWindow;
	
	public String getsCurrentWindow() {
		return sCurrentWindow;
	}

	public void setsCurrentWindow(String sCurrentWindow) {
		this.sCurrentWindow = sCurrentWindow;
	}

	private static Set<String> stCurrentWindows;
	
	public Set<String> getStCurrentWindows() {
		return stCurrentWindows;
	}

	public void setStCurrentWindows(Set<String> stCurrentWindows) {
		this.stCurrentWindows = stCurrentWindows;
	}

	private static HtmlResultsLogging htmlresultslog;

	public HtmlResultsLogging getHtmlResultsLog() {
		return htmlresultslog;
	}

	public void setWebdriverlog(HtmlResultsLogging htmlresultslog) {
		this.htmlresultslog = htmlresultslog;
	}




	private static HtmlEventsLogging htmleventslogging;
	
	
	
	public HtmlEventsLogging getHtmleventslogging() {
		return htmleventslogging;
	}


	public void setHtmleventslogging(HtmlEventsLogging htmleventslogging) {
		this.htmleventslogging = htmleventslogging;
	}



	private static EventCSVLogging eventCSVlog;
	

	
	public EventCSVLogging getEventCSVlog() {
		return eventCSVlog;
	}

	public void setEventCSVlog(EventCSVLogging eventCSVlog) {
		this.eventCSVlog = eventCSVlog;
	}


	private static boolean bDetailLog = false;

	public boolean isbDetailLog() {
		return bDetailLog;
	}

	public void setbDetailLog(boolean bDetailLog) {
		this.bDetailLog = bDetailLog;
	}

	private static String sScreenCaptureLevel = "none";

	public String getsScreenCaptureLevel() {
		return sScreenCaptureLevel;
	}

	public void setsScreenCaptureLevel(String sScreenCaptureLevel) {
		this.sScreenCaptureLevel = sScreenCaptureLevel;
	}

	private static boolean bTestResult = true;

	public boolean isbTestResult() {
		return bTestResult;
	}

	public void setbTestResult(boolean bTestResult) {
		this.bTestResult = bTestResult;
		
		
	}
	
	private static ArrayList<Event> alEvents;

	public ArrayList<Event> getAlEvents() {
		return alEvents;
	}

	public void setAlEvents(ArrayList<Event> alEvents) {
		this.alEvents = alEvents;
	}

	private static ArrayList<String> alReporterEntries;


	public ArrayList<String> getAlReporterEntries() {
		return alReporterEntries;
	}


	public void setAlReporterEntries(ArrayList<String> alReporterEntries) {
		this.alReporterEntries = alReporterEntries;
	}

	private static ITestContext TestContext;

	public static ITestContext getTestContext() {
		return TestContext;
	}

	public void setTestContext(ITestContext ctx) {
		this.TestContext = ctx;
	}


	public ScenarioObject getScenarioObject() {
		return ScenarioObject;
	}

	public void setScenarioObject(ScenarioObject scenarioObject) {
		ScenarioObject = scenarioObject;
	}

	private static ScenarioObject ScenarioObject;





}
