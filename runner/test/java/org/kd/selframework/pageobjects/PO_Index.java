package org.kd.selframework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PO_Index extends BasePage implements Page {

    private static Logger logger = LoggerFactory.getLogger(PO_Index.class);

    @Override
    public WebElement quietlyFindElement(By locator) {
        WebElement element;
        try {
            element = driver.findElement(locator);
            return element;
        } catch (NoSuchElementException e) {
            logger.error("Element {} not found on page {}", locator.toString(), driver.getCurrentUrl());
            return null;
        }
    }

    @Override
    public List<WebElement> quietlyFindElements(By locator) {
        return null;
    }

    public PO_Index(String url) {
        super();
        this.url = url;
    }

    public void navigateTo() {
        super.navigateTo();

    }
}
