package com.testng.Salesforce;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesforceBaseTest extends Basecase {

	@BeforeClass
	public static void launchbrowser(String browsername) {

		switch (browsername.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			driver = new ChromeDriver(options);
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
		
	
	@BeforeMethod
	
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
	
	@AfterMethod
	
	public static void LogoutfromSFDC () throws InterruptedException {
		WebElement drpdwn = driver.findElement(By.id("userNav-arrow"));
		clickElement(drpdwn, "UsrmenuDropdown");

		WebElement logout = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		clickElement(logout, "LogOut");
		
		
		Thread.sleep(3000);
		WebElement logoutpg = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		if (logoutpg.isDisplayed()) {
			System.out.println("SFDC LogOut successful and application is closed ");
			//closeBrowser();
		}

		    
	}
	
	@AfterClass
	
	public static void closeBrowser() {
		driver.close();
		System.out.println("Browser instance closed");
		driver=null;
		
}
}
