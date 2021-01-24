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

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestRunner;
//import org.uncommons.reportng.*;
//import org.uncommons.reportng.messages.;
public class HtmlResultsLogging<escape_output> {
	
	
	//AbstractReporter ar88;
	//HTMLReporter hr88 = new HTMLReporter();
	//JUnitXMLReporter jx88 = new JUnitXMLReporter();
	//ReportMetadata	rm88 = new ReportMetadata();
	NonObjectMethods nom99 = new NonObjectMethods();
	private FileWriter fw99 = null;
	//ReportNGUtils ru99 = new ReportNGUtils();
	
	private PrintWriter pw99 = null;
	private int iPictureCnt = Integer.parseInt((nom99.ReplaceHardTags("[~Dateyyyymmddhhmmssmm~]")).substring(8));
	private int iPage = 0;
	private String sResultsFldr = null;
	private String sScriptName = null;
	
	private RemoteWebDriver rLogDriver = null;

	Globals gbl99 = new Globals();

	public void LogStarter(String sScriptName, String sResultsFldr) {
		
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        System.setProperty("org.uncommons.reportng.title", gbl99.getsCompanyName() + " QA Tests");      
		
		String sScriptResultsLocation = sResultsFldr + File.separator + sScriptName + "_"
				+ nom99.ReplaceHardTags("[~UserID~]") + "_"
				+ nom99.ReplaceHardTags("[~Machine~]") + "_"
				+ nom99.ReplaceHardTags("[~Dateyyyymmddhhmmssmm~]");


		
		if (!new File(sScriptResultsLocation).exists()) {

			new File(sScriptResultsLocation).mkdirs();
		}

		this.sResultsFldr = sScriptResultsLocation;
		this.sScriptName = sScriptName;
		String resultHtmlFileName = sScriptResultsLocation + File.separator + "Results.html";
		System.out.println("Results File: " + resultHtmlFileName);
		try {
			fw99 = new FileWriter(resultHtmlFileName);
			pw99 = new PrintWriter(fw99);
		} catch (Exception e88) {
			System.out.println("Problem Log Start " + e88.getMessage());
		}

		if (!gbl99.isbDetailLog())
			return;
		iPage = 0;
		iPictureCnt =0;

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

		
		File fLogo = new File(gbl99.getsLogoLocation());
		if(fLogo.exists())
		{
		File fLogo2 = new File(this.getsResultsFldr() + File.separator + "Logo200.png");
		if(!fLogo2.exists())
		{
			nom99.copyDirectory(fLogo, fLogo2);
		}
		}
		String sQt = "" + (char) 34;
		// Start Results File
		try {
			pw99.println("<html xmlns=" + sQt + "http://www.w3.org/1999/xhtml"
					+ sQt + ">");
			pw99.println("<head>");
			pw99.println("<title> Verification and Validation - Test Results</title>");
			pw99.println("<meta content=" + sQt + "text/html;charset=UTF-8"
					+ sQt + " http-equiv=" + sQt + "Content-Type" + sQt + "/>");
			pw99.println("<meta content=" + sQt + "en" + sQt + " http-equiv="
					+ sQt + "Content-Language" + sQt + "/>");
			pw99.println("<meta content=" + sQt + "public" + sQt + " name="
					+ sQt + "Security" + sQt + "/>");
			pw99.println("<meta content=" + sQt
					+ "Copyright (c) 2013 by " + gbl99.getsCompanyName() + " " + sQt + " name=" + sQt
					+ "Copyright" + sQt + "/>");
			pw99.println("<script type=" + sQt + "text/javascript" + sQt + " >");
			pw99.println("function showhideFunction(iPic) {");
			pw99.println("var btnID = " + sQt + "ShowHide" + sQt + "+iPic;");
			pw99.println("var imgID = " + sQt + "ScreenCap" + sQt + "+iPic;");
			pw99.println("var btn = document.getElementById(btnID);");

			pw99.println("var pic = document.getElementById(imgID);");
			pw99.println("if (pic.style.display === " + sQt + "none" + sQt + ") {");
			pw99.println("pic.style.display = " + sQt + "block" + sQt + ";");
			pw99.println("btn.innerHTML = " + sQt + "Hide Image"  + sQt + ";");
			pw99.println("} else {");
			pw99.println("pic.style.display = " + sQt + "none" + sQt  + ";");
			pw99.println("btn.innerHTML = " + sQt + "Show Image" + sQt + ";");
			    pw99.println("}");
			    pw99.println("}");
///////////////////////////////////////////////////////////////////
				pw99.println("function showAllFunction(iPic) {");
				pw99.println("var btns = document.getElementsByTagName('button');");
				pw99.println("for (i = 0; i < btns.length;i++ ) {");
				pw99.println("var btnID = " + sQt + "ShowHide" + sQt + "+i");
				pw99.println("var imgID = " + sQt + "ScreenCap" + sQt + "+i;");
				pw99.println("var btn = document.getElementById(btnID);");
				pw99.println("var pic = document.getElementById(imgID);");
				pw99.println("pic.style.display = " + sQt + "block" + sQt + ";");
				pw99.println("btn.innerHTML = " + sQt + "Hide Image"  + sQt + ";");
	            pw99.println("}");		
				    pw99.println("}");
///////////////////////////////////////////////////////////////////
pw99.println("function hideAllFunction(iPic) {");
pw99.println("var btns = document.getElementsByTagName('button');");
pw99.println("for (i = 0; i < btns.length;i++ ) {");
pw99.println("var btnID = " + sQt + "ShowHide" + sQt + "+i");
pw99.println("var imgID = " + sQt + "ScreenCap" + sQt + "+i;");
pw99.println("var btn = document.getElementById(btnID);");
pw99.println("var pic = document.getElementById(imgID);");
pw99.println("pic.style.display = " + sQt + "none" + sQt + ";");
pw99.println("btn.innerHTML = " + sQt + "Show Image"  + sQt + ";");
pw99.println("}");		
pw99.println("}");



			pw99.println("</script>");
			pw99.println("</head>");


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
					+ "Size=5> Script: " + sScriptName + "  Run by: "
					+ nom99.ReplaceHardTags("[~UserID~]") + " on "
					+ nom99.ReplaceHardTags("[~Machine~]") + " at "
					+ nom99.ReplaceHardTags("[~Dateyyyymmddhhmmssmm~]"));
			pw99.println("<BR>");
			System.err.println("sBrowser: " + sBrowser);
			pw99.println("<FONT FACE" + sQt + "Times New Roman" + sQt
					+ "Size=3> Browser: " + sBrowser);
			pw99.println("<BR>");
			if(gbl99.getsScreenCaptureLevel().trim().compareToIgnoreCase("none")!=0)
			{
			String sShow = "javascript:showAllFunction()";
			pw99.println("<button id=" + sQt + "showAll" + sQt + " onclick=" + sQt + sShow + sQt + ">Show All Images</button>");
			String sHide = "javascript:hideAllFunction()";
			pw99.println("<button id=" + sQt + "hideAll" + sQt + " onclick=" + sQt + sHide + sQt + ">Hide All Images</button>");
			pw99.println("<BR>");
			}
			pw99.println("</div>");
		} catch (Exception e9) {
			System.out.println(e9.getMessage());
		}

	}

	public void AppendToExistingLog(String sScriptResultsLocation) {
		
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        System.setProperty("org.uncommons.reportng.title", gbl99.getsCompanyName() + " QA Tests");      
		

		

		this.sResultsFldr = sScriptResultsLocation;
		this.sScriptName = sScriptName;
		String resultHtmlFileName = sScriptResultsLocation + File.separator + "Results.html";
		

		
		if(iPage > 1)
		{
			resultHtmlFileName = sScriptResultsLocation + File.separator + "Results" + iPage + ".html";
		}
		System.out.println("append to : " + resultHtmlFileName);
		try {
			fw99 = new FileWriter(resultHtmlFileName,true);
			pw99 = new PrintWriter( new BufferedWriter(fw99));
		} catch (Exception e88) {
			System.out.println("Problem Log Start");
		}

		if (!gbl99.isbDetailLog())
			return;
		System.out.println("append to : " + resultHtmlFileName);

		//iPictureCnt = 0;
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

		
		File fLogo = new File(gbl99.getsLogoLocation());
		if(fLogo.exists())
		{
		File fLogo2 = new File(this.getsResultsFldr() + File.separator + "Logo200.png");
		if(!fLogo2.exists())
		{
			nom99.copyDirectory(fLogo, fLogo2);
		}
		}

	}

	/**
	 * 
	 */
	public HtmlResultsLogging() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public void LogBDDStep(String sStepType, String sMessage) {
		if (!gbl99.isbDetailLog())
			return;
		this.StartBDDStepLogic(sStepType,sMessage);
        
		pw99.println("</td>");
		pw99.println("</tr>");
 
		pw99.println("</table>");
		pw99.println("</div>");
	
		// System.out.println("in log test results stp4");

		pw99.flush();
		try {
			fw99.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
	}
	public void LogBDDStepMessageWithScreenCapture(String sStepType, String sMessage , String sMessage2) {
		if (!gbl99.isbDetailLog())
			return;
		StartBDDStepLogic(sStepType,sMessage);
		
			this.LogTestMessageWithScreenCapture(sMessage2);
		

       CloseBDDStep(); 
	}

	public void LogBDDStepMessage(String sStepType, String sMessage , String sMessage2) {
		if (!gbl99.isbDetailLog())
			return;
		StartBDDStepLogic(sStepType,sMessage);
		
			this.LogTestMessage(sMessage2);
		

       CloseBDDStep(); 
	}

	public void LogBDDStepFailureWithScreenCapture(String sStepType, String sMessage , String sMessage2) {
		if (!gbl99.isbDetailLog())
			return;
		StartBDDStepLogic(sStepType,sMessage);
		
			this.LogTestFailureWithScreenCapture(sMessage2);
		

       CloseBDDStep(); 
	}

	public void LogBDDStepFailure(String sStepType, String sMessage , String sMessage2) {
		if (!gbl99.isbDetailLog())
			return;
		StartBDDStepLogic(sStepType,sMessage);
		
			this.LogTestFailure(sMessage2);
		

       CloseBDDStep(); 
	}

	public void LogBDDStepPassWithScreenCapture(String sStepType, String sMessage , String sMessage2) {
		if (!gbl99.isbDetailLog())
			return;
		StartBDDStepLogic(sStepType,sMessage);
		
			this.LogTestPassWithScreenCapture(sMessage2);
		

       CloseBDDStep(); 
	}

	public void LogBDDStepPass(String sStepType, String sMessage , String sMessage2) {
		if (!gbl99.isbDetailLog())
			return;
		StartBDDStepLogic(sStepType,sMessage);
		
			this.LogTestPass(sMessage2);
		

       CloseBDDStep(); 
	}

	public void LogBDDStepWarningWithScreenCapture(String sStepType, String sMessage , String sMessage2) {
		if (!gbl99.isbDetailLog())
			return;
		StartBDDStepLogic(sStepType,sMessage);
		
			this.LogTestWarningWithScreenCapture(sMessage2);
		

       CloseBDDStep(); 
	}

	public void LogBDDStepTextFileLink(String sStepType, String sMessage , String sMessage2, String sFileName) {
		if (!gbl99.isbDetailLog())
			return;
		StartBDDStepLogic(sStepType,sMessage);
			this.LogTextFileLink(sMessage2, sFileName);
			this.LogTestWarning(sMessage2);
		

       CloseBDDStep(); 
	}

	public void LogBDDStepWarning(String sStepType, String sMessage , String sMessage2) {
		if (!gbl99.isbDetailLog())
			return;
		StartBDDStepLogic(sStepType,sMessage);
		
			this.LogTestWarning(sMessage2);
		

       CloseBDDStep(); 
	}

	public void LogBDDStepChangeWithScreenCapture(String sStepType, String sMessage , String sMessage2) {
		if (!gbl99.isbDetailLog())
			return;
		StartBDDStepLogic(sStepType,sMessage);
		
			this.LogTestChangeWithScreenCapture(sMessage2);
		

       CloseBDDStep(); 
	}

	public void LogBDDStepChange(String sStepType, String sMessage , String sMessage2) {
		if (!gbl99.isbDetailLog())
			return;
		StartBDDStepLogic(sStepType,sMessage);
		
			this.LogTestChange(sMessage2);
		

       CloseBDDStep(); 
	}


	public void LogTestResult(String sMessage, boolean bTakePicture,
			Object oPass) {
		if (!gbl99.isbDetailLog())
			return;
		
		
		boolean bPassX = true;
		// System.out.println("in log test results");

		String sQt = "" + (char) 34;
		int iX = iPictureCnt;
		//new
//		pw99.println("<style type=" + sQt + "text//css" + sQt + ">");
//		pw99.println("#Result" + (String.valueOf(iX)));
//		pw99.println("{");
//		pw99.println("border:2px solid red;");
//		pw99.println("outline:2px solid green;");
//		pw99.println("}");
//		pw99.println("</style type>");
		
		
		//old

		pw99.println("<div id=Result" + (String.valueOf(iX)) + "> ");
		sMessage = nom99.ReplaceHardTags("[~DateTimeMM/dd/yyyyHHmmssSSS~]") + "\n" + sMessage;
		pw99.println("<BR>");
		
		String sRws = "2";
		if(sMessage.length()>111) sRws="3";
		if(sMessage.length()>222) sRws="4";
		if(sMessage.length()>333) sRws="5";
		if(sMessage.length()>444) sRws="6";
		String sBorder= "style=" + sQt + "border:solid 2px BLACK;" + sQt;
		String sPass = "";
		sPass = oPass.toString();
		if (sPass.toLowerCase().indexOf("qry") >= 0)
		{
			sBorder= "style=" + sQt + "border:double 4px BLUE;" + sQt;
			
		}
		if (sPass.toLowerCase().indexOf("warn") >= 0)
		{
			sBorder= "style=" + sQt + "border:double 2px ORANGE;" + sQt;
			
		}
		if (sPass.compareToIgnoreCase("false") == 0)
		{
			sBorder= "style=" + sQt + "border:double 4px RED;" + sQt;
			
		}
		if (sPass.compareToIgnoreCase("true") == 0)
		{
			sBorder= "style=" + sQt + "border:double 3px GREEN;" + sQt;
			
		}
		String sOutLine = "<FONT FACE" + sQt + "Times New Roman" + sQt
				+ "Size=3> " + "<TEXTAREA " + sBorder + " COLS=110 ROWS=" + sRws + " READONLY=" + sQt + "readonly" + sQt + ">" + sMessage + "</TEXTAREA>";

		if (gbl99.getsScreenCaptureLevel().compareToIgnoreCase("none") == 0) {
			bTakePicture = false;
		}
		System.out.println("in log test results stp2");
		try {

			sPass = oPass.toString();
			if (sPass.toLowerCase().indexOf("warn") >= 0)
			{
				sOutLine = sOutLine + "<BR>  <FONT FACE=" + sQt
						+ "Times New Roman" + sQt
						+ " SIZE=3 COLOR=ORANGE> --- WARNING</FONT></a>";
				
			}

			if (sPass.toLowerCase().indexOf("qry") >= 0)
			{
				sOutLine = sOutLine + "<BR>  <FONT FACE=" + sQt
						+ "Times New Roman" + sQt
						+ " SIZE=3 COLOR=ORANGE> --- Something Changed</FONT></a>";
				
			}


			if ((sPass.compareToIgnoreCase("false") == 0)
					|| (sPass.compareToIgnoreCase("true") == 0)) {
				boolean bPass = Boolean.valueOf(sPass);

				if (bPass) {
					sOutLine = sOutLine + "<BR>  <FONT FACE=" + sQt
							+ "Times New Roman" + sQt
							+ " SIZE=3 COLOR=GREEN> --- PASSED</FONT></a>";
				} else {
					sOutLine = sOutLine + "<BR>  <FONT FACE=" + sQt
							+ "Times New Roman" + sQt
							+ " SIZE=3 COLOR=RED> --- FAILED</FONT></a>";

				}
			}
		} catch (Exception e99) {

		}

//		sOutLine = sOutLine + "</TEXTAREA>";
		if ((gbl99.getsScreenCaptureLevel().compareToIgnoreCase("fail") == 0)
				&& (sPass.compareToIgnoreCase("false") != 0)) {
			bTakePicture = false;
		}

		// System.out.println("in log test results stp3");

		if (bTakePicture) {
			String sPicID = "ScreenCap" + iX;
			String sShowHideID = "ShowHide" + iX;

			// System.out.println("in log test results stp3A " +
			// bal99.getSResultsFldr());
			String sFileName = sResultsFldr + File.separator +"SC"
					+ String.valueOf(iPictureCnt) + ".png";
			String sFileName2 = "SC"
					+ String.valueOf(iPictureCnt) + ".png";
			// System.out.println("in log test results stp3B  " + sFileName );
			try {
				
				new ScreenCapture().TakeTheShot(sFileName);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(sPass.trim().length()==0)
			{
			sOutLine = sOutLine + "<BR>";
			}
			pw99.println(sOutLine);
			String sVis = "  style=" + sQt + "display: block;" + sQt;
			String sShow = "javascript:showhideFunction(" + iX + ")";
			String sButtonText = "Hide Image";
					
			if (gbl99.getsScreenCaptureLevel().compareToIgnoreCase("hide") == 0)
				{
				 sVis = "  style=" + sQt + "display: none;" + sQt;
				sButtonText = "Show Image";
				}
			pw99.println("<button id=" + sQt + sShowHideID + sQt + " onclick=" + sQt + sShow + sQt + ">" + sButtonText + "</button>");
	//		pw99.println("<BR>");

			sOutLine = "<a href=" + sQt + "file:" + sFileName2 + sQt + ">";
			pw99.println(sOutLine);
			sOutLine = "<img id=" + sQt + sPicID + sQt + " width=" + sQt
					+ "900" + sQt + " height=" + sQt + "450" + sQt + " alt="
					+ sQt + "Results Screen Shot" + sQt + " src=" + sQt
					+ "file:" + sFileName2 + sQt + sVis + "/>";
			pw99.println(sOutLine);
			
			sOutLine = "</a>";
			sOutLine = sOutLine + "<BR>";
			pw99.println(sOutLine);
			iPictureCnt = iPictureCnt + 1;

		} else {
			sOutLine = sOutLine + "<BR>";
			pw99.println(sOutLine);
			
		}

		
		pw99.println("</div>");
		// System.out.println("in log test results stp4");

		pw99.flush();
		try {
			fw99.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
		String sF5A= "Results.html";
		String sF5 = this.getsResultsFldr()+ File.separator + "\\Results.html";
		if(iPage>1)
		{
			sF5 = this.getsResultsFldr() + File.separator + "Results" + iPage + ".html";
			sF5A = "Results" + iPage + ".html";
		}
		System.out.println("Current Results Page: " + sF5);
		File f7 = new File(sF5);
		if(f7.length() > 9000000)
		{

			iPage++;
			if(iPage < 2) iPage=2;
			File f8 = new File(this.getsResultsFldr()+"\\Results" + String.valueOf(iPage)+ ".html");
			this.LogTextFileLink("Next Page", "Results" + String.valueOf(iPage)+ ".html");
			try {
				this.Close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				String resultHtmlFileName = this.getsResultsFldr() + File.separator + "Results" + iPage + ".html";

			try {
				fw99 = new FileWriter(resultHtmlFileName);
				pw99 = new PrintWriter(fw99);
				System.out.println("New Log page Start: " + resultHtmlFileName);
				

				
				

			} catch (Exception e88) {
				System.out.println("Problem new Log page Start: " + resultHtmlFileName);
			}

			this.LogTextFileLink("Previous Page", sF5A);


			
		}
	}

	public void LogTestMessage(String sMessage) {
		this.LogTestResult(sMessage, false, "");
	}

	public void LogTestMessageWithScreenCapture(String sMessage) {
		this.LogTestResult(sMessage, true, "");
	}

	public void LogTestFailure(String sMessage) {
		this.LogTestResult(sMessage, false, false);
	}

	public void LogTestFailureWithScreenCapture(String sMessage) {
		this.LogTestResult(sMessage, true, false);
	}

	public void LogTestPass(String sMessage) {
		this.LogTestResult(sMessage, false, true);
	}

	public void LogTestPassWithScreenCapture(String sMessage) {
		this.LogTestResult(sMessage, true, true);
	}

	public void LogTestWarning(String sMessage) {
		this.LogTestResult(sMessage, false, "warn");
	}

	public void LogTestWarningWithScreenCapture(String sMessage) {
		this.LogTestResult(sMessage, true, "warn");
	}

	public void LogTestChange(String sMessage) {
		this.LogTestResult(sMessage, false, "qry");
	}

	public void LogTestChangeWithScreenCapture(String sMessage) {
		this.LogTestResult(sMessage, true, "qry");
	}

	public void LogAssert(String sMessage, boolean bTestResult) {
		if (bTestResult) {
			this.LogTestResult(sMessage, false, bTestResult);
		}
	}

	public void LogAssertWithScreenShot(String sMessage, boolean bTestResult) {
		if (bTestResult) {
			this.LogTestResult(sMessage, true, bTestResult);
		}
	}

	public void LogTextFileLink(String sMessage, String sFileName) {
		if (!gbl99.isbDetailLog())
			return;
		boolean bPassX = true;
		// System.out.println("in log test results");

		String sQt = "" + (char) 34;
		int iX = iPictureCnt;
		//new
		String sBorder= "style=" + sQt + "border:solid 2px BLACK;" + sQt;
		
		String sOutLine = "<FONT FACE" + sQt + "Times New Roman" + sQt
				+ "Size=4> ";
		//old

		sMessage = nom99.ReplaceHardTags("[~DateTimeMM/dd/yyyyHHmmssSSS~]") + "\n" + sMessage;
		
	
//		sOutLine = sOutLine + "</TEXTAREA>";

		// System.out.println("in log test results stp3");
			
		
			sOutLine = "<div id=Result77>" + "<FONT FACE" + sQt + "Times New Roman" + sQt
					+ "Size=4> " + "<a href=" + sQt + "file:" + sFileName + sQt + ">" + sMessage + "</a> </div>";
			pw99.println(sOutLine);
			sOutLine = "<BR>";
			sOutLine = sOutLine + "<BR>";
			pw99.println(sOutLine);
			
		

		// System.out.println("in log test results stp4");

		pw99.flush();
		try {
			fw99.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
	}

	public void LogStopper() {

		
		String sQt = "" + (char) 34;
		if (!gbl99.isbDetailLog())
			return;
		System.out.println("A3");
		pw99.println("</body>");

		pw99.println("</html>");

		pw99.flush();
		pw99.close();
		try {
			// fw99.flush();
			fw99.close();
		} catch (Exception e99) {
			System.out.println("A3: " + e99.getMessage());
		}

		String sBrowser="NONE";
		try {
			Capabilities cp = rLogDriver.getCapabilities();

			 sBrowser = cp.getBrowserName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
					+ "/Results.html" + sQt  + ">" + this.sScriptName + " Detailed Log";
			if (gbl99.isbTestResult()) {
				sLog = sLog + "  <FONT FACE=" + sQt
						+ "Times New Roman" + sQt
						+ " SIZE=3 COLOR=GREEN> --- PASSED</FONT> <br></a>";
			} else {
				sLog = sLog + "  <FONT FACE=" + sQt
						+ "Times New Roman" + sQt
						+ " SIZE=3 COLOR=RED> --- FAILED</FONT><br></a>";

			}

			
//			System.setProperty(arg0, arg1)
//			escape-output output;
				sLog = sLog + "<BR>";
				gbl99.getAlReporterEntries().add(sLog);
				System.out.println("we have " + gbl99.getAlReporterEntries().size() + " Reports");
				for(int i8=0; i8<gbl99.getAlReporterEntries().size(); i8++)
				  {
				System.out.println(gbl99.getAlReporterEntries().get(i8));
			}
				System.out.println("\n\n********************************************\n\n");
		} catch (Exception e8) {


		}


	}

	public String getsResultsFldr() {
		return sResultsFldr;
	}

	public int getiPictureCnt() {
		return iPictureCnt;
	}

	public void setiPictureCnt(int iPictureCnt) {
		this.iPictureCnt = iPictureCnt;
	}

	public void Close()
	{
		pw99.flush();
		pw99.close();
		try {
			fw99.flush();
			fw99.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void StartBDDStepLogic(String sStepType, String sMessage)
	{
		
		  String sQt = "" + (char) 34; String sColor = "MidnightBlue";
		  if(sStepType.trim().compareToIgnoreCase("Given")==0) sColor = "DarkSlateGray"; 
		  if(sStepType.trim().compareToIgnoreCase("When")==0) sColor = "DarkGreen";
		  if(sStepType.trim().compareToIgnoreCase("And")==0) sColor = "Indigo"; 
		  if(sStepType.trim().compareToIgnoreCase("Then")==0) sColor = "OrangeRed"; 

		  pw99.println("<BR>");
		  
		  pw99.println("<div id=bddStep>"); pw99.println("<table border=" + sQt + "0" +
		  sQt + "style=" + sQt + "background-color:" + sColor + ";" + sQt +
		  "COLS=110>");
		  
		  pw99.println("<tr>");
		  pw99.println("<th><font color=" + sQt + "white" +
		  sQt + ">" + sStepType + "</th>"); pw99.println("</tr>");
		  pw99.println("<tr bgcolor=" + sQt + "aqua" + sQt + ">");
		  pw99.println("<td><font color=" + sQt + "black" + sQt + ">" );
		  
		  pw99.println("<style type=" + sQt + "text//css" + sQt + ">");
		  pw99.println("{"); pw99.println("border:0px solid red;");
		  pw99.println("outline:0px solid green;"); pw99.println("}");
		  pw99.println("</style type>");
		  
		  
		  //old
		  
		  pw99.println("<div id=bddStep" + "> "); String sRws = "1"; String sBorder=
		  "style=" + sQt +
		  "padding-left:20px; background-color:aqua; border:solid 0px BLACK;" + sQt;
		  String sPass = ""; String sOutLine = "<FONT FACE" + sQt + "Times New Roman" +
		  sQt + "Size=3> " + "<TEXTAREA " + sBorder + " COLS=108 ROWS=" + sRws +
		  " READONLY=" + sQt + "readonly" + sQt + ">" + sMessage.trim() +
		  "</TEXTAREA>";
		  
		  
		  // System.out.println("in log test results stp3");
		  
		  sOutLine = sOutLine + "<BR>"; //sOutLine = sOutLine + "<BR>";
		  pw99.println(sOutLine);
	}
	
	private void CloseBDDStep()
	{
		pw99.println("</td>");
		pw99.println("</tr>");
 
		pw99.println("</table>");
			pw99.println("</div>");
	
		// System.out.println("in log test results stp4");

		pw99.flush();
		try {
			fw99.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}

	}
	
}
