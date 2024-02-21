package com.Salesforce.Automation;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.List;

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
import org.testng.asserts.SoftAssert;

import com.Salesforce.base.SFDCBasecase;
import com.Salesforce.utilities.Constants;
import com.Salesforce.utilities.ExtentReportsUtility;
@Listeners(com.Salesforce.utilities.SFDCListenerUtilities.class)

public class SalesforceCreateAcc extends SFDCBasecase{
	protected static Logger SalesforceCreateAcclog = LogManager.getLogger();
	protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();
	@Test
	public void TC10a () throws InterruptedException {
		 LogintoSFDC ();
		  WebElement plus = driver.findElement(By.xpath("//img[@class=\"allTabsArrow\"]"));
		 clickElement(plus, "+");
		 getTitle("All Tabs");
		
			WebElement cstmtab = driver.findElement(By.xpath("//input[@class=\"btnImportant\"]"));
			clickElement(cstmtab, "Customize My Tabs");
		
		 	getTitle("Customize");
		
		   try {
		   WebElement Tabs = driver.findElement(By.xpath("//select[@id=\"duel_select_0\"]"));
		   Select select = new Select(Tabs);
		   select.selectByValue("Account");}
		   catch(Exception e) {
			
			   WebElement SelectedTabs = driver.findElement(By.id("duel_select_1"));   
			   Select select = new Select(SelectedTabs);
			   select.selectByValue("Account");
			   WebElement Remove = driver.findElement(By.xpath("//*[@id=\"duel_select_0_left\"]/img"));
			   clickElement(Remove, "Remove");
			   WebElement Save = driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
				clickElement(Save, "Save");	
				WebElement cstmtab1 = driver.findElement(By.xpath("//input[@class=\"btnImportant\"]"));
				clickElement(cstmtab1, "Customize My Tabs");
				 WebElement Tabs = driver.findElement(By.xpath("//select[@id=\"duel_select_0\"]"));
				 Select select1 = new Select(Tabs);
				 select1.selectByValue("Account");
			 
		   }
			
			WebElement Add = driver.findElement(By.xpath("//*[@id=\"duel_select_0_right\"]/img"));
			clickElement(Add, "Add");
			
			WebElement Save = driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
			clickElement(Save, "Save");	
			
			getTitle("All Tabs");
			LogoutfromSFDC();
			LogintoSFDC();
			
			java.util.List<WebElement>Tablist = driver.findElements(By.xpath("//*[@id=\"tabBar\"]/li"));
			for(WebElement Tab:Tablist) {
				if(Tab.getText().contains("Account")) {
					System.out.println(Tab.getText() + "Tab is added in the Tab Bar--------TC10a --->Test Pass");
					break;
				}
		
			}			
			
	}

@Test
	public void Createanacc() throws InterruptedException {
		 
		 Acnthmpg();
		
		 WebElement Newtab = driver.findElement(By.xpath("//input[@value=\" New \"]"));
		 clickElement(Newtab, "New Tab");
		 System.out.println(driver.getTitle()+ " is displayed ");
		 WebElement Acntnm = driver.findElement(By.id("acc2"));
		 enterText(Acntnm, "Cloud", "Account Name");
		 WebElement Type = driver.findElement(By.id("acc6"));
		 Select select = new Select(Type);
		 select.selectByValue("Technology Partner");
		 WebElement TP = driver.findElement(By.xpath("//*[@id=\"acc6\"]/option[7]"));
		 System.out.println("Type is selected and "+ TP.getText()+ " is displayed");
		 WebElement priority = driver.findElement(By.id("00Nam000000LsIN"));
		 Select pri = new Select(priority);
		 pri.selectByValue("High");
		 WebElement PR = driver.findElement(By.xpath("//*[@id=\"00Nam000000LsIN\"]/option[2]"));
		 System.out.println("Customer Priority is selected and "+ PR.getText()+ " is displayed");
		 WebElement Save = driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
		 clickElement(Save, "Save");
		 String Title = driver.getTitle();
		 if(Title.contains("Cloud")) {
		 System.out.println("New Account Page is added and " + driver.getTitle()+ " is displayed: Create an account --> Test Pass"); 
		 
		}
		 else {
			 System.out.println ("Create an account --> Test Fail");
		 }	
	}
	@Test
	public void TC11Createnewview() throws InterruptedException {
		 Acnthmpg();
		 WebElement crnw = driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]"));
		 clickElement(crnw, "Create New View");
		 WebElement Vname = driver.findElement(By.id("fname"));
		 enterText(Vname, "Spirit", "View Name");
		 WebElement VUname = driver.findElement(By.id("devname"));
		 enterText(VUname, "Ring", "View Unique Name");

		 WebElement Save = driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[1]"));
		 clickElement(Save, "Save");	 
		
		 WebElement link = driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/table/tbody/tr[2]/td/a"));
		 clickElement(link, "Link");//click on the link to go back to the previous page
		 
		 WebElement lst = driver.findElement(By.xpath("//*[@class=\"title\"]"));
			Select sel = new Select(lst);
			List <WebElement> viewlist = sel.getOptions();
			for(WebElement option:viewlist) {
				if (option.getText().equalsIgnoreCase("Spirit"))
				AssertJUnit.assertTrue(option.isDisplayed());
			}	 	 
	
	}
	
	@Test

	public void TC12Editview () throws InterruptedException {
		
		
		 Acnthmpg();
		 
		 List<WebElement>ViewNm = driver.findElements(By.xpath("//*[@id=\"fcf\"]/option"));
		 for (WebElement option:ViewNm) {
		 if (option.isSelected()) {
				WebElement edit = driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[1]"));
		        clickElement(edit, "Edit");
		        break;}
		 }
		 
		 if(driver.getTitle().contains("Edit")){
			 System.out.println("<View name> edit page is displayed ");
		 }
		 else {
			 System.out.println("<View name> edit page is not displayed ");
		 }
		 
		
		 WebElement Vname = driver.findElement(By.xpath("//*[@id=\"fname\"]"));
		 enterText(Vname, "Mount", "View new name");
		
		 WebElement fltr = driver.findElement(By.xpath("//*[@id=\"fcol1\"]"));
		 Select select1 = new Select (fltr);
		 select1.selectByVisibleText("Account Name"); 
		 
		 WebElement Oprtr = driver.findElement(By.xpath("//*[@id=\"fop1\"]"));
		 Select select2 = new Select (Oprtr);
		 select2.selectByVisibleText("contains");
		
		 WebElement Value = driver.findElement(By.id("fval1"));
		 enterText(Value, "a", "Value");
		
		/* Add the last activity column if not done. 
		 WebElement SelectFields = driver.findElement(By.id("colselector_select_0"));
		 Select select3 = new Select (SelectFields);
		 select3.selectByVisibleText("Last Activity");
		 
		 WebElement Add = driver.findElement(By.xpath("//*[@id=\"colselector_select_0_right\"]/img"));
		 clickElement(Add, "Add");*/
		 
		 WebElement Save = driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]"));
		 clickElement(Save, "Save");
		
		 // validation of view new name in the dropdown list
		 
		 WebElement drpdwn = driver.findElement(By.xpath("//*[@class=\"title\"]"));
		 Select sel = new Select(drpdwn);
			List <WebElement> viewlist = sel.getOptions();
			for(WebElement option:viewlist) {
				if (option.getText().equalsIgnoreCase("Mount"))
				Assert.assertTrue(option.isDisplayed());
			}
		 
	    // validation of last activity added as column
			
		List<WebElement>rows= driver.findElements(By.xpath("//*[@id=\"ext-gen16\"]/div/table/thead/tr/td/div"));//how to validate the Last activity column
		for(WebElement column:rows){
			if(column.getAttribute("title").equalsIgnoreCase("Last activity"))
			System.out.println(column.getAttribute("title"));
			break;			
		}
		//sb.assertAll();
		
	}
	@Test
	public void TC13() throws InterruptedException {
		Acnthmpg();
		WebElement Merge = driver.findElement(By.xpath("//a[text()=\"Merge Accounts\"] "));
		clickElement(Merge, "Merge");
		WebElement Txtbox = driver.findElement(By.id("srch"));
		enterText(Txtbox, "Cloud", "Search");
		WebElement FindAc = driver.findElement(By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[4]/input[2]"));
		clickElement(FindAc, "Find Account");
		
		WebElement Chcbx1 = driver.findElement(By.xpath("//*[@id=\"cid0\"]"));
		clickElement(Chcbx1, "Check Box");
		WebElement Chcbx2 = driver.findElement(By.xpath("//*[@id=\"cid1\"]"));
		clickElement(Chcbx2, "Check Box");
		
		
		WebElement Next = driver.findElement(By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[5]/div/input[1]"));
		clickElement(Next, "Nextbtn");
		
		if(driver.getTitle().contains("Merge")) {
			System.out.println(driver.getTitle() + " is displayed ");
		}
		
		else {
			System.out.println(" The page is not displayed ");
		}
		
		WebElement Mergebtn = driver.findElement(By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[1]/div/input[2]"));
		clickElement(Mergebtn, "Merge");
	
		Alert alert = driver.switchTo().alert();
		alert.accept();
		String expected = "Accounts: Home ~ Salesforce - Developer Edition";
		AssertJUnit.assertEquals(driver.getTitle(),expected);
	
	}
	
	@Test
	public void TC14() throws InterruptedException {
		// Saving it with unique name everytime is needed
		Acnthmpg();
		WebElement Report = driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div/div[1]/ul/li[2]/a"));
		clickElement(Report, "Account with Last Activity >30 days link");
		getTitle("Unsaved");
	
		WebElement FrmDt = driver.findElement(By.xpath("//*[@id=\"ext-gen152\"]"));
		clickElement(FrmDt, "From Datepicker");
		WebElement TodayFrm = driver.findElement(By.id("ext-gen276"));
		clickElement(TodayFrm, "Today Button");
	
	
		WebElement ToDt = driver.findElement(By.xpath("//*[@id=\"ext-gen154\"]"));
		clickElement(ToDt, "To Datepicker");
		
		WebElement TodayToDt = driver.findElement(By.id("ext-gen292"));
		clickElement(TodayToDt, "Today");
		
		WebElement SaveR = driver.findElement(By.id("ext-gen49"));
		clickElement(SaveR, "Save");
		
		WebElement ReportName = driver.findElement(By.id("saveReportDlg_reportNameField"));
		enterText(ReportName,"Current", "Report Name");
		WebElement ReportUName = driver.findElement(By.id("saveReportDlg_DeveloperName"));
		enterText(ReportUName,Constants.UniqueName, "ReportUniqueName");
		
		WebElement SaveRun = driver.findElement(By.xpath("//*[@id=\"ext-gen312\"]"));
		Actions action = new Actions(driver);
		action.moveToElement(SaveRun).build().perform();
		action.click().build().perform();
		getTitle("Current");
		WebElement Reportname = driver.findElement(By.xpath("//*[@id=\"noTableContainer\"]/div/div[1]/div[1]/div[1]/h1"));
		if (Reportname.getText().contains("Current")) {
			System.out.println("Report page with details and <report name>is displayed: TC14------------->Test pass.");
		}
		else {
			System.out.println("Report page with details and <report name>is displayed: TC14------------->Test Fail.");
		}	
		
	}
}

		
		
		
			



		
		



