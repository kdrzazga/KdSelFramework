package org.kd.selframework.runners;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "test/resources/features",
        glue = "test/java/org.kd.selframework.stepdefs"
)

public class TestRunner {

}
