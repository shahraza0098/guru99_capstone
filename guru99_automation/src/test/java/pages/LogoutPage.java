package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.WaitUtil;

public class LogoutPage {

    WebDriver driver;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    By logoutLink = By.linkText("Log out");

 
    
    
    
    public void clickLogout() {

        WebElement button =
                driver.findElement(logoutLink);

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView(true);",
                        button);

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        button);
    }

    public String getLogoutAlertText() {

        Alert alert = WaitUtil.waitForAlert(driver);

        String alertText = alert.getText();

        alert.accept();

        return alertText;
    }

    public boolean isLoginPageDisplayed() {

        return driver.findElement(By.name("uid"))
                .isDisplayed();
    }
}