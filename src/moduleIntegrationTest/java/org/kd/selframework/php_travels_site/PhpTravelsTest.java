package org.kd.selframework.php_travels_site;

import org.junit.Before;
import org.junit.Test;
import org.kd.selframework.general.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class PhpTravelsTest {

    private final PO_PhpTravels po_phpTravels;

    public PhpTravelsTest() {
        WebDriver driver = WebDriverFactory.createChromeDriver();
        po_phpTravels = new PO_PhpTravels(driver);
    }

    @Before
    public void init() {

    }

    @Test
    public void testOpenAndCloseBanner() {
        po_phpTravels.navigateTo();
        po_phpTravels.findElements();
        po_phpTravels.getDriver().close();
    }
}
