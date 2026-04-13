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

public class ExtentReportManager implements ITestListener{

	ExtentSparkReporter spark;
	ExtentReports report;
	ExtentTest test;
	String reportName;
	
	@Override
	public void onStart(ITestContext context) {
		String timeStamp = new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
		reportName = System.getProperty("user.dir")+"\\reports\\"+"Test-Report-" + timeStamp + ".html";
		spark = new ExtentSparkReporter(reportName);
		
		spark.config().setDocumentTitle("CTMS Automation Report");
		spark.config().setReportName("Functional Testing");
		spark.config().setTheme(Theme.STANDARD);
		
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Application", "OpenMRS");
		report.setSystemInfo("Module", "Clinical Trail Management System");
		report.setSystemInfo("Environment", "QA");
		report.setSystemInfo("User Name", System.getProperty("user.name"));
		report.setSystemInfo("OS", context.getCurrentXmlTest().getParameter("os"));
		report.setSystemInfo("Browser", context.getCurrentXmlTest().getParameter("browser"));
		
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			report.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test = report.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+" got successfully passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		test = report.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+" got failed");
		test.info(result.getThrowable().getMessage());
	
		test.addScreenCaptureFromPath(ScreenshotUtil.captureScreenshot(result.getName()));
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		test = report.createTest(result.getTestClass().getName());
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.info(result.getThrowable().getMessage());
	}
	
	@Override
	public void onFinish(ITestContext context) {
		report.flush();
		
		File file = new File(reportName);
		try {
			Desktop.getDesktop().browse(file.toURI());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
