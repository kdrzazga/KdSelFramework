package org.kd.selframework.uitests.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.kd.selframework.core.utils.WebDriverFactory;
import org.kd.selframework.uitests.appundertest.PO_Index;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    private final PO_Index indexPage = new PO_Index(WebDriverFactory.createDriverDefinedInConfig());

    public StepDefinitions() {
    }

    @Given("^I navigate to index site$")
    public void goToIndex() {
        indexPage.navigateTo();
    }

    @Then("^I expect the page title is (.*)$")
    public void checkPageTitle(String expectedTitle) {
        assertEquals(expectedTitle, indexPage.getTitle());
    }

    @After
    public void tearDown(){
        indexPage.getDriver().close();
    }
}
