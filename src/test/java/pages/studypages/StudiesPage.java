package pages.studypages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import bases.BasePage;

public class StudiesPage extends BasePage{

	public StudiesPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//input[@id='studySearch']") WebElement searchStudy;
	@FindBy(xpath="//button[@class='btn-del']") WebElement btnDeleteStudy;
	@FindBy(xpath="//button[@id='confirmDeleteBtn']") WebElement btnConfirmDelete;
	@FindBy(id ="studiesTable") WebElement studiesTable;
	
	public boolean isModalDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-box sm']")));
		return modal.isDisplayed();
	}
	
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOf(studiesTable)).getText().contains(id);
	}
}
