Feature: 6. Input Form Submit

  Background: Open site Input Form Submit
    Given I navigate to Input Form Submit site

  Scenario Outline: 6.1 Filling the form with correct data

    When I enter a person <First Name>, <Last Name>
    And I enter address <Street>, <City>, <State>, <Zip Code>
    And I enter email <Email>
    And I enter phone <Phone>
    And I enter <website> website <hosting> for project <project>
    Then I expect all fields validated

    Examples:

      | TC id | First Name | Last Name | Street      | City      | State  | Zip Code | Email                          | Phone         | website            | hosting         | project                                                                                                                                                                                                  |
      | 6.1.1 | Joseph     | Pilsudski | Oleandrow 5 | Sulejowek | daho   | 1918     | jozef.pilsudski@niepodlegla.pl | (011)011-1918 | www.niepodlegla.pl | with hosting    | regaining independence                                                                                                                                                                                   |
      | 6.1.2 | Al         | El        | `!%c@ ()    | @#$%      | Hawaii | 1918     | !@a.1                          | (077)4101037  | s1t3               | without hosting | Lithuania, my country! You are as good health: How much one should prize you, he only can tell Who has lost you. Your beauty and splendour I view And describe here today, for I long after you.Holy Vir |

  Scenario: 6.2 Filling the form with incorrect data
    When I enter a person !, #
    And I enter address 2, ., Alaska, 1
    And I enter email @com
    And I enter phone 997
    And I enter |/ website with hosting for project 123456789
    Then I expect Please enter more than 2 characters, Please supply a valid email address, Please supply a vaild phone number with area code, Please enter more than 8 characters, Please enter more than 4 characters, Please supply a vaild zip code under one of fields

  Scenario: 6.3 Attempting to submit empty form
    When I submit the form
    Then I expect Please supply your first name, Please supply your last name, Please supply your email address, Please supply your phone number, Please supply your street address, Please supply your city, Please select your state, Please supply your zip code, Please supply a description of your project under one of fields
