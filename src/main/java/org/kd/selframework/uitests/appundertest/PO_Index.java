package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.lib.PropertiesReader;
import org.kd.selframework.core.pageobjects.LocatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.kd.selframework.core.pageobjects.Page;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class PO_Index extends Page {

    private final By inputFormsLinkNodeSelector = By.cssSelector("tree-indicator glyphicon glyphicon-chevron-right");
    private final By treeMenuSelector = By.id("treemenu");
    private final By examplesListSelector = By.tagName("li");

    private static WebElement inputFormsLinkNode;
    private static WebElement treeMenu;
    private static List<WebElement> examplesList;

    public PO_Index(WebDriver driver) {
        super(driver, PropertiesReader.readFromConfig("app-under-test.url"));
    }

    public void navigateTo() {
        super.navigateTo();

        logger.log("Navigating to " + this.url);
    }

    @Override
    public void findElements() {
        treeMenu = LocatorHelper.quietlyFindElement(this.driver, treeMenuSelector);

        if (treeMenu != null)
            examplesList = LocatorHelper.quietlyFindElementsWithin(this.driver, treeMenu, examplesListSelector);
    }

    public List<String> readMenuListItems() {
        return examplesList
                .stream()
                .map(WebElement::getText)
                .filter(item -> !item.isEmpty())
                .filter(item -> !item.contains("\n"))
                .collect(Collectors.toCollection(() -> new ArrayList<>(examplesList.size())));
    }

    public boolean isItemVisible(String item) {
        return Optional.ofNullable(getMenuItem(item))
                .map(WebElement::isDisplayed)
                .orElse(false);
    }

    public void clickMenuItem(String itemName) {
        WebElement inputFormsItem = getMenuItem(itemName);

        if (inputFormsItem == null)
            logger.error("Menu item " + itemName + " not found");
        else {
            inputFormsItem.click();
        }
    }

    private WebElement getMenuItem(String item) {
        if (!readMenuListItems().contains(item))
            return null;

        for (WebElement menuItem : examplesList) {
            if (menuItem.getText().equals(item))
                return menuItem;

        }
        return null;
    }

    public boolean getTreeMenuVisibility() {
        return LocatorHelper.isElementVisible(this.driver, treeMenuSelector);
    }

    public WebElement getTreeMenu() {
        return treeMenu;
    }
}