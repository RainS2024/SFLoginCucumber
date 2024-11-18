package com.Salesforce.pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Salesforce.pages.base.SFDCBasePage;

public class HomePage extends SFDCBasePage {

@FindBy(id ="userNavButton") public WebElement Dropdown;
@FindBy(xpath ="//*[@id=\"userNav-menuItems\"]/a[5]")public WebElement Logout;
@FindBy(id ="userNavLabel")public WebElement Homepg;	

public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public String getTitle() {
		//waitUntilPageLoads();
		return getTitle();
	}
		
	public void clickDropdown() {
		clickElement(Dropdown, "Dropdown Element");	
		}
		
	public WebDriver clickLogOut() {
	clickElement(Logout, "Logout");
	return driver;
	}
	
	public String getText() {
		waitforvisibility(Homepg, 10, "Homepage");
		return getTextFromElement(Homepg,"User Page");}
	
}


