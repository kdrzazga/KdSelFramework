package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.pageobjects.Widget;
import org.kd.selframework.core.utils.CustomWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;

import static org.kd.selframework.core.pageobjects.LocatorHelper.quietlyFindElementWithinElement;

class WO_ProductColumn extends Widget {

    private WebElement thisColumn;

    private WebElement addToCartButton;
    private WebElement loaderAnimation;
    private WebElement productRating;

    private final By columnSelector = By.className("wpsc_product_title");

    private final By buttonSelector = By.name("Buy");
    private final By loaderAnimationSelector = By.className("wpsc_loading_animation");
    private final By productRatingSelector = By.xpath("//div[contains(@class, 'product_rating')");

    WO_ProductColumn(WebDriver driver, List<WebElement> columns, String title) {
        this.driver = driver;
        findThisColumn(columns, title);
        findElements();
    }

    public void clickAddToCartButton() {
        Optional<WebElement> button = Optional.ofNullable(addToCartButton);
        button.ifPresent(WebElement::click);
        CustomWait.waitForElementVisible(loaderAnimation);
        CustomWait.waitForElementHidden(loaderAnimation);
    }

    private void findThisColumn(List<WebElement> elements, String title) {

        for (WebElement currentColumn : elements) {

            String titleCandidate =
                    quietlyFindElementWithinElement(driver, columnSelector, currentColumn).getText();

            if (title.equals(titleCandidate)) {
                thisColumn = currentColumn;
                return;
            }
        }
    }

    public void findElements() {
        this.addToCartButton = quietlyFindElementWithinElement(driver, buttonSelector, thisColumn);
        this.loaderAnimation = quietlyFindElementWithinElement(driver, loaderAnimationSelector, thisColumn);
        this.productRating = quietlyFindElementWithinElement(driver, productRatingSelector, thisColumn);
    }
}
