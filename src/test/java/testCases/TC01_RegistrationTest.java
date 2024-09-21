package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC01_RegistrationTest extends BaseClass {

	public HomePage homepage;
	public RegistrationPage registrationPage;

	@Test(groups = {"Regression", "Master"})
	public void testUserRegistration() throws Exception {
		logger.info("***** Started TC01_RegistrationTest *****");
		try {
			driver.get(properties.getProperty("url"));

			homepage = new HomePage(driver);
			homepage.clickMyAccount();
			logger.info("Clicked on myaccount.");
			homepage.clickRegister();
			logger.info("Clicked on register.");

			String firstname = getRandomString();
			String lastname = getRandomString();
			String email = firstname + "." + lastname + "@gmail.com";
			String telephone = getRandomNumber();
			String password = firstname + "@" + lastname;

			registrationPage = new RegistrationPage(driver);
			logger.info("Entered firstname.");
			registrationPage.enterFirstname(firstname);
			logger.info("Entered lastname.");
			registrationPage.enterLastname(lastname);
			logger.info("Entered email.");
			registrationPage.enterEmail(email);
			logger.info("Enetered telephone no.");
			registrationPage.enterTelephone(telephone);
			logger.info("Enetered password.");
			registrationPage.enterPassword(password);
			logger.info("Entered password again.");
			registrationPage.enterConfirmpPassword(password);
			logger.info("Accepted privacy and policy term.");
			registrationPage.clickPrivacyPolicy();
			Thread.sleep(500);
			logger.info("Clicked on continue.");
			registrationPage.clickContinue();
			Thread.sleep(500);

			logger.info("Validated expected message.");
			String msg_confirmation = registrationPage.getConfirmationMsg();
			if (msg_confirmation.equals("Your Account Has Been Created!")) {
				logger.info("Your account registration has been done successfully.: "+email);
				logger.info("Clicked on logout.");
				registrationPage.clickLogout();
				Assert.assertTrue(true);
			} else {
				logger.info("Your account registration has been failed.: "+email);
				logger.error("Test failed.");
				logger.debug("Debug log.");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			logger.info("Your account registration has been failed.");
			logger.error("Test failed.");
			logger.debug("Debug log : ", e);
			Assert.fail();
		}
		logger.info("***** Finished TC01_RegistrationTest *****");
	}
}
