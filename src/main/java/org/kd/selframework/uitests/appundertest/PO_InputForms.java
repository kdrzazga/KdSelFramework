package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.utils.PropertiesReader;
import org.kd.selframework.core.pageobjects.LocatorHelper;
import org.kd.selframework.core.pageobjects.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Hashtable;
import java.util.Map;

public class PO_InputForms extends Page {

    private final By userMessageTextBoxSelector = By.tagName("input");
    private final By formMessageSelector = By.id("get-input");
    private final By buttonSelector = By.tagName("button");
    private final By valueATextboxSelector = By.id("sum1");
    private final By valueBTextboxSelector = By.id("sum2");
    private final By formGetTotalSelector = By.id("gettotal");
    private final By resultSpanSelector = By.id("displayvalue");
    private final By diplayedMessageSpanSelector = By.id("display");

    private static WebElement formMessage;
    private static WebElement formGetTotal;
    private static WebElement userMessageTextBox;
    private static WebElement showMessageButton;
    private static WebElement getTotalButton;
    private static WebElement resultSpan;
    private static WebElement diplayedMessageSpan;
    private static WebElement valueATextbox;
    private static WebElement valueBTextbox;

    private static Map<String, WebElement> captionButtonMap;

    private final int TIMEOUT = PropertiesReader.readFromConfig("timeout.default");

    public PO_InputForms(WebDriver driver) {
        super(driver, PropertiesReader.readFromConfig("app-under-test.inputforms.url"));
    }

    @Override
    public void findElements() {
        formMessage = LocatorHelper.quietlyFindElement(driver, formMessageSelector);
        userMessageTextBox = LocatorHelper.quietlyFindElementWithinElement(driver, userMessageTextBoxSelector, formMessage, TIMEOUT);
        showMessageButton = LocatorHelper.quietlyFindElementWithinElement(driver, buttonSelector, formMessage, TIMEOUT);

        formGetTotal = LocatorHelper.quietlyFindElement(driver, formGetTotalSelector);
        getTotalButton = LocatorHelper.quietlyFindElementWithinElement(driver, buttonSelector, formGetTotal, TIMEOUT);

        resultSpan = LocatorHelper.quietlyFindElementWithinElement(driver, resultSpanSelector, formGetTotal, TIMEOUT);
        diplayedMessageSpan = LocatorHelper.quietlyFindElementWithinElement(driver, diplayedMessageSpanSelector, formMessage, TIMEOUT);

        valueATextbox = LocatorHelper.quietlyFindElementWithinElement(driver, valueATextboxSelector, formGetTotal, TIMEOUT);
        valueBTextbox = LocatorHelper.quietlyFindElementWithinElement(driver, valueBTextboxSelector, formGetTotal, TIMEOUT);

        captionButtonMap = new Hashtable<>(2);
        captionButtonMap.put("Show Message", showMessageButton);
        captionButtonMap.put("Get Total", getTotalButton);
    }

    public void enterMessage(String message) {
        LocatorHelper.focusOnElement(driver, userMessageTextBox);
        userMessageTextBox.sendKeys(message);
    }

    public void enterValuesAandB(String A, String B) {
        valueATextbox.sendKeys(A);
        valueBTextbox.sendKeys(B);
    }

    public void pressButton(String buttonCaption) {
        captionButtonMap.get(buttonCaption).click();
    }

    public String readDisplayedMessage() {
        return diplayedMessageSpan.getText();
    }

    public String readTotalResult() {
        return resultSpan.getText();
    }

}
