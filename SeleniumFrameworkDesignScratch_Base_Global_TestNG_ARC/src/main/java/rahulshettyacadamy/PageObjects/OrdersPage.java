package rahulshettyacadamy.PageObjects;

import java.util.List;

import javax.swing.text.html.CSS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> ProductsName;

	@FindBy(css = ".totalRow button")
	WebElement checkOutEle;

	public Boolean VerifyOrderDiaplay(String productName) {
		Boolean match = ProductsName.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckOutPage goToCheckout() {
		checkOutEle.click();
		return new CheckOutPage(driver);
	}

}
