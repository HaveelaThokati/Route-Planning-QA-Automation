Feature: Origin And Destination Feature

  Scenario Outline: Validating the Origin and Destination Functionality

    Given Open app URL
    When Enter <Username> and <Password>
    And  Click on the Sign in button
    Then Click on Generate New Layout button
    Then Configure the Route <routeName>
    And Click on the Create button
    Then Verify the Enter Origin and destination screen
    Then Enter the Coordinates <origin> <destination> <intermediatePoints>
    And Click on the Next Button and validate the toaster <toaster>

    Examples:

      | Username   | Password       | routeName | origin                          | destination                      | intermediatePoints                           | toaster                                                             |
      | "designer" | "Designer@123" | "Route-1" | "Dhamtari, Chhattisgarh, India" | "Kondagaon, Chhattisgarh, India" | "Kanker, Kanker Tahsil, Chhattisgarh, India" | "Origin and destination saved successfully."                        |
      | "designer" | "Designer@123" | "Route-2" | "Dhamtari, Chhattisgarh, India" | "Kondagaon, Chhattisgarh, India" | ""                                           | "Origin and destination saved successfully."                        |
      | "designer" | "Designer@123" | "Route-3" | ""                              | "Dhamtari, Chhattisgarh, India"  | ""                                           | "Please select both an origin and a destination before proceeding." |
      | "designer" | "Designer@123" | "Route-4" | "Dhamtari, Chhattisgarh, India" | ""                               | ""                                           | "Please select both an origin and a destination before proceeding." |