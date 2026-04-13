package tests.subjects;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.subjects.CreateSubjectPage;
import pojo.Subject;
import utilities.Alertutil;
import utilities.LoginHelper;
import utilities.RandomDataUtil;

public class CreateSubject extends BaseTest{

	@Test(groups = {"Smoke", "Subjects"})
	public void createSubject(){
		logger.info("***Started Create new subject");
		LoginHelper.performLogin();
		new DashBoardPage(getDriver()).createSubject();
		
		CreateSubjectPage createSubjectPage = new CreateSubjectPage(getDriver());
		Subject subject = RandomDataUtil.getSubjectData();
		
		createSubjectPage.setSubjectId(subject.getId());
		createSubjectPage.selectStudy();
		createSubjectPage.selectGender(subject.getGender());
		createSubjectPage.setAge(subject.getAge());
		createSubjectPage.setEnrollmentDate(subject.getEnrollmentDate());
		createSubjectPage.saveSubject();
		WebElement alert = Alertutil.getAlert(getDriver());
		Assert.assertTrue(alert.getText().contains("Created"));
		logger.info("***Finished Create new subject");
	}
}
