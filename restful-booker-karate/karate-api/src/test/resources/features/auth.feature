Feature: Authentication

  Scenario: Create auth token successfully
    Given url baseUrl + '/auth'
    And request
      """
      {
        "username": "admin",
        "password": "password123"
      }
      """
    When method POST
    Then status 200
    And match response.token != null
