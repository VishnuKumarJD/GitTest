package rahulshettyacademy.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	public static ExtentReports getReportObject()
	{
		String path= System.getProperty("user.dir")+"\\reports\\index1.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("TestReportTitle");
		reporter.config().setReportName("TestReportName");
		
		ExtentReports extReport = new ExtentReports();
		extReport.attachReporter(reporter);
		extReport.setSystemInfo("TesterName", "Vishnu Kumar");
		
		return extReport;
	}
}
