package org.kd.selframework.uitests.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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

    @Then("^I expect (.*) to be displayed below$")
    public void checkTextboxText(String message) {
        assertEquals(message, inputFormsPage.readDisplayedMessage());
    }

    @After
    public void tearDown() {
        WebDriverSingleton.close();
    }
}
