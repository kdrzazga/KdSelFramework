package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.pageobjects.Page;
import org.kd.selframework.core.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.kd.selframework.core.pageobjects.LocatorHelper.*;

public class PO_InputFormSubmitPage extends Page {

    private static WebElement firstNameTextbox;
    private static WebElement lastNameTextbox;
    private static WebElement emailTextbox;
    private static WebElement phoneTextbox;
    private static WebElement addressTextbox;
    private static WebElement cityTextbox;
    private static WebElement stateDropdown;
    private static WebElement zipCodeTextbox;
    private static WebElement websiteTextbox;
    private static WebElement hostingRadioYes;
    private static WebElement hostingRadioNo;
    private static WebElement textareaProjectDescription;
    private static WebElement sendButton;
    private static List<WebElement> feedbackBlocks;
    private final By firstNameTextboxSelector = By.name("first_name");
    private final By lastNameTextboxSelector = By.name("last_name");
    private final By emailTextboxSelector = By.name("email");
    private final By phoneTextboxSelector = By.name("phone");
    private final By addressTextboxSelector = By.name("address");
    private final By cityTextboxSelector = By.name("city");
    private final By stateDropdownSelector = By.name("state");
    private final By zipCodeTextboxSelector = By.name("zip");
    private final By websiteTextboxSelector = By.name("website");
    private final By hostingRadioYesSelector = By.xpath("//input[contains(@name, 'hosting') and contains(@value, 'yes')and contains(@type, 'radio')]");
    private final By hostingRadioNoSelector = By.xpath("//input[contains(@name, 'hosting') and contains(@value, 'no')and contains(@type, 'radio')]");
    private final By textareaCommentSelector = By.tagName("textarea");
    private final By sendButtonSelector = By.xpath("//button[contains(@type, 'submit')]");
    private final By feedbackBlockSelector = By.className("help-block");

    public PO_InputFormSubmitPage(WebDriver driver) {
        super(driver, PropertiesReader.readFromConfig("app-under-test.inputfromsubmit.url"));
    }

    public static List<WebElement> getFeedbackBlocks() {
        return feedbackBlocks;
    }

    @Override
    public void findElements() {
        firstNameTextbox = quietlyFindElement(driver, firstNameTextboxSelector);
        lastNameTextbox = quietlyFindElement(driver, lastNameTextboxSelector);
        emailTextbox = quietlyFindElement(driver, emailTextboxSelector);
        phoneTextbox = quietlyFindElement(driver, phoneTextboxSelector);
        addressTextbox = quietlyFindElement(driver, addressTextboxSelector);
        cityTextbox = quietlyFindElement(driver, cityTextboxSelector);
        stateDropdown = quietlyFindElement(driver, stateDropdownSelector);
        zipCodeTextbox = quietlyFindElement(driver, zipCodeTextboxSelector);
        websiteTextbox = quietlyFindElement(driver, websiteTextboxSelector);
        hostingRadioYes = quietlyFindElement(driver, hostingRadioYesSelector);
        hostingRadioNo = quietlyFindElement(driver, hostingRadioNoSelector);
        textareaProjectDescription = quietlyFindElement(driver, textareaCommentSelector);
        sendButton = quietlyFindElement(driver, sendButtonSelector);
        findFeedbackBlocks();
    }

    private void findFeedbackBlocks() {
        feedbackBlocks = quietlyFindElements(driver, feedbackBlockSelector, "");
    }

    public void enterFirstName(String value) {
        sendKeys(driver, firstNameTextbox, value);
    }

    public void enterLastName(String value) {
        sendKeys(driver, lastNameTextbox, value);
    }

    public void enterEmail(String value) {
        sendKeys(driver, emailTextbox, value);
    }

    public void enterPhone(String value) {
        sendKeys(driver, phoneTextbox, value);
    }

    public void enterStreet(String value) {
        sendKeys(driver, addressTextbox, value);
    }

    public void enterCity(String value) {
        sendKeys(driver, cityTextbox, value);
    }

    public void enterState(String value) {
        sendKeys(driver, stateDropdown, value);
    }

    public void enterZip(String value) {
        sendKeys(driver, zipCodeTextbox, value);
    }

    public void enterWebsite(String value) {
        sendKeys(driver, websiteTextbox, value);
    }

    public void setHosting(boolean withHosting) {
        if (withHosting)
            hostingRadioYes.click();
        else
            hostingRadioNo.click();
    }

    public void enterProjectDescription(String value) {
        sendKeys(driver, textareaProjectDescription, value);
    }

    public void submitForm() {
        sendButton.click();
    }


}
