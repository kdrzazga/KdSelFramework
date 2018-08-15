package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.lib.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.kd.selframework.core.pageobjects.Page;

public final class PO_Index extends Page {

    public PO_Index(WebDriver driver) {
        super(driver, PropertiesReader.readFromConfig("app-under-test.url"));
    }

    @Override
    public boolean isLoaded() {
        waitForPageLoaded();
        return driver.getCurrentUrl().contains(this.getUrl());
    }

    public void navigateTo() {
        super.navigateTo();

        System.out.println("Navigating to {}"+ this.url);
    }

    @Override
    public void findElements() {

    }
}
