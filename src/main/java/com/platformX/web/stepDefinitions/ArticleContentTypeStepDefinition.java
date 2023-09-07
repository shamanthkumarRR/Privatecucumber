package com.platformX.web.stepDefinitions;

import com.platformX.web.core.TestContext;
import com.platformX.web.pageObjects.ArticleContentPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ArticleContentTypeStepDefinition {

    private TestContext testContextSetup;
    private ArticleContentPage articleContentPageObj;

    public ArticleContentTypeStepDefinition(TestContext testContextSetup) {
        this.testContextSetup = testContextSetup;
        articleContentPageObj = testContextSetup.pageObjectManager.getArticlePageObj();
    }

    @Then("user verifies redirected article page with {string}, {string} and {string}")
    public void user_verifies_redirected_poll_page(String expectedPageTitle, String expectedRelativeUrl, String expectedPageHeading) {
        articleContentPageObj.verifyNavigationOnArticlePage(expectedPageTitle, expectedRelativeUrl, expectedPageHeading);
    }

    @Then("user verifies redirected article create or edit page with {string}, {string} and {string}")
    public void userVerifiesRedirectedArticleCreateOrEditPageWithAnd(String expectedPageTitle, String expectedRelativeUrl, String pageType) {
        articleContentPageObj.verifyNavigationOnArticleEditPage(expectedPageTitle, expectedRelativeUrl,pageType);
    }

    @Then("user fill all mandatory fields and saves new Article")
    public void userFillAllMandatoryFieldsAndSavesNewArticle() {
        articleContentPageObj.saveArticleContent();
    }

    @Then("user verifies newly created Article on listing page with status {string}")
    public void userVerifiesNewlyCreatedArticleOnListingPageWithStatus(String articleStatus) {
        articleContentPageObj.verifyCreatedArticleContent(articleStatus);
    }

    @When("user clicks on Article edit icon")
    public void userClicksOnArticleEditIcon() {
        articleContentPageObj.clickOnArticleEditIcon();
    }

    @Then("user updates any existing field and publish the article content")
    public void userUpdatesAnyExistingFieldAndPublishTheArticleContent() {
        articleContentPageObj.editAndPublishArticleContent();
    }

    @Then("user verifies published Article on listing page with status {string}")
    public void userVerifiesPublishedArticleOnListingPageWithStatus(String articleStatus) {
        articleContentPageObj.verifyPublishedArticleOnListingPage(articleStatus);
    }

    @When("user clicks on {string} action type for same article and verifies published article content in new browser tab")
    public void userClicksOnActionTypeForSameArticleAndVerifiesPublishedArticleContentInNewBrowserTab(String actionType) {
        articleContentPageObj.verifyPublishedArticlePage(actionType);
    }


}
