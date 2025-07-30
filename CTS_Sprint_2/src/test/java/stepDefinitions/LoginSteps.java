package stepDefinitions;

import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import hook.ApplicationHooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.LoginPageFactory;
import utils.ConfigFileReader;

public class LoginSteps {

    WebDriver driver = ApplicationHooks.driver;
    LoginPageFactory loginPage;
    ConfigFileReader config = new ConfigFileReader();

    @Given("Start browser and navigate to login page")
    public void start_browser() {
        loginPage = new LoginPageFactory(driver);
    }

    @And("user enters credentials to login")
    public void user_enters_valid_credentials(DataTable dataTable) {
    	List<List<String>> data = dataTable.asLists(String.class);
        loginPage.setUsername(data.get(0).get(0));
        loginPage.setPassword(data.get(0).get(1));
    }

    @And("user enters valid username and invalid password")
    public void user_enters_valid_username_invalid_password(DataTable dataTable) {
    	List<List<String>> data = dataTable.asLists(String.class);
        loginPage.setUsername(data.get(0).get(0));
        loginPage.setPassword(data.get(0).get(1));
    }

    @And("user enters invalid username and valid password")
    public void user_enters_invalid_username_valid_password(DataTable dataTable) {
    	List<List<String>> data = dataTable.asLists(String.class);
        loginPage.setUsername(data.get(0).get(0));
        loginPage.setPassword(data.get(0).get(1));
    }

    @And("user leaves username and password fields empty")
    public void user_leaves_fields_empty() {
        loginPage.setUsername("");
        loginPage.setPassword("");
    }

    @And("user get the captcha generated and enters valid captcha code")
    public void enter_captcha() throws InterruptedException {
        loginPage.setCaptcha();
        loginPage.clickValidate();
   
    }

    @And("click on login button")
    public void click_login() throws InterruptedException {
        loginPage.clickLogin();
       
    }

    @Then("it should display alert message and click ok")
    public void verify_success_alert() {
    	loginPage.verifyAlert();

    }

    @Then("it should display password error message")
    public void verify_password_error() {
        loginPage.getPasswordError();
    }

    @Then("it should display username error message")
    public void verify_username_error() {
    	loginPage.getUsernameError();
    }

    @Then("it should display username and password error messages")
    public void verify_empty_field_error() {
    	loginPage.getEmptyError();
    }

    @And("check the Remember Me checkbox and accept alerts")
    public void check_remember_me() {
        loginPage.checkRememberMe();
    }

    @And("credentials should be remembered on next visit")
    public void verify_remembered_credentials() {
        loginPage.verifyRememberedCredentials("flightadmin", "flightadmin");
    }

    @And("user clicks on the Forgot your password link")
    public void click_forgot_password() {
        loginPage.clickForgotPassword();
    }

    @Then("it should redirect to the reset password page")
    public void verify_forgot_password_redirect() {
        loginPage.verifyForgotPasswordRedirect();
    }
}


