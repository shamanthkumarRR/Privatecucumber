package api.utility;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import api.testBase.WebBaseClass;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import org.testng.asserts.SoftAssert;

public class UtilityWeb extends WebBaseClass {

	public static Logger log = Logger.getLogger(UtilityWeb.class);
	private SoftAssert sa = null;

	/**
	 * Method to refresh the page
	 */
	public static void refresh() {
		driver.navigate().refresh();
		log.info("Refreshed the page");
	}

	/**
	 * Method to navigate to the previous page
	 */
	public static void navigateBack() {
		driver.navigate().back();
		log.info("Navigate to the previous page");
	}

	/**
	 * Method to navigate to the next page
	 */
	public static void navigateForward() {
		driver.navigate().forward();
		log.info("Navigate to the next page");
	}

	/**
	 * Method to get the current URL of the page
	 * 
	 * @return
	 */
	public static String getCurrentURL() {
		log.info("Getting the current url of the page");
		return driver.getCurrentUrl();
	}

	/**
	 * Method to get the current window title of the page
	 * 
	 * @return
	 */
	public static String getWindowTitle() {
		log.info("Getting the title of the page");
		return driver.getTitle();
	}

	/**
	 * Returns the text from the alert window
	 * 
	 * @return
	 */
	public static String getAlertText() {
		log.info("Getting the Alert text");
		return driver.switchTo().alert().getText();
	}

	/**
	 * Accepts the Alert operation
	 * 
	 */
	public static void acceptAlert() {
		log.info("Accepts the Alert window");
		driver.switchTo().alert().accept();
	}

	/**
	 * Dismiss the Alert operation
	 */
	public static void dismissAlert() {
		log.info("Dismiss the Alert window");
		driver.switchTo().alert().dismiss();
	}

	/**
	 * Performs the javascript click on the given element
	 * 
	 * @param element is the WebElement Reference
	 */
	public static void clickJs(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", element);
		log.info("Click on the element " + element + "using JavaScript");
	}

	/**
	 * Closes the given pop up window without switching to any other window
	 * 
	 * @param windowId the window id that should be closed
	 */
	public static void closePopupWindow(String windowId) {
		driver.switchTo().window(windowId).close();
	}

	public static String takeScreenshot(String SubFolder, String MethodName) throws IOException {
		String failedSnapPath;
		File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		failedSnapPath = System.getProperty("user.dir") + "/ExtentReport/" + SubFolder + "/" + MethodName + getDate()
				+ ".png";
		File destFile = new File(failedSnapPath);
		FileUtils.copyFile(scrfile, destFile);
		return failedSnapPath;
	}

	public static String getDate() {
		SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		String date = SDF.format(new Date());
		return date;
	}

	public static String getCurrentDateTimeStamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return sdf.format(timestamp);
	}
	
	/**
	 * Method to wait until element is Present
	 * @param element
	 */
	public static void waitUntilElementPresent(By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
		log.info("Waiting until presence of element located");
	}

	/**
	 * Method to wait until element is visible
	 * @param element
	 */
	public static void waitUntilElementVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Waiting until visiblity of element located");
	}

	/**
	 * Method to wait until element is clickable
	 *
	 * @param element
	 */
	public static void waitUntilElementClickable(WebElement element) {
		log.info("Waiting until element to be clickable");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Method to check element is either invisible or not present on the DOM.
	 *
	 * @param locator
	 */
	public static void waitUntilElementToBeInvisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		log.info("Waiting until element to be Invisible");
	}

	public static int VerifyImage(String Imagename) {
		int flag = 0;
		Screen screen = new Screen();
		try {
			Pattern objPat = new Pattern(Imagename);
			screen.wait(objPat, 180);
			if (screen.exists(objPat) != null) {
				flag = 1;
				//ExtentLog.pass("Logo is displayed and visible");
			}
		} catch (FindFailed e) {

			//ExtentLog.fail("Logo not displayed");
		}
		return flag;
	}

	/**
	 * Performs scroll into view of the element using javascript
	 * 
	 * @param element is the WebElement Reference
	 */
	public static void scrollToElementJavascript(WebElement element, boolean scrollTop) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(arguments[1]);", element, scrollTop);
		log.info("Scrolling to the element " + element + "using JavaScript");
	}

	public static void ScrollWindow(int a) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + a + ")");
	}

	/**
	 * Method to make the driver to wait for a time interval before proceeding
	 * further
	 * 
	 * @param timeOutInSeconds
	 */
	public static void Sleep(int timeOutInSeconds) {
		try {
			Thread.sleep(timeOutInSeconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method to wait for given seconds before performing next action
	 * @param secondCount
	 */
	public void waitForSeconds(int secondCount) {
		try {
			TimeUnit.SECONDS.sleep(secondCount);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Method for switching to the frame of the given frame id
	 * 
	 * @param frameId
	 */
	public static void switchToFrame(int frameId) {
		driver.switchTo().frame(frameId);
		log.info("Switching to the frame: " + frameId);
	}

	/**
	 * Retrieves the number of windows that is currently opened.
	 * 
	 * @return the count of browser windows
	 */
	public static int getNumberOfOpenWindows() {
		return driver.getWindowHandles().size();
	}

	/**
	 * Method to switch to a parent window
	 * 
	 * @param parentWindowId the id of the parent window
	 */
	public static void switchToParentWindow(String parentWindowId) {
		driver.switchTo().window(parentWindowId);
		log.info("driver scope switched back to parent window");
	}

	public static String switchToChildWindow() {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		String parentWindowId = itr.next();
		//switch to child window
		driver.switchTo().window(itr.next());
		log.info("driver scope switched to child window");
		return parentWindowId;
	}

	public static String GetWindowHandles() {
		Set handles = driver.getWindowHandles();
		Iterator t = handles.iterator();
		String ParentWindow = (String) t.next();
		String ChildWindow = (String) t.next();
		driver.switchTo().window(ChildWindow);
		System.out.println("Parent window title " + driver.getCurrentUrl());
		String actual = driver.getCurrentUrl();
		// close the child browser window
		driver.close();
		// switching parent window
		driver.switchTo().window(ParentWindow);
		System.out.println("Child window title " + driver.getCurrentUrl());

		return actual;
	}

	/**
	 * Switches to window having specified URL part
	 * 
	 * @param URLPart the part of URL to match
	 * @return True if switch is performed. False if no window with the expected url
	 *         part is found
	 */
	public static boolean switchToWindowWithURLPart(String URLPart) {
		for (String window : driver.getWindowHandles()) {
			driver.switchTo().window(window);
			if (driver.getCurrentUrl().equals(URLPart))
				return true;
		}
		return false;
	}

	/**
	 * Switches to window having specified title part
	 * 
	 * @param titleOfNewWindow specifies the title of new window
	 */
	public static void switchToWindowByTitle(String titleOfNewWindow) {
		for (String window : driver.getWindowHandles()) {
			driver.switchTo().window(window);
			if (driver.getTitle().contains(titleOfNewWindow)) {
				break;
			}
		}
	}

	/**
	 * Switches to window having specified element
	 * 
	 * @param element locator for the element
	 * @return True if switch is performed. False if no window with the expected
	 *         element is found
	 */
	public static boolean switchToWindowWithElement(WebElement element) {
		for (String window : driver.getWindowHandles()) {
			driver.switchTo().window(window);
			if (isElementPresent(element))
				return true;
		}
		return false;
	}

	/**
	 * Method to switch to a window by its index
	 * 
	 * @param index - Window's index
	 */
	public static void switchToWindowByIndex(int index) {
		driver.switchTo().window(driver.getWindowHandles().toArray()[index].toString());
	}

	/**
	 * Return focus to the main browser window by using defaultcontent method
	 * 
	 */
	public static void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	/**
	 * Method to check Alert is present or not
	 * @return True if Alert is present on the page otherwise false
	 */
	public static boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	/**
	 * Method to perform hovering on the element
	 * 
	 * @param element
	 */
	public static void hoverOver(WebElement element) {
		Actions action = new Actions(WebBaseClass.driver);
		action.moveToElement(element).build().perform();
		log.info("Hovering over the mouse on the element " + element);
	}

	public static void keyBoardAction() {
		Actions action = new Actions(WebBaseClass.driver);
		action.keyDown(Keys.ENTER);
		log.info("Enter button selected successfully");
	}

	/**
	 * Method to perform drag and drop action
	 * 
	 * @param fromElement
	 * @param toElement
	 */
	public static void dragAndDrop(WebElement fromElement, WebElement toElement) {
		log.info("Performing the drag and drop at specified location");
		Actions action = new Actions(driver);
		action.dragAndDrop(fromElement, toElement).build().perform();
	}

	/**
	 * Method to perform the drag and drop by using pixel offset
	 * 
	 * @param sourceElement
	 * @param xOffset
	 * @param yOffset
	 */
	public static void dragAndDropByPixel(WebElement sourceElement, int xOffset, int yOffset) {
		log.info("Performing the drag and drop at specified location using pixel");
		Actions action = new Actions(driver);
		action.dragAndDropBy(sourceElement, xOffset, yOffset).build().perform();
	}

	/**
	 * Method to determines if a specific element is present
	 * 
	 * @param element
	 * @return true/false if element is found/not found
	 */
	public static boolean isElementPresent(WebElement element) {
		log.info("Checking the presence of the element: " + element);
		try {
			if (element.isEnabled()) {
				log.info("Element is present: " + element);
				return true;
			}
			log.warn("Element is NOT present: " + element);
			return false;
		} catch (NoSuchElementException e) {
			log.warn("Element is NOT present: " + element);
			return false;
		} catch (Exception e) {
			log.warn("Element is NOT present: " + element);
			return false;
		}
	}

	/**
	 * Method to determines if a specific element is visible
	 * 
	 * @param element
	 * @return true/false if element is visible/not visible
	 */
	public static boolean isElementVisible(WebElement element) {
		log.info("Checking the visibility of the element: " + element);
		try {
			if (element.isDisplayed()) {
				log.info("Element is present: " + element);
				return true;
			}
			log.warn("Element is NOT present: " + element);
			return false;
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Method to determines if a specific element is Checked
	 * 
	 * @param element
	 * @return true/false if element is checked/not checked
	 */
	public static boolean isElementChecked(WebElement element) {
		log.info("Checking the element is checked: " + element);
		try {
			if (element.isSelected()) {
				log.info("Element is checked: " + element);
				return true;
			}
			log.warn("Element is NOT checked: " + element);
			return false;
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Method is used to set the value based on index value provided
	 * 
	 * @param element
	 * @param index   index value of the option to select
	 */
	public static void selectValueFromDropDown(WebElement element, int index) {
		log.info("Selecting the " + index + "value from dropdown");
		Select dropDownValue = new Select(element);
		dropDownValue.selectByIndex(index);
	}

	/**
	 * Method is used to set the value based on value attribute provided
	 * 
	 * @param element
	 * @param value   the option to select
	 */
	public static void selectValueFromDropDown(WebElement element, String value) {
		log.info("Selecting the " + value + "value from dropdown");
		Select dropDownValue = new Select(element);
		dropDownValue.selectByValue(value);
	}

	/**
	 * Method is used to set the value based on visible text provided
	 * 
	 * @param element
	 * @param visibleText the option to select
	 */
	public static void selectVisibleTextFromDropDown(WebElement element, String visibleText) {
		log.info("Selecting the " + visibleText + "value from dropdown");
		Select dropDownValue = new Select(element);
		dropDownValue.selectByVisibleText(visibleText);
	}

	/**
	 * Method is used to get selected option in a dropdown
	 * 
	 * @param element
	 * @return
	 */
	public static String getFirstValueFromDropDown(WebElement element) {
		log.info("Getting the selected option value from dropdown");
		Select dropDownValue = new Select(element);
		return dropDownValue.getFirstSelectedOption().getText();
	}

	/**
	 * Method to check/uncheck the checkbox irrespective of its current state
	 * 
	 * @param element
	 * @param check   true to check the checkbox, false to uncheck it
	 * @return
	 */
	public static void setCheckBox(WebElement element, boolean check) {
		if (check) {
			if (!element.isSelected()) {
				element.click();
				log.info("Checked the checkbox");
			}
		} else {
			if (element.isSelected()) {
				element.click();
				log.info("Unchecked the checkbox");
			}
		}
	}

	/**
	 * Soft Assertion
	 */
	public SoftAssert softAssert() {
		if (sa == null) {
			sa = new SoftAssert();
		}
		return sa;
	}

}
