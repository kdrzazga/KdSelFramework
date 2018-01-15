package org.kd.selframework;

import org.junit.Before;
import org.junit.Test;
import org.kd.selframework.general.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class PhpTravelsTest {

    private final PO_PhpTravels po_phpTravels;
    private final WebDriver driver;

    public PhpTravelsTest(){
        driver = WebDriverFactory.createChromeDriver();
        po_phpTravels = new PO_PhpTravels(driver);
    }

    @Before
    public void init(){

    }

    @Test
    public void testOpenAndCloseBanner(){

    }
}
