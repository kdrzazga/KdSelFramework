package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.pageobjects.Page;
import org.kd.selframework.core.utils.PropertiesReader;
import org.kd.selframework.core.utils.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.*;
import java.util.stream.Collectors;

import static org.kd.selframework.core.pageobjects.LocatorHelper.quietlyFindElementWithinElement;

public class PO_ProductCategoryCheckoutPage extends Page {

    @FindBy(how = How.XPATH, using = "//table[@class='checkout_cart']/tbody/tr")
    private List<WebElement> itemsGroupTableRows;

    private List<WO_ProductCheckout> productRowWidgets;

    @FindBy(how = How.CLASS_NAME, using = "step2")
    private WebElement continueButton;

    private static final String cartItemLocatorPrefix = "//td[contains(@class, 'wpsc_product_name wpsc_product_name_";

    public PO_ProductCategoryCheckoutPage(WebDriver driver) {
        super(driver, PropertiesReader.readString("product-category-checkout.url"));
    }

    List<WebElement> readProductsFromRows(List<WebElement> itemsGroupTableRows) {
        List<WebElement> products = new Vector<>();
        Iterator<WebElement> itemRowsIter = itemsGroupTableRows.iterator();
        short rowIndex = 0;
        while (itemRowsIter.hasNext()) {
            By itemLocator = By.xpath(cartItemLocatorPrefix + rowIndex + "')]/a");
            WebElement link = quietlyFindElementWithinElement(WebDriverSingleton.getInstance(), itemLocator, itemRowsIter.next());
            Optional<WebElement> text = Optional.ofNullable(link);
            text.ifPresent(products::add);
            rowIndex++;
        }
        return products;
    }

    public List<String> readItemsInCart() {
        return readProductsFromRows(itemsGroupTableRows)
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .map(String::toString)
                .collect(Collectors.toList());
    }

    public void clickContinue() {
        if (continueButton != null)
            continueButton.click();
        else
            logger.error("Continue Button not found.");
    }

    @Override
    public void findElements() {
        PageFactory.initElements(driver, this);
        productRowWidgets = new Vector<>(itemsGroupTableRows.size());
/*
        Arrays.asList(Products.values()).forEach(product ->
                itemsGroupTableRows.forEach(row -> {
                    if (row.)
                    WO_ProductCheckout rowWidget
                            = new WO_ProductCheckout(driver, itemsGroupTableRows, product.getFullName());
                    productRowWidgets.add(rowWidget);
                })
        );
        */
    }
}
