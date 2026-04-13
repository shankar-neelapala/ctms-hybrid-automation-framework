package tests.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.LoginPage;

public class InvalidLogin extends BaseTest{

	@Test(groups = {"Regression", "Login"})
	public void invalidLogin() { 
		logger.info("**Starting Invalid Login***");
		LoginPage loginPage = new LoginPage(getDriver());
		logger.info("Providing Credentials");
		loginPage.setEmail(prop.getProperty("invalid.email"));
		loginPage.setPassword(prop.getProperty("invalid.email"));
		loginPage.clickOnLogin();
		
		String loginError[] = loginPage.loginError();
		Assert.assertTrue(loginError[0] == "true" ? true : false);
	    Assert.assertTrue(loginError[1].contains("Invalid email or password"));
		logger.info("**Finished Invalid Login***");
	}
}
