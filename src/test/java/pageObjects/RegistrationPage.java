package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement input_firstname;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement input_lastname;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement input_email;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement input_telephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement input_password;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement input_confirmPassword;
	
	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement checkbox_policy;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement button_continue;
	
	@FindBy(xpath = "//div[@id='content']//h1")
	WebElement msg_confirmation;
	
	@FindBy(xpath = "//a[@class='list-group-item'][text()='Logout']")
	WebElement button_logout;
	
	public void enterFirstname(String firstname) {
		input_firstname.sendKeys(firstname);
	}
	
	public void enterLastname(String lastname) {
		input_lastname.sendKeys(lastname);
	}
	
	public void enterEmail(String email) {
		input_email.sendKeys(email);
	}
	
	public void enterTelephone(String telephone) {
		input_telephone.sendKeys(telephone);
	}
	
	public void enterPassword(String password) {
		input_password.sendKeys(password);
	}
	
	public void enterConfirmpPassword(String password) {
		input_confirmPassword.sendKeys(password);
	}
	
	public void clickPrivacyPolicy() {
		checkbox_policy.click();
	}
	
	public void clickContinue() {
		button_continue.click();
	}
	
	public void clickLogout() {
		button_logout.click();
	}
	
	public String getConfirmationMsg() {
		try {
			return msg_confirmation.getText();
		}catch(Exception e) {
			return e.getMessage();
		}	
	}
}
