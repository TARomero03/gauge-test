package StepDefinitions;

import java.io.File;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.companyname.automation.commontools.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;

import org.testng.Assert;
import org.testng.Reporter;

import com.companyname.automation.uiobjectmodels.JoinTheMovement;
import com.companyname.automation.uiobjectmodels.ProductCollectionPage;
import com.companyname.automation.uiobjectmodels.SiteHeader;
import com.companyname.automation.webdriverextensions.ExtendedDriver;

public class TestStepDefinitions {

    Globals gbl99 = new Globals();
    NonObjectMethods nom99 = new NonObjectMethods();
    ExtendedDriver ed99;
    SiteHeader sh99 = new SiteHeader();
    ProductCollectionPage pcp99 = new ProductCollectionPage();
    JoinTheMovement jtm99 = new JoinTheMovement();
    Event evScenario;

    @Before
    public void StartScenario(Scenario scenario)
    {
        System.out.println("In @Before Start Scenario");

        //    System.out.println("Browser: " + gbl99.getsBrowser());
        System.out.println("S: ctx: Suite -- " + gbl99.getTestContext().getSuite().getName());
        System.out.println("S: ctx:XMLtest -- " + gbl99.getTestContext().getCurrentXmlTest().getName());
        System.out.println("S: ctx: Name -- " + gbl99.getTestContext().getName());
        System.out.println("Start Scenario: " + scenario.getId() + " Name: " + scenario.getName());
        String sResultFolder = "";
        String sFCurrent = System.getProperty("user.dir") + File.separator + "test-output";

        ed99 = new ExtendedDriver(gbl99.getsBrowser());
        gbl99.setExtendedDriver(ed99);
         if (gbl99.getHtmlResultsLog() == null) {
   //         gbl99.setsLogoLocation("");
   //         gbl99.setsCompanyName("4 Ocean");
            System.err.println("Start");
            gbl99.setbDetailLog(true);
   //         gbl99.setsScreenCaptureLevel(""); //fail none
            HtmlResultsLogging hrl99 = new HtmlResultsLogging();
            gbl99.setWebdriverlog(hrl99);

            HtmlEventsLogging hel99 = new HtmlEventsLogging();
            gbl99.setHtmleventslogging(hel99);
            System.out.println("SFCurrent: " + sFCurrent);
            System.out.println("sResultFolder: " + sResultFolder);

            hrl99.LogStarter(
                    scenario.getName() + "_" + scenario.getId(), sFCurrent + File.separator + sResultFolder);


            File fRslt = new File(hrl99.getsResultsFldr());
            System.out.println("Html Log: " + fRslt.getAbsolutePath());
            gbl99.setWebdriverlog(hrl99);
            hrl99.LogTestMessage(gbl99.getExtendedDriver().remoteDriver.getCapabilities().toString());
            hel99.LogStarter(scenario.getName() + "_" + scenario.getId(),
                    sFCurrent + File.separator + sResultFolder);
            gbl99.setHtmleventslogging(hel99);
            System.err.println("Log Started GGGGG");
            System.err.println("Log Started GGGGGAAAAAAAAAAAAAAAAAAAAAAA");
        }
            gbl99.setbTestResult(true);
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("Result folder: " + gbl99.getsResultsFolder());
        System.out.println("Event folder: " + gbl99.getsEventsLogFolder());
        sFCurrent = System.getProperty("user.dir") + File.separator + "test-output";
        String sTestNGLoc = "file:///"+sFCurrent + File.separator + gbl99.getsResultsFolder() + File.separator + "index.html";
        sTestNGLoc = sTestNGLoc.replace("\\", "/");
        sTestNGLoc = sTestNGLoc.replaceAll(" ", "%20");
        System.out.println("TestNGLoc: " + sTestNGLoc);

        String sSuiteName = "";
        try {
            sSuiteName = gbl99.getTestContext().getSuite().getName() + "_";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String sXMLName = "";
        try {
            sXMLName = gbl99.getTestContext().getCurrentXmlTest().getName();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("sSSuiteName: " + sSuiteName);
        System.out.println("sXMLName: " + sXMLName);

        String sScriptName =   sSuiteName + sXMLName +
                "_"
                +  gbl99.getScenarioObject().getsFeature() + "_" + gbl99.getScenarioObject().getsScenario();
        sScriptName = sScriptName.replace("<","_");
        sScriptName = sScriptName.replace(">","_");
        String sRFolder = sFCurrent + File.separator + gbl99.getsResultsFolder();

        System.out.println("ScriptName: " + sScriptName );
        System.out.println("Folder R:" + sRFolder);
        gbl99.getHtmlResultsLog().LogStarter(sScriptName,sRFolder);



       File fRslt = new File(gbl99.getHtmlResultsLog().getsResultsFldr());
        System.out.println("Html Log: " + fRslt.getAbsolutePath());
        gbl99.setWebdriverlog(gbl99.getHtmlResultsLog());
        gbl99.getHtmlResultsLog().LogTestMessage(gbl99.getExtendedDriver().
                remoteDriver.getCapabilities().toString());
//	  System.out.println("Host: " +	  gbl99.getTestContext().getHost());
        gbl99.getHtmleventslogging().LogStarter(sScriptName, sRFolder);
        System.err.println("Log Started GGGGG");
        System.err.println("Log Started GGGGGAAAAsAAAAAAAAAAAAAAAAAAA");
        gbl99.setbTestResult(true);
        String sArgs = "";
        try {
            ArrayList<String> alFlds = gbl99.getScenarioObject().getAlfvArguments().getAlFlds();
            ArrayList<String> alValues = gbl99.getScenarioObject().getAlfvArguments().getAlValues();
            sArgs = "\nArguements:";
            for(int i7=0; i7<alFlds.size(); i7++)
            {
                sArgs = sArgs + "\nField: " + alFlds.get(i7) + " Value: " + alValues.get(i7);
            }
            sArgs = sArgs + "\n";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //	e.printStackTrace();
        }
        gbl99.getHtmlResultsLog().LogTestMessage("Feature: " + gbl99.getScenarioObject().getsFeature() + "\nScenario: " + gbl99.getScenarioObject().getsFeature() + sArgs);

        try {
            gbl99.getExtendedDriver().remoteDriver.manage().window().maximize();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            //e1.printStackTrace();
        }
        evScenario = new Event("Scenario",20);
        evScenario.Start();
        System.out.println("Out @Before Start Scenario");

    }

    @After
    public void FinishScenario()
    {
        System.out.println("In @After Finish Scenario Start");

        evScenario.Stop();
        gbl99.getHtmleventslogging().LogTestEvent(evScenario);
        gbl99.getHtmleventslogging().LogStopper();
        gbl99.getHtmlResultsLog().LogStopper();
        gbl99.getExtendedDriver().ShutDown();

        System.out.println("FinishScenario: " + gbl99.getAlReporterEntries().size());
        Reporter.log("Test Scenario");
        Reporter.clear();
        System.out.println("In @After Finish Scenario End" );

    }

    @Given("The browser is open")
    public void the_browser_is_open() {
        // Write code here that turns the phrase above into concrete actions
        boolean b8 = false;
        if(gbl99.getExtendedDriver()!=null)
        {
            b8=true;
            gbl99.getHtmlResultsLog().LogBDDStepPassWithScreenCapture("Given", "browser is open", gbl99.getExtendedDriver().remoteDriver.getCapabilities().toString());
        }
        else
        {
            gbl99.getHtmlResultsLog().LogBDDStepFailureWithScreenCapture("Given", "browser is not open", "");

        }
        gbl99.setbTestResult(b8);
        Assert.assertTrue( b8, "There is not an open assigned browser");
        
    }

    @When("I enter in the URL http:\\/\\/www{double}ocean.com and hit the enter key")
    public void i_enter_in_the_URL_http_www_ocean_com_and_hit_the_enter_key(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        gbl99.getExtendedDriver().OpenURL("http://www.4Ocean.com");
    }

    @Then("the {int}Ocean Site should be open")
    public void the_Ocean_Site_should_be_open(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        String sBrowserUrl = gbl99.getExtendedDriver().GetCurrentURL();
        if((sBrowserUrl.toLowerCase().indexOf("4ocean.com")>=0))
        {

            gbl99.getHtmlResultsLog().LogBDDStepPassWithScreenCapture("Then", "4Ocean should open", "Site Opened");
        }
        else
        {
            gbl99.getHtmlResultsLog().LogBDDStepFailureWithScreenCapture("Then", "4Ocean should open", "Site did not open");
            gbl99.setbTestResult(false);
            Assert.assertTrue(false, "Site did not open");
        }

        jtm99.CloseIt();
    }

    @When("I Select the Shop button")
    public void i_Select_the_Shop_button() {
        // Write code here that turns the phrase above into concrete actions
        BooleanMessage bmRtn = sh99.StartShopping();
        gbl99.setbTestResult(bmRtn.isbSuccess());
        Assert.assertTrue(bmRtn.isbSuccess(), bmRtn.getsMessage());
    }

    @Then("the Shopping Page should open")
    public void the_Shopping_Page_should_open() {
        // Write code here that turns the phrase above into concrete actions
        if(pcp99.GetEweProductList()==null)
        {
            Assert.assertTrue(false, "Shopping Page did not open");
            gbl99.setbTestResult(false);
            gbl99.getHtmlResultsLog().LogBDDStepFailure("Then", "the Shopping Page should open", "Shopping Page did not open");;
        }
        else
        {
            gbl99.getHtmlResultsLog().LogBDDStepPass("Then", "the Shopping Page should open", "Shopping Page did open");;

        }
    }


    @When("I find Product {string}")
    public void i_find_Product(String ProductName) {
        // Write code here that turns the phrase above into concrete actions

        try {
            ProductName = gbl99.getScenarioObject().getAlfvArguments().GetValue("ProductName");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        System.out.println("product name is: " + ProductName);
        System.out.println("Results Log: " + gbl99.getHtmlResultsLog().getsResultsFldr());
        System.out.println("Events Log: " + gbl99.getHtmleventslogging().getsResultsFldr());
        if(pcp99.GetItemByNameX(ProductName)==null)
        {
            gbl99.setbTestResult(false);
            gbl99.getHtmlResultsLog().LogBDDStepFailure("When","I find Product " + ProductName , "Product Not Found");
            Assert.assertTrue(false, "Product: " + ProductName + " not found");
        }
        else
        {
            gbl99.getHtmlResultsLog().LogBDDStepPass("When","I find Product " + ProductName , "Product Found");
            Assert.assertTrue(true, "Product: " + ProductName + " not found");

        }
        System.out.println("product name is: " + ProductName);
     }

             @When("I find Item {string}")
            public void i_find_Item(String ProductName) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("product name is: " + ProductName);
        if(pcp99.GetItemByNameX(ProductName)==null)
        {
            gbl99.setbTestResult(false);
            gbl99.getHtmlResultsLog().LogBDDStepFailure("When","I find Product " + ProductName , "Product Not Found");
            Assert.assertTrue(false, "Product: " + ProductName + " not found");
        }
        else
        {
            gbl99.getHtmlResultsLog().LogBDDStepPass("When","I find Product " + ProductName , "Product Found");
            Assert.assertTrue(true, "Product: " + ProductName + " not found");

        }
        System.out.println("product name is: " + ProductName);
    }

    @Then("I highlight the Product")
    public void i_highlight_the_Product() {
        // Write code here that turns the phrase above into concrete actions
        //   throw new cucumber.api.PendingException();
    }


}
