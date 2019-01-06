package org.kd.selframework.uitests.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.kd.selframework.core.exceptions.SiteNotOpenedException;
import org.kd.selframework.core.pageobjects.Page;
import org.kd.selframework.core.utils.TestLogger;
import org.kd.selframework.core.utils.TestLoggerSingleton;
import org.kd.selframework.core.utils.WebDriverSingleton;
import org.kd.selframework.core.utils.WindowUtils;
import org.kd.selframework.uitests.appundertest.*;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;


public class StepDefinitions {

    private final Map<String, Page> pagenamePageobjectMap = new Hashtable<>();

    private final WebDriver driver = WebDriverSingleton.getInstance();
    private final TestLogger logger = TestLoggerSingleton.getInstance();

    private final PO_CheckboxDemoPage checkboxDemoPage = new PO_CheckboxDemoPage(driver);
    private final PO_InputFormSubmitPage inputFormSubmitPage = new PO_InputFormSubmitPage(driver);
    private final PO_MainPage indexPage = new PO_MainPage(driver);
    private final PO_RadioButtonDemoPage radioButtonDemoPage = new PO_RadioButtonDemoPage(driver);
    private final PO_SelectDropdownListPage selectDropdownListPage = new PO_SelectDropdownListPage(driver);
    private final PO_SimpleFormPage inputFormsPage = new PO_SimpleFormPage(driver);
    private final PO_AjaxFormSubmitPage ajaxFormSubmitPage = new PO_AjaxFormSubmitPage(driver);
    private final PO_BootstrapDatePicker bootstrapDatePicker = new PO_BootstrapDatePicker(driver);


    public StepDefinitions() {
        pagenamePageobjectMap.put("index", new PO_MainPage(driver));
        pagenamePageobjectMap.put("Input Forms", new PO_SimpleFormPage(driver));
        pagenamePageobjectMap.put("Checkbox Demo", new PO_CheckboxDemoPage(driver));
        pagenamePageobjectMap.put("Radio Button Demo", new PO_RadioButtonDemoPage(driver));
        pagenamePageobjectMap.put("Select Dropdown List", new PO_SelectDropdownListPage(driver));
        pagenamePageobjectMap.put("Input Form Submit", new PO_InputFormSubmitPage(driver));
        pagenamePageobjectMap.put("Ajax Form Submit", ajaxFormSubmitPage);
        pagenamePageobjectMap.put("Bootstrap Date Picker", bootstrapDatePicker);
    }

    @Before
    public void maximizeSite() {
        WindowUtils.maximizeWindow(driver);
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

    @When("^I click checkbox (.*)$")
    public void check(String checkboxCaption) {
        checkboxDemoPage.clickCheckbox(checkboxCaption);
    }

    @When("^I click checkboxes (.*)$")
    public void clickCheckboxes(List<String> checkboxCaptions) {
        checkboxCaptions.forEach(checkboxDemoPage::clickCheckbox);
    }

    @When("^I click button Check/Uncheck All$")
    public void clickCheckUncheckAllButton() {
        checkboxDemoPage.clickCheckUncheckAllButton();
    }

    @Then("^I expect text to be displayed below$")
    public void checkTextBelow() {
        assertTrue(checkboxDemoPage.isSingleCheckboxSubtextVisible());
    }

    @Then("^I expect button below has caption (.*)$")
    public void checkButtonCaption(String caption) {
        assertEquals(caption, checkboxDemoPage.getButtonCheckUncheckCaption());
    }

    @Then("^I expect checkboxes (.*) to be checked$")
    public void verifyCheckedCheckboxes(List<String> checkedCheckboxes) {
        checkedCheckboxes.forEach(chkboxCaption ->
                assertTrue("Fail for " + chkboxCaption, checkboxDemoPage.isCheckboxCheck(chkboxCaption)));
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

    @When("^I submit the form$")
    public void submitForm() {
        inputFormSubmitPage.submitForm();
    }

    @Then("^I expect (.*) fields validated$")
    public void checkFieldsValidation(List<String> fieldsList) {

        String expectedErrorMsg = "";
        if (fieldsList.contains("all") && fieldsList.size() == 1) {
            inputFormSubmitPage.getFeedbackBlocks()
                    .forEach(block -> assertEquals(expectedErrorMsg, block.getText()));
        } else logger
                .error("Checking a subset of fields not implemented yet");
    }

    @Then("^I expect (.*) under one of fields$")
    public void checkErrorMessageUnderAnyField(List<String> expectedMsgsList) {
        Iterator<WebElement> actualMsgsIter = inputFormSubmitPage.getFeedbackBlocks().iterator();

        for (String expMsg : expectedMsgsList) {
            boolean messageDisplayed = false;

            while (actualMsgsIter.hasNext()) {
                if (actualMsgsIter.next().getText().equals(expMsg)) {
                    messageDisplayed = true;
                    break;
                }
            }

            assertTrue("Message " + expMsg + " not found.", messageDisplayed);
        }
    }


    @Then("^I expect to navigate to (.*)$")
    public void checkPageUrl(String expectedUrl) {
        try {
            indexPage.waitForPageLoaded();
        } catch (SiteNotOpenedException siteNotOpenedException) {
            logger.error("Couldn't navigate to " + expectedUrl);
        }
        assertThat(expectedUrl, is(equalTo(driver.getCurrentUrl())));
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


    @When("^I click (.*) button on Radio Button Demo site$")
    public void clickButton(String buttonCaption) {
        radioButtonDemoPage.clickButton(buttonCaption);
    }

    @When("^I check (.*) radiobutton in Radio Button Demo section$")
    public void checkRadiobuttonInRadioButtonDemoSection(String radiobuttonNameCaption) {
        radioButtonDemoPage.clickRadioButtonDemoRadio(radiobuttonNameCaption);
    }

    @When("^I check (.*) Radiobutton in Group Radio Buttons Demo section$")
    public void checkRadiobuttonInGroupRaioButtonsDemo(String radiobutoonCaption) {
        radioButtonDemoPage.clickGroupRadioButtonDemoRadio(radiobutoonCaption);
    }

    @Then("^I expect text (.*) below (.*) button$")
    public void verifyTextBelowButton(String expectedText, String buttonCaption) {
        String actualText = (buttonCaption.equals("Get Checked values"))
                ? radioButtonDemoPage.getRadioButtonDemoMessage()
                : radioButtonDemoPage.getGroupRadioButtonsDemoMessage();

        assertEquals(actualText.replace("\n", "").replace("\r", ""), expectedText);
    }

    @Then("^I expect message value for Sex is (.*)")
    public void verifySex(String expectedMessageValue) {
        assertEquals(expectedMessageValue, radioButtonDemoPage.readSex());
    }

    @Then("^I expect message value for Age Group is (.*)")
    public void verifyAgeGroup(String expectedMessageValue) {
        assertEquals(expectedMessageValue, radioButtonDemoPage.readAgeGroup());
    }

    @When("^I select (.*) from Dropdown List$")
    public void selectFromDropdown(String weekday) {
        selectDropdownListPage.selectWeekday(weekday);
    }

    @Then("^I expect to see (.*) in message below DropdownList$")
    public void verifySelectedWeekday(String expectedWeekday) {
        assertEquals(expectedWeekday, selectDropdownListPage.readSelectedDay());
    }

    @When("^I choose (.*) in Multi Select List$")
    public void chooseStatesInMultiSelectList(List<String> states) {
        selectDropdownListPage.selectStatesInMultiSelectList(states);
    }

    @When("^I click (.*) button on Multi Select List Demo div$")
    public void clickButtonOnMultiSelectListDemo(String buttonCaption) {
        selectDropdownListPage.clickButtononMultiSelectListDemoDiv(buttonCaption);
    }

    @Then("^I expect message (.*) displayed underneath$")
    public void verifyMessageUnderneath(String expectedMessage) {
        assertEquals(expectedMessage, selectDropdownListPage.readSelectedStatesMesage());
    }

    @When("^I enter text (.*) to 'Enter message' textbox$")
    public void enterTextToTextBox(String message) {
        inputFormsPage.enterMessage(message);
    }

    @When("^To 'Enter message' textbox I enter text:\n(.*)")
    public void enterLongTextToTextBox(String message) {
        enterTextToTextBox(message);
    }

    @When("^I click (.*) button$")
    public void clickInputFormButton(String buttonCaption) {
        inputFormsPage.pressButton(buttonCaption);
    }

    @When("^I enter a = (.*) and b = (.*) to textboxes$")
    public void clickButton(String valueA, String valueB) {
        inputFormsPage.enterValuesAandB(valueA, valueB);
    }

    @Then("^I expect (.*) to be displayed as 'Your message'$")
    public void checkDisplayedMessage(String message) {
        assertEquals(message, inputFormsPage.readDisplayedMessage());
    }

    @Then("^I expect (.*) to be displayed as 'Total'$")
    public void checkTotalResult(String result) {
        assertEquals(result, inputFormsPage.readTotalResult());
    }

    @When("^I enter a name (.*)$")
    public void enterName(String name) {
        ajaxFormSubmitPage.enterName(name);
    }

    @When("^I enter a comment (.*)$")
    public void enterComment(String comment) {
        ajaxFormSubmitPage.enterComment(comment);
    }

    @When("^I click Submit button for Ajax Form Submit$")
    public void submitAjaxData() {
        ajaxFormSubmitPage.clickSubmit();
    }

    @Then("^I expect Submit button to disappear$")
    public void validateSubmitButtonDisappearance() {
        //terribly ugly code, but I didn't google any better solution :(
        try {
            ajaxFormSubmitPage.getSubmitButtonVisibility(); // should throw StaleElementReferenceException
            Assert.fail();
        } catch (StaleElementReferenceException staleElementException) {
            //this Exception is expected
        }
    }

    @Then("^Later I expect (.*) message to appear underneath for Ajax Form Submit$")
    public void validateFinalMessageUnderCommentTextbox(String expectedMsg) {
        ajaxFormSubmitPage.waitForMessageChange(expectedMsg);
        assertEquals(expectedMsg, ajaxFormSubmitPage.readMessageUnderneath());
    }

    @Then("^I expect Submit button to be visible$")
    public void validateSubmitButtonVisibility() {
        assertTrue(ajaxFormSubmitPage.getSubmitButtonVisibility());
    }

    @Then("^I expect Name textbox turn red$")
    public void validateRedColorOfNameTextbox() {
        assertThat(ajaxFormSubmitPage.getTitleTextboxStyle(), containsString("rgb(255, 0, 0)"));
    }

    @Then("^I expect (.*) to appear underneath for Ajax Form Submit$")
    public void validateMessageUnderCommentTextbox(String expectedMsg) {
        ajaxFormSubmitPage.findLoaderSpinner();
        assertNotNull("Load Icon Spinner wasn't found under TextBox ", PO_AjaxFormSubmitPage.getLoaderIcon());
        assertEquals(expectedMsg, ajaxFormSubmitPage.readMessageUnderneath());
    }

    @When("^I select today's date in Calendar$")
    public void selectTodaysDateInCalendar(){
        bootstrapDatePicker.clickTodayButton();
    }

    @Then("^I expect today's date to be visible in Date Picker$")
    public void validateSelectedDate(){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String today = df.format(new Date());

        assertEquals(today, bootstrapDatePicker.getSelectedDate());
    }

    @After
    public void tearDown() {
        WebDriverSingleton.close();
    }

}
