package tests.subjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.subjects.CreateSubjectPage;

public class AgeBoundaryValidation extends BaseTest{

	@Test
	public void validateAge() {
		logger.info("***Started Create new subject");
		BaseTest.performLogin();
		new DashBoardPage(getDriver()).createSubject();
		
		CreateSubjectPage subject = new CreateSubjectPage(getDriver());
		subject.setSubjectId("DIA-100");
		subject.selectStudyById("DIA-204");
		subject.selectGender("Male");
		subject.setAge("0");//invalid age
		subject.setEnrollmentDate("27-2-2026");
		subject.saveSubject();
		Assert.assertTrue(getDriver().findElement(By.xpath("//p[@class='form-error']")).isDisplayed());
		
		subject.setAge("134");
		subject.saveSubject();
		WebElement error = getDriver().findElement(By.xpath("//p[@class='form-error']"));
		Assert.assertTrue(error.isDisplayed(), error.getText());
	}
}
