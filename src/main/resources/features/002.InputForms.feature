Feature: 2. InputForms

  Scenario Outline: Entering messages to Single Input Field

    Given I navigate to Input Forms site
    When I enter text <entered text> to 'Enter message' textbox
    Then I expect <entered text> to be displayed below

    Examples:

      | entered text         |
      | drop table messages; |
