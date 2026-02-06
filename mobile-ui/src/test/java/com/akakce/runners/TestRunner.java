package com.akakce.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.akakce.steps", "com.akakce.hooks"},
        //tags = "@intentionalFail",
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "html:target/cucumber-report/index.html"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
