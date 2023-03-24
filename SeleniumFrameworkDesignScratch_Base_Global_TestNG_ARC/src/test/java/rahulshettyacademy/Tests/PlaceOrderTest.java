package rahulshettyacademy.Tests;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.security.PublicKey;
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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.handler.codec.AsciiHeadersEncoder.NewlineType;
import net.bytebuddy.description.ModifierReviewable.OfAbstraction;
import rahulshettyacadamy.PageObjects.CartPage;
import rahulshettyacadamy.PageObjects.CheckOutPage;
import rahulshettyacadamy.PageObjects.ConfirmationPage;
import rahulshettyacadamy.PageObjects.LandingPage;
import rahulshettyacadamy.PageObjects.OrdersPage;
import rahulshettyacadamy.PageObjects.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;



public class PlaceOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";
	
	@Test
	public void PlaceOrderTest() throws IOException, InterruptedException{
		
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("sawantarchana110@gmail.com", "Mullen@123");//loginit 

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
		
	}
	
	@Test(dependsOnMethods = "PlaceOrderTest")
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("sawantarchana110@gmail.com", "Mullen@123");
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDiaplay(productName));
	
	}
	//To verify if that ZARA COAT 3 is displaying in order page
	//all these are Test Strategy
	//If you want to run methods in parallel use like this --><suite parallel = 'tests' name="Suite">

}
