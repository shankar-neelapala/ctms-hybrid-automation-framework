package tests.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.LoginPage;

public class EmptyFieldsValidation extends BaseTest{

	@Test(groups = {"Regression", "Login"})
	public void emptyFieldValitionLogin() {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.clickOnLogin();
		String errors[] = loginPage.emailAndPasswordErrors();
	    Assert.assertEquals(errors[0], "Email is required");
	    Assert.assertEquals(errors[1], "Password is required");

	}
}
