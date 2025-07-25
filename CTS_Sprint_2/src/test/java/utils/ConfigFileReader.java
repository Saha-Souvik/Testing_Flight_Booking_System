package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath = "src/test/resources/config/config.properties";

    public ConfigFileReader() {
        try (FileInputStream fis = new FileInputStream(propertyFilePath)) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not read config file at " + propertyFilePath, e);
        }
    }

    public String getBrowser() {
        String browser = properties.getProperty("browser");
        if (browser != null) {
            return browser;
        } else {
            throw new RuntimeException("browser not specified in the config.properties file.");
        }
    }

    public String getBaseUrl() {
        String url = properties.getProperty("url");
        if (url != null) {
            return url;
        } else {
            throw new RuntimeException("url not specified in the config.properties file.");
        }
    }
}
