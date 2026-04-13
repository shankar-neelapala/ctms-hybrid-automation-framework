package tests.subjects;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.subjects.SubjectsPage;
import utilities.LoginHelper;

public class CancelDelete extends BaseTest{

	@Test(groups = {"Regression", "Subjects"})
	public void cancelDeleteKeepsSubject() {
		logger.info("***Started cancel delete keeps subject***");
		LoginHelper.performLogin();
		new DashBoardPage(getDriver()).navigateToSubjects();
		
		SubjectsPage subjectsPage = new SubjectsPage(getDriver());
		String id = prop.getProperty("subject.id");
		subjectsPage.searchSubjectById(id);
		subjectsPage.clickDelete();
		logger.info("Click on delete");
		logger.info("Click on cancel delete");
		subjectsPage.clickCancelDelete();
		
		Assert.assertTrue(subjectsPage.searchSubjectTableById(id));
		logger.info("***Finished cancel delete keeps subject***");
	}
}
