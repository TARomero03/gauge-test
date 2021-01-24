package com.companyname.automation.commontools;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;


class ScreenCapture {
	public void TakeTheShot(String sFileName) throws AWTException, IOException {
		// capture the whole screen
		NonObjectMethods nom99 = new NonObjectMethods();
		BufferedImage screencapture = new Robot()
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit()
						.getScreenSize()));

		// Save as JPEG
		// File file = new File(args[0].toString());
		// ImageIO.write(screencapture, "jpg", file);

		// Save as PNG
//		File file = new File(sFileName);
//		ImageIO.write(screencapture, "png", file);
		
		Globals gbl99 = new Globals();
//		EventFiringWebDriver efw = new EventFiringWebDriver(gbl99.getrDriver());
//		File scrFile = efw.getScreenshotAs(OutputType.FILE,"TAR_" + sFileName + "png");
		
		File scrFile = ((TakesScreenshot) gbl99.getExtendedDriver().remoteDriver).getScreenshotAs(OutputType.FILE);
		
		//Needs Commons IO library
	
//		String sPicName = nom99.ReplaceHardTags("c:\\Pic[~Dateyyyymmddhhmmssmm~].png");
		System.out.println(sFileName);
		FileUtils.copyFile(scrFile, new File(sFileName));

		
	}
}