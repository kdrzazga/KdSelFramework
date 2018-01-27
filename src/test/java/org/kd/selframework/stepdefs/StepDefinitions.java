package org.kd.selframework.stepdefs;

import cucumber.api.java.en.Given;
import org.kd.selframework.general.WebDriverFactory;
import org.kd.selframework.pageobjects.PO_Index;

public class StepDefinitions {

    private final PO_Index indexPage = new PO_Index(WebDriverFactory.createChromeDriver(), "http://www.google.com");

    public StepDefinitions() {
    }

    @Given("^I navigate to index site$")
    public void goToIndex() {
        indexPage.navigateTo();
    }

}
