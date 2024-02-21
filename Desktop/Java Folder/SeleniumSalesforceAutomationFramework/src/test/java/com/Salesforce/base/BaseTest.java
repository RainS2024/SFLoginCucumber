package com.Salesforce.base;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Salesforce.utilities.ExtentReportsUtility;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	protected static WebDriver driver = null;
	protected static Logger BaseTestlog = LogManager.getLogger();
	protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();
	public static void launchbrowser(String browsername) {

		switch (browsername.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			//ChromeOptions options = new ChromeOptions();
			//options.setHeadless(true);
			//driver = new ChromeDriver(options);
			driver = new ChromeDriver();
			BaseTestlog.info("Browser instance is created");
			//extentreport.logTestInfo("Browser instance is created");
			driver.manage().window().maximize();
			BaseTestlog.info("Window is maximized");
			break;
		case "safari":
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			BaseTestlog.info("Browser instance is created");
			driver.manage().window().maximize();
			BaseTestlog.info("Window is maximized");
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			//FirefoxOptions options = new FirefoxOptions();
			//options.setHeadless(true);
			//driver = new FirefoxDriver(options);
			driver = new FirefoxDriver();
			BaseTestlog.info("Browser instance is created");
			driver.manage().window().maximize();
			BaseTestlog.info("Window is maximized");
			break;
		
			default:
		BaseTestlog.info("The Browser is unidentified" + browsername);
		}
		
	}
		

	public static void gotourl(String url) throws InterruptedException {
	      
	    driver.get(url);
	     BaseTestlog.info(url +" was entered");
	     //extentreport.logTestInfo(url +" was entered");
	}

	public static void enterText(WebElement ele, String data, String objectName) {
		
		if (ele.isDisplayed()) {
			ele.clear();
			ele.sendKeys(data);
			BaseTestlog.info("User data is entered in " + objectName + " textbox");
			extentreport.logTestInfo("User data is entered in " + objectName + " textbox");
		} else {
			BaseTestlog.info(objectName + " element is not displayed");
			//extentreport.logTestInfo(objectName + " element is not displayed");
		}
	}

	public static void clickElement(WebElement ele, String objectName) {
		if (ele.isEnabled()) {
			ele.click();
			BaseTestlog.info(objectName + " button is clicked");
			extentreport.logTestInfo(objectName + " button is clicked");
			
		} else {
			BaseTestlog.info(objectName+" element is not enabled");
			extentreport.logTestInfo(objectName+" element is not enabled");
		}
	}

	public static String getTextFromElement(WebElement ele, String objectName) {
		String data = ele.getText();
		BaseTestlog.info("Text is extracted from "+objectName);
		extentreport.logTestInfo("Text is extracted from "+objectName);
		return data;
	}

	public static void closeBrowser() {
		driver.close();
		BaseTestlog.info("Browser instance closed");
		driver=null;
	}

	public static void getTitle(String expected) {
		if(driver.getTitle().contains(expected)) {
			BaseTestlog.info(driver.getTitle() + " is displayed");
			extentreport.logTestInfo(driver.getTitle() + " is displayed");
		}
		else {
			BaseTestlog.info(expected + " is not displayed");
		}
	}
		
		public static void TitleignoringCase(String expected) {	
		
		if(driver.getTitle().equalsIgnoreCase(expected)) {
			BaseTestlog.info(driver.getTitle() + " is displayed");
			}
		else  {

			BaseTestlog.info (expected + " page is not displayed");
		
		}
	}

	public static void waitforvisibility(WebElement ele,int time,String object) {
	WebDriverWait wait = new WebDriverWait(driver,time);
	wait.until(ExpectedConditions.visibilityOf(ele));
	BaseTestlog.info("Waited for " + object +  " visibility");
	}

	public static void takescreenshot(String filepath) {
     TakesScreenshot screencapture = (TakesScreenshot)driver;
     File src = screencapture.getScreenshotAs(OutputType.FILE);
     File des = new File(filepath);
     try {
    	 Files.copy(src, des);
    	 BaseTestlog.info("Captured the screen");
     }
     catch (IOException e){
    	 e.printStackTrace();
    	 BaseTestlog.info("Went wrong when Capturing the the screen");
     }
}
	public static void takescreenshot(WebElement element ,String filepath) {
	     TakesScreenshot screencapture = (TakesScreenshot)driver;
	     File src = screencapture.getScreenshotAs(OutputType.FILE);
	     File des = new File(filepath);
	     try {
	    	 Files.copy(src, des);
	    	 BaseTestlog.info("Captured the screen");
	     }
	     catch (IOException e){
	    	 e.printStackTrace();
	    	 BaseTestlog.info("Went wrong when Capturing the the screen");
	     }
	}
}
