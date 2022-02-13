Feature: delete pet
  Scenario: delete pet
    Given I have pet
    When I send the delete request
    Then pet is deleted
