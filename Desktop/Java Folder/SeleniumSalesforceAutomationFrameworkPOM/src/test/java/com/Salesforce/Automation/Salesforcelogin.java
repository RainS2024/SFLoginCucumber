package com.Salesforce.Automation;

import org.testng.annotations.Listeners;
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
@Test	
 public void LoginError1() throws InterruptedException {	
      
// driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	   

SalesforceLoginlog.info("------LoginError1 Automation Script started-----------");
String username = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "username");

extentreport.logTestInfo("Username and Password extracted from properties file");

LoginPage loginpage = new LoginPage(driver);
loginpage.enterUserName(username);
loginpage.enterPassword("");

loginpage.clickLoginButton();
String Actual =loginpage.getTextFromError();
String Expected = "Please enter your password.";
Assert.assertEquals(Actual, Expected);


	}
@Test
public void LogintoSalesforce2 () throws InterruptedException {
	
	SalesforceLoginlog.info("------LoginError1 Automation Script started-----------");
	String username = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "username");
	String password = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "password");
	extentreport.logTestInfo("Username and Password extracted from properties file");

	LoginPage loginpage = new LoginPage(driver);
	loginpage.enterUserName(username);
	loginpage.enterPassword(password);
	loginpage.clickLoginButton();
	
	HomePage hm = new HomePage(driver);
	String Actual = hm.getText();
	String expected = "Rain Sing";
	Assert.assertEquals(Actual, expected);
	
	

}
@Test
	public void CheckRememberme3() throws InterruptedException {
		
	
	     
	    LoginPage loginpage = new LoginPage(driver);
	    String usrname = loginpage.getusername();
		loginpage.enterUserName(usrname);
		String paswrd=loginpage.getpassword();
		loginpage.enterPassword(paswrd);
		loginpage.waitforvisibilitycheckbx();
		loginpage.clickCheckbox();
		loginpage.clickLoginButton();
		
		HomePage hm = new HomePage(driver);
		String Actual = hm.getText();
		String expected = "Rain Sing";
		SoftAssert sb = new SoftAssert();
		sb.assertEquals(Actual, expected);
	    hm.clickDropdown();
		hm.clickLogOut();
		 
		LoginPage loginpage1 = new LoginPage(driver);
		loginpage1.waitforvisibilityloginid();
		loginpage1.logindisplayed();
		loginpage1.logindassert();
		 sb.assertAll();
					
		
}
@Test
	public void Forgotpassword4A() throws InterruptedException {

		
	    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
	    LoginPage loginpage = new LoginPage(driver);
	    loginpage.clickFrgtpswrd();
	    
	    Forgotpassword FP = new Forgotpassword(driver);
	    String Heading = FP.gettext();
		String banner = "Forgot Your Password";
		SoftAssert sb = new SoftAssert();
		sb.assertEquals(Heading, banner);
		FP.enteruserid();
		FP.clickContinue();
		NewPg NP = new NewPg(driver);
		String Expected = "Check Your Email";
		String Header = NP.getHeader();
		Assert.assertEquals(Header,Expected);
	    sb.assertAll();
		
		}
		
	

@Test
	public void Forgotpassword4B() throws InterruptedException {
		
		
	    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	
	    LoginPage LP = new LoginPage(driver);
	    LP.enterwrongusername();
	    LP.enterwrongpswrd();
	    LP.clickLoginButton();
		String error = LP.getTextFromError();
		SoftAssert sb = new SoftAssert();
		sb.assertTrue(LP.errmsgElement.isDisplayed());
		String expectedmsg = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		Assert.assertEquals(error, expectedmsg);
		sb.assertAll();
		
		
	
		
	}
	
	
	
}
