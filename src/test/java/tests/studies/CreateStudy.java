package tests.studies;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.studypages.CreateStudyPage;
import pages.studypages.StudiesPage;
import pojo.Study;
import utilities.Alertutil;
import utilities.LoginHelper;
import utilities.RandomDataUtil;

public class CreateStudy extends BaseTest{

	@Test(groups = {"Smoke", "Studies"})
	public void createStudy() {
		logger.info("***Started CreateStudy***");
		LoginHelper.performLogin();
		
		DashBoardPage dashboard = new DashBoardPage(getDriver());
		dashboard.createStudy();
		logger.info("Entering study details");
		CreateStudyPage createStudyPage = new CreateStudyPage(getDriver());
		
		Study study = RandomDataUtil.getStudyData();
		createStudyPage.setStudyId(study.getId());
		createStudyPage.setStudyName(study.getStudyName());
		createStudyPage.selectPhase(study.getPhase());
		createStudyPage.setSponser(study.getSponser());
		createStudyPage.setStartDate(study.getStartDate());
		createStudyPage.setEndDate(study.getEndDate());
		createStudyPage.saveStudy();
		WebElement alert = Alertutil.getAlert(getDriver());
		Assert.assertTrue(alert.getText().contains("created"));
		
		Assert.assertTrue(new StudiesPage(getDriver()).searchStudiesTableById(study.getId()));
		logger.info("***Finished CreateStudy***");
	}
}
