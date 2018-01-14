package org.kd.selframework;

import org.junit.Assert;
import org.junit.Test;
import org.kd.selframework.general.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class WebDriverFactoryTest {

    @Test
    public void testChromeDrive(){
        WebDriver chromeDriver = WebDriverFactory.createChromeDriver();
        chromeDriver.get("http://www.google.com");
        Assert.assertTrue(chromeDriver.getTitle().contains("Google"));
        chromeDriver.close();
    }
}
