package com.platformX.web.pageObjects;

import com.platformX.web.core.ConfigFileReader;
import com.platformX.web.core.EnvParameters;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyDashboardPage extends CommonPage {

    // Object Repositories
    @FindBy(xpath = "//img[@alt='X Logo']")
    private WebElement xLogo;
    @FindBy(xpath = "//div[contains(@class,'auth')]//div[contains(@class,'h6')]")
    private WebElement loggedInUserName;
    @FindBy(xpath = "//h1[contains(text(),'Welcome back')]")
    private WebElement welcomeBackText;
    @FindBy(xpath = "//h1[contains(text(),'Welcome back')]//parent::div//following-sibling::div//h1")
    private WebElement welcomeUserNameText;

    //Reusable Variable Declaration
    private WebDriver driver;
    private Logger log = Logger.getLogger(this.getClass().getName());

    /**
     * Constructor to initialize objects
     */
    public MyDashboardPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyNavigatedDashboardsPage(String expectedPageTitle, String relativePageUrl) {
        String expectedUrl = envUrl + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, relativePageUrl);
        waitForSeconds(10);
        verifyPageRedirection(expectedPageTitle, expectedUrl);
        performPageAction("wait", xLogo, "clickable", "");
        performPageAction("wait", loggedInUserName, "visible", "");
        performPageAction("wait", welcomeBackText, "visible", "");
        performPageAction("softAssertion", welcomeBackText, "equals", "Welcome back ,");
        performPageAction("softAssertion", welcomeUserNameText, "equals", getElementText(loggedInUserName));
        log.info("Navigation on dashboard page verified successfully for Logged-in user");
    }
}
