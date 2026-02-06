Feature: Get booking by id

  Scenario: Get booking details by booking id
    Given url baseUrl + '/booking'
    And request
      """
      {
        "firstname": "Get",
        "lastname": "ById",
        "totalprice": 500,
        "depositpaid": true,
        "bookingdates": {
          "checkin": "2024-02-01",
          "checkout": "2024-02-05"
        },
        "additionalneeds": "Lunch"
      }
      """
    When method POST
    * assert responseStatus == 200 || responseStatus == 418

    * if (!response.bookingid) karate.abort()
    * def bookingId = response.bookingid

    Given url baseUrl + '/booking/' + bookingId
    When method GET
    Then status 200
