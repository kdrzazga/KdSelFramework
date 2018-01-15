package org.kd.selframework.pageobjects;

import org.openqa.selenium.WebDriver;

public abstract class Page {

    protected WebDriver driver;
    protected final String url;

    public Page(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void navigateTo() {
        this.driver.get(this.url);
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return this.driver.getTitle();
    }
}
