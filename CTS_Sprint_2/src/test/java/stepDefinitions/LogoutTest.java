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

    @Given("user has logged in to the application with {string} and {string}")
    public void user_has_logged_in_to_the_application_with_and(String username, String password) throws InterruptedException {
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.setCaptcha();
        loginPage.clickValidate();
        loginPage.clickLogin();
        loginPage.verifyAlert();
    }

    @When("user is on the home page")
    public void user_is_on_the_home_page() {
        homePage = new HomePageFactory(driver);
    }

    @Then("the logout button should be mislabeled as {string}")
    public void the_logout_button_should_be_mislabeled_as(String expectedButtonText) {
        String actualButtonText = homePage.getLogoutButtonText();
        assertEquals(actualButtonText, expectedButtonText, "Button text does not match the expected bug text.");
    }
}