package org.kd.selframework;

import org.junit.Assert;
import org.junit.Test;
import org.kd.selframework.general.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class WebDriverFactoryTest {

    @Test
    public void testFirefoxDrive(){
        WebDriver ffDriver = WebDriverFactory.createFirefoxDriver();
        ffDriver.get("http://www.google.com");
        Assert.assertTrue(ffDriver.getTitle().contains("Google"));
        ffDriver.close();
    }
}
