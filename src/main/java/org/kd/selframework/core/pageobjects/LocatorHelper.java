package org.kd.selframework.core.pageobjects;

import org.kd.selframework.core.utils.PropertiesReader;
import org.kd.selframework.core.utils.TestLogger;
import org.kd.selframework.core.utils.TestLoggerSingleton;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public final class LocatorHelper {

    private static final int TIMEOUT;
    private static final int POLLING_INTERVAL;

    private static final TestLogger logger = TestLoggerSingleton.getInstance();

    static {
        TIMEOUT = PropertiesReader.readFromConfig("timeout.default");
        if (TIMEOUT / 5 < 1) {
            POLLING_INTERVAL = 1;
        } else {
            POLLING_INTERVAL = TIMEOUT / 5;
        }
    }

    private LocatorHelper() {
    }

    public static WebElement quietlyFindElement(final WebDriver driver, final By locator) {
        return quietlyFindElement(driver, locator, TIMEOUT);
    }

    public static boolean isElementVisible(final WebDriver driver, final By locator) {
        WebElement element = quietlyFindElement(driver, locator);
        if (element != null)
            return element.isDisplayed();
        else
            return false;
    }

    public static WebElement quietlyFindElement(WebDriver driver, By locator, int timeout) {
        ExpectedCondition<WebElement> elementLocated;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement element;
        try {
            elementLocated = ExpectedConditions.presenceOfElementLocated(locator);
            element = wait.until(elementLocated);
            logger.log("Found element: " + element.getClass() + " with locator " + locator);
            return element;

        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Element " + locator.toString() + " not found on page " + driver.getCurrentUrl());
            return null;
        }
    }

    public static WebElement quietlyFindElementWithinElement(WebDriver driver, By locator, WebElement parent) {
        return quietlyFindElementWithinElement(driver, locator, parent, TIMEOUT);
    }

    public static WebElement quietlyFindElementWithinElement(WebDriver driver, By locator, WebElement parent, int timeout) {
        WebElement element;
        ExpectedCondition<WebElement> elementLocated;

        try {
            element = parent.findElement(locator);
            logger.log("Found element: " + element.getClass() + " within parent " + parent);
            return element;

        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Element " + locator.toString() + " not found on page " + driver.getCurrentUrl());
            return null;
        }
    }

    public static List<WebElement> quietlyFindElements(WebDriver driver, By locator, String locatorArg) {
        return quietlyFindElements(driver, locator, locatorArg, TIMEOUT);
    }

    public static List<WebElement> quietlyFindElements(WebDriver driver, By locator, String locatorArg, int timeout) {
        List<WebElement> elements;
        try {
            elements = driver.findElements(locator);
            logger.log("Found " + elements.size() + " elements.");
            return elements;

        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Element " + locator.toString() + " not found on page " + driver.getCurrentUrl());
            return null;
        }
    }

    public static List<WebElement> quietlyFindElementsWithin(WebDriver driver, WebElement parent, By locator, int timeout) {
        List<WebElement> elements;
        try {
            elements = parent.findElements(locator);
            logger.log("Found " + elements.size() + " elements.");
            return elements;

        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Element " + locator.toString() + " not found on page " + driver.getCurrentUrl());
            return null;
        }
    }
    public static List<WebElement> quietlyFindElementsWithin(WebDriver driver, WebElement parent, By locator) {
        return quietlyFindElementsWithin(driver, parent, locator, TIMEOUT);
    }

    public static WebElement findClickableElement(WebDriver driver, By locator, int timeout) {
        ExpectedCondition<WebElement> elementLocated;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement element;

        try {
            elementLocated = ExpectedConditions.elementToBeClickable(locator);
            element = wait.until(elementLocated);
            return element;

        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Element " + locator.toString() + " not found on page " + driver.getCurrentUrl());
            return null;
        }
    }

    public static WebElement findClickableElement(WebDriver driver, By locator) {
        return findClickableElement(driver, locator, TIMEOUT);
    }

    public static void focusOnElement(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).click().perform();
    }

    public static void sendKeys(WebDriver driver, WebElement element, String text) {
        focusOnElement(driver, element);
        Actions action = new Actions(driver);
        action.sendKeys(element, text).perform();
    }

    public static WebElement getElementFromDiv(WebDriver driver, String containerId, String elementTag, int timeout) {
        String xpath = String.format("//div[contains(@class, '%s')]//%s", containerId, elementTag);
        return quietlyFindElement(driver, By.xpath(xpath), timeout);
    }

}
