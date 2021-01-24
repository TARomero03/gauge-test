package com.companyname.automation.uiobjectmodels;

import org.openqa.selenium.By;

import com.companyname.automation.commontools.Globals;
import com.companyname.automation.webdriverextensions.ExtendedWebElement;



public class ObjectModelHelperMethods {

	Globals gbl99 = new Globals();
	public ExtendedWebElement GetEweObject(ExtendedWebElement ewe, By by, double dTime)
	{
	 
		boolean bThere = false;
		try {
			if(ewe != null)
			{
				if(ewe.isEnabled())
				{
					if(ewe.IsObjectVisible())
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
			
			ewe = gbl99.getExtendedDriver().WatchForAVisibleExtendedElement(by, dTime);
		}
		return ewe; 
	}

}
