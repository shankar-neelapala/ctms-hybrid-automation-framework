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

public class EmptyFieldsValidation extends BaseTest{

	@Test
	public void emptyFieldValitionLogin() {
		LoginPage lp = new LoginPage(getDriver());
		lp.clickOnLogin();
		
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
	    WebElement emailErr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailError")));
	    WebElement passErr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordError")));
	    Assert.assertEquals(emailErr.getText(), "Email is required");
	    Assert.assertEquals(passErr.getText(), "Password is required");

	}
}
