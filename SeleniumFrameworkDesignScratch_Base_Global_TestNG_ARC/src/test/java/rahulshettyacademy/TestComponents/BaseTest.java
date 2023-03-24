package rahulshettyacademy.TestComponents;

import org.testng.annotations.AfterMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.filefilter.TrueFileFilter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.idealized.Network.UserAgent;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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
		String browserName=prop.getProperty("browser");
		
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
				//System.setProperty("webdriver.edge.driver", "/Users/archanasawant/Documents/geckodriver");
				//driver = new EdgeDriver();
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

