package com.companyname.automation.uiobjectmodels;

import org.openqa.selenium.By;

import com.companyname.automation.commontools.BooleanMessage;
import com.companyname.automation.commontools.Event;
import com.companyname.automation.commontools.Globals;
import com.companyname.automation.webdriverextensions.ExtendedWebElement;




public class SiteHeader {
	private ExtendedWebElement eweSiteHeaderBar = null;
	private ExtendedWebElement eweShopTextBtn = null;
	private ExtendedWebElement eweGetInvolvedTextBtn = null;
	private ExtendedWebElement eweAboutTextBtn = null;
	private ExtendedWebElement eweOurImpactTextBtn = null;
	private ExtendedWebElement ewePoundPlusTextBtn = null;
	private ExtendedWebElement eweUpcyclingTextBtn = null;
	private ExtendedWebElement eweJoinTheClubTextBtn = null;
//	ProductCollectionPage pcpOne = new ProductCollectionPage();
	Globals gbl99 = new Globals();
	ObjectModelHelperMethods omhmTool = new ObjectModelHelperMethods();
	// Site Header Bar
	public By GetBycSiteHeaderBar()
	{
		return By.xpath("//div[@class='header-inner-container main-container']");
	}

	public ExtendedWebElement GetEweSiteHeaderBar(double dTime)
	{
	    return omhmTool.GetEweObject(eweSiteHeaderBar,this.GetBycSiteHeaderBar(),dTime);
	}
	
	public ExtendedWebElement GetEweSiteHeaderBar()
	{
		return GetEweSiteHeaderBar(15);
	}


	
	// Shop Text Button
	public By GetBycShopTextBtn()
	{
		return By.xpath("//span[contains(text(),'Shop')]");
//		return By.linkText("Shop"));
	}

	public ExtendedWebElement GetEweShopTextBtn(double dTime)
	{
	    return omhmTool.GetEweObject(eweShopTextBtn,this.GetBycShopTextBtn(),dTime);

	}
	
	public ExtendedWebElement GetEweShopTextBtn()
	{
		return GetEweShopTextBtn(15);
	}


	// GetInvolved Text Button
	public By GetBycGetInvolvedTextBtn()
	{
		return By.xpath("//span[contains(text(),'Get Involved')]");
//		return By.linkText("GetInvolved"));
	}

	public ExtendedWebElement GetEweGetInvolvedTextBtn(double dTime)
	{
	    return omhmTool.GetEweObject(eweGetInvolvedTextBtn,this.GetBycGetInvolvedTextBtn(),dTime);
	}
	
	public ExtendedWebElement GetEweGetInvolvedTextBtn()
	{
		return GetEweGetInvolvedTextBtn(15);
	}

	// About Text Button
	public By GetBycAboutTextBtn()
	{
		return By.xpath("//span[contains(text(),'Gift Guide')]");
//		return By.linkText("About"));
	}

	public ExtendedWebElement GetEweAboutTextBtn(double dTime)
	{
	    return omhmTool.GetEweObject(eweAboutTextBtn,this.GetBycAboutTextBtn(),dTime);
	}
	
	public ExtendedWebElement GetEweAboutTextBtn()
	{
		return GetEweAboutTextBtn(15);
	}

	// PoundPlus Text Button
	public By GetBycPoundPlusTextBtn()
	{
		return By.xpath("//span[contains(text(),'Pound+')]");
//		return By.linkText("PoundPlus"));
	}

	public ExtendedWebElement GetEwePoundPlusTextBtn(double dTime)
	{
	    return omhmTool.GetEweObject(ewePoundPlusTextBtn,this.GetBycPoundPlusTextBtn(),dTime);
	}
	
	public ExtendedWebElement GetEwePoundPlusTextBtn()
	{
		return GetEwePoundPlusTextBtn(15);
	}


	// Upcycling Text Button
	public By GetBycUpcyclingTextBtn()
	{
		return By.xpath("//span[contains(text(),'Upcycling')]");
//		return By.linkText("Upcycling"));
	}

	public ExtendedWebElement GetEweUpcyclingTextBtn(double dTime)
	{
	    return omhmTool.GetEweObject(eweUpcyclingTextBtn,this.GetBycUpcyclingTextBtn(),dTime);
	}
	
	public ExtendedWebElement GetEweUpcyclingTextBtn()
	{
		return GetEweUpcyclingTextBtn(15);
	}


	// GetInvolved Text Button
	public By GetBycGetOurImpactTextBtn()
	{
		return By.xpath("//span[contains(text(),'Our Impact')]");
//		return By.linkText("GetInvolved"));
	}

	public ExtendedWebElement GetEweOurImpactTextBtn(double dTime)
	{
	    return omhmTool.GetEweObject(eweOurImpactTextBtn,this.GetBycGetOurImpactTextBtn(),dTime);
	}
	
	public ExtendedWebElement GetEweOurImpactTextBtn()
	{
		return GetEweOurImpactTextBtn(15);
	}

	
	public By GetBycJoinTheClubTextBtn()
	{
		
		return By.xpath("//span[contains(text(),'Join the Club')]");
	}

	public ExtendedWebElement GetEweJoinTheClubTextBtn(double dTime)
	{
	    return omhmTool.GetEweObject(eweJoinTheClubTextBtn,this.GetBycJoinTheClubTextBtn(),dTime);
	}
	
	public ExtendedWebElement GetEweJoinTheClubTextBtn()
	{
		return GetEweJoinTheClubTextBtn(15);
	}

	public BooleanMessage StartShopping()
	{
		BooleanMessage bmRtn = new BooleanMessage(false,"Shop All page did not open");
		Event evStartShopping = new Event("Start Shopping (Shop All) from Site Header Link",15);
		GetEweShopTextBtn().HighliteOn("GREEN","DASHED");
			
		evStartShopping.Start();
		GetEweShopTextBtn().click();
		evStartShopping.Stop();
		try {
			gbl99.getHtmleventslogging().LogTestEvent(evStartShopping);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		System.out.println("Shop all URL: " + gbl99.getExtendedDriver().GetCurrentURL());
		if(gbl99.getExtendedDriver().GetCurrentURL().compareToIgnoreCase("https://www.4ocean.com/collections/shop-all")==0)
		{

			bmRtn = new BooleanMessage(true,"Shop All page did open");
			if(evStartShopping.getdMaxTime()< evStartShopping.getdElapsedTime())
			{
				try {
					gbl99.getHtmlResultsLog().LogTestWarningWithScreenCapture("Shop All page did not open within " + evStartShopping.getdMaxTime() + "seconds. It took " + evStartShopping.getdElapsedTime() + " seconds" );
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				}
			}
			else
			{
				try {
					gbl99.getHtmlResultsLog().LogTestPassWithScreenCapture("Shop All page opened within " + evStartShopping.getdMaxTime() + "seconds. It took " + evStartShopping.getdElapsedTime() + " seconds" );
				} catch (Exception e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				}

			}
		}
		else
		{
			try {
				gbl99.getHtmlResultsLog().LogTestFailureWithScreenCapture("Shop All page did not open " );
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			}

		}

		return  bmRtn;
	}

}
