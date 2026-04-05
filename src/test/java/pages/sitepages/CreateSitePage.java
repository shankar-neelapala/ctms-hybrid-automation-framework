package pages.sitepages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import bases.BasePage;

public class CreateSitePage extends BasePage{

	public CreateSitePage(WebDriver driver) {
		super(driver);
	}
	

	@FindBy(xpath="//input[@id='siteNameInput']") WebElement txtSiteName;
	@FindBy(xpath="//input[@id='piInput']") WebElement txtInvestigator;
	@FindBy(xpath="//input[@id='locationInput']") WebElement txtLocation;
	@FindBy(xpath="//input[@id='capacityInput']") WebElement txtCapacity;
	@FindBy(xpath="//select[@id='siteStatusSelect']") WebElement drpStatus;
	@FindBy(xpath="//input[@id='contactEmailInput']") WebElement txtContactEmail;
	@FindBy(xpath="//input[@id='phoneInput']") WebElement txtPhone;
	@FindBy(xpath="//button[@id='saveSiteBtn']") WebElement btnSaveSite;
	
	public void setSiteName(String name) {
		txtSiteName.sendKeys(name);
	}
	
	public void setInvestigator(String name) {
		txtInvestigator.sendKeys(name);
	}
	
	public void setLocation(String loc) {
		txtLocation.sendKeys(loc);
	}
	
	public void selectStatus(String status) {
		Select option = new Select(drpStatus);
		option.selectByVisibleText(status);
	}
	
	public void setCapacity(String cap) {
		txtCapacity.sendKeys(cap);
	}
	
	public void setContactEmail(String email) {
		txtContactEmail.sendKeys(email);
	}
	
	public void setPhoneNumber(String number) {
		txtPhone.sendKeys(number);
	}
	
	public void saveSite() {
		btnSaveSite.click();
	}
}
