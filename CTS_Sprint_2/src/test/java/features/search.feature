@Search
Feature: Flight Search Functionality

  Background:
    Given Start browser and navigate to flight search page after login

  Scenario: AG-T11 - To Verify search using valid flight number
    When Enter valid flight number
    Then Verify search results displayed

  Scenario: AG-T12 - To Verify search using invalid flight number
    When Enter invalid flight number
    Then Verify no results displayed
    And Not found text displayed

  Scenario: AG-T13 - To Verify search using valid flight name
    When Enter valid flight name
    Then Verify search results displayed

  Scenario: AG-T14 - To Verify search with empty flight number field
    When Enter empty flight number
    Then Verify no results displayed

  Scenario: AG-T15 - To Verify search using valid flight type
    When Enter valid flight type
    Then Verify search results displayed
