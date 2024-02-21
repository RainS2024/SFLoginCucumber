package com.Salesforce.Automation;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Salesforce.base.SFDCBasecase;
import com.Salesforce.utilities.ExtentReportsUtility;
@Listeners(com.Salesforce.utilities.SFDCListenerUtilities.class)


public class Salesforceleads extends SFDCBasecase {
	protected static Logger Salesforceleadslog = LogManager.getLogger();
	protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();
	@Test
	public void TC20Leadtab () throws InterruptedException {
		LogintoSFDC();
		WebElement Leadtab = driver.findElement(By.xpath("//a[@title=\"Leads Tab\"]"));
		Leadtab.click();
		WebElement LeadHome= driver.findElement(By.xpath("//h2[@class=\"pageDescription\"]"));
		AssertJUnit.assertTrue(LeadHome.isDisplayed() && LeadHome.getText().equalsIgnoreCase("Home"));
		//System.out.println("Lead" + Home + "page is displayed : TC20-leadsTab --> Test Pass");
		//LogoutfromSFDC();
	}
	@Test
	public void TC21Leadsselectview() throws InterruptedException {
		LogintoSFDC();
		WebElement Leadtab = driver.findElement(By.xpath("//a[@title=\"Leads Tab\"]"));
		Leadtab.click();
		WebElement LeadHome= driver.findElement(By.xpath("//h2[@class=\"pageDescription\"]"));
		String Home = LeadHome.getText();
		System.out.println("Lead" + Home + "page is displayed");
		WebElement drpdwn = driver.findElement(By.xpath("//select[@id = \"fcf\"]"));
		Select drpdwnmenu = new Select (drpdwn);
		java.util.List<WebElement> options  =drpdwnmenu.getOptions();
		List<String>menuoptions = new ArrayList<String>();
		for(WebElement c:options) {
			menuoptions.add(c.getText());
		}
		System.out.println("The options in the dropdown menu are:");
		for(String s:menuoptions) {
			System.out.println(s);
		}
		
		System.out.println("The size of drop down menu is :" + menuoptions.size());
		
		ArrayList<String> expected =new ArrayList<String>(Arrays.asList("All Open Leads","My Unread Leads","Recently Viewed Leads","Today's Leads")); 
		System.out.println("The size of expected drop down menu is :" + expected.size()); 
  	     
		//Assert.assertEquals(menuoptions, expected);
		if(menuoptions.containsAll(expected)) {
			System.out.println("Drop down list match the expected list :TC21-leadsSelectView Test Pass");
		}
			  
		/*if(options.size()!=expected.size()) {
  		System.out.println("Drop down list does not match the expected list :TC21-leadsSelectView Test case failed");
      }
		 else{
		  		System.out.println("Drop down list match the expected list :TC21-leadsSelectView Test Pass");
		      }
		//LogoutfromSFDC();*/
	}
@Test
	public void TC22Defaultview() throws InterruptedException {
	
		LogintoSFDC();
		
		WebElement Leadtab = driver.findElement(By.xpath("//a[@title=\"Leads Tab\"]"));
		Leadtab.click();
		WebElement LeadHome= driver.findElement(By.xpath("//h2[@class=\"pageDescription\"]"));
		String Home = LeadHome.getText();
		System.out.println("Lead" + Home + "page is displayed");
		WebElement drpdwn = driver.findElement(By.xpath("//select[@id = \"fcf\"]"));
		Select drpdwnmenu = new Select (drpdwn);
		drpdwnmenu.selectByVisibleText("My Unread Leads");
		//Thread.sleep(5000);
		LogoutfromSFDC();
		LogintoSFDC();
		//Thread.sleep(2000);
		WebElement leadsbut = driver.findElement(By.xpath("//a[@title=\"Leads Tab\"]"));
		leadsbut.click();
		WebElement LeadHome1= driver.findElement(By.xpath("//h2[@class=\"pageDescription\"]"));
		String Home1 = LeadHome1.getText();
		System.out.println("Lead" + Home1 + "page is displayed");
		WebElement btn = driver.findElement(By.xpath("//input[@value=\" Go! \"]"));
		waitforvisibility(btn, 20, "Go Button");
		btn.click();
		WebElement Unreadlead = driver.findElement(By.id("00Bam000000EBZs_listSelect"));
		WebElement MyUnreadlead = driver.findElement(By.xpath("//*[@id=\"00Bam000000EBZs_listSelect\"]/option[2]"));
		String Text = MyUnreadlead.getText();
		String Expected = "My Unread Leads";
		AssertJUnit.assertTrue(Unreadlead.isDisplayed());
		AssertJUnit.assertEquals(Text, Expected);
		
		/*if (Unreadlead.isDisplayed()&& Text.equalsIgnoreCase(Expected)) {
			System.out.println("My Unread Leads is displayed as default:TC22-defaultView-->Test Pass");
		}
		else {
		System.out.println("My Unread Leads is not displayed as default:TC22-defaultView-->Test Fail");
	}*/	
		
	}

@Test
	public void TC23() throws InterruptedException {
		
		LogintoSFDC();
		
		WebElement Leadtab = driver.findElement(By.xpath("//a[@title=\"Leads Tab\"]"));
		Leadtab.click();
		WebElement LeadHome= driver.findElement(By.xpath("//h2[@class=\"pageDescription\"]"));
		String Home = LeadHome.getText();
		System.out.println("Lead " + Home + "page is displayed");
		Thread.sleep(2000);
		WebElement drpdwn = driver.findElement(By.xpath("//select[@id = \"fcf\"]"));
		Select drpdwnmenu = new Select (drpdwn);
		drpdwnmenu.selectByVisibleText("Today's Leads");
		WebElement gobtn = driver.findElement(By.xpath("//input[@value=\" Go! \"]"));
		waitforvisibility(gobtn, 20, "Go Button");
		gobtn.click();
		WebElement display = driver.findElement(By.id("00Bam000000EBgY_listSelect"));
		WebElement todaylead = driver.findElement(By.xpath("//*[@id=\"00Bam000000EBgY_listSelect\"]/option[4]"));
		String Text =todaylead.getText();
		String Expected = "Today's Leads";
		Assert.assertTrue(display.isDisplayed());
		Assert.assertEquals(Text, Expected);
		/*if (display.isDisplayed()&& Text.equalsIgnoreCase(Expected)) {
			System.out.println("Today's Leads is displayed : TC23 --> Test Pass");}
			else {
				System.out.println("TC23 --> Test Fail");
			}	*/
	
		
}
@Test
	public void TC24() throws InterruptedException {
		LogintoSFDC();
		WebElement Leadtab = driver.findElement(By.xpath("//a[@title=\"Leads Tab\"]"));
		Leadtab.click();
		WebElement LeadHome= driver.findElement(By.xpath("//h2[@class=\"pageDescription\"]"));
		String Home = LeadHome.getText();
		System.out.println("Lead " + Home + "page is displayed");
		WebElement newbtn = driver.findElement(By.xpath("//input[@value=\" New \"]"));
		newbtn.click();
		WebElement newpg = driver.findElement(By.xpath("//h2[@class=\"pageDescription\"]"));
		 String titlepg = newpg.getText();
		 String Expected = "New Lead";
		 if(titlepg.equalsIgnoreCase(Expected)) {
			 System.out.println ( titlepg + " creation page is displayed");
		 }
		 WebElement lstnm = driver.findElement(By.id("name_lastlea2"));
		 lstnm.sendKeys("ABCD");
		 WebElement cmpny = driver.findElement(By.id("lea3"));
		 cmpny.sendKeys("ABCD");
		 WebElement savebtn = driver.findElement(By.xpath("//*[@id=\"topButtonRow\"]/input[1]"));
			savebtn.click();
			 WebElement newlead = driver.findElement(By.xpath("	//h2[@class=\"topName\"]"));
			String Txt = newlead.getText();
			String Expctd = "ABCD";
			AssertJUnit.assertEquals(Txt, Expctd);
			/*if (Txt.equalsIgnoreCase(Expctd)) {
				System.out.println ("ABCD is entered in the last name field");
				System.out.println ("ABCD is entered in the company field");
				System.out.println ("New Lead view page by the name ABCD is created and displayed");
				System.out.println ("Check New button on Leads Home -TC24--> Test Passed ");
			}
			else {
				System.out.println("TC24 --> Test Fail");
			}*/	
		
		

	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		Salesforceleads ob =new Salesforceleads();
		//ob.TC20Leadtab();
		//ob.TC21Leadsselectview();
		//ob.TC22Defaultview();
		//ob.TC23();
	   //ob.TC24();
		
}
}