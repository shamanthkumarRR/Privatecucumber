Feature: Verify functionality of Article type content
  @articleContentType
  Scenario: PX-ARTICLE-TC001 : Validate Article Landing page by creation of New Article & Publishing it
    Given Launch platform X application
    When user login with valid "username.role.admin" and "password.role.admin"
    Then user verifies My Dashboard page on successful login with "dashboard.page.title" and "dashboard.page.relative.url"
    When user clicks on "article.side.bar.menu" sidebar menu
    Then user verifies redirected article page with "article.page.title", "article.page.url" and "article.page.heading"
    When user clicks on "Add New" button
    Then user verifies redirected article create or edit page with "article.create.page.title", "article.create.page.url" and "article.create.page.heading"
    Then user fill all mandatory fields and saves new Article
    Then user verifies redirected article page with "article.page.title", "article.page.url" and "article.page.heading"
    Then user verifies newly created Article on listing page with status "content.status.draft"
    When user clicks on Article edit icon
    Then user verifies redirected article create or edit page with "article.edit.page.title", "article.edit.page.url" and "article.edit.page.heading"
    Then user updates any existing field and publish the article content
    Then user verifies redirected article page with "article.page.title", "article.page.url" and "article.page.heading"
    Then user verifies published Article on listing page with status "content.status.published"
    When user clicks on "content.action.type.view" action type for same article and verifies published article content in new browser tab
    Then user deletes created content

  @articleContentType
  Scenario: PX-ARTICLE-TC002 : Validate Article Landing page by updation of Existing Article & Publishing it
    Given Launch platform X application
    When user login with valid "username.role.admin" and "password.role.admin"
    Then user verifies My Dashboard page on successful login with "dashboard.page.title" and "dashboard.page.relative.url"
    When user clicks on "poll.side.bar.menu" sidebar menu
    Then user verifies redirected poll page with "poll.page.title", "poll.page.url" and "poll.page.heading"
    When user search any existing "poll.side.bar.menu" content type with substring "global.search.substring"
    Then user clicks on edit icon for searched result, edit any existing field and republish the poll
