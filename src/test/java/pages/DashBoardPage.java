package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import bases.BasePage;

public class DashBoardPage extends BasePage{

	public DashBoardPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//i[@class='bi bi-chevron-down']") WebElement myAccount;
	@FindBy(xpath="//button[@id='menuLogout']") WebElement linkLogout;
	@FindBy(xpath = "//a[@id='nav-patients']") WebElement navPatient;
	@FindBy(xpath = "//button[@id='createPatientBtn']") WebElement btnNewpatient;
	@FindBy(xpath = "//div[@class='sb-footer']//div//span") WebElement txtRole;
	@FindBy(xpath = "//a[@id='nav-studies']") WebElement navStudies;
	@FindBy(xpath = "//button[@id='createStudyBtn']") WebElement btnNewStudy;
	@FindBy(xpath="//a[@id='nav-sites']") WebElement navSites;
	@FindBy(xpath="//button[@id='createSiteBtn']") WebElement btnCreateSite;
	@FindBy(xpath="//a[@id='nav-subjects']") WebElement navSubjects;
	@FindBy(xpath="//button[@id='createSubjectBtn']") WebElement btnNewSubject;
	
	public void navigateToSubjects() {
		navSubjects.click();
	}
	
	public void createSubject() {
		navSubjects.click();
		btnNewSubject.click();
	}
	
	public void createSite() {
		navSites.click();
		btnCreateSite.click();
	}
	
	public void navigateToSites() {
		navSites.click();
	}
	
	public void createStudy() {
		navStudies.click();
		btnNewStudy.click();		
	}
	
	public void navigateToStudies() {
		navStudies.click();
	}
	
	public boolean loginConfirmation() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	    try{
	    	return wait.until(ExpectedConditions.urlContains("/dashboard"));
	    }
	    catch(Exception e) {
	    	return false;
	    }
	}
	
	public void logout() {
		myAccount.click();
		linkLogout.click();
	}
	
	public void registerPatient() {
		navPatient.click();
		btnNewpatient.click();
	}
	
	public void navigateToPatient() {
		navPatient.click();
	}
	
	public String getRole() {
		return txtRole.getText();
	}
}
