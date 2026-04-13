package tests.sites;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.sitepages.SitesPage;
import utilities.LoginHelper;

public class SearchSite extends BaseTest{

	@Test(groups = {"Regression", "Sites"})
	public void searchSiteByLocation() {
		logger.info("***Started search site by location***");
		LoginHelper.performLogin();
		new DashBoardPage(getDriver()).navigateToSites();
		SitesPage sitesPage = new SitesPage(getDriver());
		String location = prop.getProperty("site.location");
		sitesPage.searchSite(location);
		boolean status = sitesPage.searchSiteTable(location);
		Assert.assertTrue(status, "Location not found");
	}
}
