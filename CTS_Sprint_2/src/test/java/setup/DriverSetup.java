package setup;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.ConfigFileReader;

public class DriverSetup { 

    public static WebDriver driver;
    public static ConfigFileReader filereader = new ConfigFileReader();

    public static WebDriver getDriver() {

        if (driver == null) {
        	
            String browser = filereader.getBrowser().toLowerCase();

            switch (browser) {
            
                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                case "chrome":
                	driver = new ChromeDriver();
                    break;

                case "edge":
                    driver = new EdgeDriver();
                    break;

                default:
                    throw new RuntimeException("Unsupported browser: " + browser);
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }

        return driver;
    }
    
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
