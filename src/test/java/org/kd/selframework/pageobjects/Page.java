package org.kd.selframework.pageobjects;

import org.openqa.selenium.WebDriver;

public interface Page {

    void navigateTo();
    /*WebElement quietlyFindElement(By locator, String locatorArg);
    WebElement quietlyFindElement(By locator, String locatorArg, int timeout);
    List<WebElement> quietlyFindElements(By locator, String locatorArg);
    List<WebElement> quietlyFindElements(By locator, String locatorArg, int timeout);
*/
    WebDriver getDriver();
    String getTitle();
}
