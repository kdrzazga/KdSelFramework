package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.pageobjects.Page;
import org.kd.selframework.core.utils.PropertiesReader;
import org.kd.selframework.uitests.Lib;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.kd.selframework.core.pageobjects.LocatorHelper.quietlyFindElement;
import static org.kd.selframework.core.pageobjects.LocatorHelper.sendKeys;

public final class PO_AjaxFormSubmitPage extends Page {

    private static WebElement titleTextbox;
    private static WebElement descriptionTextbox;
    private static WebElement submitButton;
    private static WebElement messageUnderneath;
    private static WebElement loaderIcon;

    private final By titleTextboxSelector = By.id("title");
    private final By descriptionTextboxSelector = By.id("description");
    private final By submitButtonSelector = By.id("btn-submit");
    private final By messageUnderneathSelector = By.id("submit-control");
    private final By loaderIconSelector = By.xpath("//img[contains(@src, 'LoaderIcon.gif')]");

    public PO_AjaxFormSubmitPage(WebDriver driver) {
        super(driver, PropertiesReader.readFromConfig("app-under-test.ajaxsubmit.url"));
    }

    public static WebElement getLoaderIcon() {
        return loaderIcon;
    }

    public static void untilJqueryIsDone(WebDriver driver, Long timeoutInSeconds) {
        until(driver, (d) ->
        {
            System.err.println("\n\n\n\nU   N   T    I   L \n\n\n\n");
            Boolean isJqueryCallDone = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
            if (!isJqueryCallDone) System.out.println("JQuery call is in Progress");
            return isJqueryCallDone;
        }, timeoutInSeconds);
    }

    private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
        webDriverWait.withTimeout(timeoutInSeconds, SECONDS);
        try {
            webDriverWait.until(waitCondition);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void findElements() {
        titleTextbox = quietlyFindElement(driver, titleTextboxSelector);
        descriptionTextbox = quietlyFindElement(driver, descriptionTextboxSelector);
        submitButton = quietlyFindElement(driver, submitButtonSelector);
        messageUnderneath = quietlyFindElement(driver, messageUnderneathSelector);
    }

    public void findLoaderSpinner() {
        int timeoutToFindSpinner = 15;
        loaderIcon = quietlyFindElement(driver, loaderIconSelector, timeoutToFindSpinner);
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public void enterName(String name) {
        sendKeys(driver, titleTextbox, name);
    }

    public void enterComment(String comment) {
        sendKeys(driver, descriptionTextbox, comment);
    }

    public String readMessageUnderneath() {
        return messageUnderneath.getText();
    }

    public boolean getSubmitButtonVisibility() {
        return submitButton.isDisplayed();
    }

    public void waitForMessageChange(String desiredText) {
        Lib.waitForTextChange(messageUnderneath, desiredText);
    }

    public String getTitleTextboxStyle() {
        return titleTextbox.getAttribute("style");
    }
}
