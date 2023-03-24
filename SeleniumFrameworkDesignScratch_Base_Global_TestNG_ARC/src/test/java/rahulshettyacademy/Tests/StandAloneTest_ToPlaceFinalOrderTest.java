package rahulshettyacademy.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.awt.Desktop.Action;
import java.io.IOException;
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
import rahulshettyacadamy.PageObjects.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;

public class StandAloneTest_ToPlaceFinalOrderTest extends BaseTest{

	@Test
	public void PlaceOrderTest() throws IOException, InterruptedException{
		String productName = "ZARA COAT 3";
		
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
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));// if its matching then test will pass
																						
		

	}

}
