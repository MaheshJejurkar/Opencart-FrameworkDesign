package testBase;

import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class BaseClass {

	public Logger logger;
	public Properties properties;
	public WebDriver driver;

	@Parameters({ "OS", "Browser" })
	@BeforeClass(groups = {"Sanity", "Regression", "Master"})
	public void setup(String OS, String Browser) throws Exception {
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		properties = new Properties();
		properties.load(file);

		logger = LogManager.getLogger(this.getClass());

		if(properties.getProperty("execution_env").equalsIgnoreCase("Remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			switch(OS.toLowerCase()) {
				case "windows": capabilities.setPlatform(Platform.WIN11); break;
				case "mac": capabilities.setPlatform(Platform.MAC); break;
				case "linux": capabilities.setPlatform(Platform.LINUX); break;
				case "unix": capabilities.setPlatform(Platform.UNIX); break;
				default: System.out.println("No matching os."); return;
			}
			
			switch(Browser.toLowerCase()) {
				case "chrome": capabilities.setBrowserName("chrome"); break;
				case "firefox": capabilities.setBrowserName("firefox"); break;
				case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
				default: System.out.println("No matching browser."); return;
			}		
			driver = new RemoteWebDriver(new URL("http://192.168.45.251:4444"), capabilities);
			
		}
		
		if(properties.getProperty("execution_env").equalsIgnoreCase("Local")) {	
		switch (Browser.toLowerCase()) {
			case "chrome": driver = new ChromeDriver(); break;
			case "firefox": driver = new FirefoxDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			default: System.out.println("Invalid browser."); return;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@AfterClass(groups = {"Sanity", "Regression", "Master"})
	public void teardown() {
		driver.quit();
	}

	public String getRandomString() {
		return RandomStringUtils.randomAlphabetic(6);
	}

	public String getRandomNumber() {
		return RandomStringUtils.randomNumeric(10);
	}
	
	public static String getDateTime() {
		String datetime = new SimpleDateFormat("dd.MM.yyyy.hh.mm.ss.a").format(new Date());
		return datetime;
	}

	public String takeScreenshot(String testname) throws Exception {
		String datetime = getDateTime();
		String imageFilePath = System.getProperty("user.dir")+"\\screenshots\\"+testname+"_"+datetime+".jpg";
		
		TakesScreenshot takescreenshot = (TakesScreenshot)driver;
		File sourceFile = takescreenshot.getScreenshotAs(OutputType.FILE);
		File targetFile = new File(imageFilePath);
		FileUtils.copyFile(sourceFile, targetFile);
		return imageFilePath;
	}
	
}
