package rahulshettyacadamy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.bytebuddy.implementation.bind.annotation.Super;
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
/**
 * Here we use "extends AbstractComponents" so whatever methods, components are
 * present in this class will be automatically applicable to our LandingPage
 * also that means you can reuse the code define here
 */
public class LandingPage extends AbstractComponents {

	// Create local variable of driver
	WebDriver driver;

	// Initializing the Constructor
	public LandingPage(WebDriver driver) {

		super(driver);// this
		/**
		 * Initializing the driver from StandAloneTest for that we need to create Object
		 * of this LandingPage into StandAloneTest then only you can access that driver
		 * over here in this Constructor method.
		 * 
		 */

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// WebElement userEmails = driver.findElement(By.id("userEmail"));//this line is
	// no more required now bec we created object with the help of PageFactory
	/**
	 * PageFactory = using that you can really reduce your syntax of creating your
	 * webElement With the help of @FindBy annotation at run time "(id="userEmail")"
	 * will constructed like this "driver.findElement(By.id("userEmail"));" which we
	 * comment out up and it will assign to the following like this "WebElement
	 * userEmail;" same as above commented line. This simplified way. To give
	 * knowledge of "driver" to PageFactory element, there is one method
	 * .initElements will trigger which initializes all the elements from
	 * PageFactory that is @FindBy annotation with two arguments in init method, one
	 * is (driver) and another one is (this) "this" refers to the current driver
	 * Basically you are telling to PageFactory Elements go and initialize all the
	 * elements, when initializing it needs "driver" as an argument and use that
	 * "driver" to initialize
	 */
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	// How to build Actions of elements
	/**
	 * we enter send keys information in as an argument PageObject should not hold
	 * any data, always come from other files
	 * 
	 */
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public ProductCatalogue loginApplication(String Email, String Password) {
		userEmail.sendKeys(Email);
		userPassword.sendKeys(Password);
		login.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
