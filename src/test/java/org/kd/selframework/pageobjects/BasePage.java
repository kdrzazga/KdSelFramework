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

public class BasePage implements Page{
    protected WebDriver driver;
    protected String url;
    protected int timeout;
    protected int longTimeout;

    private final static Logger logger = LoggerFactory.getLogger(PO_Index.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        timeout = PropertiesReader.readFromConfig("timeout.default");
    }

    public WebElement quietlyQuickFindElement(By locator) {
        return quietlyFindElement(locator, timeout);
    }

    public WebElement quietlyLongFindElement(By locator) {
        return quietlyFindElement(locator, longTimeout);
    }

    public WebElement quietlyFindElement(By locator, int timeout) {
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

    public List<WebElement> quietlyFindElements(By locator, String locatorArg) {
        return null;
    }

    public List<WebElement> quietlyFindElements(By locator, String locatorArg, int timeout) {
        return null;
    }

    public WebElement findClickableElement(By locator, int timeout){
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

    public void focusOnElement(WebElement element){
        Actions actionChains = new Actions(driver);
        actionChains.moveToElement(element).click().perform();
    }

    public void sendKeys(WebElement element, String text){
        focusOnElement(element);
        element.sendKeys(text);
    }

    public WebElement getElementFromDiv(String containerId, String elementTag, int timeout){
        String xpath = String.format("//div[contains(@class, '%s')]//%s", containerId, elementTag);
        return quietlyFindElement(By.xpath(xpath), timeout);
    }

    public void navigateTo() {
        driver.get(url);
        logger.info("Navigating to {}", this.url);
    }

    @Override
    public WebDriver getDriver() {
        return this.driver;
    }

    @Override
    public String getTitle(){
        return this.driver.getTitle();
    }

}
