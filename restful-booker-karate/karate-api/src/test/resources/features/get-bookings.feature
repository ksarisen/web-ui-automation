Feature: Get bookings

  Scenario: Get booking ids list
    Given url baseUrl + '/booking'
    When method GET
    Then status 200
    And match response == '#[]'
