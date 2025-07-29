package pages;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BookingPageFactory {

    WebDriver driver;

    @FindBy(id = "name")
    WebElement nameField;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "phone")
    WebElement phoneField;

    @FindBy(id = "travelFrom")
    WebElement originField;

    @FindBy(id = "travelTo")
    WebElement destinationField;

    @FindBy(id = "departure")
    WebElement departureDateField;

    @FindBy(id = "selectclass")
    WebElement travelClassDropdown;

    @FindBy(id = "ticket-class-increase")
    WebElement addPassengerButton;

    @FindBy(id = "ticket-class-decrease")
    WebElement subtractPassengerButton;

    @FindBy(id = "ticket-class-count")
    WebElement passengerCountField;

    @FindBy(id = "book-now")
    WebElement bookFlightButton;

    @FindBy(id = "reset-now")
    WebElement resetButton;

    @FindBy(tagName = "bookingconfirm")
    WebElement confirmationMessage;

    public BookingPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterName(String name) {
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPhone(String phone) {
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    public void selectOrigin(String origin) {
        originField.clear();
        originField.sendKeys(origin);
    }

    public void selectDestination(String destination) {
        destinationField.clear();
        destinationField.sendKeys(destination);
    }

    public void enterDepartureDate(String date) {
        departureDateField.clear();
        departureDateField.sendKeys(date);
    }

    public void selectTravelClass(String travelClass) {
        new Select(travelClassDropdown).selectByVisibleText(travelClass);
    }

    public void setPassengerCount(int desiredCount) {
        int currentCount = Integer.parseInt(passengerCountField.getAttribute("value"));

        while (currentCount < desiredCount) {
            addPassengerButton.click();
            currentCount++;
        }

        while (currentCount > desiredCount) {
            subtractPassengerButton.click();
            currentCount--;
        }
    }

    public void clickBookFlight() {
        bookFlightButton.click();
    }

    public void clickReset() {
        resetButton.click();
    }

    public void assertBookingSuccess() {
        String actual = confirmationMessage.getText().trim();
        assertTrue(actual.contains("Your flight Reservation has been Confirmed !"), "Expected booking confirmation message not found");
    }

    public void assertBookingShouldNotProceed() {
        String actual = confirmationMessage.getText().trim();
        String expected = "Travel From can't be blank"; // Adjust as per actual error message
        assertTrue(actual.contains(expected), "Booking failed to validate input fields properly");
    }
}
