package tests.sites;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.sitepages.CreateSitePage;
import pages.sitepages.SitesPage;

public class EditSiteStatus extends BaseTest{

	@Test
	public void editSiteStatusToInactive() {
		BaseTest.performLogin();
		DashBoardPage dashBoardPage = new DashBoardPage(getDriver());
		dashBoardPage.navigateToSites();
		
		SitesPage page = new SitesPage(getDriver());
		page.searchSite("Apollo Research Center");
		page.clickEdit();
		
		CreateSitePage site = new CreateSitePage(getDriver());
		site.selectStatus("Inactive");
		site.saveSite();
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctmsAlert")));
		Assert.assertTrue(alert.getText().contains("updated"));
		
	}
}
