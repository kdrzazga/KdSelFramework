package org.kd.selframework.simple_page;

import org.kd.selframework.pageobjects.Page;
import org.kd.selframework.pageobjects.WebDriveable;
import org.openqa.selenium.WebDriver;

public class PO_SimplePage extends Page implements WebDriveable {

    PO_SimplePage(WebDriver driver) {
        super(driver, "https://phptravels.com/demo/");
    }
    @Override
    public void findElements() {

    }
}
