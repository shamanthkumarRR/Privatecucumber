Feature: Login to Platform-X application with different roles
  @AdminLogin
  Scenario: Login to application with Admin Role
    Given Launch platform X application
    When user login with valid "username.role.admin" and "password.role.admin"
    Then user verifies My Dashboard page on successful login with "dashboard.page.title" and "dashboard.page.relative.url"


