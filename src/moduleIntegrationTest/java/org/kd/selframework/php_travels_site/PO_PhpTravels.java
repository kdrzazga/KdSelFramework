package org.kd.selframework.php_travels_site;

import org.kd.selframework.pageobjects.LocatorHelper;
import org.kd.selframework.pageobjects.Page;
import org.kd.selframework.pageobjects.WebDriveable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

final class PO_PhpTravels extends Page implements WebDriveable {

    private final WO_LiveChatCompact wo_liveChatCompact;
    private final WO_LiveChatFull wo_liveChatFull;
    private WebElement operatorAvatar;
    private WebElement viewOfflineForm;
    private WebElement viewChat;

    private boolean isChatActive;


    PO_PhpTravels(WebDriver driver) {
        super(driver, "https://phptravels.com/demo/");
        this.wo_liveChatCompact = new WO_LiveChatCompact(driver);
        this.wo_liveChatFull = new WO_LiveChatFull(driver);

    }

    @Override
    public void findElements() {
        this.operatorAvatar = LocatorHelper.quietlyFindElement(driver, By.id("livechat-compact-container"));
        if (LocatorHelper.isElementPresent(driver, By.id(".//*[@id='view-offline-form']"))) {
            viewOfflineForm = driver.findElement(By.id("view-offline-form"));
        }
        this.viewOfflineForm = LocatorHelper.quietlyFindElement(driver, By.xpath(".//*[@id='view-offline-form']"));
        this.viewChat = LocatorHelper.quietlyFindElement(driver, By.xpath(".//*[@id='view-chat']"));
        isChatActive = this.checkChatVisibility();
    }

    private boolean checkChatVisibility() {
        return (!this.viewChat.getCssValue("display").equals("none"));
    }
}
