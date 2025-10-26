package rahulshettyacademy.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class confirmationPg {
	WebDriver driver;
	public confirmationPg(WebDriver driver) 
	{
	this.driver = driver;
	PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement confirmTxt;
	
	public void getConfirmTxt(String expTxt)
	{
		String actTxt = confirmTxt.getText().trim();
		System.out.println("Actual Text is: "+ actTxt);
		if(actTxt.equalsIgnoreCase(expTxt))
		{
			System.out.println("Order Submission Successful - Passed");
		}
	}
}
