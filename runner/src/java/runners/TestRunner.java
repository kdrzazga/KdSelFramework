package runners;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/resources/features",
        glue = "src/java/stepdefs"
)

public class TestRunner {

}
