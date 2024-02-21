package com.Salesforce.Automation;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Salesforce.base.SFDCBasecase;
import com.Salesforce.utilities.ExtentReportsUtility;
@Listeners(com.Salesforce.utilities.SFDCListenerUtilities.class)
public class SalesforceRandomScenario extends SFDCBasecase{
	protected static Logger SalesforceRandomScenariolog = LogManager.getLogger();
	protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();
	@Test
	public void TC33() throws InterruptedException {
		LogintoSFDC ();
		WebElement Home = driver.findElement(By.id("home_Tab"));
		clickElement(Home, "Home Tab");
		getTitle("Salesforce");
		WebElement Name = driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1/a"));
		System.out.println(Name.getText()+" is displayed as link");
		clickElement(Name, "User Name");
		String expected = "User: Rain Sing ~ Salesforce - Developer Edition";
		Assert.assertEquals(driver.getTitle(), expected);
		
	}
	@Test
	public void TC34() throws InterruptedException {	
		LogintoSFDC ();
		WebElement Home = driver.findElement(By.id("home_Tab"));
		clickElement(Home, "Home Tab");
		getTitle("Salesforce");
		WebElement Name = driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1/a"));
		System.out.println(Name.getText()+" is displayed as link");
		clickElement(Name, "User First and Last Name");
		getTitle("Rain Sing");
		WebElement edit = driver.findElement(By.xpath("//*[@id=\"chatterTab\"]/div[2]/div[2]/div[1]/h3/div/div/a/img" ));
		clickElement(edit,"Edit Profile");
		WebElement ifrm = driver.findElement(By.id("contactInfoContentId" ));	
		driver.switchTo().frame(ifrm);
		WebElement Abt = driver.findElement(By.xpath("//*[@id=\"aboutTab\"]/a"));
		clickElement(Abt,"About Tab");
		WebElement Lstnm = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
		enterText(Lstnm, "Abcd", "LastName") ;
		WebElement SaveAll = driver.findElement(By.xpath("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]"));
		clickElement(SaveAll,"Save All");
		driver.switchTo().parentFrame();
		
		WebElement Usrnm = driver.findElement(By.xpath("//*[@id=\"tailBreadcrumbNode\"]"));
		System.out.println(Usrnm.getText()+ " is the updated name displayed at the top left");
		WebElement UsrMenubtn = driver.findElement(By.id("userNavLabel"));
		System.out.println(UsrMenubtn.getText()+ " is the updated name displayed on the Menu Button ");
		String expected = "User: Rain Abcd ~ Salesforce - Developer Edition";
		AssertJUnit.assertEquals(driver.getTitle(), expected);
		
	}
	
	
	
	
	    @Test
	    
    	public void TC35() throws InterruptedException {	
		LogintoSFDC ();
		WebElement plustab = driver.findElement(By.cssSelector("#AllTab_Tab > a > img"));
		clickElement(plustab, "+ Tab");
		getTitle("All Tabs");
		WebElement cstmtab = driver.findElement(By.xpath("//input[@class=\"btnImportant\"]"));
		clickElement(cstmtab, "Customize My Tabs");
		getTitle("Customize");
		
		
		WebElement SelectTab1  = driver.findElement(By.id("duel_select_1"));	
		Select select = new Select(SelectTab1);
		try{select.selectByVisibleText("Campaigns");}
		catch(Exception e){
			java.util.List<WebElement> selectoptions1=driver.findElements(By.xpath("//*[@id=\"duel_select_1\"]/option"));
		    for(WebElement options:selectoptions1) {
			if(!options.getText().contains("Campaigns")) {
				TC35addremovedtab();
				extentreport.logTestInfo("Added tab Campaign before hitting remove");
			}
		
		
		WebElement Remove  = driver.findElement(By.xpath("//*[@id=\"duel_select_0_left\"]/img"));
		clickElement(Remove, "Remove");
		
		WebElement Save = driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
		clickElement(Save, "Save");	
		getTitle("All Tabs");
		String expected = "Campaigns";
		java.util.List<WebElement>Tablist = driver.findElements(By.xpath("//*[@id=\"tabBar\"]/li"));
		for(WebElement Tab:Tablist) {
			AssertJUnit.assertFalse(Tab.getText().equalsIgnoreCase(expected));
		
		}
		LogoutfromSFDC ();
		LogintoSFDC();
		
		java.util.List<WebElement>Tablist1 = driver.findElements(By.xpath("//*[@id=\"tabBar\"]/li"));
		for(WebElement Tab:Tablist1) {
			
			if(!Tab.getText().contains("Campaigns")) {
				AssertJUnit.assertFalse(Tab.getText().equalsIgnoreCase(expected));
			
		}
		}
		    }}
	}
	@Test
	public void TC36() throws InterruptedException {	
		
		LogintoSFDC ();
		WebElement Home = driver.findElement(By.id("home_Tab"));
		clickElement(Home, "Home Tab");
		getTitle("Salesforce");
		WebElement Date  = driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[2]/a"));
		SimpleDateFormat curtime = new SimpleDateFormat("EEEE MMMM dd, yyyy");  
		Date thisDate = new Date();  
		System.out.println(curtime.format(thisDate));  
		String today = curtime.format(thisDate); 
        SoftAssert sb=new SoftAssert();
        AssertJUnit.assertEquals(Date.getText(), today);
		
		clickElement(Date, "Date link");
		getTitle("Rain");
		WebElement time = driver.findElement(By.xpath("//*[@id=\"p:f:j_id25:j_id61:28:j_id64\"]/a"));
		clickElement(time, "8 pm time link");
		getTitle("Calendar");
		WebElement combo = driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[4]/table/tbody/tr[2]/td[2]/div/a/img"));
		clickElement(combo, "Subject combo icon");
		String parentwindow = driver.getWindowHandle();
		System.out.println("Parent window---->"+ parentwindow);
		Set<String> windowhandles = driver.getWindowHandles();
		System.out.println(windowhandles + ",");
		System.out.println("Total no. of window handles-->" + windowhandles.size());
		for(String windowhandle:windowhandles) {
			if(!parentwindow.contentEquals(windowhandle)) {
				driver.switchTo().window(windowhandle);
				break;
			}
		}
		WebElement other = driver.findElement(By.xpath("/html/body/div[2]/ul/li[5]/a"));
		clickElement(other, "Other from Combobox");
		driver.switchTo().window(parentwindow);
		String currentwh = driver.getWindowHandle();
		System.out.println(currentwh);
		
		WebElement subject = driver.findElement(By.id("evt5"));
		if(subject.isDisplayed())
		System.out.println("The subject field has Other entered");
	
		//the time is not getting selected--no execution on this command line150-160
		
		WebElement End = driver.findElement(By.xpath("//*[@id=\"EndDateTime_time\"]"));
		clickElement(End, "End Time");
		
		java.util.List<WebElement>Hourlist = driver.findElements(By.xpath("/html/body/div[2]/div"));
		for(WebElement Hour:Hourlist) {
			if(Hour.getText().equalsIgnoreCase("9:00 PM")) {
				Hour.click();
				System.out.println("9.00PM was selected from dropdown");
				break;				
			}		
	}
		
		WebElement save = driver.findElement(By.xpath("//*[@id=\"topButtonRow\"]/input[1]"));
		clickElement(save, "Save");
		getTitle("Calendar");
		WebElement link = driver.findElement(By.xpath("//*[@id=\"p:f:j_id25:j_id69:28:j_id71:0:j_id72:calendarEvent:j_id84\"]/a/span"));
		AssertJUnit.assertTrue(link.isDisplayed());
		sb.assertAll();
		
		
	}
	
	@Test
	public void TC37() throws InterruptedException {
		
		LogintoSFDC();
		WebElement Home = driver.findElement(By.id("home_Tab"));
		clickElement(Home, "Home Tab");
		getTitle("Salesforce");
		WebElement Date  = driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[2]/a"));
		Date thisdate = new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("EEEE MMMM d, yyyy");
		String date = sdf.format(thisdate);
		SoftAssert sb=new SoftAssert();
        AssertJUnit.assertEquals(Date.getText(), date);
		
		clickElement(Date, "Date link");
		getTitle("Rain");
		WebElement time = driver.findElement(By.xpath("//*[@id=\"p:f:j_id25:j_id61:20:j_id64\"]/a"));
		clickElement(time, "4 pm time link");
		getTitle("Calendar");
		WebElement combo = driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[4]/table/tbody/tr[2]/td[2]/div/a/img"));
		clickElement(combo, "Subject combo icon");
		String parentwindow = driver.getWindowHandle();
		System.out.println("Parent window---->"+ parentwindow);
		Set<String> windowhandles = driver.getWindowHandles();
		System.out.println(windowhandles + ",");
		System.out.println("Total no. of window handles-->" + windowhandles.size());
		for(String windowhandle:windowhandles) {
			if(!parentwindow.contentEquals(windowhandle)) {
				driver.switchTo().window(windowhandle);
				break;
			}
		}
		WebElement other = driver.findElement(By.xpath("/html/body/div[2]/ul/li[5]/a"));
		clickElement(other, "Other from Combobox");
		driver.switchTo().window(parentwindow);
		String currentwh = driver.getWindowHandle();
		System.out.println(currentwh);
		
		WebElement subject = driver.findElement(By.id("evt5"));
		if(subject.isDisplayed())
		System.out.println("The subject field has Other entered");
		WebElement End = driver.findElement(By.xpath("//*[@id=\"EndDateTime_time\"]"));
		clickElement(End, "End Time");
		
		java.util.List<WebElement>Hourlist = driver.findElements(By.xpath("/html/body/div[2]/div"));
		for(WebElement Hour:Hourlist) {
			if(Hour.getText().equalsIgnoreCase("7:00 PM")) {
				Hour.click();
				System.out.println("7.00 pm was selected from dropdown");
				break;						
			}
			
	}
		WebElement Recur = driver.findElement(By.id("IsRecurrence"));
		clickElement(Recur, "Create Recurring Series of Events");
		WebElement Freq = driver.findElement(By.xpath("//*[@id=\"recpat\"]/table/tbody/tr[1]/td[1]/label"));
		if(Freq.isDisplayed()== true) {
			System.out.println("Frequency is displayed");
		}
		else {
			System.out.println("Frequency is not displayed");
			
		}
		
		WebElement RecurStart = driver.findElement(By.xpath("//*[@id=\"recpat\"]/table/tbody/tr[2]/td[1]/label"));
		AssertJUnit.assertTrue(RecurStart.isDisplayed());
		
		
		
		WebElement RecurEnd = driver.findElement(By.xpath("//*[@id=\"recpat\"]/table/tbody/tr[3]/td[1]/label"));
		AssertJUnit.assertTrue(RecurEnd.isDisplayed());
		
		WebElement Radio = driver.findElement(By.id("rectypeftw"));
		clickElement(Radio, "Weekly radio button ");
		
		WebElement Every = driver.findElement(By.xpath("//*[@id=\"di\"]"));
		String Value = Every.getAttribute("Value");
		
		if (Value.equalsIgnoreCase("1")) {
			System.out.println("Recurs Every..' section display has '1' entered in it");
		}
		else {
			System.out.println("Recurs Every..' section display doesnot have '1' entered in it");
			
		}
		WebElement Crntday = driver.findElement(By.xpath("//*[@id=\"64\"]"));
		//sb.assertTrue(Crntday.isSelected());
		if (Crntday.isSelected()) {
			System.out.println("Current day of the week is checked.");
		}
		else {
			System.out.println("Current day of the week is not checked ");
				
		}
		
		WebElement EndDate = driver.findElement(By.id("RecurrenceEndDateOnly"));
		clickElement(EndDate, "End Date");
		
		
		java.util.List<WebElement>Calender = driver.findElements(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[2]/div[2]/table/tbody/tr/td"));
		for(WebElement Date1:Calender) {
			if(Date1.getText().equalsIgnoreCase("24")) {
				Date1.click();
				System.out.println("2wks from today was selected from dropdown");
				break;				

			}		
	}
	    
		WebElement save = driver.findElement(By.xpath("//*[@id=\"topButtonRow\"]/input[1]"));
		waitforvisibility(save, 10, "Save");
		clickElement(save, "Save");
		getTitle("Rain");
		WebElement link = driver.findElement(By.xpath("//*[@id=\"p:f:j_id25:j_id69:20:j_id71:0:j_id72:calendarEvent:j_id84\"]/a/span"));
		if (link.isDisplayed()) {
			System.out.println("'Other' event  in the time slot 4:00PM to 7:00PM, as a link.");		
		}
		else {
			System.out.println("'Other' event in the time slot 4:00PM to 7:00PM, as a link is not created.");
		}
		//sb.assertTrue(link.isDisplayed());
		
		WebElement Mnthview = driver.findElement(By.xpath("//*[@id=\"bCalDiv\"]/div/div[2]/span[2]/a[3]/img"));
		clickElement(Mnthview, "Month View");
		WebElement mnthviewcal = driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h1"));
		//sb.assertTrue(mnthviewcal.isDisplayed());
		if (mnthviewcal.isDisplayed()) {
			System.out.println("The 'Calendar for FirstName LastName - Month View' page is displayed");
			
		}
		else {
			System.out.println(" The 'Calendar for FirstName LastName - Month View' page is not displayed");
		}
		
		java.util.List<WebElement>Calender1 = driver.findElements(By.xpath("//*[@id=\"bodyCell\"]/div[2]/div[1]/div[1]/table/tbody/tr/td"));
		for(WebElement Date1:Calender1) {
			if(Date1.getText().contains("4:00 PM - 7:00 PM")) {
				System.out.println(Date1.getText());						
			}			
		
		sb.assertAll();
	}
	
	
	
	}
	
	
	
}
