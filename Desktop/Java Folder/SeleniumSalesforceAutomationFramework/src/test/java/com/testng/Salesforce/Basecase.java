package com.testng.Salesforce;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Basecase {
	public static WebDriver driver = null;
	
public static void launchbrowser(String browsername) {

	switch (browsername.toLowerCase()) {
	case "chrome":
		WebDriverManager.chromedriver().setup();
		//ChromeOptions options = new ChromeOptions();
		//options.setHeadless(true);
		//driver = new ChromeDriver(options);
		driver = new ChromeDriver();
		System.out.println("Browser instance is created");
		driver.manage().window().maximize();
		System.out.println("Window is maximized");
		break;
	case "safari":
		WebDriverManager.safaridriver().setup();
		driver = new SafariDriver();
		System.out.println("Browser instance is created");
		driver.manage().window().maximize();
		System.out.println("Window is maximized");
		break;
	case "firefox":
		WebDriverManager.firefoxdriver().setup();
		//FirefoxOptions options = new FirefoxOptions();
		//options.setHeadless(true);
		//driver = new FirefoxDriver(options);
		driver = new FirefoxDriver();
		System.out.println("Browser instance is created");
		driver.manage().window().maximize();
		System.out.println("Window is maximized");
		break;
	
		default:
	System.out.println("The Browser is unidentified" + browsername);
	}
	
}
	

public static void gotourl(String url) throws InterruptedException {
      
    driver.get(url);
     System.out.println(url +" was entered");
}

public static void enterText(WebElement ele, String data, String objectName) {
	if (ele.isDisplayed()) {
		ele.clear();
		ele.sendKeys(data);
		System.out.println("User data is entered in " + objectName + " textbox");
	} else {
		System.out.println(objectName + " element is not displayed");
	}
}

public static void clickElement(WebElement ele, String objectName) {
	if (ele.isEnabled()) {
		ele.click();
		System.out.println(objectName + " button is clicked");
		
	} else {
		System.out.println(objectName+" element is not enabled");
		
	}
}

public static String getTextFromElement(WebElement ele, String objectName) {
	String data = ele.getText();
	System.out.println("Text is extracted from "+objectName);
	return data;
}

public static void closeBrowser() {
	driver.close();
	System.out.println("Browser instance closed");
	driver=null;
}

public static void getTitle(String expected) {
	if(driver.getTitle().contains(expected)) {
		System.out.println(driver.getTitle() + " is displayed");
	}
	else {
		System.out.println(expected + " is not displayed");
	}
}
	
	public static void TitleignoringCase(String expected) {	
	
	if(driver.getTitle().equalsIgnoreCase(expected)) {
		System.out.println(driver.getTitle() + " is displayed");
		}
	else  {

		System.out.println (expected + " page is not displayed");
	
	}
}

public static void waitforvisibility(WebElement ele,int time,String object) {
WebDriverWait wait = new WebDriverWait(driver,time);
wait.until(ExpectedConditions.visibilityOf(ele));
System.out.println("Waited for " + object +  " visibility");
}


public static void LogintoSFDC () throws InterruptedException {
	
	launchbrowser("chrome");
    gotourl("https://login.salesforce.com/");
    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
  
	WebElement usrname = driver.findElement(By.id("username"));
	enterText(usrname,"spirit42@rainbow.com ", "Username");
	
	WebElement passwrd = driver.findElement(By.id("password"));
	enterText(passwrd,"pink422@", "Password");
	
	WebElement loginbtn  = driver.findElement(By.id("Login"));
	clickElement(loginbtn, "LoginButton");
	
	WebElement Homepg = driver.findElement(By.id("userNavLabel"));
	String usrpg = Homepg.getText();
	String expected = "Rain Abcd";
	if(usrpg.equalsIgnoreCase(expected)) {
		System.out.println("Login to SFDC successful with correct username");
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
		System.out.println("SFDC LogOut successful and application is closed ");
		closeBrowser();
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
			System.out.println(Acntpg + " is displayed");
			}
		else  {

			System.out.println ("Account page is displayed. Title ->" + Acntpg+  " doesnot display correct <username> as expected --> Test Fail" );
		
		}
}
}

