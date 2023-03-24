package rahulshettyacadamy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

/**All the Objects which belongs to this landing page will go in this Landing Page Class.
 * Like this we are segregating the pieces without having in single file.
 *Details for landing page objects- UserEmail, User Password, LoginButton and define them.
 *Create on local object of "WebDriver driver;" and Create one constructor to bring driver from StandAloneTest to here.
 *Constructor takes the same name as a class name and the good thing about constructor is that will me the first method to execute when you touch your class
 *If anybody trying to initialize the object of this class then first constructor method will execute after that only anything will happen
 *That is the main advantage of this Constructor method bec people want to write any initialization code this is the best place 
 *to write bec this method initialize at the beginning before anything happen or any process execute in this class.
 */
public class ProductCatalogue extends AbstractComponents {
	
	//Create local variable of driver
	WebDriver driver;
	
	//Initializing the Constructor
	public ProductCatalogue(WebDriver driver)
	{
		/**Initializing the driver from StandAloneTest for that we need to create Object of this LandingPage into StandAloneTest then only you can access 
		 * that driver over here in this Constructor method.
		 * 
		 */
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	/**we need to do here List<WebElements> because those are more elements not single element
	 * Get me the all product list
	 * Here we created one Action method which will simply get the product list. for that we are waiting for product to appear on page
	 * and products are appearing then we calling List<WebElements> and returning all the products
	 */
	//this code is from StandAloneTest - waiting to load list of elements in cart  = below code with the help @FindBy and getProductList method
	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy=(By.cssSelector(".mb-3"));
	By addToCart= (By.cssSelector(".card-body button:last-of-type"));
	By toastMessage= (By.cssSelector(".card-body button:last-of-type"));
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);// here you cannot use PageFactory, it is exclusively for "driver.findElement constructor"
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException 
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisAppear(spinner);
		//int que = can you apply page factory within webelement.findelement = no its not possible
	}
	
	
	
	

}
