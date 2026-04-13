package tests.sites;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.sitepages.CreateSitePage;
import pages.sitepages.SitesPage;
import pojo.Site;
import utilities.LoginHelper;
import utilities.RandomDataUtil;

public class InvalidEmailValidation extends BaseTest{

	@Test(groups = {"Regression", "Sites"})
	public void siteInvalidEmailValidation() {
		logger.info("***Started site invalid email validation***");
		LoginHelper.performLogin();
		new DashBoardPage(getDriver()).createSite();
		
		CreateSitePage createSitePage = new CreateSitePage(getDriver());
		
		Site site = RandomDataUtil.getSiteData();
		createSitePage.setSiteName(site.getSiteName());
		createSitePage.setInvestigator(site.getInvestigator());
		createSitePage.setLocation(site.getLocation());
		createSitePage.setCapacity(site.getCapacity());
		createSitePage.setContactEmail(prop.getProperty("site.invalid.email"));
		createSitePage.setPhoneNumber(site.getPhoneNumber());
		createSitePage.saveSite();
		Assert.assertTrue(new SitesPage(getDriver()).validateEmail());
		logger.info("***Finished site invalid email validation***");
	}
}
