package api.utility;

//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.markuputils.ExtentColor;
//import com.aventstack.extentreports.markuputils.MarkupHelper;


//public class Listeners extends WebBaseClass implements ITestListener {

	/**
	@Override
	public void onTestStart(ITestResult result) {
		// Start operation for extent reports.
		ReportTestManager.startTest(result.getMethod().getMethodName(), result.getInstance().getClass().getSimpleName(),
				result.getMethod().getDescription());
		ReportTestManager.addPriority(result.getMethod().getPriority());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ReportTestManager.getTest().log(Status.PASS,
				MarkupHelper.createLabel(result.getName() + ": Testcase Passed", ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ReportTestManager.getTest().log(Status.FAIL,
				MarkupHelper.createLabel(result.getName() + ": Testcase Failed due to below issues:", ExtentColor.RED));
		ReportTestManager.getTest().fail(result.getThrowable());
		try {
			if (driver != null) {
				// Take base64Screenshot
				String base64Screenshot = "data:image/png;base64,"
						+ ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				ReportTestManager.getTest().fail("Snapshot: ",
						MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String screenShotPath = Utility.takeScreenshot("FailureScreenshot", result.getName());
//		ReportTestManager.getTest().fail("Snapshot below: "
//				+ ReportTestManager.getTest().addScreenCaptureFromPath(screenShotPath));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ReportTestManager.getTest().log(Status.SKIP,
				MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
		ReportTestManager.getTest().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		WebBaseClass.getInstance().flush();

	}
	**/

//}
