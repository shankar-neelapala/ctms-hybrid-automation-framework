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


public class CreateSite extends BaseTest{

	@Test
	public void createSite() {
		logger.info("***Started Create new site***");
		BaseTest.performLogin();
		new DashBoardPage(getDriver()).createSite();
		
		CreateSitePage site = new CreateSitePage(getDriver());
		site.setSiteName("Apollo Research Center");
		site.setInvestigator("Dr. John Smith");
		site.setLocation("Chennai");
		site.setCapacity("100");
		site.setContactEmail("apollo@research.com");
		site.setPhoneNumber("9878675423");
		site.saveSite();
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctmsAlert")));
		Assert.assertTrue(alert.getText().contains("created"));
		Assert.assertTrue(new SitesPage(getDriver()).searchSiteTable("Apollo Research Center"));
	}
}
