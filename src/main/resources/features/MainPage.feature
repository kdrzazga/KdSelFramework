Feature: Main page

  Scenario: Go to main page
    When I navigate to index site
    Then I expect the page title is Selenium Easy - Best Demo website to practice Selenium Webdriver Online

  Scenario: Navigate to Input Form site from link
    Given I navigate to index site
    When I unfold Input Forms side menu
    And I click Simple Form Demo item
    Then I expect to navigate to http://www.seleniumeasy.com/test/basic-first-form-demo.html
