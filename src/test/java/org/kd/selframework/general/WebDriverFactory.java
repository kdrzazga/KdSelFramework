package org.kd.selframework.general;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Collections;

public class WebDriverFactory {

    private WebDriverFactory() {
    }

    static {
        System.setProperty("webdriver.chrome.driver", PropertiesReader.readFromConfig("driver.chrome.path"));
        System.setProperty("webdriver.gecko.driver", PropertiesReader.readFromConfig("driver.firefox.path"));
    }

    public static WebDriver createChromeDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Collections.singletonList("--incognito"));

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");

        return new ChromeDriver(capabilities);
    }

    public static WebDriver createFirefoxDriver() {
        return new FirefoxDriver();
    }

    public static WebDriver createHeadlessDriver() {
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.setJavascriptEnabled(true);
        return driver;
    }
}
