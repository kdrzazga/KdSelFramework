package org.kd.selframework.uitests.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.kd.selframework.core.utils.WebDriverSingleton;
import org.kd.selframework.uitests.appundertest.PO_InputForms;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class InputFormsStepdefs {

    private final WebDriver driver = WebDriverSingleton.getInstance();
    private final PO_InputForms inputFormsPage = new PO_InputForms(driver);

    @When("^I enter text (.*) to 'Enter message' textbox$")
    public void enterTextToTextBox(String message) {
        inputFormsPage.enterMessage(message);
    }

    @When("^I click (.*) button$")
    public void clickButton(String buttonCaption) {
        inputFormsPage.pressButton(buttonCaption);
    }

    @When("^I enter a = (.*) and b = (.*) to textboxes$")
    public void clickButton(String valueA, String valueB) {
        inputFormsPage.enterValuesAandB(valueA, valueB);
    }

    @Then("^I expect (.*) to be displayed as 'Your message'$")
    public void checkDisplayedMessage(String message) {
        assertEquals(message, inputFormsPage.readDisplayedMessage());
    }

    @Then("^I expect (.*) to be displayed as 'Total'$")
    public void checkTotalResult(String result) {
        assertEquals(result, inputFormsPage.readTotalResult());
    }

    @After
    public void tearDown() {
        WebDriverSingleton.close();
    }
}
