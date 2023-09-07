package com.platformX.web.stepDefinitions;

import com.platformX.web.core.TestContext;
import com.platformX.web.pageObjects.MyDashboardPage;
import io.cucumber.java.en.Then;

public class MyDashboardStepDefinition {

    public TestContext testContextSetup;
    private MyDashboardPage myDashboardPage;

    public MyDashboardStepDefinition(TestContext testContextSetup) {
        this.testContextSetup = testContextSetup;
        myDashboardPage = testContextSetup.pageObjectManager.getMyDashboardPageObj();
    }
    @Then("user verifies My Dashboard page on successful login with {string} and {string}")
    public void user_verifies_myDashboardPage(String expectedPageTitle, String expectedPageUrl) {
        myDashboardPage.verifyNavigatedDashboardsPage(expectedPageTitle, expectedPageUrl);
    }
}
