package org.kd.selframework.general;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;

public class WebDriverFactory {

    private WebDriverFactory() {
    }

    static {
        System.setProperty("webdriver.chrome.driver", PropertiesReader.readFromConfig("driver.chrome.path"));
        System.setProperty("webdriver.gecko.driver", PropertiesReader.readFromConfig("driver.firefox.path"));
    }

    public static WebDriver createChromeDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");

        return new ChromeDriver(capabilities);
    }

    public static WebDriver createFirefoxDriver() {
        return new FirefoxDriver();
    }
}
