package com.platformX.web.stepDefinitions;

import com.platformX.web.core.TestContext;
import com.platformX.web.pageObjects.ArticleContentPage;
import com.platformX.web.pageObjects.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginStepDefinition {
    private TestContext testContextSetup;
    private LoginPage loginPageObj;
    public LoginStepDefinition(TestContext testContextSetup) {
        this.testContextSetup = testContextSetup;
        loginPageObj = testContextSetup.pageObjectManager.getLoginPageObj();

    }

    @Given("Launch platform X application")
    public void launch_application_on_given_env() {
        loginPageObj.launchApp();
    }
    @When("user login with valid {string} and {string}")
    public void user_Login_With_ValidAnd(String username, String password) {
        loginPageObj.loginToPlatformX(username, password);
    }





}
