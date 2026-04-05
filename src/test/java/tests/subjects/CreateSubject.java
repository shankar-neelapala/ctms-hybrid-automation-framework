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

public class CreateSubject extends BaseTest{

	@Test
	public void createSubject(){
		logger.info("***Started Create new subject");
		BaseTest.performLogin();
		new DashBoardPage(getDriver()).createSubject();
		
		CreateSubjectPage subject = new CreateSubjectPage(getDriver());
		subject.setSubjectId("DIA-100");
		subject.selectStudyById("DIA-204");
		subject.selectGender("Male");
		subject.setAge("50");
		subject.setEnrollmentDate("27-2-2026");
		subject.saveSubject();
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctmsAlert")));
		Assert.assertTrue(alert.getText().contains("Created"));
		logger.info("***Finished Create new subject");
	}
}
