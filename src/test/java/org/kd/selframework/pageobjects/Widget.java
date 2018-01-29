package org.kd.selframework.pageobjects;

import org.openqa.selenium.WebDriver;

public class Widget implements WebDriveable {
    protected WebDriver driver;

    public Widget(WebDriver driver){
        this.driver = driver;
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void findElements() {

    }
}
