package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By pageTitle=By.xpath("//h2[text()='AI Powered Railway Alignment Design']");
    private By usernameFiled=By.xpath("//input[@name='username']");
    private By passwordField=By.xpath("//input[@name='password']");
    private By loginButton=By.xpath("//input[@value='Sign In']");
    private By errorMessage =   By.xpath("//small[@class='text-danger mt-3 d-block']");


    public LoginPage(WebDriver driver) {
        this.driver=driver;
//        this.driver.manage().window().maximize();
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openUrl() {
 if (driver.findElement(pageTitle).isDisplayed()) {
     System.out.println("Page Title Displayed");
 }
 else {
     System.out.println("Page Title Not Displayed");
 }
    }

    public void enterCredentials(String Username,String Password) {
driver.findElement(usernameFiled).sendKeys(Username);
driver.findElement(passwordField).sendKeys(Password);
    }

    public void clickSignIn() {
driver.findElement(loginButton).click();
    }

    public void checkOutput(String Results) {
if (Results.contains("success")) {
    Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(pageTitle)), "Login Failed");
}
else {
    Assert.assertTrue(driver.findElement(errorMessage).isDisplayed(), "Error message not displayed");
}
    }
}
