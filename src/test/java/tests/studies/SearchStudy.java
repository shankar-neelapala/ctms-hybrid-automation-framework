package tests.studies;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.studypages.StudiesPage;

public class SearchStudy extends BaseTest{

	@Test
	public void searchStudyById() {
		logger.info("***Started Search study by id***");
		BaseTest.performLogin();
		new DashBoardPage(getDriver()).navigateToStudies();
		StudiesPage studyPage = new StudiesPage(getDriver());
		studyPage.searchById("dia-204");
		Assert.assertTrue(studyPage.searchStudiesTableById("DIA-204"), "No such study");
		logger.info("***Started Search study by id***");
	}
}
