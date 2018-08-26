Tests for website:
http://www.seleniumeasy.com/test

Modify src/test/resources/application.properties with:
 path to your web drivers
 desired broswer name to run tests on

Develop your tests in uitests package. Place your Page Objects in appundertest subpackage.
Write cucumber scenarios in resources/features and code scenario steps in uitests.stepdefs

For best examples, please checkout and execute tests in the following branches:
selenium-easy-master
