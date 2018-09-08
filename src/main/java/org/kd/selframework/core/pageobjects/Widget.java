package org.kd.selframework.core.pageobjects;

import org.openqa.selenium.WebDriver;

public abstract class Widget implements WebDriveable {

    protected WebDriver driver;
    @Override
    public void findElements() {

    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
