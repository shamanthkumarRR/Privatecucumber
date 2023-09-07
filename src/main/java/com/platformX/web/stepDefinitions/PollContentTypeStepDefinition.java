package com.platformX.web.stepDefinitions;

import com.platformX.web.core.TestContext;
import com.platformX.web.pageObjects.PollContentPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PollContentTypeStepDefinition {

    private TestContext testContextSetup;
    private PollContentPage pollContentPage;
    public PollContentTypeStepDefinition(TestContext testContextSetup) {
        this.testContextSetup = testContextSetup;
        pollContentPage = testContextSetup.pageObjectManager.getPollPageObj();
    }

    @When("user clicks on {string} sidebar menu")
    public void user_Clicks_On_Sidebar_Menu(String sideBarMenu) {
        pollContentPage.clickOnSideBarMenu(sideBarMenu);
    }

    @Then("user verifies redirected poll page with {string}, {string} and {string}")
    public void user_verifies_redirected_poll_page(String expectedPageTitle, String expectedPageUrl, String expectedPageHeading) {
        pollContentPage.verifyNavigationOnPollPage(expectedPageTitle, expectedPageUrl, expectedPageHeading);
    }

    @When("user clicks on {string} button")
    public void userClicksOnButton(String buttonName) {
        pollContentPage.clickOnAddNewButton(buttonName);
    }

    @Then("user verifies redirected content create or edit page with {string}, {string}, and {string}")
    public void userVerifiesRedirectedContentCreateEditPageWithAnd(String expectedPageTitle, String expectedPageUrl, String expectedPageHeading) {
        pollContentPage.verifyNavigationOnContentEditPage(expectedPageTitle, expectedPageUrl, expectedPageHeading);
    }

    @Then("user fill all mandatory fields and saves new Poll")
    public void userFillAllMandatoryFieldsAndSavesNewPoll() {
        pollContentPage.savePollContent();
    }

    @Then("user verifies newly created Poll on listing page with status {string}")
    public void userVerifiesNewlyCreatedPollOnListingPageWithStatus(String pollStatus) {
        pollContentPage.verifyCreatedPollContent(pollStatus);
    }

    @When("user clicks on Poll edit icon")
    public void userClicksOnEditIcon() {
        pollContentPage.clickOnPollEditIcon();
    }
    @Then("user updates any existing field and publish the poll content")
    public void userUpdatesAnyExistingFieldAndPublishThePollContent() {
        pollContentPage.editAndPublishPollContent();
    }

    @Then("user verifies published Poll on listing page with status {string}")
    public void userVerifiesPublishedPollOnListingPageWithStatus(String pollStatus) {
        pollContentPage.verifyPublishedPollOnListingPage(pollStatus);
    }
    @When("user clicks on {string} action type for same poll and verifies published poll content in new browser tab")
    public void userClicksOnActionTypeForSamePoll(String actionType) {
        pollContentPage.verifyPublishedPollPage(actionType);
    }

    @Then("user deletes created content")
    public void userDeletesCreatedPoll() {
        pollContentPage.deleteContent();
    }


    @When("user search any existing {string} content type with substring {string}")
    public void userSearchAnyExistingContentTypeWithSubstring(String contentType, String subString) {
        pollContentPage.verifyAndPerformGlobalSearch(contentType, subString);
    }

    @Then("user clicks on edit icon for searched result, edit any existing field and republish the poll")
    public void userClicksOnEditIconForSearchedResultEditAnyExistingFieldAndRepublishTheContent() {
        pollContentPage.editAndPublishExistingPoll();
    }
}
