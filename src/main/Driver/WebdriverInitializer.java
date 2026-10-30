// Author: haveela | Project: Route_Planning_Automation
package Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class WebdriverInitializer {
	public WebDriver driver;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the thradlocal driver on the basis of given
	 * browser
	 *
	 * @param browser
	 * @return this will return tldriver.
	 */
	public WebDriver init_driver(String browser) {

		System.out.println("browser value is: " + browser);

		if (browser.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("window-size=1400,800");
//			options.addArguments("headless");

			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(options));

		} else if (browser.equals("firefox")) {

			FirefoxOptions options = new FirefoxOptions();

			options.addArguments("window-size=1400,800");
//    options.setHeadless(true);
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(options));
		} else if (browser.equals("safari")) {
			tlDriver.set(new SafariDriver());
		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
//			edgeOptions.addArguments("--headless");
//	       WebDriverManager.edgedriver();

			tlDriver.set(new EdgeDriver(edgeOptions));
		}
		else {
			System.out.println("Please pass the correct browser value: " + browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();

	}

	/**
	 * this is used to get the driver with ThreadLocal
	 *
	 * @return
	 */
	public static synchronized WebDriver getDriver() {

		return tlDriver.get();
	}
}
