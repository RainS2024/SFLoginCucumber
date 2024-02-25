package com.Salesforce.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Salesforce.pages.base.SFDCBasePage;

public class NewPg extends SFDCBasePage {
	@FindBy(id="header") public WebElement NewPageHeader;
	public NewPg(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public String getHeader() {
		return getTextFromElement(NewPageHeader,"New Page Header");
}
}
