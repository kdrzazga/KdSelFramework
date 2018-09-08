package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.pageobjects.Widget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.kd.selframework.core.pageobjects.LocatorHelper.quietlyFindElementWithinElement;

public class WO_ProductCheckout extends Widget {

    private WebElement thisProductRow;

    private WebElement productName;
    private WebElement quantityTextbox;
    private WebElement updateButton;
    private WebElement priceSpan;
    private WebElement totalSpan;
    private WebElement removeButton;

    private final By productNameSelector = By.xpath("//td[contains(@class, 'wpsc_product_name wpsc_product_name')]/a");
    private final By quantityTextboxSelector = By.xpath("//input[@name = 'quantity']");
    //private final By updateButtonSelector;
    private final By priceSpanSelector = By.xpath("//span/[@class='pricedisplay']");

    //private final By totalSpanSelector;
    //private final By removeButtonSelector;

    WO_ProductCheckout(WebDriver driver, List<WebElement> productRows, String productName) {
        this.driver = driver;
        findThisProductRow(productRows, productName);
        findElements();
    }

    private void findThisProductRow(List<WebElement> productRows, String productName) {

    }

    @Override
    public void findElements() {
        productName = quietlyFindElementWithinElement(driver, priceSpanSelector, thisProductRow);
        quantityTextbox = quietlyFindElementWithinElement(driver, quantityTextboxSelector, thisProductRow);

    }
}
