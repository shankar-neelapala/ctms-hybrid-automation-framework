package tests.studies;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.studypages.StudiesPage;
import utilities.LoginHelper;

public class SearchStudy extends BaseTest{

	@Test(groups = {"Regression", "Studies"})
	public void searchStudyById() {
		logger.info("***Started Search study by id***");
		LoginHelper.performLogin();
		new DashBoardPage(getDriver()).navigateToStudies();
		StudiesPage studyPage = new StudiesPage(getDriver());
		String id = prop.getProperty("study.id");
		studyPage.searchById(id);
		Assert.assertTrue(studyPage.searchStudiesTableById(id), "No such study");
		logger.info("***Started Search study by id***");
	}
}
