package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import pojo.Patient;
import pojo.Site;
import pojo.Study;
import pojo.Subject;

public class RandomDataUtil {
	
	public static Subject getSubjectData() {
		Subject subject = new Subject();
		Faker faker = new Faker();
		
		subject.setId("CTMS-SUB-"+faker.number().digits(3));		
		String gender = faker.options().option(
			    "Male",
			    "Female"
			);
		subject.setGender(gender);
		subject.setAge(String.valueOf(faker.number().numberBetween(18, 100)));
		Date date = faker.date().birthday(18, 100);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		subject.setEnrollmentDate(sdf.format(date));
		
		return subject;
	}
	
	public static Study getStudyData() {
		Study study = new Study();
		Faker faker = new Faker();
		
		String id = "CTMS-STU-"+faker.number().digits(3);		
		study.setId(id);
		study.setStudyName(faker.lorem().characters(15));
		
		String phase = faker.options().option(
				"Phase I",
				"Phase II",
				"Phase III",
				"Phase IV"
				);
		
		study.setPhase(phase);
		study.setSponser(faker.name().firstName());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date startDate = faker.date().past(30, TimeUnit.DAYS);
		Date endDate = faker.date().future(30, TimeUnit.DAYS, startDate);
		study.setStartDate(sdf.format(startDate));
		study.setEndDate(sdf.format(endDate));
		
		return study;
	}
	
	public static Site getSiteData() {
		Site site = new Site();
		Faker faker = new Faker();		
		String hospital = faker.options().option(
			    "Apollo Hospital",
			    "Care Hospital",
			    "Sunrise Hospital",
			    "City Medical Center",
			    "Global Health Hospital"
			);
		site.setSiteName(hospital);
		site.setInvestigator("Dr. "+faker.name().firstName());
		site.setLocation(faker.address().city());
		site.setCapacity(String.valueOf(faker.number().numberBetween(20, 100)));
		site.setContactEmail(hospital.replace(" ", ".")+"@research.com");
		site.setPhoneNumber(faker.phoneNumber().cellPhone());
		
		return site;
	}

	public static Patient getPatientData() {
		Patient patient = new Patient();
		Faker faker = new Faker();		
		String id = "PAT-"+faker.number().digits(4);
		patient.setId(id);
		patient.setFirstName(faker.name().firstName());
		patient.setLastName(faker.name().lastName());
		
		String gender = faker.options().option(
			    "Male",
			    "Female"
			);
		
		patient.setGender(gender);
		
		Date dobDate = faker.date().birthday(18, 100);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String dob = sdf.format(dobDate);
		patient.setDob(dob);		
		String bloodGroup = faker.options().option(
			    "AB+", "AB-", "A+", "A-", "B+", "B-", "O+", "O-"
			);
		patient.setBloodGroup(bloodGroup);
		patient.setEmail(faker.internet().emailAddress());
		patient.setPhoneNumber(faker.phoneNumber().cellPhone().toString());
		patient.setAddress(faker.address().city());
		
		return patient;
	}
}
