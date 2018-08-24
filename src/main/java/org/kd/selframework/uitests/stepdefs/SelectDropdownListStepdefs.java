package org.kd.selframework.uitests.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.kd.selframework.core.utils.WebDriverSingleton;
import org.kd.selframework.uitests.appundertest.PO_SelectDropdownListPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SelectDropdownListStepdefs {

    private final WebDriver driver = WebDriverSingleton.getInstance();
    private final PO_SelectDropdownListPage selectDropdownListPage = new PO_SelectDropdownListPage(driver);

    @When("^I select (.*) from Dropdown List$")
    public void selectFromDropdown(String weekday) {
        selectDropdownListPage.selectWeekday(weekday);
    }

    @Then("^I expect to see (.*) in message below DropdownList$")
    public void verifySelectedWeekday(String expectedWeekday) {
        assertEquals(expectedWeekday, selectDropdownListPage.readSelectedDay());
    }

    @When("^I choose (.*) in Multi Select List$")
    public void chooseStatesInMultiSelectList(List<String> states) {
        selectDropdownListPage.selectStatesInMultiSelectList(states);
    }

    @When("^I click (.*) button on Multi Select List Demo div$")
    public void clickButtonOnMultiSelectListDemo(String buttonCaption) {
        selectDropdownListPage.clickButtononMultiSelectListDemoDiv(buttonCaption);
    }

    @Then("^I expect message (.*) displayed underneath$")
    public void verifyMessageUnderneath(String expectedMessage) {
        assertEquals(expectedMessage, selectDropdownListPage.readSelectedStatesMesage());
    }

    @After
    public void tearDown() {
        WebDriverSingleton.close();
    }
}
