package org.kd.selframework.stepdefs;

import cucumber.api.java.en.Given;
import org.kd.selframework.pageobjects.PO_Index;

public class StepDefinitions {

    private final PO_Index indexPage;

    public StepDefinitions(){
        indexPage = new PO_Index("http://www.google.com");
    }

    @Given("^I navigate to index site$")
    public void goToIndex() {
        indexPage.navigateTo();
    }

}
