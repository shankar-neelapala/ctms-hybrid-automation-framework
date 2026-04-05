package tests.login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.LoginPage;

public class InvalidLogin extends BaseTest{

	@Test
	public void invalidLogin() { 
		logger.info("**Starting Invalid Login***");
		LoginPage lp = new LoginPage(getDriver());
		logger.info("Providing Credentials");
		lp.setEmail("demo@ctms.com");
		lp.setPassword("Demo@123");
		lp.clickOnLogin();
		
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginError")));
		Assert.assertTrue(errorMsg.isDisplayed());
	    Assert.assertTrue(errorMsg.getText().contains("Invalid email or password"));
		logger.info("**Finished Invalid Login***");
	}
}
