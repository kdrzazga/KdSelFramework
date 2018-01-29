package org.kd.selframework.simple_page;

import org.junit.Before;
import org.junit.Test;
import org.kd.selframework.general.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class SimplePageTest {
    private final PO_SimplePage po_phpTravels;

    public SimplePageTest() {
        WebDriver driver = WebDriverFactory.createChromeDriver();
        po_phpTravels = new PO_SimplePage(driver);
    }

    @Before
    public void init() {

    }

    @Test
    public void testStub(){
        throw new RuntimeException("Not implemented yet");
    }
}
