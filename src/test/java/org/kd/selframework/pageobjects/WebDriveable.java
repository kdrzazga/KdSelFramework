package org.kd.selframework.pageobjects;

import org.openqa.selenium.WebDriver;

public interface WebDriveable {
    WebDriver getDriver();
    void setDriver(WebDriver driver);
    void findElements();
}
