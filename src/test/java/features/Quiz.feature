Feature: Verify functionality of Quiz type content
  @quizContentType
  Scenario: PX-QUIZ-TC001 : Validate user can able to attempt the quiz in Landing page by creation of New quiz & Publishing it
    Given Launch platform X application
    When user login with valid "username.role.admin" and "password.role.admin"
    Then user verifies My Dashboard page on successful login with "dashboard.page.title" and "dashboard.page.relative.url"
    When user clicks on "quiz.side.bar.menu" sidebar menu
    Then user verifies redirected quiz page with "quiz.page.title", "quiz.page.url" and "quiz.page.heading"
    When user clicks on "Add New" button
    Then user verifies redirected content create or edit page with "quiz.create.page.title", "quiz.create.page.url", and "quiz.create.page.heading"
    Then user fill all mandatory fields and saves new Quiz with "quiz.qus.type"
    Then user verifies redirected quiz page with "quiz.page.title", "quiz.page.url" and "quiz.page.heading"
    Then user verifies newly created quiz on listing page with status "content.status.draft"
    When user clicks on Quiz edit icon
    Then user verifies redirected content create or edit page with "quiz.edit.page.title", "quiz.edit.page.url", and "quiz.edit.page.heading"
    Then user updates any existing field and publish the quiz content
    Then user verifies redirected quiz page with "quiz.page.title", "quiz.page.url" and "quiz.page.heading"
    Then user verifies published quiz on listing page with status "content.status.published"
    When user clicks on "content.action.type.view" action type for same quiz and verifies published quiz content in new browser tab
    Then user deletes created content

  @quizContentType
  Scenario: PX-QUIZ-TC002 : Validate user can able to attempt the quiz in Landing page by updation of Existing quiz & Publishing it
    Given Launch platform X application
    When user login with valid "username.role.admin" and "password.role.admin"
    Then user verifies My Dashboard page on successful login with "dashboard.page.title" and "dashboard.page.relative.url"
    When user clicks on "quiz.side.bar.menu" sidebar menu
    Then user verifies redirected quiz page with "quiz.page.title", "quiz.page.url" and "quiz.page.heading"
    When user search any existing "quiz.side.bar.menu" content type with substring "global.search.substring"
    Then user clicks on edit icon for searched result, edit any existing field and republish the quiz