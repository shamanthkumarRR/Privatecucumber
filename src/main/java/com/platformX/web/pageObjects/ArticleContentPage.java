package com.platformX.web.pageObjects;

import com.platformX.web.core.ConfigFileReader;
import com.platformX.web.core.EnvParameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.HashMap;
import java.util.List;

import static com.platformX.web.core.TestContext.softAssert;

public class ArticleContentPage extends CommonPage {

    //Object Repositories
    @FindBy(xpath = "//span[text()='Article']/ancestor::li")
    private static WebElement articleMenuItem;
    @FindBy(xpath = "//h4[text()='Article']")
    private WebElement articlePageHeading;
    @FindBy(xpath = "//span[text()='Create Article']")
    private static WebElement createPollLabel;
    @FindBy(xpath = "//div[contains(@class,'imgUploadBox')]")
    private static WebElement chooseBanner;
    @FindBy(xpath = "//input[@id='title']")
    private static WebElement articleTitleTextField;
    @FindBy(xpath = "//div[@id='desc']")
    private static WebElement articleDescField;
    @FindBy(xpath = "//div[@role='group']//following-sibling::div")
    private static WebElement chatGptIcon;
    @FindBy(xpath = "//label[@aria-label='upload picture']")
    private static WebElement uploadPictureIcon;

    @FindBy(xpath = "(//span[contains(@aria-label,'Social Share')]//following-sibling::span)[3]")
    private static WebElement articleSaveAsDraftButton;
    @FindBy(xpath = "//button[contains(text(), 'Submit')]")
    private static WebElement articleSubmitButton;
    @FindBy(xpath = "//li[contains(text(), 'Publish')]")
    private static WebElement articlePublishButton;

    @FindBy(xpath = "//div[@role='dialog']//h2[contains(text(),'Submit')]")
    private static WebElement articleDialogHeading;
    @FindBy(xpath = "//div[@role='dialog']//button[contains(text(),'Publish')]")
    private static WebElement articleDialogPublishButton;
    @FindBy(xpath = "//h5[contains(text(),'Content Type')]")
    private static WebElement articleDialogContentTypeTagType;
    @FindBy(xpath = "//h5[contains(text(),'Content Type')]//parent::div//ul//li")
    private static WebElement articleDialogContentTypeTagValue;

    @FindBy(xpath = "(//div[contains(@class,'backarrow')])[2]")
    private static WebElement backArrowIcon;

    //Published Page
    @FindBy(xpath = "(//img[@alt='Logo'])[1]")
    private static WebElement xLogo;
    @FindBy(xpath = "//img[@alt='cropped-img']")
    private static WebElement publishedArticleBannerImage;
    @FindBy(xpath = "//h1[@id='title']")
    private static WebElement publishedArticleTitle;
    @FindBy(xpath = "//img[@alt='Article']//following-sibling::p")
    private static WebElement publishedArticleAuthor;
    @FindBy(xpath = "")
    private static WebElement publishedArticleDateTime;
    @FindBy(xpath = "//img[@class='descAsset']")
    private static WebElement publishedArticleImage;
    @FindBy(xpath = "//img[@class='descAsset']//parent::p")
    private static WebElement publishedArticleDesc;
    @FindBy(xpath = "//h4[contains(text(),'Related Tags')]")
    private static WebElement relatedTagHeading;
    @FindBy(xpath = "//h4[contains(text(),'Related Tags')]//parent::div//span")
    private static WebElement relatedTagValue;
    @FindBy(xpath = "//h4[contains(text(),'Related Articles')]")
    private static WebElement relatedArticlesHeading;

    //Reusable Variable Declaration
    private WebDriver driver;
    private String pageParamUrl = "";
    public String articleTitle = "";
    public String articleDesc = "";
    public HashMap<String, String> articleDataSet = new HashMap<String, String>();

    public ArticleContentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /**
     * Method to verify redirected article page
     *
     */
    public void verifyNavigationOnArticlePage(String expectedPageTitle, String relativePageUrl, String expectedPageHeading) {
        waitForSeconds(3);
        String expectedPageUrl = envUrl + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, relativePageUrl);
        verifyPageRedirection(expectedPageTitle, expectedPageUrl);
        performPageAction("wait", articlePageHeading, "visible", "");
        performPageAction("softAssertion", articlePageHeading, "equals", ConfigFileReader.getProperty(EnvParameters.envPropFilePath, expectedPageHeading));
        log.info("Navigation on Article page verified successfully");
    }

    public void verifyNavigationOnArticleEditPage(String expectedPageTitle, String relativePageUrl, String pageType) {
        String expectedUrl = "";
        if (ConfigFileReader.getProperty(EnvParameters.envPropFilePath, pageType).contains("Create")) {
            expectedUrl =  envUrl + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, relativePageUrl);
        } else if (ConfigFileReader.getProperty(EnvParameters.envPropFilePath, pageType).contains("Edit")) {
            expectedUrl = envUrl + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, relativePageUrl) + pageParamUrl;
        } else {
            log.error("Invalid Page Type!!!");
        }
        verifyPageRedirection(expectedPageTitle, expectedUrl);
        log.info("Navigation on Article page verified successfully");
    }

    /**
     * Method to create & save new Article type content
     *
     */
    public void saveArticleContent() {
        articleTitle = "Automation Article" + " " + getCurrentDateTimeStamp();
        pageParamUrl = articleTitle.replace(" ", "-").toLowerCase();
        articleDataSet.put("Article Title", articleTitle);
        //articleDataSet.put("Poll Desc", pollDesc);
        //Enter Title
        performPageAction("wait", articleTitleTextField, "visible", "");
        performPageAction("sendKeys", articleTitleTextField, "", articleTitle);
        performPageAction("click", chooseBanner, "", "");
        String uploadBannerUrl = uploadDspaceImage();
        waitForSeconds(20);
        articleDataSet.put("Article Banner", uploadBannerUrl);
        performPageAction("wait", chatGptIcon, "clickable", "");
        performPageAction("click", chatGptIcon, "", "chatGptIcon");
        //Wait for given seconds to update article by chatgpt in desc field
        waitForSeconds(10);
        articleDesc = articleDescField.getText();
        articleDataSet.put("Article Desc", articleDesc);
        verifyAndSelectUploadType("upload picture");

        //State1-Save As Draft
        performPageAction("wait", articleSaveAsDraftButton, "clickable", "");
        performPageAction("click", articleSaveAsDraftButton, "", "articleSaveAsDraftButton");
        //verify success message box with 2 buttons
        //verifySuccessDialog("Article", "Save As Draft");
        waitForSeconds(10);
        scrollToElementJavascript(backArrowIcon);
        performPageAction("wait", backArrowIcon, "clickable", "");
        performPageAction("click", backArrowIcon, "", "backArrowIcon");
    }

    public void verifyCreatedArticleContent(String articleStatus) {
        verifyCreatedContent(ConfigFileReader.getProperty(EnvParameters.envPropFilePath, articleStatus), articleTitle);
    }

    public void clickOnArticleEditIcon() {
        clickOnEditIcon(articleTitle);
    }

    /**
     * Method to edit & publish new Article type content
     *
     */
    public void editAndPublishArticleContent() {
        //State2-Publish
        performPageAction("wait", articleSubmitButton, "clickable", "");
        performPageAction("click", articleSubmitButton, "", "articleSubmitButton");
        //verify opened published dialog and publish article
        waitForSeconds(1);
        performPageAction("wait", articleDialogHeading, "visible", "");
        //performPageAction("softAssertion", articleDialogHeading, "equals", "Publish");
        scrollToElementJavascript(articleDialogContentTypeTagType);
        performPageAction("wait", articleDialogContentTypeTagType, "visible", "");
        performPageAction("softAssertion", articleDialogContentTypeTagType, "equals", "Content Type");
        performPageAction("wait", articleDialogContentTypeTagValue, "clickable", "");
        performPageAction("click", articleDialogContentTypeTagValue, "", "articleDialogContentTypeTagValue");
        log.info("Published dialog opened for Article and tag selected successfully");
        performPageAction("wait", articleDialogPublishButton, "clickable", "");
        performPageAction("softAssertion", articleDialogPublishButton, "equals", "Publish");
        performPageAction("click", articleDialogPublishButton, "", "articleDialogPublishButton");

        verifySuccessDialog("Article", "Published");
    }

    /**
     * verify created article on listing page
     */
    public void verifyPublishedArticleOnListingPage(String articleStatus) {
        //verifyCreatedContent("Draft", articleTitle);
        //waitForSeconds(3);
        //refresh();
        //waitForSeconds(1);
        //isAlertPresent();
        //acceptAlert();
        //waitForSeconds(10);
        verifyCreatedContent("Published", articleTitle);
        waitForSeconds(5);
    }

    /**
     * Method to verify published article
     *
     */
    public void verifyPublishedArticlePage(String actionType) {
        clickOnMoreActions(ConfigFileReader.getProperty(EnvParameters.envPropFilePath, actionType));
        waitForSeconds(5);
        //switch to child window
        String parentWindowId = switchToChildWindow();
        waitForSeconds(5);
        //acceptCookies();

        performPageAction("wait", xLogo, "clickable", "");
        performPageAction("softAssertion", xLogo, "assertTrue", "true");

        performPageAction("wait", publishedArticleBannerImage, "visible", "");
        performPageAction("softAssertion", publishedArticleBannerImage, "assertTrue", "true");
        //softAssert().assertEquals(publishedArticleBannerImage.getAttribute("src"), articleDataSet.get("Article Banner"));

        performPageAction("wait", publishedArticleTitle, "visible", "");
        //performPageAction("softAssertion", publishedArticleTitle, "equals", articleTitle);

        //validate Author & DateTime
        performPageAction("wait", publishedArticleDesc, "visible", "");
        performPageAction("softAssertion", publishedArticleDesc, "equals", articleDesc);

        scrollToElementJavascript(publishedArticleImage);
        performPageAction("wait", publishedArticleImage, "visible", "");
        performPageAction("softAssertion", publishedArticleImage, "assertTrue", "true");
        //softAssert().assertEquals(publishedArticleImage.getAttribute("src"), articleDataSet.get("Article Image"));

        waitForSeconds(5);
        scrollToElementJavascript(relatedTagHeading);
        performPageAction("wait", relatedTagHeading, "visible", "");
        performPageAction("softAssertion", relatedTagHeading, "equals", "Related Tags");
        performPageAction("softAssertion", relatedTagValue, "equals", "Articles");

        performPageAction("softAssertion", socialShareLabel, "equals", "Share");
        softAssert().assertEquals(isElementPresent(facebookLink), Boolean.parseBoolean("true"));
        softAssert().assertEquals(isElementPresent(linkedinLink), Boolean.parseBoolean("true"));
        softAssert().assertEquals(isElementPresent(copyLink), Boolean.parseBoolean("true"));
        softAssert().assertEquals(isElementPresent(socialEmbedLink), Boolean.parseBoolean("true"));

        scrollToElementJavascript(relatedArticlesHeading);
        performPageAction("wait", relatedArticlesHeading, "visible", "");
        performPageAction("softAssertion", relatedArticlesHeading, "equals", "Related Articles");

        List<WebElement> relatedArticles = driver.findElements(By.xpath("//h4[contains(text(),'Related Articles')]//parent::div//div[@class='slick-track']//div[contains(@class,'slick-slide')]"));
        softAssert().assertEquals(relatedArticles.size(), 15);
        log.info("Published Article page verified successfully");

        switchToParentWindow(parentWindowId);
    }

    /**
     * Method to perform search for existing article and edit & published it again
     *
     */
    public void editAndPublishExistingArticle() {
        String expectedTitle = editSearchedContent();
        //Verify redirected edit page

        //Edit any existing field
        //softAssert().assertEquals(articleTitleTextField.getAttribute("value"), expectedTitle);
        articleTitle = "Automation Article" + " " + getCurrentDateTimeStamp();
        articleTitleTextField.clear();
        articleTitleTextField.sendKeys(articleTitle);

        waitUntilElementClickable(articleSubmitButton);
        articleSubmitButton.click();
        waitUntilElementClickable(articlePublishButton);
        articlePublishButton.click();
        //verify opened published dialog and publish article
        waitForSeconds(1);
        waitUntilElementVisible(articleDialogHeading);
        //softAssert().assertEquals(articleDialogHeading.getText(), "Publish");
        waitUntilElementClickable(articleDialogPublishButton);
        //softAssert().assertEquals(articleDialogPublishButton.getText(), "Publish");
        articleDialogPublishButton.click();
        log.info("Publish button clicked successfully");

        verifySuccessDialog("Article", "Published");
        //verifyRedirectedPage(actualPageTitle, actualPageUrl);
        log.info("Redirected quiz page verified successfully");
        //verify created quiz on listing page
        verifyCreatedContent("Draft", articleTitle);
        waitForSeconds(15);
        refresh();
        waitForSeconds(1);
        isAlertPresent();
        acceptAlert();
        waitForSeconds(10);
        verifyCreatedContent("Published", articleTitle);
        waitForSeconds(5);

    }

    /**
     * Method to verify and select a upload option by clicking on plus icon
     *
     */
    public void verifyAndSelectUploadType(String uploadType) {
        waitForSeconds(5);
        By uploadTypeList = By.xpath("//div[contains(@class,'plusIconBox')]//label");
        scrollToElementJavascript(uploadPictureIcon);
        waitUntilElementClickable(uploadPictureIcon);
        uploadPictureIcon.click();
        waitUntilElementPresent(uploadTypeList);
        List<WebElement> uploadListItems = driver.findElements(uploadTypeList);
        for (WebElement uploadListItem : uploadListItems) {
            if (uploadListItem.getAttribute("aria-label").equals(uploadType)) {
                uploadListItem.click();
                break;
            }

        }
        log.info(uploadType + " selected successfully.");
        //Upload Images/Videos/Contents
        String uploadedImageUrl = uploadDspaceImage();
        articleDataSet.put("Article Image", uploadedImageUrl);
        waitForSeconds(20);//wait for some seconds to render uploaded image in banner field
    }
}
