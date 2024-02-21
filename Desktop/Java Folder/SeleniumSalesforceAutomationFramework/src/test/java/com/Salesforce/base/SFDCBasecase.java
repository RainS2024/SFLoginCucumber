package com.Salesforce.base;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

public class SFDCBasecase extends BaseTest{
	protected static Logger SFDCBaseCaselog = LogManager.getLogger();
	protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();
	@BeforeMethod
	@Parameters("browserName")
	public void SetupBeforeMethod(@Optional("chrome")String name) throws InterruptedException {
		SFDCBaseCaselog.info("----------------SetUp Before Method executed-------------- ");
		launchbrowser(name);
		String url = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "url");
	    gotourl(url);	
	}
	
	@AfterMethod
	public void TearDownAfterMethod() throws InterruptedException {
		closeBrowser();
		SFDCBaseCaselog.info("----------------Tear Down After Method executed-------------- ");
	}

public static void LogintoSFDC () throws InterruptedException {
	
	String Username = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "username");
	String Password = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "password");
	
    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
  
	WebElement usrnm = driver.findElement(By.id("username"));
	enterText(usrnm,Username, "Username");
	
	WebElement pwrd = driver.findElement(By.id("password"));
	enterText(pwrd,Password, "Password");
	
	WebElement loginbtn  = driver.findElement(By.id("Login"));
	clickElement(loginbtn, "LoginButton");
	
	WebElement Homepg = driver.findElement(By.id("userNavLabel"));
	String usrpg = Homepg.getText();
	String expected = "Rain Abcd";
	if(usrpg.equalsIgnoreCase(expected)) {
		SFDCBaseCaselog.info("Login to SFDC successful with correct username");
	}
}
	
public static void LogoutfromSFDC () throws InterruptedException {
	WebElement drpdwn = driver.findElement(By.id("userNav-arrow"));
	clickElement(drpdwn, "UsrmenuDropdown");

	WebElement logout = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
	clickElement(logout, "LogOut");
	
	
	Thread.sleep(3000);
	WebElement logoutpg = driver.findElement(By.xpath("//*[@id=\"username\"]"));
	if (logoutpg.isDisplayed()) {
		SFDCBaseCaselog.info("SFDC LogOut successful and application is closed ");
		
	}

	    
}
public static void OptyHmpg() throws InterruptedException {
	LogintoSFDC ();
	Thread.sleep(5000);
	WebElement opptab = driver.findElement(By.xpath("//a[@title=\"Opportunities Tab\"]"));
	clickElement(opptab, "Opportunity Tab");
	getTitle("Opportunities: Home ~ Salesforce - Developer Edition");
}

public static void Acnthmpg() throws InterruptedException {
	LogintoSFDC ();
	 WebElement Acnttab = driver.findElement(By.xpath("//*[@id=\"Account_Tab\"]/a"));
	 clickElement(Acnttab, "Account Tab");
	 String Acntpg = driver.getTitle();
	 String pgtitle = "Accounts: Rain Sing ";
	 if(Acntpg.equalsIgnoreCase(pgtitle)) {
			SFDCBaseCaselog.info(Acntpg + " is displayed");
			}
		else  {

			SFDCBaseCaselog.info ("Account page is displayed. Title ->" + Acntpg+  " doesnot display correct <username> as expected" );
		
		}
}

public static void TC35addremovedtab() throws InterruptedException {
	
	WebElement SelectTab  = driver.findElement(By.id("duel_select_0"));	
	Select select = new Select(SelectTab);
	select.selectByVisibleText("Campaigns");
	WebElement Add  = driver.findElement(By.xpath("//*[@id=\"duel_select_0_right\"]/img"));
	clickElement(Add, "Add");
	WebElement Save = driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
	clickElement(Save, "Save");	
}
}

