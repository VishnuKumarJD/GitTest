package rahulshettyacademy.tests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.PageObjects.CartPg;
import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.PaymentPg;
import rahulshettyacademy.PageObjects.ProductCatalogue;
import rahulshettyacademy.PageObjects.confirmationPg;
import rahulshettyacademy.TestComponents.BaseTest;

public class EndToEndTest extends BaseTest{
	public LandingPage lp;
	ProductCatalogue pc;

	@Test(dataProvider="getData")
	public void submitOrderTest(HashMap<String, String> input)throws InterruptedException, IOException
	{
		lp=launchApp();
		pc = lp.loginUser(input.get("userEmail"), input.get("userPwd"));
		pc.addToCart(input.get("prodNm"));
		CartPg cp = pc.goToCart();
		PaymentPg pp = cp.clickCheckOut();
		pp.enterCvv("CVV Code ?", "9887067");
		pp.enterNameOnCard("Name on Card", "Vishnu Kumar");
		pp.enterCoupon("Apply Coupon", input.get("coupon"));
		String sCoupon=pp.clickApplyCoupon();
		Assert.assertEquals(sCoupon, "* Coupon Applied");
		pp.selectCountry("India");
		confirmationPg co = pp.clickCheckOut();
		co.getConfirmTxt("Thankyou for the order.");
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToString(System.getProperty("user.dir")+"\\inputData.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	//String uNm[][]= {{"jdvishnu@gmail.com", "Vishnu@123", "ZARA COAT 3"}};
	//return uNm;
	
	//HashMap<String, String> map = new HashMap<String, String>();
	//map.put("userEmail", "jdvishnu@gmail.com");
	//map.put("userPwd", "Vishnu@123");
	//map.put("prodNm", "ZARA COAT 3");
	//map.put("coupon", "rahulshettyacademy");
	
	//return new Object[][] {{map}};
	
	//@Test(dependsOnMethods="submitOrderTest")
	public void testComplete()
	{
		System.out.println("Testing Completed");
	}
	
	
	
}