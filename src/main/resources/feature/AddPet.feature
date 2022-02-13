Feature: Add pet to petStore with POST API


  @addPet @positive
  Scenario: : Add pet to pet store with all valid data
    Given I have valid URL with all valid data
    When I send the POST request with valid header
    Then I validate the successful response