package org.kd.selframework.core.pageobjects;

import org.apache.commons.io.FileUtils;
import org.kd.selframework.core.exceptions.SiteNotOpenedException;
import org.kd.selframework.core.lib.PropertiesReader;
import org.kd.selframework.core.lib.TestLogger;
import org.kd.selframework.core.utils.WindowUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.IntStream;

public abstract class Page implements WebDriveable {

    protected WebDriver driver;
    protected final String url;
    protected final TestLogger logger = new TestLogger();

    public Page(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void load() {
        final int step = 500;

        Integer timeoutSeconds = PropertiesReader.readFromConfig("timeout.loadpage");
        IntStream.range(0, timeoutSeconds * 1000 / step)
                .forEach(i -> {
                    if (this.isLoaded())
                        return;

                    try {
                        Thread.sleep(step);
                        this.waitForPageLoaded();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        throw new SiteNotOpenedException(this.url, timeoutSeconds);
    }


    public void waitForPageLoaded() {
        long startTime = System.currentTimeMillis();
        final String jsScript = "return document.readyState";
        ExpectedCondition<Boolean> pageLoadComplete = driver -> ((JavascriptExecutor) Objects.requireNonNull(driver)).executeScript(jsScript)
                .equals("complete");

        WebDriverWait wait = new WebDriverWait(this.driver, ((Integer) PropertiesReader.readFromConfig("timeout.loadpage")).longValue());

        try {
            wait.until(pageLoadComplete);
        } catch (TimeoutException e) {
            throw new SiteNotOpenedException(this.url, Math.round(System.currentTimeMillis() - startTime / 1000));
        }
    }

    public void navigateTo() {
        this.driver.get(this.url);
    }

    public void refresh(){
        this.driver.navigate().refresh();
        load();
    }

    public void navigateBack(){
        this.driver.navigate().back();
        load();
    }

    public void takeScreenshot(String filePath){
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isLoaded() {
        waitForPageLoaded();
        return driver.getCurrentUrl().contains(this.getUrl());
    }

    private void waitForPageLoaded(){
        waitForPageLoaded(this.driver, this.url);
    }


    public void openInNewTab() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open(arguments[0], '_blank');", url);
        WindowUtils.switchWindow(driver, url, true);
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
