package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement input_email;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement input_password;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement button_Login;
	
	
	public void enterEmail(String email) {
		input_email.sendKeys(email);
	}
	
	public void enterPassword(String password) {
		input_password.sendKeys(password);
	}

	public void clickLogin() {
		button_Login.click();
	}
}
