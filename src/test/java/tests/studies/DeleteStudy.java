package tests.studies;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.studypages.StudiesPage;

public class DeleteStudy extends BaseTest{

	@Test
	public void deleteStudyById() {
		logger.info("***Started delete study by id***");
		BaseTest.performLogin();
		new DashBoardPage(getDriver()).navigateToStudies();
		
		StudiesPage studyPage = new StudiesPage(getDriver());
		studyPage.searchById("DIA-GT-202");
		if(!studyPage.searchStudiesTableById("DIA-GT-202")) {
			Assert.fail("Study id does not exist");
		}
		studyPage.clickDelete();
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-box sm']")));
		Assert.assertTrue(modal.isDisplayed());
		logger.info("Deleting study");
		studyPage.confirmDelete();
		WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctmsAlert")));
		Assert.assertTrue(alert.getText().contains("deleted"));
		Assert.assertFalse(studyPage.searchStudiesTableById("DIA-GT-202"));
		logger.info("***Finished delete study by id***");
	}
}
