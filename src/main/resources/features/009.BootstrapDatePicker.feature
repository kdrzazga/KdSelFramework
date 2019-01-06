Feature: 9. Bootstrap Date Picker

  Background: Open site Bootstrap Date Picker
    Given I navigate to Bootstrap Date Picker site

  Scenario: 9.1 Setting today's date
    When I select today's date in Calendar
    Then I expect today's date to be visible in Date Picker
