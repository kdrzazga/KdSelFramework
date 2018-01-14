package org.kd.selframework.pageobjects;

import org.openqa.selenium.WebDriver;

public final class PO_Index extends BasePage{

    public PO_Index(String url, WebDriver driver) {
        super(driver);
        this.url = url;
    }

    public void navigateTo() {
        super.navigateTo();

        System.out.println("Navigating to {}"+ this.url);
    }
}
