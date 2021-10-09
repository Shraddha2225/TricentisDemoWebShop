Feature: Login Feature

@smoke
  Scenario: User is able to login in the application
    Given User opened the browser
    When User navigate to the url and click on login link
    And User enter credentials Email as "shr2225@gmail.com" and password as "NaviMumbai@123"
    And user click login button
    Then User login successfully by their registered email


