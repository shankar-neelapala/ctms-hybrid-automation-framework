package bases;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

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
	@Parameters({"browser"})
	public void setUp(String browser) throws IOException {
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
		getDriver().get(prop.getProperty("aut"));
	}
	
	@AfterClass
	public void tearDown() {
		getDriver().quit();
	}
	
}
