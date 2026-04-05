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
import pages.studypages.CreateStudyPage;

public class DuplicateStudyId extends BaseTest{

	@Test
	public void duplicateStudyIdRejection() {
		logger.info("***Started Duplicate Study Id Rejection***");
		BaseTest.performLogin();
		
		DashBoardPage dashboard = new DashBoardPage(getDriver());
		dashboard.createStudy();
		logger.info("Entering study details");
		CreateStudyPage study = new CreateStudyPage(getDriver());
		study.setStudyId("DIA-204");
		study.setStudyName("A Phase II Trial of Semaglutide in Type 2 Diabetes");
		study.selectPhase("Phase II");
		study.selectStatus("Active");
		study.setSponser("Novo Nordisk");
		study.setStartDate("27-2-2026");
		study.setEndDate("27-3-2026");
		study.saveStudy();
		
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ctms-alert alert-danger']")));
		Assert.assertTrue(alert.getText().contains("already exists"), "Study Id already exist");

	}
}
