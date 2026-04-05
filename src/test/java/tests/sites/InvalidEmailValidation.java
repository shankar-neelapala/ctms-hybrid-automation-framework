package tests.sites;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.sitepages.CreateSitePage;

public class InvalidEmailValidation extends BaseTest{

	@Test
	public void siteInvalidEmailValidation() {
		logger.info("***Started site invalid email validation***");
		BaseTest.performLogin();
		new DashBoardPage(getDriver()).createSite();
		
		CreateSitePage site = new CreateSitePage(getDriver());
		site.setSiteName("Apollo Research Center");
		site.setInvestigator("Dr. John Smith");
		site.setLocation("Chennai");
		site.setCapacity("100");
		site.setContactEmail("apolloresearch.com");
		site.setPhoneNumber("9878675423");
		site.saveSite();
		
		Assert.assertTrue(getDriver().findElement(By.xpath("//p[@class='form-error']")).isDisplayed());
		logger.info("***Finished site invalid email validation***");
	}
}
