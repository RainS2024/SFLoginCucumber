package com.Salesforce.pages.base;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.Salesforce.utilities.Constants;
import com.Salesforce.utilities.ExtentReportsUtility;
import com.Salesforce.utilities.PropertiesUtilty;
import com.beust.jcommander.Parameter;

import io.github.bonigarcia.wdm.WebDriverManager;


public class SFDCBasePage {
	protected static WebDriver driver = null;
	protected static Logger SFDCBasePagelog = LogManager.getLogger();
	protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();

	public SFDCBasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public static void enterText(WebElement ele, String data, String objectName) {
		if (ele.isDisplayed()) {
			ele.clear();
			ele.sendKeys(data);
			SFDCBasePagelog.info("User data is entered in " + objectName + " textbox");
			extentreport.logTestInfo("User data is entered in " + objectName + " textbox");
		} else {
			SFDCBasePagelog.info(objectName + " element is not displayed");
			extentreport.logTestInfo(objectName + " element is not displayed");
		}
	}

	public static void clickElement(WebElement ele, String objectName) {
		if (ele.isEnabled()) {
			ele.click();
			SFDCBasePagelog.info(objectName + " button is clicked");
			extentreport.logTestInfo(objectName + " button is clicked");
			
		} else {
			SFDCBasePagelog.info(objectName+" element is not enabled");
			extentreport.logTestInfo(objectName+" element is not enabled");
		}
	}

	public static String getTextFromElement(WebElement ele, String objectName) {
		String data = ele.getText();
		SFDCBasePagelog.info("Text is extracted from "+objectName);
		extentreport.logTestInfo("Text is extracted from "+objectName);
		return data;
	}

	public static void getTitle(String expected) {
		if(driver.getTitle().contains(expected)) {
			SFDCBasePagelog.info(driver.getTitle() + " is displayed");
			extentreport.logTestInfo(driver.getTitle() + " is displayed");
		}
		else {
			SFDCBasePagelog.info(expected + " is not displayed");
		}
	}
				

	public static void waitforvisibility(WebElement ele,int time,String object) {
	WebDriverWait wait = new WebDriverWait(driver,time);
	wait.until(ExpectedConditions.visibilityOf(ele));
	SFDCBasePagelog.info("Waited for " + object +  " visibility");
	}

	public String getusername() {
		 String username = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "username");
			return username;}
	
	public String getpassword() {
		 String password = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "password");
		 return password;}
	
	
	
	
}


