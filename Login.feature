Feature: Login page

  Scenario Outline: Covering positive and negative scenario
    Given Open app URL
    When Enter <Username> and <Password>
    And  Click on the Sign in button
    Then Login results check <Results>
    Examples:
      | Username   | Password        | Results   |
      | "designer" | "Designer@123"  | "success" |
      | "designer" | "Designer@1234" | "error"   |

