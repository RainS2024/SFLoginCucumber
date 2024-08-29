package com.Salesforce.pages.login;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.Salesforce.pages.base.SFDCBasePage;
import com.Salesforce.utilities.Constants;
import com.Salesforce.utilities.PropertiesUtilty;

public class LoginPage extends SFDCBasePage {
	@FindBy(id="username") public WebElement userNameElement;
	@FindBy(id="password")public WebElement passwordElement;
	@FindBy(id="Login") public WebElement loginButtonElement;
	@FindBy(id="error")public WebElement errmsgElement;
	@FindBy(xpath="//*[@id=\"rememberUn\"]")public WebElement Checkbx;
	@FindBy(xpath="//*[@id=\"username\"]")public WebElement LoginID;
	@FindBy(id="forgot_password_link")public WebElement Frgtpswrd;
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
/*public String getusername() {
	 String username = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "username");
		return username;}*/
	 
/*public String getpassword() {
	 String password = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "password");
	 return password;}*/

public void enterwrongusername() {
	 String wrongun = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "wrongusername");	
     enterText(userNameElement, wrongun, "Usrname Field") ;
}
  
public void enterwrongpswrd() {
	 String wrongpw = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "wrongpaswrd");
	 enterText(passwordElement, wrongpw, "Password Field") ;
		}

	
		
public String getusername() {
	 String username = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "username");
		return username;}
	 
public String getpassword() {
	 String password = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "password");
	 return password;}


	public void enterUserName(String data) {
		//userNameElement.sendKeys(data);
		enterText(userNameElement, data, "Username textbox");
		//extentReport.logTestInfo("username data is entered in username field");
	}
	
	
	public void enterPassword(String data) {
		enterText(passwordElement, data, "password field");
	}
	
	
	
	public WebDriver clickLoginButton() {
		clickElement(loginButtonElement,"login button");
		return driver;
		
	}

	public  WebDriver clickFrgtpswrd() {
		clickElement(Frgtpswrd,"Forgot Password");
	return driver;
		
	}
	
	public void clickCheckbox(){
		clickElement(Checkbx,"Check Box");
	}
	public String getTextFromError() {
	return getTextFromElement(errmsgElement,"Error Message");

	}
	
	public String getTitle() {
		//waitUntilPageLoads();
		return getTitle();
	}
	public Alert switchToErrorAlert() {
		return switchToErrorAlert();
	}
	//public String extractTextFromAlert(Alert a) {
		//return getAlertText(a,"LOginError Alert");
	//}
	public void acceptErrorAlert(Alert a) {
		acceptErrorAlert(a);
	}
	
	public void waitforvisibilitycheckbx() {
	waitforvisibility(Checkbx,20,"checkbox");}
	
	public String Loginid() {
		return LoginID.getAttribute("value");	
		
}
	public void logindisplayed() {
		LoginID.isDisplayed(); 
		System.out.println("Usrname is displayed");
	}

	public void logindassert() {
	String displayed =LoginID.getAttribute("value");
	String realid = "spirit42@rainbow.com";
	Assert.assertEquals(displayed, realid);}
	
	
	
	public void waitforvisibilityloginid() {
		waitforvisibility(LoginID,30,"loginid");}

}
