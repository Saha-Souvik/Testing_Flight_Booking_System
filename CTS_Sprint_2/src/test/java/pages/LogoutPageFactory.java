package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPageFactory {
    WebDriver driver;

    @FindBy(xpath = "//li[@id='loginLi']/a")
    private WebElement logoutButton;

    // Constructor
    public LogoutPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to get the text of the logout button to verify the bug
    public String getLogoutButtonText() {
        return logoutButton.getText().trim();
    }
    
    public void clickLogoutButton() {
        logoutButton.click();
    }
}