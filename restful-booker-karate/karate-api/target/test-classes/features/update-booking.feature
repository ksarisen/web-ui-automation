Feature: Update booking

  Scenario: Update an existing booking
    Given url baseUrl + '/auth'
    And request { username: 'admin', password: 'password123' }
    When method POST
    Then status 200
    * def token = response.token

    Given url baseUrl + '/booking'
    And request
      """
      {
        "firstname": "Temp",
        "lastname": "User",
        "totalprice": 100,
        "depositpaid": true,
        "bookingdates": {
          "checkin": "2024-01-01",
          "checkout": "2024-01-02"
        },
        "additionalneeds": "None"
      }
      """
    When method POST
    * assert responseStatus == 200 || responseStatus == 418

    * if (!response.bookingid) karate.abort()
    * def bookingId = response.bookingid

    Given url baseUrl + '/booking/' + bookingId
    And header Cookie = 'token=' + token
    And request { firstname: 'Updated', lastname: 'User' }
    When method PUT
    Then status 200
