package rahulshettyacademy.TestComponents;

import java.io.IOException;
import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.Resources.ExtentReport;

public class ListenersDemo extends BaseTest implements ITestListener {

	  ExtentReports extReport=ExtentReport.getReportObject();
	  ExtentTest test;
	  ThreadLocal<ExtentTest> threadTest = new ThreadLocal<ExtentTest>();
	  @Override 
	  public void onTestStart(ITestResult result) 
	  {
		   test = extReport.createTest(result.getMethod().getMethodName());
		   threadTest.set(test);
	  }
	  
	  public void onTestSuccess(ITestResult result) 
	  {
		    threadTest.get().log(Status.PASS, "Test Case is Pass");
	  }

	  public void onTestFailure(ITestResult result) 
	  {
		//test.fail(result.getThrowable());
		  threadTest.get().fail(result.getThrowable());
		try 
		{
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		} 
		
		String filePath = null;
		try 
		{
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		threadTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	  }

	  public void onFinish(ITestContext context) {
		    extReport.flush();
		  }
}
