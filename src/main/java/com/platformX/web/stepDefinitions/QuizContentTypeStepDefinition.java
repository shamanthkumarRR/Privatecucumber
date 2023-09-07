package com.platformX.web.stepDefinitions;

import com.platformX.web.core.TestContext;
import com.platformX.web.pageObjects.QuizContentPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class QuizContentTypeStepDefinition {

    private TestContext testContextSetup;
    private QuizContentPage quizContentPageObj;

    public QuizContentTypeStepDefinition(TestContext testContextSetup) {
        this.testContextSetup = testContextSetup;
        quizContentPageObj = testContextSetup.pageObjectManager.getQuizPageObj();
    }

    @Then("user verifies redirected quiz page with {string}, {string} and {string}")
    public void user_verifies_redirected_poll_page(String expectedPageTitle, String expectedPageUrl, String expectedPageHeading) {
        quizContentPageObj.verifyNavigationOnQuizPage(expectedPageTitle, expectedPageUrl, expectedPageHeading);
    }

    @Then("user fill all mandatory fields and saves new Quiz with {string}")
    public void userFillAllMandatoryFieldsAndSavesNewQuiz(String qusType) {
        quizContentPageObj.saveQuizContent(qusType);
    }

    @Then("user verifies newly created quiz on listing page with status {string}")
    public void userVerifiesNewlyCreatedQuizOnListingPageWithStatus(String quizStatus) {
        quizContentPageObj.verifyCreatedQuizContent(quizStatus);
    }

    @When("user clicks on Quiz edit icon")
    public void userClicksOnQuizEditIcon() {
        quizContentPageObj.clickOnQuizEditIcon();
    }

    @Then("user verifies published quiz on listing page with status {string}")
    public void userVerifiesPublishedQuizOnListingPageWithStatus(String quizStatus) {
        quizContentPageObj.verifyPublishedQuizOnListingPage(quizStatus);
    }

    @When("user clicks on {string} action type for same quiz and verifies published quiz content in new browser tab")
    public void userClicksOnActionTypeForSameQuizAndVerifiesPublishedQuizContentInNewBrowserTab(String actionType) {
        quizContentPageObj.verifyPublishedQuizPage(actionType);
    }

    @Then("user clicks on edit icon for searched result, edit any existing field and republish the quiz")
    public void userClicksOnEditIconForSearchedResultEditAnyExistingFieldAndRepublishTheQuiz() {
        quizContentPageObj.editAndPublishExistingQuiz();
    }

    @Then("user updates any existing field and publish the quiz content")
    public void userUpdatesAnyExistingFieldAndPublishTheQuizContent() {
        quizContentPageObj.editAndPublishQuizContent();
    }
}
