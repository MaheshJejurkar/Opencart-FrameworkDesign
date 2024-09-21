package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

	public WebDriver driver;

	public AccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@id='content']//h2[text()='My Account']")
	WebElement text_myAccount;
	
	@FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
	WebElement button_logout;
	

	public boolean isUserLoggedIn() {
		try {
			return text_myAccount.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		button_logout.click();
	}
}
