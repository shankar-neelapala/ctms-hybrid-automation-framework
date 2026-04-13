package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import bases.BaseTest;

public class ScreenshotUtil {

	public static String captureScreenshot(String name) {
		TakesScreenshot ts = (TakesScreenshot) BaseTest.getDriver();
		File file = ts.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
		String fileName = System.getProperty("user.dir")+"\\screenshots\\"+name+timeStamp+".png";
		File target = new File(fileName);
		file.renameTo(target);
		return fileName;
	}
}
