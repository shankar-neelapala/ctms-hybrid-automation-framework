package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import bases.BasePage;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//input[@id='email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='password']") WebElement txtPassword;
	@FindBy(xpath="//button[@id='loginBtn']") WebElement btnLogin;
	By emailError = By.id("emailError");
	By passwordError = By.id("passwordError");
	By loginError = By.id("loginError");
	@FindBy(id = "logoutBtn") WebElement btnLogout;
	
	public boolean clickLogout() {
		btnLogout.click();
		return driver.getCurrentUrl().contains("/login");
	}
	
	public boolean validateLoginUrl() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.urlContains("/login"));
		return driver.getCurrentUrl().contains("/login");
	}
	
	public String[] loginError() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(loginError));
		String status = errorMsg.isDisplayed() ? "true" : "false";
		return new String [] {status, errorMsg.getText()};
	}
	
	public String[] emailAndPasswordErrors() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	    WebElement emailErr = wait.until(ExpectedConditions.visibilityOfElementLocated(emailError));
	    WebElement passErr = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError));
	    
	    return new String[] {emailErr.getText(), passErr.getText()};
	}
	
	public void setEmail(String email) {
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	public void setPassword(String password) {
		txtPassword.clear();
		txtPassword.sendKeys(password);
	}
	
	public void clickOnLogin() {
		btnLogin.click();
	}
}
