package org.kd.selframework.pageobjects;

import org.kd.selframework.general.WebDriverFactory;
import org.openqa.selenium.WebDriver;

abstract class BasePage implements Page {
    protected final WebDriver driver;
    protected String url;

    BasePage() {
        driver = WebDriverFactory.createChromeDriver();
    }

    public void navigateTo() {
        driver.get(url);
    }


}
