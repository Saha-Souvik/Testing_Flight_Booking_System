package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.*;
import pages.EnquiryPageFactory;
import pages.LoginPageFactory;
import hook.ApplicationHooks;

public class EnquirySteps {

    WebDriver driver = ApplicationHooks.driver;
    LoginPageFactory loginPage = new LoginPageFactory(driver);
    EnquiryPageFactory enquiry;

    @Given("The enquiry form page is loaded with {string} and {string}")
    public void the_enquiry_form_page_is_loaded_with_and(String username, String password) throws InterruptedException {
    	loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.setCaptcha();
        loginPage.clickValidate();
        loginPage.clickLogin();
        loginPage.verifyAlert();
        driver.findElement(By.id("enquiryLink")).click();
        enquiry= new EnquiryPageFactory(driver);
    }

    @When("The user enters full name {string}")
    public void the_user_enters_full_name(String fullName) {
        enquiry.setUsername(fullName);
    }

    @Then("The Full Name field should accept {string}")
    public void the_full_name_field_should_accept(String expectedName) {
        enquiry.checkUsername(expectedName);
    }

    @When("The user enters email {string}")
    public void the_user_enters_email(String email) {
        enquiry.setEmail(email);
    }

    @When("The user submits the enquiry form")
    public void the_user_submits_the_enquiry_form() throws InterruptedException {
        enquiry.clickSubmit();
        Thread.sleep(2000);   
    }

    @Then("An error should appear for invalid email format")
    public void an_error_should_appear_for_invalid_email_format() {
        boolean error = enquiry.isEmailErrorDisplayed();
        if (!error) throw new AssertionError("Expected email format error.");
    }

    @When("The user leaves required fields empty")
    public void the_user_leaves_required_fields_empty() {
        enquiry.setUsername("");
        enquiry.setEmail("");
        enquiry.setMessage("");
    }

    @Then("Error messages should be shown for missing fields")
    public void error_messages_should_be_shown_for_missing_fields() {
        boolean error = enquiry.isRequiredFieldErrorDisplayed();
        if (!error) throw new AssertionError("Expected required field error.");
    }

    @When("The user enters message with length {string}")
    public void the_user_enters_message_with_length(String lengthStr) {
        int length = Integer.parseInt(lengthStr);
        StringBuilder longMsg = new StringBuilder();
        for (int i = 0; i < length; i++) {
            longMsg.append("a");
        }
        enquiry.setMessage(longMsg.toString());
    }

    @Then("The system should display character limit warning")
    public void the_system_should_display_character_limit_warning() {
        boolean tooLong = enquiry.isMessageTooLong();
        if (tooLong) throw new AssertionError("Expected message length error.");
        
    }

    @When("The user fills out Full Name, Email, and Message")
    public void the_user_fills_out_full_name_email_and_message() {
        enquiry.setUsername("Test User");
        enquiry.setEmail("test@example.com");
        enquiry.setMessage("Hello, I am testing the form.");
    }

    @When("Leaves Subject field as {string}")
    public void leaves_subject_field_as(String subject) {
        enquiry.setSubject(subject);
        enquiry.clickSubmit();
    }

    @Then("Error should be displayed for empty subject")
    public void error_should_be_displayed_for_empty_subject() {
        boolean error = enquiry.isSubjectFieldErrorDisplayed();
        if (error) throw new AssertionError("Expected message length error.");
    }

}
