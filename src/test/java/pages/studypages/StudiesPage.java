package pages.studypages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import bases.BasePage;

public class StudiesPage extends BasePage{

	public StudiesPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//input[@id='studySearch']") WebElement searchStudy;
	@FindBy(xpath="//button[@class='btn-del']") WebElement btnDeleteStudy;
	@FindBy(xpath="//button[@id='confirmDeleteBtn']") WebElement btnConfirmDelete;
	@FindBy(id ="studiesTable") WebElement studiesTable;
	
	public void searchById(String id) {
		searchStudy.sendKeys(id);
	}
	
	public void clickDelete() {
		btnDeleteStudy.click();
	}
	
	public void confirmDelete() {
		btnConfirmDelete.click();
	}
	
	public boolean searchStudiesTableById(String id) {
		return studiesTable.getText().contains(id);
	}
}
