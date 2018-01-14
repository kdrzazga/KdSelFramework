package org.kd.selframework;

import org.junit.Assert;
import org.junit.Test;
import org.kd.selframework.general.PropertiesReader;

public class PropertiesReaderTest {

    @Test
    public void testResourcesReading() {

        Assert.assertEquals("C:\\Users\\KDRZAZGA\\webdriver\\chromedriver.exe"
                , PropertiesReader.readFromConfig("driver.chrome.path"));
        Assert.assertEquals("C:\\Users\\KDRZAZGA\\webdriver\\geckodriver.exe"
                , PropertiesReader.readFromConfig("driver.firefox.path"));
    }
}
