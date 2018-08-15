package org.kd.selframework.core.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        dryRun = false,
        features = "src/test/resources/features",
        glue = "src/main/java/uitests.stepdefs"
)

public class TestRunner {

}
