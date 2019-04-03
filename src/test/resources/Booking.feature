Feature: Hotel Booking

  Scenario Outline: Successful Hotel booking with valid inputs
    Given I am on Hotel booking Page
    When I enter Firstname as <first_name>
    And I enter Surname as <surname>
    And I enter Price as <price>
    And I enter Deposit as <deposit>
    And I enter Check in date as <check_in_date>
    And I enter Check out date as <check_out_date>
    Then I click on Save button
    And New Booking should be created with <first_name> and <surname> and <price> and <deposit> and <check_in_date> and <check_out_date>
    Then I delete created booking having details as <first_name> and <surname> and <price> and <deposit> and <check_in_date> and <check_out_date>
    Examples:
      | first_name | surname     | price | deposit | check_in_date | check_out_date |
      | test       | testsurname | 99.89 | false   | 2019-04-23    | 2019-04-28     |
      | ash        | gayk        | 100   | true    | 2019-05-14    | 2019-05-15     |

  Scenario Outline: Unsuccessful Hotel booking with invalid price inputs
    Given I am on Hotel booking Page
    When I enter Firstname as <first_name>
    And I enter Surname as <surname>
    And I enter Price as <price>
    And I enter Deposit as <deposit>
    And I enter Check in date as <check_in_date>
    And I enter Check out date as <check_out_date>
    And I click on Save button
    And New Booking should not be created with <first_name> and <surname> and <price> and <deposit> and <check_in_date> and <check_out_date>
    Examples:
      | first_name            | surname | price | deposit | check_in_date | check_out_date |
      | test                  | rete    | abc   | false   | 2019-04-23    | 2019-04-28     |
      | !~#@%$£"&*()\/?.,;:+_ |         |       | true    |               |                |




