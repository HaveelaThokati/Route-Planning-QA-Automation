// Author: haveela | Project: Route_Planning_Automation
package AppHooks;

import Driver.WebdriverInitializer;
import Utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.Properties;

public class ApplicationHooks {

    private WebdriverInitializer driverFactory;
    private WebDriver driver;
    private Properties prop;

    /**
     * Runs first: loads src/test/Resources/Config/config.properties.txt
     */
    @Before(order = 0)
    public void getProperty() {
        ConfigReader configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    /**
     * Runs second: starts the browser and opens the application URL.
     * Expected keys in config.properties.txt:
     *   browser=chrome
     *   url=https://dev.rapid.pragathirail.ai/rad/
     */
    @Before(order = 1)
    public void launchBrowser() {
        String browserName = prop.getProperty("browser", "chrome").trim().toLowerCase();
        // Uses 'url' from config.properties.txt; falls back to the RAD dev URL if the key is missing
        String url = prop.getProperty("url", "https://dev.rapid.pragathirail.ai/rad/");

        if (url.trim().isEmpty()) {
            throw new IllegalStateException(
                    "'url' is empty in src/test/Resources/Config/config.properties.txt");
        }

        driverFactory = new WebdriverInitializer();
        driver = driverFactory.init_driver(browserName);

        // Keep the implicit wait short; use WebDriverWait for slow elements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

        driver.get(url.trim());
    }

    /**
     * Cucumber runs @After hooks from the highest order to the lowest,
     * so the screenshot (order 1) is taken before the browser is closed (order 0).
     */
    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && driver != null) {
            try {
                String screenshotName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", screenshotName);
            } catch (Exception e) {
                System.out.println("Could not capture screenshot: " + e.getMessage());
            }
        }
    }

    @After(order = 0)
    public void quitBrowser() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } finally {
            WebdriverInitializer.tlDriver.remove();
        }
    }
}