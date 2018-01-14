package org.kd.selframework;

import org.kd.selframework.general.WebDriverFactory;
import org.kd.selframework.pageobjects.BasePage;

class DuckDuckGoPage extends BasePage{

    public DuckDuckGoPage() {
        super(WebDriverFactory.createHeadlessDriver());
        this.url = "https://duckduckgo.com/";
    }
}
