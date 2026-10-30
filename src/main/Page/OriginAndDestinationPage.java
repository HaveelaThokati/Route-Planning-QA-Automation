package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class OriginAndDestinationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By generateNewLayoutButton = By.xpath("//button//span[text()='Generate New Layout']");
    private By configureRouteHeader = By.xpath("//div//span[text()='Create New Project']");
    private By routeNamePath = By.xpath("//input[@id='route-name-input']");
    private By createButtonPath = By.xpath("//button//span[text()='Create']");
    private By originTextField = By.xpath("//input[@placeholder='Origin']");
    private By destinationField = By.xpath("//input[@placeholder='Destination']");
    private By addIntermediatePointButton = By.xpath("//button//span[text()='Add intermediate points']");
    private By intermediatePointPath = By.xpath("//div//input[@placeholder='Add intermediate point...']");
    private By suggestionList = By.xpath("//div[@data-pc-section='listcontainer']");

    private By enterCoordinatesHeader = By.xpath("//div//h3[text()='Enter Coordinates']");
    private By zoomInIcon = By.xpath("//button[@title='Zoom in']");
    private By zoomOutIcon = By.xpath("//button[@title='Zoom out']");
    private By refreshIcon = By.xpath("//button[@title='Reset map']");
    private By fullScreen = By.xpath("//button[@title='Fullscreen']");

    private By nextButton = By.xpath("//p-button[@id='workflow-next-button']");
    private By toastMessage = By.xpath("//div[@class='p-toast-detail']");
    private By nextScreenHeader = By.xpath("//div//h2[text()='GIS Spatial Data Layers']");


    public OriginAndDestinationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnGenerateNewLayoutButton() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(generateNewLayoutButton).click();


    }

    public void configureTheRouteName(String routeName) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        if (driver.findElements(configureRouteHeader).size() > 0) {
            driver.findElement(routeNamePath).sendKeys(routeName);
        }
    }

    public void clickOnCreateButton() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        if (driver.findElement(createButtonPath).isEnabled()) {
            driver.findElement(createButtonPath).click();
        }
    }

    public void verifyTheEnterOriginDestinationPageComponents() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Assert.assertTrue(driver.findElement(enterCoordinatesHeader).isDisplayed(), "Header not displayed");
        Assert.assertTrue(driver.findElement(originTextField).isDisplayed(), "Origin Field is displayed");
        Assert.assertTrue(driver.findElement(destinationField).isDisplayed(), "Destination is displayed");
        Assert.assertTrue(driver.findElement(addIntermediatePointButton).isDisplayed(), "Intermediate button is not displayed");
    }

    public void enterCoordinates(String origin, String destination, String intermediatePoints) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Enter Origin
        driver.findElement(originTextField).sendKeys(origin);

        if (!origin.trim().isEmpty()) {

            By originSuggestion = By.xpath("//li//span[normalize-space()='" + origin.trim() + "']");

            wait.until(ExpectedConditions.elementToBeClickable(originSuggestion)).click();
        }

        // Enter Destination
        driver.findElement(destinationField).sendKeys(destination);

        if (!destination.trim().isEmpty()) {

            By destinationSuggestion = By.xpath("//li//span[normalize-space()='" + destination.trim() + "']");

            wait.until(ExpectedConditions.elementToBeClickable(destinationSuggestion)).click();
        }

        // Enter Intermediate Point
        if (!intermediatePoints.isEmpty()) {

            driver.findElement(addIntermediatePointButton).click();

            driver.findElement(intermediatePointPath).sendKeys(intermediatePoints);

            By intermediatePointSuggestion = By.xpath("//li//span[normalize-space()='" + intermediatePoints.trim() + "']");

            wait.until(ExpectedConditions.elementToBeClickable(intermediatePointSuggestion)).click();
        }
    }


    public void clickOnTheNextButton(String toasterMessage) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        if (driver.findElement(nextButton).isEnabled()) {
            driver.findElement(nextButton).click();

            Assert.assertEquals(driver.findElement(toastMessage).getText().trim(), toasterMessage);

            if (toasterMessage.equalsIgnoreCase("Origin and destination saved successfully."))
                // Validate navigation to the next screen
//            wait.until(ExpectedConditions.visibilityOfElementLocated(nextScreenHeader));

                Assert.assertTrue(driver.findElement(nextScreenHeader).isDisplayed(), "Navigation to the next screen failed");
        }

    }
}



