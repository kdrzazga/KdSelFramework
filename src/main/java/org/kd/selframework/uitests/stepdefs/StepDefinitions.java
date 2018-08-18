package org.kd.selframework.uitests.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.kd.selframework.core.exceptions.SiteNotOpenedException;
import org.kd.selframework.core.utils.WebDriverFactory;
import org.kd.selframework.core.lib.TestLogger;
import org.kd.selframework.core.pageobjects.Page;
import org.kd.selframework.uitests.appundertest.PO_Index;
import org.kd.selframework.uitests.appundertest.PO_InputForms;
import org.openqa.selenium.WebDriver;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StepDefinitions {

    private final WebDriver driver = WebDriverFactory.createDriverDefinedInConfig();
    private final PO_Index indexPage = new PO_Index(driver);
    private final PO_InputForms inputFormsPage = new PO_InputForms(driver);

    private String currentlyChosenMenuItem;

    private final Map<String, Page> pagenamePageobjectMap = new Hashtable<>();

    private final TestLogger logger = new TestLogger();

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

    @Then("^I expect the page title is (.*)$")
    public void checkPageTitle(String expectedTitle) {
        assertThat(expectedTitle, is(equalTo(driver.getTitle())));
    }

    @Then("^I expect to navigate to (.*)$")
    public void checkPageUrl(String expectedUrl) {
        try {
            indexPage.waitForPageLoaded();
        }

        catch (SiteNotOpenedException siteNotOpenedException){
            logger.error("Couldn't navigate to " + expectedUrl);
        }
        assertThat(expectedUrl, is(equalTo(driver.getCurrentUrl())));
    }

    /*
        For Index page - MainPage
     */

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
        assertThat(itemsCount, is(equalTo(indexPage.readExamplesMenuListItems().size())));
    }

    @Then("^I expect item (.*) to be available$")
    public void checkIfItemIsContained(String itemName) {
        assertTrue(indexPage.readExamplesMenuListItems().contains(itemName));
    }

    @Then("^I expect item (.*) to be visible$")
    public void checkIfItemIsVisible(String itemName) {
        assertTrue(indexPage.isItemVisible(itemName));
    }

    @When("^I unfold (.*) side menu$")
    public void unfoldInputForms(String itemName) {
        clickSideMenuItem(itemName);
    }

    @When("^I click (.*) item$")
    public void clickSideMenuItem(String itemName) {
        indexPage.clickItemInExamplesMenuList(itemName);
        currentlyChosenMenuItem = itemName;
    }

    @Then("I expect subitems (.*) to be visible")
    public void checkSubItems(List<String> expectedSubitems){
        List<String> actualSubtems = indexPage.readSubItemsOfMenuItem(currentlyChosenMenuItem);
        Assert.assertThat(expectedSubitems, containsInAnyOrder(actualSubtems));
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
