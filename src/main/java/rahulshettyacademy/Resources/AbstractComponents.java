package rahulshettyacademy.Resources;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.PageObjects.CartPg;

public class AbstractComponents {
	WebDriver driver;
	Wait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	
	public AbstractComponents(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Actions actionsMethod()
	{
		Actions a = new Actions(driver);
		return a;
	}
	
	public void waitForWebElementToDisappear(WebElement ele)
	{
	    wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void waitForWebElementToAppear(WebElement ele)
	{
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForWebElementToBeSelected(WebElement ele)
	{
		wait.until(ExpectedConditions.elementToBeSelected(ele));
	}
	
	public void waitForWebElementToBeClickable(WebElement ele)
	{
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void waitForElementToDisappear(By loc)
	{
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loc));
	}
	
	public void waitForElementToAppear(By loc)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
	}

	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartEle;
	
	public CartPg goToCart()
	{
		cartEle.click();
		return new CartPg(driver);
	}
}