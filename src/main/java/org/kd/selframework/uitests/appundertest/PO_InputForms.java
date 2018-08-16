package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.lib.PropertiesReader;
import org.kd.selframework.core.pageobjects.LocatorHelper;
import org.kd.selframework.core.pageobjects.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_InputForms extends Page {

    private final By userMessageTextBoxSelector = By.id("user-message");

    private static WebElement userMessageTextBox;

    public PO_InputForms(WebDriver driver) {
        super(driver, PropertiesReader.readFromConfig("app-under-test.inputforms.url"));
    }

    @Override
    public void findElements() {
        userMessageTextBox = LocatorHelper.quietlyFindElement(driver, userMessageTextBoxSelector);
    }

    public void enterMessage(String message){
        LocatorHelper.focusOnElement(driver, userMessageTextBox);
        userMessageTextBox.sendKeys(message);
    }

    public String readDisplayedMessage(){
        return "not implemneted yet";
    }


}
