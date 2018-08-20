package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.pageobjects.Page;
import org.kd.selframework.core.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Hashtable;
import java.util.Map;

import static org.kd.selframework.core.pageobjects.LocatorHelper.*;

public class PO_SimpleFormPage extends Page {

    private final By userMessageTextBoxSelector = By.tagName("input");
    private final By userMsgFormGroupSelector = By.className("form-group");
    private final By valueATextboxSelector = By.id("sum1");
    private final By valueBTextboxSelector = By.id("sum2");
    private final By formMessageSelector = By.id("get-input");
    private final By buttonSelector = By.tagName("button");
    private final By formGetTotalSelector = By.id("gettotal");
    private final By resultSpanSelector = By.xpath("//span[contains(@id, 'displayvalue')]");
    private final By displayedMessageSpanSelector = By.id("display");
    private final By userMessageDivSelector = By.xpath("//div[contains(@id, 'user-message')]");

    private static WebElement formMessage;
    private static WebElement userMsgFormGroup;
    private static WebElement formGetTotal;
    private static WebElement userMessageTextBox;
    private static WebElement showMessageButton;
    private static WebElement getTotalButton;
    private static WebElement resultSpan;
    private static WebElement userMessageDiv;
    private static WebElement displayedMessageSpan;
    private static WebElement valueATextbox;
    private static WebElement valueBTextbox;

    private static Map<String, WebElement> captionButtonMap;

    private final int TIMEOUT = PropertiesReader.readFromConfig("timeout.default");

    public PO_SimpleFormPage(WebDriver driver) {
        super(driver, PropertiesReader.readFromConfig("app-under-test.inputforms.url"));
    }

    @Override
    public void findElements() {
        formMessage = quietlyFindElement(driver, formMessageSelector);
        userMsgFormGroup = quietlyFindElementWithinElement(driver, userMsgFormGroupSelector, formMessage, TIMEOUT);
        if (userMsgFormGroup != null)
            userMessageTextBox = quietlyFindElementWithinElement(driver, userMessageTextBoxSelector, userMsgFormGroup, TIMEOUT);

        showMessageButton = quietlyFindElementWithinElement(driver, buttonSelector, formMessage, TIMEOUT);

        formGetTotal = quietlyFindElement(driver, formGetTotalSelector);
        getTotalButton = quietlyFindElementWithinElement(driver, buttonSelector, formGetTotal, TIMEOUT);

        findDisplayedResultElements();

        findDisplayedMessageElements();

        valueATextbox = quietlyFindElementWithinElement(driver, valueATextboxSelector, formGetTotal, TIMEOUT);
        valueBTextbox = quietlyFindElementWithinElement(driver, valueBTextboxSelector, formGetTotal, TIMEOUT);

        captionButtonMap = new Hashtable<>(2);
        captionButtonMap.put("Show Message", showMessageButton);
        captionButtonMap.put("Get Total", getTotalButton);
    }

    private void findDisplayedResultElements() {
        resultSpan = quietlyFindElementWithinElement(driver, resultSpanSelector, formGetTotal, TIMEOUT);
    }

    private void findDisplayedMessageElements() {
        userMessageDiv = quietlyFindElement(driver, userMessageDivSelector, TIMEOUT);
        if (userMessageDiv != null)
            displayedMessageSpan = quietlyFindElementWithinElement(driver, displayedMessageSpanSelector, userMessageDiv, TIMEOUT);
    }

    public void enterMessage(String message) {
        sendKeys(driver, userMessageTextBox, message);
    }

    public void enterValuesAandB(String A, String B) {
        sendKeys(driver, valueATextbox, A);
        sendKeys(driver, valueBTextbox, B);
    }

    public void pressButton(String buttonCaption) {
        captionButtonMap.get(buttonCaption).click();
    }

    public String readDisplayedMessage() {
        findDisplayedMessageElements(); //possibly redundant, but good practice to prevent StaleElement exception
        return displayedMessageSpan.getText();
    }

    public String readTotalResult() {
        findDisplayedResultElements();
        return resultSpan.getText();
    }

}
