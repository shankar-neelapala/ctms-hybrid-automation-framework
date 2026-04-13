package tests.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.LoginPage;
import utilities.LoginHelper;

public class LogoutValidation extends BaseTest{

	@Test(groups = {"smoke", "Login"})
	public void logout() {
		logger.info("**Started Logout Validation***");
		LoginHelper.performLogin();
		logger.info("clicking on logout");
		LoginPage loginPage = new LoginPage(getDriver());
		boolean status =loginPage.clickLogout();		
		Assert.assertTrue(status);
	}
}
