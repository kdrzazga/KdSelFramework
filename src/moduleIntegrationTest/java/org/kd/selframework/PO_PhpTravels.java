package org.kd.selframework;

import org.kd.selframework.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;

public class PO_PhpTravels extends BasePage{

    WO_LiveChatCompact wo_liveChatCompact;
    WO_LiveChatFull wo_liveChatFull;

    public PO_PhpTravels(WebDriver driver){
        super(driver);
    }
}
