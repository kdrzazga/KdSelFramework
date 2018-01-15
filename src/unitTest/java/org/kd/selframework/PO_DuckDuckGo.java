package org.kd.selframework;

import org.kd.selframework.general.WebDriverFactory;
import org.kd.selframework.pageobjects.BasePage;

class PO_DuckDuckGo extends BasePage{

    public PO_DuckDuckGo() {
        super(WebDriverFactory.createHeadlessDriver());
        this.url = "https://duckduckgo.com/";
    }
}
