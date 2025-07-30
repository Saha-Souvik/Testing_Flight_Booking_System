@Module2
Feature: Booking Module Functionality

  Background:
    Given user has logged in to the application using "flightadmin" and "flightadmin"
    And user is on the Booking Module page
    
  @Scenario1
  Scenario: Verify the Booking Module Required Fields with valid data
    When the user enters all required fields with valid data
    | Travel From   | Travel To     | Departure Date | Class     | Name       | Email              | Phone Number | Passengers |
    | Delhi         | Mumbai        | 30/08/2025     | Economy   | John Smith | john@example.com   | 9876543210   | 2          |
    And clicks the Book Now button
    Then the booking should be successful

  @Scenario2
  Scenario: Verify the Booking module with the Required Fields Blank
    When the user leaves all required fields blank
    And clicks the Book Now button
    Then an error message should be displayed for required fields

  @Scenario3
  Scenario: Verify the Booking Module Required Fields with Invalid Data
    When the user enters invalid data in required fields
    | Travel From | Travel To | Departure Date | Class   | Name       | Email           | Phone Number | Passengers |
    | $$$$        | @@@@      | invalid-date   | Select  | 12345      | invalid_email   | abcdef       | -1         |
    And clicks the Book Now button
    Then validation error messages should be displayed

  @Scenario4
  Scenario: Verify the Booking Module Book Now Button works
    And user has filled in all valid booking details
    | Travel From | Travel To | Departure Date | Class   | Name         | Email             | Phone Number | Passengers |
    | Chennai     | Bangalore | 15/09/2025     | Economy | Alice Watson | alice@mail.com    | 9876543211   | 3          |
    When the user clicks the Book Now button
    Then the booking should be confirmed

  @Scenario5
  Scenario: Verify the Booking Module Reset Button Works
    And the user has entered data in the booking form
    | Travel From | Travel To | Departure Date | Class   | Name     | Email            | Phone Number | Passengers |
    | Pune        | Goa       | 22/10/2025     | Business| Rahul M | rahul@email.com  | 9123456789   | 1          |
    When the user clicks the Reset button
    Then all fields in the form should be cleared