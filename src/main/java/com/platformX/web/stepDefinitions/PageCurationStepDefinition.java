package com.platformX.web.stepDefinitions;

import com.platformX.web.core.TestContext;
import com.platformX.web.pageObjects.PollContentPage;
import com.platformX.web.pageObjects.pageCuration.PageCuration;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PageCurationStepDefinition {

    private TestContext testContextSetup;
    private PageCuration pageCurationPageObj;

    public PageCurationStepDefinition(TestContext testContextSetup) {
        this.testContextSetup = testContextSetup;
        pageCurationPageObj = testContextSetup.pageObjectManager.getPageCurationObj();
    }

    @Then("user verifies redirected page-list page with {string}, {string} and {string}")
    public void userVerifiesRedirectedPageListPageWithAnd(String expectedPageTitle, String expectedPageUrl, String expectedPageHeading) {
        pageCurationPageObj.verifyRedirectionOnPageList(expectedPageTitle, expectedPageUrl, expectedPageHeading);
    }

    @Then("user verifies redirected page-curation edit page with {string}, {string}")
    public void userVerifiesRedirectedPageCurationEditPageWith(String expectedPageTitle, String expectedPageUrl) {
        pageCurationPageObj.verifyRedirectionOnPageCurationEditPage(expectedPageTitle, expectedPageUrl);
    }

    @Then("user verifies {string} details in page-curation edit page")
    public void userVerifiesDetailsInPageCurationEditPage(String expectedPageHeading) {
        pageCurationPageObj.verifyPageSettings(expectedPageHeading);
    }

    @When("user clicks on {string} icon to add prelems")
    public void userClicksOnIconToAddPrelems(String addPrelemIconText) {
        pageCurationPageObj.clickOnAddPrelemIcon(addPrelemIconText);
    }

    @Then("user verifies prelem-search page with {string}, {string} and {string}")
    public void userVerifiesPrelemSearchPage(String expectedPageTitle, String expectedPageUrl, String expectedPageHeading) {
        pageCurationPageObj.verifyRedirectionOnPrelemSearchPage(expectedPageTitle, expectedPageUrl, expectedPageHeading);
    }

    @Then("user verifies {string} and search {string} on prelem-search page")
    public void userVerifiesAndSearchOnPrelemSearchPage(String expectedPrelemCount, String prelemToBeSearch) {
        pageCurationPageObj.verifyPrelemCount(expectedPrelemCount);
    }
}
