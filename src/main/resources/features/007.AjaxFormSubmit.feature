Feature: 7. Ajax Form Submit

  Background: Open site Ajax Form Submit
    Given I navigate to Ajax Form Submit site

  Scenario Outline: <TC id> Submitting data
    When I enter a name <Name>
    And I enter a comment <Comment>
    And I click Submit button for Ajax Form Submit
    Then I expect Submit button to disappear
    And I expect Ajax Request is Processing! to appear underneath for Ajax Form Submit
    And Later I expect Form submited Successfully! message to appear underneath for Ajax Form Submit

    Examples:
      | TC id | Name                                                           | Comment                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
      | 7.1.1 | Charles Louis de Secondat, baron de La Brède et de Montesquieu | Laws, in their most general signification, are the necessary relations from the nature of things. In this sense all beings have their laws: the Deity His laws, the material world its laws, the intelligences superior to man their laws, the beasts their laws, man his laws. They who assert that a blind fatality produced the various effects we behold in this world talk very absurdly; for can anything be more unreasonable than to pretend that a blind fatality could be productive of intelligent beings? There is, then, a prime reason; and laws are the relations subsisting between t and different beings, and the relations of these to one another. God is related to the universe, as Creator and Preserver; the laws by which |
      | 7.1.2 | Unknown Sumerian                                               | GILGAMESH went abroad in the world, but he met with none who could withstand his arms till be came to   Uruk. But the men of Uruk muttered in their houses, ‘Gilgamesh sounds the tocsin for his amusement, his arrogance   has no bounds by day or night. No son is left with his father, for Gilgamesh takes them all, even the children; yet the   king should be a shepherd to his people. His lust leaves no virgin to her lover, neither the warrior's daughter nor the   wife of the noble; yet this is the shepherd of the city, wise, comely, and resolute.'                                                                                                                                                                              |
      | 7.1.3 | ~!@#$%^&*()_ZXCVBNM<>?                                         |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |

  Scenario: 7.2 Submit empty form
    When I click Submit button for Ajax Form Submit
    Then I expect Submit button to be visible
    And I expect Name textbox turn red
