package com.Salesforce.Automation;

import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Salesforce.base.SFDCBasecase;
import com.Salesforce.utilities.ExtentReportsUtility;
@Listeners(com.Salesforce.utilities.SFDCListenerUtilities.class)

public class SalesforceUsrmenu extends SFDCBasecase{
	protected static Logger SalesforceUsermenulog = LogManager.getLogger();
	protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();
@Test
	public void TC05() throws InterruptedException {
		LogintoSFDC ();
		WebElement usrmenu = driver.findElement(By.id("userNav" ));
		clickElement(usrmenu,"usrmenudrpdwn");
		java.util.List<WebElement> menuitem =usrmenu.findElements(By.tagName("a"));
		List <String> menu = new ArrayList<String>();
		for(WebElement c:menuitem) {
			menu.add(c.getText());
    	}
		System.out.println("The options in the usrdropdown menu are:");
		System.out.println(menu);
		
		ArrayList<String> expected =new ArrayList<String>(Arrays.asList("My Profile", "My Settings", "Developer Console", "Switch to Lightning Experience" ,"Logout" )); 
		Assert.assertEqualsNoOrder(menu, expected);
		
	}
	@Test
	public void TC06() throws InterruptedException {
		
		LogintoSFDC ();
		
		WebElement usrmenu = driver.findElement(By.id("userNav" ));
		clickElement(usrmenu,"usrmenudrpdwn");
		WebElement myprfl = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[1]" ));
		clickElement(myprfl,"My Profile");
		getTitle("Rain");
		WebElement edit = driver.findElement(By.xpath("//*[@id=\"chatterTab\"]/div[2]/div[2]/div[1]/h3/div/div/a/img" ));
		clickElement(edit,"Edit Profile");
		String currentwh = driver.getWindowHandle();
		System.out.println("current window handle is: " + currentwh);

		Set<String> windowhandles = driver.getWindowHandles();
	System.out.println("Total no. of window handles-->" + windowhandles.size());
	
	 WebElement editprfl = driver.findElement(By.xpath("//*[@id=\"contactInfoTitle\"]" ));
	String newwindow = editprfl.getText();
	System.out.println("The new window opened is : " + newwindow);
	Set<String> newwindowhandles = driver.getWindowHandles();
	System.out.println(newwindowhandles);
	
	//switch to iframe to select About Tab
	WebElement ifrm = driver.findElement(By.id("contactInfoContentId" ));	
	driver.switchTo().frame(ifrm);
	WebElement Abt = driver.findElement(By.xpath("//*[@id=\"aboutTab\"]/a"));
	clickElement(Abt,"About Tab");
	WebElement Lstnm = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
	enterText(Lstnm, "Sing", "LastName") ;
	WebElement SaveAll = driver.findElement(By.xpath("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]"));
	clickElement(SaveAll,"Save All");
	driver.switchTo().parentFrame();
	String NewTitle = driver.getTitle();
	String expected = "User: Rain Sing ~ Salesforce - Developer Edition";
	if(NewTitle.equalsIgnoreCase(expected)) {
		System.out.println(NewTitle + " is displayed");
		}
	else  {

		System.out.println (expected + "page is not displayed");
	
	}
	//Post Link
	
	WebElement Post = driver.findElement(By.xpath("//*[@id=\"publisherAttachTextPost\"]/span[1]"));
	clickElement(Post,"Post");
	Thread.sleep(5000);
	WebElement ifrm2 = driver.findElement(By.xpath("//*[@id=\"cke_43_contents\"]/iframe" ));	
	driver.switchTo().frame(ifrm2);
	WebElement txtbx = driver.findElement(By.xpath("/html/body"));
	enterText(txtbx, "Entering Txt in Post TextBox", "PostTxtArea");
	Thread.sleep(10000);
	driver.switchTo().parentFrame();
	WebElement shr = driver.findElement(By.xpath("//input[@id=\"publishersharebutton\"]"));//switched out to parent frame.
	Actions action = new Actions(driver);
	action.moveToElement(shr).build().perform();
	clickElement(shr,"Share");	
	WebElement comment = driver.findElement(By.xpath("//*[@class=\"feeditemtext cxfeeditemtext\"]"));
	waitforvisibility(comment, 40,"Comment");
	if (comment.isDisplayed()&& comment.getText().contains("Entering")) {
		System.out.println("Entered Text is displayed on the page");
	}
	else {
		System.out.println("Entered Text is not displayed on the page");
	}
	
	//File link
	
	WebElement File = driver.findElement(By.xpath("//a[@id=\"publisherAttachContentPost\"]"));
	waitforvisibility(File, 30, "File Link");
	clickElement(File,"File link");
	
	WebElement Fileupload = driver.findElement(By.xpath("//*[@id=\"chatterUploadFileAction\"]"));
	waitforvisibility(Fileupload, 40, "File uploadLink");
	clickElement(Fileupload,"File upload from computer link");
	
	WebElement Choosefile = driver.findElement(By.xpath("//input[@id=\"chatterFile\"]"));
	waitforvisibility(Choosefile, 40, "ChooseFileLink");
	
	Choosefile.sendKeys("/Users/niwas/Desktop/fl.jpeg");
	
	WebElement shr1 = driver.findElement(By.xpath("//input[@id=\"publishersharebutton\"]"));//switched out to parent frame.
	waitforvisibility(shr1, 40, "Sharebutton");
	Actions action1 = new Actions(driver);
	action1.moveToElement(shr1).build().perform();
	clickElement(shr1,"Share");
	
	WebElement Post1 = driver.findElement(By.xpath("//*[@class=\"thumbnailCell\"]"));
	waitforvisibility(Post1,40,"File Uploading");
	if(Post1.isDisplayed()) {
		System.out.println("Selected File got posted");
	}
	else {
		System.out.println("Selected File was not posted");
	}
	
	//Photo upload
	
	WebElement Moderator = driver.findElement(By.xpath("//span[@id=\"displayBadge\"]"));
	Actions action3 = new Actions(driver);
	action3.moveToElement(Moderator).build().perform();
	Thread.sleep(5000);
	WebElement Addphoto = driver.findElement(By.xpath("//a[@id=\"uploadLink\"]"));
	clickElement(Addphoto,"AddPhotoLink");
	Thread.sleep(2000);
	WebElement ifrm3 = driver.findElement(By.xpath("//iframe[@id=\"uploadPhotoContentId\"]" ));	
	driver.switchTo().frame(ifrm3);
	WebElement Choosephoto = driver.findElement(By.xpath("//input[@id=\"j_id0:uploadFileForm:uploadInputFile\"]"));
	Choosephoto.sendKeys("/Users/niwas/Desktop/bf.jpeg");
	
	
	WebElement Save = driver.findElement(By.xpath("//input[@id=\"j_id0:uploadFileForm:uploadBtn\"]"));
	waitforvisibility(Save, 40, "Save");
	clickElement(Save,"Save");
	
	
	WebElement Save2 = driver.findElement(By.xpath("//input[@id=\"j_id0:j_id7:save\"]"));
	waitforvisibility(Save2, 40, "Save2");
	clickElement(Save2,"Save Again");
	WebElement Photo = driver.findElement(By.xpath("//*[@id=\"photoSection\"]/span[2]/img[1]"));
	if(Photo.isDisplayed()) {
		System.out.println("The Uploaded Photo is displayed: TC06-->Test Pass ");
	}
	else {
	System.out.println("The Uploaded Photo is not displayed :TC06--> Test Fail");}

	
	}
	@Test
	public void TC07() throws InterruptedException {
		    
		    LogintoSFDC ();
			
			WebElement usrmenu = driver.findElement(By.id("userNav" ));
			clickElement(usrmenu,"usrmenudrpdwn");
			WebElement Mysetting = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[2]" ));
			clickElement(Mysetting,"MySetting");
			getTitle("Rain");
			
		   WebElement Prsnl = driver.findElement(By.xpath("//*[@id=\"PersonalInfo\"]/a" ));
			clickElement(Prsnl,"Personal");
			
			WebElement Lginhistry = driver.findElement(By.id("LoginHistory_font" ));
			clickElement(Lginhistry,"usrmenudrpdwn");
			getTitle("History");
			
			WebElement Dsply = driver.findElement(By.id("DisplayAndLayout_font" ));
			clickElement(Dsply,"Display and LayOut");
			
			WebElement Cstmtb = driver.findElement(By.id("CustomizeTabs_font" ));
			clickElement(Cstmtb,"CustomizeTabs_font");
			
			WebElement CstmApp = driver.findElement(By.id("p4" ));
			Select select = new Select(CstmApp);
			select.selectByVisibleText("Salesforce Chatter");
			
			WebElement AvlblTab = driver.findElement(By.id("duel_select_0" ));
			Select select1 = new Select(AvlblTab);
			select1.selectByVisibleText("Reports");
			
			WebElement Add = driver.findElement(By.xpath("//*[@id=\"duel_select_0_right\"]" ));
			clickElement(Add,"Add");
			
			
			List<WebElement>Tablist = driver.findElements(By.xpath("//*[@id=\"tabBar\"]/li"));
			for(WebElement Tab: Tablist) {
				if(Tab.getAttribute("id").contains("report_Tab")) {
					System.out.println("Report link is added in the links available on top of salesforce page");
					break;
					}
					
				}
			WebElement Emailsetup = driver.findElement(By.id("EmailSetup" ));
			clickElement(Emailsetup,"Emailsetup");	
			
			WebElement MyEmailsetting = driver.findElement(By.id("EmailSettings_font"));
			clickElement(MyEmailsetting,"MyEmailSetting");	
			
			WebElement Emailname = driver.findElement(By.id("sender_name"));
			Emailname.clear();
			Emailname.sendKeys("Rain Sing");
			WebElement Emailaddress = driver.findElement(By.id("sender_email"));
			Emailaddress.clear();
			Emailaddress.sendKeys("garima.symbiosis@gmail.com");
			WebElement AutoBcc = driver.findElement(By.id("auto_bcc1"));
			if (AutoBcc.isSelected()) {
				System.out.println("AutoBcc is selected");}
			else {
				AutoBcc.click();
				System.out.println("AutoBcc is selected");
			}
			WebElement Save1 = driver.findElement(By.xpath("//input[@value=\" Save \"]"));
			clickElement(Save1,"Save");
			
			WebElement CalRem = driver.findElement(By.id("CalendarAndReminders_font"));
			clickElement(CalRem,"Calender and Reminder");
			//String Parenthandle = driver.getWindowHandle();
			//System.out.println("The parent handle is " + Parenthandle);
			
			WebElement ActRem = driver.findElement(By.id("Reminders_font"));
			clickElement(ActRem,"Activity Reminders");
			
			WebElement TstRem = driver.findElement(By.id("testbtn"));
			clickElement(TstRem,"Open Test Reminder");
			String Parenthandle = driver.getWindowHandle();
			/*System.out.println(Parenthandle +"is the parenthandle");
			Set<String> newwindowhandles = driver.getWindowHandles();
			System.out.println(newwindowhandles.size() + " ,");
			//not able to switch to the reminder and get title
			//I don't see different window handles or alert.
			String newwin= driver.getTitle();
			System.out.println(newwin);
			driver.switchTo().activeElement();
			String handle = driver.getWindowHandle();
			System.out.println("The current handle is " + handle);
			//driver.switchTo().window(handle);
			//Thread.sleep(5000);
			/*WebElement dismissall = driver.findElement(By.id("dismiss_all"));
			Actions action = new Actions(driver);
			action.moveToElement(dismissall).build().perform();
			action.click().build().perform();*/
			//driver.quit();
			
			//clickElement(DismissAll,"Dismiss All");
			
			//String Popup=driver.getTitle();
			//System.out.println(Popup + "is displayed");*/
	}
	
	@Test
	public void TC08() throws InterruptedException {
		LogintoSFDC ();
		WebElement usrmenu = driver.findElement(By.id("userNav" ));
		clickElement(usrmenu,"usrmenudrpdwn");
		WebElement dvlprcnsl = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[3]" ));
		clickElement(dvlprcnsl,"Developer console");
		String parentwh = driver.getWindowHandle();
		System.out.println("Parent window handle is: " + parentwh);
		Set<String> windowhandles = driver.getWindowHandles();
		System.out.println("Total no. of window handles-->" + windowhandles.size());
		System.out.println(windowhandles);
		for(String handle : windowhandles) {
			System.out.println("current window handle = " + handle);
			if(!handle.equals(parentwh)) {
				driver.switchTo().window(handle);
				String url = driver.getCurrentUrl();
				System.out.println("The url of current window is -->" + url);
				driver.close();
			}
			
		}
		driver.switchTo().window(parentwh);
		String Title = driver.getTitle();
		System.out.println("The Title of the parent window is --->" + Title);
		
	}
	@Test
	public void TC09() throws InterruptedException {
         
		LogintoSFDC ();
		
		WebElement drpdwn = driver.findElement(By.id("userNav-arrow"));
		clickElement(drpdwn, "UsrmenuDropdown");
		WebElement usrmenu = driver.findElement(By.id("userNav" ));
		if(usrmenu.isDisplayed()) {
			System.out.println("The Usermenu is displayed");
		}

		WebElement logout = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		clickElement(logout, "LogOut");
		
		
		Thread.sleep(3000);
		WebElement logoutpg = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		if (logoutpg.isDisplayed()) {
			System.out.println("SFDC LogOut successful and application is closed: TC09--->Test Pass");}
			else {
				System.out.println("TC09--->Test Fail");	
			}
			
	}
	@Test
	public void Delprcnslerrortxt() throws InterruptedException {
		
		LogintoSFDC ();
		WebElement usrmenu = driver.findElement(By.id("userNav" ));
		clickElement(usrmenu,"usrmenudrpdwn");
		WebElement dvlprcnsl = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[3]" ));
		clickElement(dvlprcnsl,"Developer console");
		String parenthndl = driver.getWindowHandle();
		System.out.println("The parent windowhandle is " + parenthndl);
		Set<String>windowhndls=driver.getWindowHandles();
		System.out.println("The windowhandles are" + windowhndls);
		for(String handle:windowhndls) {
			if(!handle.equals(parenthndl)) {
				driver.switchTo().window(handle);
				System.out.println("The current windowhandle is " + handle);
				WebElement testbtn = driver.findElement(By.xpath("//*[@id=\"testMenuEntry-btnWrap\"]" ));
				clickElement(testbtn,"TestMenu");
				WebElement runall = driver.findElement(By.id("testRunAllButton-textEl"));
				clickElement(runall,"Runall");
				Thread.sleep(3000);
			
				WebElement errmsg = driver.findElement(By.xpath("//*[@id=\"messagebox-1001-displayfield-inputEl\"]"));
				errmsg.getAttribute("innerHTML");
			
				
				System.out.println("The error msg printed is dislayed : " + errmsg.getAttribute("innerHTML"));
				
				WebElement Okbtn = driver.findElement(By.xpath("//*[@id=\"button-1005-btnInnerEl\"]"));
				clickElement(Okbtn,"Ok");			
			
		}		
		
}
		driver.switchTo().window(parenthndl);
		WebElement drpdwn = driver.findElement(By.id("userNav-arrow"));
		clickElement(drpdwn, "UsrmenuDropdown");

		WebElement logout = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		clickElement(logout, "LogOut");
		
		
		Thread.sleep(3000);
		WebElement logoutpg = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		if (logoutpg.isDisplayed()) {
			System.out.println("SFDC LogOut successful and application is closed ");
		}			
		
	}

	
		
		
		}
		
		




