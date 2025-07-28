@Module5
Feature: Logout Functionality

  # This scenario automates test case AG-T22 by verifying the known bug AG-28
  Scenario: To verify the logout button is mislabeled
    Given user has logged in to the application
    When user is on the home page
    Then the logout button should be mislabeled as "LOGIN"