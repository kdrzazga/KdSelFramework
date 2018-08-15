package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.lib.PropertiesReader;
import org.kd.selframework.core.pageobjects.LocatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.kd.selframework.core.pageobjects.Page;
import org.openqa.selenium.WebElement;

public final class PO_Index extends Page {

    private final By inputFormsLinkNodeSelector = By.className("tree-indicator glyphicon glyphicon-chevron-right");

    private static WebElement inputFormsLinkNode;

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
        inputFormsLinkNode = LocatorHelper.findClickableElement(this.driver, inputFormsLinkNodeSelector);
    }
}
