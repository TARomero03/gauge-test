package com.companyname.automation.cucumberhelpers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import com.companyname.automation.commontools.Globals;

public class HelpReportNG extends Reporter{
	Globals gbl99 = new Globals();
    String sTestName = "Test 12134";
    String sTarget = "Target ABCD";
    String sStep = "99";

	public void takeScreen(String text, Boolean addReport) {
        
        log(text, false);
 
            // set file name and destination for screen shot
            File scrFile = ((TakesScreenshot) gbl99.getExtendedDriver().remoteDriver)
                    .getScreenshotAs(OutputType.FILE);
            DateFormat dateFormat = new SimpleDateFormat(
                    "dd_MMM_yyyy__hh_mm_ssaa");
            String destDir = "./surefire-reports/html/screenshots/";
            new File(destDir).mkdirs();
             String destFile = sTestName + "_" + sTarget 
                    + "_Step" + sStep + "_" + dateFormat.format(new Date())
                    + ".png";
 
            // copy screen shot to directory for jenkins
            try {
                FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
             
            log("screenShot: " + destDir + "/" + destFile, false);
            // Display screenshot to ReportNG
            if (addReport) {
 
                String userDirector = "./screenshots/";
                log("<u><b>||||||" + text + "</b></u><br><a href=\""
                        + userDirector + destFile + "\"><img src=\""
                        + userDirector + destFile + "\" alt=\"\""
                        + "height='100' width='100'/> " + "<br />", addReport);
            }
         
    }
	
	   // writes to console or/and report log
    // boolean controls whether report log is written to
    public void log(String text, Boolean addReport) {
        String newLine = System.getProperty("line.separator");
 
        if (addReport) {
            final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
            System.setProperty(ESCAPE_PROPERTY, "false");
            Reporter.log(text.replace("<u><b>||||||", "<u><b>" + sTestName + "_"
                    + sTarget +  "_Step" + sStep + "_"));
        } else {
            System.out.println(sTestName + "_" + sTarget + "_" 
                    + "_Step" + sStep + "_" + text + newLine);
        }
    }
 
    // Calls downloadreport, copys the perfecto report to the screen directory
    // boolean will add the report to the testNG report
    public void downloadReportDisplay(Boolean addReport) throws IOException {
 
        {
            // set file format and destination for report
            DateFormat dateFormat = new SimpleDateFormat(
                    "dd_MMM_yyyy__hh_mm_ssaa");
            String destDir = "./surefire-reports/html/screenshots/";
            new File(destDir).mkdirs();
            String destFile = dateFormat.format(new Date());
 
            // download report
            downloadReport("pdf", destDir , destFile);
            // Display screenshot to ReportNG
            String userDirector = "./screenshots/";
 
            String destFileNew = destFile + ".pdf";
 
            log("perfectoReport: " + userDirector + destFileNew, false);
            if (addReport) {
                log("<a href=\"" + userDirector + destFileNew
                        + "\">Perfecto Report</a><br />", addReport);
            }
        }
    }
 
 
    // download report from perfecto
    private void downloadReport(String type, String fileLocation, String file)
            throws IOException {
       {       
                // downloads report for remote web driver
                String command = "mobile:report:download";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("type", type);
                String report;               
                    report = (String) gbl99.getExtendedDriver().remoteDriver.executeScript(
                            command, params);
                     
                //download to directory for jenkins
                File reportFile = new File(fileLocation + "/" + file + "." + type);
                BufferedOutputStream output = new BufferedOutputStream(
                        new FileOutputStream(reportFile));
                byte[] reportBytes = OutputType.BYTES
                        .convertFromBase64Png(report);
                output.write(reportBytes);
                output.close();   
            }       
    }
}
