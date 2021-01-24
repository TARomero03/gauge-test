package com.companyname.automation.uiobjectmodels;

import org.openqa.selenium.By;

import com.companyname.automation.commontools.BooleanMessage;
import com.companyname.automation.commontools.Globals;
import com.companyname.automation.commontools.StopWatch;
import com.companyname.automation.webdriverextensions.ExtendedWebElement;



public class JoinTheMovement {

	private ExtendedWebElement eweCloseImgBtn = null;
	private ExtendedWebElement eweEntirePopUpDiv = null;
	private ExtendedWebElement eweBtnKeepMePosted = null;
	private ExtendedWebElement eweIbEmail = null;
	private ExtendedWebElement eweImgLogo = null;
	private ExtendedWebElement eweTbTitle = null;
	private ExtendedWebElement eweTbMessage = null;
	
	ProductCollectionPage pcpOne = new ProductCollectionPage();
	Globals gbl99 = new Globals();
	
	public By GetBycEntirePopUpDiv()
	{
		return By.xpath("\r\n" + 
				"//div[@class='privy-popup-content privy-foreground-element']");
	}

	public ExtendedWebElement GetEweEntirePopUpDiv(double dTime)
	{
		boolean bThere = false;
		try {
			if(eweEntirePopUpDiv != null)
			{
				if(eweEntirePopUpDiv.isEnabled())
				{
					if(eweEntirePopUpDiv.IsObjectVisible())
					{
						bThere = true;
					}
							
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			bThere = false;
		}
		
		if(!bThere)
		{
			eweEntirePopUpDiv = gbl99.getExtendedDriver().WatchForAVisibleExtendedElement(this.GetBycEntirePopUpDiv(), dTime);
		}
		return eweEntirePopUpDiv; 

	}
	
	public ExtendedWebElement GetEweEntirePopUpDiv()
	{
		return GetEweEntirePopUpDiv(15);
	}

	
	
	public By GetBycCloseImgBtn()
	{
		return By.id("closeIconSvg");
	}
	public By GetByCloseImgBtn()
	{
		return (By.id("closeIconSvg"));
	}

	public ExtendedWebElement GetEweCloseImgBtn(double dTime)
	{
		/*
		boolean bThere = false;
		try {
			if(eweCloseImgBtn != null)
			{
				if(eweCloseImgBtn.isEnabled())
				{
					if(eweCloseImgBtn.IsObjectVisible())
					{
						bThere = true;
					}
							
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			bThere = false;
		}
		
		if(!bThere)
		{
			eweCloseImgBtn = gbl99.getExtendedDriver().WatchForAVisibleExtendedElement(this.GetBycCloseImgBtn(), dTime);
		}
		
		*/
		gbl99.getExtendedDriver().remoteDriver.switchTo().defaultContent();

		StopWatch sw = new StopWatch();
		sw.start();
		ExtendedWebElement eweFrame = gbl99.getExtendedDriver().GetExtendedWebElement(By.xpath("//iframe[@id='attentive_creative']"), 30);
		if(eweFrame == null)
			{System.out.println("Frame not there");
			}
		else
		{
			System.out.println("Frame still there");
			}
	BooleanMessage bm = gbl99.getExtendedDriver().SwitchToFrame(By.xpath("//iframe[@id='attentive_creative']"));
	System.out.println("Frane Switch: " + bm.isbSuccess() + " -- " + bm.getsMessage());

		while(sw.getElapsedTimeSecs() < dTime)
		{
		eweCloseImgBtn = gbl99.getExtendedDriver().GetExtendedWebElement(this.GetByCloseImgBtn());
		if(eweCloseImgBtn != null) break;
		}
		System.out.println("Close X found");
		eweCloseImgBtn.HighliteOn("Dashed", "Red");
		return eweCloseImgBtn; 

	}
	
	public ExtendedWebElement GetEweCloseImgBtn()
	{
		return GetEweCloseImgBtn(15);
	}

	public By GetBycBtnKeepMePosted()
	{
		return By.cssSelector("body.template-index-the-4ocean-bracelet-every-purchase-pulls-a-pound-of-plastic-from-our:nth-child(2) > script:nth-child(35)");
	}

	public ExtendedWebElement GetEweBtnKeepMePosted(double dTime)
	{
		boolean bThere = false;
		try {
			if(eweBtnKeepMePosted != null)
			{
				if(eweBtnKeepMePosted.isEnabled())
				{
					if(eweBtnKeepMePosted.IsObjectVisible())
					{
						bThere = true;
					}
							
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			bThere = false;
		}
		
		if(!bThere)
		{
			eweBtnKeepMePosted = gbl99.getExtendedDriver().WatchForAVisibleExtendedElement(this.GetBycBtnKeepMePosted(), dTime);
		}
		return eweBtnKeepMePosted; 

	}
	
	public ExtendedWebElement GetEweBtnKeepMePosted()
	{
		return GetEweBtnKeepMePosted(15);
	}

	public By GetBycIbEmail()
	{
		return By.xpath("//input[@placeholder='Email']");
	}

	public ExtendedWebElement GetEweIbEmail(double dTime)
	{
		boolean bThere = false;
		try {
			if(eweIbEmail != null)
			{
				if(eweIbEmail.isEnabled())
				{
					if(eweIbEmail.IsObjectVisible())
					{
						bThere = true;
					}
							
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			bThere = false;
		}
		
		if(!bThere)
		{
			eweIbEmail = gbl99.getExtendedDriver().WatchForAVisibleExtendedElement(this.GetBycIbEmail(), dTime);
		}
		return eweIbEmail; 

	}
	
	public ExtendedWebElement GetEweIbEmail()
	{
		return GetEweIbEmail(15);
	}


	public By GetBycImgLogo()
	{
		return By.xpath("//img[@class='privy-element privy-image-element']");
	}

	public ExtendedWebElement GetEweImgLogo(double dTime)
	{
		boolean bThere = false;
		try {
			if(eweImgLogo != null)
			{
				if(eweImgLogo.isEnabled())
				{
					if(eweImgLogo.IsObjectVisible())
					{
						bThere = true;
					}
							
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			bThere = false;
		}
		
		if(!bThere)
		{
			eweImgLogo = gbl99.getExtendedDriver().WatchForAVisibleExtendedElement(this.GetBycImgLogo(), dTime);
		}
		return eweImgLogo; 

	}
	
	public ExtendedWebElement GetEweImgLogo()
	{
		return GetEweImgLogo(15);
	}


	public By GetBycTbTitle()
	{
		return By.xpath("//body/div[@id='privy-container']/div[@id='privy-inner-container']/div[@class='privy privy-popup-container']/div[@class='privy-popup-content-wrap']/div[@class='privy-popup-inner-content-wrap']/div[@class='privy-popup-content privy-foreground-element']/div[3]/div[1]/div[1]");
	}

	public ExtendedWebElement GetEweTbTitle(double dTime)
	{
		boolean bThere = false;
		try {
			if(eweTbTitle != null)
			{
				if(eweTbTitle.isEnabled())
				{
					if(eweTbTitle.IsObjectVisible())
					{
						bThere = true;
					}
							
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			bThere = false;
		}
		
		if(!bThere)
		{
			eweTbTitle = gbl99.getExtendedDriver().WatchForAVisibleExtendedElement(this.GetBycTbTitle(), dTime);
		}
		return eweTbTitle; 

	}
	
	public ExtendedWebElement GetEweTbTitle()
	{
		return GetEweTbTitle(15);
	}


	public By GetBycTbMessage()
	{
		return By.xpath("//body/div[@id='privy-container']/div[@id='privy-inner-container']/div[@class='privy privy-popup-container']/div[@class='privy-popup-content-wrap']/div[@class='privy-popup-inner-content-wrap']/div[@class='privy-popup-content privy-foreground-element']/div[2]/div[1]");
	}

	public ExtendedWebElement GetEweTbMessage(double dTime)
	{
		boolean bThere = false;
		try {
			if(eweTbMessage != null)
			{
				if(eweTbMessage.isEnabled())
				{
					if(eweTbMessage.IsObjectVisible())
					{
						bThere = true;
					}
							
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			bThere = false;
		}
		
		if(!bThere)
		{
			eweTbMessage = gbl99.getExtendedDriver().WatchForAVisibleExtendedElement(this.GetBycTbMessage(), dTime);
		}
		return eweTbMessage; 

	}
	
	public ExtendedWebElement GetEweTbMessage()
	{
		return GetEweTbMessage(15);
	}


	
	public void CloseIt()
	{
		System.out.println("Start Close it: " +gbl99.getExtendedDriver().GetCurrentURL());
		ExtendedWebElement ewePop = gbl99.getExtendedDriver().GetExtendedWebElement(By.xpath("//div[@id='attentive_overlay']"), 30);
		if(ewePop != null)
		{
			System.out.println("PopUp Found");
		}
		else
		{
			System.out.println("No Popup");
			return;
		}

		ExtendedWebElement eweFrame = gbl99.getExtendedDriver().GetExtendedWebElement(By.xpath("//iframe[@id='attentive_creative']"), 30);
		if(eweFrame != null)
		{
			System.out.println("eweFrame Found");
		}
		else
		{
			System.out.println("No eweFrame");
			return;
		}
		

		
//		gbl99.getExtendedDriver().SwitchToFrame(0);
		gbl99.getExtendedDriver().remoteDriver.switchTo().defaultContent();
//		gbl99.getExtendedDriver().remoteDriver.switchTo().frame(0);
		if(this.GetEweCloseImgBtn(15) == null) return;
		System.out.println("Flash close btn");
		this.GetEweCloseImgBtn(15).Flash(5);
		gbl99.getExtendedDriver().remoteDriver.switchTo().defaultContent();
		System.out.println("Highlight close btn");
		this.GetEweCloseImgBtn(15).HighliteOn("dashed", "green");
		gbl99.getExtendedDriver().remoteDriver.switchTo().defaultContent();
		System.out.println("Click the close btn");
		this.GetEweCloseImgBtn().click();
		gbl99.getExtendedDriver().remoteDriver.switchTo().defaultContent();

	}
}
