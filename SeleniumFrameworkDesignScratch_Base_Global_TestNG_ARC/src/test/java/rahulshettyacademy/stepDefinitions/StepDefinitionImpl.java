package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacadamy.PageObjects.CartPage;
import rahulshettyacadamy.PageObjects.CheckOutPage;
import rahulshettyacadamy.PageObjects.ConfirmationPage;
import rahulshettyacadamy.PageObjects.LandingPage;
import rahulshettyacadamy.PageObjects.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest{
	
	
	public LandingPage landingPage;
	public  ProductCatalogue productCatalogue;
	ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce page ")//this is plain static no dynamic values thats why no need of regex
	public void i_landed_on_Ecommerce_page() throws IOException
	{
		//LandingPage landingPage = landingPage=launchApplication();
		landingPage=launchApplication();
	}
	
	 @Given ("^Logged in with username (.+) and password (.+)$")//this entire is now regular expression bec we put ^at beginning and $at end 
	 public void logged_in_with_username__and_password(String username, String password)
	  {
		// ProductCatalogue productCatalogue = landingPage.loginApplication(username, password );
		 productCatalogue = landingPage.loginApplication(username, password );
	  }
	 
	 @When("^ I add product (.+) to cart$")
	 public void i_add_product_to_cart(String productName) throws InterruptedException
	 {
		 List<WebElement> products = productCatalogue.getProductList();//getting the product list
			productCatalogue.addProductToCart(productName);
	 }
	 
	 
	@ When ("^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCartPage();//going to cart page
		Boolean match = cartPage.VerifyProductDiaplay(productName);//verifying the product display
		Assert.assertTrue(match);
	
		CheckOutPage checkOutPage = cartPage.goToCheckout();//checking out
		checkOutPage.selectCountry("India");//selecting the country
		//Thread.sleep(5000);
		//checkOutPage.submitOrder();//submitting the order
		//ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		confirmationPage = checkOutPage.submitOrder();
	}

	//Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation page
	@Then ("{string} message is displayed on Confirmation page")
	public void message_is_displayed_on_Confirmation_page(String string)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();//getting on confirmation page 
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));// if its matching then test will pass
		
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable 
	{
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
    }
}
