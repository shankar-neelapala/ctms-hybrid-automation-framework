package tests.login;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;

public class ProtectedRouteRedirectToLogin extends BaseTest{

	@Test
	public void protectedRouteRedirectToLogin() {
		
		getDriver().get("http://localhost:5173/dashboard");
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.urlContains("/login"));
	    Assert.assertTrue(getDriver().getCurrentUrl().contains("/login"));

	}
}
