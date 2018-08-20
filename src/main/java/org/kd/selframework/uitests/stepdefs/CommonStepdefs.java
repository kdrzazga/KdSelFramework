package org.kd.selframework.uitests.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.kd.selframework.core.pageobjects.Page;
import org.kd.selframework.core.utils.WebDriverSingleton;
import org.kd.selframework.uitests.appundertest.PO_MainPage;
import org.kd.selframework.uitests.appundertest.PO_SimpleFormPage;
import org.openqa.selenium.WebDriver;

import java.util.Hashtable;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CommonStepdefs {

    private final Map<String, Page> pagenamePageobjectMap = new Hashtable<>();

    private final WebDriver driver = WebDriverSingleton.getInstance();

    private final PO_MainPage mainPage = new PO_MainPage(driver);
    private final PO_SimpleFormPage simpleFormPage = new PO_SimpleFormPage(driver);

    public CommonStepdefs() {
        pagenamePageobjectMap.put("index", mainPage);
        pagenamePageobjectMap.put("Input Forms", simpleFormPage);
    }

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

    @After
    public void tearDown() {
        WebDriverSingleton.close();
    }

}
