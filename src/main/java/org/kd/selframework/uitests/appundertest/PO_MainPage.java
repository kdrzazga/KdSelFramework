package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.kd.selframework.core.pageobjects.Page;

public final class PO_MainPage extends Page {

    public PO_MainPage(WebDriver driver) {
        super(driver, PropertiesReader.readFromConfig("app-under-test.url"));
    }

    public void navigateTo() {
        super.navigateTo();

        logger.log("Navigating to " + this.url);
    }

    @Override
    public void findElements() {

    }
}