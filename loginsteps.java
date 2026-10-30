package StepDefinitions;

import Driver.WebdriverInitializer;
import Page.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginsteps {
    LoginPage loginPage = new LoginPage(WebdriverInitializer.getDriver());
    @Given("Open app URL")
    public void open_app_url() {
        loginPage.openUrl();
    }

    @When("Enter {string} and {string}")
    public void enter_and(String Username, String Password) {
        loginPage.enterCredentials(Username, Password);
    }

    @When("Click on the Sign in button")
    public void click_on_the_sign_in_button() {
        loginPage.clickSignIn();
    }

    @Then("Login results check {string}")
    public void login_results_check(String Results) {
        loginPage.checkOutput(Results);

    }


}


