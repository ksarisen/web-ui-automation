Feature: Alerts validation

  Scenario: Validate all alert popups
    Given user navigates to Alerts page
    When user clicks all alert buttons
    Then alert messages should be validated correctly
