package com.platformX.web.pageObjects.pageCuration;

import com.platformX.web.core.ConfigFileReader;
import com.platformX.web.core.EnvParameters;
import com.platformX.web.genericUtility.SeleniumUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CommonPrelem extends SeleniumUtility {

    //Object repo
    @FindBy(xpath = "//button[contains(text(),'Add Prelem')]")
    private WebElement previewAddPrelemButton;

    //Reusable variable declaration
    private WebDriver driver;

    public CommonPrelem(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void verifyPrelemDetailsOnHover(String expectedPrelemName, String expectedPrelemDesc) {
        String prelemName = ConfigFileReader.getProperty(EnvParameters.prelemPropFilePath, expectedPrelemName);

        String prelemNameXpath = "//p[contains(text(),'Choose Prelem')]//following-sibling::div//div[contains(@class,'prelemboxwp')]//p[contains(text(),'" + prelemName + "')]";
        WebElement prelemTitle = findElement(By.xpath(prelemNameXpath));

        String prelemDescXpath = prelemNameXpath + "//following-sibling::p";
        WebElement prelemDescription = findElement(By.xpath(prelemDescXpath));

        String prelemPreviewButtonXpath = prelemNameXpath + "//parent::div//button[contains(text(),'Preview')]";
        WebElement prelemPreviewButton = findElement(By.xpath(prelemPreviewButtonXpath));

        String prelemAddButtonXpath = prelemNameXpath + "//parent::div//button[contains(text(),'Add Prelem')]";
        WebElement prelemAddButton = findElement(By.xpath(prelemAddButtonXpath));

        mouseHover(prelemTitle);
        List<WebElement> expectedPrelem = findElements(By.xpath(prelemNameXpath));
        if (getElementText(prelemTitle).equals(prelemName) && expectedPrelem.size() == 1) {
            performPageAction("wait", prelemTitle, "visible", "");
            performPageAction("softAssertion", prelemTitle, "equals", prelemName);
            performPageAction("softAssertion", prelemDescription, "equals", ConfigFileReader.getProperty(EnvParameters.prelemPropFilePath, expectedPrelemDesc));
            performPageAction("wait", prelemPreviewButton, "clickable", "");
            performPageAction("softAssertion", prelemPreviewButton, "equals", ConfigFileReader.getProperty(EnvParameters.prelemPropFilePath, "prelem.preview.button"));
            performPageAction("wait", prelemAddButton, "clickable", "");
            performPageAction("softAssertion", prelemAddButton, "equals", ConfigFileReader.getProperty(EnvParameters.prelemPropFilePath, "prelem.add.button"));
            log.info(prelemName + " Prelem found & verified successfully on mouse hover in prelem-search page");
            performPageAction("click", prelemPreviewButton, "", "");
            //verify redirected page
            //verify title & desc in preview page
        } else {
            performPageAction("softAssertion", prelemTitle, "fail", "Prelem Not Found!");
            log.error(prelemName + " Prelem Not Found!");
        }
    }

    protected void clickOnBackArrow() {

    }

    protected void clickOnAddPrelemButton() {
        performPageAction("wait", previewAddPrelemButton, "clickable", "");
        performPageAction("softAssertion", previewAddPrelemButton, "equals", ConfigFileReader.getProperty(EnvParameters.prelemPropFilePath, "prelem.add.button"));
        performPageAction("click", previewAddPrelemButton, "", "");
        log.info("Add Prelem button verified and clicked successfully");
    }
}
