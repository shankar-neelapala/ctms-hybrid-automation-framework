package tests.subjects;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.subjects.CreateSubjectPage;
import pojo.Subject;
import utilities.LoginHelper;
import utilities.RandomDataUtil;

public class AgeBoundaryValidation extends BaseTest{

	@Test(groups = {"Regression", "Subjects"})
	public void validateAge() {
		logger.info("***Started Create new subject");
		LoginHelper.performLogin();
		new DashBoardPage(getDriver()).createSubject();
		
		CreateSubjectPage createSubjectPage = new CreateSubjectPage(getDriver());
		Subject subject = RandomDataUtil.getSubjectData();
		
		createSubjectPage.setSubjectId(subject.getId());
		createSubjectPage.selectStudy();
		createSubjectPage.selectGender(subject.getGender());
		createSubjectPage.setAge(prop.getProperty("subject.invalid.age"));
		createSubjectPage.setEnrollmentDate(subject.getEnrollmentDate());
		createSubjectPage.saveSubject();
		Assert.assertTrue(createSubjectPage.isErrorDisplayed());
	}
}
