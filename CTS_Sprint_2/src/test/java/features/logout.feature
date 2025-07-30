@Logout
Feature: Logout Functionality

  # This scenario automates test case AG-T22.
  Scenario: To verify the correct logout functionality
    Given user has logged in to the application with "flightadmin" and "flightadmin"
    When user is on the home page
    Then the user should see a "Logout" button
    And user clicks on the button
    Then it should redirect to login page