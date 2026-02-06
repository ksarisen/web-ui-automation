Feature: Health check

  Scenario: Ping API should be healthy
    Given url baseUrl + '/ping'
    When method GET
    Then status 201
