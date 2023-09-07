package com.platformX.web.genericUtility;

import com.platformX.web.core.EnvParameters;
import com.platformX.web.core.ConfigFileReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class WebBaseDriver {

    public Logger log = Logger.getLogger(this.getClass().getName());
    public WebDriver driver;

    public String executionType = ConfigFileReader.getProperty(EnvParameters.globalPropFilePath, "execution");
    public String browser_property = ConfigFileReader.getProperty(EnvParameters.globalPropFilePath,"browser");
    public String browser_maven = System.getProperty("browser");
    public String browserType = browser_maven != null ? browser_maven : browser_property;
    //Run using command line : mvn test -Dbrowser=chrome


    public WebDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    public WebDriver createDriver() {
        log.info(executionType);
        switch (executionType) {
            case "Local":
                driver = createLocalDriver();
                break;
            case "Remote":
                driver = createRemoteDriver();
                break;
            default:
                log.error("Environment type not found!!!");
                log.info(executionType);
                break;
        }
        return driver;
    }

    public WebDriver createLocalDriver() {
        switch (browserType) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src//test//resources//chromedriver.exe");
                driver = new ChromeDriver();
                log.info("Chrome browser initialized");
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                log.info("Firefox browser initialized");
                break;
            case "Edge":
                driver = new EdgeDriver();
                log.info("Edge browser initialized");
                break;
            default:
                log.error("Browser type not found!!!");
                break;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
    }
}
