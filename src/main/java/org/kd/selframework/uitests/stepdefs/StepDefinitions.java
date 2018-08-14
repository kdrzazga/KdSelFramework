package org.kd.selframework.uitests.stepdefs;

import cucumber.api.java.en.Given;
import org.kd.selframework.core.stepdefs.AbstractStepDefinitions;
import org.kd.selframework.uitests.google_site.PO_Index;
import org.kd.selframework.core.general.WebDriverFactory;

public class StepDefinitions extends AbstractStepDefinitions {
    private final PO_Index indexPage = new PO_Index(WebDriverFactory.createChromeDriver());

    public StepDefinitions() {
    }

    @Given("^I navigate to index site$")
    public void goToIndex() {
        indexPage.navigateTo();
    }

}
