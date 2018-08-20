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

    @Then("^I expect text (.*) below (.*) button$")
    public void verifyTextBelowButton(String expectedText, String buttonName) {
        String actualText = (buttonName.equals("Get Checked values"))
                ? radioButtonDemoPage.getRadioButtonDemoMessage()
                : radioButtonDemoPage.getGroupRadioButtonsDemoMessage();

        assertEquals(actualText.replace("\n", "").replace("\r", ""), expectedText);
    }

    @After
    public void tearDown() {
        WebDriverSingleton.close();
    }
}
