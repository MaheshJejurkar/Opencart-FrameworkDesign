package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement button_myaccount;
	
	@FindBy(xpath = "//a[text()='Register']")
	WebElement button_register;
	
	@FindBy(xpath = "//a[text()='Login']")
	WebElement button_login;
	
	public void clickMyAccount() {
		button_myaccount.click();
	}

	public void clickRegister() {
		button_register.click();
	}
	
	public void clickLogin() {
		button_login.click();
	}
}
