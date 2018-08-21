package org.kd.selframework.uitests.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.kd.selframework.core.utils.TestLogger;
import org.kd.selframework.core.utils.TestLoggerSingleton;
import org.kd.selframework.core.utils.WebDriverSingleton;
import org.kd.selframework.uitests.appundertest.PO_RadioButtonDemoPage;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class RadioButtonDemoStepdefs {

    private final WebDriver driver = WebDriverSingleton.getInstance();
    private final PO_RadioButtonDemoPage radioButtonDemoPage = new PO_RadioButtonDemoPage(driver);
    private final TestLogger logger = TestLoggerSingleton.getInstance();

    @When("^I click (.*) button on Radio Button Demo site$")
    public void clickButton(String buttonCaption) {
        radioButtonDemoPage.clickButton(buttonCaption);
    }

    @When("^I check (.*) radiobutton in Radio Button Demo section$")
    public void checkRadiobuttonInRadioButtonDemoSection(String radiobuttonNameCaption) {
        radioButtonDemoPage.clickRadioButtonDemoRadio(radiobuttonNameCaption);
    }

    @When("^I check (.*) Radiobutton in Group Radio Buttons Demo section$")
    public void checkRadiobuttonInGroupRaioButtonsDemo(String radiobutoonCaption) {
        radioButtonDemoPage.clickGroupRadioButtonDemoRadio(radiobutoonCaption);
    }

    @Then("^I expect text (.*) below (.*) button$")
    public void verifyTextBelowButton(String expectedText, String buttonCaption) {
        String actualText = (buttonCaption.equals("Get Checked values"))
                ? radioButtonDemoPage.getRadioButtonDemoMessage()
                : radioButtonDemoPage.getGroupRadioButtonsDemoMessage();

        assertEquals(actualText.replace("\n", "").replace("\r", ""), expectedText);
    }

    @Then("^I expect message value for Sex is (.*)")
    public void verifySex(String expectedMessageValue) {
        assertEquals(expectedMessageValue, radioButtonDemoPage.readSex());
    }

    @Then("^I expect message value for Age Group is (.*)")
    public void verifyAgeGroup(String expectedMessageValue) {
        assertEquals(expectedMessageValue, radioButtonDemoPage.readAgeGroup());
    }

    @After
    public void tearDown() {
        WebDriverSingleton.close();
    }
}
