package bases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import pages.LoginPage;

public class BaseTest{
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public static void setDriver(WebDriver driverInstance) {
		driver.set(driverInstance);
	}
	public static  WebDriver getDriver() {
		return driver.get();
	}
	
	public Properties prop;
	public  Logger logger;
	
	@BeforeClass
	@Parameters({"os", "browser"})
	public void setUp(String os, String browser) throws IOException {
		FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
		prop = new Properties();
		prop.load(file);
		
		logger = (Logger) LogManager.getLogger(this.getClass());
		
		switch (browser.toLowerCase()) {
		case "chrome" : {
							ChromeOptions options = new ChromeOptions();
							options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
							options.addArguments("--disable-blink-features=AutomationControlled");
							options.addArguments("--incognito");
							setDriver(new ChromeDriver(options));
						};break;
		case "edge" : {
							EdgeOptions options = new EdgeOptions();
							options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
							options.addArguments("--disable-blink-features=AutomationControlled");
							options.addArguments("--incognito");
							setDriver(new EdgeDriver(options));break;
					  }
		default : System.out.println("Invalid Browser!");break;
		}
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("appurl"));
	}
	
	@AfterClass
	public void tearDown() {
		getDriver().quit();
	}
	
	public static String captureScreenshot(String name) {
		TakesScreenshot ts = (TakesScreenshot) getDriver();
		File file = ts.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
		String fileName = System.getProperty("user.dir")+"\\screenshots\\"+name+timeStamp+".png";
		File target = new File(fileName);
		file.renameTo(target);
		return fileName;
	}
	
	public static void performLogin()  {
		Properties prop = null;
		try {
			FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
			prop = new Properties();
			prop.load(file);
		}
		catch(IOException e) {}
		LoginPage lp = new LoginPage(getDriver());
		lp.setEmail(prop.getProperty("email"));
		lp.setPassword(prop.getProperty("password"));
		lp.clickOnLogin();
	}
	
}
