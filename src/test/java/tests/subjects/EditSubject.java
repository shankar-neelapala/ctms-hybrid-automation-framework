package tests.subjects;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.subjects.CreateSubjectPage;
import pages.subjects.SubjectsPage;
import utilities.Alertutil;
import utilities.LoginHelper;

public class EditSubject extends BaseTest{

	@Test(groups = {"Regression", "Subjects"})
	public void editSubjectStatus() {
		logger.info("***Started edit subject status***");
		LoginHelper.performLogin();
		new DashBoardPage(getDriver()).navigateToSubjects();
		
		SubjectsPage subjectsPage = new SubjectsPage(getDriver());
		subjectsPage.searchSubjectById(prop.getProperty("subject.id"));
		subjectsPage.clickEdit();
		
		CreateSubjectPage createSubject = new CreateSubjectPage(getDriver());
		createSubject.selectStatus("Active");
		createSubject.saveSubject();

		WebElement alert = Alertutil.getAlert(getDriver());
		Assert.assertTrue(alert.getText().contains("Updated"));
		logger.info("***Finished edit subject status***");
	}
}
