Feature: 1.Main page

  Background: Open Main Page site

    When I navigate to index site

  Scenario: 1.1 Go to main page
    Then I expect the page title is Selenium Easy - Best Demo website to practice Selenium Webdriver Online

  Scenario: 1.2 Check availability of left side menu
    Then I expect Menu List is available
    And I expect Menu List is visible
    And I expect 7 items in Menu List

  Scenario Outline: <TC id> Check visibility of left side menu items
    Then I expect item <Menu List Item> to be available
    And I expect item <Menu List Item> to be visible
    Examples:
      | TC id | Menu List Item          |
      | 1.3.1 | Input Forms             |
      | 1.3.2 | Date pickers            |
      | 1.3.3 | Table                   |
      | 1.3.4 | Progress Bars & Sliders |

  Scenario Outline: <TC id> Check visibility of left side menu subitems
    And I unfold <Menu List Item> side menu
    Then I expect subitems <Menu List Subitem> to be visible

    Examples:
      | TC id | Menu List Item          | Menu List Subitem                                                                                                                      |
      | 1.4.1 | Input Forms             | Simple Form Demo, Checkbox Demo, Radio Buttons Demo, Select Dropdown List, Input Form Submit, Ajax Form Submit, JQuery Select dropdown |
      | 1.4.2 | Date pickers            | Bootstrap Date Picker, JQuery Date Picker                                                                                              |
      | 1.4.3 | Table                   | Table Pagination, Table Data Search, Table Filter, Table Sort & Search, Table Data Download                                            |
      | 1.4.4 | Progress Bars & Sliders | JQuery Download Progress bars, Bootstrap Progress bar, Drag & Drop Sliders                                                             |
