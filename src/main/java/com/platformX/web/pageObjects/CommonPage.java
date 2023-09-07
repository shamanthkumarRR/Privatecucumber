package com.platformX.web.pageObjects;

import com.platformX.web.core.ConfigFileReader;
import com.platformX.web.core.EnvParameters;
import com.platformX.web.genericUtility.SeleniumUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.platformX.web.core.TestContext.softAssert;

public class CommonPage extends SeleniumUtility {
	
	// Object Repositories
	@FindBy(xpath = "//div[contains(@class,'menulist')]//span[contains(text(),'Content')]")
	protected WebElement contentMenu;

	@FindBy(xpath = "//div[contains(@class,'menuIcon')]//img[@alt='Menu Icon']")
	private WebElement sideBarMenuCollapseIcon;
	
	@FindBy(xpath = "//div[contains(@class,'menuIcon')]//following-sibling::div//button")
	private WebElement globalSearchField;
	@FindBy(xpath = "//div[@role='dialog']//h6[contains(text(),'All')]")
	private WebElement searchFilterLabelAll;
	@FindBy(xpath = "//div[@role='dialog']//div[contains(@class,'searchwp')]//div[contains(@class,'searchIcon')]")
	private WebElement globalSearchIcon;
	@FindBy(xpath = "//div[@role='dialog']//div[contains(@class,'searchwp')]//input")
	private WebElement globalSearchTextField;
	@FindBy(xpath = "//div[@role='dialog']//div[contains(@class,'filterBtn')]")
	private WebElement globalSearchFilterButton;
	@FindBy(xpath = "//button[contains(text(),'search')]")
	private WebElement globalSearchButton;

	@FindBy(xpath = "")
	private WebElement languageSelectionIcon;
	
	@FindBy(xpath = "")
	private WebElement notificationBellIcon;
	
	@FindBy(xpath = "")
	private WebElement userDetailsIcon;
	
	@FindBy(xpath = "")
	private WebElement menuTypeHeader;

	//@FindBy(xpath = "//button[contains(@class,'addnewpage')]//ancestor::main//div//span")
	//private static WebElement contentpageHeading;

	private By contentpageHeading = By.xpath("//button[contains(@class,'addnewpage')]//ancestor::main//div//span");
	
	@FindBy(xpath = "")
	private WebElement stateFilterIcon;
	
	@FindBy(xpath = "//button[contains(@class,'addnewpage')]")
	private WebElement createNewButton;
	
	@FindBy(xpath = "")
	private WebElement contentTypeIcon;

	@FindBy(xpath = "")
	private WebElement contentCreator;
	
	@FindBy(xpath = "")
	private WebElement contentCreationDate;
	
	@FindBy(xpath = "")
	private WebElement contentCreationTime;
	
	@FindBy(xpath = "")
	private WebElement contentEditIcon;
	
	@FindBy(xpath = "")
	private WebElement contentDeleteIcon;
	
	@FindBy(xpath = "")
	private WebElement contentMultiActions;

	//Image Upload
	@FindBy(xpath = "//h5[contains(text(),'Choose your image')]")
	protected WebElement addImageBackground;

	By dspaceImagePageHeader = By.xpath("//h4[contains(text(),'Choose your image')]");
	@FindBy(xpath = "(//div[@id='scrollableDiv']//img)[1]")
	protected WebElement dspaceImageToBeSelected;
	@FindBy(xpath = "//button[contains(text(),'Save')]")
	protected WebElement dspaceImagePageDoneButton;

	//Delete
	@FindBy(xpath = "//div[@role='dialog']//h2[@id='alert-dialog-title']")
	private WebElement dialogHeading;
	@FindBy(xpath = "//div[@role='dialog']//h5[contains(text(),'delete')]")
	private WebElement deleteDialogMessage;
	@FindBy(xpath = "//button[contains(text(),'Yes, delete it')]")
	private WebElement deleteConfirmButton;
	@FindBy(xpath = "//h5[contains(text(),'No results found Please try again')]")
	private WebElement noRecordMessage;

	//Common buttons
	@FindBy(xpath = "//button[contains(text(), 'Preview')]")
	private WebElement previewButton;
	@FindBy(xpath = "//button[contains(text(), 'Save As Draft')]")
	private WebElement saveAsDraftButton;
	@FindBy(xpath = "//button[contains(text(), 'Publish')]")
	private WebElement publishButton;

	//Add Qus
	@FindBy(xpath = "//button[contains(text(),'Add Question')]")
	public WebElement AddQusButton;
	@FindBy(xpath = "//span[contains(text(),'Add Question')]")
	public WebElement addQusHeading;
	@FindBy(xpath = "//span[contains(text(),'Question Content')]")
	public WebElement qusContentHeading;
	@FindBy(xpath = "//input[@id='question']")
	public WebElement qusTextField;
	@FindBy(xpath = "//h3[contains(text(),'Answer Content')]")
	public WebElement ansContentHeading;
	@FindBy(xpath = "//input[@name='1']")
	public WebElement firstAnswerTextField;
	@FindBy(xpath = "//input[@name='2']")
	public WebElement secondAnswerTextField;
	@FindBy(xpath = "//span[contains(text(),'Add Answer')]")
	public WebElement addMoreAnsTextLink;
	@FindBy(xpath = "//input[@name='3']")
	public WebElement pollThirdAnswerTextField;
	@FindBy(xpath = "//button[contains(text(),'Done')]")
	public WebElement saveQusButton;

	//Success Dialog
	@FindBy(xpath = "//div[@role='dialog']//h2")
	private WebElement contentStateSuccessHeading;
	@FindBy(xpath = "//div[@role='dialog']//button[contains(text(),'Edit')]")
	private WebElement dialogEditButton;
	@FindBy(xpath = "//div[@role='dialog']//button[contains(text(),'Go To Listing')]")
	private WebElement dialogGoToListingButton;

	//Published Poll Page Objects
	@FindBy(xpath = "//h6[contains(text(),'Share')]")
	protected WebElement socialShareLabel;
	@FindBy(xpath = "//h6[contains(text(),'Share')]//parent::div//div//button[@aria-label='facebook']")
	protected WebElement facebookLink;
	@FindBy(xpath = "//h6[contains(text(),'Share')]//parent::div//div//button[@aria-label='linkedin']")
	protected WebElement linkedinLink;
	@FindBy(xpath = "//h6[contains(text(),'Share')]//parent::div//div//img[@alt='share5']")
	protected WebElement copyLink;
	@FindBy(xpath = "//h6[contains(text(),'Share')]//parent::div//div//img[@alt='share6']")
	protected WebElement socialEmbedLink;
	@FindBy(xpath = "//img[@alt='Close Icon']")
	private WebElement closeIconLink;

	private By welcomeText = By.xpath("//h1[contains(text(),'Welcome')]");

	// Reusable Variable Declaration
	private WebDriver driver;

	protected String envUrl = ConfigFileReader.getProperty(EnvParameters.envPropFilePath, "url");
	public By searchFilterItems = By.xpath("//div[contains(@class,'MuiModal-root-MuiPopover')]//div[contains(@class,'Platform-x-Paper-rounded')]//ul//li//div[contains(@class,'icon')]//following-sibling::span");
	public ArrayList<String> expectedFilterOptions = new ArrayList<>(Arrays.asList("All", "Pages", "Article", "Quiz", "Poll", "Event"));
	private String subStringToBeSearched = "";
	public String actualContentType = "";
	public String actualContentTitle = "";
	protected static String editPageParamUrl = "";

	public CommonPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	/**
	 * Method to verify redirected page
	 */

	public void verifyPageRedirection(String expectedPageTitle, String expectedPageUrl) {
		waitForSeconds(5);
		softAssert().assertEquals(getPageTitle(), ConfigFileReader.getProperty(EnvParameters.envPropFilePath, expectedPageTitle));
		softAssert().assertEquals(getCurrentUrl(), expectedPageUrl);
		log.info("Redirected page verified successfully");
	}

	/**
	 * Method to click on Add New Button
	 *
	 */
	public void clickOnAddNewButton() {
		performPageAction("wait", createNewButton, "clickable", "");
		performPageAction("click", createNewButton, "", "createNewButton");
	}

	/**
	 * Method to click on Preview button
	 *
	 */
	public void clickOnPreviewButton() {
		performPageAction("wait", previewButton, "clickable", "");
		performPageAction("click", previewButton, "", "previewButton");
	}

	/**
	 * Method to click on Save As Draft button
	 *
	 */
	public void clickOnSaveAsDraftButton() {
		performPageAction("wait", saveAsDraftButton, "clickable", "");
		performPageAction("click", saveAsDraftButton, "", "saveAsDraftButton");
		waitForSeconds(10);
	}

	/**
	 * Method to click on Publish Button
	 *
	 */
	public void clickOnPublishButton() {
		performPageAction("wait", publishButton, "clickable", "");
		performPageAction("click", publishButton, "", "publishButton");
	}

	/**
	 * Method to verify success dialog on the basis of content states
	 *
	 */
	public void verifySuccessDialog(String contentType, String contentState) {
		actualContentType = contentType;
		waitForSeconds(1);
		performPageAction("wait", contentStateSuccessHeading, "visible", "");
		if (contentState.equals("Save As Draft")) {
			performPageAction("softAssertion", contentStateSuccessHeading, "equals", contentState);
			//softAssert() for successMessage

		} else if (contentState.equals("Published")) {
			performPageAction("softAssertion", contentStateSuccessHeading, "equals", "Congratulations!");
			//softAssert() for successMessage
		} else {
			log.error("Invalid State!!!");
		}
		performPageAction("wait", dialogGoToListingButton, "clickable", "");
		waitForSeconds(25);//waiting for publishing the content. Published content will show on listing page ~ 15 sec
		performPageAction("click", dialogGoToListingButton, "", "");
		log.info(contentType + " has been created and Go To Listing button clicked successfully");
	}

	/**
	 * Method to created content details on listing page
	 *
	 */
	public void verifyCreatedContent(String actualContentState, String contentTitleName) {
		actualContentTitle = contentTitleName;
		String expectedContentStateMediaUrl = "";
		if (actualContentState.equals("Draft")) {
			expectedContentStateMediaUrl = envUrl + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, "content.status.draft.media.url");
		} else if (actualContentState.equals("Published")) {
			expectedContentStateMediaUrl = envUrl + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, "content.status.published.media.url");
		} else {
			log.error("Invalid State!!!");
		}
		waitForSeconds(3);
		String contentTitleXpath = "//div[@id='scrollableDiv']//h5[contains(text(),'" + contentTitleName + "')]";
		By contentTitle = By.xpath(contentTitleXpath);
		String contentStateXpath = "(//div[@id='scrollableDiv']//h5[contains(text(),'" + contentTitleName +  "')]//ancestor::div[5]//following-sibling::div)[6]//p[@aria-label='" + actualContentState + "']//img";
        By contentState = By.xpath(contentStateXpath);
		waitUntilElementPresent(contentTitle);
		waitUntilElementPresent(contentState);
		softAssert().assertEquals(driver.findElement(contentTitle).getText(), contentTitleName);
		softAssert().assertEquals(driver.findElement(contentState).getAttribute("src"), expectedContentStateMediaUrl);
		log.debug("Content verified on listing page");
	}

	/**
	 * Method to click on edit action
	 *
	 */
	public void clickOnEditIcon(String contentTitle) {
		String contentEditIconXpath = "(//div[@id='scrollableDiv']//h5[contains(text(),'" + contentTitle + "')]//ancestor::div[5]//following-sibling::div)[6]//li[@role='menuitem']//img[contains(@src,'/editIcon.')]";
		By contentEditIcon = By.xpath(contentEditIconXpath);
		waitUntilElementPresent(contentEditIcon);
		driver.findElement(contentEditIcon).click();
		waitForSeconds(10);
		log.info("Redirected edit page verified successfully");
	}

	public void verifyNavigationOnContentEditPage(String expectedPageTitle, String relativePageUrl, String expectedPageHeading) {
		String pageHeadingXpath = "//h4[contains(text(),'" + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, expectedPageHeading) + "')]";
		WebElement pageHeading = findElement(By.xpath(pageHeadingXpath));

		String expectedUrl = "";
		if (ConfigFileReader.getProperty(EnvParameters.envPropFilePath, expectedPageHeading).contains("Create")) {
			expectedUrl =  envUrl + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, relativePageUrl);
		} else if (ConfigFileReader.getProperty(EnvParameters.envPropFilePath, expectedPageHeading).contains("Edit")) {
			expectedUrl = envUrl + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, relativePageUrl) + editPageParamUrl;
		} else {
			log.error("Invalid Page Heading!!!");
		}

		verifyPageRedirection(expectedPageTitle, expectedUrl);
		performPageAction("wait", pageHeading, "clickable", "");
		performPageAction("softAssertion", pageHeading, "equals", ConfigFileReader.getProperty(EnvParameters.envPropFilePath, expectedPageHeading));
		log.info("Navigation on Content page verified successfully");
	}

	/**
	 * Method to select required tags
	 *
	 */
	public void selectRequiredTag(String tagCategories, String tagValues) {
		String tagCategoryXpath = "//div[@id='tags']//h5[contains(text(),'" + tagCategories + "')]";
		String moreTagsLinkXpath = "//h5[contains(text(),'" + tagCategories + "')]//parent::div//ul//span[contains(text(),'View')]";
		String tagValueXpath = "//h5[contains(text(),'" + tagCategories + "')]//parent::div//ul//li//label//span[contains(text(),'" + tagValues + "')]";
		By tagCategory = By.xpath(tagCategoryXpath);
		By viewMoreTags = By.xpath(moreTagsLinkXpath);
		By tagValue = By.xpath(tagValueXpath);

		waitUntilElementPresent(tagCategory);
		//softAssert().assertEquals(driver.findElement(tagCategory).getText(), tagCategories);
		driver.findElement(viewMoreTags).click();
		waitUntilElementPresent(tagValue);
		//softAssert().assertEquals(driver.findElement(tagValue).getText(), tagValues);
		driver.findElement(tagValue).click();
		log.debug("Tag selected successfully");
	}

	/**
	 * Method to click on given action type from 2 dots
	 *
	 */
	public void clickOnMoreActions(String actionTypes) {
		String moreActionIconXpath = "(//div[@id='scrollableDiv']//h5[contains(text(),'" + actualContentTitle + "')]//ancestor::div[5]//following-sibling::div)[6]//button[@id='long-button']//img[contains(@src,'/moreHoriz.')]";
		By moreActionIcon = By.xpath(moreActionIconXpath);
		waitUntilElementPresent(moreActionIcon);
		driver.findElement(moreActionIcon).click();
		waitForSeconds(5);

		String actionTypeXpath = "//li[contains(text(),'" + actionTypes + "')]";
		By actionType = By.xpath(actionTypeXpath);
		driver.findElement(actionType).click();
		log.info(actionTypes + " clicked successfully");
	}

	/**
	 * Method to verify landing page of any published page
	 *
	 */
	public void verifyLandingPublishedPage(String contentDesc) {
		//verifyRedirectedPage(actualPageTitle, actualPageUrl);

		String publishedTitleXpath = "//h1[contains(text(),'" + actualContentTitle + "')]";
		String publishedDescXpath = "//h5[contains(text(),'" + contentDesc + "')]";
		String startButtonXpath = "//button[contains(text(),'" + actualContentType + "')]";
		By publishedTitle = By.xpath(publishedTitleXpath);
		By publishedDesc = By.xpath(publishedDescXpath);
		By startButton = By.xpath(startButtonXpath);

		waitUntilElementPresent(publishedTitle);
		softAssert().assertEquals(driver.findElement(publishedTitle).getText(), actualContentTitle);
		softAssert().assertEquals(driver.findElement(publishedDesc).getText(), contentDesc);
		softAssert().assertEquals(driver.findElement(startButton).isEnabled(), Boolean.parseBoolean("true"));

		softAssert().assertEquals(driver.findElement(startButton).getText(), "Start " +actualContentType);
		//softAssert().assertEquals(socialShareLabel.getText(), "Share");
		softAssert().assertEquals(isElementPresent(facebookLink), Boolean.parseBoolean("true"));
		softAssert().assertEquals(isElementPresent(linkedinLink), Boolean.parseBoolean("true"));
		softAssert().assertEquals(isElementPresent(copyLink), Boolean.parseBoolean("true"));
		softAssert().assertEquals(isElementPresent(socialEmbedLink), Boolean.parseBoolean("true"));
		softAssert().assertEquals(isElementPresent(closeIconLink), Boolean.parseBoolean("true"));

		driver.findElement(startButton).click();
		log.info("Published Poll page verified and start button clicked successfully");
	}

	/**
	 * Method to perform global search
	 *
	 */
	public void verifyAndPerformGlobalSearch(String contentType, String subStringToBeSearched) {
		List<WebElement> searchFilterOptions = driver.findElements(searchFilterItems);
		String filterVerificationResult = "";
		this.subStringToBeSearched = subStringToBeSearched;
		performPageAction("wait", globalSearchField, "clickable", "");
		performPageAction("click", globalSearchField, "", "");
		log.info("Global search icon click successfully");
		performPageAction("wait", searchFilterLabelAll, "clickable", "");
		performPageAction("softAssertion", searchFilterLabelAll, "equals", "All");
		log.info("Global search dialog opened and verified All filter successfully");

		searchFilterLabelAll.click();
		waitForSeconds(3);
		filterVerificationResult = verifySearchFilterOptions();
        if (filterVerificationResult.equals("pass")) {
			for (WebElement filterOption : searchFilterOptions) {
				if (filterOption.getText().equals(actualContentType)) {
					performPageAction("click", filterOption, "", "");
					log.error("Filter selected successfully.");
					break;
				}
			}
		} else {
			log.error("Filter verification failed!!!");
			//Workaround of above if condition
			waitForSeconds(3);
			String filterXpath = "//div[contains(@class,'MuiModal-root-MuiPopover')]//div[contains(@class,'Platform-x-Paper-rounded')]//ul//li//div[contains(@class,'icon')]//following-sibling::h6[contains(text(),'" + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, contentType) + "')]";
			WebElement filterOption = driver.findElement(By.xpath(filterXpath));
			performPageAction("wait", filterOption, "clickable", "");
			performPageAction("click", filterOption, "", "filterOption");
		}
		waitForSeconds(3);
		performPageAction("wait", globalSearchTextField, "clickable", "");
		performPageAction("sendKeys", globalSearchTextField, "", ConfigFileReader.getProperty(EnvParameters.envPropFilePath, subStringToBeSearched));
		performPageAction("wait", globalSearchFilterButton, "clickable", "");
		performPageAction("click", globalSearchFilterButton, "", "globalSearchFilterButton");
		waitForSeconds(1);
		performPageAction("wait", globalSearchButton, "clickable", "");
		performPageAction("click", globalSearchButton, "clickable", "globalSearchButton");
		log.info("Search operation performed");
	}

	/**
	 * Method to verify search filters
	 *
	 */
	public String verifySearchFilterOptions() {
		List<WebElement> searchFilterOptions = driver.findElements(searchFilterItems);
		String verificationResult = "";
		if (expectedFilterOptions.size() == searchFilterOptions.size()) {
			for (WebElement filterOption : searchFilterOptions) {
				if (searchFilterOptions.contains(filterOption.getText())) {
					verificationResult = "pass";
					log.info("Expected filter option list matched with actual filter list");
				} else {
					verificationResult = "fail";
					performPageAction("softAssertion", filterOption, "fail", "Expected filter list does not contain actual filters");
					log.error("Actual filter : " + filterOption.getText() +" not matched with expected filter list!!!");
				}
			}
		} else {
			performPageAction("softAssertion", closeIconLink, "fail", "Expected search filter options not matched with actual options in Global Search");
			log.error("Expected filter list size : " + expectedFilterOptions.size());
			log.error("Actual filter list size : " + searchFilterOptions.size());
		}
		return verificationResult;
	}

	/**
	 * Method to edit searched content
	 *
	 */
	public String editSearchedContent() {
		String searchedContentTitle = "";
		By searchedContent = By.xpath("//div[@id='scrollableDiv']//span[contains(text(),'" + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, subStringToBeSearched) + "')]");
		searchedContentTitle = driver.findElement(searchedContent).getText();
		verifyCreatedContent("Published", searchedContentTitle);
		clickOnEditIcon(searchedContentTitle);
		return searchedContentTitle;
	}

	/**
	 * Method to deleted newly published content
	 *
	 */
	public void deleteContent() {
		waitForSeconds(5);
		String deleteIconXpath = "(//div[@id='scrollableDiv']//h5[contains(text(),'" + actualContentTitle + "')]//ancestor::div[5]//following-sibling::div)[6]//img[contains(@src,'/deleteIcon.')]";
		By deleteIcon = By.xpath(deleteIconXpath);
		String deleteMessage = "Do you really want to delete this "+ actualContentType + "?. This process cannot be undone.";
		//verifyCreatedContent("Published", actualContentTitle);

		waitUntilElementPresent(deleteIcon);
		driver.findElement(deleteIcon).click();
		waitForSeconds(3);
		performPageAction("wait", dialogHeading, "visible", "");
		performPageAction("softAssertion", dialogHeading, "equals", "Are you sure?");
		//performPageAction("softAssertion", deleteDialogMessage, "equals", deleteMessage);
		performPageAction("softAssertion", deleteConfirmButton, "equals", "Yes, Delete It");
		performPageAction("click", deleteConfirmButton, "", "deleteConfirmButton");
		log.info("Delete dialog verified and " + actualContentTitle + " deleted successfully");
		waitForSeconds(5);
		//verifyAndPerformGlobalSearch(actualContentType, actualContentTitle);
		//performPageAction("wait", noRecordMessage, "visible", "");
		//performPageAction("softAssertion", noRecordMessage, "equals", "No results found Please try again");
        log.info(actualContentTitle + " deleted successfully");
	}

	/**
	 * Method to upload 1st image from dspace
	 *
	 */
	public String uploadDspaceImage() {
		String selectedImageUrl = "";
		waitForSeconds(10);
		waitUntilElementPresent(dspaceImagePageHeader);
		//softAssert().assertEquals(driver.findElement(dspaceImagePageHeader).getText(), "Choose your image");
		waitUntilElementClickable(dspaceImageToBeSelected);
		selectedImageUrl = dspaceImageToBeSelected.getAttribute("src");
		clickJs(dspaceImageToBeSelected);
		waitForSeconds(5);
		waitUntilElementClickable(dspaceImagePageDoneButton);
		dspaceImagePageDoneButton.click();
		log.info("Image selected and save successfully");
		return selectedImageUrl;
	}

	/**
	 * Method to upload specific image from dspace
	 *
	 */
	public String uploadDspaceImage(String imageUrl) {
		String selectedImageUrl = "";
		String specificImageToBeUploadedXpath = "//div[@id='scrollableDiv']//img[contains(@src,'" + imageUrl + "')]";
		WebElement specificImageToBeUploaded = driver.findElement(By.xpath(specificImageToBeUploadedXpath));
		waitForSeconds(10);//wait for given seconds to load all image completely
		waitUntilElementPresent(dspaceImagePageHeader);
		//softAssert().assertEquals(driver.findElement(dspaceImagePageHeader).getText(), "Choose your image");
		waitUntilElementClickable(specificImageToBeUploaded);
		selectedImageUrl = specificImageToBeUploaded.getAttribute("src");
		clickJs(specificImageToBeUploaded);
		waitForSeconds(5);
		waitUntilElementClickable(dspaceImagePageDoneButton);
		dspaceImagePageDoneButton.click();
		waitForSeconds(20);
		log.info("Image selected and save successfully");
		return selectedImageUrl;
	}

	/**
	 * Method to click on back Arrow icon to navigate back on listing page from edit page
	 *
	 */
	public void clickOnBackArrow(String contentType) {
		waitForSeconds(5);
		String backArrowIconXpath = "//h4[contains(text(),'Create " + contentType + "')]";
		WebElement backArrowIcon = findElement(By.xpath(backArrowIconXpath));
		scrollToElementJavascript(backArrowIcon);
		performPageAction("wait", backArrowIcon, "clickable", "");
		performPageAction("click", backArrowIcon, "", "backArrowIcon");
	}

}
