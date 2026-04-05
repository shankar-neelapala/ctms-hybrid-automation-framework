package tests.sites;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.sitepages.SitesPage;

public class SearchSite extends BaseTest{

	@Test
	public void searchSiteByLocation() {
		logger.info("***Started search site by location***");
		BaseTest.performLogin();
		new DashBoardPage(getDriver()).navigateToSites();
		SitesPage site = new SitesPage(getDriver());
		site.searchSite("chennai");		
		Assert.assertTrue(site.searchSiteTable("chennai"), "Location not found");
	}
}
