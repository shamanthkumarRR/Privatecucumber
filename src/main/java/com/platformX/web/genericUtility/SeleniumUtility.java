package com.platformX.web.genericUtility;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.platformX.web.core.TestContext.softAssert;


public class SeleniumUtility {

    protected Logger log = Logger.getLogger(this.getClass().getName());
    protected WebDriver driver;
    protected WebDriverWait wait;

    public SeleniumUtility(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Method to navigate to given url
     * @param url
     */
    public void goToUrl(String url) {
        try {
            driver.get(url);
            log.info("Navigated to : " + url);
        } catch (NullPointerException exp){
            log.error("NullPointerException : Either driver has no object or null value can't be passed!!!");
            exp.printStackTrace();
        }
    }

    /**
     * Method to refresh the page
     */
    public void refresh() {
        try {
            driver.navigate().refresh();
            log.info("Refreshed the page");
        } catch (NullPointerException exp){
            log.error("NullPointerException : Driver has no object/Driver scope is not defined correctly!!!");
            exp.printStackTrace();
        }
    }

    /**
     * Method to navigate to the previous page
     */
    public void navigateBack() {
        try {
            driver.navigate().back();
            log.info("Navigated back to the previous page");
        } catch (NullPointerException exp){
            log.error("NullPointerException : Driver has no object/Driver scope is not defined correctly!!!");
            exp.printStackTrace();
        }
    }

    /**
     * Method to navigate to the next page
     */
    public void navigateForward() {
        try {
            driver.navigate().forward();
            log.info("Navigated to the next page");
        } catch (NullPointerException exp){
            log.error("NullPointerException : Driver has no object/Driver scope is not defined correctly!!!");
            exp.printStackTrace();
        }
    }

    /**
     * Method to get the current URL of the page
     *
     * @return
     */
    public String getCurrentUrl() {
        String actualPageUrl = "";
        try {
            log.info("Getting the current url of the page");
            actualPageUrl = driver.getCurrentUrl();
        } catch (NullPointerException exp){
            log.error("NullPointerException : Driver has no object/Driver scope is not defined correctly!!!");
            exp.printStackTrace();
        }
        return actualPageUrl;
    }

    /**
     * Method to get the current window title of the page
     */
    public String getPageTitle() {
        String actualTitle = "";
        try {
            log.info("Getting the title of the page");
            actualTitle = driver.getTitle();
        } catch (NullPointerException exp){
            log.error("NullPointerException : Driver has no object/Driver scope not defined correctly!!!");
            exp.printStackTrace();
        }
        return actualTitle;
    }

    /**
     * Method to return text of given element
     * @param element
     * @return
     */
    public String getElementText(WebElement element) {
        String actualText = "";
        try {
            actualText =  element.getText();
        } catch (org.openqa.selenium.NoSuchElementException exp) {
            log.error("NoSuchElementException : There is no such element in DOM!!!");
            exp.printStackTrace();
        }
        return actualText;
    }

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    /**
     * Returns the text from the alert window
     *
     * @return
     */
    public String getAlertText() {
        String alertText = "";
        try {
            log.info("Getting the Alert Text");
            alertText =  driver.switchTo().alert().getText();
        } catch (NoAlertPresentException exp) {
            log.error("NoAlertPresentException : There is no such alert present in current DOM!!!");
            exp.printStackTrace();
        }
        return alertText;
    }

    /**
     * Accepts the Alert operation
     */
    public void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
            log.info("Accepts the Alert window");
        } catch (NoAlertPresentException exp) {
            log.error("NoAlertPresentException : There is no such alert present in current DOM!!!");
            exp.printStackTrace();
        }
    }

    /**
     * Dismiss the Alert operation
     */
    public void dismissAlert() {
        try {
            driver.switchTo().alert().dismiss();
            log.info("Dismiss the Alert window");
        } catch (NoAlertPresentException exp) {
            log.error("NoAlertPresentException : There is no such alert present in current DOM!!!");
            exp.printStackTrace();
        }
    }

    /**
     * Performs the javascript click on the given element
     *
     * @param element is the WebElement Reference
     */
    public void clickJs(WebElement element) {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].click();", element);
            log.info("Clicked on the element " + element + " using JavaScript");
        } catch (JavascriptException exp) {
            log.error("JavascriptException!!!");
            exp.printStackTrace();
        } catch (NullPointerException exp) {
            log.error("NullPointerException : Either driver has no object or null value can't be passed!!!");
            exp.printStackTrace();
        }
    }

    /**
     * Performs scroll into view of the element using javascript
     * @param element is the WebElement Reference
     */
    public void scrollToElementJavascript(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(arguments[1]);", element, true);
        log.info("Scrolling to the element " + element + "using JavaScript");
    }

    /**
     * Closes the given pop up window without switching to any other window
     *
     * @param windowId the window id that should be closed
     */
    public void closePopupWindow(String windowId) {
        try {
            driver.switchTo().window(windowId).close();
            log.info("Closed popup window");
        } catch (NoSuchWindowException exp) {
            log.error("NoSuchWindowException!!!");
            exp.printStackTrace();
        }
    }

    //Need to remove it
    public String takeScreenshot(String SubFolder, String MethodName) throws IOException {
        String failedSnapPath;
        File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        failedSnapPath = System.getProperty("user.dir") + "/ExtentReport/" + SubFolder + "/" + MethodName + getDate()
                + ".png";
        File destFile = new File(failedSnapPath);
        FileUtils.copyFile(scrfile, destFile);
        return failedSnapPath;
    }

    public String getDate() {
        SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String date = SDF.format(new Date());
        return date;
    }

    public String getCurrentDateTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return sdf.format(timestamp);
    }

    public WebDriverWait getExplicitWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * Method to wait until element is Present
     * @param element
     */
    public void waitUntilElementPresent(By element) {
        log.info("Waiting until presence of element located : " + element);
        wait = getExplicitWait();
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(element));
            log.info("Element located successfully");
        } catch (TimeoutException exp) {
            log.error("Timeout exception : Element not located within given wait!!!");
            exp.printStackTrace();
        }
    }

    /**
     * Method to wait until element is visible
     * @param element
     */
    public void waitUntilElementVisible(WebElement element) {


    }

    /**
     * Method to wait until element is clickable
     * @param element
     */
    public void waitUntilElementClickable(WebElement element) {

    }

    /**
     * Method to check element is either invisible or not present on the DOM.
     * @param locator
     */
    public void waitUntilElementToBeInvisible(By locator) {
        log.info("Waiting until element to be Invisible : " + locator);
        wait = getExplicitWait();
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            log.info("Element invisible now");
        } catch (TimeoutException exp) {
            log.error("Timeout exception : Element is still visible!!!");
            exp.printStackTrace();
        }
    }


    public void ScrollWindow(int a) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + a + ")");
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
        log.info("Wait, Next action will be perform after " + secondCount + " seconds");
    }

    /**
     * Method for switching to the frame of the given frame id
     *
     * @param frameId
     */
    public void switchToFrame(int frameId) {
        driver.switchTo().frame(frameId);
        log.info("Switching to the frame: " + frameId);
    }

    /**
     * Method to switch to a parent window
     *
     * @param parentWindowId the id of the parent window
     */
    public void switchToParentWindow(String parentWindowId) {
        driver.close();
        driver.switchTo().window(parentWindowId);
        log.info("driver scope switched to parent window : " + parentWindowId);
    }

    public String switchToChildWindow() {
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> itr = windows.iterator();
        String parentWindowId = itr.next();
        //switch to child window
        driver.switchTo().window(itr.next());
        log.info("driver scope switched to child window");
        return parentWindowId;
    }

    public String GetWindowHandles() {
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
    public boolean switchToWindowWithURLPart(String URLPart) {
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
    public void switchToWindowByTitle(String titleOfNewWindow) {
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
    public boolean switchToWindowWithElement(WebElement element) {
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
    public void switchToWindowByIndex(int index) {
        driver.switchTo().window(driver.getWindowHandles().toArray()[index].toString());
        log.info("driver switched to " + index + "th window");
    }

    /**
     * Return focus to the main browser window by using defaultcontent method
     *
     */
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    /**
     * Method to check Alert is present or not
     * @return True if Alert is present on the page otherwise false
     */
    public boolean isAlertPresent() {
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
    public void mouseHover(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        log.info("Hovering over the mouse on the element " + element);
    }

    public void keyBoardAction() {
        Actions action = new Actions(driver);
        action.keyDown(Keys.ENTER);
        log.info("Enter button pressed successfully");
    }

    /**
     * Method to perform drag and drop action
     *
     * @param fromElement
     * @param toElement
     */
    public void dragAndDrop(WebElement fromElement, WebElement toElement) {
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
    public void dragAndDropByPixel(WebElement sourceElement, int xOffset, int yOffset) {
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
    public boolean isElementPresent(WebElement element) {
        log.info("Checking the presence of the element: " + element);
        try {
            if (element.isEnabled()) {
                log.info("Element is present: " + element);
                return true;
            }
            log.warn("Element is NOT present: " + element);
            return false;
        } catch (java.util.NoSuchElementException e) {
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
    public boolean isElementVisible(WebElement element) {
        log.info("Checking the visibility of the element: " + element);
        try {
            if (element.isDisplayed()) {
                log.info("Element is present: " + element);
                return true;
            }
            log.warn("Element is NOT present: " + element);
            return false;
        } catch (java.util.NoSuchElementException e) {
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
    public boolean isElementChecked(WebElement element) {
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
    public void selectValueFromDropDown(WebElement element, int index) {
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
    public void selectValueFromDropDown(WebElement element, String value) {
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
    public void selectVisibleTextFromDropDown(WebElement element, String visibleText) {
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
    public String getFirstValueFromDropDown(WebElement element) {
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
    public void setCheckBox(WebElement element, boolean check) {
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

    public void performPageAction(String actionType, WebElement element, String subAction, String value) {
        switch (actionType) {
            case "click":
                try {
                    element.click();
                    log.info(value + " clicked successfully");
                } catch (org.openqa.selenium.NoSuchElementException exp) {
                    log.error("NoSuchElementException : There is no such element in DOM!!!");
                    exp.printStackTrace();
                } catch (ElementNotInteractableException exp) {
                    log.debug("ElementClickInterceptedException : Hence, performing click action using JavaScriptExecutor");
                    clickJs(element);
                }
                break;
            case "sendKeys":
                try {
                    element.sendKeys(value);
                    log.info(value + " entered successfully");
                } catch (org.openqa.selenium.NoSuchElementException exp) {
                    log.error("NoSuchElementException : There is no such element in DOM!!!");
                    exp.printStackTrace();
                } catch (NullPointerException exp) {
                    log.error("NullPointerException : Either driver has no object or null value can't be passed!!!");
                    exp.printStackTrace();
                }
                break;
            case "wait":
                switch (subAction) {
                    case "clickable":
                        log.info("Waiting until element to be clickable : " + element);
                        wait = getExplicitWait();
                        try {
                            wait.until(ExpectedConditions.elementToBeClickable(element));
                            log.info("Element located successfully");
                        } catch (TimeoutException exp) {
                            log.error("Timeout exception : Element not located within given wait!!!");
                            exp.printStackTrace();
                        }
                        break;
                    case "visible":
                        log.info("Waiting until visibility of element located : " + element);
                        wait = getExplicitWait();
                        try {
                            wait.until(ExpectedConditions.visibilityOf(element));
                            log.info("Element located successfully");
                        } catch (TimeoutException exp) {
                            log.error("Timeout exception : Element not located within given wait!!!");
                            exp.printStackTrace();
                        }
                        break;
                    case "default":
                        log.error("Invalid Wait Type");
                        break;
                }
            case "softAssertion":
                switch (subAction) {
                    case "equals":
                        softAssert().assertEquals(getElementText(element), value);
                        break;
                    case "notEquals":
                        softAssert().assertNotEquals(getElementText(element), value);
                        break;
                    case "fail":
                        softAssert().fail(value);
                        break;
                    case "assertTrue":
                        softAssert().assertTrue(isElementVisible(element));
                        break;
                    case "default":
                        log.error("Invalid Assertion Method!!!");
                        break;
                }
            case "default":
                log.error("INVALID ACTION TYPE!");
                break;
        }

    }
}
