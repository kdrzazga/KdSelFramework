Feature: 4. Radio Button Demo

  Background: Open Checkbox Demo site
    Given I navigate to Radio Button Demo site

  Scenario Outline: 4.1 Clicking Buttons without choosing any radio
    When I click <Button> button on Radio Button Demo site
    Then I expect text <Message> below <Button> button

    Examples:

      | TC id | Button             | Message                     |
      | 4.1.1 | Get Checked values | Radio button is Not checked |
      | 4.1.2 | Get values         | Sex :Age group:             |

  Scenario Outline: 4.2 Checking Radiobuttons in Radio Button Demo section
    When I check <Radiobutton> radiobutton in Radio Button Demo section
    And I click Get Checked values button on Radio Button Demo site
    Then I expect text <Message> below Get Checked values button

    Examples:

      | TC id | Radiobutton | Message                          |
      | 4.2.1 | Male        | Radio button 'Male' is checked   |
      | 4.2.2 | Female      | Radio button 'Female' is checked |

  Scenario Outline: 4.3 Checking Radiobuttons in Group Radio Buttons Demo section
    When I check <Sex> Radiobutton in Group Radio Buttons Demo section
    And I check <Age Group Input> Radiobutton in Group Radio Buttons Demo section
    And I click Get values button on Radio Button Demo site
    Then I expect message value for Sex is <Sex>
    And I expect message value for Age Group is <Age Group Output>

    Examples:

      | TC id | Sex    | Age Group Input | Age Group Output |
      | 4.3.1 | Male   | 0 to 5          | 0 - 5            |
      | 4.3.2 | Male   | 5 to 15         | 5 - 15           |
      | 4.3.3 | Male   | 15 to 50        | 15 - 50          |
      | 4.3.4 | Female | 0 to 5          | 0 - 5            |
      | 4.3.5 | Female | 5 to 15         | 5 - 15           |
      | 4.3.6 | Female | 15 to 50        | 15 - 50          |
