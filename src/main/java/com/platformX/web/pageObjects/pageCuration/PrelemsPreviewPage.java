package com.platformX.web.pageObjects.pageCuration;

import com.platformX.web.core.ConfigFileReader;
import com.platformX.web.core.EnvParameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.platformX.web.core.TestContext.softAssert;

public class PrelemsPreviewPage extends CommonPrelem {

    //Object Repository - About Us 2
    @FindBy(xpath = "//div[contains(@class,'about-us2')]//div[contains(@class,'textcenter')]//h1")
    private WebElement aboutUs2PrelemHeading;
    @FindBy(xpath = "//div[contains(@class,'about-us2')]//div[contains(@class,'textcenter')]//span[@id='typescript_heading']")
    private WebElement aboutUs2PrelemBlinkText;
    @FindBy(xpath = "//div[contains(@class,'about-us2')]//div[contains(@class,'textcenter')]//p")
    private WebElement aboutUs2PrelemDesc;
    @FindBy(xpath = "//div[contains(@class,'about-us2')]//div[contains(@class,'textcenter')]//button[@id='Button1_Name']")
    private WebElement aboutUs2PrelemCtaButton;

    @FindBy(xpath = "//div[contains(@class,'about-us2')]//div[contains(@class,'imageWrapper')]//picture//img[contains(@src,'WebsiteIntroduction.png')]")
    private WebElement aboutUs2PrelemMainImage;
    @FindBy(xpath = "//div[contains(@class,'about-us2')]//div[contains(@class,'imageWrapper')]//h1[@id='secondary_heading']")
    private WebElement aboutUs2PrelemImageSecondaryHeading;
    @FindBy(xpath = "//div[contains(@class,'about-us2')]//div[contains(@class,'imageWrapper')]//img[@class='frame1']")
    private WebElement aboutUs2PrelemImageFirstFrame;
    @FindBy(xpath = "//div[contains(@class,'about-us2')]//div[contains(@class,'imageWrapper')]//img[@class='frame2']")
    private WebElement aboutUs2PrelemImageSecondFrame;
    @FindBy(xpath = "//div[contains(@class,'about-us2')]//div[contains(@class,'imageWrapper')]//img[@class='frame3']")
    private WebElement aboutUs2PrelemImageThirdFrame;

    //Object Repository - Image/Video Carousel 1
    @FindBy(xpath = "//div[contains(@class,'image-video-carousel1')]//h2[@id='title']")
    private WebElement carousel1PrelemHeading;
    @FindBy(xpath = "//div[contains(@class,'image-video-carousel1')]//p[@id='sub_title']")
    private WebElement carousel1PrelemDesc;
    @FindBy(xpath = "//div[contains(@class,'image-video-carousel1')]//div[contains(@class,'rightimagevideo')]//img[contains(@src,'ProductDetails.png')]")
    private WebElement carousel1PrelemMainImage;



    //Reusable Variable Declaration & Initialization
    private WebDriver driver;


    public PrelemsPreviewPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void verifyAboutUs2PrelemPreviewPage(String expectedPrelemName, String expectedPrelemDesc) {
        verifyPrelemDetailsOnHover(expectedPrelemName, expectedPrelemDesc);
        waitForSeconds(10);
        performPageAction("wait", aboutUs2PrelemHeading, "visible", "");
        performPageAction("softAssertion", aboutUs2PrelemHeading, "equals", ConfigFileReader.getProperty(EnvParameters.prelemPropFilePath, "aboutus2.prelem.preview.heading"));

        performPageAction("wait", aboutUs2PrelemBlinkText, "visible", "");
        performPageAction("softAssertion", aboutUs2PrelemBlinkText, "equals", ConfigFileReader.getProperty(EnvParameters.prelemPropFilePath, "aboutus2.prelem.preview.blinktext"));

        performPageAction("wait", aboutUs2PrelemDesc, "visible", "");
        performPageAction("softAssertion", aboutUs2PrelemDesc, "equals", ConfigFileReader.getProperty(EnvParameters.prelemPropFilePath, "aboutus2.prelem.preview.desc"));

        performPageAction("wait", aboutUs2PrelemCtaButton, "clickable", "");
        performPageAction("softAssertion", aboutUs2PrelemCtaButton, "equals", ConfigFileReader.getProperty(EnvParameters.prelemPropFilePath, "aboutus2.prelem.preview.cta.button1"));

        performPageAction("wait", aboutUs2PrelemMainImage, "visible", "");
        softAssert().assertEquals(aboutUs2PrelemMainImage.getAttribute("src"), ConfigFileReader.getProperty(EnvParameters.prelemPropFilePath, "aboutus2.prelem.preview.image"));

        performPageAction("wait", aboutUs2PrelemImageSecondaryHeading, "visible", "");
        performPageAction("softAssertion", aboutUs2PrelemImageSecondaryHeading, "equals", ConfigFileReader.getProperty(EnvParameters.prelemPropFilePath, "aboutus2.prelem.preview.secondary.heading"));

        performPageAction("wait", aboutUs2PrelemImageFirstFrame, "visible", "");
        performPageAction("softAssertion", aboutUs2PrelemImageFirstFrame, "assertTrue", "");

        performPageAction("wait", aboutUs2PrelemImageSecondFrame, "visible", "");
        performPageAction("softAssertion", aboutUs2PrelemImageSecondFrame, "assertTrue", "");

        performPageAction("wait", aboutUs2PrelemImageThirdFrame, "visible", "");
        performPageAction("softAssertion", aboutUs2PrelemImageThirdFrame, "assertTrue", "");

        clickOnAddPrelemButton();
        log.info("About Us 2 prelem preview verified and Add Prelem button clicked successfully");
    }

    protected void verifyImageVideoCarousel1PrelemPreviewPage(String expectedPrelemName, String expectedPrelemDesc) {
        verifyPrelemDetailsOnHover(expectedPrelemName, expectedPrelemDesc);
        List<String> expectedLeftSideBarHeadings = new ArrayList<>(Arrays.asList("Product picture", "Eiffel Tower picture", "Jerusalem picture", "Nature", "Celebration", "Mercedes-Benz"));
        List<WebElement> actualLeftSideBarHeadings = findElements(By.xpath("//div[contains(@class,'image-video-carousel1')]//div[contains(@class,'leftSideBarWrapper')]//h3"));

        waitForSeconds(10);
        performPageAction("wait", carousel1PrelemHeading, "visible", "");
        performPageAction("softAssertion", carousel1PrelemHeading, "equals", ConfigFileReader.getProperty(EnvParameters.prelemPropFilePath, "carousel1.prelem.preview.heading"));

        performPageAction("wait", carousel1PrelemDesc, "visible", "");
        performPageAction("softAssertion", carousel1PrelemDesc, "equals", ConfigFileReader.getProperty(EnvParameters.prelemPropFilePath, "carousel1.prelem.preview.desc"));

        if (expectedLeftSideBarHeadings.size() == actualLeftSideBarHeadings.size()) {
            int index = 0;
            for (WebElement actualHeading : actualLeftSideBarHeadings) {
                performPageAction("wait", actualHeading, "visible", "");
                if (expectedLeftSideBarHeadings.contains(getElementText(actualHeading))) {
                    performPageAction("softAssertion", actualHeading, "equals", expectedLeftSideBarHeadings.get(index));
                    log.info(getElementText(actualHeading) + " verified successfully.");
                } else {
                    performPageAction("softAssertion", actualHeading, "fail", "Expected and Actual side bar heading not matched!!!");
                    log.error("Expected and Actual side bar heading not matched!!!");
                }
                index++;
            }
        } else {
            performPageAction("softAssertion", carousel1PrelemHeading, "fail", "Expected and Actual side bar heading size not equal!!!");
            log.error("Expected and Actual side bar heading size not equal!!!");
        }
        performPageAction("wait", carousel1PrelemMainImage, "visible", "");
        softAssert().assertEquals(carousel1PrelemMainImage.getAttribute("src"), ConfigFileReader.getProperty(EnvParameters.prelemPropFilePath, "carousel1.prelem.preview.image"));
        clickOnAddPrelemButton();
        log.info("Image/Video Carousel 1 prelem preview verified and Add Prelem button clicked successfully");
    }
}
