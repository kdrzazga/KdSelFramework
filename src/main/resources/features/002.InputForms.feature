Feature: 2. InputForms

  Scenario Outline: 2.1 Entering messages to Single Input Field

    Given I navigate to Input Forms site
    When I enter text <entered text> to 'Enter message' textbox
    And I click <string> button
    Then I expect <entered text> to be displayed as 'Your message'

    Examples:

      | TC id | entered text         |
      | 2.1.1 | drop table messages; |

  Scenario Outline: 2.2 Entering values to Two Input Fields

    Given I navigate to Input Forms site
    When I enter a = <valueA> and b = <valueB> to textboxes
    And I click <string> button
    Then I expect <total> to be displayed as 'Total'

    Examples:

      | TC id | valueA               | valueB               | total      |
      | 2.2.1 | 2                    | 2                    | 4          |
      | 2.2.2 | -2                   | 4                    | -1         |
      | 2.2.3 | 2.5                  | 2                    | 4          |
      | 2.2.4 | Litwo, Ojczyzno moja | 2                    | NaN        |
      | 2.2.5 | Litwo, Ojczyzno moja | Tys jest jak zdrowie | NaN        |
      | 2.2.6 | !@#$%                | drop database;       | NaN        |
      | 2.2.7 | 2147483647           | 2147483648           | 4294967295 |
