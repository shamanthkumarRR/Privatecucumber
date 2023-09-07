package api.testBase;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import api.utility.EnvParameters;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.yaml.snakeyaml.Yaml;

//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class WebBaseClass {


	public static Capabilities cap;
	public static ChromeOptions chromeOptions;
	public static EdgeOptions edgeOptions;
	//public static ExtentHtmlReporter reporter;
	//public static ExtentReports report;
	public static FirefoxOptions firefoxOptions;
	public static List<String> listuser;
	public static Map<String, Object> YamlMap;
	public static String browserName;
	public static String browserVersion;
	public static String log4jConfPath = "log4j.properties";
	public static WebDriver driver;
	public static Yaml yaml = new Yaml();

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws IOException {
		EnvParameters.loadData();
		PropertyConfigurator.configure(log4jConfPath);
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method result) throws IOException {
		init();
		//ExtentLog.startTestCase(result);
	}

	public void init() throws IOException {
		selectBrowser(EnvParameters.getData("browser"));
		selectUrl(EnvParameters.getData("url"));
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void selectBrowser(String property) {
		if (EnvParameters.getData("browser").equalsIgnoreCase("chrome")) {
			//WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", "C:\\Users\\vinaykumar-gupta\\OneDrive - HCL Technologies Ltd\\Documents\\Workspace\\PatformX_Repos\\WebAutomationFramework\\hep-authoring-ui\\PlatformX\\lib\\browserdrivers\\chromedriver.exe");
			chromeOptions = new ChromeOptions();
			if (EnvParameters.getData("execution").equalsIgnoreCase("pipeline")) {
				//chromeOptions.setHeadless(true);
				// You should set window size for better resolution and screen capture
				chromeOptions.addArguments("window-size=1200x600");
			}
			driver = new ChromeDriver();
			//cap = ((RemoteWebDriver) driver).getCapabilities();
			//browserName = cap.getBrowserName();
			//browserVersion = cap.getCapability("browserVersion").toString();
		} else if (EnvParameters.getData("browser").equalsIgnoreCase("edge")) {
			//WebDriverManager.edgedriver().setup();
			edgeOptions = new EdgeOptions();
			if (EnvParameters.getData("execution").equalsIgnoreCase("pipeline")) {
//				edgeOptions.setHeadless(true);
//				// You should set window size for better resolution and screen capture
//				edgeOptions.addArguments("window-size=1200x600");
			}
			driver = new EdgeDriver(edgeOptions);
			cap = ((RemoteWebDriver) driver).getCapabilities();
			browserName = cap.getBrowserName();
			browserVersion = cap.getCapability("browserVersion").toString();
		} else if (EnvParameters.getData("browser").equalsIgnoreCase("firefox")) {
			//WebDriverManager.firefoxdriver().setup();
			firefoxOptions = new FirefoxOptions();
			if (EnvParameters.getData("execution").equalsIgnoreCase("pipeline")) {
				firefoxOptions.setHeadless(true);
				// You should set window size for better resolution and screen capture
				firefoxOptions.addArguments("window-size=1200x600");
			}
			driver = new FirefoxDriver(firefoxOptions);
			cap = ((RemoteWebDriver) driver).getCapabilities();
			browserName = cap.getBrowserName();
			//browserVersion = cap.getVersion();
			browserName = cap.getBrowserVersion();
		}
	}

	public void selectUrl(String appUrl) {
		//driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws IOException {
//		String screenShotPath;
//		if (result.getStatus() == ITestResult.FAILURE) {
//			screenShotPath = Utility.takeScreenshot("FailureScreenshot", result.getName());
//			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + ": Testcase Failed due to below issues:", ExtentColor.RED));
//			test.fail(result.getThrowable());
//			test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));	
//		} else if(result.getStatus() == ITestResult.SUCCESS)
//        {
//			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + ": Testcase Passed", ExtentColor.GREEN));
//        } else if(result.getStatus() == ITestResult.SKIP)
//        {
//    		test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
//    		test.skip(result.getThrowable());
//        }
		//ExtentLog.endTestCase();
		driver.close();
	}
    /**
	public static ExtentReports getInstance() {
		if (report == null)
			createInstance();
		return report;
	}

	@SuppressWarnings("deprecation")
	private static ExtentReports createInstance() {
		if (report == null) {
			String reportFileNameFormat = EnvParameters.getData("Report_Path") + UtilityWeb.getDate() + ".html";
			reporter = new ExtentHtmlReporter(reportFileNameFormat);
			report = new ExtentReports();
			report.attachReporter(reporter);
			// To add environment info by using the setSystemInfo method.
			report.setSystemInfo("Execution Environment", EnvParameters.getData("Env"));
			report.setSystemInfo("Browser", browserName);
			report.setSystemInfo("Browser Version", browserVersion);
			report.setSystemInfo("User Name", System.getProperty("user.name"));
			report.setSystemInfo("OS", System.getProperty("os.name"));
		}
		return report;
	}
	 **/

	@AfterSuite(alwaysRun = true)
	public void afterSuite() throws IOException {

	}
}
