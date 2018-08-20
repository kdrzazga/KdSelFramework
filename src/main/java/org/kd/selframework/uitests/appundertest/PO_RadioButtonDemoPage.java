package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.pageobjects.Page;
import org.kd.selframework.core.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

import static org.kd.selframework.core.pageobjects.LocatorHelper.quietlyFindElement;

public class PO_RadioButtonDemoPage extends Page {

    private final static Map<String, WebElement> textButtonMap = new Hashtable<>(2);
    private static WebElement getCheckedValueButton;
    private static WebElement getValuesButton;
    private static WebElement radioButtonDemoMessagePara;
    private static WebElement groupRadioButtonsDemoMessagePara;
    private final By getCheckedValueButtonSelector = By.id("buttoncheck");
    private final By getValuesButtonSelector = By.xpath("//button[contains(text(), 'Get values')]");
    private final By radioButtonDemoMessageSelector = By.xpath("//p[contains(text(), 'Radio button ')]");
    private final By groupRadioButtonsDemoMessageSelector = By.xpath("//p[contains(text(), 'Sex :')]");

    public PO_RadioButtonDemoPage(WebDriver driver) {
        super(driver, PropertiesReader.readFromConfig("app-under-test.radiobuttondemo.url"));
    }

    @Override
    public void findElements() {
        getCheckedValueButton = quietlyFindElement(driver, getCheckedValueButtonSelector);
        getValuesButton = quietlyFindElement(driver, getValuesButtonSelector);

        initTextButtonMap();
    }

    public void findRadioButtonDemoMessagePara() {
        radioButtonDemoMessagePara = quietlyFindElement(driver, radioButtonDemoMessageSelector);
    }

    public void findGroupRadioButtonsDemoMessagePara() {
        groupRadioButtonsDemoMessagePara = quietlyFindElement(driver, groupRadioButtonsDemoMessageSelector);
    }

    public void clickButton(String buttonCaption) {
        Optional<WebElement> button = Optional.ofNullable(textButtonMap.get(buttonCaption));
        button.ifPresent(WebElement::click);
    }

    public String getRadioButtonDemoMessage() {
        findRadioButtonDemoMessagePara();

        return Optional.ofNullable(radioButtonDemoMessagePara)
                .map(WebElement::getText)
                .orElse("");
    }

    public String getGroupRadioButtonsDemoMessage() {
        findGroupRadioButtonsDemoMessagePara();

        return Optional.ofNullable(groupRadioButtonsDemoMessagePara)
                .map(WebElement::getText)
                .orElse("");
    }

    private void initTextButtonMap() {
        textButtonMap.put("Get Checked values", getCheckedValueButton);
        textButtonMap.put("Get values", getValuesButton);
    }
}
