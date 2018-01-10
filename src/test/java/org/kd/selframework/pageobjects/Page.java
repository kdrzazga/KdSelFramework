package org.kd.selframework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

interface Page {

    void navigateTo();
    WebElement quietlyFindElement(By locator);
    List<WebElement> quietlyFindElements(By locator);
}
