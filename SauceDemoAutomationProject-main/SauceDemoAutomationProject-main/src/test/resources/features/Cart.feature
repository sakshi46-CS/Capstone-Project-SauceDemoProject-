Feature: SauceDemo Cart Page

  Background:
    Given user is logged in with "standard_user" and "secret_sauce"

  Scenario: Verify cart page is displayed after adding a product
    When user adds a product to the cart
    And user clicks on the cart icon
    Then cart page should be displayed

  

