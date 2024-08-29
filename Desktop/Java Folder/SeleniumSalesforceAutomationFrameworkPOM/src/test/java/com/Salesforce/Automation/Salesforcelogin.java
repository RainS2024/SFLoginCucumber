package com.Salesforce.Automation;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.apache.hc.core5.util.Asserts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Salesforce.base.BaseTest;

import com.Salesforce.pages.home.HomePage;
import com.Salesforce.pages.login.Forgotpassword;
import com.Salesforce.pages.login.LoginPage;
import com.Salesforce.pages.login.NewPg;
import com.Salesforce.utilities.Constants;
import com.Salesforce.utilities.ExtentReportsUtility;
import com.Salesforce.utilities.PropertiesUtilty;
@Listeners(com.Salesforce.utilities.SFDCListenerUtilities.class)
public class Salesforcelogin extends BaseTest {
	protected static Logger SalesforceLoginlog = LogManager.getLogger();
	protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();
	LoginPage loginpage;
	HomePage hm;
	Forgotpassword FP;
	NewPg NP;
@Test	
 public void LoginError1() throws InterruptedException {	
      
// driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	   

SalesforceLoginlog.info("------LoginError1 Automation Script started-----------");
String username = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "username");

extentreport.logTestInfo("Username and Password extracted from properties file");

loginpage = new LoginPage(driver);
loginpage.enterUserName(username);
loginpage.enterPassword("");

loginpage.clickLoginButton();

String Actual =loginpage.getTextFromError();
String Expected = "Please enter your password.";
AssertJUnit.assertEquals(Actual, Expected);


	}
@Test
public void LogintoSalesforce2 () throws InterruptedException {
	
	SalesforceLoginlog.info("------LoginError1 Automation Script started-----------");
	extentreport.logTestInfo("Username and Password extracted from properties file");
	String username = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "username");
	String password = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "password");
	loginpage = new LoginPage(driver);
	loginpage.enterUserName(username);
	loginpage.enterPassword(password);
	driver = loginpage.clickLoginButton();
	
	hm = new HomePage(driver);
	String Actual = hm.getText();
	String expected = "Rain Sing";
	AssertJUnit.assertEquals(Actual, expected);
	
	

}
@Test
	public void CheckRememberme3() throws InterruptedException {
		
	
	     
	    loginpage = new LoginPage(driver);
	    String usrname = loginpage.getusername();
		loginpage.enterUserName(usrname);
		String paswrd=loginpage.getpassword();
		loginpage.enterPassword(paswrd);
		loginpage.waitforvisibilitycheckbx();
		loginpage.clickCheckbox();
		driver = loginpage.clickLoginButton();
		Thread.sleep(2000);
		hm = new HomePage(driver);
		String Actual = hm.getText();
		String expected = "Rain Sing";
		SoftAssert sb = new SoftAssert();
		AssertJUnit.assertEquals(Actual, expected);
	    hm.clickDropdown();
		driver = hm.clickLogOut();
		Thread.sleep(2000);
		loginpage = new LoginPage(driver);
		//loginpage.waitforvisibilityloginid();
		loginpage.logindisplayed();
		loginpage.logindassert();
		 sb.assertAll();
					
		
}
@Test
	public void Forgotpassword4A() throws InterruptedException {

		
	    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
	    loginpage = new LoginPage(driver);
	    driver = loginpage.clickFrgtpswrd();
	    
	    FP = new Forgotpassword(driver);
	    String Heading = FP.gettext();
		String banner = "Forgot Your Password";
		SoftAssert sb = new SoftAssert();
		AssertJUnit.assertEquals(Heading, banner);
		FP.enteruserid();
		driver = FP.clickContinue();
		NP = new NewPg(driver);
		String Expected = "Check Your Email";
		String Header = NP.getHeader();
		AssertJUnit.assertEquals(Header,Expected);
	    sb.assertAll();
		
		}
		
	

@Test
	public void Forgotpassword4B() throws InterruptedException {
		
		
	    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	
	    loginpage = new LoginPage(driver);
	    loginpage.enterwrongusername();
	    loginpage.enterwrongpswrd();
	    loginpage.clickLoginButton();
		String error = loginpage.getTextFromError();
		SoftAssert sb = new SoftAssert();
		AssertJUnit.assertTrue(loginpage.errmsgElement.isDisplayed());
		String expectedmsg = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		AssertJUnit.assertEquals(error, expectedmsg);
		sb.assertAll();
		
		
	
		
	}
	
	
	
}
