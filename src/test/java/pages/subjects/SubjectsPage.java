package pages.subjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import bases.BasePage;

public class SubjectsPage extends BasePage{

	public SubjectsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='subjectSearch']") WebElement searchSubject;
	@FindBy(id = "subjectsTable") WebElement subjectsTable;
	@FindBy(xpath = "//button[@class='btn-edit me-1']") WebElement editSubject;
	By btnDelete = By.xpath("//button[@class='btn-del']");
	@FindBy(xpath="//button[@id='cancelDeleteBtn']") WebElement btnCancelDelete;
	
	
	public void clickCancelDelete() {
		btnCancelDelete.click();
	}
	
	public void clickDelete() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnDelete)).click();
	}
	
	public void clickEdit() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn-edit me-1']"))).click();
	}
	
	public void searchSubjectById(String id) {
		searchSubject.sendKeys(id);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean searchSubjectTableById(String id) {
		return subjectsTable.getText().contains(id);
	}
}
