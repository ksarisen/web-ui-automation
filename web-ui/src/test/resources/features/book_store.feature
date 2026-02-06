Feature: Book Store List Validation

  Scenario: Validate book list rows and details
    Given user navigates to Book Store page
    Then book table rows should be displayed correctly
    And book details should be listed in the report