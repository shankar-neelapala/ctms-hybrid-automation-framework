package tests.studies;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.studypages.CreateStudyPage;
import pojo.Study;
import utilities.LoginHelper;
import utilities.RandomDataUtil;

public class DuplicateStudyId extends BaseTest{

	@Test(groups = {"Regression", "Studies"})
	public void duplicateStudyIdRejection() {
		logger.info("***Started Duplicate Study Id Rejection***");
		LoginHelper.performLogin();
		
		DashBoardPage dashboard = new DashBoardPage(getDriver());
		dashboard.createStudy();
		logger.info("Entering study details");
		CreateStudyPage createStudyPage = new CreateStudyPage(getDriver());
		Study study = RandomDataUtil.getStudyData();
	
		createStudyPage.setStudyId(prop.getProperty("study.id"));
		createStudyPage.setStudyName(study.getStudyName());
		createStudyPage.selectPhase(study.getPhase());
		createStudyPage.setSponser(study.getSponser());
		createStudyPage.setStartDate(study.getStartDate());
		createStudyPage.setEndDate(study.getEndDate());
		createStudyPage.saveStudy();
		
		Assert.assertTrue(createStudyPage.getAlert().getText().contains("already exists"), "Study Id already exist");

	}
}
