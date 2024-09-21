package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC03_LoginTestUsingDataProvider extends BaseClass {
	public HomePage homepage;
	public LoginPage loginpage;
	public AccountPage accountpage;

	@Test(groups = "DataDriven" , dataProvider = "getLoginData", dataProviderClass = Utilities.DataProviderClass.class)
	public void validate_userLogin(String email, String password, String expected_status) throws Exception {
		logger.info("***** Started TC02_LoginTest *****");
		try {
			driver.get(properties.getProperty("url"));
			// Home Page
			homepage = new HomePage(driver);
			logger.info("Clicked on myaccount.");
			homepage.clickMyAccount();
			logger.info("Clicked on register.");
			homepage.clickLogin();

			// Login Page
			loginpage = new LoginPage(driver);
			logger.info("Entered email address.");
			loginpage.enterEmail(email);
			logger.info("Entered password.");
			loginpage.enterPassword(password);
			Thread.sleep(500);
			logger.info("Clicked on login.");
			loginpage.clickLogin();
			Thread.sleep(500);

			// Account Page
			accountpage = new AccountPage(driver);
			logger.info("Validated user login status.");
			boolean status_userlogin = accountpage.isUserLoggedIn();

			if (expected_status.equalsIgnoreCase("Valid")) {
				if (status_userlogin == true) {
					logger.info("Clicked on logout.");
					logger.info("Passed : Valid user and able to login: " + email);
					accountpage.clickLogout();
					Assert.assertTrue(true);
				} else {
					logger.info("Failed : Valid user but still not able to login: " + email);
					Assert.assertTrue(false);
				}
			} else if (expected_status.equalsIgnoreCase("Invalid")) {
				if (status_userlogin == true) {
					logger.info("Clicked on logout.");
					logger.info("Failed : Invalid user but still able to login: " + email);
					accountpage.clickLogout();
					Assert.assertTrue(false);
				} else {
					logger.info("Passed: Invalid user and not able to login: " + email);
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			logger.info("User login failed.");
			logger.error("Test failed.");
			logger.debug("Debug log : ", e);
			Assert.fail();
		}
		logger.info("***** Finished TC02_LoginTest *****");
	}
}
