@Module5
Feature: Logout Functionality

  # This scenario automates test case AG-T22 by verifying the known bug AG-28
  Scenario: To verify the logout button is mislabeled
    # CHANGE: Credentials are now passed as parameters to avoid hardcoding
    Given user has logged in to the application with "flightadmin" and "flightadmin"
    When user is on the home page
    Then the logout button should be mislabeled as "LOGIN"