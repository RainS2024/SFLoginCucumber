package com.Salesforce.Automation;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import com.Salesforce.base.SFDCBasecase;
import com.Salesforce.utilities.ExtentReportsUtility;
@Listeners(com.Salesforce.utilities.SFDCListenerUtilities.class)
public class SalesforceContacts extends SFDCBasecase {
	protected static Logger SalesforceContactslog = LogManager.getLogger();
	protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();
@Test
	public void TC25() throws InterruptedException {	
        
		LogintoSFDC ();
		
		WebElement Contact = driver.findElement(By.id("Contact_Tab"));
		clickElement(Contact, "Contact");
		getTitle("Contacts");
	
		WebElement New = driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input"));
		clickElement(New, "New");
		getTitle("New Contact");
		
		WebElement LastName = driver.findElement(By.id("name_lastcon2"));
		enterText(LastName,"Cadence", "Last Name");
		
		WebElement AcName = driver.findElement(By.id("con4"));
		enterText(AcName,"SunnysideUp", "Account Name");
		
		WebElement Save = driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
		clickElement(Save, "Save");
		if(driver.getTitle().contains("Cadence")){
			SalesforceContactslog.info("New contact is created: ---------------TC25--> Test Pass");
	
		}
		else {
			SalesforceContactslog.info("New contact is not created:-------------- TC25--> Test Fail");
		}
	}
	@Test
	public void TC26() throws InterruptedException {	
           
		LogintoSFDC ();
		
		WebElement Contact = driver.findElement(By.id("Contact_Tab"));
		clickElement(Contact, "Contact");
		getTitle("Contacts");
		WebElement CreateNew = driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]"));
		clickElement(CreateNew, "Create New View");
		getTitle("Create New View");
		WebElement ViewName = driver.findElement(By.id("fname"));
		enterText(ViewName, "Bear", "View Name");
		WebElement ViewUname = driver.findElement(By.id("devname"));
		enterText(ViewUname,"Valley", "View Unique Name");
	
		WebElement Save = driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[1]"));
		clickElement(Save, "Save");
		//Error saving same account name again on rerunning the test case..it doesnot save.
		//extra steps to continue
	   WebElement Error = driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/table/tbody/tr[1]/td/span"));
	   SalesforceContactslog.info(Error.getText());
	   WebElement click =driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/table/tbody/tr[2]/td/a"));
	   clickElement(click, "link");
			
	
		getTitle("Contacts");
		Thread.sleep(5000);
		
		List<WebElement>Listoption = driver.findElements(By.xpath("//*[@class=\"title\"]/option"));
		Thread.sleep(5000);
		for(WebElement option:Listoption) {
			if(option.getText().equalsIgnoreCase("Bear")) {
				SalesforceContactslog.info(option.getText()+ " is displayed in the dropdown : ---------TC26-->Test Pass");
				break;
				
		}
			else {
			SalesforceContactslog.info(option.getText()+ " is not displayed in the dropdown : -----------TC26-->Test Fail");
	}
  
	}
	}
	
@Test
	public void TC27() throws InterruptedException {	
          
		LogintoSFDC ();

		WebElement Contact = driver.findElement(By.id("Contact_Tab"));
		clickElement(Contact, "Contact");
		getTitle("Contacts");
		WebElement Recent = driver.findElement(By.id("hotlist_mode"));
		Select select = new Select(Recent);
		select.selectByValue("2");
		WebElement RecentPg = driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[1]/h3"));
		if (RecentPg.getText().contains("Recent")){
			SalesforceContactslog.info("Recently created contact is displayed :-------- TC27 --> Test Pass");
			        
		}
		else {
			SalesforceContactslog.info("----------TC27 --> Test Fail");
		}
	}
	
@Test
	public void TC28() throws InterruptedException {	
		
		LogintoSFDC ();

		WebElement Contact = driver.findElement(By.id("Contact_Tab"));
		clickElement(Contact, "Contact");
		getTitle("Contacts");
		Thread.sleep(5000);
		WebElement Recent = driver.findElement(By.id("fcf"));
		Select select = new Select(Recent);
		select.selectByVisibleText("My Contacts"); 
		WebElement Go = driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[1]/input"));
		clickElement(Go, "Go");
		WebElement MyCnt = driver.findElement(By.xpath("//*[@id=\"00Bam000000EBhF_listSelect\"]/option[10]"));
		if (MyCnt.isDisplayed()) {
			SalesforceContactslog.info("My contacts View is displayed :---------- TC28--> Test Pass");
		}
		else {
			SalesforceContactslog.info("My contacts View is displayed :----------- TC28--> Test Fail");
		}
	}

@Test
	public void TC29() throws InterruptedException {	
		
		LogintoSFDC ();

		WebElement Contact = driver.findElement(By.id("Contact_Tab"));
		clickElement(Contact, "Contact");
		getTitle("Contacts");
		Thread.sleep(5000);
		WebElement Contactnm = driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/th/a"));
		clickElement(Contactnm, "Contact Name");
		WebElement Contactpg = driver.findElement(By.xpath("//h2[@class=\"topName\"]"));
		if (Contactpg.getText().contains("Cadence")) {
			SalesforceContactslog.info("Contact Page is displayed : --------------TC29-->Test Pass");
		}
		else {
			SalesforceContactslog.info("Contact Page is displayed :------------- TC29-->Test Fail");
		}
		
	}
@Test	
	public void TC30() throws InterruptedException {	
		
		LogintoSFDC ();
		WebElement Contact = driver.findElement(By.id("Contact_Tab"));
		clickElement(Contact, "Contact");
		getTitle("Contacts");
		Thread.sleep(5000);
		WebElement CreatenewView = driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]"));
		clickElement(CreatenewView, "Create New View");
		getTitle("Create New View");
		WebElement ViewUName = driver.findElement(By.id("devname"));
		enterText(ViewUName,"EFGH","View Unique Name");
		WebElement Save = driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[1]"));
		clickElement(Save, "Save");
		WebElement Errormsg = driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[2]/div[1]/div[2]/table/tbody/tr[1]/td[2]/div/div[2]"));
		String Errormessage = Errormsg.getText();
		String expected = "Error: You must enter a value";
	    if(Errormessage.equalsIgnoreCase(expected))
	    	SalesforceContactslog.info(Errormessage + " is displayed and matched expectation : -------TC30-->Test Pass");
	    else {
		SalesforceContactslog.info("Errormessage displayed doesnot match expectation :--------- TC30-->Test Pass");
        }
	}

@Test
	
	public void TC31() throws InterruptedException {	
		
		LogintoSFDC ();
		WebElement Contact = driver.findElement(By.id("Contact_Tab"));
		clickElement(Contact, "Contact");
		getTitle("Contacts");
		Thread.sleep(5000);
		WebElement CreatenewView = driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]"));
		clickElement(CreatenewView, "Create New View");
		getTitle("Create New View");
		WebElement ViewName = driver.findElement(By.id("fname"));
		ViewName.clear();
		enterText(ViewName,"ABCD","View Name");
		WebElement ViewUName = driver.findElement(By.id("devname"));
		ViewUName.clear();
		enterText(ViewUName,"EFGH","View Unique Name");
		WebElement Cancel = driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[2]"));
		clickElement(Cancel, "Cancel");
		getTitle("Home");
		Thread.sleep(5000);
		
		List<WebElement>Listoption = driver.findElements(By.xpath("//*[@id=\"fcf\"]/option"));
		Thread.sleep(5000);
		for(WebElement option:Listoption) {
			if(!option.getText().contains("ABCD")) {
				SalesforceContactslog.info("--------------TC31-->Test Pass");
				break;
				
		}
			else {
			SalesforceContactslog.info("----------------TC31-->Test Fail");
	}
		
        }
	}
@Test

	public void TC32() throws InterruptedException {
		
		LogintoSFDC ();
		WebElement Contact = driver.findElement(By.id("Contact_Tab"));
		clickElement(Contact, "Contact");
		getTitle("Home");
		WebElement New = driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input"));
		clickElement(New, "New");
		getTitle("Edit");
		WebElement LastName = driver.findElement(By.id("name_lastcon2"));
		enterText(LastName,"Indian", "Last Name");
		
		WebElement AcName = driver.findElement(By.id("con4"));
		enterText(AcName,"Global Media", "Account Name");
		
		WebElement SaveN = driver.findElement(By.xpath("//*[@id=\"topButtonRow\"]/input[2]"));
		clickElement(SaveN, "Save and New");
		
		if(driver.getTitle().contains("Indian")){
			SalesforceContactslog.info("New contact is created:----------- TC32--> Test Pass");
	
		}
		else {
			SalesforceContactslog.info("New contact is not created:---------- TC32--> Test Fail");
		}
		
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		SalesforceContacts ob = new SalesforceContacts();
		//ob.TC25();
		//ob.TC26();
		//ob.TC27();
		//ob.TC28();
		//ob.TC29();
		//ob.TC30();
		//ob.TC31();
		//ob.TC32();
		
		
        }
}
