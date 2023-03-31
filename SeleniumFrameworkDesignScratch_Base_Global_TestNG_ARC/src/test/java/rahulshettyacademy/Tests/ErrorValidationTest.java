package rahulshettyacademy.Tests;

import org.testng.annotations.Test;
import org.testng.internal.GroupsHelper;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacadamy.PageObjects.CartPage;
import rahulshettyacadamy.PageObjects.CheckOutPage;
import rahulshettyacadamy.PageObjects.ConfirmationPage;
import rahulshettyacadamy.PageObjects.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest{
	/**Our scenario is here 
	*all negative tests are in Errorvalidation class
	*all positive tests are in ToPlaceFinalOrderTest Class
	*Use different emaildid for positiev and negative tests is ggod for parallel testing
	*/

	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void LoginErrorValidationTest() throws IOException, InterruptedException{
		String productName = "ZARA COAT 3";
		
		landingPage.loginApplication("sawantarchana110@gmail.com", "Mullen@1235");//loginit
		landingPage.getErrorMessage();//now we need to put Assertion here to validate error message
		AssertJUnit.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
		/**if you gone in blinkout while catching the locator we can check with out developer or you use selector hub plugin and copy
		 * xpath and use it, below locator we cannot catch that why we use SelectorHub Plugin to catch relative xpath
		 * SelectorHub relative xpath= //div[@aria-label='Incorrect email or password.']   it is giving stringbased bec our goal is to verify string  so use other locator
		 * selectorHub abs xpath = /html[1]/body[1]/div[1]/div[1]/div[1]
		 * selectorHub class = flyInOut
		 * 
		 */
		
	}
	@Test
	public void ProductErrorValidationTest() throws IOException, InterruptedException{
		String productName = "ZARA COAT 3";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("sawantarchana110@gmail.com", "Mullen@123");//loginit 

		List<WebElement> products = productCatalogue.getProductList();//getting the product list
		productCatalogue.addProductToCart(productName);//adding the products to cart
		
		CartPage cartPage = productCatalogue.goToCartPage();//going to cart page
		Boolean match = cartPage.VerifyProductDiaplay("ZARA COAT 33");//We are expecting false here
		Assert.assertFalse(match);
	
		
																						
		

	}

}
