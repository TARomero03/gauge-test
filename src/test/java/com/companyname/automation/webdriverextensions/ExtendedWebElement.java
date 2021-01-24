package com.companyname.automation.webdriverextensions;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.Mouse;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;

import com.companyname.automation.commontools.BooleanMessage;
import com.companyname.automation.commontools.Event;
import com.companyname.automation.commontools.Globals;
import com.companyname.automation.commontools.NonObjectMethods;
import com.companyname.automation.commontools.StopWatch;




/**
 * Extends the WebElement
 * 
 * @author Timothy.Romero
 * 
 */
public class ExtendedWebElement {

	Globals gbl99 = new Globals();
	RemoteWebElement weCastFrom = null;
	NonObjectMethods nom99 = new NonObjectMethods();
	RemoteWebElement rwe99 = null;
	StopWatch sw88 = new StopWatch();
	Actions actions = new Actions(gbl99.getExtendedDriver().remoteDriver);
//	JavascriptExecutor jse = (JavascriptExecutor)gbl99.getExtendedDriver().remoteDriver;
	ExtendedDriver edWorks = gbl99.getExtendedDriver();

	/**
	 * @param weCastFrom
	 */
	public ExtendedWebElement(RemoteWebElement weCastFrom) {
		
		
		this.weCastFrom = weCastFrom;
		rwe99 = weCastFrom;
	}

	public ExtendedWebElement(WebElement weFrom) {
		
		
		this.weCastFrom = (RemoteWebElement) weFrom;
		rwe99 = weCastFrom;
	}

	/**
	 * This Method returns the parent element
	 * 
	 * @return A WebElement that is the ExtendedWebElement
	 */
	public ExtendedWebElement GetParentWebElement() {
		RemoteWebElement rwe = (RemoteWebElement) this.findElement(By.xpath(".."));
		return new ExtendedWebElement(rwe);
	}

	
	/**
	 * This Method returns the WebElement that the ExtendedWebElement is based on
	 * 
	 * @return A WebElement that is the ExtendedWebElement
	 */
	public RemoteWebElement GetWebElementCastFrom() {
		return rwe99;
	}

	 public BooleanMessage safeJavaScriptClick() {

		 

         WebElement element = this.weCastFrom;

         try {

                         if (element.isEnabled() && element.isDisplayed()) {

                                         System.out.println("Clicking on element with using java script click");

                                         ((JavascriptExecutor) gbl99.getExtendedDriver().remoteDriver).executeScript("arguments[0].click();", element);

                                         return new BooleanMessage(true, "success");

                         } else {

                                         System.out.println("Unable to click on element");

                                         return new BooleanMessage(false, "Unable to click on element");



                         }

         } catch (StaleElementReferenceException e) {

                         System.out.println("Element is not attached to the page document " + e.getStackTrace());

                         return new BooleanMessage(false, "Element is not attached to the page document ");



         } catch (NoSuchElementException e) {

                         System.out.println("Element was not found in DOM " + e.getStackTrace());

                         return new BooleanMessage(false, "Element was not found in DOM ");



         } catch (Exception e) {

                         System.out.println("Unable to click on element " + e.getStackTrace());

                         return new BooleanMessage(false, "Element was not found in DOM ");



         }



}



public ExtendedWebElement GetExtendedWebElement(By By, double dTimeInSecs) {

         ExtendedWebElement ewe = null;

         StopWatch swTimer = new StopWatch();

         swTimer.start();

         while (ewe == null) {



                         try {

                                         ewe = new ExtendedWebElement((RemoteWebElement) this.findElement(By));

                                         if (ewe == null) {

                                                         System.out.println("DT get ewe - null " + By.toString());

                                         } else {

                                                         System.out.println("DT get ewe - not null " + By.toString());

                                                         System.out.println("IsObjectVisible " + ewe.IsObjectVisible());

                                                         System.out.println("IsDisplayed " + ewe.isDisplayed());

//                                     return ewe;



                                         }



//                     if(!ewe.IsObjectVisible())

//                     {

//                                     System.out.println("Object is not found");

//                                     ewe = null;

//                                     continue;

//                     }

//                     else

//                     {

//                                     System.out.println("Object is not found");

//                                     break;

//                                     //ewe = null;

//                     }

                         } catch (Exception e) {

                                         // TODO Auto-generated catch block

                                         ewe = null;

//                     e.printStackTrace();

                         }

                         if (ewe != null)

                                         break;

                         if (swTimer.getElapsedTimeSecs() > dTimeInSecs)

                                         break;

                         nom99.NapTime(0.100);

         }



         return ewe;

}



public ExtendedWebElement GetExtendedWebElement(By By) {

         return GetExtendedWebElement(By, 10);

}


public List<ExtendedWebElement> GetExtendedWebElements(By By, double dTimeInSecs) {

         ExtendedWebElement ewe = null;

         List<ExtendedWebElement> lweReturn = new ArrayList();

         StopWatch swTimer = new StopWatch();

         swTimer.start();

         while (lweReturn.size() == 0) {



                         try {

                                         List<WebElement> lwe = this.findElements(By);

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

//                     e.printStackTrace();

                         }

                         if (lweReturn.size() > 0)

                                         break;

                         if (swTimer.getElapsedTimeSecs() > dTimeInSecs)

                                         break;

                         nom99.NapTime(0.100);

         }



         return lweReturn;

}

public List<ExtendedWebElement> GetExtendedWebElements(By By) {

return GetExtendedWebElements(By,10);

}



	
	
	/**
	 * This Method returns the Image Source of the Element or its parent
	 * 
	 * @return A String
	 */
	public String GetImageSrc() {
		String sImgSrc = this.getAttribute("src");
		if((sImgSrc == null) || (sImgSrc.trim().length() == 0))
		{
			
			sImgSrc = this.GetParentWebElement().getAttribute("src");
		}
		
		
		return sImgSrc;
	}

	public String GetHref() {
		String sImgSrc = this.getAttribute("href");
		if((sImgSrc == null) || (sImgSrc.trim().length() == 0))
		{
			sImgSrc = this.GetParentWebElement().getAttribute("href");
		}
		
		
		return sImgSrc;
	}

	public String GetForeGroundColor() {
		return this.getCssValue("color");
		
	}

	public String GetBackGroundColor() {
		String sRtn =  this.getCssValue("background-color");
		
		ExtendedWebElement eweP = new ExtendedWebElement(this.findElement(By.xpath("..")));
		while ((sRtn==null) || (sRtn.trim().length()==0))
		{
			if(eweP==null) 
			{
				sRtn = "";
				break;
			}
			else
			{
				sRtn = eweP.getCssValue("background-color");
			}
			eweP = new ExtendedWebElement(eweP.findElement(By.xpath("..")));	
		}
		
		return this.RGBAtoHEX(sRtn);
	}

	public String GetFontFamily() {
		return this.getCssValue("font-family");
	}

	public String GetFontSize() {
		return this.getCssValue("font-size");
	}

	public String GetFontWeight() {
		return this.getCssValue("font-weight");
	}

	public String GetHeight() {
		return this.getCssValue("height");
	}

	public String GetLineHeight() {
		return this.getCssValue("line-height");
	}

	public String GetMargin() {
		return this.getCssValue("margin");
	}

	public String GetPadding() {
		return this.getCssValue("padding");
	}

	public String GetWidth() {
		return this.getCssValue("width");
	}


	public String GetXpath()
	{
		String jscript = "function getPathTo(node) {" +
	            "  var stack = [];" +
	            "  while(node.parentNode !== null) {" +
	            "    stack.unshift(node.tagName);" +
	            "    node = node.parentNode;" +
	            "  }" +
	            "  return stack.join('/');" +
	            "}" +
	            "return getPathTo(arguments[0]);";
	        return (String) gbl99.getExtendedDriver().remoteDriver.executeScript(jscript, this.weCastFrom);
	 
	}
	// @Override
	public void clear() {
		boolean bNotCleared = true;
		sw88.start();
		while (bNotCleared) {
			try {
				this.HighliteOn();
				rwe99.clear();
				bNotCleared = false;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				if (edWorks.WatchForAnElement(
						By.xpath("//div[@class='fancybox-wrap fancybox-desktop fancybox-type-html fancybox-opened']"),
						.25) != null) {
					edWorks.WaitWhileAnElementIsVisible(
							By.xpath("//div[@class='fancybox-wrap fancybox-desktop fancybox-type-html fancybox-opened']"),
							30);
				} else {
					this.ScrollIntoView();
				}

			}
			if (sw88.getElapsedTimeSecs()>5)
			{
				break;
			}
		}

	}

	// @Override
	public void click() {
		boolean bNotClicked = true;
		sw88.start();
		while (bNotClicked) {
			if(sw88.getElapsedTimeSecs()>300){
				gbl99.getHtmlResultsLog().LogTestWarningWithScreenCapture("Not Clickable in 5 minutes");
				return;
			}
			try {
				System.err.println("attempt click");
			//	this.HighliteOn();
				rwe99.click();
				System.err.println("made click");
				bNotClicked = false;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				ExtendedWebElement eweLbl = edWorks.WatchForAVisibleExtendedElement(By.xpath("//div[@class='fancybox-wrap fancybox-desktop fancybox-type-html fancybox-opened']/div/div/div/h4"), .250);
				if (eweLbl  != null) {
					boolean bThere = true;
					Event ev99 = new Event("Please Wait",0);
					ev99.Start();
					while(bThere && (ev99.getdActualTime() < 120))
					{	

					eweLbl =  edWorks.WatchForAVisibleExtendedElement(By.xpath("//div[@class='fancybox-wrap fancybox-desktop fancybox-type-html fancybox-opened']/div/div/div/h4"), .250);	
					if(eweLbl==null) break;
					if(eweLbl.getText().compareToIgnoreCase("Please Wait")!=0) break;
					}
					ev99.Stop();
					
					gbl99.getHtmleventslogging().LogTestEvent(ev99);
					gbl99.getEventCSVlog().LogEvent(ev99);
					gbl99.getHtmlResultsLog().LogTestWarning("Please Wait displayed for " + ev99.getdActualTime() + " seconds" );
					sw88.start();
					
	
				}
				this.ScrollIntoView();
	//			System.err.println("Scroll into View2");
				gbl99.getExtendedDriver().remoteDriver.executeScript("window.scrollBy(0,-200)", "");

			}
			if(sw88.getElapsedTimeSecs()>300)
			{
				gbl99.getHtmlResultsLog().LogTestWarningWithScreenCapture("Not Clickable in 5 minutes");
				break;
			}
		}
	}


	// @Override
	public WebElement findElement(By arg0) {
		// TODO Auto-generated method stub
		return rwe99.findElement(arg0);
	}

	// @Override
	public List<WebElement> findElements(By arg0) {
		// TODO Auto-generated method stub
		return rwe99.findElements(arg0);
	}

	// @Override
	public String getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return rwe99.getAttribute(arg0);
	}

	// @Override
	public String getCssValue(String arg0) {
		// TODO Auto-generated method stub
		try {
			return rwe99.getCssValue(arg0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// @Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return rwe99.getLocation();
	}

	// @Override
	public Dimension getSize() {
		// TODO Auto-generated method stub
		return rwe99.getSize();
	}

	// @Override
	public String getTagName() {
		// TODO Auto-generated method stub
		return rwe99.getTagName();
	}

	// @Override
	public String getText() {
		// TODO Auto-generated method stub
		String sTxt = "";
		try {
			sTxt = rwe99.getText();
		} catch (Exception e88) {
		}
		System.err.println("getText: " + sTxt);
		
		
		if ((sTxt.trim().length() == 0) || sTxt == null) {
			try {
				sTxt = rwe99.getAttribute("label");
				System.err.println("label: " + sTxt);

			} catch (Exception e) {
			}

		}


		
		if ((sTxt == null)){
			try {
				sTxt = (String) gbl99.getExtendedDriver().remoteDriver.executeScript(
						"return arguments[0].innerHTML;", this.weCastFrom);
				System.err.println("innerHtml: " + sTxt);

			} catch (Exception e) {
			}

		}

		if (sTxt != null) {
			if ((sTxt.trim().length() == 0)) {
				try {
					sTxt = (String) gbl99.getExtendedDriver().remoteDriver.executeScript(
							"return arguments[0].innerHTML;", this.weCastFrom);
					System.err.println("innerHtml: " + sTxt);

				} catch (Exception e) {
				}

			}
		}
////////////////////////////////////////////

		
///////////////////////////////////////////		
		if(sTxt !=null)
		{
		if ((sTxt.trim().length() == 0)) {
			try {
				sTxt = rwe99.getAttribute("value");
				System.err.println("value: " + sTxt);

			} catch (Exception e) {
			}

		}
		}
		if ( sTxt == null) {
			try {
				sTxt = rwe99.getAttribute("value");
				System.err.println("value: " + sTxt);

			} catch (Exception e) {
			}

		}

		return sTxt;
	}

	// @Override
	public boolean isDisplayed() {
		// TODO Auto-generated method stub
		return rwe99.isDisplayed();
	}

	// @Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return rwe99.isEnabled();
	}

	// @Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return rwe99.isSelected();
	}

	// @Override
	public void sendKeys(CharSequence... arg0) {
		// TODO Auto-generated method stub
		this.HighliteOn();
		boolean bNotTyped = true;
		while (bNotTyped) {
			try {
				rwe99.sendKeys(arg0);
				bNotTyped = false;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				if (edWorks.WatchForAnElement(
						By.xpath("//div[@class='fancybox-wrap fancybox-desktop fancybox-type-html fancybox-opened']"),
						.25) != null) {
					edWorks.WaitWhileAnElementIsVisible(
							By.xpath("//div[@class='fancybox-wrap fancybox-desktop fancybox-type-html fancybox-opened']"),
							30);
				} else {
					this.ScrollIntoView();
				}

			}
		}

	}

	// @Override
	public void submit() {
		// TODO Auto-generated method stub
		rwe99.submit();
	}

	// @Override
	public Coordinates getCoordinates() {
		return rwe99.getCoordinates();
	}

	public String GetToolTip()
	{
		this.Hover(0.1);
		String sTip = this.weCastFrom.getAttribute("title");
		if(sTip.trim().length()==0)
		{
			
			sTip = (this.findElement(By.xpath("..")).findElement(By.xpath(".//*[@class='popover fade top in']")).getText());
		}
		System.out.println("Tip: " + sTip);
		return sTip;
		
	}
	public void setText(String sText) {
		this.clear();
		this.sendKeys(sText);
	}

	public void setTextAndExitField(String sText) {
		this.clear();
		this.sendKeys(sText);
		this.sendKeys(Keys.TAB);
	}

	public void setTextAndHitEnter(String sText) {
		this.clear();
		this.sendKeys(sText);
		this.sendKeys(Keys.ENTER);
	}

	public void Hover() {
		actions.moveToElement(this.GetWebElementCastFrom()).build().perform();

	}

	public void Hover(double dSecs) {
		StopWatch sw1 = new StopWatch();
		actions.moveToElement(this.GetWebElementCastFrom()).build().perform();
		
		while(sw1.getElapsedTimeSecs() < dSecs)
		{
		actions.moveToElement(this.GetWebElementCastFrom()).build().perform();
		nom99.NapTime(1);
		}

	}

	public void MoveOff()
	{
		RemoteWebElement rwe = (RemoteWebElement) this.findElement(By.xpath(".."));

		actions.moveToElement(rwe).build().perform();

	}
	public void TypeText(String sText) {
		TypeText(sText, 0.10);

	}

	public void TypeText(String sText, double dDelay) {
		System.out.println("in Type Text");
		for (int iL = 0; iL < sText.length(); iL++) {

			this.sendKeys(sText.substring(iL, (iL + 1)));
			nom99.NapTime(dDelay);
		}
	}


	/**
	 * Determines if the given By locator is present
	 * 
	 * @param byLocator
	 *            - A By locator that represents the desired element
	 * @return - A boolean that states if an element can be found with the By
	 *         locator true - Element Found false - Element Not Found
	 */
	public boolean IsObjectPresent(By byLocator) {
		try {
			gbl99.getExtendedDriver().remoteDriver.manage().timeouts()
					.implicitlyWait(250, TimeUnit.MILLISECONDS);

			if (weCastFrom.findElement(byLocator) != null) {
				return true;
			} else {
				return false;
			}
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Determines if the given ByChained locator is present and visible
	 * 
	 * @param bycLocator
	 *            - A ByChained locator that represents the desired element
	 * @return - A boolean that states if an element can be found with the
	 *         ByChained Locator and is visible true - Element Found and Visible
	 *         false - Element Not Found or Not Visible
	 */
	public boolean IsObjectVisible() {
System.out.println("Start IsObjectVisible");
		gbl99.getExtendedDriver().remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);

		// WebDriver driver = gbl99.getDriver();
		WebElement we99;
		try {
			try {
				we99 = (WebElement) weCastFrom;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				we99 = null;
			}
			if (we99 == null) {
				System.out.println("Element not found");
				System.out.println("Exit IsObjectVisible -- A");
				return false;
			} else {

				System.out.println("Element found:");
				boolean bHidden = false;
				try {
					if (we99.getCssValue("visibility").compareTo("hidden") == 0) {
						bHidden = true;
					}
				} catch (Exception e99) {

				}
				try {
					if (we99.getCssValue("VISIBILITY").compareTo("hidden") == 0) {
						bHidden = true;
					}
				} catch (Exception e99) {

				}

				if ((we99.isDisplayed() && (!bHidden))) {
					System.out.println("Element displayed");
					if ((we99.getLocation().x >= 0)
							&& (we99.getLocation().y >= 0)
							&& (we99.getSize().getHeight() > 0)
							&& (we99.getSize().getWidth() > 0)) {
						System.out.println("Element visible");
						System.out.println("Exit IsObjectVisible -- B");

						return true;
					} else {
						System.out.println("Element not visible");
						System.out.println("Exit IsObjectVisible -- C");
						return false;
					}

				} else {
					System.out.println("Element not displayed");
					System.out.println("Exit IsObjectVisible -- D");
					return false;
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element exception");
			System.out.println("Exit IsObjectVisible -- E");
			return false;
		}
	}


	/**
	 * Determines if the given By locator is present and visible
	 * 
	 * @param bycLocator
	 *            - A By locator that represents the desired element
	 * @return - A boolean that states if an element can be found with the By
	 *         Locator and is visible true - Element Found and Visible false -
	 *         Element Not Found or Not Visible
	 */
	public boolean IsObjectVisible(By byLocator) {
		gbl99.getExtendedDriver().remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);

		try {
			WebElement we99 = (WebElement) weCastFrom.findElement(byLocator);
			if (we99 == null) {
				System.out.println("Element not found:");
				return false;
			} else {
				System.out.println("Element found:");
				boolean bHidden = false;
				try {
					if (we99.getCssValue("visibility").compareTo("hidden") == 0) {
						bHidden = true;
					}
				} catch (Exception e99) {

				}
				try {
					if (we99.getCssValue("VISIBILITY").compareTo("hidden") == 0) {
						bHidden = true;
					}
				} catch (Exception e99) {

				}

				if ((we99.isDisplayed() && (!bHidden))) {
					System.out.println("Element displayed");
					if ((we99.getLocation().x >= 0)
							&& (we99.getLocation().y >= 0)
							&& (we99.getSize().getHeight() > 0)
							&& (we99.getSize().getWidth() > 0)) {
						System.out.println("Element visible");
						return true;
					} else {
						System.out.println("Element not visible");
						return false;
					}
				} else {
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
	 * Waits while an Element with the given locator exists
	 * 
	 * @param bycLocator
	 *            - A By locator for the WebElement that is being watched
	 * @param dWait
	 *            - A double: the number of seconds to wait for the element to
	 *            no longer be there
	 * @return - a boolean to determine the elements existence true - element no
	 *         longer exists false - element still exists
	 */
	public boolean WaitWhileAnElementExists(By byLocator, double dWait) {
		boolean bPass = false;
		// WebDriver driver = gbl99.getDriver();
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

			nom99.NapTime(0.250);
		}
		return false;
	}


	/**
	 * Waits while an Element with the given locator exists and is visible
	 * 
	 * @param bycLocator
	 *            - A By locator for the WebElement that is being watched to
	 *            disappear
	 * @param dWait
	 *            - A double: the number of seconds to wait for the element to
	 *            no longer be there
	 * @return - a boolean to determine the elements existence true - element no
	 *         longer exists false - element still exists
	 */
	public boolean WaitWhileAnElementIsVisible(By byLocator, double dWait) {
		boolean bPass = false;
		// WebDriver driver = gbl99.getDriver();
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

			nom99.NapTime(0.250);
		}
		return false;
	}


	/**
	 * Returns the element found with the locator if it's found within the given
	 * time
	 * 
	 * @param by99
	 *            - A By Locator for the Element being looked for.
	 * @param dWaitSecs
	 *            - A double , the maximum number pof seconds to wait for the
	 *            element
	 * @return - A WebElement
	 */
	public WebElement WatchForAnElement(By by99, double dWaitSecs) {
		WebElement weFnd = null;

		gbl99.getExtendedDriver().remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);

		StopWatch sw99 = new StopWatch();
		// System.out.println(saWinTitles.length);
		// WebDriver driver = gbl99.getDriver();
		sw99.start();
		while (sw99.getElapsedTime() < (dWaitSecs * 1000)) {

			// System.out.println("Waiting: " + lElapsed);
			try {
				// System.out.println("Chn: " + iChs);
				try {
					weFnd = findElement(by99);
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
		// System.out.println(weFnd.getText());
		return weFnd;
	}


	/**
	 * Determines if the at least one of the given By/ByChained locator is
	 * present within the given time
	 * 
	 * @param alLocators
	 *            - An ArrayList of By/ByChained Locators that represents one of
	 *            the desired element
	 * @param dWait
	 *            - A double that represents the number of seconds to wait for
	 *            at least one of the By/ByChained locators to be found
	 * @return - A WebElement that is the first WebElement that is found with
	 *         one of the By/ByChained Locators , within the time given true -
	 *         An Element Found false - An Element Not Found or Not Visible
	 */
	public WebElement WatchForElement(ArrayList alLocators, double dWait) {
		boolean bPass = false;
		long lElapsed = 0;
		gbl99.getExtendedDriver().remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);

		StopWatch sw99 = new StopWatch();
		// System.out.println(saWinTitles.length);
		// WebDriver driver = gbl99.getDriver();
		sw99.start();
		while (sw99.getElapsedTime() < (dWait * 1000)) {

			for (int iX = 0; iX < alLocators.size(); iX++) {

				System.err.println("Loc class: "
						+ alLocators.get(iX).getClass().getName());
				String sType = alLocators.get(iX).getClass().getName().trim();

				if (sType.indexOf("By") < 0) {
					System.out.println("Look for an element");
					WebElement we99 = (WebElement) alLocators.get(iX);

					if (we99 != null) {
						bPass = true;
						System.err.println("Found it");
						return we99;
					}
				} 
			}

			nom99.NapTime(0.250);
		}
		return null;
	}


//	public void GetAllAttributeNames()
//	{
//		  ((JavascriptExecutor) gbl99.getExtendedDriver().remoteDriver).executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", this.weCastFrom);
//
//	}
	
	
	public void HighliteOn() {
		try {
			gbl99.getExtendedDriver().remoteDriver.executeScript(
					"arguments[0].style.border='3px solid red'", this.weCastFrom);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void HighliteOn(String sLineType , String sColor) {
		try {
			gbl99.getExtendedDriver().remoteDriver.executeScript(
					"arguments[0].style.border='3px " + sLineType.toLowerCase() + " " + sColor.toLowerCase() +"'", this.weCastFrom);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void HighliteOff() {
		try {
			gbl99.getExtendedDriver().remoteDriver.executeScript(
					"arguments[0].style.border='0px solid red'", this.weCastFrom);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Flash(int iTimes) {
		
		
		try {
			for (int iY = 0; iY < iTimes; iY++) {
				gbl99.getExtendedDriver().remoteDriver.executeScript(
						"arguments[0].style.border='3px solid red'",
						this.weCastFrom);
				nom99.NapTime(0.25);
				gbl99.getExtendedDriver().remoteDriver.executeScript(
						"arguments[0].style.border='3px dashed blue'",
						this.weCastFrom);
				nom99.NapTime(0.25);
			}
			gbl99.getExtendedDriver().remoteDriver.executeScript(
					"arguments[0].style.border='0px solid red'",
					this.weCastFrom);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ScrollIntoView() {
			if(gbl99.getExtendedDriver().remoteDriver.getCapabilities().getBrowserName().toLowerCase().indexOf("firefox") <0)
			{
			actions.moveToElement(this.GetWebElementCastFrom()).build().perform();
			}
			else
			{
				((JavascriptExecutor) gbl99.getExtendedDriver().remoteDriver).executeScript("arguments[0].scrollIntoView(true);", this.GetWebElementCastFrom());
			}
				

//		}

	}
	
	 public void ScrollToBottomOf(ExtendedWebElement ewe) {

         int iYPos = this.getLocation().getY();

         int iYSize = gbl99.getExtendedDriver().remoteDriver.manage().window().getSize().getHeight();

         int iYStart = ewe.getLocation().getY() + ewe.getSize().height;

         int iPx = Math.abs(iYStart - this.getLocation().getY());

         if (this.getLocation().getY() < iYStart) {

                         this.ScrollDownVertical(iPx);

         } else {

                         this.ScrollUpVertical(iPx);



         }

}

	 public void ScrollToTopOf(ExtendedWebElement ewe) {

         int iYPos = this.getLocation().getY();

         
         int iYSize = gbl99.getExtendedDriver().remoteDriver.manage().window().getSize().getHeight();

         int iYStart = ewe.getLocation().getY();

         int iPx = Math.abs(iYStart - (this.getLocation().getY() + this.getSize().getHeight()) );

         if ((this.getLocation().getY() + this.getSize().getHeight()) < iYStart) {

                         this.ScrollDownVertical(iPx);

         } else {

                         this.ScrollUpVertical(iPx);



         }

}



public void ScrollTopToCenterVertical() {



         int iScnX = gbl99.getExtendedDriver().remoteDriver.manage().window().getSize().getWidth();

         int iScnY = gbl99.getExtendedDriver().remoteDriver.manage().window().getSize().getHeight();

         int iScnMidVert = iScnY / 2;

         int iLocY = this.getLocation().getY();

         System.out.println("X loc: " + this.getLocation().getX());

         System.out.println("Y loc: " + this.getLocation().getY());

         while (this.getLocation().getY() < iScnMidVert) {

                         JavascriptExecutor js = (JavascriptExecutor) gbl99.getExtendedDriver().remoteDriver;

                         js.executeScript("window.scrollBy(0,50)");

                         System.out.println("X loc2: " + this.getLocation().getX());

                         System.out.println("Y loc2: " + this.getLocation().getY());

                         if (iLocY == this.getLocation().getY())

                                         break;

                         iLocY = this.getLocation().getY();

         }



         while (this.getLocation().getY() > iScnMidVert) {

                         JavascriptExecutor js = (JavascriptExecutor) gbl99.getExtendedDriver().remoteDriver;

                         js.executeScript("window.scrollBy(0,-50)");

                         System.out.println("X loc2: " + this.getLocation().getX());

                         System.out.println("Y loc2: " + this.getLocation().getY());

                         if (iLocY == this.getLocation().getY())

                                         break;

                         iLocY = this.getLocation().getY();

         }



//}



}



public void ScrollUpVertical() {



         int iScnX = gbl99.getExtendedDriver().remoteDriver.manage().window().getSize().getWidth();

         int iScnY = gbl99.getExtendedDriver().remoteDriver.manage().window().getSize().getHeight();

         System.out.println("X loc1: " + this.getLocation().getX());

         System.out.println("Y loc1: " + this.getLocation().getY());



         JavascriptExecutor js = (JavascriptExecutor) gbl99.getExtendedDriver().remoteDriver;

         js.executeScript("window.scrollBy(0,1)");

         System.out.println("X loc2: " + this.getLocation().getX());

         System.out.println("Y loc2: " + this.getLocation().getY());



}



public void ScrollDownVertical() {



         int iScnX = gbl99.getExtendedDriver().remoteDriver.manage().window().getSize().getWidth();

         int iScnY = gbl99.getExtendedDriver().remoteDriver.manage().window().getSize().getHeight();

         System.out.println("X loc1: " + this.getLocation().getX());

         System.out.println("Y loc1: " + this.getLocation().getY());



         JavascriptExecutor js = (JavascriptExecutor) gbl99.getExtendedDriver().remoteDriver;

         js.executeScript("window.scrollBy(0,-1)");

         System.out.println("X loc2: " + this.getLocation().getX());

         System.out.println("Y loc2: " + this.getLocation().getY());

}



public void ScrollUpVertical(int iPx) {



         int iScnX = gbl99.getExtendedDriver().remoteDriver.manage().window().getSize().getWidth();

         int iScnY = gbl99.getExtendedDriver().remoteDriver.manage().window().getSize().getHeight();

         System.out.println("X loc1: " + this.getLocation().getX());

         System.out.println("Y loc1: " + this.getLocation().getY());

         JavascriptExecutor js = (JavascriptExecutor) gbl99.getExtendedDriver().remoteDriver;



         String sJS = "window.scrollBy(0," + iPx + ")";

         js.executeScript(sJS);

         /*

         * if(iPx > 50) { int i8 = iPx /10; iPx = iPx - (i8 * 50); System.out.println(i8

         * + " x 10 " + iPx);

         *

          * for(int i9=0; i9<i8; i9++) { System.out.println("Scroll " + i9);

         * js.executeScript("window.scrollBy(0,10)");

         *

          * } }

         */



}



public void ScrollDownVertical(int iPx) {



         int iScnX = gbl99.getExtendedDriver().remoteDriver.manage().window().getSize().getWidth();

         int iScnY = gbl99.getExtendedDriver().remoteDriver.manage().window().getSize().getHeight();

         System.out.println("X loc1: " + this.getLocation().getX());

         System.out.println("Y loc1: " + this.getLocation().getY());



         {

                         iPx = iPx * -1;

                         JavascriptExecutor js = (JavascriptExecutor) gbl99.getExtendedDriver().remoteDriver;

                         String sJS = "window.scrollBy(0," + iPx + ")";

                         js.executeScript(sJS);



                         System.out.println("X loc2: " + this.getLocation().getX());

                         System.out.println("Y loc2: " + this.getLocation().getY());

         }



}


	/**
	 * Returns the element found with the locator if it's found within the given time 
	 * @param by99 - A By Locator for the Element being looked for.
	 * @param dWaitSecs - A double , the maximum number pof seconds to wait for the element
	 * @return - A WebElement 
	 */
	public WebElement WatchForAVisibleElement(By by99, double dWaitSecs) {
		WebElement weFnd = null;
 
		gbl99.getExtendedDriver().remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);

		StopWatch sw99 = new StopWatch();
		// System.out.println(saWinTitles.length);
		RemoteWebDriver driver = gbl99.getExtendedDriver().remoteDriver;
		sw99.start();
		while (sw99.getElapsedTime() < (dWaitSecs * 1000)) {

			// System.out.println("Waiting: " + lElapsed);
			try {
				// System.out.println("Chn: " + iChs);
				try {
					List<WebElement> lw88 = findElements(by99);
					for (int iW=0; iW<lw88.size(); iW++)
					{
						ExtendedWebElement ewe = new ExtendedWebElement((RemoteWebElement) lw88.get(iW));
						if(ewe.IsObjectVisible())
						{
							weFnd = lw88.get(iW);
							break;
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



    //**********************************************************************************
	
	/**
	 * Returns the element found with the locator if it's found within the given time 
	 * @param by99 - A By Locator for the Element being looked for.
	 * @param dWaitSecs - A double , the maximum number pof seconds to wait for the element
	 * @return - A WebElement 
	 */
	public ExtendedWebElement WatchForAVisibleExtendedElement(By by99, double dWaitSecs) {
		WebElement weFnd = null;

		gbl99.getExtendedDriver().remoteDriver.manage().timeouts()
				.implicitlyWait(250, TimeUnit.MILLISECONDS);

		StopWatch sw99 = new StopWatch();
		// System.out.println(saWinTitles.length);
		RemoteWebDriver driver = gbl99.getExtendedDriver().remoteDriver;
		sw99.start();
		while (sw99.getElapsedTime() < (dWaitSecs * 1000)) {

			// System.out.println("Waiting: " + lElapsed);
			try {
				// System.out.println("Chn: " + iChs);
				try {
					List<WebElement> lw88 = findElements(by99);
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


	
public BooleanMessage DragTo(ExtendedWebElement ewe)
{
    
  //Dragged and dropped.
    if(!this.IsObjectVisible()) return new BooleanMessage(false,"Element to be dragged not on screen");
    if(!ewe.IsObjectVisible()) return new BooleanMessage(false,"Target Element to be dragged to not on screen");

    try {
		actions.dragAndDrop(this.weCastFrom, ewe.weCastFrom).build().perform();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		return new BooleanMessage(false,"Drag failed " + e.getMessage());
	}		

    return new BooleanMessage(true,"It worked");
    
}

private String RGBAtoHEX(String sRGBA)
{
	String[] numbers = sRGBA.replace("rgba(", "").replace(")", "").split(",");
	int r = Integer.parseInt(numbers[0].trim());
	int g = Integer.parseInt(numbers[1].trim());
	int b = Integer.parseInt(numbers[2].trim());
	int a = Integer.parseInt(numbers[3].trim());
	System.out.println("r: " + r + " g: " + g + " b: " + b + " a:" + a);
	String hexR = Integer.toHexString(r);
	if(r<16) hexR = "0" + hexR;
	String hexG = Integer.toHexString(g);
	if(g<16) hexG = "0" + hexG;
	String hexB = Integer.toHexString(b);
	if(b<16) hexB = "0" + hexB;

	String hex = "#" + hexR + hexG + hexB; //+ Integer.toHexString(a);
	System.out.println(hex);
	return hex;
}

}