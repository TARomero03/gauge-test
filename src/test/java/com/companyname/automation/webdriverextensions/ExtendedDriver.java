package com.companyname.automation.webdriverextensions;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.testng.annotations.Optional;

import com.companyname.automation.commontools.BooleanMessage;
import com.companyname.automation.commontools.Globals;
import com.companyname.automation.commontools.NonObjectMethods;
import com.companyname.automation.commontools.StopWatch;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class ExtendedDriver {

	public WebDriver driver;
	public RemoteWebDriver remoteDriver;
	private NonObjectMethods nomTools = new NonObjectMethods();
	private Globals gbl99 = new Globals();
	double dDefaultWaitTime = 10.00;

	public ExtendedDriver(String sBrowser) {
		super();
		// TODO Auto-generated constructor stub
		NonObjectMethods nomTools = new NonObjectMethods();
		Globals gbl99 = new Globals();
		if(sBrowser.compareToIgnoreCase("android")==0)
		{
			DesiredCapabilities dcZ = new DesiredCapabilities();
			dcZ.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_4_XL");
			dcZ.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
			dcZ.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		//	dcZ.setCapability(MobileCapabilityType., "Android");
			dcZ.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
			dcZ.setCapability("chromedriverExecutable", System.getenv("BrowserDrivers") + File.separator + "chromedriver.exe");
			AndroidDriver<AndroidElement> adZ;
			try {
				adZ = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"),dcZ);
				adZ.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver = adZ;
				remoteDriver = adZ;
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		if(sBrowser.compareToIgnoreCase("chrome")==0)
		{
		     
			  System.setProperty("webdriver.chrome.driver", System.getenv("BrowserDrivers") + File.separator + "chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
		
			options.addArguments("start-maximized");
			options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
//			options.addArguments("--dns-prefetch-disable");
			Set<String> stCaps = options.getCapabilityNames();
			String  sMsgX="";
//			gbl99.getHtmlResultsLog().LogTestMessage("Capabilities");
//			stCaps.forEach((e) -> { gbl99.getHtmlResultsLog().LogTestMessage("Capability" + e +  ": " + options.getCapability(e)); });

			driver = new ChromeDriver(options);
			remoteDriver = (RemoteWebDriver)driver;
			 
			 
			
	}
		
		if(sBrowser.compareToIgnoreCase("FireFox")==0)
		{
			System.setProperty("webdriver.gecko.driver",System.getenv("BrowserDrivers") + File.separator +    "geckodriver.exe");
			
			try {
				FirefoxOptions options = new FirefoxOptions();
//				options.addArguments("disable-infobars","start-maximized","headless","--disable-notifications");
//				options.addArguments("start-maximized","--disable-notifications");

				driver = new FirefoxDriver();
				remoteDriver = (RemoteWebDriver)driver;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		try {
			if(sBrowser.compareToIgnoreCase("Edge")==0)
			{
				System.setProperty("webdriver.edge.driver", System.getenv("BrowserDrivers") + File.separator + "MicrosoftWebDriver.exe");
				driver = new EdgeDriver();
				remoteDriver = (RemoteWebDriver)driver;
				driver.manage().window().maximize();	
				driver.navigate().to("http://www.ford.con");
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Edge Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
		if(sBrowser.compareToIgnoreCase("Safari")==0)
		{
		driver = (RemoteWebDriver)new SafariDriver();
		
		}
		this.driver = driver;
		remoteDriver = (RemoteWebDriver) driver;

//		remoteDriver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
//		remoteDriver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);


	}

	

	public Integer WatchForAVisibleElement(By by[], double dWaitSecs) {

		WebElement weFnd = null;

		int iFnd = -1;

		// System.out.println(saWinTitles.length);

		StopWatch sw99 = new StopWatch();

		sw99.start();

		while (sw99.getElapsedTime() < (dWaitSecs * 1000)) {

			// System.out.println("Waiting: " + lElapsed);

			for (int iChs = 0; iChs < by.length; iChs++) {

				try {

					// System.out.println("Chn: " + iChs);

					try {

						List<WebElement> lw88 = driver.findElements(by[iChs]);

						System.out.println("Chn: " + iChs + "  Found: " + lw88.size());

						if (lw88.size() == 0)
							continue;

						for (int iW = 0; iW < lw88.size(); iW++)

						{

							ExtendedWebElement ewe = (ExtendedWebElement) lw88.get(iW);

							if (ewe.IsObjectVisible())

							{

								weFnd = lw88.get(iW);

								iFnd = iChs;

								break;

							}

							else

							{

								weFnd = null;

							}

						}

					} catch (Exception e) {

						// TODO Auto-generated catch block

						weFnd = null;

					}

					if (weFnd != null) {

						break;

					}

				} catch (Exception e44) {

				}

			}

			if (weFnd != null) {

				break;

			}

		}

		return iFnd;

	}

	public ExtendedWebElement GetExtendedWebElement(By By, double dTimeInSecs)

	{

		ExtendedWebElement ewe = null;

		StopWatch swTimer = new StopWatch();

		swTimer.start();

		while (ewe == null)

		{

			try {

				ewe = new ExtendedWebElement(remoteDriver.findElement(By));

				if (ewe == null)

				{

					System.out.println("DT get ewe - null " + By.toString());

				}

				else

				{

					System.out.println("DT get ewe - not null " + By.toString());

					System.out.println("IsObjectVisible " + ewe.IsObjectVisible());

					System.out.println("IsDisplayed " + ewe.isDisplayed());

//                                                            return ewe;

				}

//                                    if(!ewe.IsObjectVisible())

//                                    {

//                                                    System.out.println("Object is not found");

//                                                    ewe = null;

//                                                    continue;

//                                    }

//                                    else

//                                    {

//                                                    System.out.println("Object is not found");

//                                                    break;

//                                                    //ewe = null;

//                                    }

			} catch (Exception e) {

				// TODO Auto-generated catch block

				ewe = null;

				e.printStackTrace();

			}

			if (ewe != null)
				break;

			if (swTimer.getElapsedTimeSecs() > dTimeInSecs)
				break;

			nomTools.NapTime(0.100);

		}

		return ewe;

	}

	public ExtendedWebElement GetExtendedWebElement(By By)

	{

		return GetExtendedWebElement(By, dDefaultWaitTime);

	}

	public ExtendedWebElement WatchForVisibleExtendedWebElement(By By, double dTimeInSecs)

	{

		ExtendedWebElement ewe = null;

		StopWatch swTimer = new StopWatch();

		swTimer.start();

		while (ewe == null)

		{

			try {

				ewe = new ExtendedWebElement(remoteDriver.findElement(By));

				if (ewe == null)

				{

					System.out.println("DT get ewe - null " + By.toString());

				}

				else

				{

					System.out.println("DT get ewe - not null " + By.toString());

					System.out.println("IsObjectVisible " + ewe.IsObjectVisible());

					System.out.println("IsDisplayed " + ewe.isDisplayed());

//                                                            return ewe;

				}

                                   if(!ewe.IsObjectVisible())

                                    {

                                                    System.out.println("Object is not visible");

                                                    ewe = null;

                                                    continue;

                                    }

                                    else

                                    {

                                                    System.out.println("Object is visible");

                                                    break;

                                                    //ewe = null;

                                    }

			} catch (Exception e) {

				// TODO Auto-generated catch block

				ewe = null;

				e.printStackTrace();

			}

			if (swTimer.getElapsedTimeSecs() > dTimeInSecs)
				break;

			nomTools.NapTime(0.100);

		}

		return ewe;

	}

	public ExtendedWebElement WatchForVisibleExtendedWebElement(By By)

	{

		return WatchForVisibleExtendedWebElement(By, dDefaultWaitTime);

	}



	public ExtendedWebElement GetExtendedWebElement(WebElement we)

	{

		return new ExtendedWebElement(we);

	}

	public List<ExtendedWebElement> GetExtendedWebElements(By By, double dTimeInSecs) {

		ExtendedWebElement ewe = null;

		List<ExtendedWebElement> lweReturn = new ArrayList<ExtendedWebElement>();

		StopWatch swTimer = new StopWatch();

		swTimer.start();

		while (lweReturn.size() == 0) {

			try {

				List<WebElement> lwe = this.driver.findElements(By);

				if (lwe.size() > 0) {

					for (int iL = 0; iL < lwe.size(); iL++) {

						ewe = new ExtendedWebElement(lwe.get(iL));

						if (ewe == null) {

							System.out.println("DT get ewe - null " + By.toString());

						} else {

							System.out.println("DT get ewe - not null " + By.toString());

							System.out.println("IsObjectVisible " + ewe.IsObjectVisible());

							System.out.println("IsDisplayed " + ewe.isDisplayed());

							lweReturn.add(ewe);

						}

					}

				}

			} catch (Exception e) {

				// TODO Auto-generated catch block

				ewe = null;

//                                    e.printStackTrace();

			}

			if (lweReturn.size() > 0)

				break;

			if (swTimer.getElapsedTimeSecs() > dTimeInSecs)

				break;

			nomTools.NapTime(0.100);

		}

		return lweReturn;

	}

	public List<ExtendedWebElement> GetExtendedWebElements(By By) {

		return GetExtendedWebElements(By, dDefaultWaitTime);

	}

	public void ShutDown()
	{
		try {
			this.remoteDriver.close();
		} catch (Exception e) {
		}
		try {
			this.remoteDriver.quit();
		} catch (Exception e) {
		}
	}

	public BooleanMessage SwitchToDefault()
	{
		BooleanMessage bm = new BooleanMessage(true,"frame swtiched");

		try {
			this.remoteDriver.switchTo().defaultContent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bm.setbSuccess(false);
			System.out.println("Unable to switch to default content frame: " + " -- " + e.getMessage());
			bm.setsMessage("Unable to switch to default content frame: " + " -- " + e.getMessage());

		}
		return bm;
	}

	
	public BooleanMessage SwitchToFrame(int iFrame)
	{
		BooleanMessage bm = new BooleanMessage(true,"frame swtiched");

		try {
			this.remoteDriver.switchTo().frame(iFrame);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bm.setbSuccess(false);
			System.out.println("Unable to switch to frame: " + iFrame + " -- " + e.getMessage());
			bm.setsMessage("Unable to switch to frame: " + iFrame + " -- " + e.getMessage());

		}
		return bm;
	}
	
	public BooleanMessage SwitchToFrame(String sFrame)
	{
		BooleanMessage bm = new BooleanMessage(true,"frame swtiched");

		try {
			this.remoteDriver.switchTo().frame(sFrame);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bm.setbSuccess(false);
			System.out.println("Unable to switch to frame: " + sFrame + " -- " + e.getMessage());
			bm.setsMessage("Unable to switch to frame: " + sFrame + " -- " + e.getMessage());
			

		}
		return bm;
	}
	
	public BooleanMessage SwitchToFrame(By by)
	{
		ExtendedWebElement ewe = this.GetExtendedWebElement(by, 30);
		if(ewe == null) System.out.println("no frame " + by);
		BooleanMessage bm = new BooleanMessage(true,"frame swtiched");
		if(ewe != null)
		{
		try {
			this.remoteDriver.switchTo().frame(ewe.weCastFrom);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bm.setbSuccess(false);
			System.out.println("Unable to switch to frame: " + by + " -- " + e.getMessage());
			bm.setsMessage("Unable to switch to frame: " + by + " -- " + e.getMessage());
		}
		}
		else
		{
			bm.setbSuccess(false);
			bm.setsMessage("Unable to switch to frame: it does not exist");

		}
		return bm;
	}

	public BooleanMessage SwitchToFrame(ExtendedWebElement ewe)
	{
		BooleanMessage bm = new BooleanMessage(true,"frame swtiched");
		if(ewe != null)
		{
		try {
			this.remoteDriver.switchTo().frame(ewe.weCastFrom);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bm.setbSuccess(false);
			System.out.println("Unable to switch to frame: " + ewe.GetXpath() + " -- " + e.getMessage());
			bm.setsMessage("Unable to switch to frame: " + ewe.GetXpath() + " -- " + e.getMessage());
		}
		}
		else
		{
			bm.setbSuccess(false);
			bm.setsMessage("Unable to switch to frame: it does not exist");

		}
		return bm;
	}

	
	public void OpenURL(String sURL)
	{
		this.remoteDriver.get(sURL);
	}
	
	public String GetCurrentURL()
	{
		return this.remoteDriver.getCurrentUrl();
	}
	
	public String GetTitle()
	{
		return this.remoteDriver.getTitle();
	}

	/**
	 *  Flash The Elements in ArrayList
	 */
	public void FlashElements(ArrayList<ExtendedWebElement>alElements,int iSeconds)
	{
		StopWatch sw77 = new StopWatch();
		sw77.start();
		while(sw77.getElapsedTimeSecs() < iSeconds)
		{	
		for(int iG=0; iG<alElements.size();iG++)
		{
			alElements.get(iG).ScrollIntoView();
			 this.remoteDriver.getMouse().mouseMove(alElements.get(iG).getCoordinates());
			alElements.get(iG).HighliteOn();
			nomTools.NapTime(.25);
			alElements.get(iG).HighliteOff();
			
		}
		}
		}
	/**
	 *  Maximize the browser window for the driver that is passed via the Globals class
	 */
	public void MaxTheWindow() {
		try {
			RemoteWebDriver driver = this.remoteDriver;
			
			Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
			Dimension screensize = toolkit.getScreenSize();
			//System.err.println(screensize.width + " --- " + screensize.height);
			int iX = screensize.width / 2;
			int iY = screensize.height / 4;
			//System.err.println(iX + " --- " + iY);
			//System.err.println(screensize.getWidth() + " --- "
			//		+ screensize.getHeight());

			String sMx = " window.moveTo(0,0)";
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(sMx);

			sMx = "window.resizeTo(" + screensize.width + ","
					+ screensize.height + ")";
			jse.executeScript(sMx);

			System.err.println(screensize.width + " --- " + screensize.height);
		} catch (Exception E99X) {
			System.out.println("Exception E99: " + E99X.getMessage());

		}

	}

	/**
	 * Gets the parent element of the passed in element
	 * @param weChild - A WebElement for which the parent is being looked for
	 * @return - A WebElemet that is the parent
	 */
	public RemoteWebElement GetParent(RemoteWebElement weChild) {
		
		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);
		return (RemoteWebElement) weChild.findElement(By.xpath(".."));
	}
	

	
	/**
	 * Determines if the given By locator is present
	 * @param byLocator - A By locator that represents the desired element
	 * @return - A boolean that states if an element can be found with the By locator
	 * 			true - Element Found
	 * 			false - Element Not Found
	 */
	public boolean IsObjectPresent(By byLocator) {
		try {
			this.remoteDriver.manage().timeouts()
					.implicitlyWait(250, TimeUnit.MILLISECONDS);

			if(this.remoteDriver.findElement(byLocator)!=null)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	/**
	 * Determines if the given WebElement is present and visible 
	 * @param we99 - A WebElement
	 * @return - A boolean that states if an element can be found with the ByChained Locator
	 *			and is visible
	 * 			true - Element Found and Visible
	 * 			false - Element Not Found or Not Visible
	 */
	public boolean IsObjectVisible(WebElement we99) {
		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);

		try {
			if (we99==null)
			{
				System.out.println("Element not found");
				return false;
			}
			else
			{
				System.out.println("Element found");
				boolean bHidden = false;
				try
				{
					if(we99.getCssValue("visibility").compareTo("hidden")==0)
					{
						bHidden = true;
					}
				}
				catch(Exception e99)
				{
					
				}
				try
				{
					if(we99.getCssValue("VISIBILITY").compareTo("hidden")==0)
					{
						bHidden = true;
					}
				}
				catch(Exception e99)
				{
					
				}

			if ((we99.isDisplayed()&&(!bHidden))) {
					System.out.println("Element displayed");
					if((we99.getLocation().x >= 0) && (we99.getLocation().y >= 0) && (we99.getSize().height > 0) && (we99.getSize().width > 0))
					{	
						System.out.println("Element visible");
						return true;
					}
					else
					{
						System.out.println("Element not visible");
						return false;
					}	
					
				}
				else
				{	
					System.out.println("Element not displayed");
					return false;
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element exception");
			return false;
		}
	}

	

	/*
	 * Determines if the given By locator is present and visible 
	 * @param bycLocator - A By locator that represents the desired element
	 * @return - A boolean that states if an element can be found with the By Locator
	 *			and is visible
	 * 			true - Element Found and Visible
	 * 			false - Element Not Found or Not Visible
	 */
	public boolean IsObjectVisible(By byLocator) {
		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);

		try {
			RemoteWebDriver driver = this.remoteDriver;
			WebElement we99 = (WebElement) driver.findElement(byLocator);
			if(we99==null)
			{
				System.out.println("Element not found:");
				return false;
			}
			else
			{
				System.out.println("Element found:");
				boolean bHidden = false;
				try
				{
					if(we99.getCssValue("visibility").compareTo("hidden")==0)
					{
						bHidden = true;
					}
				}
				catch(Exception e99)
				{
					
				}
				try
				{
					if(we99.getCssValue("VISIBILITY").compareTo("hidden")==0)
					{
						bHidden = true;
					}
				}
				catch(Exception e99)
				{
					
				}

			if ((we99.isDisplayed()&&(!bHidden))) {
				System.out.println("Element displayed");
				if((we99.getLocation().x >= 0) && (we99.getLocation().y >= 0) && (we99.getSize().height > 0) && (we99.getSize().width > 0))
				{	
					System.out.println("Element visible");
					return true;
				}
				else
				{
					System.out.println("Element not visible");
				return false;
				}
			}
			else
			{
				System.out.println("Element not displayed");
			return false;
			}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element exception");
			return false;
		}
	}

	/**
	// * Determines if the given By locator is present and visible within the given time
	// * @param bycLocator - A By Locator that represents the desired element
	// * @param dWait - A double that represents the number of seconds to wait for the By locator to be found
	// * @return - A boolean that states if an element is found with the By Locator
	// *			,is visible and  found within the time given
	// * 			true - Element Found and Visible
	// * 			false - Element Not Found or Not Visible
	 */
	public boolean WaitForAnElement(By byLocator, double dWait) {
		boolean bPass = false;
		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);

		StopWatch sw99 = new StopWatch();
		sw99.start();
		// System.out.println(saWinTitles.length);
		while (sw99.getElapsedTime() < (dWait*1000)) {

			try {
				if (this.IsObjectVisible(byLocator)) {
					return true;
				} else {
					//System.err.println("Not Yet");
					bPass = false;
				}
			} catch (Exception E) {
				//System.err.println("Not Yet: " + E.getMessage());
				bPass = false;

			}

			if (bPass) {
				break;
			}
			nomTools.NapTime(0.100);

		}

		// System.out.println("Waitfor: " + bPass);
		return bPass;
	}



	/**
	 * Determines if the at least one of the given By/ByChained locator is present and visible within the given time
	 * @param alLocators - An ArrayList of By/ByChained Locators that represents one of the desired element
	 * @param dWait - A double that represents the number of seconds to wait for at least one of the By/ByChained locators to be found
	 * @return - A boolean that states if an element is found with one of the By/ByChained Locators
	 *			,is visible and  found within the time given
	 * 			true - An Element Found and Visible
	 * 			false - An Element Not Found or Not Visible
	 */
	public boolean WaitForElement(ArrayList alLocators, double dWait) {
		boolean bPass = false;
		long lElapsed = 0;
		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);

		StopWatch sw99 = new StopWatch();
		// System.out.println(saWinTitles.length);
		RemoteWebDriver driver = this.remoteDriver;
		sw99.start();
		while (sw99.getElapsedTime() < (dWait*1000)) {


			for (int iX = 0; iX < alLocators.size(); iX++) {

				System.err.println("Loc class: "
						+ alLocators.get(iX).getClass().getName());
				String sType = alLocators.get(iX).getClass().getName().trim();

				if (sType.indexOf("By") < 0) {
					System.out.println("Look for an element");
					WebElement we99 = (WebElement) alLocators.get(iX);
					
					if (this.IsObjectVisible(we99)) {
						bPass = true;
						System.err.println("Found it");
						return true;
					}
				}
			}
			if (bPass) {
				break;
			}

			nomTools.NapTime(0.100);

		}

		// System.out.println("Waitfor: " + bPass);
		return bPass;
	}

	/**
	 *  Clicks the element found with the locator
	 * @param byLocator - A By Locator for the element click. 
	 * @param dWait - A double represents the maximum time in seconds to wait
	 * @return - A boolean 
	 * 			true - Element clicked
	 * 			false - Element not clicked
	 */
	public boolean click(By byLocator, @Optional("0") double dWait) {
		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);
		RemoteWebDriver rwe99 = this.remoteDriver;
		Capabilities cp = rwe99.getCapabilities();

		try {
			
			WebElement weX = this.WatchForAnElement(byLocator, dWait);
			weX.click();
			return true;
/*			if (this.WaitForAnObject(ByLocator, iWaitInSeconds)) {
				driver.findElement(ByLocator).click();
			}
*/
			} catch (Exception E44) {
				return false;
		}

	}

	/**
	 *  Clicks the element 
	 * @param weX - the element to click. 
	 * @return - A boolean 
	 * 			true - Element clicked
	 * 			false - Element not clicked
	 */
	public boolean click(WebElement weX) {
		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);
		RemoteWebDriver rwe99 = this.remoteDriver;
		Capabilities cp = rwe99.getCapabilities();

		try {
			
			
			weX.click();
			return true;
/*			if (this.WaitForAnObject(ByLocator, iWaitInSeconds)) {
				driver.findElement(ByLocator).click();
			}
*/
			} catch (Exception E44) {
				return false;
		}

	}


	/**
	 * Returns the element found with the locator if it's found within the given time 
	 * @param by99 - A By Locator for the Element being looked for.
	 * @param dWaitSecs - A double , the maximum number pof seconds to wait for the element
	 * @return - A WebElement 
	 */
	public WebElement WatchForAnElement(By by99, double dWaitSecs) {
		WebElement weFnd = null;
 
		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);

		StopWatch sw99 = new StopWatch();
		// System.out.println(saWinTitles.length);
		RemoteWebDriver driver = this.remoteDriver;
		sw99.start();
		while (sw99.getElapsedTime() < (dWaitSecs * 1000)) {

			// System.out.println("Waiting: " + lElapsed);
			try {
				// System.out.println("Chn: " + iChs);
				try {
					weFnd = driver.findElement(by99);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					weFnd=null;
				}
				if (weFnd != null) {
					break;
				}
			} catch (Exception e44) {
				
			}

		}
		// System.out.println(weFnd.getText());
		return weFnd;
	}

	
	/**
	 * Returns the element found with the locator if it's found within the given time 
	 * @param by99 - A By Locator for the Element being looked for.
	 * @param dWaitSecs - A double , the maximum number pof seconds to wait for the element
	 * @return - A WebElement 
	 */
	public WebElement WatchForAVisibleElement(By by99, double dWaitSecs) {
		WebElement weFnd = null;
 
		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);

		StopWatch sw99 = new StopWatch();
		// System.out.println(saWinTitles.length);
		RemoteWebDriver driver = this.remoteDriver;
		sw99.start();
		while (sw99.getElapsedTime() < (dWaitSecs * 1000)) {

			// System.out.println("Waiting: " + lElapsed);
			try {
				// System.out.println("Chn: " + iChs);
				try {
					List<WebElement> lw88 = driver.findElements(by99);
					for (int iW=0; iW<lw88.size(); iW++)
					{
						ExtendedWebElement ewe = new ExtendedWebElement((RemoteWebElement) lw88.get(iW));
						if(ewe.IsObjectVisible())
						{
							weFnd = lw88.get(iW);
							break;
						}
						else
						{
							weFnd=null;
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					weFnd=null;
				}
				if (weFnd != null) {
					break;
				}
			} catch (Exception e44) {
				
			}

		}
		// System.out.println(weFnd.getText());
		return weFnd;
	}


	//************************************************************************************
	/**
	 * Determines if the at least one of the given By/ByChained locator is present within the given time
	 * @param alLocators - An ArrayList of By/ByChained Locators that represents one of the desired element
	 * @param dWait - A double that represents the number of seconds to wait for at least one of the By/ByChained locators to be found
	 * @return - A WebElement that is the first WebElement that is found with one of the By/ByChained Locators
	 *			, within the time given
	 * 			true - An Element Found
	 * 			false - An Element Not Found or Not Visible
	 */
	public WebElement WatchForElement(ArrayList alLocators, double dWait) {
		boolean bPass = false;
		long lElapsed = 0;
		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);

		StopWatch sw99 = new StopWatch();
		// System.out.println(saWinTitles.length);
		RemoteWebDriver driver = this.remoteDriver;
		sw99.start();
		while (sw99.getElapsedTime() < (dWait*1000)) {


			for (int iX = 0; iX < alLocators.size(); iX++) {

				System.err.println("Loc class: "
						+ alLocators.get(iX).getClass().getName());
				String sType = alLocators.get(iX).getClass().getName().trim();

				if (sType.indexOf("By") < 0) {
					System.out.println("Look for an element");
					WebElement we99 = (WebElement) alLocators.get(iX);
					
					if (we99!=null) {
						bPass = true;
						System.err.println("Found it");
						return we99;
					}
				}
			}

			nomTools.NapTime(0.100);
		}
		return null;
		}


	
	/**
	 * Waits for the URL to change to given URL with the given time 
	 * @param sNewUrl - A String that is the expected URL
	 * @param dWaitSecs - a double : the maximum number seconds to wait for the URKL to change
	 * @return - A boolean that shows if the URL changed or not
	 * 			true - the URL Changed to the expected
	 * 			false - the URL did not change to the expected URL
	 */
	public boolean WaitForUrlToChangeTo(String sNewUrl, double dWaitSecs) {
		WebElement weFnd = null;

		// System.out.println(saWinTitles.length);
		RemoteWebDriver driver = this.remoteDriver;
		StopWatch sw99 = new StopWatch();
		sw99.start();
		while (sw99.getElapsedTime() < (dWaitSecs * 1000)) {
			
			String sBrowser = driver.getCapabilities().getBrowserName().toLowerCase();
			if(sBrowser.indexOf("android")<0)
			{
			Set<String> stWindows =  driver.getWindowHandles();
			stWindows.removeAll(gbl99.getStCurrentWindows());
			if(stWindows.size() > 0) 
			{
				if(driver.getWindowHandle().compareTo((String) stWindows.toArray()[0])!=0)
				{	
				driver.switchTo().window((String) stWindows.toArray()[0]);
				}
			}
			}

			// System.out.println("Waiting: " + lElapsed);
			try {
				String sUrl = driver.getCurrentUrl();
				System.out.println("Waiting: " + sUrl + " vs " + sNewUrl);

				if (sUrl.compareToIgnoreCase(sNewUrl) == 0) {
					return true;
				}
				if (sUrl.compareToIgnoreCase(sNewUrl+"/") == 0) {
					return true;
				}

				if (sUrl.compareToIgnoreCase(sNewUrl.replaceFirst("http", "https")) == 0) {
					return true;
				}
				if (sUrl.compareToIgnoreCase(sNewUrl.replaceFirst("http", "https")+"/") == 0) {
					return true;
				}


			} catch (Exception e44) {

			}

			nomTools.NapTime(.10);
		}
		// System.out.println(weFnd.getText());
		return false;
	}

	/**
	// * Waits for the URL to change from given URL with the given time
	// * @param sNewUrl - A String that is old URL
	// * @param dWaitSecs - a double : the maximum number seconds to wait for the URKL to change
	// * @return - A boolean that shows if the URL changed or not
	// * 			true - the URL Change
	 //d
	 * 			false - the URL did not change
	 */
	public boolean WaitForUrlToChangeFrom(String sOldUrl, double dWait) {

		long lElapsed = 0;

		// System.out.println(saWinTitles.length);
		RemoteWebDriver driver = this.remoteDriver;
		StopWatch sw99 = new StopWatch();
		sw99.start();
		while (sw99.getElapsedTime() < (dWait * 1000)) {
			Set<String> stWindows =  driver.getWindowHandles();
			stWindows.removeAll(gbl99.getStCurrentWindows());
			if(stWindows.size() > 0) 
			{
				if(driver.getWindowHandle().compareTo((String) stWindows.toArray()[0])!=0)
				{	
				driver.switchTo().window((String) stWindows.toArray()[0]);
				}
			}

			// System.out.println("Waiting: " + lElapsed);
			try {
				String sUrl = driver.getCurrentUrl();
				if (sUrl.compareToIgnoreCase(sOldUrl) != 0) {
					return true;
				}
			} catch (Exception e44) {

			}

			
			nomTools.NapTime(.10);
		}
		// System.out.println(weFnd.getText());
		return false;
	}

	/*
	public WebElement WaitForAnElement(double dWait, final ByChained by99) {
		RemoteWebDriver driver = this.remoteDriver;
		long lWait = (long) (dWait * 1000);
		@SuppressWarnings("unchecked")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(lWait, TimeUnit.MILLISECONDS)
				.pollingEvery(1000, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by99);
			}
		});
		return null;
	}
*/
	
	/**
	 * Waits while an Element exists and is visible
	 * @param weItem - A WebElement that is being watched to disappear 
	 * @param dWait - A double: the number of seconds to wait for the element to no longer be there
	 * @return - a boolean to determine the elements existence
	 * 			true - element no longer exists
	 * 			false - element still exists
	 */
	public boolean WaitWhileAnElementIsVisible(WebElement weItem, double dWait) {
		boolean bPass = false;
		RemoteWebDriver driver = this.remoteDriver;
		StopWatch sw99 = new StopWatch();
		sw99.start();
		while (sw99.getElapsedTime() < (dWait * 1000)) {
			try {
				if (this.IsObjectVisible(weItem)) {
				}
				else
				{
					return true;
				}
			} catch (Exception E) {
				return true;
			}

			nomTools.NapTime(0.100);
		}
		return false;
	}


	/**
	 * Waits while an Element with the given locator exists and is visible
	// * @param bycLocator - A By locator for the WebElement that is being watched to disappear
	// * @param dWait - A double: the number of seconds to wait for the element to no longer be there
	 * @return - a boolean to determine the elements existence
	 * 			true - element no longer exists
	 * 			false - element still exists
	 */
	public boolean WaitWhileAnElementIsVisible(By byLocator, double dWait) {
		boolean bPass = false;
		RemoteWebDriver driver = this.remoteDriver;
		StopWatch sw99 = new StopWatch();
		sw99.start();
		while (sw99.getElapsedTime() < (dWait * 1000)) {
			try {
				if (!this.IsObjectVisible(byLocator)) {
					return true;
				}

			} catch (Exception E) {
				return true;
			}

			nomTools.NapTime(0.100);
		}
		return false;
	}

	/**
	 * Waits while an Element exists
	 * @param weItem - A WebElement that is being watched 
	 * @param dWait - A double: the number of seconds to wait for the element to no longer be there
	 * @return - a boolean to determine the elements existence
	 * 			true - element no longer exists
	 * 			false - element still exists
	 */
	public boolean WaitWhileAnElementExists(WebElement weItem, double dWait) {
		boolean bPass = false;
		RemoteWebDriver driver = this.remoteDriver;
		StopWatch sw99 = new StopWatch();
		sw99.start();
		while (sw99.getElapsedTime() < (dWait * 1000)) {
			try {
				if (weItem.isDisplayed()) {
				}
				else
				{
					return true;
				}
			} catch (Exception E) {
				return true;
			}

			nomTools.NapTime(0.100);
		}
		return false;
	}

	/**
	 * Waits while an Element with the given locator exists
	 //* @param bycLocator - A By locator for the WebElement that is being watched
	 //* @param dWait - A double: the number of seconds to wait for the element to no longer be there
	 * @return - a boolean to determine the elements existence
	 * 			true - element no longer exists
	 * 			false - element still exists
	 */
	public boolean WaitWhileAnElementExists(By byLocator, double dWait) {
		boolean bPass = false;
		RemoteWebDriver driver = this.remoteDriver;
		StopWatch sw99 = new StopWatch();
		sw99.start();
		while (sw99.getElapsedTime() < (dWait * 1000)) {
			try {
				if (!this.IsObjectPresent(byLocator)) {
					return true;
				}

			} catch (Exception E) {
				return true;
			}

			nomTools.NapTime(0.100);
		}
		return false;
	}

	/**
	 *  Waits for an Alert for a maximum of the given time in seconds
	 * @param dWait - A double: Specifies the maximum time to wait in seconds
	 * @return - A boolean: Specifies if an alert was found or not
	 * 			true - Alert was found
	 * 			false - Alert not found
	 */
	public Alert WaitForAlert(double dWait) {

/*		try {
			this.remoteDriver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("TimeOut Error");
			e.printStackTrace();
			return null;
		} 
*/

		StopWatch sw99 = new StopWatch();
		sw99.start();
		while (sw99.getElapsedTime() < (dWait * 1000)) {
			try {
				Alert a1 = this.remoteDriver.switchTo().alert();
				if(a1==null) continue;
				System.out.println("Alert Fnd: " + a1.getText());
				return a1;
			} catch (Exception e88) {
				
			}
			nomTools.NapTime(0.100);
		}
		return null;
	}

	
	/**
	 *  Waits for an Alert and if found retrieves alert Text
	 *  
	 //* @param dWait - A double: Specifies the maximum time to wait in seconds
	 //* @param by99 - A ByChained locator of the element being looked for
	 * @return - a string with contents of alert text or a NULL (alert not found) 
	 * 			
	 */
	public String GetAlertText(double dWait) {
		
		
		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);
		StopWatch sw99 = new StopWatch();
		sw99.start();
		while (sw99.getElapsedTime() < (dWait * 1000)) {
			try {
				Alert _alerttext=this.remoteDriver.switchTo().alert();
				return _alerttext.getText();
			} catch (Exception e88) {

			}
			
			nomTools.NapTime(0.100);
		}
		return null;
	}
	

	/**
	 *  Waits for an Alert or an Element for a maximum of the given time in seconds
	 * @param dWait - A double: Specifies the maximum time to wait in seconds
	 * @param by99 - A By locator of the element being looked for
	 * @return - A boolean: Specifies if the alert or element was found or not
	 * 			true - Alert or Element was found
	 * 			false - Neither the Alert nor the Element was found
	 */
	public boolean WaitForAlertOrElement(final By by99, double dWait) {
		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);
		StopWatch sw99 = new StopWatch();
		sw99.start();
		while (sw99.getElapsedTime() < (dWait * 1000)) {
			try {
				this.remoteDriver.switchTo().alert();
				return true;
			} catch (Exception e88) {

			}
			if (this.IsObjectVisible(by99)) {
				return true;
			}
			nomTools.NapTime(0.100);
		}
		return false;
	}

	/**
	 *  Waits for an Alert or an Element for a maximum of the given time in seconds
	 //* @param dWait - A double: Specifies the maximum time to wait in seconds
	 //* @param by99 - A By locator of the element being looked for
	 * @return - A boolean: Specifies if the alert or element was found or not
	 * 			true - Alert or Element was found
	 * 			false - Neither the Alert nor the Element was found
	 */
	public boolean WaitForAlertOrElement(final WebElement we99, double dWait) {

		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);
		StopWatch sw99 = new StopWatch();
		sw99.start();
		while (sw99.getElapsedTime() < (dWait * 1000)) {
			try {
				this.remoteDriver.switchTo().alert();
				return true;
			} catch (Exception e88) {

			}
			if (this.IsObjectVisible(we99)) {
				return true;
			}
			nomTools.NapTime(0.100);
		}
		return false;
	}

	/**
	 *  Waits for an Alert to appear or an Element to disappear for a maximum of the given time in seconds
	 * @param dWait - A double: Specifies the maximum time to wait in seconds
	 * @param by99 - A By locator of the element that is being monitored for existence
	 * @return - A boolean: Specifies if the alert or element was found or not
	 * 			true - Alert was found or Element went away
	 * 			false - Neither the Alert was found nor did the Element go away
	 */
	public boolean WaitForAlertToAppearOrElementToDisappear(final By by99,
			double dWait) {

		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);
		StopWatch sw99 = new StopWatch();
		sw99.start();
		while (sw99.getElapsedTime() < (dWait * 1000)) {
			try {
				this.remoteDriver.switchTo().alert();
				return true;
			} catch (Exception e88) {

			}
			if (this.IsObjectVisible(by99)==false) {
				return true;
			}
		}
		return false;
	}

	/**
	 *  Waits for an Alert to appear or an Element to disappear for a maximum of the given time in seconds
	 * @param dWait - A double: Specifies the maximum time to wait in seconds
	 * @param we99 - A WebElement that is being monitored for existence
	 * @return - A boolean: Specifies if the alert or element was found or not
	 * 			true - Alert was found or Element went away
	 * 			false - Neither the Alert was found nor did the Element go away
	 */
	public boolean WaitForAlertToAppearOrElementToDisappear(final WebElement we99,
			double dWait) {

		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);
		StopWatch sw99 = new StopWatch();
		sw99.start();
		while (sw99.getElapsedTime() < (dWait * 1000)) {
			try {
				this.remoteDriver.switchTo().alert();
				return true;
			} catch (Exception e88) {

			}
			if (this.IsObjectVisible(we99)==false) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Locates Ajax Elements within the given time
	 * @param locator
	 * @param iWaitInSeconds
	 * @return
	 */
	public boolean WaitForAnAjaxElemet(java.lang.reflect.Field locator,
			int iWaitInSeconds) {
		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);

		boolean bPass = false;
		long lElapsed = 0;
		RemoteWebDriver driver = this.remoteDriver;
		long lStart = System.currentTimeMillis();
		// System.out.println(saWinTitles.length);
		while (lElapsed < iWaitInSeconds) {

			lElapsed = (System.currentTimeMillis() - lStart) / 1000;
			System.out.println("Waiting: " + lElapsed);

			try {
				AjaxElementLocator ael = new AjaxElementLocator(driver,
						locator, iWaitInSeconds);
				WebElement we = ael.findElement();

			} catch (Exception E) {
				System.err.println("Ajax Not Yet: " + E.getMessage());
				bPass = false;

			}

			if (bPass) {
				break;
			}

			nomTools.NapTime(250);

		}

		// System.out.println("Waitfor: " + bPass);
		return bPass;
	}

	public boolean WaitForAjaxJqueryToFinish(int iSeconds)
	{
		
		RemoteWebDriver rwd99 = this.remoteDriver;
		Object oa[] = new Object[1];
		oa[0]="";
		boolean bThere = false;
		try {
			bThere = !(Boolean) rwd99.executeScript("return jQuery.active == 0", oa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StopWatch sw99 = new StopWatch();
		while(bThere)
		{
			if(sw99.getElapsedTimeSecs() > ((long)iSeconds))
			{
				
				return false;
			}
				try {
					bThere = !(Boolean) rwd99.executeScript("return jQuery.active == 0", oa);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		}
		return true;
	}
	

	/**
	 *  Waits for an Alert to appear or an Element to disappear for a maximum of the given time in seconds
	 * @param dWait - A double: Specifies the maximum time to wait in seconds
	 * @param by99 - A By locator of the element that is being monitored for existence
	 * @param byNew - A By locator of the New element that is being monitored for existence
	 * @return - A boolean: Specifies if the alert or element was found or not
	 * 			true - Alert was found or Element went away
	 * 			false - Neither the Alert was found nor did the Element go away
	 */
	public boolean WaitForAlertToAppearOrElementToDisappearOrNewElement(final By by99, final By byNew,
			double dWait) {

		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);
		StopWatch sw99 = new StopWatch();
		sw99.start();
		while (sw99.getElapsedTime() < (dWait * 1000)) {
			try {
				this.remoteDriver.switchTo().alert();
				return true;
			} catch (Exception e88) {

			}
			if (this.IsObjectVisible(by99)==true) {
				return true;
			}
		}
		return false;
	}

	/**
	 *  Waits for an Alert to appear or an Element to disappear for a maximum of the given time in seconds
	 * @param dWait - A double: Specifies the maximum time to wait in seconds
	 * @param we99 - A WebElement that is being monitored for existence
	 * @param weNew - A New WebElement that is being monitored for existence
	 * @return - A boolean: Specifies if the alert or element was found or not
	 * 			true - Alert was found or Element went away
	 * 			false - Neither the Alert was found nor did the Element go away
	 */
	public boolean WaitForAlertToAppearOrElementToDisappearOrNewElement(final WebElement we99, WebElement weNew,
			double dWait) {

		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);
		StopWatch sw99 = new StopWatch();
		sw99.start();
		while (sw99.getElapsedTime() < (dWait * 1000)) {
			try {
				this.remoteDriver.switchTo().alert();
				return true;
			} catch (Exception e88) {

			}
			if (this.IsObjectVisible(we99)==false) {
				return true;
			}
			if (this.IsObjectVisible(weNew)==true) {
				return true;
			}
		}
		return false;
	}

	/**
	 *  Waits for an Alert to appear or an Element to disappear for a maximum of the given time in seconds
	 * @param dWait - A double: Specifies the maximum time to wait in seconds
	 * @param we99 - A WebElement that is being monitored for existence
	 * @param weNew - A New WebElement that is being monitored for existence
	 * @return - A boolean: Specifies if the alert or element was found or not
	 * 			true - Alert was found or Element went away
	 * 			false - Neither the Alert was found nor did the Element go away
	 */
	public boolean WaitForElementsToDisappearOrNewElement(final WebElement we99[], WebElement weNew[],
			double dWait) {

		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);
		StopWatch sw99 = new StopWatch();
		sw99.start();
		while (sw99.getElapsedTime() < (dWait * 1000)) {
			try {
				this.remoteDriver.switchTo().alert();
				return true;
			} catch (Exception e88) {

			}
			boolean bItsGone = true;
			for(int iYY=0; iYY<we99.length; iYY++)
			{
				if (this.IsObjectVisible(we99[iYY])==true) {
					bItsGone = false;
				}
			}
			if(bItsGone) return true;
			for(int iXX=0; iXX<weNew.length; iXX++)
			{	
				if (this.IsObjectVisible(weNew[iXX])==true) {
					return true;
				}
			}
		}
		return false;
	}


    //**********************************************************************************
	
	/**
	 * Returns the element found with the locator if it's found within the given time 
	 * @param by99 - A By Locator for the Element being looked for.
	 * @param dWaitSecs - A double , the maximum number pof seconds to wait for the element
	 * @return - A WebElement 
	 */
	public ExtendedWebElement WatchForAVisibleExtendedElement(By by99, double dWaitSecs) {
		WebElement weFnd = null;

		this.remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);

		StopWatch sw99 = new StopWatch();
		// System.out.println(saWinTitles.length);
		RemoteWebDriver driver = this.remoteDriver;
		sw99.start();
		while (sw99.getElapsedTime() < (dWaitSecs * 1000)) {

			// System.out.println("Waiting: " + lElapsed);
			try {
				// System.out.println("Chn: " + iChs);
				try {
					List<WebElement> lw88 = driver.findElements(by99);
					for (int iW=0; iW<lw88.size(); iW++)
					{
						ExtendedWebElement ewe = new ExtendedWebElement((RemoteWebElement) lw88.get(iW));
						if(ewe.IsObjectVisible())
						{
							weFnd = lw88.get(iW);
							break;
						}
						else
						{
							weFnd=null;
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					weFnd=null;
				}
				if (weFnd != null) {
					break;
				}
			} catch (Exception e44) {
				
			}

		}
		// System.out.println(weFnd.getText());
		if(weFnd != null)
			{
			return new ExtendedWebElement((RemoteWebElement) weFnd);
			}
		else
		{
		return null;
		}
	}



}
