package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ListenerClass implements ITestListener{
	String reportName;
	
	public ExtentSparkReporter sparkReporter;		//UI of report.
	public ExtentReports reports;					//Populate common info on report.
	public ExtentTest test;							//Create test case entry and update status of test methods in report.
	
	
	public void onStart(ITestContext context) {
		String timeStamp = new SimpleDateFormat("dd.MM.yyyy.hh.mm.ss.a").format(new Date());
		reportName = context.getName()+"-TestReport-"+timeStamp+".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName);
		
		sparkReporter.config().setDocumentTitle(context.getName()+" Automation Report");
		sparkReporter.config().setReportName(context.getName());
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		reports = new ExtentReports();
		reports.setSystemInfo("Tester Name", System.getProperty("user.name"));
		
		String OS = context.getCurrentXmlTest().getParameter("OS");
		reports.setSystemInfo("Operating System", OS);
		reports.setSystemInfo("Computer", "Localhost");
		reports.setSystemInfo("Environment", "QA");
		String browser = context.getCurrentXmlTest().getParameter("Browser");
		reports.setSystemInfo("Browser", browser);
		reports.setSystemInfo("Application", context.getName());
		reports.attachReporter(sparkReporter);
		
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			reports.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onFinish(ITestContext context) {	
		reports.flush();
		String extentReportFilePath = System.getProperty("user.dir")+"\\reports\\"+reportName;
		File extentReportFile = new File(extentReportFilePath);
		
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void onTestStart(ITestResult result) {

	}
	
	public void onTestSuccess(ITestResult result) {		
		test = reports.createTest(result.getTestClass().getName()+"."+result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+" got executed successfully.");
	}
	
	public void onTestFailure(ITestResult result) {
		test = reports.createTest(result.getTestClass().getName()+"."+result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+" got failed.");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imageFilePath = new BaseClass().takeScreenshot(result.getName());
			test.addScreenCaptureFromPath(imageFilePath);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {	
		test = reports.createTest(result.getTestClass().getName()+"."+result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped.");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
}
