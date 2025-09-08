Feature: SauceDemo Product Page

  Scenario: Verify product page title
    Given user is logged in with "standard_user" and "secret_sauce"
    Then the product page title should be "Products"

  Scenario: Add product to cart
    Given user is logged in with "standard_user" and "secret_sauce"
    When user adds a product to the cart
    And user clicks on the cart icon
    Then cart page should be displayed
    And the cart should contain the product "Sauce Labs Backpack"
    And the cart badge count should be "1"
