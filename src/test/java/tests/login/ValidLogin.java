package tests.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.LoginPage;

public class ValidLogin extends BaseTest{
	
	@Test
	public void validLogin() { 
		logger.info("**Starting Valid Login***");
		LoginPage lp = new LoginPage(getDriver());
		logger.info("Providing Credentials");
		lp.setEmail(prop.getProperty("email"));
		lp.setPassword(prop.getProperty("password"));
		lp.clickOnLogin();
		
		DashBoardPage dashboard = new DashBoardPage(getDriver());
		boolean status = dashboard.loginConfirmation();
		logger.info("Login validation");
		Assert.assertTrue(status, "Login should succeed with valid credentails");
		logger.info("**Finished Valid Login***");
	}
}
