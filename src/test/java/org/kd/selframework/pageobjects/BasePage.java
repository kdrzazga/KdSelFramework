package org.kd.selframework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;

abstract class BasePage implements Page {
    protected WebDriver driver;
    protected String url;

    BasePage() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");

        driver = new ChromeDriver(capabilities);
    }

    public void navigateTo() {
        driver.get(url);
    }


}
