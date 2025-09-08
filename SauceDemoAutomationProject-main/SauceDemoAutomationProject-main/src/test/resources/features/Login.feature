Feature: SauceDemo Login

  Scenario: Successful login with valid credentials
    Given user is on the login page
    When user enters "standard_user" and "secret_sauce"
    And clicks login button
    Then user should see the product page

  Scenario: Login with invalid credentials
    Given user is on the login page
    When user enters "invalid_user" and "wrong_pass"
    And clicks login button
    Then an error message should be displayed
