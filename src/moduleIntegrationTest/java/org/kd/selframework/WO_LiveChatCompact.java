package org.kd.selframework;

import org.kd.selframework.general.WebDriverFactory;
import org.kd.selframework.pageobjects.LocatorHelper;
import org.kd.selframework.pageobjects.Widget;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class WO_LiveChatCompact extends Widget{

    private WebElement chatNowButton;

    public WO_LiveChatCompact(WebDriver driver){
        super(driver);
        //this.chatNowButton = LocatorHelper.findClickableElement(driver, By.)
    }
}
