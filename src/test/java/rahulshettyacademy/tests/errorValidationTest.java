package rahulshettyacademy.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.PageObjects.CartPg;
import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry1;

public class errorValidationTest extends BaseTest{
	public LandingPage lp;
	ProductCatalogue pc;
	@Test(retryAnalyzer=Retry1.class)
	public void zerrorValidationTest() throws IOException, InterruptedException
	{
		System.out.println("Executing Error Validation Test");
		lp=launchApp();
		lp.loginUser("jdvishnu@gmail.com", "test");
		System.out.println("Error Message is:"+ lp.getMessage());
		String actErrMsg = lp.getMessage();
		Assert.assertEquals(actErrMsg, "Login Successful");
		System.out.println("New Line Added - 1");
		System.out.println("New Line Added - 2");
		System.out.println("New Line Added - 3");
		System.out.println("New Line Added - 4");
		System.out.println("New Line Added - 5 GitX");
		System.out.println("New Line Added - 4 GitX");
		
	}

	@Test(retryAnalyzer=Retry1.class)
	public void prodErrorValidationTest() throws IOException, InterruptedException
	{
		lp=launchApp();
		pc=lp.loginUser("jdvishnu@gmail.com", "Vishnu@123");
		pc.addToCart("ZARA COAT 3");
		CartPg cp = pc.goToCart();
		boolean match = cp.confirmProd("ZARA COAT 33");
		Assert.assertTrue(match);
	}
	
	public void test()
	{

	}
	
	public void test1()
	{
		
	}
	
	public void test2()
	{
		System.out.println("By GitX1");
	}
	
	public void test3()
	{
		System.out.println("Master Branch final update");
	}
}
