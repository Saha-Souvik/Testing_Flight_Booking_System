@Module4
Feature: Enquiry Form Validations

  Scenario Outline: Verify the Full Name field accepts valid input
    Given The enquiry form page is loaded with "flightadmin" and "flightadmin"
    When The user enters full name "<fullname>"
    Then The Full Name field should accept "<fullname>"

    Examples:
      | fullname     |
      | John Doe     |

  Scenario Outline: Verify invalid email format shows error
    Given The enquiry form page is loaded with "flightadmin" and "flightadmin"
    When The user enters email "<email>"
    And The user submits the enquiry form
    Then An error should appear for invalid email format

    Examples:
      | email        |
      | john.doe@    |

  Scenario Outline: Verify empty required fields show errors
    Given The enquiry form page is loaded with "flightadmin" and "flightadmin"
    When The user leaves required fields empty
    Then Error messages should be shown for missing fields

  Scenario Outline: Verify character limit for Message field
    Given The enquiry form page is loaded with "flightadmin" and "flightadmin"
    When The user enters message with length "<length>"
    Then The system should display character limit warning

    Examples:
      | length |
      | 551    |

  Scenario Outline: Verify Subject field required validation
    Given The enquiry form page is loaded with "flightadmin" and "flightadmin"
    When The user fills out Full Name, Email, and Message
    And Leaves Subject field as "<subject>"
    Then Error should be displayed for empty subject

    Examples:
      | subject |
      |         |
