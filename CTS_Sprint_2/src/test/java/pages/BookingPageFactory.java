package pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;
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

    @FindBy(id = "bookingconfirm")
    WebElement confirmationMessage;
    
    @FindBy(id = "errfn")
    WebElement errorMessageContainer;


    public BookingPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setBookingDetails(String from, String to, String date, String cls, String name, String email, String phone, String passengerCount) throws InterruptedException {
        
        originField.sendKeys(from);
        destinationField.sendKeys(to);
        departureDateField.sendKeys(date);
        travelClassDropdown.sendKeys(cls);
        nameField.sendKeys(name);
        emailField.sendKeys(email);
        scrollIntoView(phoneField);
        Thread.sleep(1000);
        phoneField.sendKeys(phone);
        setPassengerCount(Integer.parseInt(passengerCount));
    }

    private void setPassengerCount(int targetCount) {
        int currentCount = Integer.parseInt(passengerCountField.getAttribute("value"));
        while (currentCount < targetCount) {
            addPassengerButton.click();
            currentCount++;
        }
        while (currentCount > targetCount) {
            subtractPassengerButton.click();
            currentCount--;
        }
    }

    public void clickBookNow() throws InterruptedException {
        bookFlightButton.click();
        Thread.sleep(2000);
    }

    public void clickReset() {
    	//scrollIntoView(resetButton);
        resetButton.click();
    }

    public String getSuccessMessage() {
    	//scrollIntoView(confirmationMessage);
        return confirmationMessage.getText();
    }

    public boolean areFieldsCleared() {
        return nameField.getAttribute("value").isEmpty() &&
               emailField.getAttribute("value").isEmpty() &&
               phoneField.getAttribute("value").isEmpty() &&
               originField.getAttribute("value").isEmpty() &&
               destinationField.getAttribute("value").isEmpty() &&
               departureDateField.getAttribute("value").isEmpty() &&
               passengerCountField.getAttribute("value").equals("0");
    }
    
    public boolean isErrorDisplayed() {
        return errorMessageContainer.isDisplayed() && !errorMessageContainer.getText().trim().isEmpty();
    }

    
    public void scrollIntoView(WebElement element) {
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}

