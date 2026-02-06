Feature: Delete booking

  Scenario: Delete an existing booking
    Given url baseUrl + '/auth'
    And request { username: 'admin', password: 'password123' }
    When method POST
    Then status 200
    * def token = response.token

    Given url baseUrl + '/booking'
    And request
      """
      {
        "firstname": "Gidici",
        "lastname": "Hesap",
        "totalprice": 200,
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
    When method DELETE
    Then status 201
