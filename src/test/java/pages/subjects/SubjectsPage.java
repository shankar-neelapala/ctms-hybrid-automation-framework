package pages.subjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import bases.BasePage;

public class SubjectsPage extends BasePage{

	public SubjectsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='subjectSearch']") WebElement searchSubject;
	@FindBy(id = "subjectsTable") WebElement subjectsTable;
	@FindBy(xpath = "//button[@class='btn-edit me-1']") WebElement editSubject;
	@FindBy(xpath = "//button[@class='btn-del']") WebElement btnDelete;
	@FindBy(xpath="//button[@id='cancelDeleteBtn']") WebElement btnCancelDelete;
	
	public void clickCancelDelete() {
		btnCancelDelete.click();
	}
	
	public void clickDelete() {
		btnDelete.click();
	}
	
	public void clickEdit() {
		editSubject.click();
	}
	
	public void searchSubjectById(String id) {
		searchSubject.sendKeys(id);
	}
	
	public boolean searchSubjectTableById(String id) {
		return subjectsTable.getText().contains(id);
	}
}
