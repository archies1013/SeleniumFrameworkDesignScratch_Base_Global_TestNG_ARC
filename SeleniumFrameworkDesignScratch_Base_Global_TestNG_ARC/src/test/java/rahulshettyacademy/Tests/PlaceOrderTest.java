package rahulshettyacademy.Tests;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import javax.activation.FileDataSource;

import org.apache.commons.io.FileUtils;
import org.checkerframework.framework.qual.FromByteCode;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
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
	
	@Test(dataProvider = "getData", groups = "Purchase")//you can put more groups also simply put comma and add it
	//public void PlaceOrderTest(String email, String password, String productName) throws IOException, InterruptedException{
	public void PlaceOrderTest(HashMap<String,String>input) throws IOException, InterruptedException{
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));//loginit 

		List<WebElement> products = productCatalogue.getProductList();//getting the product list
		productCatalogue.addProductToCart(input.get("productName"));//adding the products to cart
		
		CartPage cartPage = productCatalogue.goToCartPage();//going to cart page
		Boolean match = cartPage.VerifyProductDiaplay(input.get("productName"));//verifying the product display
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
	
	
	//2nd method from DataProvider
	@DataProvider
	public Object[][] getData() throws IOException
	{
		/*HashMap<String, String> map = new HashMap<String, String>();
		map.put("email","sawantarchana110@gmail.com");
		map.put("password", "Mullen@123");
		map.put("productName", "ZARA COAT 3");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email","archies1013@gmail.com");
		map1.put("password", "Mullen@123");
		map1.put("productName", "ADIDAS ORIGINAL");
		
		return new Object [] [] {{map}, {map1} };*/
		
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");//it returns List of HashMap
		return new Object [] [] {{data.get(0)}, {data.get(1)} };
		
		/**this time while running the data is driving from external json file and utilizing testNG dataProvider as an 
		*integration to Parameterization Data Driven Testing in framework
		*DataProvider Parameterization and HashMap Parameterization
		*/
		
	}
	//we run this in "Purchase.xml"  file, son run and check
	//1st - method from DataProvider
	//@DataProvider
	//public Object[][] getData()
	//{
		//return new Object [] [] {{"sawantarchana110@gmail.com", "Mullen@123", "ZARA COAT 3"}, {"archies1013@gmail.com", "Mullen@123", "ADIDAS ORIGINAL"} };
		//From Here we converted into "HashMap"
		//return new Object [] [] {{map}, {"archies1013@gmail.com", "Mullen@123", "ADIDAS ORIGINAL"} };
		//after add 2nd HashMap = 
		//return new Object [] [] {{map}, {map1} };
	//Now we will see How to Drive the data from Json
	//	}
	
	/**Let say if you want to run this above PlaceOrderTest with two different data sets, so simply create two dimensional array
	 * which accepts multiple sets. 
	 * Now we have to attach this DataProvider getData test to above PlaceOrdertest and send data as an argument
	 * Here our code is so generic that we are passing different data sets with the help of dataprovider, so that is how you can Parameterized 
	 * your test with multiple data sets.
	 * Next we will see get the data from external sources like excel file
	 * We created two different data sets with HashMap
	 */
	

}

