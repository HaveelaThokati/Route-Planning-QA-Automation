package StepDefinitions;

import Driver.WebdriverInitializer;
import Page.OriginAndDestinationPage;
import io.cucumber.java.en.Then;


public class OriginAndDestinationSteps {

    OriginAndDestinationPage originDestinationPage = new OriginAndDestinationPage(WebdriverInitializer.getDriver());


    @Then("Click on Generate New Layout button")
    public void click_on_generate_new_layout_button() throws InterruptedException {
        originDestinationPage.clickOnGenerateNewLayoutButton();
    }

    @Then("Configure the Route {string}")
    public void configure_the_route(String routeName) {
        originDestinationPage.configureTheRouteName(routeName);
    }
    @Then("Click on the Create button")
    public void click_on_the_create_button() {
        originDestinationPage.clickOnCreateButton();
    }
    @Then("Verify the Enter Origin and destination screen")
    public void verify_the_enter_origin_and_destination_screen() {
        originDestinationPage.verifyTheEnterOriginDestinationPageComponents();
    }
    @Then("Enter the Coordinates {string} {string} {string}")
    public void enter_the_coordinates(String origin, String destination, String intermediatePoints) throws InterruptedException {
        originDestinationPage.enterCoordinates(origin,destination,intermediatePoints);
    }
    @Then("Click on the Next Button and validate the toaster {string}")
    public void click_on_the_next_button_and_validate_the_toaster(String toaster) {
     originDestinationPage.clickOnTheNextButton(toaster);
    }
}
