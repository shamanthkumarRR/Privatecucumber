package com.platformX.web.cucumber;

import com.platformX.web.core.TestContext;
import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;

public class Hooks {

    private static final Logger log = Logger.getLogger(Hooks.class.getName());
    private static TestContext testContextSetup;

    /**
     * Setting context
     * @param testContextSetup
     */
    public Hooks(TestContext testContextSetup) {
        this.testContextSetup = testContextSetup;
    }

    /**
     * Run before any scenario is run
     */
    @BeforeAll
    public static void beforeAll() {
        PropertyConfigurator.configure("log4j.properties");
        log.info("*****************************************************************************************");
        log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Testcase Execution Started $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        log.info("*****************************************************************************************");
    }

    /**
     * Run after all scenarios have been executed
     */
    @AfterAll
    public static void AfterAll() {
        log.info("*****************************************************************************************");
        log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Testcase Execution Completed $$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        log.info("*****************************************************************************************");
    }

    /**
     * Run after each steps of scenarios if any failure occurred
     */
    @AfterStep
    public void addScreenshots(Scenario scenario) {
        WebDriver driver = testContextSetup.webBaseDriver.getDriver();
        if (scenario.isFailed()) {
            File sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
                scenario.attach(fileContent, "image/png", "image");
                log.info("Failure occurred!!!, Screenshot captured for failed scenario and attached with extent report");
            } catch (IOException exp) {
                log.error(exp.getMessage());
                exp.printStackTrace();
            }
        }
    }

    /**
     * Run before each scenario
     */
    @Before
    public void beforeScenario(Scenario scenario) {
        testContextSetup.resetSoftAssert();
        log.info("$$$$$$$$$$$$$$$$$$$$$$$$ Scenario " + "'" + scenario.getName() + "'" + " started $$$$$$$$$$$$$$$$$$$$");
    }

    /**
     * Run after each scenario
     */
    @After
    public void afterScenario(Scenario scenario) {
        testContextSetup.softAssert().assertAll();
        testContextSetup.webBaseDriver.getDriver().quit();
        log.info("$$$$$$$$$$$$$$$$ Scenario " + "'" + scenario.getName() + "'" + " completed successfully $$$$$$$$$$$$$");
    }
}
