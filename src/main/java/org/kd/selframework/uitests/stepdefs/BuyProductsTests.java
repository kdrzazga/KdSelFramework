package org.kd.selframework.uitests.stepdefs;

import org.kd.selframework.core.utils.TestLogger;
import org.kd.selframework.core.utils.TestLoggerSingleton;
import org.kd.selframework.core.utils.WebDriverSingleton;
import org.kd.selframework.uitests.appundertest.PO_ProductCategoryCheckoutPage;
import org.kd.selframework.uitests.appundertest.PO_ProductCategoryPage;

import org.kd.selframework.uitests.appundertest.Products;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.kd.selframework.uitests.appundertest.Products.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BuyProductsTests {

    private final PO_ProductCategoryPage productCategoryPage
            = new PO_ProductCategoryPage(WebDriverSingleton.getInstance());
    private final PO_ProductCategoryCheckoutPage productCategoryCheckoutPage
            = new PO_ProductCategoryCheckoutPage(WebDriverSingleton.getInstance());
    private final TestLogger logger = TestLoggerSingleton.getInstance();

    @BeforeMethod
    public void initTest() {
        productCategoryPage.navigateTo();
        productCategoryPage.waitForPageLoaded();
        productCategoryPage.findElements();
    }

    @Test(testName = "test adding items to cart")
    public void testAddingItemsToCart() {
        logger.log("Test adding items to cart");

        productCategoryPage.addToCart(IPhone5);
        productCategoryPage.addToCart(AppleTV);
        productCategoryPage.addToCart(AppleTV);
        productCategoryPage.addToCart(MagicMouse);
        productCategoryPage.addToCart(IPhone5);
        assertEquals(productCategoryPage.readCartItemsCount().intValue(), 5);
    }

    @Test(testName = "test cart content")
    public void testCartContent() {
        logger.log("Test cart content");
        List<Products> productsToBuy = Arrays.asList(iPodNanoBlue, MagicMouse, AppleTV);
        addToCartAndCheckout(productsToBuy);

        List<String> itemsInCart = productCategoryCheckoutPage.readItemsInCart();

        assertEquals(3, itemsInCart.size());
        productsToBuy
                .stream()
                .map(Products::getFullName)
                .forEach(
                        product -> assertTrue(itemsInCart.contains(product), product + " was not added to cart.")
                );
    }

    @Test(testName = "update order during checkout")
    public void testOrderUpdateInCheckout() {
        logger.log("test update order during checkout");
        List<Products> productsToBuy = Arrays.asList(IPhone5, AppleIphone4s, MagicMouse, AppleIpad2);
        addToCartAndCheckout(productsToBuy);

        productCategoryPage.changeAmountOf(MagicMouse, 4);

        List<String> namesOfProductsInCart = productCategoryCheckoutPage.readItemsInCart();

        assertTrue(namesOfProductsInCart.containsAll(productsToBuy.stream().map(Products::getFullName).map(String::toString).collect(Collectors.toList())));

    }

    @AfterMethod
    public void clearCookies() {
        deleteCookies();
    }

    @AfterClass
    public void closeBrowser() {
        WebDriverSingleton.close();
    }

    private void deleteCookies() {
        logger.log("Deleting cookies");
        WebDriverSingleton.getInstance().manage().deleteAllCookies();
    }

    private void addToCartAndCheckout(List<Products> productsToBuy) {
        productsToBuy.forEach(productCategoryPage::addToCart);
        productCategoryPage.goToCart();
        productCategoryCheckoutPage.findElements();
    }
}
