package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	@BeforeMethod(alwaysRun = true)
	public void initializeDriver() throws IOException
	{
		
	Properties prop = new Properties();
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\GlobalData.properties");
	prop.load(fis);
//	String browserNm=prop.getProperty("browser");
	String browserNm=System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");
	
	if(browserNm.equalsIgnoreCase("chrome"))
	{
		ChromeOptions options = new ChromeOptions();
	WebDriverManager.chromedriver().setup();
	if(browserNm.contains("headless"))
	{
	options.addArguments("headless");
	}
	driver = new ChromeDriver(options);
	driver.manage().window().setSize(new Dimension(1440,900));
	}
	
	else if(browserNm.equalsIgnoreCase("edge"))
	{
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	public List<HashMap<String, String>> getJsonDataToString(String path) throws IOException
	{
		String jsonContent=FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference <List<HashMap<String, String>>>(){});
		return data;
	}
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\reports\\"+ testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"\\reports\\"+ testCaseName + ".png";
	}
	
	public LandingPage launchApp() throws IOException
	{
		//initializeDriver();
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		return new LandingPage(driver);
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.quit();
	}

}