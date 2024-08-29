package com.SFDCloginCucumber.stepdefns;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.Salesforce.pages.home.HomePage;
import com.Salesforce.pages.login.Forgotpassword;
import com.Salesforce.pages.login.LoginPage;
import com.Salesforce.pages.login.NewPg;
import com.Salesforce.utilities.Constants;
import com.Salesforce.utilities.Log4jUtility;
import com.Salesforce.utilities.PropertiesUtilty;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.google.common.io.Files;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginStepDefinition {
	public static Logger log;
	public static Log4jUtility LogObject =  Log4jUtility.getInstance();
	public static WebDriver driver;
	LoginPage loginpage;
	HomePage homepage;
	Forgotpassword FP;
	NewPg NP;
	
	public static void launchbrowser(String browsername) {
		switch (browsername.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			//ChromeOptions options = new ChromeOptions();
			//options.setHeadless(true);
			//driver = new ChromeDriver(options);
			driver = new ChromeDriver();
			log.info("Browser instance is created");
			//extentreport.logTestInfo("Browser instance is created");
			driver.manage().window().maximize();
			log.info("Window is maximized");
			break;
		case "safari":
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			log.info("Browser instance is created");
			driver.manage().window().maximize();
			log.info("Window is maximized");
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			//FirefoxOptions options = new FirefoxOptions();
			//options.setHeadless(true);
			//driver = new FirefoxDriver(options);
			driver = new FirefoxDriver();
			log.info("Browser instance is created");
			driver.manage().window().maximize();
			log.info("Window is maximized");
			break;
		
			default:
		log.info("The Browser is unidentified" + browsername);
		}
		
	}
		

	public static void gotourl(String url) throws InterruptedException {
	      
	    driver.get(url);
	     log.info(url +" was entered");
	    // extentreport.logTestInfo(url +" was entered");
	}

	

	public static void closeBrowser() {
		driver.close();
		log.info("Browser instance closed");
		//extentreport.logTestInfo("Browser instance closed");
		driver=null;
	}				

	
@Before
public void before_each_Scenario() {
	launchbrowser("chrome");
}
	
@BeforeAll
public static void before_all_Scenario() {
log=LogObject.getLogger();
}


@After
public void Aftere_each_Scenario() {
closeBrowser();
}

/*@AfterStep
public void After_eachstep(Scenario sc) {

}*/

@Given("User open Salesforce application")
public void user_open_salesforce_application() throws InterruptedException {
	String url = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "url");
    gotourl(url);
	
}

@When("User on {string}")
	public void user_on(String page) {
	if(page.equalsIgnoreCase("loginpage"))
    	loginpage=new LoginPage(driver);
    else if(page.equalsIgnoreCase("homepage"))
    	homepage=new HomePage(driver);		
		
}

@When("User on page {string}")
public void user_on_page(String newpage) {
	if(newpage.equalsIgnoreCase("ForgotPasswordPage"))
    	FP =new Forgotpassword(driver);
    else if(newpage.equalsIgnoreCase("NewPage"))
    	NP=new NewPg(driver);	
}

@When("User Enters value in username {string}")
public void user_enters_value_in_username(String Username) {
	String username = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "username");
	loginpage.enterUserName(username);
}

@When("User Enters novalue in password")
public void user_enters_novalue_in_password() {
   loginpage.enterPassword("");
}

@When("User Clicks on Login")
public void user_clicks_on_login() {
    driver=loginpage.clickLoginButton();
}

@Then("Verify we can see Error Message")
public void verify_we_can_see_error_message() {
	String Actual =loginpage.getTextFromError();
	String Expected = "Please enter your password.";
	Assert.assertEquals(Actual, Expected);
}



@When("User Enters value in password {string}")
public void user_enters_value_in_password(String Password) {
	String password = PropertiesUtilty.ReadingdatafromPropertyfile(Constants.APPLICATION_PROPERTIES, "password");
	loginpage.enterPassword(password);
}

@Then("Verify we can see Home Page")
public void verify_we_can_see_home_page() {
	
	String Actual = homepage.getText();
	String expected = "Rain Sing";
	Assert.assertEquals(Actual, expected);
	
}

@When("User Clicks on CheckBox")
public void user_clicks_on_check_box() {
	loginpage.waitforvisibilitycheckbx();
	loginpage.clickCheckbox();
}

@When("User Clicks on UserMenudropdown")
public void user_clicks_on_user_menudropdown() throws InterruptedException{
	Thread.sleep(5000);
	homepage.clickDropdown();
	
}

@When("User Clicks on Log out")
public void user_clicks_on_log_out() {
	driver=homepage.clickLogOut();
}

@Then("Verify we can see Login sales force page")
public void verify_we_can_see_login_sales_force_page() throws InterruptedException {
    Thread.sleep(5000);
	loginpage.logindisplayed();
	loginpage.logindassert();
}

@When("User Clicks on Forgot password")
public void user_clicks_on_forgot_password() {
	driver=loginpage.clickFrgtpswrd();
  
}

@When("User Enters value in FPUsername {string}")
public void user_enters_value_in_fp_username(String string) {
	  	String Heading = FP.gettext();
		String banner = "Forgot Your Password";
		Assert.assertEquals(Heading, banner);
		FP.enteruserid();
}

@When("User Clicks on Contiue button")
public void user_clicks_on_contiue_button() {
    driver=FP.clickContinue();
}

@Then("Verify we can see Password reset message")
public void verify_we_can_see_password_reset_message() {
	String Expected = "Check Your Email";
	String Header = NP.getHeader();
	Assert.assertEquals(Header,Expected);
}

@When("User Enters Wrong UserName")
public void user_enters_wrong_user_name() {
	 loginpage.enterwrongusername();
}

@When("User Enters Wrong Password")
public void user_enters_wrong_password() {
   loginpage.enterwrongpswrd();
}

@Then("Verify we can see newerror message")
public void verify_we_can_see_newerror_message() {
	String error = loginpage.getTextFromError();
	Assert.assertTrue(loginpage.errmsgElement.isDisplayed());
	String expectedmsg = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
	Assert.assertEquals(error, expectedmsg);
}











}	

