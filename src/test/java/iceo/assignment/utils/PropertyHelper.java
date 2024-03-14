package iceo.assignment.utils;


import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyHelper {

    private static final Properties properties = new Properties();

    static {
        String environment = getSerenityProperty("environment");
        if (environment == null || environment.isEmpty()) {
            throw new RuntimeException("Environment variable 'environment' is not set.");
        }

        String propFileName = "src/test/resources/environments/" + environment + ".properties";
        try (FileInputStream fis = new FileInputStream(propFileName)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not read the properties file: " + propFileName, e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getSerenityProperty(String key) {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
        return EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(key);
    }
}
