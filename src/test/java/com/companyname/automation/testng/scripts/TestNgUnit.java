package com.companyname.automation.testng.scripts;

import com.companyname.automation.commontools.*;
import org.testng.annotations.Test;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.TestNG;
import org.testng.TestRunner;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;



public class TestNgUnit {

    ITestContext TestContext;
    Globals gbl99 = new Globals();
    NonObjectMethods nom99 = new NonObjectMethods();
    HtmlResultsLogging hrl99 = new HtmlResultsLogging();
    HtmlEventsLogging  hel99 = new HtmlEventsLogging();
    Event ev12 = new Event("Total Test Time", 300);
    String sLoc = "";
    String sCurTest = "";
    String sTestNGLoc = "";
    Long lSuiteStartedTime = System.currentTimeMillis();

    String sLineOfStars = "********************************************************************";
    TestNG testNG;
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext ctx) {
        System.out.println("Start Before Suite");
        lSuiteStartedTime = System.currentTimeMillis();
        System.setProperty("org.uncommons.reportng.timeout", "6000");
        System.setProperty("org.uncommons.reportng.maxRetryCount", "2");
        System.setProperty("org.uncommons.reportng.logOutputReport", "true");
        System.setProperty("org.uncommons.reportng.title", "CompanyName");
        gbl99.setdDefaultWaitTime(10);
        gbl99.setAlReporterEntries(new ArrayList<String>());

        testNG = new TestNG();
        TestRunner runner = (TestRunner) ctx;
        testNG.setUseDefaultListeners(false);
        testNG.initializeEverything();
        System.out.println("TestNG out: " + testNG.getOutputDirectory());
 //       ArrayList<String> alRptogs = new ArrayList();
 //       gbl99.setAlReporterEntries(alRptogs);

        System.out.println("in beforeSuite");

        System.out.println("Current Dir: " + nom99.GetWorkingDirectory());

 //       File wf = new File(nom99.GetWorkingDirectory());

 //       File fFeatureTemplates = new File("src/test/resources/features");
 //       File fFeatureGened =new File(fFeatureTemplates.getAbsolutePath() + File.separator + "Gened");
 //       fFeatureGened.mkdirs();
 //       File faFt[] = fFeatureTemplates.listFiles();
 //       for(int i=0; i < faFt.length; i++)
 //       {
 //           System.out.println("File " + i + " name: " + faFt[i].getName());
 //           if(!faFt[i].isDirectory()) {
 //               LineReader lr = new LineReader(faFt[i].getAbsolutePath());
 //               LineWriter lw = new LineWriter(fFeatureGened.getAbsolutePath() + File.separator + faFt[i].getName());
 //               ArrayList<String> alFt = lr.ReadAllLines();
 //               for (int i4 = 0; i4 < alFt.size(); i4++) {
 //                   System.out.println(alFt.get(i4));
 //                   lw.WriteLine((alFt.get(i4)));
 //               }
 //               System.out.println("");
 //               System.err.println(" End File &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
 //               System.out.println();
 //           }
 //       }


        System.out.println("Exit Before Suite");
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
        System.out.println("Log Count: " + alLogs.size());

        for(int i88 = 0; i88 < alLogs.size(); i88++)
        {
            System.out.println(alLogs.get(i88));
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
            while(sT.indexOf("(+2)")>=0) sT = sT.replace("(+2)", "");
            sY = sY + sT;
            System.out.println("Reporter Output beforeZZ: " + lsOut.get(i8));
        }
        System.out.println("Pass/Fail: " + sY);
        sY = sY.replace("Total", "");
        sY = sY.replace("Failures", "Failed");
        sY = sY.replace("Skips", "Skipped");
        sAll = "<h3>" + sY + "</h3><BR>" + sAll;


        try {
            Reporter.log("Stuff");
            Reporter.clear();
            Reporter.log(sAll);
            System.out.println("Reporter loggged");
        } catch (Exception e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }


        Desktop d = Desktop.getDesktop();

        System.out.println("Test Dir: " + gbl99.getTestContext().getSuite().getOutputDirectory());
        System.out.println("Results Dir: " + gbl99.getHtmlResultsLog().getsResultsFldr());
        System.out.println("Events Dir: " + gbl99.getHtmleventslogging().getsResultsFldr());
        System.out.println("Current Dir: " + nom99.GetWorkingDirectory());

        String sFCurrent = System.getProperty("user.dir") + File.separator + "test-output";
        String sNewFTestNGLoc = nom99.GetWorkingDirectory().substring(0, nom99.GetWorkingDirectory().length()-2)  + File.separator + "test-output_" + gbl99.getTestContext().getSuite().getName() + "_" + nom99.ReplaceHardTags("[~Dateyyyymmddhhmmssmm~]");
        String sOldFTestNGLoc = nom99.GetWorkingDirectory().substring(0, nom99.GetWorkingDirectory().length()-2)  + File.separator + "test-output";
        String sFTestNGLoc =  nom99.GetWorkingDirectory().substring(0, nom99.GetWorkingDirectory().length()-2)  + File.separator + "test-output"+ File.separator + "html" + File.separator + "index.html";

        System.out.println("Working: " + nom99.GetWorkingDirectory());
        System.out.println("TestNG LOC: " + sFTestNGLoc);
        System.out.println("sOldFTestNGLoc: " + sOldFTestNGLoc);
        System.out.println(gbl99.getTestContext().getOutputDirectory());
        System.out.println("sNewFTestNGLoc: " + sNewFTestNGLoc);

        TestRunner runner = (TestRunner) gbl99.getTestContext();
        //    runner.setOutputDirectory("/Path/To/Store/Output");


        System.out.println("Output Dir: " + runner.getOutputDirectory());
//	try {
//		System.in.read();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}

        String sTestNGLoc = "file:///" + sFTestNGLoc;
        sTestNGLoc = sTestNGLoc.replace("\\", "/");
        sTestNGLoc = sTestNGLoc.replaceAll(" ", "%20");

        System.out.println("TestNGLoc: " + sTestNGLoc);
       File fNew = new File(sNewFTestNGLoc);
//        fNew.mkdirs();
//        File fOld = new File(sOldFTestNGLoc);
//        nom99.copyDirectory(fOld, fNew);

          	  com.companyname.automation.commontools.AutoDismiss ad77 = new com.companyname.automation.commontools.AutoDismiss();

  //        ad77.createGUI("Test Automation", "Waiting on folders to update: " +
  //        nom99.ReplaceHardTags("[~DateTimeMM/dd/yyyyHHmmssSSS~]") + " File Date: " +
  //        new Date(fOld.lastModified()));

  //        while(fOld.lastModified() <= lSuiteStartedTime) {
  //        ad77.setMessage("Waiting on folders to update: " +
  //        nom99.ReplaceHardTags("[~DateTimeMM/dd/yyyyHHmmssSSS~]") + " File Date: " +
  //        new Date(fOld.lastModified())); nom99.NapTime(0.250); }
//
  //        ad77.thatsAllFolks(); System.out.println("Waiting on Key press");
  //        System.out.println("old: " + fOld.getAbsolutePath());
  //        System.out.println("new: " + fNew.getAbsolutePath());



    	  

  boolean bTry = true;
    	  while(bTry)
    	  {

			try {
				d.browse(new URI(sTestNGLoc));
				bTry=false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nom99.NapTime(5);
    	  }	  
		//   	  nom99.copyDirectory(fOld, fNew);

        System.out.println("user home:  " + System.getProperty("user.home"));

        System.out.println("Exit After Suite");

    }

}
