package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.pageobjects.Page;
import org.kd.selframework.core.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.kd.selframework.core.pageobjects.LocatorHelper.quietlyFindElement;
import static org.kd.selframework.core.pageobjects.LocatorHelper.quietlyFindElements;

public final class PO_CheckboxDemoPage extends Page {

    private final By singleCheckboxSelector = By.id("isAgeSelected");
    private final By singleCheckboxSubtextSelector = By.id("txtAge");
    private final By optionCheckboxesSelector = By.className("cb1-element");
    private final By buttonCheckUncheckAllSelector = By.id("check1");

    private static WebElement singleCheckbox;
    private static List<WebElement> optionCheckboxes;
    private static WebElement singleCheckboxSubtextDiv;
    private static WebElement buttonCheckUncheckAll;

    private final static Map<String, WebElement> textCheckboxMap = new Hashtable<>(5);

    public PO_CheckboxDemoPage(WebDriver driver) {
        super(driver, PropertiesReader.readFromConfig("app-under-test.checkboxdemo.url"));
    }

    @Override
    public void findElements() {
        singleCheckbox = quietlyFindElement(driver, singleCheckboxSelector);
        singleCheckboxSubtextDiv = quietlyFindElement(driver, singleCheckboxSubtextSelector);

        optionCheckboxes = quietlyFindElements(driver, optionCheckboxesSelector, "");

        initTextCheckboxMap();

        buttonCheckUncheckAll = quietlyFindElement(driver, buttonCheckUncheckAllSelector);
    }

    private void initTextCheckboxMap() {
        textCheckboxMap.put("Click on this check box", singleCheckbox);

        byte i = 1;
        for (WebElement chkbox : optionCheckboxes) {
            textCheckboxMap.put(String.format("Option %d", i++), chkbox);
        }
    }

    public void clickCheckbox(String chkboxCaption) {
        WebElement chkbox = textCheckboxMap.get(chkboxCaption);
        if (chkbox != null)
            textCheckboxMap.get(chkboxCaption).click();
        else
            logger.error(PropertiesReader.readFromConfig("scenario.error.wrongbuttonname") + chkboxCaption);
    }

    public void clickCheckUncheckAllButton() {
        buttonCheckUncheckAll.click();
    }

    public boolean isCheckboxCheck(String checkboxCaption) {
        return Optional
                .ofNullable(textCheckboxMap.get(checkboxCaption))
                .map(WebElement::isSelected)
                .orElse(false);
    }

    public boolean isSingleCheckboxSubtextVisible() {
        return singleCheckboxSubtextDiv.isDisplayed();
    }

    public String getButtonCheckUncheckCaption() {
        return buttonCheckUncheckAll.getAttribute("value");
    }
}
