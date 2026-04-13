package tests.sites;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.sitepages.CreateSitePage;
import pages.sitepages.SitesPage;
import pojo.Site;
import utilities.Alertutil;
import utilities.LoginHelper;
import utilities.RandomDataUtil;


public class CreateSite extends BaseTest{

	@Test(groups = {"Smoke", "Sites"})
	public void createSite() {
		logger.info("***Started Create new site***");
		LoginHelper.performLogin();
		new DashBoardPage(getDriver()).createSite();		
		CreateSitePage createSitePage = new CreateSitePage(getDriver());
		
		Site site = RandomDataUtil.getSiteData();
		createSitePage.setSiteName(site.getSiteName());
		createSitePage.setInvestigator(site.getInvestigator());
		createSitePage.setLocation(site.getLocation());
		createSitePage.setCapacity(site.getCapacity());
		createSitePage.setContactEmail(site.getContactEmail());
		createSitePage.setPhoneNumber(site.getPhoneNumber());		
		createSitePage.saveSite();
		WebElement alert = Alertutil.getAlert(getDriver());
		Assert.assertTrue(alert.getText().contains("created"));
		Assert.assertTrue(new SitesPage(getDriver()).searchSiteTable(site.getSiteName()));
	}
}
