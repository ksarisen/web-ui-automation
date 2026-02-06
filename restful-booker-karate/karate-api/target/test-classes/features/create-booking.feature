Feature: Create booking

  Scenario: Create a new booking
    Given url baseUrl + '/booking'
    And request
      """
      {
        "firstname": "Kerem",
        "lastname": "Sarisen",
        "totalprice": 1500,
        "depositpaid": true,
        "bookingdates": {
          "checkin": "2024-01-01",
          "checkout": "2024-01-10"
        },
        "additionalneeds": "Breakfast"
      }
      """
    When method POST
    * assert responseStatus == 200 || responseStatus == 418
