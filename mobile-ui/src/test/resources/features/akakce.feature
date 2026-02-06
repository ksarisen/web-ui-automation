Feature: Akakce Mobile Shopping

  Scenario: Validate lowest price laptop flow
    Given user searches for Laptop
    When user applies filters
    And user sorts by lowest price
    And user selects 9th product
    Then prices should be equal and seller button visible

  # @intentionalFail
  # Scenario: Intentional failure to verify screenshot capture
  #   Given user is on Akakce home page
  #   Then intentionally fail the scenario
  
