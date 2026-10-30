Feature: Login page

  Scenario Outline: Covering positive and negative scenario
    Given Open app URL
    When Enter <Username> and <Password>
    And  Click on the Sign in button
    Then Login results check <Results>
    Examples:
      | Username   | Results   | Password        |
      | "designer" | "success" | "Designer@123"  |
      | "designer" | "error"   | "Designer@1234" |

