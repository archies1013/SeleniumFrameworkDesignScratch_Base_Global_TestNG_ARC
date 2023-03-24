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

public class StandAloneTest {

	public static void main(String[] args) {
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
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("archies1013@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Mullen@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));//
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		/**explicit wait on global level,this is how you can avoid synchronization issue
		 * while loading the icon or page
		 */
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		/**
		 * this two above lines of code need JavaSE 1.8, older version won't work for
		 * this code please check text on website when you enter in the code "ZARA COAT
		 * 3" if mismatch you will get error that your prod is null ExplicitWait
		 * --WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		 * --wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		 * ng-animating (frm RSA)--locator for loading when click on add to cart button
		 * if you cannot see loading locator in developer tool ask to developer what is
		 * the locator they have written for loading icon
		 */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));//this
		// step is taking a more time, will do directly from driver.findelemt
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));//
		/**
		 * expecting total webelement thats why driver.findelement
		 */
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		/**
		 * Now we have to find the common locators for items which are already in the
		 * cart Logic to verify items in the cart with Streams and Checkout
		 */
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		/**
		 * grab all cart elements in variable Now we have use JavaStreams again to
		 * iterate and to see "ZARA COAT 3" is present in cart or not from list of
		 * elements
		 */
		// cartProducts.stream().filter(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		/**
		 * We are iterating through list of cartproducts and on every iteration one
		 * product get retrieved on that webelement so on first iteration first product
		 * will sit in "cartProduct" on this element we are doing getText and checking
		 * if it equals to "productName" so here instead of .filter we will use
		 * .anyMatch to see any product matches to "productName" that is "ZARA COAT 3"
		 * if it matches any of the product it will return boolean value and it returns
		 * true if this matches correctly any of the value of elements for that we will
		 * Assertion here
		 */
		Assert.assertTrue(match);// this method will accept only true
		driver.findElement(By.cssSelector(".totalRow button")).click();
		/**
		 * Here we will use Action Class to select the country at checkout page
		 */
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
		/**
		 * Now we have to wait until the dropdown names gets opens for that we will give
		 * Explicit wait here
		 */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));//
		/**this is specific element
		 * it will wait until that countries name opens on the page
		 * India from PlaceOrder
		 */
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();// this is to get text after placeorder click
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));// if its matching then test will pass
		driver.close();																				
	

	}

}
