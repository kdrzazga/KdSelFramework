Feature: 4. Radio Button Demo

  Scenario Outline: 4.1 Clicking Buttons without choosing any radio
    Given I navigate to Radio Button Demo site
    When I click <Button> button on Radio Button Demo site
    Then I expect text <Message> below <Button> button

    Examples:

      | TC id | Button             | Message                     |
      | 4.1.1 | Get Checked values | Radio button is Not checked |
      | 4.1.2 | Get values         | Sex :Age group:             |

  Scenario Outline: 4.2 Clicking Buttons without various combinations
    Given I navigate to Radio Button Demo site

    #When I check <Radiobutton> radiobutton
    And I click <Button> button on Radio Button Demo site
    Then I expect text <Message> below <Button> button

    Examples:

      | TC id | Radiobutton | Button             | Message                     |
      | 4.2.1 |             | Get Checked values | Radio button is Not checked |
      | 4.2.2 |             | Get values         | Sex :Age group:             |

