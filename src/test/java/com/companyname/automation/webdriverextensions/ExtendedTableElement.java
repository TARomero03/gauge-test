package com.companyname.automation.webdriverextensions;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTML.Attribute;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import com.companyname.automation.commontools.Globals;





/**
 * @author Timothy.Romero
 * 
 *         A Class that reprsents a table
 */
public class ExtendedTableElement implements WebElement {

	ExtendedWebElement weChild;
	ExtendedWebElement Tble;
	Globals gbl99 = new Globals();
	/**
	 * Class Constructor
	 * 
	 * @param table
	 *            - the WebElenent that is the table
	 * 
	 * @return a Table Object
	 */
	public ExtendedTableElement(ExtendedWebElement table) {
		super();
		Tble = table;
		weChild = Tble;
		// System.err.println("Table Text: &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		// System.err.println(Tble.getText());
		// System.err.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	}

	/**
	 * This Method returns the WebElement that the Table is based on
	 * 
	 * @return A WebElement that is the Table
	 */
	public ExtendedWebElement GetWebElementCastFrom()
	{
		if(weChild==null)
		{
			// System.out.println("Child is null");
		}
		return weChild;
	}

	/**
	 * This Method returns the number of rows in the table
	 * 
	 * @return An Integer of the number of rows in the table
	 */
	public int GetRowCount() {

//		List<WebElement> weX = Tble.findElements(By.tagName("tr"));
//		List<WebElement> weX = Tble.findElements(By.xpath("//tbody/tr"));
		List<WebElement> weX = Tble.findElements(By.xpath("tbody/tr"));
		return weX.size();

	}

	/**
	 * This Method returns the number of Columns for the given Row
	 * 
	 * @param int : iRow the row for which the column count is requested
	 * @return int : the number of columns for the given row
	 */
	public int GetColumnCount(int iRow) {

		WebElement weR = Tble.findElement(By.xpath("tbody/tr[" + iRow + "]"));
		List<WebElement> weX = weR.findElements(By.xpath("td"));
		return weX.size();

	}

	public ExtendedWebElement GetColumnHeader(int iColumn) {
		gbl99.getExtendedDriver().remoteDriver.manage().timeouts()
		.implicitlyWait(1000, TimeUnit.MILLISECONDS);

		try {
			try {
				RemoteWebElement weR=null;
				
				try {
					weR = (RemoteWebElement) Tble.findElement(By.xpath("thead/tr/th[" + iColumn + "]"));
				} catch (Exception e) {
					weR = null;
				}
				// System.out.println(iColumn + ": " + weR.getText());
				return new ExtendedWebElement(weR);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		return null;

	}


	public ExtendedWebElement GetCell(int iRow, int iColumn) {
		gbl99.getExtendedDriver().remoteDriver.manage().timeouts()
		.implicitlyWait(1000, TimeUnit.MILLISECONDS);

		try {
			try {
				WebElement weR=null;
				
				try {
//					weR = Tble.findElement(By.xpath("//tbody/tr[" + iRow + "]/td[" + iColumn + "]"));
					weR = Tble.findElement(By.xpath("tbody/tr[" + iRow + "]/td[" + iColumn + "]"));
				} catch (Exception e) {
					if((this.GetRowCount()==1) && (iRow==1))
					{
//						weR = Tble.findElement(By.xpath("//tbody/tr/td[" + iColumn + "]"));
						weR = Tble.findElement(By.xpath("tbody/tr/td[" + iColumn + "]"));
						
					}
				}
				// System.out.println(iRow + "," + iColumn + ": " + weR.getText());
				return new ExtendedWebElement((RemoteWebElement) weR);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		return null;

	}

	public ExtendedWebElement GetRow(int iRow) {
		gbl99.getExtendedDriver().remoteDriver.manage().timeouts()
		.implicitlyWait(1000, TimeUnit.MILLISECONDS);

		try {
			try {
				WebElement weR=null;
				
				try {
//					weR = Tble.findElement(By.xpath("//tbody/tr[" + iRow + "]/td[" + iColumn + "]"));
					weR = Tble.findElement(By.xpath("tbody/tr[" + iRow + "]"));
				} catch (Exception e) {
					if((this.GetRowCount()==1) && (iRow==1))
					{
//						weR = Tble.findElement(By.xpath("//tbody/tr/td[" + iColumn + "]"));
						weR = Tble.findElement(By.xpath("tbody/tr"));
						
					}
				}
				// System.out.println(iRow + "," + iColumn + ": " + weR.getText());
				return new ExtendedWebElement((RemoteWebElement) weR);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		return null;

	}

	public ExtendedWebElement GetCellByText(String sText) {
		List<WebElement> weX = Tble.findElements(new ByChained(
				By.tagName("tr"), By.tagName("td")));
		for (int iX = 0; iX < weX.size(); iX++) {
			WebElement weXX = weX.get(iX);
			if (weXX.getText().compareTo(sText) == 0) {
				return new ExtendedWebElement((RemoteWebElement) weXX);
			}
		}
		return null;

	}

	public ExtendedWebElement GetCellByText(String sText, int iCol) {
		int iRws = this.GetRowCount();
		List<WebElement> weX = Tble.findElements(By.tagName("tr"));
		for (int iRow = 0; iRow < iRws; iRow++) {
			if (iCol > GetColumnCount(iRow)) {
				continue;
			}
			
			WebElement weR = weX.get(iRow);
			
			List<WebElement> weXX = weR.findElements(By.tagName("td"));
			if (weX.get(iCol).getText().compareTo(sText) == 0) {
				return new ExtendedWebElement((RemoteWebElement) weX.get(iCol));
			}
		}

		return null;
	}

	public int GetCellRowByText(String sText, int iCol) {
		int iRws = this.GetRowCount();
		List<WebElement> weX = Tble.findElements(By.tagName("tr"));
		for (int iRow = 0; iRow < iRws; iRow++) {
			if (iCol > GetColumnCount(iRow)) {
				continue;
			}

			WebElement weR = weX.get(iRow);
			List<WebElement> weXX = weR.findElements(By.tagName("td"));
			if (weX.get(iCol).getText().compareTo(sText) == 0) {
				return iRow;
			}
		}

		return -1;
	}

	public String Text(int iRow, int iCol)
	{
		WebElement element = this.GetCell(iRow, iCol).GetWebElementCastFrom();
		
		String innerText = (String) (gbl99.getExtendedDriver().remoteDriver).executeScript("return arguments[0].innerText",element ); 
		return innerText;
	}
//	public void clear() {
//		weChild.clear();
//
//	}

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

	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
