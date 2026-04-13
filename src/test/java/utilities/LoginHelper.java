package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import bases.BaseTest;
import pages.LoginPage;

public class LoginHelper {

	public static void performLogin()  {
		Properties prop = null;
		try {
			FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
			prop = new Properties();
			prop.load(file);
		}
		catch(IOException e) {}
		LoginPage lp = new LoginPage(BaseTest.getDriver());
		lp.setEmail(prop.getProperty("valid.email"));
		lp.setPassword(prop.getProperty("valid.password"));
		lp.clickOnLogin();
	}
}
