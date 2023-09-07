package com.platformX.web.pageObjects;

import com.platformX.web.core.EnvParameters;
import com.platformX.web.core.ConfigFileReader;
import com.platformX.web.genericUtility.SeleniumUtility;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends SeleniumUtility {

    // Object Repositories

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    private WebElement loginButton;
    //By loginButton = By.xpath("//button[contains(text(),'Login')]");
    @FindBy(xpath = "//li[text()='Logout']")
    private static WebElement logoutButton;
    @FindBy(xpath = "//img[contains(@class, 'Platform-x-Avatar-img')]")
    private static WebElement logoutImgIcon;
    @FindBy(name = "password")
    private static WebElement platxPassword;
    @FindBy(name = "username")
    private static WebElement platxUsername;
    //private By platxUsername = By.name("username");
    //private By platxPassword = By.name("password");
    @FindBy(name = "login")
    private static WebElement submitButton;
    //private By submitButton = By.name("login");
    @FindBy(xpath = "//button[text()='Take me out']")
    private static WebElement takeMeOutButton;
    @FindBy(xpath = "//h2[text()='Unsaved Changes']")
    private static WebElement unsavedChangesPopup;

    //Reusable Variable Declaration
    private WebDriver driver;
    private Logger log = Logger.getLogger(this.getClass().getName());
    private final String url = ConfigFileReader.getProperty(EnvParameters.envPropFilePath, "url");

    /**
     * Driver initialization
     */
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * To launch the application
     */
    public void launchApp() {
        goToUrl(url);
    }

    /**
     * To login into PlatformX application
     *
     */
    public void loginToPlatformX(String username, String password) {
        //performPageAction("wait", loginButton, "clickable", "");
        //performPageAction("click", loginButton, "", "loginButton");
        performPageAction("wait", platxUsername, "visible", "");
        performPageAction("sendKeys", platxUsername, "", ConfigFileReader.getProperty(EnvParameters.envPropFilePath, username));
        performPageAction("wait", platxPassword, "visible", "");
        performPageAction("sendKeys", platxPassword, "", ConfigFileReader.getProperty(EnvParameters.envPropFilePath, password));
        performPageAction("click", submitButton, "", "submitButton");
        log.info("Login successfully");

    }
}
