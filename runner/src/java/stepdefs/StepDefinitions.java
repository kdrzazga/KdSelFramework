package stepdefs;

import cucumber.api.java.en.Given;
import pageobjects.POM_Index;

public class StepDefinitions {

    private final POM_Index indexPage;

    public StepDefinitions(){
        indexPage = new POM_Index("http://www.google.com");
    }

    @Given("^I navigate to index site")
    public void goToIndex() {
        indexPage.navigateTo();
    }

}
