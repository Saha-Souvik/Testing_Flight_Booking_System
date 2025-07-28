package stepDefinitions;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import hook.ApplicationHooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePageFactory;
import pages.LoginPageFactory;

public class LogoutTest {

    WebDriver driver = ApplicationHooks.driver;
    LoginPageFactory loginPage = new LoginPageFactory(driver);
    HomePageFactory homePage;

    @Given("user has logged in to the application")
    public void user_has_logged_in_to_the_application() throws InterruptedException {
        // This step performs a valid login as a precondition for the logout test
        loginPage.setUsername("flightadmin");
        loginPage.setPassword("flightadmin");
        loginPage.setCaptcha();
        loginPage.clickValidate();
        loginPage.clickLogin();
        loginPage.verifyAlert();
    }

    @When("user is on the home page")
    public void user_is_on_the_home_page() {
        // Initialize the HomePageFactory once we are on the home page
        homePage = new HomePageFactory(driver);
    }

    @Then("the logout button should be mislabeled as {string}")
    public void the_logout_button_should_be_mislabeled_as(String expectedButtonText) {
        String actualButtonText = homePage.getLogoutButtonText();
        // This assertion checks for the bug.
        assertEquals(actualButtonText, expectedButtonText, "Button text does not match the expected bug text.");
    }
}