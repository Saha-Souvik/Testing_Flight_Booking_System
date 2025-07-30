package stepDefinitions;

import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import pages.BookingPageFactory;
import pages.LoginPageFactory;
import utils.ConfigFileReader;
import hook.ApplicationHooks;

public class BookingSteps {

    WebDriver driver = ApplicationHooks.driver;
    LoginPageFactory loginPage = new LoginPageFactory(driver);
    BookingPageFactory bookingPage;
    ConfigFileReader config = new ConfigFileReader();
    
    @Given("user has logged in to the application with {string} and {string}")
    public void user_has_logged_in_to_the_application_with_and(String username, String password) throws InterruptedException {
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.setCaptcha();
        loginPage.clickValidate();
        loginPage.clickLogin();
        loginPage.verifyAlert();
        Thread.sleep(1000);
    }
    
    @And("user is on the Booking Module page")
    public void user_is_on_booking_module_page() {
        bookingPage= new BookingPageFactory(driver);
    }

    @When("the user enters all required fields with valid data")
    public void enter_valid_data(DataTable dataTable) throws InterruptedException {
        Map<String, String> data = dataTable.asMaps().get(0);
        bookingPage.setBookingDetails(
            data.get("Travel From"),
            data.get("Travel To"),
            data.get("Departure Date"),
            data.get("Class"),
            data.get("Name"),
            data.get("Email"),
            data.get("Phone Number"),
            data.get("Passengers")
        );
    }

    @When("the user leaves all required fields blank")
    public void leave_all_fields_blank() throws InterruptedException {
        bookingPage.setBookingDetails("", "", "", "", "", "", "", "0");
    }

    @When("the user enters invalid data in required fields")
    public void enter_invalid_data(DataTable dataTable) throws InterruptedException {
        Map<String, String> data = dataTable.asMaps().get(0);
        bookingPage.setBookingDetails(
            data.get("Travel From"),
            data.get("Travel To"),
            data.get("Departure Date"),
            data.get("Class"),
            data.get("Name"),
            data.get("Email"),
            data.get("Phone Number"),
            data.get("Passengers")
        );
    }

    @And("clicks the Book Now button")
    @When("the user clicks the Book Now button")
    public void click_book_now() throws InterruptedException {
        bookingPage.clickBookNow();
    }

    @Then("the booking should be successful")
    @Then("the booking should be confirmed")
    public void booking_should_be_successful() {
        String message = bookingPage.getSuccessMessage();
        assertTrue(message.toLowerCase().contains("confirmed"), "Booking success message not displayed");
    }

    @Then("an error message should be displayed for required fields")
    @Then("validation error messages should be displayed")
    public void error_message_should_be_displayed() {
        assertTrue(bookingPage.isErrorDisplayed(), "Expected error messages not displayed");
    }

    @And("user has filled in all valid booking details")
    public void user_filled_valid_data(DataTable dataTable) throws InterruptedException {
        enter_valid_data(dataTable);
    }

    @And("the user has entered data in the booking form")
    public void user_entered_booking_data(DataTable dataTable) throws InterruptedException {
        enter_valid_data(dataTable);
    }

    @When("the user clicks the Reset button")
    public void click_reset_button() {
        bookingPage.clickReset();
    }

    @Then("all fields in the form should be cleared")
    public void verify_fields_cleared() {
        assertTrue(bookingPage.areFieldsCleared(), "Fields are not cleared after reset");
    }
    
}
