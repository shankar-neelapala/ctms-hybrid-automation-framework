package pages.sitepages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import bases.BasePage;

public class SitesPage extends BasePage{

	public SitesPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "sitesTable") WebElement sitesTable;
	@FindBy(xpath="//input[@id='siteSearch']") WebElement searchSite;
	@FindBy(xpath = "//table//tr[1]//td[8]//button[1]") WebElement btnEditSite;
	@FindBy(xpath = "//p[@class='form-error']") WebElement formError;
	
	public boolean validateEmail() {
		return formError.isDisplayed();
	}
	
	public void clickEdit() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(btnEditSite));
		btnEditSite.click();
	}
	
	public boolean searchSiteTable(String name) {
		return sitesTable.getText().contains(name);
	}
	
	public void searchSite(String name) {
		searchSite.sendKeys(name);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
