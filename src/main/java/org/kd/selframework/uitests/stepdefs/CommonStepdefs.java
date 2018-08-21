package org.kd.selframework.uitests.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.kd.selframework.core.pageobjects.Page;
import org.kd.selframework.core.utils.WebDriverSingleton;
import org.kd.selframework.uitests.appundertest.PO_CheckboxDemoPage;
import org.kd.selframework.uitests.appundertest.PO_MainPage;
import org.kd.selframework.uitests.appundertest.PO_RadioButtonDemoPage;
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

    public CommonStepdefs() {
        pagenamePageobjectMap.put("index", new PO_MainPage(driver));
        pagenamePageobjectMap.put("Input Forms", new PO_SimpleFormPage(driver));
        pagenamePageobjectMap.put("Checkbox Demo", new PO_CheckboxDemoPage(driver));
        pagenamePageobjectMap.put("Radio Button Demo", new PO_RadioButtonDemoPage(driver));
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
