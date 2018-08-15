Feature: 1.Main page

  Scenario: 1.1 Go to main page
    When I navigate to index site
    Then I expect the page title is Selenium Easy - Best Demo website to practice Selenium Webdriver Online

  Scenario: 1.2 Check availability of left side menu
    When I navigate to index site
    Then I expect Menu List is available
    And I expect Menu List is visible
    And I expect 7 items in Menu List

  Scenario Outline: <TC id> Check visibility of left side menu items
    When I navigate to index site
    Then I expect item <Menu List Item> to be available
    And I expect item <Menu List Item> to be visible
    Examples:
      | TC id | Menu List Item          |
      | 1.3.1 | Input Forms             |
      | 1.3.2 | Date pickers            |
      | 1.3.3 | Table                   |
      | 1.3.4 | Progress Bars & Sliders |

#  Scenario: 1.4 Navigate to Input Form site from link
#    Given I navigate to index site
#    When I unfold Input Forms side menu
#    And I click Simple Form Demo item
#    Then I expect to navigate to http://www.seleniumeasy.com/test/basic-first-form-demo.html
