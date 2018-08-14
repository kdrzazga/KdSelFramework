package org.kd.selframework.core;

import org.openqa.selenium.WebDriver;

public abstract class StandaloneTest {

    protected final WebDriver driver;

    protected StandaloneTest(WebDriver driver) {
        this.driver = driver;
    }


}
