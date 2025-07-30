package stepDefinitions;

import java.util.List;
import org.openqa.selenium.By;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hook.ApplicationHooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import pages.LoginPageFactory;
import pages.SearchPageFactory;
import utils.ConfigFileReader;

public class SearchSteps {

    WebDriver driver = ApplicationHooks.driver;
    LoginPageFactory loginPage;
    SearchPageFactory searchPage;
    ConfigFileReader config = new ConfigFileReader();

    @Given("Start browser and navigate to flight search page after login")
    public void start_browser_and_navigate_to_flight_search_page_after_login() throws InterruptedException {
        driver.get(config.getBaseUrl());
        loginPage = new LoginPageFactory(driver);
        loginPage.setUsername("flightadmin");
        loginPage.setPassword("flightadmin");
        loginPage.setCaptcha();
        loginPage.clickValidate();
        loginPage.clickLogin();
        loginPage.verifyAlert();

        // Wait and click on "Flight Search" tab
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.id("flightSearchLink"))).click();
        
        // Initialize search page
        searchPage = new SearchPageFactory(driver);
    }

    @When("Enter valid flight number")
    public void enter_valid_flight_number() {
        searchPage.setFlightNumber("AC789");
        searchPage.clickFlightNumberSearch();
    }

    @When("Enter invalid flight number")
    public void enter_invalid_flight_number() {
        searchPage.setFlightNumber("INVALID123");
        searchPage.clickFlightNumberSearch();
    }

    @When("Enter empty flight number")
    public void enter_empty_flight_number() {
        searchPage.setFlightNumber("");
        searchPage.clickFlightNumberSearch();
    }

    @When("Enter valid flight name")
    public void enter_valid_flight_name() {
        searchPage.setFlightName("SkyRider Express");
        searchPage.clickFlightNameSearch();
    }

    @When("Enter valid flight type")
    public void enter_valid_flight_type() {
        searchPage.setFlightType("Layover flight");
        searchPage.clickFlightTypeSearch();
    }

    @Then("Verify search results displayed")
    public void verify_search_results_displayed() {
        searchPage.verifySearchResultsPresent();
    }

    @Then("Verify no results displayed")
    public void verify_no_results_displayed() {
        searchPage.verifyNoSearchResults();
    }
    
    @And("Not found text displayed")
    public void not_found_text_displayed(){
    	searchPage.noTextDisplayed();
    }
}

