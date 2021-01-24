package com.companyname.automation.webdriverextensions;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.Select;

import com.companyname.automation.commontools.Globals;
import com.companyname.automation.commontools.NonObjectMethods;




public class SelectList implements WebElement {
	RemoteWebElement weChild;
	public	Select selector;
	NonObjectMethods nom99 = new NonObjectMethods();
	Globals gbl99 = new Globals();
	public SelectList(RemoteWebElement lstbox) {
		super();
		weChild = lstbox;
		selector = new Select(lstbox);
		
	}

	/**
	 * This Method returns the WebElement that the SelectList is based on
	 * 
	 * @return A WebElement that is the SelectList
	 */
	public WebElement GetWebElementCastFrom()
	{
		return weChild;
	}

/*	public boolean SelectByStringA(String sSelect)
	{
		String sTagName = weChild.getTagName();
		// // System.out.println("TagName: " + sTagName);
		new ExtendedWebElement(this.weChild).click();
//		selector.selectByVisibleText(sSelect);
//		List<WebElement> lwOptions = selector.getOptions();
		boolean bWt = true;
//		if (bWt) return true;
		while(bWt)
		{
			try
			{
				weChild = (RemoteWebElement) gbl99.getrDriver().findElement(By.tagName(sTagName));
				while(!ee9.IsObjectVisible(weChild))
				{
					nom99.NapTime(0.5);
				}
				// // System.out.println("Element is there");
				selector = new Select(weChild);
				List<WebElement> lwe99 =selector.getAllSelectedOptions();
				bWt=false;
				break;
			}
			catch(Exception e99)
			{
				
			}
		}
		
		List<WebElement> lwe99X =selector.getOptions();
		for(int iL=0; iL<=lwe99X.size(); iL++)
		{
			try {
				ExtendedWebElement ewe99 = new ExtendedWebElement((RemoteWebElement) lwe99X.get(iL));
				ewe99.click();
				System.err.println("Click: " + ewe99.getText());
				if(ewe99.getText().compareToIgnoreCase(sSelect)==0)
				{
					System.err.println("ClickX: " + ewe99.getText());
				//	ewe99.HighliteOn();
				//	nom99.NapTime(5);
//					ewe99.click();
//			this.weChild.click();
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		List<WebElement> lwe99 =selector.getAllSelectedOptions();

		for(int iL=0; iL<=lwe99.size(); iL++)
		{
			try {
				ExtendedWebElement ewe99 = new ExtendedWebElement((RemoteWebElement) lwe99.get(iL));
				if(ewe99.getText().compareTo(sSelect)==0)
				{
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			}
		}
		return false;
	}
*/
	public boolean SelectByString(String sSelect)
	{
		boolean bRtn = false;
		List<WebElement> lweOptions = this.getOptions();
//		for(int i=0; i<lweClients.size(); i++)
//		{
//			// // System.err.println(lweClients.get(i).getText());
//		}
		
//		ect88.getSlClientNameSL(1).click();
		for(int i=0; i<lweOptions.size(); i++)
		{
			ExtendedWebElement ewe99 = new ExtendedWebElement((RemoteWebElement) lweOptions.get(i));

			String sName = ewe99.getText();
			 System.err.println(sName);
			if(sName.compareToIgnoreCase(sSelect)==0)
			{
				this.SelectByIndex(i);
				
				return true;
				
			}
		}

		return false;
	}

/*	public boolean SelectByString2(String sSelect)
	{
		boolean bRtn = false;
		List<WebElement> lweOptions = this.getOptions();
//		for(int i=0; i<lweClients.size(); i++)
//		{
//			// // System.err.println(lweClients.get(i).getText());
//		}
		
//		ect88.getSlClientNameSL(1).click();
		for(int i=0; i<lweOptions.size(); i++)
		{
			ExtendedWebElement ewe99 = new ExtendedWebElement((RemoteWebElement) lweOptions.get(i));

			String sName = ewe99.getText();
			// // System.err.println(sName);
			if(sName.compareToIgnoreCase(sSelect)==0)
			{
			//	this.sendKeys(Keys.ENTER);
				this.sendKeys(Keys.ARROW_DOWN);
				
				return true;
				
			}
			this.sendKeys(Keys.ARROW_DOWN);

//			nom99.NapTime(1);
		}

		return false;
	}
*/
	public boolean SelectByIndex(int iIndx)
	{
		if(iIndx < 0)
		{
			return false;
		}
		
		if(iIndx >= selector.getOptions().size())
		{
			return false;
		}
		selector.selectByIndex(iIndx);
		return true;
	}

	public void deselectAll()
	{
		selector.deselectAll();
	}
	
	public void deselectByIndex(int index)
	{
		selector.deselectByIndex(index);
	}
	
	public void deselectByValue(String sValue)
	{
		selector.deselectByValue(sValue);
	}

	public void deselectByVisibleText(String sText)
	{
		selector.deselectByVisibleText(sText);
	}
	
	public List<WebElement> getAllSelectedOptions()
	{
		return selector.getAllSelectedOptions();
	}
	
	public WebElement getFirstSelectedOption()
	{
		return selector.getFirstSelectedOption();
	}
	
	public List<WebElement> getOptions()
	{
		return selector.getOptions();
	}

	public boolean isMultiple()
	{
		return selector.isMultiple();
	}


	
	public void clear() {
		weChild.clear();

	}

	
	public void click() {
		weChild.click();

	}

	
	public WebElement findElement(By arg0) {
		// TODO Auto-generated method stub
		return weChild.findElement(arg0);
	}

	
	public List<WebElement> findElements(By arg0) {
		// TODO Auto-generated method stub
		return weChild.findElements(arg0);
	}

	
	public String getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return weChild.getAttribute(arg0);
	}

	
	public String getCssValue(String arg0) {
		// TODO Auto-generated method stub
		return weChild.getCssValue(arg0);
	}


	public Point getLocation() {
		// TODO Auto-generated method stub
		return weChild.getLocation();
	}


	public Dimension getSize() {
		// TODO Auto-generated method stub
		return weChild.getSize();
	}

	
	public String getTagName() {
		// TODO Auto-generated method stub
		return weChild.getTagName();
	}

	
	public String getText() {
		// TODO Auto-generated method stub
		return weChild.getText();
	}

	
	public boolean isDisplayed() {
		// TODO Auto-generated method stub
		return weChild.isDisplayed();
	}

	
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return weChild.isEnabled();
	}

	
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return weChild.isSelected();
	}

	
	public void sendKeys(CharSequence... arg0) {
		// TODO Auto-generated method stub
		weChild.sendKeys(arg0);

	}

	
	public void submit() {
		// TODO Auto-generated method stub
		weChild.submit();
	}

	
	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}

}
