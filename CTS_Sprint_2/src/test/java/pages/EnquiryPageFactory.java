package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnquiryPageFactory {

    WebDriver driver;

    public EnquiryPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;
    
    @FindBy(id = "name")
    WebElement username;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "phone")
    WebElement phoneField;

    @FindBy(id = "subject")
    WebElement subjectField;

    @FindBy(id = "message")
    WebElement messageBox;

    @FindBy(id = "submit")
    WebElement button;
    @FindBy(id = "captcha")
    WebElement captchaField;
    
    @FindBy(id = "captchaBtn")
    WebElement captchaButton;
    
    @FindBy(id = "login-submit")
    WebElement loginButton;
    // Action methods
    public void setUsername(String name) {
        username.clear();
        username.sendKeys(name);
    }

    public void checkUsername(String expectedName) {
        String actual = username.getAttribute("value");
        if (!actual.equals(expectedName)) {
            throw new AssertionError("Username field does not match expected value.");
        }
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setPhone(String phone) {
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    public void setSubject(String subject) {
        subjectField.clear();
        subjectField.sendKeys(subject);
    }

    public void setMessage(String msg) {
        messageBox.clear();
        messageBox.sendKeys(msg);
    }

    public void clickSubmit() {
        button.click();
    }

    public boolean isEmailErrorDisplayed() {
        return emailField.getAttribute("validationMessage") != null;
    }

    public boolean isSubjectFieldErrorDisplayed() {
        String temp  = driver.findElement(By.id("subject")).getText();
        if(temp.equalsIgnoreCase(""))
        {
        	boolean ans = true;
        	 System.out.print(ans);
        	return ans;
        }
        else
        {
        	boolean ans = false;
               	 System.out.print(ans);
               	return ans;
        }
    }

    public boolean isRequiredFieldErrorDisplayed() {
        return username.getAttribute("required") != null ||
               emailField.getAttribute("required") != null ||
               messageBox.getAttribute("required") != null;
    }

    public boolean isMessageTooLong() {
         boolean ans= messageBox.getAttribute("value").length() >= 500;     
         return ans;
    }
    public void setUserloginame(String uname)
    {
    	driver.findElement(By.id("username")).sendKeys(uname);
    }
    public void setPassowrd(String pass)
    {
    	driver.findElement(By.id("password")).sendKeys(pass);
    }
    public void verifyAlert() {
    	driver.switchTo().alert().accept();
    	
    }
    public void setCaptcha() {
        String value = driver.findElement(By.id("code")).getText();
        captchaField.sendKeys(value);
    }
    public void clickValidate() throws InterruptedException {
        captchaButton.click();
        driver.switchTo().alert().accept();
    }
    public void clickLogin() throws InterruptedException {
        loginButton.click();
        Thread.sleep(1000);
    }
}

