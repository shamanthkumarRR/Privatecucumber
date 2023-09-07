Feature: Verify functionality of User Management
  @userManagement
  Scenario: PX-UM-TC001 : Create New user and verify whether user can able to login to the Platform X application
    Given Launch platform X application
    When user login with valid "username.role.admin" and "password.role.admin"
    Then user verifies My Dashboard page on successful login with "dashboard.page.title" and "dashboard.page.relative.url"
    When user clicks on "user.side.bar.submenu" sidebar sub-menu for "user.side.bar.menu" sidebar menu
    Then user verifies redirected users management page with "user.page.title", "user.page.url" and "user.page.heading"
    When user creates a new user
    Then user verifies newly registered user on listing page

  @userManagement
  Scenario: PX-UM-TC002 : Update Existing user and verify whether user can able to login to the Platform X application
    Given Launch platform X application
    When user login with valid "username.role.admin" and "password.role.admin"
    Then user verifies My Dashboard page on successful login with "dashboard.page.title" and "dashboard.page.relative.url"
    When user clicks on "user.side.bar.submenu" sidebar sub-menu for "user.side.bar.menu" sidebar menu
    Then user verifies redirected users management page with "user.page.title", "user.page.url" and "user.page.heading"
    Then user search and update existing user details