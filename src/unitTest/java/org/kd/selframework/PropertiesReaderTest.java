package org.kd.selframework;

import org.junit.Assert;
import org.junit.Test;
import org.kd.selframework.general.PropertiesReader;

public class PropertiesReaderTest {

    @Test
    public void testResourcesReading() {
        Integer I = PropertiesReader.readFromConfig("someKey");
        String S = PropertiesReader.readFromConfig("driver.chrome.path");
        Double D = PropertiesReader.readFromConfig("lista");
        Assert.assertEquals(2, I.intValue());
        Assert.assertEquals("C:\\Users\\KDRZAZGA\\webdriver\\chromedriver.exe", S);
    }
}
