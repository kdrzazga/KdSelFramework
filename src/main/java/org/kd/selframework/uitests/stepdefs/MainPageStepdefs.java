package org.kd.selframework.uitests.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.When;
import org.kd.selframework.core.utils.TestLogger;
import org.kd.selframework.core.utils.TestLoggerSingleton;
import org.kd.selframework.core.utils.WebDriverSingleton;
import org.openqa.selenium.WebDriver;

public class MainPageStepdefs {

    private final TestLogger logger = TestLoggerSingleton.getInstance();
    private WebDriver driver = WebDriverSingleton.getInstance();

    @When("^I navigate to index site$")
    public void navigate(){
        logger.log("navigate");
    }

    @After
    public void tearDown() {
        WebDriverSingleton.close();
    }
}
