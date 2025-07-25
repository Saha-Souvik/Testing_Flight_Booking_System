package hook;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import setup.DriverSetup;
import utils.ConfigFileReader;

public class ApplicationHooks {

    public static WebDriver driver;
    ConfigFileReader configReader;

    @Before
    public void setUp() throws InterruptedException {
        configReader = new ConfigFileReader();
        driver = DriverSetup.getDriver();
        driver.get(configReader.getBaseUrl());
    }
    
    @AfterStep
    public void takeScreenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Screenshot- "+scenario.getName());
        }
    }

    @AfterAll
    public static void after_all_tear_down(){
    	DriverSetup.quitDriver();
    }
}

