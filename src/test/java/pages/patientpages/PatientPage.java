package pages.patientpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
		searchPatient.sendKeys(id);
		
		if(tabelPatients.getText() == null) {
			return false;
		}
		return true;
	}
}
