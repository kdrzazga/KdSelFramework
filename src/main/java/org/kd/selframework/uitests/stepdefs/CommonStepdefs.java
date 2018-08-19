package org.kd.selframework.uitests.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.kd.selframework.core.pageobjects.Page;
import org.kd.selframework.uitests.appundertest.PO_Index;
import org.kd.selframework.uitests.appundertest.PO_InputForms;
import org.openqa.selenium.WebDriver;

import java.util.Hashtable;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CommonStepdefs {

    private final Map<String, Page> pagenamePageobjectMap = new Hashtable<>();

    private final WebDriver driver = WebDriverSingleton.getInstance();

    private final PO_Index indexPage = new PO_Index(driver);
    private final PO_InputForms inputFormsPage = new PO_InputForms(driver);

    public CommonStepdefs() {
        pagenamePageobjectMap.put("index", indexPage);
        pagenamePageobjectMap.put("Input Forms", inputFormsPage);
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
