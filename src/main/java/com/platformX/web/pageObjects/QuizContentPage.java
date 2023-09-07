package com.platformX.web.pageObjects;

import com.platformX.web.core.ConfigFileReader;
import com.platformX.web.core.EnvParameters;
import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class QuizContentPage extends CommonPage {

	// Object Repositories
	@FindBy(xpath = "//div[contains(@class,'menulist')]//span[contains(text(),'Content')]")
	private static WebElement contentMenu;
	@FindBy(xpath = "//span[text()='Quiz']/ancestor::li")
	private static WebElement quizMenuItem;

	@FindBy(xpath = "//h4[text()='Quiz']")
	private WebElement quizPageHeading;
	@FindBy(xpath = "//span[text()='Create Quiz']")
	private static WebElement createQuizLabel;

	@FindBy(xpath = "//input[@id='title']")
	private static WebElement quizTitleTextField;
	@FindBy(xpath = "//input[@id='short_title']")
	private static WebElement shortTitleTextField;
	@FindBy(xpath = "//textarea[@id='short_description']")
	private static WebElement shortDescriptionTextField;
	@FindBy(xpath = "//textarea[@id='description']")
	private static WebElement descriptionTextField;

	@FindBy(xpath = "//div[@id='imageVideo']//h5[contains(text(),'Choose your image')]")
	private static WebElement addQuizImageBackground;

	By dspaceImagePageHeader = By.xpath("//span[contains(text(),'Choose your image')]");
	@FindBy(xpath = "(//div[@id='scrollableDiv']//img)[1]")
	//@FindBy(xpath = "//div[@id='scrollableDiv']//img[contains(@src,'https://platx-dspace-dev.fanuep.com/server/api/core/bitstreams/63777cc1-2bc8-4ba3-87ed-5e4728dfed58/content')]")
	private static WebElement dspaceImageToBeSelected;
	@FindBy(xpath = "//button[contains(text(),'Done')]")
	private static WebElement dspaceImagePageDoneButton;

	@FindBy(xpath = "")
	private static WebElement uploadedImage;

	@FindBy(xpath = "")
	private static WebElement replaceImageIcon;

	@FindBy(xpath = "")
	private static WebElement autoCropImageIcon;

	@FindBy(xpath = "//button[contains(text(),'Choose from List')]")
	private static WebElement ChooseQusListButton;
	@FindBy(xpath = "//span[contains(text(),'Choose Your Question')]")
	private static WebElement chooseQusHeading;
	@FindBy(xpath = "//span[contains(text(),'Recently Added')]")
	private static WebElement recentlyAddedLabel;
	@FindBy(xpath = "")
	private static WebElement addNewQusButton;
	@FindBy(xpath = "")
	private static WebElement qusList;
	@FindBy(xpath = "//div[@id='questionListing']//button[contains(text(), 'select')]")
	private static WebElement selectQusButton;
	@FindBy(xpath = "//span[contains(text(),'Choose Your Question')]//following::div[2]//button[2]")
	private static WebElement qusAddButton;


	@FindBy(xpath = "")
	private static WebElement countRadioButton;
	@FindBy(xpath = "")
	private static WebElement percentageRadioButton;
	@FindBy(xpath = "")
	private static WebElement sportTag;
	@FindBy(xpath = "")
	private static WebElement contentTypeTag;
	@FindBy(xpath = "//span[contains(@class,'startIcon')]")
	private static WebElement quizSubmitButton;

	@FindBy(xpath = "//button[text()='Take Me Out']")
	private static WebElement takeMeOutBtn;
	@FindBy(xpath = "(//*[local-name()='svg' and @data-testid='CloseIcon'])[2]")
	private static WebElement unsavedChangesCloseIcon;
	@FindBy(xpath = "//h2[text()='Unsaved Changes']")
	private static WebElement unsavedChangesPopup;

	//Published Page
	@FindBy(xpath = "//span[@role='progressbar']")
	private static WebElement quizProgressBar;
	@FindBy(xpath = "//span[@role='progressbar']//parent::div//following-sibling::div//p")
	private static WebElement quizQusNumericProgressBar;
	@FindBy(xpath = "//span[@role='progressbar']//following-sibling::h1")
	private static WebElement quizAnsNumericProgressBar;
	@FindBy(xpath = "//button[contains(text(),'View Answers')]")
	private static WebElement viewQuizAnswerButton;
	@FindBy(xpath = "//button[contains(text(),'Back to Home')]")
	private static WebElement backToHomeButton;
	@FindBy(xpath = "//span[contains(@class,'endIcon')]")
	private static WebElement backArrowIcon;
	@FindBy(xpath = "//span[contains(@class,'endIcon')]//ancestor::div[1]//following-sibling::div//h3")
	private static WebElement quizResultQus;
	@FindBy(xpath = "//span[contains(@class,'endIcon')]//ancestor::div[1]//following-sibling::div//p")
	private static WebElement finalAnsField;
	@FindBy(xpath = "//span[contains(@class,'endIcon')]//ancestor::div[1]//following-sibling::div//p[contains(text(),'Correct Ans')]")
	private static WebElement correctAnsField;
	@FindBy(xpath = "//h1[contains(text(),'“A winner never stops trying”')]")
	private static WebElement wrongQuizResultMessage;

	// Reusable Variable Declaration
	private WebDriver driver;
	HashMap<String, String> quizDataSet = new HashMap<String, String>();
	ArrayList<String> answerList = new ArrayList<String>();
	public String quizTitle = "";
	public String quizDesc = "";
	public  String finalQuizAns = "";

	public QuizContentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	/**
	 * Method to click Add New button in Quiz listing page
	 * 
	 */
	public void clickAddNewInQuizListingPage() {
		//listPageObjects.clickOnAddNewButton();
		Awaitility.await().atMost(120, TimeUnit.SECONDS).with().pollInterval(5, TimeUnit.SECONDS)
		.until(() -> isElementVisible(createQuizLabel));
	}

	/**
	 * Method to validate unsaved changes popup appears on clicking Back button
	 * 
	 */
	public void verifyUnsavedChangesPopupOnClickingBackBtn() {	
		//waitUntilElementClickable(backArrow, 60);
		//backArrow.click();
		//Added to handle the Unsaved Changes popup
		if (isElementVisible(unsavedChangesPopup)) {
			//ExtentLog.info("Unsaved changes popup has appeared");
		} else {
			//ExtentLog.fail("Unsaved changes popup has not appeared");
		}
		waitUntilElementClickable(unsavedChangesCloseIcon);
		unsavedChangesCloseIcon.click();
	}
	
	/**
	 * Method to validate TakeMeOut action in unsaved changes popup on clicking Back
	 * button
	 * 
	 */
	public void verifyTakeMeOutInUnsavedChangesPopup() {
		//waitUntilElementClickable(backArrow, 60);
		//backArrow.click();
		// Added to handle the Unsaved Changes popup
		if (isElementVisible(unsavedChangesPopup)) {
			waitUntilElementClickable(takeMeOutBtn);
			takeMeOutBtn.click();
			//if (isElementVisible(addNewBtn)) {
			//	ExtentLog.info("Redirected to Quiz listing page");
			//} else {
			//	ExtentLog.fail("Failed to redirect to Quiz listing page");
			//}
		} else {
			//ExtentLog.fail("Unsaved changes popup has not appeared");
		}
	}

	/**
	 * Method to click on sidebar menu and verify redirected page
	 *
	 */
	public void verifyNavigationOnQuizPage(String expectedPageTitle, String relativePageUrl, String expectedPageHeading) {
		String expectedPageUrl = envUrl + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, relativePageUrl);
		verifyPageRedirection(expectedPageTitle, expectedPageUrl);
		performPageAction("wait", quizPageHeading, "clickable", "");
		performPageAction("softAssertion", quizPageHeading, "equals", ConfigFileReader.getProperty(EnvParameters.envPropFilePath, expectedPageHeading));
		log.info("Navigation on Quiz page verified successfully");
	}

	/**
	 * Method to create & save new Quiz Content page
	 *
	 */
	public void saveQuizContent(String qusType) {
		quizTitle = "Automation Quiz" + " " + getCurrentDateTimeStamp();
		editPageParamUrl = quizTitle.replace(" ", "-").toLowerCase();
		quizDesc = "Automation_Description";
		quizDataSet.put("Quiz Title", quizTitle);
		quizDataSet.put("Quiz Desc", quizDesc);

		//Enter Title
		waitUntilElementVisible(quizTitleTextField);
		quizTitleTextField.sendKeys(quizTitle);

		//Enter Short Title
		shortTitleTextField.sendKeys("Automation_Short_Title");
		quizDataSet.put("Quiz Short Title", "Automation_Short_Title");

		//Enter Short Description
		shortDescriptionTextField.sendKeys("Automation_ShortDescription");
		quizDataSet.put("Quiz Short Desc", "Automation_ShortDescription");

		//Enter Description
		scrollToElementJavascript(descriptionTextField);
		descriptionTextField.sendKeys(quizDesc);
		quizDataSet.put("Quiz Desc", quizDesc);

        //Upload Images
		scrollToElementJavascript(addQuizImageBackground);
		performPageAction("click", addQuizImageBackground, "", "addQuizImageBackground");
		String uploadedImageUrl = uploadDspaceImage();
		quizDataSet.put("Background Image", uploadedImageUrl);
		//Need to assert here to validate uploaded image with crop and replace icon

		//Back to edit quiz page after successful upload of image
		//Add Qus
		waitForSeconds(10);
		switch (ConfigFileReader.getProperty(EnvParameters.envPropFilePath, qusType)) {
			case "QusList":
				scrollToElementJavascript(ChooseQusListButton);
				waitUntilElementClickable(ChooseQusListButton);
				ChooseQusListButton.click();
				waitForSeconds(5);
				waitUntilElementClickable(chooseQusHeading);
				//assert heading
				selectQusButton.click();
				waitForSeconds(5);
				waitUntilElementClickable(qusAddButton);
				qusAddButton.click();
				//Back to edit quiz page after successful addition of qus from list
				break;
			case "Add New Qus":
				scrollToElementJavascript(AddQusButton);
				waitUntilElementClickable(AddQusButton);
				AddQusButton.click();
				waitForSeconds(5);
				waitUntilElementClickable(addQusHeading);
				//softAssert().assertEquals(addQusHeading.getText(), "Add Question");
				//softAssert().assertEquals(qusContentHeading.getText(), "Question Content");
				String qus = "Quiz" + "_" + getCurrentDateTimeStamp() + ": 2+2=?";
				qusTextField.sendKeys(qus);
				quizDataSet.put("Quiz Qus", qus);
				scrollToElementJavascript(addImageBackground);
				addImageBackground.click();
				uploadDspaceImage();
				waitForSeconds(10);
				scrollToElementJavascript(ansContentHeading);
				//softAssert().assertEquals(ansContentHeading.getText(), "Answer Content");
				//Poll Answers
				firstAnswerTextField.sendKeys("4");
				answerList.add("4");
				secondAnswerTextField.sendKeys("2+2=");
				answerList.add("2+2=");
                waitUntilElementClickable(saveQusButton);
				saveQusButton.click();
				log.info("New Qus & Ans added successfully");
				break;
			default:
				log.error("Invalid selection type!!!");
		}
		//State1-Save As Draft
		clickOnSaveAsDraftButton();
		//verify success message box with 2 buttons
		//verifySuccessDialog("Quiz", "Save As Draft");

		clickOnBackArrow("Quiz");
	}

	public void verifyCreatedQuizContent(String quizStatus) {
		verifyCreatedContent(ConfigFileReader.getProperty(EnvParameters.envPropFilePath, quizStatus), quizTitle);
	}

	public void clickOnQuizEditIcon() {
		clickOnEditIcon(quizTitle);
	}

	/**
	 * Method to edit created content and publish it
	 *
	 */
	public void editAndPublishQuizContent() {
		selectRequiredTag("Content Type", "Quiz");
		quizDataSet.put("Content Type", "Quiz");
		waitForSeconds(5);

		clickOnPublishButton();
		verifySuccessDialog("Quiz", "Published");
	}

	public void verifyPublishedQuizOnListingPage(String pollStatus) {
		//verifyCreatedContent("Draft", quizTitle);
		//waitForSeconds(15);
		//refresh();
		//waitForSeconds(1);
		//isAlertPresent();
		//acceptAlert();
		//waitForSeconds(10);
		verifyCreatedContent("Published", quizTitle);
		waitForSeconds(5);
	}

	/**
	 * Verify published page details
	 *
	 */
	public void verifyPublishedQuizPage(String actionType) {
		clickOnMoreActions(ConfigFileReader.getProperty(EnvParameters.envPropFilePath, actionType));
		waitForSeconds(10);
		//switch to child window
		String parentWindowId = switchToChildWindow();
		waitForSeconds(10);
		//acceptCookies();

		//Verify navigated landing page of published poll page
		verifyLandingPublishedPage(quizDesc);
		//Verify all Qus-Ans and Submit poll
		//submitQuiz();
		//Verify quiz submission and result count
		//verifyQuizSubmission(finalQuizAns);

		switchToParentWindow(parentWindowId);
	}

	/**
	 * Method to submit quiz from published page
	 *
	 */
	public void submitQuiz() {
        //Verify All qus & ans and participate in poll
		String publishedQuizQusXpath = "//h3[contains(text(),'" + quizDataSet.get("Quiz Qus") + "')]";
		By publishedQuizQus = By.xpath(publishedQuizQusXpath);
		waitUntilElementVisible(quizProgressBar);
		//waitUntilElementPresent(publishedQuizQus, 20);
		//softAssert().assertTrue(quizProgressBar.isDisplayed());
		//softAssert().assertTrue(quizQusNumericProgressBar.isDisplayed());
		//softAssert().assertEquals(quizQusNumericProgressBar.getText(), "1/1");
		//softAssert().assertEquals(driver.findElement(publishedQuizQus).getText(), quizDataSet.get("Quiz Qus"));

		//Verify all answers and selects last answer
		By publishedAnsList = By.xpath("//div[@role='radiogroup']//label//span//p");
		List<WebElement> expectedAnswers = driver.findElements(publishedAnsList);
		if (answerList.size() == expectedAnswers.size()) {
			for (int i = 0; i < expectedAnswers.size(); i++) {
				if (expectedAnswers.get(i).getText().equals(answerList.get(i))) {
					finalQuizAns = expectedAnswers.get(i).getText();
					//Interating loop till last ans and selecting last ans as of now
					clickJs(expectedAnswers.get(i));
					log.info(finalQuizAns + " answer verified and selected successfully");
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
		waitUntilElementClickable(quizSubmitButton);
		//softAssert().assertEquals(quizSubmitButton.getText(), "Submit");
		quizSubmitButton.click();
		log.info("Quiz submitted successfully");
	}

	/**
	 * Method to verify submitted quiz
	 *
	 */
	public void verifyQuizSubmission(String finalAns) {
		String submittedQuizTitleXpath = "//h1[contains(text(),'" + quizTitle + "')]";
		By submittedQuizTitle = By.xpath(submittedQuizTitleXpath);
		String wrongScoreCountXpath = "//h1[contains(text(),'" + quizTitle + "')]//following-sibling::p";
		By wrongScoreCount = By.xpath(wrongScoreCountXpath);
		//Circle
		waitUntilElementVisible(quizProgressBar);
		//softAssert().assertTrue(quizProgressBar.isDisplayed());

		//softAssert().assertEquals(driver.findElement(submittedQuizTitle).getText(), quizTitle);
		//softAssert().assertEquals(driver.findElement(wrongScoreCount).getText(), "You Scored 0 out of 1");
		//softAssert().assertEquals(quizAnsNumericProgressBar.getText(), "0/1");
		//softAssert().assertEquals(viewQuizAnswerButton.getText(), "View Answers");
		//softAssert().assertTrue(viewQuizAnswerButton.isEnabled());
		//softAssert().assertEquals(backToHomeButton.getText(), "Back to Home");
		//softAssert().assertTrue(backToHomeButton.isEnabled());
		//softAssert().assertEquals(wrongQuizResultMessage.getText(), "“A winner never stops trying”");

		viewQuizAnswerButton.click();
		waitForSeconds(1);
		waitUntilElementClickable(backArrowIcon);
		//softAssert(). Qus validation
		//softAssert().assertEquals(finalAnsField.getText(), finalAns);
		//softAssert().assertEquals(correctAnsField.getText(), "Correct Answer :");
		//Need to store correct ans and perform submitting new quiz
		log.info("Verified submitted quiz");
		backArrowIcon.click();
	}

	/**
	 * Method to search any existing quiz and edit & publish it
	 *
	 */
	public void editAndPublishExistingQuiz() {
		String expectedTitle = editSearchedContent();
		//Verify redirected edit page
		//softAssert().assertEquals(quizTitleTextField.getAttribute("value"), expectedTitle);
		quizTitle = "Automation_Quiz" + "_" + getCurrentDateTimeStamp();
		quizTitleTextField.clear();
		quizTitleTextField.sendKeys(quizTitle);
        //Need to update later, will make it to call common methods
		waitForSeconds(3);
		clickOnPublishButton();
		verifySuccessDialog("Quiz", "Published");
		//verifyRedirectedPage(actualPageTitle, actualPageUrl);
		log.info("Redirected quiz page verified successfully");

		//verify created quiz on listing page
		verifyCreatedContent("Draft", quizTitle);
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
