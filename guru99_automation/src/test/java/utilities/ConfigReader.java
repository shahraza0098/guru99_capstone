package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    static {

        try {

            FileInputStream fis = new FileInputStream(
                    "src/test/resources/config/config.properties");

            prop = new Properties();

            prop.load(fis);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {

        return prop.getProperty(key);
    }
}