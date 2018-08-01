@smoke
Feature: Test FifthThird App login and Location

  @smoke
  Scenario: Validating invalid user name and password error message
    Given I start the application by the name "Fifth Third"
    And I am using an AppiumDriver
    When I enter "PerfectoMOBTest" and "Test@1234"
    And I click on login button
    Then I get an error message "User ID and Password invalid."
    
    Scenario: Validate no internet connection error message
    Given User is in login page
    And there is no internet connection
    When I have enter password as "Test@1234"
    And I click on login button
    Then I get an no internet error message


