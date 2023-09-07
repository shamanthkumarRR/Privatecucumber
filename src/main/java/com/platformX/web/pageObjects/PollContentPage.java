package com.platformX.web.pageObjects;

import com.platformX.web.core.ConfigFileReader;
import com.platformX.web.core.EnvParameters;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PollContentPage extends CommonPage {

    // Object Repositories
    @FindBy(xpath = "//div[contains(@class,'menulist')]//p[contains(text(),'Content')]")
    private WebElement contentMenu;
    @FindBy(xpath = "//h4[text()='Poll']")
    private WebElement pollPageHeading;
    @FindBy(xpath = "//h6[text()='Poll']/ancestor::li")
    private WebElement pollMenuItem;
    @FindBy(xpath = "//span[text()='Create Poll']")
    private WebElement createPollLabel;

    @FindBy(xpath = "//input[@id='title']")
    private WebElement pollTitleTextField;
    @FindBy(xpath = "//input[@id='short_title']")
    private WebElement pollShortTitleTextField;
    @FindBy(xpath = "//textarea[@id='short_description']")
    private WebElement pollShortDescTextField;
    @FindBy(xpath = "//textarea[@id='description']")
    private WebElement pollDescTextField;

    @FindBy(xpath = "//h5[contains(text(),'Choose your Image')]")
    private WebElement pollBackGroundColor;

    @FindBy(xpath = "//div[@id='imageVideo']//div[contains(@class,'1vk33dk')]")
    private WebElement pollBackgroundColor;

    @FindBy(xpath = "//input[@id='poll_title']")
    private WebElement pollQusTitleTextField;
    @FindBy(xpath = "//textarea[@id='poll_description']")
    private WebElement pollQusDescTextField;
    @FindBy(xpath = "//div[@id='questions']//div[contains(@class,'1kn0wyr')]")
    private WebElement pollQusBackgroundColor;
    @FindBy(xpath = "//input[@name='1']")
    private WebElement pollFirstAnswerTextField;
    @FindBy(xpath = "//input[@name='2']")
    private WebElement pollSecondAnswerTextField;
    @FindBy(xpath = "//span[contains(text(),'Add Answer')]")
    private WebElement addMoreAnsTextLink;
    @FindBy(xpath = "//input[@name='3']")
    private WebElement pollThirdAnswerTextField;

    //Published Poll Page Objects
    @FindBy(xpath = "//button[contains(text(),'Submit')]")
    private WebElement pollSubmitButton;
    @FindBy(xpath = "//button[contains(text(),'Back to Home')]")
    private WebElement backToHomeButton;

    //Reusable Variable Declaration
    private Logger log = Logger.getLogger(this.getClass().getName());
    private WebDriver driver;
    HashMap<String, String> pollDataSet = new HashMap<String, String>();
    ArrayList<String> answerList = new ArrayList<String>();
    public String pollTitle = "";
    public String pageParamUrl = "";
    public String pollDesc = "";
    public  String finalPollAns = "";

    /**
     * Constructor call to initialize driver scope withing the call with help of PageObjectManager
     * Sharing same drive object with parent class
     *
     * @param driver
     */
    public PollContentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickOnSideBarMenu(String commonSideBarMenu) {
        String sideBarMenuXpath = "//h6[text()='" + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, commonSideBarMenu) + "']/ancestor::li";
        WebElement sideBarMenu = findElement(By.xpath(sideBarMenuXpath));
        performPageAction("wait", contentMenu, "clickable", "");
        performPageAction("softAssertion", contentMenu, "equals", "CONTENT");
        performPageAction("wait", sideBarMenu, "clickable", "");
        performPageAction("softAssertion", sideBarMenu, "equals", ConfigFileReader.getProperty(EnvParameters.envPropFilePath, commonSideBarMenu));
        performPageAction("click", sideBarMenu, "", "commonSideBarMenu");
    }

    /**
     * Method to click on sidebar menu and verify redirected page
     *
     */
    public void verifyNavigationOnPollPage(String expectedPageTitle, String relativePageUrl, String expectedPageHeading) {
        String expectedPageUrl = envUrl + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, relativePageUrl);
        verifyPageRedirection(expectedPageTitle, expectedPageUrl);
        performPageAction("wait", pollPageHeading, "visible", "");
        performPageAction("softAssertion", pollPageHeading, "equals", ConfigFileReader.getProperty(EnvParameters.envPropFilePath, expectedPageHeading));
        log.info("Navigation on Poll page verified successfully");
    }

    public void clickOnAddNewButton(String buttonName) {
        clickOnAddNewButton();
    }



    /**
     * Method to create & save new poll
     */
    public void savePollContent() {
        pollTitle = "Automation Poll" + " " + getCurrentDateTimeStamp();
        editPageParamUrl = pollTitle.replace(" ", "-").toLowerCase();
        pollDesc = "Automation_Description";
        pollDataSet.put("Poll Title", pollTitle);
        pollDataSet.put("Poll Desc", pollDesc);
        //Verify Edit page

        //Enter Title
        performPageAction("wait", pollTitleTextField, "visible", "");
        performPageAction("sendKeys", pollTitleTextField, "", pollTitle);
        //Enter Short Title
        performPageAction("sendKeys", pollShortTitleTextField, "", "Automation_Short_Title");
        pollDataSet.put("Poll Short Title", "Automation_Short_Title");
        //Enter Short Description
        performPageAction("sendKeys", pollShortDescTextField, "", "Automation_ShortDescription");
        pollDataSet.put("Poll Short Desc", "Automation_ShortDescription");
        //Enter Description
        scrollToElementJavascript(pollDescTextField);
        performPageAction("sendKeys", pollDescTextField, "", pollDesc);

        //Select Background color for Poll
        //scrollToElementJavascript(pollBackgroundColor, true);
        //waitUntilElementClickable(pollBackgroundColor, 20);
        //pollBackgroundColor.click();

        //Upload Images
        scrollToElementJavascript(pollBackGroundColor);
        performPageAction("click", pollBackGroundColor, "", "pollBackGroundColor");
        String uploadedImageUrl = uploadDspaceImage();
        pollDataSet.put("Background Image", uploadedImageUrl);


        //Poll Qus
        waitForSeconds(10);
        scrollToElementJavascript(pollQusTitleTextField);
        performPageAction("wait", pollQusTitleTextField, "clickable", "");
        performPageAction("sendKeys", pollQusTitleTextField, "", "Who will be next PM?");
        pollDataSet.put("Poll Qus", "Who will be next PM?");
        performPageAction("sendKeys", pollQusDescTextField, "", "Only for non-bjp supporters?");
        pollDataSet.put("Poll Qus Desc", "Only for non-bjp supporters?");

        //Upload Images Or Select Background color for Poll Qus
        scrollToElementJavascript(pollQusBackgroundColor);
        performPageAction("wait", pollQusBackgroundColor, "clickable", "");
        performPageAction("click", pollQusBackgroundColor, "", "");

        //Poll Answers
        scrollToElementJavascript(pollFirstAnswerTextField);
        performPageAction("sendKeys", pollFirstAnswerTextField, "", "BJP");
        answerList.add("BJP");
        performPageAction("sendKeys", pollSecondAnswerTextField, "", "Non-BJP");
        answerList.add("Non-BJP");

        //State1-Save As Draft
        clickOnSaveAsDraftButton();

        //verify success message box with 2 buttons
        //verifySuccessDialog("Poll", "Save As Draft");
        clickOnBackArrow("Poll");
    }

    public void verifyCreatedPollContent(String pollStatus) {
        verifyCreatedContent(ConfigFileReader.getProperty(EnvParameters.envPropFilePath, pollStatus), pollTitle);
    }

    public void clickOnPollEditIcon() {
        clickOnEditIcon(pollTitle);
    }

    /**
     * Method to edit new poll, update and publish it
     *
     */
    public void editAndPublishPollContent() {
        //Selects Tag
        selectRequiredTag("Content Type", "Polls");
        pollDataSet.put("Content Type", "Polls");

        waitForSeconds(5);
        clickOnPublishButton();
        verifySuccessDialog("Poll", "Published");
    }

    public void verifyPublishedPollOnListingPage(String pollStatus) {
        //verifyCreatedContent("Draft", pollTitle);
        //waitForSeconds(10);
        //refresh();
        //waitForSeconds(1);
        //isAlertPresent();
        //acceptAlert();
        //waitForSeconds(10);
        verifyCreatedContent("Published", pollTitle);
        waitForSeconds(5);
    }

    /**
     * Method to verify published poll page
     *
     */
    public void verifyPublishedPollPage(String actionType) {
        clickOnMoreActions(ConfigFileReader.getProperty(EnvParameters.envPropFilePath, actionType));
        waitForSeconds(10);
        //switch to child window
        String parentWindowId = switchToChildWindow();
        waitForSeconds(10);
        //acceptCookies();

        //Verify navigated landing page of published poll page
        verifyLandingPublishedPage(pollDesc);
        //Verify all Qus-Ans and Submit poll
        submitPoll();
        //Verify poll submission and result count
        verifyPollSubmission(finalPollAns);
        switchToParentWindow(parentWindowId);
    }

    /**
     * Method to submit poll from published page
     *
     */
    public void submitPoll() {
        //Verify All qus & ans and participate in poll
        String publishedPollQusXpath = "//h3[contains(text(),'" + pollDataSet.get("Poll Qus") + "')]";
        By publishedPollQus = By.xpath(publishedPollQusXpath);
        String publishedPollQusDescXpath = "//h5[contains(text(),'" + pollDataSet.get("Poll Qus Desc") + "')]";
        By publishedPollQusDesc = By.xpath(publishedPollQusDescXpath);
        waitUntilElementPresent(publishedPollQus);
        //softAssert().assertEquals(driver.findElement(publishedPollQus).getText(), pollDataSet.get("Poll Qus"));
        //softAssert().assertEquals(driver.findElement(publishedPollQusDesc).getText(), pollDataSet.get("Poll Qus Desc"));

        //Verify all answers and selects last answer
        By publishedAnsList = By.xpath("//div[@role='radiogroup']//label//input");
        List<WebElement> expectedAnswers = driver.findElements(publishedAnsList);
        if (answerList.size() == expectedAnswers.size()) {
            for (int i = 0; i < expectedAnswers.size(); i++) {
                if (expectedAnswers.get(i).getAttribute("value").equals(answerList.get(i))) {
                    finalPollAns = expectedAnswers.get(i).getAttribute("value");
                    clickJs(expectedAnswers.get(i));
                    log.info(finalPollAns + " answer verified and selected successfully");
                } else {
                    //softAssert().fail("Actual answers not matched with expected answers!!!");
                    log.error("Actual Answer : " + answerList.get(i));
                    log.error("Expected Answer : " + expectedAnswers.get(i).getAttribute("value"));
                }
            }
        } else {
            //softAssert().fail("Actual answers not matched with expected answer list!!!");
            log.error("Actual Total Answers : " + answerList.size());
            log.error("Expected Total Answers : " + expectedAnswers.size());
        }
        //softAssert().assertEquals(pollSubmitButton.getText(), "Submit");
        pollSubmitButton.click();
        log.info("Poll submitted successfully");
    }

    /**
     * Method to verify submitted poll
     *
     */
    public void verifyPollSubmission(String finalAns) {
        String submittedPollTitleXpath = "//h1[contains(text(),'" + pollTitle + "')]";
        By submittedPollTitle = By.xpath(submittedPollTitleXpath);
        String pollSubmissionResponseXpath = "//h1[contains(text(),'" + pollTitle + "')]//following-sibling::h4";
        By pollSubmissionResponse = By.xpath(pollSubmissionResponseXpath);

        String pollResultXpath = "//h1[contains(text(),'" + pollTitle + "')]//parent::div//following-sibling::div//p[contains(@class,'h5')]";
        By pollResult = By.xpath(pollResultXpath);
        String pollResultCountXpath = "//h1[contains(text(),'" + pollTitle + "')]//parent::div//following-sibling::div//p[contains(@class,'h6')]";
        By pollResultCount = By.xpath(pollResultCountXpath);

        waitForSeconds(5);
        waitUntilElementPresent(submittedPollTitle);
        //softAssert().assertEquals(driver.findElement(submittedPollTitle).getText(), pollTitle);
        //softAssert().assertEquals(driver.findElement(pollSubmissionResponse).getText(), "Thanks for your response!");

        List<WebElement> pollResults = driver.findElements(pollResult);
        List<WebElement> pollResultCounts = driver.findElements(pollResultCount);
        if (answerList.size() == pollResults.size() && answerList.size() == pollResultCounts.size()) {
            //Verify selected ans whether showing on top place with count-1
            //Used 0 as default index to validate with 1st result
            //User 1 as count result 1
            if (finalAns.equals(pollResults.get(0).getText()) && pollResultCounts.get(0).getText().equals("1")) {
                log.info("Poll submission verified successfully");
            } else {
                //softAssert().fail("Poll verification failed!!!");
                log.error("Final poll ans " + finalAns + " not matched with " + pollResults.get(0).getText() + " result count : " + pollResultCounts.get(0).getText());
            }
            //Need to verify un-selected ans
        } else {
            //softAssert().fail("Actual answers not matched with expected answer list!!!");
            log.error("Actual Total Answers : " + answerList.size());
            log.error("Expected Total Answers : " + pollResults.size());
        }
        log.info("Verified submitted poll");
        waitForSeconds(5);
        waitUntilElementClickable(backToHomeButton);
        //softAssert().assertEquals(backToHomeButton.getText(), "Back to Home");
        clickJs(backToHomeButton);
        log.info("Back to Home button clicked and page navigated to homepage of published poll page");
        verifyLandingPublishedPage(pollDesc);
    }

    /**
     * Method to perform search for existing poll and edit & publish it again
     *
     */
    public void  editAndPublishExistingPoll() {
        String expectedTitle = editSearchedContent();
        //Verify redirected edit page
        //softAssert().assertEquals(pollTitleTextField.getAttribute("value"), expectedTitle);
        pollTitle = "Automation Poll" + " " + getCurrentDateTimeStamp();
        pollTitleTextField.clear();
        pollTitleTextField.sendKeys(pollTitle);

        //Need to update later, will make it to call common methods
        waitForSeconds(5);
        clickOnPublishButton();
        verifySuccessDialog("Poll", "Published");

        //verifyRedirectedPage(actualPageTitle, actualPageUrl);

        //verify created quiz on listing page
        verifyCreatedContent("Draft", expectedTitle);
        waitForSeconds(10);
        refresh();
        waitForSeconds(1);
        isAlertPresent();
        acceptAlert();
        waitForSeconds(10);
        verifyCreatedContent("Published", expectedTitle);
        waitForSeconds(5);
    }
}
