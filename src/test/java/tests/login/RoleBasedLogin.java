package tests.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.LoginPage;
import utilities.DataProviders;

public class RoleBasedLogin extends BaseTest{

	@Test(dataProvider = "login-roles", dataProviderClass = DataProviders.class, groups = {"smoke", "Login"})
	public void roleBasedLogin(String email, String password, String expectedRole) {
		logger.info("**Starting RoleBasedLogin***");
		LoginPage lp = new LoginPage(getDriver());
		logger.info("Providing Credentials");
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickOnLogin();
		
		DashBoardPage dashboard = new DashBoardPage(getDriver());
		boolean status = dashboard.loginConfirmation();
		if(status) {
			String actualRole = dashboard.getRole();
			dashboard.logout();
			Assert.assertEquals(actualRole.toLowerCase(), expectedRole.toLowerCase());
		}
		else {
			Assert.fail("Login failed!");
		}
		logger.info("**Finished RoleBasedLogin***");
	}
}
