package rahulshettyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.Resources.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='card-body']//b")
	List<WebElement> prodNmEle;
	
	//@FindBy(className="mb-3")
	//List<WebElement> productsEle;

    //By addToCartBy = By.className("w-10");//By.className("w-10");
	
	By byAddToCart=By.xpath("parent::h5/following-sibling::button[2]");
	
	@FindBy(id="toast-container")
	WebElement loginSuccessEle;
	
	public void addToCart(String prodNm) throws InterruptedException
	{	
		//productsEle.stream()
		//  		.filter(s->s.findElement(By.tagName("b")).getText().trim().equalsIgnoreCase(prodNm))
		//		.forEach(s->s.findElement(addToCartBy).click());
		prodNmEle.stream()
		.filter(s->s.getText().trim().equalsIgnoreCase(prodNm))
		.forEach(s-> s.findElement(byAddToCart).click());
		waitForWebElementToAppear(loginSuccessEle);
		waitForWebElementToDisappear(loginSuccessEle);
	}
}
