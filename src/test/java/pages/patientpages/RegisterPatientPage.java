package pages.patientpages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import bases.BasePage;

public class RegisterPatientPage extends BasePage{
	
	public RegisterPatientPage(WebDriver driver) {
	    super(driver);
	}
	
	@FindBy(xpath="//input[@id='patientIdInput']") WebElement txtPatientId;
	@FindBy(xpath="//input[@id='firstNameInput']") WebElement txtFirstName;
	@FindBy(xpath="//input[@id='lastNameInput']") WebElement txtLastName;
	@FindBy(xpath="//input[@id='patientPhoneInput']") WebElement txtPatientPhone;
	@FindBy(xpath="//input[@id='patientAddressInput']") WebElement txtPatientAddress;
	@FindBy(xpath="//select[@id='patientGenderSelect']") WebElement drpGender;
	@FindBy(xpath="//input[@id='dobInput']") WebElement datePickerDob;
	@FindBy(xpath="//select[@id='bloodGroupSelect']") WebElement drpBloodGroup;
	@FindBy(xpath = "//input[@id='patientEmailInput']") WebElement txtEmail;
	@FindBy(xpath="//button[normalize-space()='Next']") WebElement linkNext;
	@FindBy(xpath="//select[@id='patientStudyDropdown']") WebElement drpAssignStudy;
	@FindBy(xpath="//select[@id='patientSiteDropdown']") WebElement drpSite;
	@FindBy(xpath="//button[@id='savePatientBtn']") WebElement btnRegister;
	@FindBy(xpath = "//div[@class='ctms-alert alert-danger']") WebElement errorMessage;
	
	
	By patientIdInput = By.id("patientIdInput");

	public void setPatientId(String id) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    WebElement input = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(patientIdInput)
	    );
	    
	    input.clear();
	    input.sendKeys(id);
	}
	
	public void setFirstName(String name) {
		txtFirstName.sendKeys(name);
	}
	
	public void setLastName(String name) {
		txtLastName.sendKeys(name);
	}
	
	public void setPatientPhone(String number) {
		txtPatientPhone.sendKeys(number);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setPatientAddress(String add) {
		txtPatientAddress.sendKeys(add);
	}
	
	public void selectGender(String gender) {		
		Select option = new Select(drpGender);
		option.selectByVisibleText(gender);
	}
	
	public void setDateOfBirth(String date) {
		datePickerDob.sendKeys(date);
	}
	
	public void setBloodGroup(String bg) {
		drpBloodGroup.sendKeys(bg);
	}
	
	public void clickNext() {
		linkNext.click();
	}
	
	public void assignStudy() {
		Select select = new Select(drpAssignStudy);
		List<WebElement> options = select.getOptions();
		WebElement randomOption = options.get(new Random().nextInt(options.size()));
		select.selectByVisibleText(randomOption.getText());
	}
	
	public void assignSite() {
		Select select = new Select(drpSite);
		List<WebElement> options = select.getOptions();
		WebElement randomOption = options.get(new Random().nextInt(options.size()));
		select.selectByVisibleText(randomOption.getText());
	}
	
	public void register() {
		btnRegister.click();
	}
	
	public boolean error() {
		try {
			return errorMessage.isDisplayed();
		}
		catch(NoSuchElementException e) {
			return false;
		}
	}
}
