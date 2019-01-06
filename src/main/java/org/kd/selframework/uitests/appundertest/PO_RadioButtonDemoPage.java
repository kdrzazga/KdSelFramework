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

public final class PO_RadioButtonDemoPage extends Page {

    private final static Map<String, WebElement> textButtonMap = new Hashtable<>(2);
    private final static Map<String, WebElement> radioButtonDemoTextRadioMap = new Hashtable<>(2);
    private final static Map<String, WebElement> groupRadioButtonsDemoTextRadioMap = new Hashtable<>(5);

    private static WebElement getCheckedValueButton;
    private static WebElement getValuesButton;
    private static WebElement radioButtonDemoMessagePara;
    private static WebElement groupRadioButtonsDemoMessagePara;

    private static WebElement radioButtonDemoMaleRadio;
    private static WebElement radioButtonDemoFemaleRadio;
    private static WebElement groupRadioButtonsDemoMaleRadio;
    private static WebElement groupRadioButtonsDemoFemaleRadio;
    private static WebElement groupRadioButtonsDemoAge0_5Radio;
    private static WebElement groupRadioButtonsDemoAge5_15Radio;
    private static WebElement groupRadioButtonsDemoAge15_50Radio;

    private final By getCheckedValueButtonSelector = By.id("buttoncheck");
    private final By getValuesButtonSelector = By.xpath("//button[contains(text(), 'Get values')]");
    private final By radioButtonDemoMessageSelector = By.xpath("//p[contains(text(), 'Radio button ')]");
    private final By groupRadioButtonsDemoMessageSelector = By.xpath("//p[contains(text(), 'Sex :')]");

    private final By radioButtonDemoMaleRadioSelector = By.xpath("//input[contains(@value, 'Male') and contains(@name, 'optradio')]");
    private final By radioButtonDemoFemaleRadioSelector = By.xpath("//input[contains(@value, 'Female') and contains(@name, 'optradio')]");
    private final By groupRadioButtonsDemoMaleRadioSelector = By.xpath("//input[contains(@value, 'Male') and contains(@name, 'gender')]");
    private final By groupRadioButtonsDemoFemaleRadioSelector = By.xpath("//input[contains(@value, 'Female') and contains(@name, 'gender')]");
    private final By groupRadioButtonsDemoAge0_5RadioSelector = By.xpath("//input[contains(@value, '0 - 5') and contains(@name, 'ageGroup')]");
    private final By groupRadioButtonsDemoAge5_15RadioSelector = By.xpath("//input[contains(@value, '5 - 15') and contains(@name, 'ageGroup')]");
    private final By groupRadioButtonsDemoAge15_50RadioSelector = By.xpath("//input[contains(@value, '15 - 50') and contains(@name, 'ageGroup')]");


    public PO_RadioButtonDemoPage(WebDriver driver) {
        super(driver, PropertiesReader.readFromConfig("app-under-test.radiobuttondemo.url"));
    }

    @Override
    public void findElements() {
        getCheckedValueButton = quietlyFindElement(driver, getCheckedValueButtonSelector);
        getValuesButton = quietlyFindElement(driver, getValuesButtonSelector);

        radioButtonDemoMaleRadio = quietlyFindElement(driver, radioButtonDemoMaleRadioSelector);
        radioButtonDemoFemaleRadio = quietlyFindElement(driver, radioButtonDemoFemaleRadioSelector);
        groupRadioButtonsDemoMaleRadio = quietlyFindElement(driver, groupRadioButtonsDemoMaleRadioSelector);
        groupRadioButtonsDemoFemaleRadio = quietlyFindElement(driver, groupRadioButtonsDemoFemaleRadioSelector);
        groupRadioButtonsDemoAge0_5Radio = quietlyFindElement(driver, groupRadioButtonsDemoAge0_5RadioSelector);
        groupRadioButtonsDemoAge5_15Radio = quietlyFindElement(driver, groupRadioButtonsDemoAge5_15RadioSelector);
        groupRadioButtonsDemoAge15_50Radio = quietlyFindElement(driver, groupRadioButtonsDemoAge15_50RadioSelector);

        initTextElementMaps();
    }

    private void initTextElementMaps() {
        textButtonMap.put("Get Checked values", getCheckedValueButton);
        textButtonMap.put("Get values", getValuesButton);

        radioButtonDemoTextRadioMap.put("Male", radioButtonDemoMaleRadio);
        radioButtonDemoTextRadioMap.put("Female", radioButtonDemoFemaleRadio);

        groupRadioButtonsDemoTextRadioMap.put("Male", groupRadioButtonsDemoMaleRadio);
        groupRadioButtonsDemoTextRadioMap.put("Female", groupRadioButtonsDemoFemaleRadio);
        groupRadioButtonsDemoTextRadioMap.put("0 to 5", groupRadioButtonsDemoAge0_5Radio);
        groupRadioButtonsDemoTextRadioMap.put("5 to 15", groupRadioButtonsDemoAge5_15Radio);
        groupRadioButtonsDemoTextRadioMap.put("15 to 50", groupRadioButtonsDemoAge15_50Radio);
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

    public void clickRadioButtonDemoRadio(String radioCaption) {
        Optional<WebElement> radio = Optional
                .ofNullable(radioButtonDemoTextRadioMap.get(radioCaption));

        radio.ifPresent(WebElement::click);
    }

    public void clickGroupRadioButtonDemoRadio(String radioCaption) {
        Optional<WebElement> radio = Optional
                .ofNullable(groupRadioButtonsDemoTextRadioMap.get(radioCaption));

        radio.ifPresent(WebElement::click);
    }

    public String readSex() {
        String wholeMessage = getGroupRadioButtonsDemoMessage();
        String sex = "Sex :";
        int startIndex = wholeMessage.indexOf(sex) + sex.length();
        int endIndex = wholeMessage.indexOf("\n");

        return wholeMessage.substring(startIndex, endIndex).trim();
    }

    public String readAgeGroup() {
        String wholeMessage = getGroupRadioButtonsDemoMessage();
        String ageGroup = "Age group:";
        int startIndex = wholeMessage.indexOf(ageGroup) + ageGroup.length();

        return wholeMessage.substring(startIndex).trim();
    }
}
