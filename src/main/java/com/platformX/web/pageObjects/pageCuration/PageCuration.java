package com.platformX.web.pageObjects.pageCuration;

import com.platformX.web.core.ConfigFileReader;
import com.platformX.web.core.EnvParameters;
import com.platformX.web.genericUtility.SeleniumUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.platformX.web.core.TestContext.softAssert;

public class PageCuration extends PrelemsPreviewPage {

    //Object Repository
    @FindBy(xpath = "//h4[text()='Pages']")
    private WebElement pageHeading;
    @FindBy(xpath = "//div[@role='dialog']//h2[@id='alert-dialog-title']")
    private WebElement addNewPageDialogHeading;

    @FindBy(xpath = "//div[@role='dialog']//h5[contains(text(),'page name')]//parent::div//input[@id='name']")
    private WebElement addNewPageDialogPageNameTextBox;

    @FindBy(xpath = "//div[@role='dialog']//h5[contains(text(),'page URL')]//parent::div//input[@id='name']")
    private WebElement addNewPageDialogPageUrlTextBox;

    @FindBy(xpath = "//div[@role='dialog']//img[@alt='create Page Popup Image']")
    private WebElement addNewPageDialogPopupImage;

    @FindBy(xpath = "//button[contains(text(),'Done')]")
    private WebElement addNewPageDialogPageDoneButton;

    @FindBy(xpath = "//p[contains(text(),'Page Setting')]")
    private WebElement pageSettingsSideHeader;

    @FindBy(xpath = "//button[@name='add']")
    private WebElement addPrelemIcon;

    @FindBy(xpath = "//button[@name='add']//parent::div//following-sibling::p")
    private WebElement addPrelemText;

    @FindBy(xpath = "//p[contains(text(),'Layouts')]")
    private WebElement prelemLayoutText;

    @FindBy(xpath = "//p[contains(text(),'Layouts')]//following-sibling::p")
    private WebElement prelemLayoutDescription;

    @FindBy(xpath = "//input[@id='search']")
    private WebElement prelemSearch;

    @FindBy(xpath = "//p[contains(text(),'Choose Prelem')]")
    private WebElement choosePrelemPageHeading;

    @FindBy(xpath = "//p[contains(text(),'Choose Prelem')]//following-sibling::p")
    private WebElement choosePrelemPageDescription;

    @FindBy(xpath = "")
    private WebElement expectedPrelems;

    //Reusable variables & declaration
    private WebDriver driver;
    public String envUrl = ConfigFileReader.getProperty(EnvParameters.envPropFilePath, "url");


    /**
     * Constructor call to initialize driver & pass same driver object to parent class
     * @param driver
     */
    public PageCuration(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyRedirectionOnPageList(String expectedPageTitle, String relativePageUrl, String expectedPageHeading) {
        String expectedPageUrl = envUrl + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, relativePageUrl);
        //verifyPageRedirection(expectedPageTitle, expectedPageUrl);
        performPageAction("wait", pageHeading, "visible", "");
        performPageAction("softAssertion", pageHeading, "equals", ConfigFileReader.getProperty(EnvParameters.envPropFilePath, expectedPageHeading));
        log.info("Navigation on Page List page verified successfully");
    }

    public void verifyRedirectionOnPageCurationEditPage(String expectedPageTitle, String relativePageUrl) {
        waitForSeconds(10);
        String expectedPageUrl = envUrl + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, relativePageUrl);
        //Need to implement assertion for title & url
    }

    public void verifyPageSettings(String expectedPageSettings) {
        performPageAction("wait", pageSettingsSideHeader, "clickable", "");
        performPageAction("softAssertion", pageSettingsSideHeader, "equals", ConfigFileReader.getProperty(EnvParameters.envPropFilePath, expectedPageSettings));

        ArrayList<String> expectedPageSettingList = new ArrayList<String>(Arrays.asList("Page Info", "Seo Basic", "Social Share", "Analytics", "Schedule"));
        By pageSettingList = By.xpath("//div[@role='tabpanel']//p");
        List<WebElement> actualPageSettingsList = findElements(pageSettingList);

        if(expectedPageSettingList.size() == actualPageSettingsList.size()) {
            int index = 0;
            for (WebElement actualPageSettings : actualPageSettingsList) {
                if (expectedPageSettingList.contains(actualPageSettings.getText())) {
                    performPageAction("wait", actualPageSettings, "clickable", "");
                    performPageAction("softAssertion", actualPageSettings, "equals", expectedPageSettingList.get(index));
                    index++;
                    log.info("Page Settings verified successfully");
                } else {
                    log.error("Actual Page Settings does not matched with expected page settings");
                }
            }
        } else {
            log.error("Expected Page Setting list not matched with Actual Page Setting list");
        }
    }

    public void clickOnAddPrelemIcon(String addPrelemIconText) {
        performPageAction("wait", addPrelemIcon, "clickable", "");
        performPageAction("wait", addPrelemText, "visible", "");
        performPageAction("softAssertion", addPrelemText, "equals", ConfigFileReader.getProperty(EnvParameters.envPropFilePath, addPrelemIconText));
        performPageAction("click", addPrelemIcon, "", "");
    }

    public void verifyRedirectionOnPrelemSearchPage(String expectedPageTitle, String relativePageUrl, String expectedPageHeading) {
        String expectedPageUrl = envUrl + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, relativePageUrl);
        //verifyPageRedirection(expectedPageTitle, expectedPageUrl);
        performPageAction("wait", choosePrelemPageHeading, "visible", "");
        performPageAction("softAssertion", choosePrelemPageHeading, "equals", ConfigFileReader.getProperty(EnvParameters.envPropFilePath, expectedPageHeading));
        performPageAction("softAssertion", choosePrelemPageDescription, "equals", ConfigFileReader.getProperty(EnvParameters.envPropFilePath, "prelem.search.page.desc"));
        log.info("Navigation on prelem-search page verified successfully");
    }

    public void verifyPrelemCount(String expectedPrelemCount) {
        int expectedPrelemSize = Integer.parseInt(ConfigFileReader.getProperty(EnvParameters.envPropFilePath, expectedPrelemCount));
        By expectedPrelemsXpath = By.xpath("//p[contains(text(),'Choose Prelem')]//following-sibling::div//div[contains(@class,'prelemboxwp')]");
        List<WebElement> expectedPrelems = findElements(expectedPrelemsXpath);
        if (expectedPrelemSize == expectedPrelems.size()) {
            log.info("Total prelem count matched successfully");
        } else {
            softAssert().assertEquals(expectedPrelems.size(), expectedPrelemSize);
            performPageAction("softAssertion", prelemSearch, "fail", "Total prelem count not matched!!!");
        }
    }

    public void searchAndAddPrelem(String prelemType) {
        String prelem = ConfigFileReader.getProperty(EnvParameters.envPropFilePath, prelemType);
        performPageAction("wait", prelemSearch, "clickable", "");
        performPageAction("sendKeys", prelemSearch, "", prelem);


        verifyAboutUs2PrelemPreviewPage("aboutus2.prelem.title", "aboutus2.prelem.desc");

    }

}
