Feature: Partial update booking

  Scenario: Partially update booking firstname
    Given url baseUrl + '/auth'
    And request { username: 'admin', password: 'password123' }
    When method POST
    Then status 200
    * def token = response.token

    Given url baseUrl + '/booking'
    And request
      """
      {
        "firstname": "Partial",
        "lastname": "Update",
        "totalprice": 300,
        "depositpaid": true,
        "bookingdates": {
          "checkin": "2024-03-01",
          "checkout": "2024-03-05"
        },
        "additionalneeds": "Dinner"
      }
      """
    When method POST
    * assert responseStatus == 200 || responseStatus == 418

    * if (!response.bookingid) karate.abort()
    * def bookingId = response.bookingid

    Given url baseUrl + '/booking/' + bookingId
    And header Cookie = 'token=' + token
    And request { firstname: 'Patched' }
    When method PATCH
    Then status 200
