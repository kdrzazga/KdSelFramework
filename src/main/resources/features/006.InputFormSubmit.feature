Feature: 6. Input Form Submit

  Background Outline: 6.1 Validation of entered data

    Given I navigate to Input Form Submit site
    When I enter a person <First Name>, <Last Name>
    And I enter address <Street>, <City>, <State>, <Zip Code>
    And I enter email <Email>
    And I enter phone <Phone>
    And I enter <website> website <hosting> for project <project>
    Then I expect all fields validated

  Examples:

  | TC id | First Name | Last Name | Street      | City      | State | Zip Code | Email                          | Phone         | website            | hosting      | project                |
  | 6.1.1 | Joseph     | Pilsudski | Oleandrow 5 | Sulejowek | daho  | 1918     | jozef.pilsudski@niepodlegla.pl | (011)011-1918 | www.niepodlegla.pl | with hosting | regaining independence |
     # | 6.1.2 | Al         | El        | `!%c@ ()    | @#$%      | Hawaii | 1918     | !@a.1                          | (077)4101037  | s1t3               | without hosting | Lithuania, my country! You are as good health: How much one should prize you, he only can tell Who has lost you. Your beauty and splendour I viewAnd describe here today, for I long after you.  ly Virgin who shelters our bright Częstochowa And shines in Ostra Brama! You, who yet watch over The castled Nowogródek’s folk faithful and mild; As You once had returned me to health, a sick child, (When by my weeping mother into Your care given, I by miracle opened a dead eye to heaven, And to Your temple’s threshold could straightaway falter For a life thus returned to thank God at the altar)e |