Feature: 5. Select Dropdown List

  Background: Open Select Dropdown List site
    Given I navigate to Select Dropdown List site

  Scenario Outline: <"TC id"> Choosing all weekdays in Select List Demo

    When I select <weekday> from Dropdown List
    Then I expect to see <weekday> in message below DropdownList

    Examples:

      | TC id | weekday   |
      | 5.1.1 | Tuesday   |
      | 5.1.2 | Monday    |
      | 5.1.3 | Friday    |
      | 5.1.4 | Wednesday |
      | 5.1.5 | Sunday    |
      | 5.1.6 | Thursday  |
      | 5.1.7 | Saturday  |

  Scenario Outline: <TC id> Checking first selected item in Multi Select List

    When I choose <States to Select> in Multi Select List
    And I click First Selected button on Multi Select List Demo div
    Then I expect message First selected option is : <Message with First Selected> displayed underneath

    Examples:
      | TC id | States to Select                      | Message with First Selected |
      | 5.2.1 |                                       | undefined                   |
      | 5.2.2 | Washington                            | Washington                  |
      | 5.2.3 | Pennsylvania, Ohio, New York, Florida | Florida                     |
      #Florida is highest on the list

  Scenario Outline: <"TC id"> Checking all selected items in Multi Select List

    When I choose <States to Select> in Multi Select List
    And I click Get All Selected button on Multi Select List Demo div
    Then I expect message Options selected are :<Message with Get All Options> displayed underneath

    Examples:
      | TC id | States to Select                      | Message with Get All Options          |
      | 5.3.1 |                                       |                                       |
      | 5.3.2 | Washington                            | Washington                            |
      | 5.3.3 | Pennsylvania, Ohio, New York, Florida | Pennsylvania, Ohio, New York, Florida |
