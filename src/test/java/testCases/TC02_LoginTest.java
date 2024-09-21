package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC02_LoginTest extends BaseClass {
	public HomePage homepage;
	public LoginPage loginpage;
	public AccountPage accountpage;

	@Test(groups = {"Sanity", "Master"})
	public void testUserLogin() throws Exception {
		logger.info("***** Started TC02_LoginTest *****");
		try {
			driver.get(properties.getProperty("url"));
			//Home Page
			homepage = new HomePage(driver);
			logger.info("Clicked on myaccount link.");
			homepage.clickMyAccount();
			logger.info("Clicked on register link.");
			homepage.clickLogin();

			//Login Page
			loginpage = new LoginPage(driver);
			logger.info("Entered email address.");
			loginpage.enterEmail(properties.getProperty("email"));
			logger.info("Entered password.");
			loginpage.enterPassword(properties.getProperty("password"));
			Thread.sleep(500);
			loginpage.clickLogin();
			Thread.sleep(500);

			//Account Page
			accountpage = new AccountPage(driver);
			logger.info("Validated user login status.");
			boolean status = accountpage.isUserLoggedIn();
			if (status==true) {
				logger.info("User logged in successfully.: "+properties.getProperty("email"));
				logger.info("Clicked on logout.");
				accountpage.clickLogout();
				Assert.assertTrue(true);
			} else {
				logger.info("User login failed.: "+properties.getProperty("email"));
				logger.error("Test failed.");
				logger.debug("Debug log.");
				Assert.assertTrue(false);
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
