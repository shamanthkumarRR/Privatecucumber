package com.platformX.web.core;

import com.platformX.web.genericUtility.WebBaseDriver;
import com.platformX.web.genericUtility.PageObjectManager;
import org.testng.asserts.SoftAssert;

public class TestContext {
    public WebBaseDriver webBaseDriver;
    public PageObjectManager pageObjectManager;
    public static SoftAssert sa = null;


    public TestContext() {
        webBaseDriver = new WebBaseDriver();
        pageObjectManager = new PageObjectManager(webBaseDriver.getDriver());
    }

    public static SoftAssert softAssert() {
        if (sa == null) {
            sa = new SoftAssert();
        }
        return sa;
    }

    public static void resetSoftAssert() {
        sa = null;
    }

}
