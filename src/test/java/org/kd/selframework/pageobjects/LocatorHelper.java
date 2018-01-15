package org.kd.selframework.pageobjects;

import org.kd.selframework.general.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public final class LocatorHelper {

    private static final int timeout;
    private static int longTimeout;

    private final static Logger logger = LoggerFactory.getLogger(PO_Index.class);

    static {
        timeout = PropertiesReader.readFromConfig("timeout.default");
    }

    private LocatorHelper() {
    }

    public static WebElement quietlyQuickFindElement(WebDriver driver, By locator) {
        return quietlyFindElement(driver, locator, timeout);
    }

    public static WebElement quietlyLongFindElement(WebDriver driver, By locator) {
        return quietlyFindElement(driver, locator, longTimeout);
    }

    public static WebElement quietlyFindElement(WebDriver driver, By locator, int timeout) {
        ExpectedCondition<WebElement> elementLocated;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement element;
        try {
            elementLocated = ExpectedConditions.presenceOfElementLocated(locator);
            element = wait.until(elementLocated);
            return element;
        } catch (NoSuchElementException e) {
            logger.error("Element {} not found on page {}", locator.toString(), driver.getCurrentUrl());
            return null;
        }
    }

    public static List<WebElement> quietlyFindElements(WebDriver driver, By locator, String locatorArg) {
        return null;
    }

    public static List<WebElement> quietlyFindElements(WebDriver driver, By locator, String locatorArg, int timeout) {
        return null;
    }

    public static WebElement findClickableElement(WebDriver driver, By locator, int timeout){
        ExpectedCondition<WebElement> elementLocated;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement element;
        try {
            elementLocated = ExpectedConditions.elementToBeClickable(locator);
            element = wait.until(elementLocated);
            return element;
        } catch (NoSuchElementException e) {
            logger.error("Element {} not found on page {}", locator.toString(), driver.getCurrentUrl());
            return null;
        }
    }

    public static void focusOnElement(WebDriver driver, WebElement element){
        Actions actionChains = new Actions(driver);
        actionChains.moveToElement(element).click().perform();
    }

    public static void sendKeys(WebDriver driver, WebElement element, String text){
        focusOnElement(driver, element);
        element.sendKeys(text);
    }

    public static WebElement getElementFromDiv(WebDriver driver, String containerId, String elementTag, int timeout){
        String xpath = String.format("//div[contains(@class, '%s')]//%s", containerId, elementTag);
        return quietlyFindElement(driver, By.xpath(xpath), timeout);
    }

}
