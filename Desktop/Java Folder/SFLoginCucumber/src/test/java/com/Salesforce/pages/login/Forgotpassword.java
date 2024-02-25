package com.Salesforce.pages.login;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Salesforce.pages.base.SFDCBasePage;
import com.Salesforce.utilities.Constants;
import com.Salesforce.utilities.PropertiesUtilty;

public class Forgotpassword extends SFDCBasePage{
	@FindBy(xpath="//*[@id=\"header\"]") public WebElement Frgtpswrdpg;
	@FindBy(xpath="//*[@id=\"un\"]") public WebElement Userid;
	@FindBy(xpath="//*[@id=\"continue\"]") public WebElement Continue;
	@FindBy(xpath="//*[@id=\"header\"]") public WebElement Reset;
	
	public Forgotpassword(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public String gettext() {
		return getTextFromElement(Frgtpswrdpg, "Forgot Password");
	}
	
	public void enteruserid() {
		String username = getusername();
		enterText(Userid, username , "Userid");
	}
	public WebDriver clickContinue() {
		clickElement(Continue,"Continue button");
		return driver;
}
}
