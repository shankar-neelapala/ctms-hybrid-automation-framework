package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import bases.BaseTest;
import pages.DashBoardPage;
import pages.patientpages.PatientPage;
import pages.patientpages.RegisterPatientPage;


public class RegisterPatient extends BaseTest{
	
	@Test()
	public void register() {
		logger.info("***Started RegisterPatient***");
		DashBoardPage dashboard = new DashBoardPage(getDriver());
		dashboard.registerPatient();
		logger.info("Providing patient details");
		RegisterPatientPage patient = new RegisterPatientPage(getDriver());
		patient.setPatientId("P003");
		patient.setFirstName("Matheo");
		patient.setLastName("maxi");
		patient.selectGender("Male");
		patient.setDateOfBirth("12-05-2000");
		patient.setPatientPhone("9867542315");
		patient.setPatientAddress("Mumbai");
		patient.setBloodGroup("AB+");
		patient.clickNext();
		patient.assignStudy("ONC-ABC-301");
		patient.assignSite("Lakeside Endocrinology Clinic");
		logger.info("Registering the patient");
		patient.register();
		if(patient.error()) {
			Assert.fail("Patient ID already exists.");;
		}
		logger.info("Validation registered patient");
		boolean status = new PatientPage(getDriver()).searchPatientById("P003");
		Assert.assertTrue(status);
		logger.info("***Finished RegisterPatient");
	}
}
