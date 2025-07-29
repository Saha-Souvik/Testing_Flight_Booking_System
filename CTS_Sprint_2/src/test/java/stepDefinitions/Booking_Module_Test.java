package stepDefinitions;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import pages.BookingPageFactory;
import utils.ConfigFileReader;
import hook.ApplicationHooks;

public class Booking_Module_Test {

    WebDriver driver = ApplicationHooks.driver;
    BookingPageFactory bookingPage;
    ConfigFileReader config = new ConfigFileReader();

    @Given("^User is on the booking page$")
    public void user_is_on_booking_page() {
        bookingPage = new BookingPageFactory(driver);
//        driver.get("http://localhost:5500/index.html"); 
    }

    @When("^User enters name as \"(.*)\", email as \"(.*)\", and phone as \"(.*)\"$")
    public void user_enters_name_email_phone(String name, String email, String phone) {
        bookingPage.enterName(name);
        bookingPage.enterEmail(email);
        bookingPage.enterPhone(phone);
    }

    @And("^Selects origin as \"(.*)\" and destination as \"(.*)\"$")
    public void user_selects_origin_destination(String origin, String destination) {
        bookingPage.selectOrigin(origin);
        bookingPage.selectDestination(destination);
    }

    @And("^Enters departure date as \"(.*)\"$")
    public void enters_departure_date(String date) {
        bookingPage.enterDepartureDate(date);
    }

    @And("^Selects travel class as \"(.*)\"$")
    public void selects_travel_class(String travelClass) {
        bookingPage.selectTravelClass(travelClass);
    }

    @And("^Sets passenger count to (\\d+)$")
    public void sets_passenger_count(int count) {
        bookingPage.setPassengerCount(count);
    }

    @And("^Clicks on Book Flight button$")
    public void clicks_on_book_flight() {
        bookingPage.clickBookFlight();
    }

    @Then("^Booking should be confirmed$")
    public void booking_should_be_confirmed() {
        bookingPage.assertBookingSuccess();
    }

    @Then("^Booking should not proceed$")
    public void booking_should_not_proceed() {
        bookingPage.assertBookingShouldNotProceed();
    }

    @When("^User clicks on Reset button$")
    public void user_clicks_reset_button() {
        bookingPage.clickReset();
    }
}
