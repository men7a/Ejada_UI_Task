Feature: select the most expensive two products
  Scenario: add products to cart
    Given open the website and go to login page
    And enter "standard_user" and "secret_sauce"
    When select first two products
    And filter product from low to high
    Then check products added to cart and open cart
    And go to checkout page
    And go to finish page and check total price
    And go to thanks page