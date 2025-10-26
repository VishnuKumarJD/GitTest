package rahulshettyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.Resources.AbstractComponents;

public class CartPg extends AbstractComponents {
	WebDriver driver;
	public CartPg(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//li/button[@class='btn btn-primary']")
	WebElement checkOutEle;
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> prodListEle;
	
	public PaymentPg clickCheckOut()
	{
		actionsMethod().moveToElement(checkOutEle).click().build().perform();
		return new PaymentPg(driver);
	}
	
	public boolean confirmProd(String confProd)
	{
		boolean match = prodListEle.stream().anyMatch(s->s.getText().trim().equalsIgnoreCase(confProd));
		return match;
	}
}
