package com.platformX.web.pageObjects;

import com.platformX.web.core.ConfigFileReader;
import com.platformX.web.core.EnvParameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;

public class UserRegistrationPage extends CommonPage {

    //Object Repositories
    @FindBy(xpath = "//div[contains(@class,'menulist')]//p[contains(text(),'User Management')]")
    private static WebElement userMgmtSideBarMenu;
    @FindBy(xpath = "//h6[text()='Users']")
    private static WebElement usersMenuItem;
    @FindBy(xpath = "//button[contains(text(),'Add New')]")
    private static WebElement addNewButton;
    @FindBy(xpath = "//h3[contains(text(),'Create User')]")
    private static WebElement createUserLabel;
    @FindBy(xpath = "//img[contains(@src,'/upload')]")
    private static WebElement profilePictureUploadIcon;
    @FindBy(id = "first_name")
    private static WebElement userFirstNameTextField;
    @FindBy(id = "last_name")
    private static WebElement userLastNameTextField;
    @FindBy(id = "email")
    private static WebElement userEmailTextField;
    @FindBy(xpath = "//span[contains(text(),'Admin')]")
    private static WebElement adminRoleRadioButton;

    @FindBy(xpath = "//button[contains(text(),'Create User')]")
    private static WebElement createUserButton;

    @FindBy(xpath = "//div[@role='dialog']//img[contains(@src,'/warningIcon')]")
    private static WebElement dialogWarningIcon;
    @FindBy(xpath = "//div[@role='dialog']//h2")
    private static WebElement dialogSuccessHeading;

    @FindBy(xpath = "//div[@role='dialog']//span[contains(text(),'e-mail')]")
    private static WebElement dialogSuccessMessage;
    @FindBy(xpath = "//div[@role='dialog']//button[contains(text(),'Go To Listing')]")
    private static WebElement dialogGoToListingButton;
    @FindBy(xpath = "//div[@role='dialog']//button[contains(text(),'Go To Listing')]//following-sibling::button")
    private static WebElement dialogCreateAnotherUserButton;

    @FindBy(id = "search-users")
    private static WebElement userSearchField;

    // Reusable Variable Declaration
    private WebDriver driver;
    public String userMail = "";
    public HashMap<String, String> userDetails = new HashMap<String, String>();

    public UserRegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to click on User sidebar menu and verify redirected page
     */
    public void clickOnUserSideBarMenu(String subMenu, String menu) {
        performPageAction("wait", userMgmtSideBarMenu, "clickable", "");
        performPageAction("softAssertion", userMgmtSideBarMenu, "equals", ConfigFileReader.getProperty(EnvParameters.envPropFilePath, menu));
        performPageAction("wait", usersMenuItem, "clickable", "");
        performPageAction("softAssertion", usersMenuItem, "equals", ConfigFileReader.getProperty(EnvParameters.envPropFilePath, subMenu));
        performPageAction("click", usersMenuItem, "", "usersMenuItem");
    }

    public void verifyNavigationOnUserRegistrationPage(String expectedPageTitle, String relativePageUrl, String expectedPageHeading) {
        String expectedPageUrl = envUrl + ConfigFileReader.getProperty(EnvParameters.envPropFilePath, relativePageUrl);
        verifyPageRedirection(expectedPageTitle, expectedPageUrl);
        //Need to merge below code in above verifyPageRedirection() method
        //performPageAction("wait", pollPageHeading, "clickable", "");
        //performPageAction("softAssertion", pollPageHeading, "equals", ConfigFileReader.getProperty(EnvParameters.envPropFilePath, expectedPageHeading));
        log.info("Navigation on Poll page verified successfully");
    }

    /**
     * Method to fill new user details and submit for user registration
     */
    public void createNewUser() {
        userMail = "automation" + "_" + getCurrentDateTimeStamp() + "@domain.com";
        userDetails.put("User Mail", userMail);
        waitUntilElementVisible(addNewButton);
        waitUntilElementClickable(addNewButton);
        addNewButton.click();
        log.info("Add New button clicked successfully");
        //verifyRedirectedPage("", "", "");
        waitUntilElementVisible(createUserLabel);
        //softAssert().assertEquals(createUserLabel.getText(), "Create User");

        userFirstNameTextField.sendKeys("Automation");
        userDetails.put("First Name", "Automation");
        userLastNameTextField.sendKeys("User");
        userDetails.put("Last Name", "User");
        userEmailTextField.sendKeys(userMail);

        scrollToElementJavascript(adminRoleRadioButton);
        waitUntilElementClickable(adminRoleRadioButton);
        //softAssert().assertEquals(adminRoleRadioButton.getText(), "Admin");
        adminRoleRadioButton.click();
        userDetails.put("Role", "admin");

        waitUntilElementClickable(createUserButton);
        //softAssert().assertEquals(createUserButton.getText(), "Create User");
        createUserButton.click();
        log.info("New user details filled and create user button clicked successfully");
        verifySuccessDialogOnSuccessfulRegistration();
    }

    /**
     * Method to verify success details on Dialog
     */
    public void verifySuccessDialogOnSuccessfulRegistration() {
        waitForSeconds(10);
        String dialogEmailXpath = "//div[@role='dialog']//span[contains(text(),'" + userMail + "')]";
        String dialogEmail = driver.findElement(By.xpath(dialogEmailXpath)).getText();
        String activationTextXpath = "//div[@role='dialog']//span[contains(text(),'" + userMail + "')]//following-sibling::span";
        String activationText = driver.findElement(By.xpath(activationTextXpath)).getText();
        String dialogSuccessMessages = dialogSuccessMessage.getText();

        waitUntilElementVisible(dialogWarningIcon);
        //softAssert().assertTrue(dialogWarningIcon.isDisplayed());
        waitUntilElementVisible(dialogSuccessHeading);
        //softAssert().assertEquals(dialogSuccessHeading.getText(), "User Created Successfully!");
        System.out.println("******************Test actual values*******************");
        System.out.println(dialogEmail);
        System.out.println(activationText);
        System.out.println(dialogSuccessMessages);

        //add above variable to form a complete success message
        waitUntilElementClickable(dialogGoToListingButton);
        //softAssert().assertEquals(dialogGoToListingButton.getText(), "Go To Listing");

        waitUntilElementClickable(dialogCreateAnotherUserButton);
        //softAssert().assertEquals(dialogCreateAnotherUserButton.getText(), "CREATE ANOTHER USER");

        dialogGoToListingButton.click();
        log.info("Success Dialog verified for new registered user and Go To Listing page clicked successfully");
        //verifyRedirectedPage("", "", "");
    }

    /**
     * Method to verify registered user details on Listing Page
     */
    public void verifyRegisteredUserDetailsOnListingPage() {
        String registeredMailIdXpath = "//div[@id='scrollableDiv']//div[contains(@class,'userlist')]//p[contains(text(),'" + userMail + "')]";
        String registeredUserStatusXpath = registeredMailIdXpath + "//following-sibling::p[contains(text(),'Invite Pending')]";
        String registeredUserRoleXpath = registeredUserStatusXpath + "//following-sibling::p";
        String registeredUserFirstNameXpath = registeredMailIdXpath + "//ancestor::div[3]//h5[contains(text(),'" + userDetails.get("First Name") + "')]";
        String registeredUserLastNameXpath = registeredUserFirstNameXpath + "//following-sibling::h5";

        By registeredMailId = By.xpath(registeredMailIdXpath);
        By registeredUserStatus = By.xpath(registeredUserStatusXpath);
        By registeredUserRole = By.xpath(registeredUserRoleXpath);
        By registeredUserFirstName = By.xpath(registeredUserFirstNameXpath);
        By registeredUserLastName = By.xpath(registeredUserLastNameXpath);

        waitUntilElementPresent(registeredMailId);
        //softAssert().assertEquals(driver.findElement(registeredMailId).getText(), userMail);

        waitUntilElementPresent(registeredUserStatus);
        //softAssert().assertEquals(driver.findElement(registeredUserStatus).getText(), "Invite Pending");

        waitUntilElementPresent(registeredUserRole);
        //softAssert().assertEquals(driver.findElement(registeredUserRole).getText(), userDetails.get("Role"));

        waitUntilElementPresent(registeredUserFirstName);
        //softAssert().assertEquals(driver.findElement(registeredUserFirstName).getText(), userDetails.get("First Name"));

        waitUntilElementPresent(registeredUserLastName);
        //softAssert().assertEquals(driver.findElement(registeredUserLastName).getText(), userDetails.get("Last Name"));

        log.info("Registered user verified successfully on listing page");
    }

    public void searchAndEditExistingUserDetails() {
        waitUntilElementClickable(userSearchField);
        userSearchField.clear();
        userSearchField.sendKeys("@test.com");
        waitForSeconds(1);

        //verify searched result

    }
}