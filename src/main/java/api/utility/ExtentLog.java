package api.utility;

//import com.aventstack.extentreports.Status;

public class ExtentLog {
	// Initialize Log4j logs
    /**
	static Logger extentLog = Logger.getLogger(ExtentLog.class);

	// This is to print log for the beginning of the test case, as we usually run so
	// many test cases as a test suite
	public static void startTestCase(Method result) {

		extentLog.info("**************************************************************************************");

		extentLog.info("$$$$$$$$$$$$$$$$$$ TestMethod Name: " + result.getName() + " $$$$$$$$$$$$$$$$$$");

		extentLog.info("**************************************************************************************");
		
		extentLog.info("Testcase Execution started");
	}

	// This is to print log for the ending of the test case
	public static void endTestCase() {
		
		extentLog.info("Testcase Execution completed");
		extentLog.info("***************************     " + "-E---N---D-" + "    ******************************");
	}
	
	public static void info(String message) {

		extentLog.info(message);
		if (ReportTestManager.getTest() != null)
			ReportTestManager.getTest().log(Status.INFO, message);
	}
	
	public static void pass(String message) {

		extentLog.info(message);
		if (ReportTestManager.getTest() != null)
			ReportTestManager.getTest().log(Status.PASS, message);
	}
	
	public static void fail(String message) {

		extentLog.info(message);
		if (ReportTestManager.getTest() != null)
			ReportTestManager.getTest().log(Status.FAIL, message);
	}

	public static void warn(String message) {

		extentLog.warn(message);
		if (ReportTestManager.getTest() != null)
			ReportTestManager.getTest().log(Status.WARNING, message);
	}

	public static void error(String message) {

		extentLog.error(message);
		if (ReportTestManager.getTest() != null)
			ReportTestManager.getTest().log(Status.ERROR, message);
	}

	public static void debug(String message) {

		extentLog.debug(message);
		if (ReportTestManager.getTest() != null)
			ReportTestManager.getTest().log(Status.DEBUG, message);
	}
	 **/
}
