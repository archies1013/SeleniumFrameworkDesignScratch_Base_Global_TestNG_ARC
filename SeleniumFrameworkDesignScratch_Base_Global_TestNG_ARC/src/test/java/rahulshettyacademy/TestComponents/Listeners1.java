package rahulshettyacademy.TestComponents;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacadamy.PageObjects.LandingPage;
import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners1 extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//unique thread id(ErrorValidationTest)->test
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		 extentTest.get().fail(result.getThrowable());////here take screenshot and attach it to report
			try {
				driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			String filePath=null;
			try {
			filePath=getScreenShot(result.getMethod().getMethodName(), driver);
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
			
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		extent.flush();
	}

	@Override
	public WebDriver InitializeDriver() throws IOException {
		// TODO Auto-generated method stub
		return super.InitializeDriver();
	}

	@Override
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// TODO Auto-generated method stub
		return super.getJsonDataToMap(filePath);
	}

	@Override
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		// TODO Auto-generated method stub
		return super.getScreenShot(testCaseName, driver);
	}

	@Override
	public LandingPage launchApplication() throws IOException {
		// TODO Auto-generated method stub
		return super.launchApplication();
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	
	

}
