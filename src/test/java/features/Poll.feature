Feature: Verify functionality of Poll type content
  @pollContentType
  Scenario: PX-POLLS-TC002 : Validate user can able to attempt the Poll in Landing page by creation of New poll [Background Color] & Publishing it
    Given Launch platform X application
    When user login with valid "username.role.admin" and "password.role.admin"
    Then user verifies My Dashboard page on successful login with "dashboard.page.title" and "dashboard.page.relative.url"
    When user clicks on "poll.side.bar.menu" sidebar menu
    Then user verifies redirected poll page with "poll.page.title", "poll.page.url" and "poll.page.heading"
    When user clicks on "Add New" button
    Then user verifies redirected content create or edit page with "poll.create.page.title", "poll.create.page.url", and "poll.create.page.heading"
    Then user fill all mandatory fields and saves new Poll
    Then user verifies redirected poll page with "poll.page.title", "poll.page.url" and "poll.page.heading"
    Then user verifies newly created Poll on listing page with status "content.status.draft"
    When user clicks on Poll edit icon
    Then user verifies redirected content create or edit page with "poll.edit.page.title", "poll.edit.page.url", and "poll.edit.page.heading"
    Then user updates any existing field and publish the poll content
    Then user verifies redirected poll page with "poll.page.title", "poll.page.url" and "poll.page.heading"
    Then user verifies published Poll on listing page with status "content.status.published"
    When user clicks on "content.action.type.view" action type for same poll and verifies published poll content in new browser tab
    Then user deletes created content

  @pollContentType
  Scenario: PX-POLLS-TC003 : Validate user can able to attempt the Poll in Landing page by updation of Existing poll [Background Image] & Publishing it
    Given Launch platform X application
    When user login with valid "username.role.admin" and "password.role.admin"
    Then user verifies My Dashboard page on successful login with "dashboard.page.title" and "dashboard.page.relative.url"
    When user clicks on "poll.side.bar.menu" sidebar menu
    Then user verifies redirected poll page with "poll.page.title", "poll.page.url" and "poll.page.heading"
    When user search any existing "poll.side.bar.menu" content type with substring "global.search.substring"
    Then user clicks on edit icon for searched result, edit any existing field and republish the poll