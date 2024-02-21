package com.Salesforce.Automation;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Salesforce.base.SFDCBasecase;
import com.Salesforce.utilities.ExtentReportsUtility;
@Listeners(com.Salesforce.utilities.SFDCListenerUtilities.class)
public class Salesforcelogin extends SFDCBasecase {
	protected static Logger SalesforceLoginlog = LogManager.getLogger();
	protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();
@Test	
 public void LoginError1() throws InterruptedException {
		
	
      
// driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
       

WebElement usrname = driver.findElement(By.id("username"));
enterText(usrname, "spirit42@rainbow.com", "Username");

WebElement passwrd = driver.findElement(By.id("password"));
passwrd.clear();

WebElement loginbtn  = driver.findElement(By.id("Login"));
clickElement(loginbtn, "LoginButton");

WebElement errmsg = driver.findElement(By.id("error"));
String errormsg = errmsg.getText();
String Expected = "Please enter your password.";
if (errormsg.equalsIgnoreCase(Expected)) {
	System.out.println("Errormessage on Login without password : Login Error Message - 1 Test passed");
}
else {
	System.out.println("Login Error Message - 1 Test Failed ");
}

	}
@Test
public void LogintoSalesforce2 () throws InterruptedException {
	
	
	
	WebElement usrname = driver.findElement(By.id("username"));
	enterText(usrname, "spirit42@rainbow.com", "Username");

	WebElement passwrd = driver.findElement(By.id("password"));
	enterText(passwrd,"pink422@" , "Password");
	
	WebElement loginbtn  = driver.findElement(By.id("Login"));
	clickElement(loginbtn, "LoginButton");
	
	WebElement Homepg = driver.findElement(By.id("userNavLabel"));
	String usrpg = Homepg.getText();
	String expected = "Rain Sing";
	if(usrpg.equalsIgnoreCase(expected)) {
		System.out.println("Login to SFDC successful: Login To SalesForce -2 ---> Test Pass");
	}
	else {
		System.out.println("Login To SalesForce -2 Test Failed ");
	}

}
@Test
	public void CheckRememberme3() throws InterruptedException {
		
	    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	
		
		WebElement usrname = driver.findElement(By.id("username"));
		enterText(usrname, "spirit42@rainbow.com", "Username");
		
		
		WebElement passwrd = driver.findElement(By.id("password"));
		enterText(passwrd,"pink422@" , "Password");
	
		WebElement chcbx=driver.findElement(By.xpath("//*[@id=\"rememberUn\"]"));
		clickElement(chcbx, "Checkbox");
		
		WebElement loginbtn  = driver.findElement(By.id("Login"));
		clickElement(loginbtn, "Login");
		
		WebElement Homepg = driver.findElement(By.id("userNavLabel"));
		String usrpg = Homepg.getText();
		String expected = "Rain Sing";
		if(usrpg.equalsIgnoreCase(expected)) {
			System.out.println("Login to SFDC successful");
		
			WebElement drpdwn = driver.findElement(By.id("userNavButton"));
		clickElement(drpdwn, "Dropdown");
		
		WebElement logout = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		clickElement(logout, "Logout");
	
		
				
		WebElement loginid = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		if (loginid.isDisplayed()) {
			System.out.println("Usrname is displayed");
		}
	
		String displayed =loginid.getAttribute("value");
		String realid = "spirit42@rainbow.com";
		if(displayed.equalsIgnoreCase(realid)) {
			System.out.println("Logout is successful Usrname displayed is correct : Check RemeberMe - 3 -->Test Pass");
		}
		else {
			System.out.println("Check RemeberMe - 3 Test Failed ");
		}
	
		}
}
@Test
	public void Forgotpassword4A() throws InterruptedException {

		
	    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		WebElement frgtpswrd = driver.findElement(By.id("forgot_password_link"));
		clickElement(frgtpswrd, "Forgot Password");
		
		WebElement nextpg = driver.findElement(By.xpath("//*[@id=\"header\"]"));
		String heading = nextpg.getText();
		String banner = "Forgot Your Password";
		if(heading.equalsIgnoreCase(banner)) {
			System.out.println("Forgot your password page is displayed: Test passed");
			
		}
		WebElement enterid = driver.findElement(By.xpath("//*[@id=\"un\"]"));
		enterText(enterid, "spirit42@rainbow.com", "Username");
		
		WebElement button = driver.findElement(By.xpath("//*[@id=\"continue\"]"));
		button.click();
		WebElement reset = driver.findElement(By.xpath("//*[@id=\"header\"]"));
		if (reset.isDisplayed()) {
			System.out.println("Check your email page is displayed: Forgot Password- 4 A--->Test Passed");
		}
		else {
			System.out.println("Forgot Password- 4 A Test Failed ");
		}
		
	}

@Test
	public void Forgotpassword4B() throws InterruptedException {
		
		
	    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	
		WebElement usrname = driver.findElement(By.id("username"));
		enterText(usrname, "123", "Username");
		
		
		WebElement passwrd = driver.findElement(By.id("password"));
		enterText(passwrd, "22131", "Password");
		
		
		WebElement loginbtn  = driver.findElement(By.id("Login"));
		clickElement(loginbtn, "Login");
		
		WebElement errmsg = driver.findElement(By.xpath("//*[@id=\"error\"]"));
		if (errmsg.isDisplayed()){
			System.out.println("Login error messagr is displayed : Test passed");
		}
		String Loginerrormsg = errmsg.getText();
		String expectedmsg = "Your login attempt has failed. The username or password may be incorrect, or your location or login time may be restricted. Please contact the administrator at your company for help";
	if(Loginerrormsg.equalsIgnoreCase(expectedmsg)) {
		System.out.println("Error msg displayed as expected: Forgot Password- 4 B---> Test Pass");
	}
	else {
		System.out.println("The message displayed doesnot match : Forgot Password- 4 B--->Test Fail");
	}	
	
		
	}
	
	

		
		
	}
	

