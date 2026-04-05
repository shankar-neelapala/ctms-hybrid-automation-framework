package pages.studypages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import bases.BasePage;

public class CreateStudyPage extends BasePage{

	public CreateStudyPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='studyIdInput']") WebElement txtStudyId;
	@FindBy(xpath = "//input[@id='studyNameInput']") WebElement txtStudyName;
	@FindBy(xpath = "//select[@id='phaseSelect']") WebElement drpPhase;
	@FindBy(xpath = "//select[@id='studyStatusSelect']") WebElement drpStatus;
	@FindBy(xpath = "//input[@id='sponsorInput']") WebElement txtSponser;
	@FindBy(xpath = "//input[@id='startDateInput']") WebElement startDate;
	@FindBy(xpath = "//input[@id='endDateInput']") WebElement endDate;
	@FindBy(xpath="//button[@id='saveStudyBtn']") WebElement btnSaveStudy;
	
	public void setStudyId(String id) {
		txtStudyId.sendKeys(id);
	}
	
	public void setStudyName(String name) {
		txtStudyName.sendKeys(name);
	}
	
	public void selectPhase(String phase) {
		Select option = new Select(drpPhase);
		option.selectByVisibleText(phase);
	}
	
	public void selectStatus(String status) {
		Select option = new Select(drpStatus);
		option.selectByVisibleText(status);
	}
	
	public void setSponser(String sponser) {
		txtSponser.sendKeys(sponser);
	}

	public void setStartDate(String date) {
		startDate.sendKeys(date);
	}
	
	public void setEndDate(String date) {
		endDate.sendKeys(date);
	}
	
	public void saveStudy() {
		btnSaveStudy.click();
	}
}
