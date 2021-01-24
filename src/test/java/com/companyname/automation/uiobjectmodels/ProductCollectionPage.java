package com.companyname.automation.uiobjectmodels;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import com.companyname.automation.commontools.BooleanMessage;
import com.companyname.automation.commontools.Event;
import com.companyname.automation.commontools.Globals;
import com.companyname.automation.commontools.NonObjectMethods;
import com.companyname.automation.webdriverextensions.ExtendedWebElement;



public class ProductCollectionPage {

	Globals gbl99 = new Globals();
	com.companyname.automation.uiobjectmodels.SiteHeader shOne = new SiteHeader();
	NonObjectMethods nom88 = new NonObjectMethods();
	public By GetBycProductList()
	{
		return By.className("collection-products-outer-container");
	}

	public ExtendedWebElement GetEweProductList(double dTime)
	{
		return gbl99.getExtendedDriver().WatchForAVisibleExtendedElement(this.GetBycProductList(), dTime);
	}
	
	public ExtendedWebElement GetEweProductList()
	{
		return GetEweProductList(15);
	}
	
	

	public Item GetItemByNameX(String sProductName)
	{
		ExtendedWebElement ewe;
		try {
			ewe = this.GetEweProductList().GetExtendedWebElement(By.xpath("//a[contains(text(),'" + sProductName + "')]"),1).GetParentWebElement().GetParentWebElement();
			ewe.ScrollToBottomOf(shOne.GetEweSiteHeaderBar());
			ewe.Flash(5);
			ewe.HighliteOn();
			Item itm = new Item(ewe);
			
			  if(itm.getEweItemBadge()!=null) { try {
			  itm.getEweItemBadge().ScrollToBottomOf(shOne.GetEweSiteHeaderBar()); } catch
			  (Exception e) { // TODO Auto-generated catch block
			  
			  } } else {
			  try {
				itm.getEweItemImage().ScrollToBottomOf(shOne.GetEweSiteHeaderBar());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			  
			  }
			 			return itm;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		
	}
	
	public Item GetItemByName(String sProductName)
	{
		Item itmFound = null;
		int iTry =0;
		while(GetEweProductList(5)== null)
		{
			gbl99.getExtendedDriver().remoteDriver.navigate().refresh();
			if(iTry++ > 12) break;
		}
		ExtendedWebElement ewe99 = GetEweProductList(1);
		if(ewe99 == null)
		{
			gbl99.getHtmlResultsLog().LogTestFailureWithScreenCapture("Now Where is the product list");
			System.out.println("Now Where is the product list");
		}
		else
		{
		}

		List<WebElement> lWE = ewe99.findElements(By.xpath("div"));
		gbl99.getHtmlResultsLog().LogTestMessage("We have " + lWE.size()/2 + " Items");
		ExtendedWebElement ewe = null;
		ExtendedWebElement eweP = null;

		for(int iItm = 0; iItm < lWE.size(); iItm++)
		{
			ewe = new ExtendedWebElement((RemoteWebElement) lWE.get(iItm));
			eweP = new ExtendedWebElement((RemoteWebElement) lWE.get(iItm+1));
			ewe.ScrollIntoView();
			ewe.Flash(5);
			ewe.HighliteOn();
			ewe.WatchForAVisibleExtendedElement(By.cssSelector("a:nth-child(1) > div:nth-child(2) > span:nth-child(1)"), 5).ScrollIntoView();
			ewe.WatchForAVisibleExtendedElement(By.cssSelector("a:nth-child(1) > div:nth-child(2) > span:nth-child(1)"), 5).HighliteOn();
			String sName = ewe.WatchForAVisibleExtendedElement(By.cssSelector("a:nth-child(1) > div:nth-child(2) > span:nth-child(1)"), 5).getText();
			System.out.println(sName.trim() + " --- " + sProductName.trim());
			gbl99.getHtmlResultsLog().LogTestMessageWithScreenCapture("Product " + sName + " looking 4 " + sProductName);
			ewe.HighliteOff();
			ewe.WatchForAVisibleExtendedElement(By.cssSelector("a:nth-child(1) > div:nth-child(2) > span:nth-child(1)"), 5).HighliteOff();
			ewe.WatchForAVisibleExtendedElement(By.cssSelector("a:nth-child(1) > div:nth-child(2) > span:nth-child(1)"), 5).Flash(5);
			if(sName.trim().compareToIgnoreCase(sProductName.trim())==0)
			{
				itmFound = new Item(ewe);
				break;
			}
			iItm++;
			
		}
		
		
		
		return itmFound;
	}

	public ArrayList<Item> GetItems()
	{
		ArrayList<Item> alItems = new ArrayList();
		int iTry =0;
		while(GetEweProductList(5)== null)
		{
			gbl99.getExtendedDriver().remoteDriver.navigate().refresh();
			if(iTry++ > 12) break;
		}
		ExtendedWebElement ewe99 = GetEweProductList(1);
		if(ewe99 == null)
		{
			gbl99.getHtmlResultsLog().LogTestFailureWithScreenCapture("Now Where is the product list");
			System.out.println("Now Where is the product list");
		}
		else
		{
		}

		List<WebElement> lWE = ewe99.findElements(By.xpath("div"));
		gbl99.getHtmlResultsLog().LogTestMessage("We have " + lWE.size()/2 + " Items");
		
		for(int iItm = 0; iItm < lWE.size(); iItm++)
		{
			ExtendedWebElement ewe = new ExtendedWebElement((RemoteWebElement) lWE.get(iItm));
			ExtendedWebElement eweP = new ExtendedWebElement((RemoteWebElement) lWE.get(iItm+1));
			ewe.Flash(5);
			Item X = new Item(ewe);
			
			alItems.add(X);
			iItm++;
			
		}
		
		
		
		return alItems;
	}
	
	
	public BooleanMessage FindItemByName(String sItemName)
	{
		BooleanMessage bmRtn = new BooleanMessage(false,"Item: " + sItemName + " not found");
		gbl99.getHtmlResultsLog().LogTestMessage("Look for Product: " + sItemName);
		Item itmSelected=null;
		try {
			itmSelected = GetItemByName(sItemName);
			System.out.println("We found it maybe");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(itmSelected != null)
		{
			System.out.println("We found it ");
//		itmSelected.getEweItemBuyBtn().ScrollIntoView();
		System.out.println("We found it A");
//		itmSelected.getEweItemName().GetParentWebElement().HighliteOn("Dashed", "Green");
		System.out.println("We found it B");
		itmSelected.getEweItemImage().HighliteOn();
		System.out.println("We found it C");
		itmSelected.getEweItemName().HighliteOn();
		System.out.println("We found it D");
		itmSelected.getEweItemPrice().HighliteOn();
		System.out.println("We found it E");
		gbl99.getHtmlResultsLog().LogTestPassWithScreenCapture("Product " + itmSelected.getEweItemName().getText() + " selected" );
		System.out.println("We found it F");
		itmSelected.getEweItemName().HighliteOff();
		System.out.println("We found it G");
		itmSelected.getEweItemImage().HighliteOff();
		System.out.println("We found it H");
		itmSelected.getEweItemName().HighliteOff();
		System.out.println("We found it I");
		itmSelected.getEweItemPrice().HighliteOff();
		System.out.println("We found it J");
		 bmRtn = new BooleanMessage(false,"Item: " + sItemName + " found");
		}
		
		return bmRtn;
	}

	public class Item
	{
		private ExtendedWebElement eweItemImage = null;
		private ExtendedWebElement eweItemName = null;
		private ExtendedWebElement eweItemPrice = null;
		private ExtendedWebElement eweOriginalPrice = null;
		private ExtendedWebElement eweItemBadge = null;
		private ArrayList<ExtendedWebElement> aleweColorTiles = null;
		private String sItemName = "";
		private String sItemPrice = "";
		private String sOriginalPrice = "";
		private String sItemProdID = "";
		private String sItemProdUrl = "";
		private String sHREF = "";
		private String sBadge = null;
		public Item(ExtendedWebElement eweItemCell)
		{
			eweItemCell.ScrollToBottomOf(shOne.GetEweSiteHeaderBar());
			eweItemCell.GetParentWebElement().HighliteOn("dashed", "Green");
			eweItemCell.HighliteOn("dashed", "RED");
			String sMsgString = "";
			eweItemName = eweItemCell.WatchForAVisibleExtendedElement(By.className("product-card-title"),5);
			try {
				eweItemName.HighliteOn();
				sHREF = eweItemName.GetHref();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				sMsgString = sMsgString + "Error on eweItemName";
			}

			eweItemBadge = eweItemCell.WatchForAVisibleExtendedElement(By.className("condition-badge--inner"),1);
			try {
				sBadge = eweItemBadge.getText();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			}
			eweItemImage = eweItemCell.WatchForAVisibleExtendedElement(By.xpath(".//img[1]"), 1);
			try {
				eweItemImage.HighliteOn();
				eweItemImage.GetParentWebElement().HighliteOn("Solid", "BLUE");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				sMsgString = sMsgString + "\n Error on eweItemImage";
			}
	//		eweItemPrice = eweItemCell.WatchForAVisibleExtendedElement(By.cssSelector("a:nth-child(1) > div:nth-child(2) > span:nth-child(4) > span:nth-child(1)"), 5);
			
			
			List<ExtendedWebElement> lwe = eweItemCell.GetExtendedWebElements(By.className("money"));
			if(lwe.size() == 1) eweItemPrice = lwe.get(0);
			if(lwe.size() == 2) {
				eweOriginalPrice = lwe.get(0);
				eweItemPrice =lwe.get(1);
			}
			try {
				lwe = eweItemCell.GetExtendedWebElements(By.className("color-tile"));
				aleweColorTiles = new ArrayList();
				for(int iT=0; iT<lwe.size(); iT++)
				{
					lwe.get(iT).Flash(3);
				//	this.aleweColorTiles.add(lwe.get(iT));
					int itl = iT+1;
					sMsgString = sMsgString + "\n" + "Color for Tile " + itl + " : " + lwe.get(iT).GetBackGroundColor();
					System.out.println("Color for Tile " + itl + " : " + lwe.get(iT).GetBackGroundColor());
				}
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			try {
				eweItemPrice.HighliteOn();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				sMsgString = sMsgString + "\n Error on eweItemPrice";
			}
			
			gbl99.getHtmlResultsLog().LogTestMessageWithScreenCapture("In make Item \n" + sMsgString );
			sItemName = eweItemName.getText();
			sItemPrice = eweItemPrice.getText();
			try {
				sOriginalPrice = eweOriginalPrice.getText();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
		//		e1.printStackTrace();
			}
			try {
	//	sItemProdID = eweProdCell.getAttribute("id").replace("product-", "");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		public String getsHREF() {
			return sHREF;
		}
		public ExtendedWebElement getEweItemBadge() {
			return eweItemBadge;
		}

		public String getsBadge() {
			return sBadge;
			
		}
		public ExtendedWebElement getEweItemImage() {
			return eweItemImage;
		}
		public ExtendedWebElement getEweItemName() {
			return eweItemName;
		}
		public ExtendedWebElement getEweItemPrice() {
			return eweItemPrice;
		}
		public ExtendedWebElement getEweOriginalPrice() {
			return eweOriginalPrice;
		}
		public String getsItemName() {
			return sItemName;
		}
		public String getsItemPrice() {
			return sItemPrice;
		}
		public String getsOriginalPrice() {
			return sOriginalPrice;
		}
		public String getsItemProdID() {
			return sItemProdID;
		}
		public String getsItemProdUrl() {
			return sItemProdUrl;
		}
		
		
	}

}
