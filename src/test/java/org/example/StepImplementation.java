package org.example;

import com.companyname.automation.webdriverextensions.ExtendedDriver;
import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.BeforeSpec;
import com.thoughtworks.gauge.AfterSpec;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.companyname.automation.commontools.*;
import com.companyname.automation.webdriverextensions.*;
import com.companyname.automation.uiobjectmodels.SiteHeader;
import com.companyname.automation.uiobjectmodels.ProductCollectionPage;
import com.companyname.automation.uiobjectmodels.ProductCollectionPage.Item;
import com.companyname.automation.uiobjectmodels.JoinTheMovement;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.Assert;

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


public class StepImplementation {
    NonObjectMethods nom77 = new NonObjectMethods();

    Globals gbl177 = new Globals();
    ExtendedDriver ed55 = null;
    SiteHeader sh99 = new SiteHeader();

    @BeforeSpec()
    public void OpenDriver() {
        String sFCurrent = System.getProperty("user.dir") + File.separator + "test-output";
        String sResultFolder = "";
        ed55 = new ExtendedDriver("chrome");
        gbl177.setExtendedDriver(ed55);
        gbl177.setAlReporterEntries(new ArrayList<String>());
        if (gbl177.getHtmlResultsLog() == null) {
            gbl177.setsLogoLocation("");
            gbl177.setsCompanyName("4 Ocean");
            System.err.println("Start");
            gbl177.setbDetailLog(true);
            gbl177.setsScreenCaptureLevel(""); //fail none
            HtmlResultsLogging hrl99 = new HtmlResultsLogging();
            gbl177.setWebdriverlog(hrl99);
            HtmlEventsLogging hel99 = new HtmlEventsLogging();
            gbl177.setHtmleventslogging(hel99);
            System.out.println("SFCurrent: " + sFCurrent);
            System.out.println("sResultFolder: " + sResultFolder);

            hrl99.LogStarter(
                    "4Ocean Test1", sFCurrent + File.separator + sResultFolder);


            File fRslt = new File(hrl99.getsResultsFldr());
            System.out.println("Html Log: " + fRslt.getAbsolutePath());
            gbl177.setWebdriverlog(hrl99);
            hrl99.LogTestMessage(gbl177.getExtendedDriver().remoteDriver.getCapabilities().toString());
            hel99.LogStarter("4Ocean Test1",
                    sFCurrent + File.separator + sResultFolder);
            gbl177.setHtmleventslogging(hel99);
            System.err.println("Log Started GGGGG");
            System.err.println("Log Started GGGGGAAAAAAAAAAAAAAAAAAAAAAA");
            gbl177.setbTestResult(true);

        }
    }

    @AfterSpec()
    public void CloseDriver() {
        gbl177.getExtendedDriver().ShutDown();
        gbl177.getHtmleventslogging().LogStopper();
        gbl177.getHtmlResultsLog().LogStopper();

        ArrayList<String> alR = gbl177.getAlReporterEntries();
        for (int iStart = 0; iStart < alR.size(); iStart++) {
            Gauge.writeMessage("Report %s", alR.get(iStart).toString());
        }


    }

    @Step("Go to Gauge Get Started Page")
    public void gotoGetStartedPage() throws InterruptedException {
        ExtendedWebElement getStartedButton = gbl177.getExtendedDriver().GetExtendedWebElement(By.xpath("//a[@href='https://docs.gauge.org/getting_started/installing-gauge.html']"));
        getStartedButton.HighliteOn();
        Gauge.captureScreenshot();
        Gauge.writeMessage("Click on Get Started");
        getStartedButton.click();

        Gauge.captureScreenshot();
        Gauge.writeMessage("Page title is %s  \n Page url is %s", gbl177.getExtendedDriver().GetTitle(), gbl177.getExtendedDriver().GetCurrentURL());

        Gauge.writeMessage("working Dir: %s", nom77.GetWorkingDirectory());
    }

    @Step("Ensure installation instructions are available")
    public void ensureInstallationInstructionsAreAvailable() throws InterruptedException {
        ExtendedWebElement instructions = gbl177.getExtendedDriver().GetExtendedWebElement(By.xpath("//a[@href='/writing-specifications.html']"));
        instructions.HighliteOn();

        assertThat(instructions).isNotNull();
        Gauge.captureScreenshot();
        Gauge.writeMessage("Instructions are there");
    }

    @Step("Open the Gauge homepage")
    public void implementation1() {
        String app_url = System.getenv("APP_URL");
        gbl177.getExtendedDriver().OpenURL(app_url + "/");
        assertThat(gbl177.getExtendedDriver().GetTitle()).contains("Gauge");
        Gauge.captureScreenshot();
        Gauge.writeMessage("Gauge is Open");
    }

    @Step("Open The 4Ocean Site")
    public void Open4Ocean() {
        if (SpecDataStore.get("4OceanOpen") == null) {

            String app_url = "https://www.4ocean.com";
            gbl177.getExtendedDriver().OpenURL(app_url);
            Assert.assertTrue(gbl177.getExtendedDriver().GetTitle().contains("ocean"), "4Ocean Did not open");

            Gauge.captureScreenshot();
            Gauge.writeMessage("Gauge is Open");
            try {
                JoinTheMovement jtmNow = new JoinTheMovement();
                jtmNow.CloseIt();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
            }
            SpecDataStore.put("4OceanOpen", true);

        } else {
            Gauge.captureScreenshot();
            Gauge.writeMessage("Site Already Open");
        }
    }

    @Step("Open The Catalog")
    public void OpenCatalog() {
        if (SpecDataStore.get("CatalogOpen") == null) {

            BooleanMessage bm55 = sh99.StartShopping();
            Gauge.captureScreenshot();
            Assert.assertTrue(bm55.isbSuccess(), bm55.getsMessage());
            SpecDataStore.put("CatalogOpen", true);

        } else {
            Gauge.captureScreenshot();
            Gauge.writeMessage("Catalog Already Open");
        }

    }



    @Step("Find Item <table>")
    public void FindItem(String sItemName) {
        ProductCollectionPage pcpOne = new ProductCollectionPage();

        String sMsg = "";
        pcpOne.GetEweProductList().HighliteOn();
        Item ib = pcpOne.GetItemByNameX(sItemName);
        if (ib != null) {
             Assert.assertTrue(true, sItemName + " FOUND");
            ib.getEweItemImage().HighliteOn();
            ib.getEweItemName().HighliteOn();
            Gauge.captureScreenshot();
            Gauge.writeMessage("Item %s Found", sItemName);
            ib.getEweItemBadge().HighliteOff();
            ib.getEweItemImage().HighliteOff();
            ib.getEweItemName().HighliteOff();
            ib.getEweItemPrice().HighliteOff();
            try {
                ib.getEweOriginalPrice().HighliteOff();
            } catch (Exception e88) {

            }

        } else {
               Assert.assertTrue(false, sItemName + " NOT FOUND");
        }

    }

}
