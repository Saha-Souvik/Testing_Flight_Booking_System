package pages;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageFactory {

    WebDriver driver;

    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "captcha")
    WebElement captchaField;
    
    @FindBy(id = "captchaBtn")
    WebElement captchaButton;
    
    @FindBy(id = "remember_me")
    WebElement rememberMeCheckbox;
    
    @FindBy(id = "usernameErr")
    WebElement usernameError;
    
    @FindBy(id = "passwordErr")
    WebElement passwordError;

    @FindBy(id = "login-submit")
    WebElement loginButton;

    @FindBy(id = "reset-password-link")
    WebElement forgotPasswordLink;

    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void setCaptcha() {
        String value = driver.findElement(By.id("code")).getText();
        captchaField.sendKeys(value);
    }
    
    public void clickValidate() throws InterruptedException {
        captchaButton.click();
        driver.switchTo().alert().accept();
    }
    
    public void getUsernameError() {
    	String actualError = usernameError.getText().trim();
    	String expectedError = "username is wrong";
    	assertTrue(actualError.equalsIgnoreCase(expectedError), "Username error message mismatch");
    }

	public void getPasswordError() {
		String actualError = passwordError.getText().trim();
    	String expectedError = "password is wrong";
    	assertTrue(actualError.equalsIgnoreCase(expectedError), "Password error message mismatch");
	}
	
	public void getEmptyError() {
		String unameMsg = usernameError.getText().trim();
    	String expectedUnameMsg = "username cannot be empty";
    	String passMsg = passwordError.getText().trim();
    	String expectedPassMsg = "password cannot be empty";
    	assertTrue(unameMsg.equalsIgnoreCase(expectedUnameMsg), "Username empty message mismatch");
    	assertTrue(passMsg.equalsIgnoreCase(expectedPassMsg), "Password empty message mismatch");
		
	}

    public void clickLogin() throws InterruptedException {
        loginButton.click();
        Thread.sleep(1000);
    }

    public void clickForgotPassword() {
        forgotPasswordLink.click();
    }

    public void checkRememberMe() {
        if (!rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
            try {
            	driver.switchTo().alert().accept();
            	Thread.sleep(1000);
            	driver.switchTo().alert().accept();
            	Thread.sleep(2000);
            }catch(Exception e) {
            	System.out.println(e);
            }
        }
    }
    
    public void verifyRememberedCredentials(String expectedUser, String expectedPass) {
    	driver.navigate().refresh();
    	String actualUser = usernameField.getAttribute("value");
    	String actualPass = passwordField.getAttribute("value");
    	assert actualUser.equals(expectedUser) : "Username does not match. Expected: " + expectedUser + ", Actual: " + actualUser;
    	assert actualPass.equals(expectedPass) : "Password does not match. Expected: " + expectedPass + ", Actual: " + actualPass;
    }
    
    public void verifyForgotPasswordRedirect() {
    	String pageSource = driver.getPageSource();
        assert !pageSource.contains("HTTP Status 404") : "Page not found (404 error detected)";
    }
    
    public void verifyAlert() {
    	driver.switchTo().alert().accept();
    	
    }

}