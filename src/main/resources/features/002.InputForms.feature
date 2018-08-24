Feature: 2. InputForms

  Background: Open Input Form site

    Given I navigate to Input Forms site

  Scenario Outline: <TC id> Entering messages to Single Input Field

    When I enter text <entered text> to 'Enter message' textbox
    And I click Show Message button
    Then I expect <entered text> to be displayed as 'Your message'

    Examples:

      | TC id | entered text                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
      | 2.1.1 | drop table messages;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
      | 2.1.2 | window.alert('Java Script Inject')                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
      | 2.1.3 | Once upon a time there lived near a large wood a poor woodcutter, with his wife and two children by his former marriage, a little boy called Hansel, and a girl named Gretel. He had little enough to break or bite; and once, when there was a great famine in the land, he could not procure even his daily bread; and as he lay thinking in his bed one evening, rolling about for trouble, he sighed, and said to his wife, “What will become of us? How can we feed our children, when we have no more than we can eat ourselves?” |

  Scenario Outline: 2.2 Entering values to Two Input Fields

    When I enter a = <valueA> and b = <valueB> to textboxes
    And I click Get Total button
    Then I expect <total> to be displayed as 'Total'

    Examples:

      | TC id | valueA               | valueB               | total      |
      | 2.2.1 | 2                    | 2                    | 4          |
      | 2.2.2 | -5                   | 4                    | -1         |
      | 2.2.3 | 2.5                  | 2                    | 4          |
      | 2.2.4 | Litwo, Ojczyzno moja | 2                    | NaN        |
      | 2.2.5 | Tys jest jak zdrowie | Ile Cie trzeba cenic | NaN        |
      | 2.2.6 | !@#$%                | drop database;       | NaN        |
      | 2.2.7 | 2147483647           | 2147483648           | 4294967295 |
