package com.companyname.automation.testng.scripts.TestNGTestScripts;

import org.testng.annotations.Test;


import com.companyname.automation.commontools.Event;
import com.companyname.automation.commontools.Globals;
import com.companyname.automation.commontools.HtmlEventsLogging;
import com.companyname.automation.commontools.HtmlResultsLogging;
import com.companyname.automation.commontools.LineWriter;
import com.companyname.automation.commontools.NonObjectMethods;
import com.companyname.automation.commontools.StopWatch;
import com.companyname.automation.uiobjectmodels.JoinTheMovement;
import com.companyname.automation.uiobjectmodels.ProductCollectionPage;
import com.companyname.automation.uiobjectmodels.ProductCollectionPage.Item;
import com.companyname.automation.uiobjectmodels.SiteHeader;
import com.companyname.automation.webdriverextensions.ExtendedDriver;
import com.companyname.automation.webdriverextensions.ExtendedWebElement;
import com.google.common.reflect.Parameter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.net.*;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;



public class BuyAnItem {
	
	ITestContext TestContext;
	Globals gbl99 = new Globals();
	NonObjectMethods nom99 = new NonObjectMethods();
	HtmlResultsLogging hrl99 = new HtmlResultsLogging();
	HtmlEventsLogging  hel99 = new HtmlEventsLogging();
	Event ev12 = new Event("Total Test Time", 300);
	String sLoc = "";
	String sCurTest = "";
	String sTestNGLoc = "";
	
	String sLineOfStars = "********************************************************************";
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		

		
		
		System.setProperty("org.uncommons.reportng.timeout", "6000");
		System.setProperty("org.uncommons.reportng.maxRetryCount", "2");
		System.setProperty("org.uncommons.reportng.logOutputReport", "true");
		System.setProperty("org.uncommons.reportng.title", "CompanyName");
		gbl99.setdDefaultWaitTime(10);
		TestNG testNG = new TestNG();
		testNG.setUseDefaultListeners(false);
		testNG.initializeEverything();
		
		ArrayList<String> alRptogs = new ArrayList();
		gbl99.setAlReporterEntries(alRptogs);
		
	   System.out.println("in beforeSuite");
	   System.out.println("Current Dir: " + nom99.GetWorkingDirectory());

		String sFTestNGLocDirP = nom99.GetWorkingDirectory().substring(0, nom99.GetWorkingDirectory().length()-2)  + File.separator + "test-output";
		File fRptNG = new File(sFTestNGLocDirP);
		if(!fRptNG.exists()) fRptNG.mkdirs();
		fRptNG.setWritable(true, false);

	   
	   String sFTestNGLocDir = nom99.GetWorkingDirectory().substring(0, nom99.GetWorkingDirectory().length()-2)  + File.separator + "test-output"+ File.separator + "html";
		fRptNG = new File(sFTestNGLocDir);
		if(!fRptNG.exists()) fRptNG.mkdirs();
		fRptNG.setWritable(true, false);

	}
	
	@BeforeTest(groups = { "Start", "ALL" })
	@Parameters({
		"DetailLog",
		"ScreenCaptureLevel",
		"Browser"
		})
	public void beforeTest(
			@Optional("true") boolean bDetailLog,
			@Optional("All") String sScreenCaptureLevel,
			@Optional("chrome") String sBrowser)
	{
	
		 
		System.out.println("BD: " + System.getenv("BrowserDrivers"));
		ExtendedDriver ewd77= new ExtendedDriver(sBrowser);
		ewd77.MaxTheWindow();
		ewd77.remoteDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		gbl99.setExtendedDriver(ewd77);

		
	System.err.println("Start");

	gbl99.setsLogoLocation("");
	gbl99.setsCompanyName("4 Ocean");
		System.err.println("Start");
		gbl99.setbDetailLog(true);
		gbl99.setsScreenCaptureLevel(""); //fail none
		HtmlResultsLogging hrl99 = new HtmlResultsLogging();
		gbl99.setWebdriverlog(hrl99);
		HtmlEventsLogging hel99 = new HtmlEventsLogging();
		gbl99.setHtmleventslogging(hel99);
	}//end method beforeMethod

	
@Parameters({
	"ProductName",
	"OrderQuantity", "ResultsFolder",
	"EventLogFolder","Expected" })
@Test(description = "Tests Buying an Item without being Logged In", groups = { "grpA" })
public void BuyAnItemWithoutLoggedIn(
		@Optional("") String sProductName,
		@Optional("13") String sQty,
		@Optional("") String sResultFolder,
		@Optional("") String sEventLogFolder,
		@Optional("True") boolean bExpected,

		ITestContext ctx) {
	
	int iQty = Integer.parseInt(sQty);
	TestContext = ctx;
	System.out.println("Result folder: " + sResultFolder);
	System.out.println("Event folder: " + sEventLogFolder);
	String sFCurrent = System.getProperty("user.dir") + File.separator + "test-output";
	sTestNGLoc = "file:///"+sFCurrent + File.separator + sResultFolder + File.separator + "index.html";
	sTestNGLoc = sTestNGLoc.replace("\\", "/");
	sTestNGLoc = sTestNGLoc.replaceAll(" ", "%20");
	System.out.println("TestNGLoc: " + sTestNGLoc);
	
	hrl99.LogStarter( 
			ctx.getSuite().getName() + "-"
					+ ctx.getCurrentXmlTest().getName() + "-"
					+ new Throwable().getStackTrace()[0].getMethodName(),
					sFCurrent + File.separator + sResultFolder);
	
	
	File fRslt = new File(hrl99.getsResultsFldr());
	System.out.println("Html Log: " + fRslt.getAbsolutePath());
	gbl99.setWebdriverlog(hrl99);
	hrl99.LogTestMessage(gbl99.getExtendedDriver().remoteDriver.getCapabilities().toString());
	System.out.println("Host: " + ctx.getHost());
	hel99.LogStarter(ctx.getSuite().getName() + "-"
					+ ctx.getCurrentXmlTest().getName() + "-"
					+ new Throwable().getStackTrace()[0].getMethodName(),
					sFCurrent + File.separator + sResultFolder);
	gbl99.setHtmleventslogging(hel99);
	System.err.println("Log Started GGGGG");
	System.err.println("Log Started GGGGGAAAAAAAAAAAAAAAAAAAAAAA");
	gbl99.setbTestResult(true);
	try {
		gbl99.getExtendedDriver().remoteDriver.manage().window().maximize();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		//e1.printStackTrace();
	}
	Event ev1 = new Event("Open Four Ocean",1);
	ev1.Start();
	
	gbl99.getExtendedDriver().OpenURL("http://www.4ocean.com");
		ev1.Stop();
//	esv99.LogEvent(ev1);
	hrl99.LogTestMessageWithScreenCapture("4 Ocean took " + ev1.getdActualTime() + " seconds");
	hel99.LogTestEvent(ev1);
	try {
		JoinTheMovement jtmNow = new JoinTheMovement();
		jtmNow.CloseIt();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	/*
	 * ExtendedWebElement ewePop =
	 * gbl99.getExtendedDriver().GetExtendedWebElement(By.xpath(
	 * "//div[@id='attentive_overlay']"), 30); if(ewePop != null) {
	 * System.out.println("PopUp Found"); } else { System.out.println("No Popup"); }
	 * 
	 * ExtendedWebElement eweFrame =
	 * gbl99.getExtendedDriver().GetExtendedWebElement(By.xpath(
	 * "//iframe[@id='attentive_creative']"), 30); if(eweFrame != null) {
	 * System.out.println("eweFrame Found"); } else {
	 * System.out.println("No eweFrame"); }
	 * 
	 * 
	 * 
	 * // gbl99.getExtendedDriver().SwitchToFrame(0);
	 * gbl99.getExtendedDriver().remoteDriver.switchTo().defaultContent(); //
	 * gbl99.getExtendedDriver().remoteDriver.switchTo().frame(0);
	 * jtmNow.GetEweCloseImgBtn(15).Flash(5);
	 * gbl99.getExtendedDriver().remoteDriver.switchTo().defaultContent();
	 * jtmNow.GetEweCloseImgBtn(15).HighliteOn("dashed", "green");
	 * gbl99.getExtendedDriver().remoteDriver.switchTo().defaultContent();
	 * 
	 * jtmNow.GetEweCloseImgBtn(15).click();
	 * 
	 * // gbl99.getExtendedDriver().remoteDriver.switchTo().frame(0);
	 * 
	 * // jtmNow.CloseIt();
	 */	SiteHeader shOne = new SiteHeader();
	ProductCollectionPage pcpOne = new ProductCollectionPage();

	gbl99.getExtendedDriver().remoteDriver.switchTo().defaultContent();
	try {
		shOne.GetEweSiteHeaderBar().HighliteOn("dashed", "Orange");
		shOne.GetEweShopTextBtn().HighliteOn();
		shOne.GetEweAboutTextBtn().HighliteOn();
//		shOne.GetEweGetInvolvedTextBtn().HighliteOn();
		shOne.GetEweOurImpactTextBtn().HighliteOn();
		shOne.GetEwePoundPlusTextBtn().HighliteOn();
//		shOne.GetEweUpcyclingTextBtn().HighliteOn();
		shOne.GetEweJoinTheClubTextBtn().HighliteOn();
		gbl99.getHtmlResultsLog().LogTestMessageWithScreenCapture("Site Header Check");
		shOne.GetEweShopTextBtn().HighliteOff();
		shOne.GetEweAboutTextBtn().HighliteOff();
//		shOne.GetEweGetInvolvedTextBtn().HighliteOff();
		shOne.GetEweOurImpactTextBtn().HighliteOff();
		shOne.GetEwePoundPlusTextBtn().HighliteOff();
//		shOne.GetEweUpcyclingTextBtn().HighliteOff();
		shOne.GetEweJoinTheClubTextBtn().HighliteOff();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(shOne.StartShopping().isbSuccess())
	{
		String sMsg = "";
		pcpOne.GetEweProductList().HighliteOn();
		Item ib = pcpOne.GetItemByNameX(sProductName);
		if(ib != null)
		{
		sMsg = "Item: " + ib.getsItemName() + "\n"
				+ "HREF: " + ib.getsHREF()
				+ "\nBadge: " + ib.getsBadge()
				+ "\nPrice: " + ib.getsItemPrice();
		if(ib.getsOriginalPrice().trim().length() > 0)
		{
			sMsg = sMsg + "\nOriginally: " + ib.getsOriginalPrice();
		}
		gbl99.getHtmlResultsLog().LogTestPassWithScreenCapture(sMsg);
		Assert.assertTrue(true, sProductName + " FOUND");

		}
		else
		{
			gbl99.getHtmlResultsLog().LogTestFailure(sProductName + " NOT FOUND");
			gbl99.setbTestResult(false);
			Assert.assertTrue(false, sProductName + " NOT FOUND");
		}
		
	}
	else
	{
		hrl99.LogTestFailure("Unable to start shopping");
		gbl99.setbTestResult(false);
		Assert.assertTrue(false, "Unable to start shopping");

	}

}

@AfterTest(groups = { "Finish", "All" })
public void afterTest() {

	System.out.println("in After Test " + gbl99.isbDetailLog());
	
	ev12.Stop();
	// hel99.LogTestEvent(ev12);
	// el99.LogEvent(ev12);
	   System.out.println("Test Dir: " + TestContext.getSuite().getOutputDirectory());
	   System.out.println("Results Dir: " + gbl99.getHtmlResultsLog().getsResultsFldr());
	   System.out.println("Current Dir: " + nom99.GetWorkingDirectory());

	gbl99.getHtmleventslogging().LogStopper();
	gbl99.getHtmlResultsLog().LogStopper();

	try {
		gbl99.getExtendedDriver().ShutDown();
		gbl99.setExtendedDriver(null);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
	}
	System.err
			.println("************************************************************************************************");
	System.err
			.println("************************************************************************************************");
	System.err
			.println("************************************************************************************************");
	System.err
			.println("************************************************************************************************");
	/*
	 * Desktop d = Desktop.getDesktop(); try { String sLoc = "file:///"+
	 * hel99.getsResultsFldr() + File.separator + "Events.html"; sLoc =
	 * sLoc.replace("\\", "/"); sLoc = sLoc.replaceAll(" ", "%20");
	 * 
	 * d.browse(new URI(sLoc));
	 * 
	 * sLoc = "file:///"+ hrl99.getsResultsFldr() + File.separator + "Results.html";
	 * sLoc = sLoc.replace("\\", "/"); sLoc = sLoc.replaceAll(" ", "%20");
	 * 
	 * d.browse(new URI(sLoc)); } catch (IOException | URISyntaxException e2) {
	 * e2.printStackTrace(); }
	 */
	Reporter.log("Test Finished: " + TestContext.getName());
	Reporter.clear();
	System.out.println("user home:  " + System.getProperty("user.home"));

}

@AfterSuite(alwaysRun = true)
public void afterSuite() {
	System.out.println("in After Suite " + gbl99.isbDetailLog());

System.err
			.println("************************************************************************************************");
	System.err
			.println("************************************************************************************************");
	System.err
			.println("************************************************************************************************");
	System.err
			.println("************************************************************************************************");
	Reporter.setEscapeHtml(false);
	
	
//		Rpt.log(sLog);
	ArrayList<String> alLogs = gbl99.getAlReporterEntries();
	String sAll = "";
	for(int i88 = 0; i88 < alLogs.size(); i88++)
	{
		sAll = sAll + alLogs.get(i88);
	}
	List<String> lsOut = Reporter.getOutput();
	System.out.println("Reporter Output before: A" );
	
	for(int i8=0; i8<lsOut.size(); i8++)
	{
		System.out.println("Reporter Output before: " + lsOut.get(i8));
	}
	System.out.println("Reporter Output before: Z" );
	System.out.println("Reporter Output before: Z2 \n " + sAll );

	Reporter.clear();
	Reporter.log("");
	lsOut = Reporter.getOutput();
	System.out.println("Reporter Output before: A" );
	String sY="";
	for(int i8=0; i8<lsOut.size(); i8++)
	{
		String sT =  lsOut.get(i8);
		while(sT.indexOf("=")>=0) sT = sT.replace("=", "");
		while(sT.indexOf("(+0)")>=0) sT = sT.replace("(+0)", "");
		while(sT.indexOf("(+1)")>=0) sT = sT.replace("(+1)", "");
		sY = sY + sT;
		System.out.println("Reporter Output beforeZZ: " + lsOut.get(i8));
	}
	System.out.println("Pass/Fail: " + sY);
	sY = sY.replace("Total", "");
	sY = sY.replace("Failures", "Failed");
	sY = sY.replace("Skips", "Skipped");
	sAll = "<h3>" + sY + "</h3><BR>" + sAll;
	

	Reporter.clear();
	Reporter.log(sAll);

	lsOut = Reporter.getOutput();
	
	for(int i8=0; i8<lsOut.size(); i8++)
	{
		System.out.println("Reporter Output after: " + lsOut.get(i8));
	}

	Desktop d = Desktop.getDesktop();

	   System.out.println("Test Dir: " + TestContext.getSuite().getOutputDirectory());
	   System.out.println("Results Dir: " + gbl99.getHtmlResultsLog().getsResultsFldr());
	   System.out.println("Events Dir: " + gbl99.getHtmleventslogging().getsResultsFldr());
	   System.out.println("Current Dir: " + nom99.GetWorkingDirectory());

		String sFCurrent = System.getProperty("user.dir") + File.separator + "test-output";
		String sFTestNGLoc = nom99.GetWorkingDirectory().substring(0, nom99.GetWorkingDirectory().length()-2)  + File.separator + "test-output"+ File.separator + "html" + File.separator + "index.html";
		System.out.println("Working: " + nom99.GetWorkingDirectory());
		System.out.println("TestNG LOC: " + sFTestNGLoc);
		StopWatch sw66 = new StopWatch();
		sw66.start();
		File f = new File(sFTestNGLoc);
		while(sw66.getElapsedTimeSecs() < 30)
		{
			System.out.println(sFTestNGLoc + "Exists: " + f.exists());
			if(f.exists()) break;
		}
		System.out.println(sFTestNGLoc + "Exists: " + f.exists());
		
		long fSize =0;
		while(fSize < f.length())
		{ fSize = f.length();
			System.out.println("File length: " + f.length());
		}
		String sTestNGLoc = "file:///" + sFTestNGLoc;
		sTestNGLoc = sTestNGLoc.replace("\\", "/");
		sTestNGLoc = sTestNGLoc.replaceAll(" ", "%20");
		System.out.println("TestNGLoc: " + sTestNGLoc);


	       try {
			d.browse(new URI(sTestNGLoc));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
System.out.println("user home:  " + System.getProperty("user.home"));



}

}
