package org.kd.selframework.uitests.google_site;

import org.openqa.selenium.WebDriver;
import org.kd.selframework.core.pageobjects.Page;

public final class PO_Index extends Page {

    public PO_Index(WebDriver driver) {
        super(driver, "http://www.google.com");
    }

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
