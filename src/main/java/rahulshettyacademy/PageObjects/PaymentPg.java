package rahulshettyacademy.PageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.Resources.AbstractComponents;

public class PaymentPg extends AbstractComponents {
	WebDriver driver;
	public PaymentPg(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryEle;
	
	@FindBy(xpath="//span[@class='ng-star-inserted']")
	List<WebElement> countryList;
	
	@FindBy(className="action__submit")
	WebElement placeOrderEle;
	
	public void selectCountry(String expCountry)
	{
		countryEle.sendKeys("Ind");
		List<WebElement> cList = countryList.stream().filter(c -> c.getText().trim().equalsIgnoreCase(expCountry)).collect(Collectors.toList());
		cList.stream().forEach(c -> c.click());
	}
	
	public confirmationPg clickCheckOut()
	{
		waitForWebElementToBeClickable(placeOrderEle);
		actionsMethod().moveToElement(placeOrderEle).click().perform();
		return new confirmationPg(driver);
	}
	
	@FindBy(xpath="//div[@class='title']")
	List<WebElement> personalInfoEle;
	
	By personalInfoCheckBox = By.xpath("following-sibling::input");
	
	public void enterCvv(String expCvv, String cvv)
	{
		List<WebElement> expCvvEle = personalInfoEle.stream().filter(s-> s.getText().trim().equalsIgnoreCase(expCvv)).collect(Collectors.toList());
		expCvvEle.stream().forEach(s-> s.findElement(personalInfoCheckBox).sendKeys("887067"));
	}
	
	public void enterNameOnCard(String expName, String name)
	{
		List<WebElement> expNameEle = personalInfoEle.stream().filter(s-> s.getText().trim().equalsIgnoreCase(expName)).collect(Collectors.toList());
		expNameEle.stream().forEach(s-> s.findElement(personalInfoCheckBox).sendKeys("Vishnu Kumar"));	
	}
	
	public void enterCoupon(String expCoupon, String couponCode)
	{
		List<WebElement> expCouponEle = personalInfoEle.stream().filter(s-> s.getText().trim().equalsIgnoreCase(expCoupon)).collect(Collectors.toList());
		expCouponEle.stream().forEach(s-> s.findElement(personalInfoCheckBox).sendKeys("rahulshettyacademy"));	
	}
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement applyCouponEle;
	
	@FindBy(xpath="//p[contains(@class,'mt-1')]")
	WebElement couponAppliedTxtEle;
	
	public String clickApplyCoupon()
	{
		applyCouponEle.click();
		waitForWebElementToAppear(couponAppliedTxtEle);
		return couponAppliedTxtEle.getText().trim();
		//if(couponAppliedTxtEle.getText().trim().equalsIgnoreCase(sCoupon))
			//System.out.println("Coupon Applied - Pass");
	}

}
