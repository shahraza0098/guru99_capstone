//package base;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//
//public class DriverFactory {
//
//    private static WebDriver driver;
//
//    public static WebDriver initDriver() {
//
//        if(driver == null) {
//
//            driver = new ChromeDriver();
//
//            driver.manage().window().maximize();
//        }
//
//        return driver;
//    }
//
//    public static WebDriver getDriver() {
//
//        return driver;
//    }
//
//    public static void quitDriver() {
//
//        if(driver != null) {
//
//            driver.quit();
//
//            driver = null;
//        }
//    }
//}




package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver initDriver(String browser) {

        if (driver == null) {

            switch (browser.toLowerCase()) {

                case "chrome":

                    ChromeOptions chromeOptions = new ChromeOptions();

                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");

                    System.out.println("Creating Chrome Driver");

                    driver = new ChromeDriver(chromeOptions);
//                  driver = new ChromeDriver(); // Use this for local execution

                    break;

                case "edge":

                    EdgeOptions edgeOptions = new EdgeOptions();

                    edgeOptions.addArguments("--headless=new");
                    edgeOptions.addArguments("--no-sandbox");
                    edgeOptions.addArguments("--disable-dev-shm-usage");
                    edgeOptions.addArguments("--disable-gpu");
                    edgeOptions.addArguments("--window-size=1920,1080");

                    System.out.println("Creating Edge Driver");

                    driver = new EdgeDriver(edgeOptions);
//                  driver = new EdgeDriver(); // Use this for local execution

                    break;

                case "firefox":

                    FirefoxOptions firefoxOptions = new FirefoxOptions();

                    firefoxOptions.addArguments("--headless");

                    System.out.println("Creating Firefox Driver");

                    driver = new FirefoxDriver(firefoxOptions);
//                  driver = new FirefoxDriver();

                    break;

                default:

                    throw new IllegalArgumentException(
                            "Invalid browser: " + browser);
            }

            driver.manage().window().maximize();
        }

        return driver;
    }

    public static WebDriver getDriver() {

        return driver;
    }

    public static void quitDriver() {

        if (driver != null) {

            driver.quit();

            driver = null;
        }
    }
}