package com.Salesforce.utilities;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.Salesforce.base.BaseTest;

public class SFDCListenerUtilities extends BaseTest implements ITestListener {
	
	protected static Logger SFDCListenerUtilitieslog = LogManager.getLogger();
	private static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();
	@Test
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		SFDCListenerUtilitieslog.info(context.getName()+"............execution has completed..............");
		extentreport.endReport();
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		SFDCListenerUtilitieslog.info(context.getName()+ "...........execution has started..............");
		extentreport.startExtentReport();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		SFDCListenerUtilitieslog.error(result.getMethod().getMethodName()+"....... execution completed with failure..............");
		extentreport.logTestFailed(result.getMethod().getMethodName()+"....... execution completed with failure..............");
		String filename=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date(0));
		System.out.println("filename="+filename);
		String path=Constants.SCREENSHOTS_DIRECTORY_PATH+filename+".png"; // 2024_02_13_02_11_23.png
		//WebDriver driver=getDriverInstance();
		takescreenshot(path);
		extentreport.logTestWithscreenshot(System.getProperty("user.dir")+"/Reports/Screenshots/"+filename+".png");
		extentreport.logTestFailedWithException(result.getThrowable());
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		SFDCListenerUtilitieslog.info(result.getMethod().getMethodName()+"............execution started..............");
		extentreport.startSingleTestReport(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		SFDCListenerUtilitieslog.info(result.getMethod().getMethodName()+"............execution completed with success..............");
		extentreport.logTestpassed(result.getMethod().getMethodName()+ "---Execution completed with success---");
	}

}
