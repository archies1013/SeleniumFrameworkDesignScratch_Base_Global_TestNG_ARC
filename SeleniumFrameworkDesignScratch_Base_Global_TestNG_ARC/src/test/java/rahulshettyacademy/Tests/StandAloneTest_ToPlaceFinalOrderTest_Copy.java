package rahulshettyacademy.Tests;

import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.List;

import org.checkerframework.framework.qual.FromByteCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.handler.codec.AsciiHeadersEncoder.NewlineType;
import net.bytebuddy.description.ModifierReviewable.OfAbstraction;
import rahulshettyacadamy.PageObjects.CartPage;
import rahulshettyacadamy.PageObjects.CheckOutPage;
import rahulshettyacadamy.PageObjects.ConfirmationPage;
import rahulshettyacadamy.PageObjects.LandingPage;
import rahulshettyacadamy.PageObjects.ProductCatalogue;

public class StandAloneTest_ToPlaceFinalOrderTest_Copy {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		// System.setProperty("webdriver.chrome.driver",
		// "/Users/archanasawant/Documents/chromedriver");
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		// System.setProperty("webdriver.edge.driver",
		// "/Users/archanasawant/Documents/msedgedriver");
		// WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// driver.get("https://rahulshettyacademy.com/client");//used in LandingPage
		// class
		// Creating LandingPage Object, so we can access this class driver into
		// LandingPage class
		LandingPage landingPage = new LandingPage(driver);//We are landing on the page
		// if you send any argument here you can catch this argument in the Constructor (WebDriver driver)
		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginApplication("archies1013@gmail.com", "Mullen@123");//loginit 

		List<WebElement> products = productCatalogue.getProductList();//getting the product list
		productCatalogue.addProductToCart(productName);//adding the products to cart
		
		CartPage cartPage = productCatalogue.goToCartPage();//going to cart page
		Boolean match = cartPage.VerifyProductDiaplay(productName);//verifying the product display
		Assert.assertTrue(match);
	
		CheckOutPage checkOutPage = cartPage.goToCheckout();//checking out
		checkOutPage.selectCountry("India");//selecting the country
		//Thread.sleep(5000);
		checkOutPage.submitOrder();//submitting the order
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();//getting on confirmation page 
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));// if its matching then test will pass
																						
		driver.close();

	}

}
