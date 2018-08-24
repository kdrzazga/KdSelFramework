Feature: 3. Checkbox Demo

  Background: Open Checkbox Demo site
    Given I navigate to Checkbox Demo site

  Scenario: 3.1 Clicking Single Checkbox
    When I click checkbox Click on this check box
    Then I expect text to be displayed below

  Scenario Outline: 3.2 Selecting different sets of options to affect button caption
    When I click checkboxes <Checkboxes>
    Then I expect button below has caption <Button Caption>

    Examples:

      | TC id | Checkboxes                                       | Button Caption |
      | 3.2.1 | Option 1, Option 2, Option 3, Option 4           | Uncheck All    |
      | 3.2.2 | Option 1, Option 2, Option 3, Option 4, Option 1 | Check All      |
      | 3.2.3 |                                                  | Check All      |

  Scenario Outline: 3.3 Clicking Button to affect checkboxes
    Given I navigate to Checkbox Demo site
    When I click checkboxes <Checkboxes to be clicked>
    And I click button Check/Uncheck All
    Then I expect checkboxes <Checked Checkboxes> to be checked

    Examples:

      | TC id | Checkboxes to be clicked               | Checked Checkboxes                     |
      | 3.3.1 | Option 1, Option 2, Option 3, Option 4 |                                        |
      | 3.3.2 |                                        | Option 1, Option 2, Option 3, Option 4 |
      | 3.3.3 | Option 1, Option 3, Option 4           | Option 1, Option 2, Option 3, Option 4 |