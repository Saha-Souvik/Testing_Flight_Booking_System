@Login
Feature: Login Functionality

  Background:
    Given Start browser and navigate to login page

  Scenario Outline: To Verify login with valid credentials
    And user enters credentials to login
      | flightadmin | flightadmin |
    And user get the captcha generated and enters valid captcha code
    And click on login button
    Then it should display alert message and click ok

  Scenario Outline: To Verify login with invalid password
    And user enters valid username and invalid password
      | flightadmin | wrongpass |
    And user get the captcha generated and enters valid captcha code
    And click on login button
    Then it should display password error message

  Scenario Outline: To Verify login with invalid username
    And user enters invalid username and valid password
      | invalid | flightadmin |
    And user get the captcha generated and enters valid captcha code
    And click on login button
    Then it should display username error message

  Scenario Outline: To Verify login with empty fields
    And user leaves username and password fields empty
    And user get the captcha generated and enters valid captcha code
    And click on login button
    Then it should display username and password error messages

  Scenario Outline: To Verify Remember Me functionality
    And user enters credentials to login
      | flightadmin | flightadmin |
    And user get the captcha generated and enters valid captcha code
    And check the Remember Me checkbox and accept alerts 
    And click on login button
    Then it should display alert message and click ok
    And credentials should be remembered on next visit

  Scenario Outline: To Verify Forgot Password functionality
    And user clicks on the Forgot your password link
    Then it should redirect to the reset password page