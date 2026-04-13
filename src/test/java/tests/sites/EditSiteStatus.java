package tests.sites;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.sitepages.CreateSitePage;
import pages.sitepages.SitesPage;
import utilities.Alertutil;
import utilities.LoginHelper;

public class EditSiteStatus extends BaseTest{

	@Test(groups = {"Regression", "Sites"})
	public void editSiteStatusToInactive() {
		LoginHelper.performLogin();
		DashBoardPage dashBoardPage = new DashBoardPage(getDriver());
		dashBoardPage.navigateToSites();
		
		SitesPage sitesPage = new SitesPage(getDriver());
		sitesPage.searchSite(prop.getProperty("site.name"));
		sitesPage.clickEdit();
		
		CreateSitePage createSitePage = new CreateSitePage(getDriver());
		createSitePage.selectStatus("Inactive");
		createSitePage.saveSite();
		WebElement alert = Alertutil.getAlert(getDriver());
		Assert.assertTrue(alert.getText().contains("updated"));
		
	}
}
