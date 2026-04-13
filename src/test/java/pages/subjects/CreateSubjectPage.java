package pages.subjects;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import bases.BasePage;

public class CreateSubjectPage extends BasePage{

	public CreateSubjectPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='subjectIdInput']") WebElement txtSubjectId;
	@FindBy(xpath="//select[@id='studyDropdown']") WebElement drpStudy;
	@FindBy(xpath="//select[@id='genderSelect']") WebElement drpGender;
	@FindBy(xpath="//input[@id='ageInput']") WebElement txtAge;
	@FindBy(xpath="//select[@id='subjectStatusSelect']") WebElement drpStatus;
	@FindBy(xpath="//input[@id='enrollmentDateInput']") WebElement enrollDate;
	@FindBy(xpath="//button[@id='saveSubjectBtn']") WebElement btnSaveSubject;
	@FindBy(xpath="//button[normalize-space()='Cancel']") WebElement btnCancel;
	
	public boolean isErrorDisplayed() {
		return driver.findElement(By.xpath("//p[@class='form-error']")).isDisplayed();
	}
	
	public void clickCancel() {
		btnCancel.click();
	}
	
	public void setSubjectId(String name) {
		txtSubjectId.sendKeys(name);
	}
	
	public void selectStudy() {
		Select select = new Select(drpStudy);
		int num = select.getOptions().size();
		int index = 1 + new Random().nextInt(num - 1);
		select.selectByIndex(index);
		
	}
	
	public void selectGender(String gender) {
		Select option = new Select(drpGender);
		option.selectByVisibleText(gender);
	}
	
	public void setAge(String age) {
		txtAge.clear();
		txtAge.sendKeys(age);
	}
	
	public void selectStatus(String status) {
		Select option = new Select(drpStatus);
		option.selectByVisibleText(status);
	}
	
	public void setEnrollmentDate(String date) {
		enrollDate.sendKeys(date);
	}
	
	public void saveSubject() {
		btnSaveSubject.click();
	}

}
