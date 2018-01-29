package org.kd.selframework.pageobjects;

import org.openqa.selenium.WebDriver;

public final class PO_Index extends Page{

    public PO_Index(WebDriver driver, String url) {
        super(driver, url);
    }

    public void navigateTo() {
        super.navigateTo();

        System.out.println("Navigating to {}"+ this.url);
    }

    @Override
    public void findElements() {

    }
}
