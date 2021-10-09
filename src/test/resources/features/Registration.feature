Feature:Registration Feature

  @smoke
  Scenario: User must be able to registered in the application
    Given User open the browser
    When User navigate to the url and click on register
    And User have to fill registration form as per below list
      | FirstName       | Shraddha          |
      | LastName        | Meshram           |
      | Email           | shr2225@gmail.com |
      | Password        | NaviMumbai@123    |
      | ConfirmPassword | NaviMumbai@123    |
    And Click on Register button
    Then Message display your registration completed




