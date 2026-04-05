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
import pages.subjects.CreateSubjectPage;
import pages.subjects.SubjectsPage;

public class EditSubject extends BaseTest{

	@Test
	public void editSubjectStatus() {
		logger.info("***Started edit subject status***");
		BaseTest.performLogin();
		new DashBoardPage(getDriver()).navigateToSubjects();
		
		SubjectsPage subject = new SubjectsPage(getDriver());
		subject.searchSubjectById("ONC-001");
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		WebElement edit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn-edit me-1']")));
		edit.click();
		
		CreateSubjectPage createSubject = new CreateSubjectPage(getDriver());
		createSubject.selectStatus("Active");
		createSubject.saveSubject();

		WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctmsAlert")));
		Assert.assertTrue(alert.getText().contains("Updated"));
		logger.info("***Finished edit subject status***");
	}
}
