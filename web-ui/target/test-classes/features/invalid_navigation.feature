Feature: Invalid menu navigation

  Scenario: Click on a non-existing menu item
    Given user navigates to Elements page
    When user tries to click a non-existing menu item
    Then operation should fail gracefully
