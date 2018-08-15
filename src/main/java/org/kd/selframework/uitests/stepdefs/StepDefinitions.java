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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StepDefinitions {

    private final WebDriver driver = WebDriverFactory.createDriverDefinedInConfig();
    private final PO_Index indexPage = new PO_Index(driver);
    private final PO_InputForms inputFormsPage = new PO_InputForms(driver);

    private final Map<String, Page> pagenamePageobjectMap = new Hashtable<>();

    public StepDefinitions() {
        pagenamePageobjectMap.put("index", indexPage);
        pagenamePageobjectMap.put("Input Forms", inputFormsPage);
    }
    /*
        Common
     */

    @Given("^I navigate to (.*) site$")
    public void goToSite(String siteName) {
        Page site = pagenamePageobjectMap.get(siteName);

        site.navigateTo();
        site.load();
        site.findElements();
    }

    /*
        For Index page - MainPage
     */

    @Then("^I expect the page title is (.*)$")
    public void checkPageTitle(String expectedTitle) {
        assertEquals(expectedTitle, indexPage.getTitle());
    }

    @Then("^I expect Menu List is available$")
    public void checkMenuListAvailability() {
        assertNotNull(indexPage.getTreeMenu());
    }

    @Then("^I expect Menu List is visible")
    public void checkMenuListVisibility() {
        assertTrue(indexPage.getTreeMenuVisibility());
    }

    @Then("^I expect (.*) items in Menu List$")
    public void checkItemsCountInMenuList(int itemsCount) {
        assertThat(itemsCount, is(equalTo(indexPage.readMenuListItems().size())));
    }

    @Then("^I expect item (.*) to be available$")
    public void checkIfItemIsContained(String itemName){
        assertTrue(indexPage.readMenuListItems().contains(itemName));
    }

    @Then("^I expect item (.*) to be visible$")
    public void checkIfItemIsVisible(String itemName){
        assertTrue(indexPage.isItemVisible(itemName));
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
        indexPage.getDriver().close();
    }
}
