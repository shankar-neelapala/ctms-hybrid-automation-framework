package pages.sitepages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import bases.BasePage;

public class SitesPage extends BasePage{

	public SitesPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "sitesTable") WebElement sitesTable;
	@FindBy(xpath="//input[@id='siteSearch']") WebElement searchSite;
	@FindBy(xpath = "//button[@class='btn-edit me-1']") WebElement btnEditSite;
	
	public void clickEdit() {
		btnEditSite.click();
	}
	
	public boolean searchSiteTable(String name) {
		return sitesTable.getText().contains("Apollo Research Center");
	}
	
	public void searchSite(String name) {
		searchSite.sendKeys(name);
	}
}
