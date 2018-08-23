package org.kd.selframework.uitests.stepdefs;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.kd.selframework.core.utils.TestLogger;
import org.kd.selframework.core.utils.TestLoggerSingleton;
import org.kd.selframework.core.utils.WebDriverSingleton;
import org.kd.selframework.uitests.appundertest.PO_InputFormSubmitPage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class InputFormSubmitStepdefs {

    private final WebDriver driver = WebDriverSingleton.getInstance();
    private final PO_InputFormSubmitPage inputFormSubmitPage = new PO_InputFormSubmitPage(driver);
    private final TestLogger logger = TestLoggerSingleton.getInstance();

    @Before
    public void maximizeSite() {
        driver.manage().window().setPosition(new Point(0, 0));
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
        driver.manage().window().setSize(dim);
    }

    @When("^I enter a person (.*), (.*)$")
    public void enterPerson(String firstName, String lastName) {
        inputFormSubmitPage.enterFirstName(firstName);
        inputFormSubmitPage.enterLastName(lastName);
    }

    @When("^I enter address (.*), (.*), (.*), (.*)$")
    public void enterAddress(String street, String city, String state, String zipCode) {
        inputFormSubmitPage.enterStreet(street);
        inputFormSubmitPage.enterCity(city);
        inputFormSubmitPage.enterState(state);
        inputFormSubmitPage.enterZip(zipCode);
    }

    @When("^I enter email (.*)$")
    public void enterEmail(String email) {
        inputFormSubmitPage.enterEmail(email);
    }

    @When("^I enter phone (.*)$")
    public void enterPhone(String phone) {
        inputFormSubmitPage.enterPhone(phone);
    }

    @When("^I enter (.*) website (.*) for project (.*)$")
    public void enterWebsiteForProject(String website, String withHosting, String projectDescription) {
        inputFormSubmitPage.enterWebsite(website);

        switch (withHosting.toLowerCase()) {
            case "with hosting":
                inputFormSubmitPage.setHosting(true);
                break;
            case "without hosting":
                inputFormSubmitPage.setHosting(false);
                break;
            default:
                logger.error("Wrong option for hosting. Available options: with hosting, without hosting");
                break;
        }

        inputFormSubmitPage.enterProjectDescription(projectDescription);
    }

    @Then("^I expect (.*) fields validated$")
    public void checkFieldsValidation(List<String> fieldsList) {

        if (fieldsList.contains("all") && fieldsList.size() == 1) {

        }

        PO_InputFormSubmitPage.getFeedbackBlocks();
    }
}
