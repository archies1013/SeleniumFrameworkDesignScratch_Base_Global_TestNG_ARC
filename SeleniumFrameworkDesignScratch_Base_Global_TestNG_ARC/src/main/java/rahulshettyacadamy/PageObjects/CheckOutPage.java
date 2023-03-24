package rahulshettyacadamy.PageObjects;

import javax.security.auth.callback.ConfirmationCallback;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	//@FindBy(xpath = "//input[@placeholder='Select Country']")
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".action__submit")
	//@FindBy(className ="btnn action__submit ng-star-inserted")
	WebElement submit;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement selectCountry;

	By resultsBy = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {

		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}

}
