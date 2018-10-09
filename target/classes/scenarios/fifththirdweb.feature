@web
Feature: Test FifthThird Browser login and Location

  @web
  Scenario: Validating web login
    Given I open the browser and enter URL
    And I click on login button on screen
    And I input <Accounts> and "Test@1234"
      | Accounts     |
      | Private Bank |
    And I click login
    Then I get an error message "User ID and password invalid." on screen
