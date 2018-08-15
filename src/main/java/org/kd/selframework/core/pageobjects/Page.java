package org.kd.selframework.core.pageobjects;

import org.apache.commons.io.FileUtils;
import org.kd.selframework.core.exceptions.SiteNotOpened;
import org.kd.selframework.core.lib.PropertiesReader;
import org.kd.selframework.core.lib.TestLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Objects;

public abstract class Page implements WebDriveable {

    protected WebDriver driver;
    protected final String url;
    protected final TestLogger logger = new TestLogger();

    public Page(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void load(){
        while (!this.isLoaded())
            this.waitForPageLoaded();
    }

    public abstract boolean isLoaded();

    public void waitForPageLoaded() {
        long startTime = System.currentTimeMillis();
        final String jsScript = "return document.readyState";
        ExpectedCondition<Boolean> pageLoadComplete = driver -> ((JavascriptExecutor) Objects.requireNonNull(driver)).executeScript(jsScript)
                .equals("complete");

        WebDriverWait wait = new WebDriverWait(this.driver, ((Integer)PropertiesReader.readFromConfig("timeout.default")).longValue());

        try {
            wait.until(pageLoadComplete);
        } catch (TimeoutException | NoSuchElementException e) {
            throw new SiteNotOpened(this.url, Math.round(System.currentTimeMillis() - startTime / 1000));
        }
    }

    public void navigateTo() {
        this.driver.get(this.url);
    }

    public void refresh(){
        this.driver.navigate().refresh();
    }

    public void navigateBack(){
        this.driver.navigate().back();
    }

    public void takeScreenshot(String filePath){
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public String getUrl() {
        return this.url;
    }
}
