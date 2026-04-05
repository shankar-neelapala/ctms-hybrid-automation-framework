package tests.subjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.subjects.SubjectsPage;

public class CancelDelete extends BaseTest{

	@Test
	public void cancelDeleteKeepsSubject() {
		logger.info("***Started cancel delete keeps subject***");
		BaseTest.performLogin();
		new DashBoardPage(getDriver()).navigateToSubjects();
		
		SubjectsPage subject = new SubjectsPage(getDriver());
		subject.searchSubjectById("ONC-001");
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		WebElement delete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn-del']")));
		logger.info("Click on delete");
		delete.click();
		logger.info("Click on cancel delete");
		subject.clickCancelDelete();
		
		Assert.assertTrue(subject.searchSubjectTableById("ONC-001"));
		logger.info("***Finished cancel delete keeps subject***");
	}
}
