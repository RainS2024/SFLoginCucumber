package com.testng.Salesforce;

import org.testng.annotations.Test;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;




public class SalesforceContacts extends Basecase {

	@Test(priority = -1 ,groups = {"Smoke"})
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
			System.out.println("New contact is created: ---------------TC25--> Test Pass");
	
		}
		else {
			System.out.println("New contact is not created:-------------- TC25--> Test Fail");
		}
		driver.close();
	}
	
	@Test(enabled = true)
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
	   System.out.println(Error.getText());
	   WebElement click =driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/table/tbody/tr[2]/td/a"));
	   clickElement(click, "link");
		
		
	
		getTitle("Contacts");
		Thread.sleep(5000);
		
		List<WebElement>Listoption = driver.findElements(By.xpath("//*[@class=\"title\"]/option"));
		Thread.sleep(5000);
		for(WebElement option:Listoption) {
			if(option.getText().equalsIgnoreCase("Bear")) {
				System.out.println(option.getText()+ " is displayed in the dropdown : ---------TC26-->Test Pass");
				break;
				
		}
			else {
			System.out.println(option.getText()+ " is not displayed in the dropdown : -----------TC26-->Test Fail");
	}
       driver.close();
	}
	}
	
	@Test(priority = 0,groups = {"Sanity"})
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
			System.out.println("Recently created contact is displayed :-------- TC27 --> Test Pass");
			        
		}
		else {
			System.out.println("----------TC27 --> Test Fail");
		}
		driver.close();
	}
	
	@Test(priority = -2, groups = {"Smoke"})
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
			System.out.println("My contacts View is displayed :---------- TC28--> Test Pass");
		}
		else {
			System.out.println("My contacts View is displayed :----------- TC28--> Test Fail");
		}
			driver.close();
	}
	
	@Test(priority = 3,groups = {"Sanity"})
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
			System.out.println("Contact Page is displayed : --------------TC29-->Test Pass");
		}
		else {
			System.out.println("Contact Page is displayed :------------- TC29-->Test Fail");
		}
		driver.close();
		
	}
	
	@Test(priority = 0 ,groups = {"Smoke"})
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
	    	System.out.println(Errormessage + " is displayed and matched expectation : -------TC30-->Test Pass");
	    else {
		System.out.println("Errormessage displayed doesnot match expectation :--------- TC30-->Test Pass");
        }
	    driver.close();
	}
	
	@Test(priority = 2,groups = {"Sanity"})
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
				System.out.println("--------------TC31-->Test Pass");
				break;
				
		}
			else {
			System.out.println("----------------TC31-->Test Fail");
	}
        driver.close();
		
        }
	}
	
	@Test(priority = 1,groups = {"Sanity"})
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
			System.out.println("New contact is created:----------- TC32--> Test Pass");
	
		}
		else {
			System.out.println("New contact is not created:---------- TC32--> Test Fail");
		}
		
	   driver.close();
		
	}
	
	
        }

