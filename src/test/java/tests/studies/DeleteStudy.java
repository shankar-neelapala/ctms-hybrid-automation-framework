package tests.studies;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.studypages.StudiesPage;
import utilities.Alertutil;
import utilities.LoginHelper;

public class DeleteStudy extends BaseTest{

	@Test(groups = {"Regression", "Studies"})
	public void deleteStudyById() {
		logger.info("***Started delete study by id***");
		LoginHelper.performLogin();
		new DashBoardPage(getDriver()).navigateToStudies();
		
		StudiesPage studyPage = new StudiesPage(getDriver());
		String id = prop.getProperty("study.id");
		studyPage.searchById(id);
		if(!studyPage.searchStudiesTableById(id)) {
			Assert.fail("Study id does not exist");
		}
		studyPage.clickDelete();		
		Assert.assertTrue(studyPage.isModalDisplayed());
		logger.info("Deleting study");
		studyPage.confirmDelete();
		WebElement alert = Alertutil.getAlert(getDriver());
		Assert.assertTrue(alert.getText().contains("deleted"));
		Assert.assertFalse(studyPage.searchStudiesTableById(id));
		logger.info("***Finished delete study by id***");
	}
}
