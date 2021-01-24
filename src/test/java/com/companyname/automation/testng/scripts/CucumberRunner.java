package com.companyname.automation.testng.scripts;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import com.companyname.automation.commontools.ArrayListFldValues;
import com.companyname.automation.commontools.LineReader;
import com.companyname.automation.commontools.LineWriter;
import com.companyname.automation.cucumberhelpers.ScenarioObject;
import io.cucumber.testng.*;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.util.ArrayList;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"StepDefinitions"},
        tags = ""
)

public class CucumberRunner extends TestNgUnit
{
    private final TestNGCucumberRunner testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    String sFeatureSet="";
    String sScenarioSet="";
    String sQt = "" + (char)39;



    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {

//	        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        System.out.println("In Before Class");
    }

    @BeforeMethod(groups = { "Start", "ALL" })
    @Parameters({
            "DetailLog",
            "ScreenCaptureLevel",
            "Browser",
            "Features",  // blank no check on the Feature Provided
            "Scenarios", // blank no check on the Scenario/Pickle Provided
            "ResultsFolder",
            "EventLogFolder",
            "Expected",
            "ExcelFile",
            "Worksheet"
    })
    public void beforeTestMethod(
            @Optional("true") boolean bDetailLog,
            @Optional("All") String sScreenCaptureLevel,
            @Optional("chrome") String sBrowser,
            @Optional("") String sFeatures,
            @Optional("") String sScenarios,
            @Optional("") String sResultsFolder,
            @Optional("") String sEventsLogFolder,
            @Optional("") String sExpected,
            @Optional("") String sExcelFile,
            @Optional("") String sWorksheet,
            ITestContext ctx)

    {
        System.out.println("in Before Method");
        System.out.println("BT: ctx: Suite -- " + ctx.getSuite().getName());
        System.out.println("BT: ctx:XMLtest -- " + ctx.getCurrentXmlTest().getName());
        System.out.println("BT: ctx: Name -- " + ctx.getName());
        System.out.println("BT: Browser -- " + sBrowser);


        sScenarioSet = sScenarios;
        sFeatureSet = sFeatures;

        gbl99.setsBrowser(sBrowser);
        gbl99.setsResultsFolder(sResultsFolder);
        gbl99.setsEventsLogFolder(sEventsLogFolder);


        System.err.println("Start");

        gbl99.setsLogoLocation("");
        gbl99.setsCompanyName("4 Ocean");
        System.err.println("Start");
        gbl99.setbDetailLog(true);
        gbl99.setsScreenCaptureLevel(""); //fail none

         gbl99.setWebdriverlog(hrl99);
        gbl99.setHtmleventslogging(hel99);
        System.out.println("Exit beforeMethod");
    }//end method beforeMethod


    @Test(groups = "cucumber scenarios", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickleEvent, FeatureWrapper
            cucumberFeature,ITestContext ctx) throws Throwable{
            System.out.println("In @Test scenario");
            gbl99.setTestContext(ctx);
        System.out.println("Picle is: " + pickleEvent.getPickle().getName());
        System.out.println("Picle URI is: " + pickleEvent.getPickle().getUri());
        LineReader lr88 = new LineReader(pickleEvent.getPickle().getUri().getPath());
        ArrayList<String> alLines=  lr88.ReadAllLines();
        String sFeature ="";
        System.out.println("Feature is ");
        String sScenario = pickleEvent.getPickle().getName();
        String sScenarioOutline = "";
        System.out.println("sScenario: " + sScenario);
        for(int i88=0; i88<alLines.size(); i88++)
        {
            if(alLines.get(i88).toLowerCase().contains("feature"))
            {
                String saF[] =  alLines.get(i88).split(":");
                sFeature =  saF[1];

            }

            if(alLines.get(i88).toLowerCase().contains("scenario"))          {
                String saF[] =  alLines.get(i88).split(":");
                sScenario =  saF[1];
                System.out.println("Scenario is: " + sScenario);

            }

            if(alLines.get(i88).toLowerCase().contains("scenario outline"))          {
                String saF[] =  alLines.get(i88).split(":");
                sScenarioOutline =  saF[1];
                System.out.println("Scenario Outline is: " + sScenarioOutline);
            }

        }
/*
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        Fillo fillo = new Fillo();
        boolean bNoRecs = true;
        Connection conn1 = fillo.getConnection("C:\\TestTemplateSource\\Book1.xlsx");
        String sQry = "Select * from Sheet1 where Feature = " + sQt + sFeature.trim() +
                sQt + " and Scenario = " + sQt + sScenario.trim() +sQt;
        System.out.println("Qry: " + sQry);
        Recordset rs77=null;
        try {
            rs77 = conn1.executeQuery(sQry);
        } catch (FilloException e) {
            // TODO Auto-generated catch block
            //	e.printStackTrace();
            rs77 = null;
        }
        if(rs77 != null)
        {
            if (rs77.getCount() > 0) {
                System.out.println("we have " + rs77.getCount() + " records");
                bNoRecs = false;
                ArrayList<String> alFields = rs77.getFieldNames();
                for (String sFld : alFields) {
                    System.out.println("Field: " + sFld);
                }
                int iRec = 0;
                rs77.moveFirst();
                boolean bLoop = true;
                while (bLoop) {
                    ArrayList<String> alValues = new ArrayList<String>();

                    System.out.println("Record _______________" + iRec);
                    for (int i8 = 0; i8 < alFields.size(); i8++) {
                        System.out.println("Field Name: " + alFields.get(i8));
                        System.out.println("Field Value: " + rs77.getField(alFields.get(i8)));

                        alValues.add(rs77.getField(alFields.get(i8)));
                    }

                    ArrayListFldValues alfv = new ArrayListFldValues(alFields, alValues);
                    gbl99.setScenarioObject(new ScenarioObject(sFeature, sScenario, alfv));
                    testNGCucumberRunner.runScenario(pickleEvent.getPickle());
                    System.out.println("Back from Run Scenario");
                    bLoop = rs77.next();
                    System.out.println("Go to top");

                }

            }
            rs77.close();
        }
        conn1.close();

 */
    //    if(bNoRecs)
        {
            gbl99.setScenarioObject(new ScenarioObject(sFeature,sScenario,null));
            testNGCucumberRunner.runScenario(pickleEvent.getPickle());

        }
        System.out.println("exit @Test scenario");

    }


    @AfterMethod(groups = { "Finish", "All" })
    public void afterTestMethod() {

        System.out.println("in After Test Method" + gbl99.isbDetailLog());

        // hel99.LogTestEvent(ev12);
        // el99.LogEvent(ev12);
        System.out.println("Test Dir: " + gbl99.getTestContext().getSuite().getOutputDirectory());
        System.out.println("Results Dir: " + gbl99.getHtmlResultsLog().getsResultsFldr());
        System.out.println("Current Dir: " + nom99.GetWorkingDirectory());

        System.err
                .println("************************************************************************************************");
        System.err
                .println("************************************************************************************************");
        System.err
                .println("************************************************************************************************");
        System.err
                .println("************************************************************************************************");
        Reporter.log("Test Finished: " + gbl99.getTestContext().getName());
        Reporter.clear();
        System.out.println("user home:  " + System.getProperty("user.home"));
        System.out.println("out  After Test Merhod");

    }


    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
        System.out.println("Out  @AfterClass");

    }


    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

}
