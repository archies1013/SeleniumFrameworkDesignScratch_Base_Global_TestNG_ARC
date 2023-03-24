package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.checkerframework.checker.formatter.qual.ReturnsFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.dockerjava.api.model.Driver;

import rahulshettyacadamy.PageObjects.CartPage;
import rahulshettyacadamy.PageObjects.OrdersPage;

public class AbstractComponents {
	
	/**this will be the Parent Class of all PageObjectClasses, that is holding all reusable stuff
	 * Here instead of creating a object we will use concept called Inheritance
	 * (By.cssSelector(".mb-3"))) = this is By locator and return type is By
	 * driver.findElement(By.locator("value of locator")) ==this is webElement
	 * Only constructor can catch the variables
	 */
	
	WebDriver driver;
	public AbstractComponents(WebDriver driver)
	{
		super();
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	@FindBy(css="[routerlink*='cart']")
			WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersHeader;
	
	public void waitForElementToAppear(By findBy)
	{
		
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));//this is reusable code, synchronization code for every test case
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	wait.until(ExpectedConditions.visibilityOf(findBy));
		
		
	}
	
	public void waitForElementToDisAppear(WebElement ele) throws InterruptedException
	{
		//Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));		
	}
	
	/**WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));//this is reusable code, synchronization code for every test case
	*wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	 * @return 
	*/

	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	
	public OrdersPage goToOrdersPage()
	{
		ordersHeader.click();
		OrdersPage ordersPage=new OrdersPage(driver);
		return ordersPage;
	}
	
	
}
