package pages;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPageFactory {

    WebDriver driver;

    public SearchPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "myInputnumber")
    WebElement flightNumberInput;

    @FindBy(id = "mySearchnumber")
    WebElement flightNumberSearchButton;

    @FindBy(id = "myInputname")
    WebElement flightNameInput;

    @FindBy(id = "mySearchname")
    WebElement flightNameSearchButton;

    @FindBy(id = "myInputtype")
    WebElement flightTypeInput;

    @FindBy(id = "mySearchtype")
    WebElement flightTypeSearchButton;

    public void setFlightNumber(String number) {
        flightNumberInput.clear();
        flightNumberInput.sendKeys(number);
    }

    public void clickFlightNumberSearch() {
        flightNumberSearchButton.click();
    }

    public void setFlightName(String name) {
        flightNameInput.clear();
        flightNameInput.sendKeys(name);
    }

    public void clickFlightNameSearch() {
        flightNameSearchButton.click();
    }

    public void setFlightType(String type) {
        flightTypeInput.clear();
        flightTypeInput.sendKeys(type);
    }

    public void clickFlightTypeSearch() {
        flightTypeSearchButton.click();
    }

    public void verifySearchResultsPresent() {
        List<WebElement> results = driver.findElements(By.xpath("//table/tbody/tr"));
        assertTrue(results.size() > 0, "Expected search results to be displayed");
    }

    public void verifyNoSearchResults() {
        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
        boolean empty = rows.stream().allMatch(row -> !row.isDisplayed() || row.getText().trim().isEmpty());
        assertTrue(empty, "Expected no search results to be displayed");
    }
    
    public void noTextDisplayed() {
    	List<WebElement> messages = driver.findElements(By.xpath("//table/tbody/tr"));

        if (messages.isEmpty() || !messages.get(0).isDisplayed()) {
            fail("'Not found' message is not displayed. Nothing appears for invalid input.");
        }
    }
}
