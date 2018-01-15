package org.kd.selframework;

import org.kd.selframework.pageobjects.Page;
import org.openqa.selenium.WebDriver;

public class PO_PhpTravels extends Page {

    private final WO_LiveChatCompact wo_liveChatCompact;
    private final WO_LiveChatFull wo_liveChatFull;

    public PO_PhpTravels(WebDriver driver) {
        super(driver, "https://phptravels.com/demo/");
        this.wo_liveChatCompact = new WO_LiveChatCompact();
        this.wo_liveChatFull = new WO_LiveChatFull();
    }
}
