package tests.login;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import bases.BaseTest;

public class LogoutValidation extends BaseTest{

	@Test
	public void logout() {
		logger.info("**Started Logout Validation***");
		BaseTest.performLogin();
		logger.info("clicking on logout");
		getDriver().findElement(By.id("logoutBtn")).click();		
		Assert.assertTrue(getDriver().getCurrentUrl().contains("/login"));
	}
}
