package tests.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.LoginPage;

public class ProtectedRouteRedirectToLogin extends BaseTest{

	@Test(groups = {"smoke", "Login"})
	public void protectedRouteRedirectToLogin() {
		
		getDriver().get(prop.getProperty("dashboard.url"));
		boolean status = new LoginPage(getDriver()).validateLoginUrl();
	    Assert.assertTrue(status);

	}
}
