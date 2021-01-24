package com.companyname.automation.cucumberhelpers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.companyname.automation.commontools.LineReader;


import io.cucumber.java.Scenario;

public class CucumberInfo {

	static String sDQt = "" + (char) 34;

	
	public static String getFeatureName(Scenario scenario) {
		
        String featureName = "";
        System.out.println("scenario.getId(): " + scenario.getId());  
        // Usually the scenario Id is doctored version of the lines following 
        // the Feature: and the Scenario: keywords.
        // Eg.: scenario.getId(): a-long-(20-minute)-non-invasive-smoke-test-that-
        //comfirms-that-i-can-login-to-area51-via-the-nasa-portal;as-a-superuser-i-
        //must-be-able-to-login-to-area51-via-the-nasa-portal-so-that-i-can-access-
        //all-the-secret-files
        
        String rawFeatureName = scenario.getId().split(";")[0]
                         .replace("-i-", "-I-").replace("-"," ");
        System.out.println("Raw Ftr: " + rawFeatureName);
        String sFile = rawFeatureName.replace("file:///", "");
        sFile = sFile.substring(0,sFile.lastIndexOf(":"));
        System.out.println("Feature File: " + sFile);
        LineReader lr = new LineReader(sFile);
        String sLn = lr.ReadNextLine();
        while(sLn.indexOf("Feature") < 0)
        {
        	sLn = lr.ReadNextLine();
        }
        featureName = sLn.replace("Feature:", "").trim();
        sLn = lr.ReadNextLine();
        while(sLn.indexOf("Scenario") < 0)
        {
        	featureName = featureName + "\n" + sLn;
        	sLn = lr.ReadNextLine();
        }
//        featureName = featureName + rawFeatureName.substring(0, 1).toUpperCase() + 
//                rawFeatureName.substring(1).replace("nasa", "NASA");
//        String saFeatureNameParts[] = featureName.split("/");
//        featureName = saFeatureNameParts[saFeatureNameParts.length-1];
//        String sPd = "" + (char)56;
//        saFeatureNameParts = featureName.split(".feature");
//        featureName = saFeatureNameParts[0];

        return featureName;
    }

	public static StepInfo GetStepInfo(Method mthd)
	{
	    Annotation aX[] = mthd.getAnnotations();
	    System.out.println("STEP INFO");
	    System.out.println(aX[0].toString());
	    String saParts[] = aX[0].toString().split(sDQt);
	    String sDesc = saParts[1];
	    String sType = aX[0].annotationType().getSimpleName();
	    return new StepInfo(sType,sDesc);
	}
}
