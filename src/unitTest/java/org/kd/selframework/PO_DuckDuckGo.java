package org.kd.selframework;

import org.kd.selframework.general.WebDriverFactory;
import org.kd.selframework.pageobjects.LocatorHelper;
import org.kd.selframework.pageobjects.Page;
import org.openqa.selenium.WebDriver;

class PO_DuckDuckGo extends Page {

    PO_DuckDuckGo(WebDriver driver) {
        super(driver, "https://duckduckgo.com/");
    }
}
