package api.testBase;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import api.utility.EnvParameters;

public class ApiBaseClass {
	
	public static String log4jConfPath = "log4j.properties";
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws IOException {
		EnvParameters.loadData();
		PropertyConfigurator.configure(log4jConfPath);
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method result) throws IOException {
		//ExtentLog.startTestCase(result);
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws IOException {
		//ExtentLog.endTestCase();
	}
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite() throws IOException {
		
	}
}
