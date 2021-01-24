Feature: As a user I want to be able to find an item

  Scenario: Open 4Ocean Site
    Given The browser is open
    When I enter in the URL http://www.4ocean.com and hit the enter key
    Then the 4Ocean Site should be open

  Scenario: Search for an Item by its name
    Given The browser is open
    When I enter in the URL http://www.4ocean.com and hit the enter key
    Then the 4Ocean Site should be open
    When I Select the Shop button
    Then the Shopping Page should open
    When I find Product "Stingray Beaded Bracelet"
    Then I highlight the Product

  Scenario: Find an Item by its name
    Given The browser is open
    When I enter in the URL http://www.4ocean.com and hit the enter key
    Then the 4Ocean Site should be open
    When I Select the Shop button
    Then the Shopping Page should open
    When I find Product "4ocean x To-Go Ware Bamboo Utensil Set"
    Then I highlight the Product

  Scenario Outline: Find Item <ProductName>
    Given The browser is open
    When I enter in the URL http://www.4ocean.com and hit the enter key
    Then the 4Ocean Site should be open
    When I Select the Shop button
    Then the Shopping Page should open
    When I find Item <ProductName>
    Then I highlight the Product

    Examples:
      |ProductName|
      | "4ocean x To-Go Ware Bamboo Utensil Set"  |
      | "Stingray Beaded Bracelet"               |
      | "Stingray 2-Pound Pack"                  |
      | "Stingray Braided Bracelet"              |
      | "4ocean On-The-Go Pack"                   |
