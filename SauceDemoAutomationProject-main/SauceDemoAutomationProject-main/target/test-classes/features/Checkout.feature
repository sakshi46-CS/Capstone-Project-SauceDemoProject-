Feature: Checkout functionality on SauceDemo

  Background:
    Given user is logged in with "standard_user" and "secret_sauce"

  @checkout_success
  Scenario: Complete checkout with valid data
    Given user adds "Sauce Labs Backpack" to the cart
    When user enters "John" "Doe" and "560001"
    And clicks continue
    And clicks finish
    Then order success message "Thank you for your order!" should be displayed

  @checkout_error_firstname
  Scenario: Checkout with empty first name
    Given user adds "Sauce Labs Backpack" to the cart
    When user enters "" "Doe" and "560001"
    And clicks continue
    Then error message "Error: First Name is required" should be displayed

  @checkout_error_lastname
  Scenario: Checkout with empty last name
    Given user adds "Sauce Labs Backpack" to the cart
    When user enters "John" "" and "560001"
    And clicks continue
    Then error message "Error: Last Name is required" should be displayed

  @checkout_error_postalcode
  Scenario: Checkout with empty postal code
    Given user adds "Sauce Labs Backpack" to the cart
    When user enters "John" "Doe" and ""
    And clicks continue
    Then error message "Error: Postal Code is required" should be displayed

  @checkout_cancel
  Scenario: Cancel checkout and return to cart
  Given user adds "Sauce Labs Backpack" to the cart
  When user enters "John" "Doe" and "560001"
  And user cancels checkout
  Then user should be redirected back to cart page


  @checkout_summary
  Scenario: Verify order summary before finishing checkout
    Given user adds "Sauce Labs Backpack" to the cart
    When user enters "John" "Doe" and "560001"
    And clicks continue
    Then order summary should display product "Sauce Labs Backpack"
