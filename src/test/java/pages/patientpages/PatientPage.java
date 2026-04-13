package pages.patientpages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import bases.BasePage;
import pages.DashBoardPage;

public class PatientPage extends BasePage{

	public PatientPage(WebDriver driver) {
		super(driver);
		new DashBoardPage(driver).navigateToPatient();
	}

	@FindBy(xpath = "//input[@id='patientSearch']") WebElement searchPatient;
	@FindBy(xpath = "//table//tbody//tr") WebElement tabelPatients;
	
	public boolean searchPatientById(String id) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(searchPatient));
		searchPatient.sendKeys(id);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(tabelPatients.getText() == null) {
			return false;
		}
		return true;
	}
}
