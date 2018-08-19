package org.kd.selframework.uitests.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.kd.selframework.core.exceptions.SiteNotOpenedException;
import org.kd.selframework.core.lib.TestLoggerSingleton;
import org.kd.selframework.core.lib.TestLogger;
import org.kd.selframework.uitests.appundertest.PO_MainPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MainPageStepdefs {

    private final WebDriver driver = WebDriverSingleton.getInstance();
    private final PO_MainPage indexPage = new PO_MainPage(driver);
    private final TestLogger logger = TestLoggerSingleton.getInstance();

    @Then("^I expect to navigate to (.*)$")
    public void checkPageUrl(String expectedUrl) {
        try {
            indexPage.waitForPageLoaded();
        } catch (SiteNotOpenedException siteNotOpenedException) {
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
    }

    @Then("I expect subitems (.*) to be visible")
    public void checkSubItems(List<String> expectedSubitems) {
        Assert.assertTrue(indexPage.readExamplesMenuListItems().containsAll(expectedSubitems));
    }

    @After
    public void tearDown() {
        WebDriverSingleton.close();
    }
}
