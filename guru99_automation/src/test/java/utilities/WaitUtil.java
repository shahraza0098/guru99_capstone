package utilities;


import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

    WebDriver driver;
    WebDriverWait wait;

    public WaitUtil(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement waitForElementVisible(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementClickable(By locator) {
        return wait.until(
                ExpectedConditions.elementToBeClickable(locator));
    }
    
    public static Alert waitForAlert(WebDriver driver) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(
                ExpectedConditions.alertIsPresent());
    }
    
    
    //fluent visibility wait
    
    public WebElement waitForElementVisibleFluent(By locator) {

        FluentWait<WebDriver> wait =
                new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(20))
                        .pollingEvery(Duration.ofSeconds(2))
                        .ignoring(NoSuchElementException.class);

        return wait.until(driver ->
                driver.findElement(locator).isDisplayed()
                        ? driver.findElement(locator)
                        : null);
    }
}