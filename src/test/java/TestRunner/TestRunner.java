// Author: haveela | Project: Route_Planning_Automation
package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;

import java.io.File;

@CucumberOptions(
        features = {"src/test/Resources/FeatureFiles/OriginAndDestination.feature"},
        glue = {"StepDefinitions", "AppHooks"},
        plugin = {"pretty",
                "summary",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:test-output/",
                "html:target/cucumber-report.html"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterSuite
    public void printReportPath() {
        System.out.println("Cucumber report: " + new File("target/cucumber-report.html").toURI());
    }
}