package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    By managerText =
            By.xpath("//td[contains(text(),'Manger Id')]");

    public HomePage(WebDriver driver) {

        this.driver = driver;
    }

    public boolean isManagerDisplayed() {

        return driver.findElement(managerText)
                     .isDisplayed();
    }
}