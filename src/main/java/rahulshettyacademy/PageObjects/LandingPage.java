package rahulshettyacademy.PageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.Resources.AbstractComponents;

public class LandingPage extends AbstractComponents{
	public WebDriver driver;
	public LandingPage(WebDriver driver)
	{
	super(driver);
	this.driver= driver;
	PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmailEle;
	
	@FindBy(id="userPassword")
	WebElement userPwdEle;
	
	@FindBy(id="login")
	WebElement submitBtnEle;
	
	@FindBy(id="toast-container")
	WebElement loginSuccessEle;
	
	//@FindBy(xpath="//div[@id='toast-container']//div")
	@FindBy(css="[class*='flyInOut']")
	WebElement messageEle;
	
	public ProductCatalogue loginUser(String userEmail, String userPwd)
	{
		userEmailEle.sendKeys(userEmail);
		userPwdEle.sendKeys(userPwd);
		submitBtnEle.click();
		return new ProductCatalogue(driver);
	}
	
	public String getMessage()
	{
		waitForWebElementToAppear(messageEle);
		return messageEle.getText();
	}
	
}