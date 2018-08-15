package org.kd.selframework.uitests.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.kd.selframework.core.general.WebDriverFactory;
import org.kd.selframework.core.pageobjects.Page;
import org.kd.selframework.uitests.appundertest.PO_Index;
import org.kd.selframework.uitests.appundertest.PO_InputForms;
import org.openqa.selenium.WebDriver;

import java.util.Hashtable;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    private final WebDriver driver = WebDriverFactory.createDriverDefinedInConfig();
    private final PO_Index indexPage = new PO_Index(driver);
    private final PO_InputForms inputFormsPage = new PO_InputForms(driver);

    private final Map<String, Page> pagenamePageobjectMap = new Hashtable<>();

    public StepDefinitions() {
        initPagenamePageobjectMap();
        initPageObjects();
    }

    private void initPageObjects() {
        indexPage.findElements();
        inputFormsPage.findElements();
    }

    private void initPagenamePageobjectMap() {
        pagenamePageobjectMap.put("index", indexPage);
        pagenamePageobjectMap.put("Input Forms", inputFormsPage);
    }

    @Given("^I navigate to (.*) site$")
    public void goToSite(String siteName) {
        pagenamePageobjectMap.get(siteName).navigateTo();
        pagenamePageobjectMap.get(siteName).waitForPageLoaded();
    }

    /*
        For Index page
     */

    @Then("^I expect the page title is (.*)$")
    public void checkPageTitle(String expectedTitle) {
        assertEquals(expectedTitle, indexPage.getTitle());
    }

    /*
        For Input Forms page
     */

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
        //indexPage.getDriver().close();
    }
}
