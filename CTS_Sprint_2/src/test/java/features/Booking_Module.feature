@Module2
Feature: Booking Module Functionality

  @Scenario1
  Scenario: Verify the Booking Module Required Fields with valid data
    Given the user is on the Booking Module page
    When the user enters all required fields with valid data
    And clicks the Submit button
    Then the booking should be successful

  @Scenario2
  Scenario: Verify the Booking module with the Required Fields Blank
    Given the user is on the Booking Module page
    When the user leaves all required fields blank
    And clicks the Submit button
    Then an error message should be displayed for required fields

  @Scenario3
  Scenario: Verify the Booking Module Required Fields with Invalid Data
    Given the user is on the Booking Module page
    When the user enters invalid data in required fields
    And clicks the Submit button
    Then validation error messages should be displayed

  @Scenario4
  Scenario: Verify the Booking Module Book Now Button works
    Given the user has filled in all valid booking details
    When the user clicks the Book Now button
    Then the booking should be confirmed

  @Scenario5
  Scenario: Verify the Booking Module Reset Button Works
    Given the user has entered data in the booking form
    When the user clicks the Reset button
    Then all fields in the form should be cleared