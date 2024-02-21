package com.Salesforce.base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.Salesforce.utilities.Constants;
import com.Salesforce.utilities.ExtentReportsUtility;
import com.Salesforce.utilities.PropertiesUtilty;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	protected static WebDriver driver = null;
	protected static Logger BaseTestlog = LogManager.getLogger();
	protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();
	
	@BeforeMethod
	@Parameters("browserName")//you will have to add parameters in configuration file too to use this parameter.
	public void SetupBeforeMethod(@Optional("chrome")String name) throws InterruptedException {
		BaseTestlog.info("----------------SetUp Before Method executed-------------- ");
		launchbrowser(name);
		String url = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "url");
	    gotourl(url);	
	}
	
	@AfterMethod
	public void TearDownAfterMethod() throws InterruptedException {
		closeBrowser();
		BaseTestlog.info("----------------Tear Down After Method executed-------------- ");
	}
	
	
	
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
	    // extentreport.logTestInfo(url +" was entered");
	}

	

	public static void closeBrowser() {
		driver.close();
		BaseTestlog.info("Browser instance closed");
		//extentreport.logTestInfo("Browser instance closed");
		driver=null;
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

		    
	
