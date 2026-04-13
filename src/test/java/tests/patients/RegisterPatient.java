package tests.patients;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import pages.DashBoardPage;
import pages.patientpages.PatientPage;
import pages.patientpages.RegisterPatientPage;
import pojo.Patient;
import utilities.LoginHelper;
import utilities.RandomDataUtil;


public class RegisterPatient extends BaseTest{
	
	@Test(groups = {"smoke", "Patients"})
	public void register() {
		logger.info("***Started RegisterPatient***");
		LoginHelper.performLogin();
		DashBoardPage dashboard = new DashBoardPage(getDriver());
		dashboard.registerPatient();
		logger.info("Providing patient details");
		RegisterPatientPage registerPatientPage = new RegisterPatientPage(getDriver());
		
		boolean isRegistered = false;
		int attempts = 0;
		String id = "";

		while (!isRegistered && attempts < 3) {
		    attempts++;
		    
		    Patient patient = RandomDataUtil.getPatientData();
		    id = patient.getId();
		    patient.setId(patient.getId() + attempts);

		    registerPatientPage.setPatientId(patient.getId());
		    registerPatientPage.setFirstName(patient.getFirstName());
		    registerPatientPage.setLastName(patient.getLastName());
		    registerPatientPage.selectGender(patient.getGender());
		    registerPatientPage.setDateOfBirth(patient.getDob());
		    registerPatientPage.setBloodGroup(patient.getBloodGroup());
		    registerPatientPage.setEmail(patient.getEmail());
		    registerPatientPage.setPatientPhone(patient.getPhoneNumber());
		    registerPatientPage.setPatientAddress(patient.getAddress());

		    registerPatientPage.clickNext();
		    registerPatientPage.assignStudy();
		    registerPatientPage.assignSite();
		    registerPatientPage.register();

		    if (!registerPatientPage.error()) {
		        isRegistered = true;
		    } else {
		        logger.warn("Duplicate ID detected. Retrying...");
		    }
		}
		Assert.assertTrue(isRegistered, "Failed to register patient after retries.");
		logger.info("Validation registered patient");
		boolean status = new PatientPage(getDriver()).searchPatientById(id);
		Assert.assertTrue(status);
		logger.info("***Finished RegisterPatient");
	}
}
