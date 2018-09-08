package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.pageobjects.Page;
import org.kd.selframework.core.utils.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class PO_ProductCategoryPage extends Page {

    private WO_ProductColumn iPhone5Widget;
    private WO_ProductColumn magicMouseWidget;
    private WO_ProductColumn iPodNanoWidget;
    private WO_ProductColumn appleTvWidget;
    private WO_ProductColumn iPhone4Widget;
    private WO_ProductColumn iPad2Widget;

    private final Map<Products, WO_ProductColumn> nameWidgetMap = new Hashtable<>();

    /*@FindBy(how = How.XPATH, using = "//div[contains(@class, 'product_rating') and contains(@class, '32')]")
    //webdriver doesn't support spaces in selectors
    private WebElement iPhone5Rating;*/

    @FindBy(how = How.CLASS_NAME, using = "productcol")
    private List<WebElement> productColumns;

    @FindBy(how = How.NAME, using = "Buy")
    private List<WebElement> addToCartButtons;

    @FindBy(how = How.XPATH, using = "//div[@id='header_cart']")
    private WebElement goToCartLink;

    @FindBy(how = How.XPATH, using = "//div[@id='header_cart']//em[@class='count']")
    private WebElement cartItemsCount;

    private TestLogger logger = TestLoggerSingleton.getInstance();

    public PO_ProductCategoryPage(WebDriver driver) {
        super(driver, PropertiesReader.readString("product-category.url"));
    }

    public void addToCart(Products product) {
        int oldItemsAmount = this.readCartItemsCount();
        WO_ProductColumn productWidget =
                nameWidgetMap.get(product);

        if (productWidget != null) {
            productWidget.clickAddToCartButton();
            waitForCartItemsCountDifferentThan(oldItemsAmount, 10);
        } else
            logger.error("Widget with Product: " + product.getFullName() + " not found.");
    }

    public Integer readCartItemsCount() {
        return readCartItemsCount(1);
    }

    private Integer readCartItemsCount(int time) {
        this.waitForPageLoaded();
        CustomWait.wait(time); //cart content is not updated immediately

        try {
            return Integer.parseInt(cartItemsCount.getText());
        } catch (NullPointerException | NumberFormatException exc) {
            logger.error("Cannot read items count " + exc.getMessage());
            return 0;
        }
    }

    public void goToCart() {
        goToCartLink.click();
    }

    private void waitForCartItemsCountDifferentThan(int unwantedItemsCount, int timeout) {
        Integer currentCartItemCount = readCartItemsCount(1);

        while (currentCartItemCount == unwantedItemsCount) {
            currentCartItemCount = readCartItemsCount(1);
            if (timeout-- < 0)
                return;
        }
    }

    @Override
    public void findElements() {
        this.waitForPageLoaded();
        PageFactory.initElements(driver, this);
        iPhone5Widget = new WO_ProductColumn(driver, productColumns, Products.IPhone5.getFullName());
        magicMouseWidget = new WO_ProductColumn(driver, productColumns, Products.MagicMouse.getFullName());
        iPodNanoWidget = new WO_ProductColumn(driver, productColumns, Products.iPodNanoBlue.getFullName());
        appleTvWidget = new WO_ProductColumn(driver, productColumns, Products.AppleTV.getFullName());
        iPhone4Widget = new WO_ProductColumn(driver, productColumns, Products.AppleIphone4s.getFullName());
        iPad2Widget = new WO_ProductColumn(driver, productColumns, Products.AppleIpad2.getFullName());
        initNameWidgetMap();
    }

    private void initNameWidgetMap() {
        nameWidgetMap.put(Products.IPhone5, iPhone5Widget);
        nameWidgetMap.put(Products.MagicMouse, magicMouseWidget);
        nameWidgetMap.put(Products.iPodNanoBlue, iPodNanoWidget);
        nameWidgetMap.put(Products.AppleTV, appleTvWidget);
        nameWidgetMap.put(Products.AppleIphone4s, iPhone4Widget);
        nameWidgetMap.put(Products.AppleIpad2, iPad2Widget);
    }

    public void changeAmountOf(Products product, int amount) {
        WO_ProductColumn widget = nameWidgetMap.get(product);
        //widget.
    }


}
