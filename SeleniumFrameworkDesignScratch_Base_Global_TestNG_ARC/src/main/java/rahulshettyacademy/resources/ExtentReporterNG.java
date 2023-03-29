package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	//public ExtentReports extent;
	
	
	//when we put static we can access the method without even declaring the object of that class
	public static ExtentReports getReportObject()
	{
		
		String path=	System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);//expect path where your report should be created
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");	
			
		ExtentReports extent=new ExtentReports ();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Rahul Shetty");
		return extent;
		
		
	}

}
