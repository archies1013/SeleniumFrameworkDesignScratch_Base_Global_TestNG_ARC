package rahulshettyacademy.TestComponents;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.idealized.Network.UserAgent;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacadamy.PageObjects.LandingPage;

public class BaseTest {

	
	public WebDriver driver;
	public LandingPage landingPage;//once you create object it is activating for all tests wherever required
	
	public WebDriver InitializeDriver() throws IOException
	{
		//Properties class
		Properties prop= new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+ "//src//main//java//rahulshettyacademy//resources//GlobalData.properties");
		prop.load(fis);
		
		//using Ternary Operator here to choose browser from terminal
		String browserName=System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browserName=prop.getProperty("browser");
		prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			//WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
		}	
			
			else if (browserName.equalsIgnoreCase("firefox")) 
			{
				System.setProperty("webdriver.gecko.driver", "/Users/archanasawant/Documents/geckodriver");
				driver = new FirefoxDriver();
			}
			
			else if (browserName.equalsIgnoreCase("edge"))
			{
			//System.setProperty("webdriver.edge.driver", "/Users/archanasawant/Documents/msedgedriver");
			//driver = new EdgeDriver();
			}
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();
			return driver;
		}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{	
		//read Json to String
		String jsonContent=FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		//Basically in the second argument we have to give new method in encoding format that how to convert into String, then your deprecation error will go away
		//We have Json file in place, first up all we convert our Json file into string variable and then thst string to HashMap we converted and finally we have a
		//list of HashMap
		//Now we have to call our HashMap in PlaceOrderTest
		
		//String to HashMap  - Jackson DataBind Maven dependency helps you to convert String into HashMap
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>>data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
			
		
	}
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;//this is how you have to cast driver to take a screenshot
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+ "//reports//" +testCaseName +".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png";
		
		//with the help of this utility we can create HTML Extent Reports
	
	}
	
	
	
		@BeforeMethod(alwaysRun = true)
		public LandingPage launchApplication() throws IOException
		{
			driver=InitializeDriver();
			landingPage = new LandingPage(driver);
			landingPage.goTo();
			return landingPage;
		}
		

		@AfterMethod(alwaysRun = true)
		public void tearDown()
		{
			
			driver.close();
		}
		
		
				
	}


