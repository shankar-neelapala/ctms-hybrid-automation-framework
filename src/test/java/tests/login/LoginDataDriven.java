package tests.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.LoginPage;
import utilities.DataProviders;

public class LoginDataDriven extends BaseTest{

	@Test(dataProvider = "login-data", dataProviderClass  = DataProviders.class, groups = {"Regression", "Login"})
	public void login(String email, String password, String expectedResult) {
		logger.info("***Started LoginDataDrivenTC002***");
		LoginPage lp = new LoginPage(getDriver());
		logger.info("Providing details");
		lp.setEmail(email);
		lp.setPassword(password);
		logger.info("Clicking on login");
		lp.clickOnLogin();
		
		DashBoardPage dashboard = new DashBoardPage(getDriver());
		boolean status = dashboard.loginConfirmation();
		logger.info("login validation");
		if(expectedResult.equalsIgnoreCase("valid")) {
			Assert.assertTrue(status, "Login should succeed for valid credentials");
		}
		else if(expectedResult.equalsIgnoreCase("invalid")) {
			Assert.assertFalse(status, "Login should fail for invalid credentials");
		}
		if(status) {
			dashboard.logout();
		}
		logger.info("***Finished LoginDataDrivenTC002***");
	}
}
