package com.platformX.web.stepDefinitions;

import com.platformX.web.core.TestContext;
import com.platformX.web.pageObjects.UserRegistrationPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UsersRegistrationStepDefinition {

    private TestContext testContextSetup;
    private UserRegistrationPage userRegPage;

    public UsersRegistrationStepDefinition(TestContext testContextSetup) {
        this.testContextSetup = testContextSetup;
        userRegPage = testContextSetup.pageObjectManager.getUserRegistrationPageObj();
    }
    @When("user clicks on {string} sidebar sub-menu for {string} sidebar menu")
    public void userClicksOnSidebarSubMenuForSidebarMenu(String subMenu, String menu) {
        userRegPage.clickOnUserSideBarMenu(subMenu, menu);
    }
    @Then("user verifies redirected users management page with {string}, {string} and {string}")
    public void user_verifies_redirected_users_page(String expectedPageTitle, String expectedPageUrl, String expectedPageHeading) {
        userRegPage.verifyNavigationOnUserRegistrationPage(expectedPageTitle, expectedPageUrl, expectedPageHeading);
    }


    @When("user creates a new user")
    public void userCreatesANewUser() {
        userRegPage.createNewUser();
    }

    @Then("user verifies newly registered user on listing page")
    public void userVerifiesNewlyRegisteredUserOnListingPage() {
        userRegPage.verifyRegisteredUserDetailsOnListingPage();
    }

    @Then("user search and update existing user details")
    public void userSearchAndUpdateExistingUserDetails() {
        userRegPage.searchAndEditExistingUserDetails();
    }
}
