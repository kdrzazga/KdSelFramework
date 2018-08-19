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

    private final By treeMenuSelector = By.id("treemenu");
    private final By menuItemSelector = By.tagName("li");

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
        examplesList = findMenuItemsInMenu(treeMenu);
    }

    private List<WebElement> findMenuItemsInMenu(WebElement menu) {
        if (menu != null) {
            return LocatorHelper.quietlyFindElementsWithin(this.driver, menu, menuItemSelector);
        } else return new ArrayList<>(0);
    }

    private List<String> readMenuListItems(List<WebElement> list) {
        return list
                .stream()
                .map(WebElement::getText)
                .filter(item -> !item.isEmpty())
                .filter(item -> !item.contains("\n"))
                .collect(Collectors.toCollection(() -> new ArrayList<>(examplesList.size())));
    }

    public boolean isItemVisible(String item) {
        return Optional.ofNullable(getMenuItem(examplesList, item))
                .map(WebElement::isDisplayed)
                .orElse(false);
    }

    private void clickMenuItem(String itemName, List<WebElement> itemsList) {
        WebElement inputFormsItem = getMenuItem(itemsList, itemName);

        if (inputFormsItem == null)
            logger.error("Menu item " + itemName + " not found");
        else {
            inputFormsItem.click();
        }
    }

    private WebElement getMenuItem(List<WebElement> itemsList, String item) {
        if (!readMenuListItems(itemsList).contains(item))
            return null;

        for (WebElement menuItem : itemsList) {
            if (menuItem.getText().equals(item))
                return menuItem;

        }
        return null;
    }

    //private List

    public boolean getTreeMenuVisibility() {
        return LocatorHelper.isElementVisible(this.driver, treeMenuSelector);
    }

    public List<String> readSubItemsOfMenuItem(String item) {

        List<String> remainingElements = readMenuListItems(examplesList);
        remainingElements.removeAll(examplesList);
        return null;//remainingElements.re
    }

    public List<String> readExamplesMenuListItems() {
        return readMenuListItems(examplesList);
    }

    public void clickItemInExamplesMenuList(String itemName) {
        clickMenuItem(itemName, examplesList);
    }

    public WebElement getTreeMenu() {
        return treeMenu;
    }
}