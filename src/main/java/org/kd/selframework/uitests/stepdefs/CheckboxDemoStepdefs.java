package org.kd.selframework.uitests.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.kd.selframework.core.utils.WebDriverSingleton;
import org.kd.selframework.uitests.appundertest.PO_CheckboxDemoPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckboxDemoStepdefs {

    private final WebDriver driver = WebDriverSingleton.getInstance();
    private final PO_CheckboxDemoPage checkboxDemoPage = new PO_CheckboxDemoPage(driver);


    @When("^I click checkbox (.*)$")
    public void check(String checkboxCaption) {
        checkboxDemoPage.clickCheckbox(checkboxCaption);
    }

    @When("^I click checkboxes (.*)$")
    public void clickCheckboxes(List<String> checkboxCaptions) {
        checkboxCaptions.forEach(checkboxDemoPage::clickCheckbox);
    }

    @When("^I click button Check/Uncheck All$")
    public void clickCheckUncheckAllButton() {
        checkboxDemoPage.clickCheckUncheckAllButton();
    }

    @Then("^I expect text to be displayed below$")
    public void checkTextBelow() {
        assertTrue(checkboxDemoPage.isSingleCheckboxSubtextVisible());
    }

    @Then("^I expect button below has caption (.*)$")
    public void checkButtonCaption(String caption) {
        assertEquals(caption, checkboxDemoPage.getButtonCheckUncheckCaption());
    }

    @Then("^I expect checkboxes (.*) to be checked$")
    public void verifyCheckedCheckboxes(List<String> checkedCheckboxes) {
        checkedCheckboxes.forEach(chkboxCaption ->
                assertTrue("Fail for " + chkboxCaption, checkboxDemoPage.isCheckboxCheck(chkboxCaption)));
    }

    @After
    public void tearDown() {
        WebDriverSingleton.close();
    }
}
