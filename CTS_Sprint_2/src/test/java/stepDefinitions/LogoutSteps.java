package stepDefinitions;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import hook.ApplicationHooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LogoutPageFactory;
import pages.LoginPageFactory;

public class LogoutSteps {

    WebDriver driver = ApplicationHooks.driver;
    LoginPageFactory loginPage = new LoginPageFactory(driver);
    LogoutPageFactory homePage;

    @Given("user has logged in to the application using {string} and {string}")
    public void user_has_logged_in_to_the_application_using_and(String username, String password) throws InterruptedException {
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.setCaptcha();
        loginPage.clickValidate();
        loginPage.clickLogin();
        loginPage.verifyAlert();
        Thread.sleep(3000);
    }

    @When("user is on the home page")
    public void user_is_on_the_home_page() {
        homePage = new LogoutPageFactory(driver);
    }

    @Then("the user should see a {string} button")
    public void the_user_should_see_a_button(String expectedButtonText) {
        String actualButtonText = homePage.getLogoutButtonText();
        
        assertEquals(actualButtonText, expectedButtonText, "TEST FAILED: Logout button is mislabeled. Bug AG-28 is confirmed.");
    }
    
    @Then("user clicks on the button")
    public void user_clicks_on_the_button() {
        homePage.clickLogoutButton();
    }

    @Then("it should redirect to login page")
    public void it_should_redirect_to_login_page() {
        String expectedTitle = "Login Page";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, expectedTitle, "TEST FAILED: Logout did not redirect to login page. Bug AG-29 is confirmed.");
    }
}