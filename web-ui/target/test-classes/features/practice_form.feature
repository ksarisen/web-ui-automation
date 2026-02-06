Feature: Practice Form Validation

  Scenario: All fields are filled correctly
    Given user navigates to Practice Form page
    When user fills all fields with valid data
    And user submits the form
    Then all submitted form data should be displayed correctly

  Scenario: Only mandatory fields are filled
    Given user navigates to Practice Form page
    When user fills only mandatory fields
    And user submits the form
    Then only mandatory form data should be displayed correctly
