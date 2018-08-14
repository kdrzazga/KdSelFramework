package org.kd.selframework.core.pageobjects;

import org.kd.selframework.core.exceptions.SiteNotOpened;
import org.kd.selframework.core.lib.PropertiesReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public abstract class Page implements WebDriveable {

    protected WebDriver driver;
    protected String url;

    public Page(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void waitForPageLoaded() {
        long startTime = System.currentTimeMillis();
        final String jsScript = "return document.readyState";
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript(jsScript)
                .equals("complete");

        WebDriverWait wait = new WebDriverWait(this.driver, PropertiesReader.readFromConfig("timeout.default"));

        try {
            wait.until(expectation);
        } catch (TimeoutException | NoSuchElementException e) {
            throw new SiteNotOpened(this.url, Math.round(System.currentTimeMillis() - (startTime / 1000)));
        }
    }

    public void navigateTo() {
        this.driver.get(this.url);
    }

    @Override
    public WebDriver getDriver() {
        return this.driver;
    }

    @Override
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
