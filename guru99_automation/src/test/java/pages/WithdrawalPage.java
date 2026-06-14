package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import utilities.WaitUtil;

public class WithdrawalPage {

    WebDriver driver;

    public WithdrawalPage(WebDriver driver) {
        this.driver = driver;
    }

    By withdrawalLink =
            By.linkText("Withdrawal");

    By accountNo =
            By.name("accountno");

    By amount =
            By.name("ammount");

    By description =
            By.name("desc");

    By submitBtn =
            By.name("AccSubmit");

    By successMessage =
            By.xpath("//p[contains(text(),'Transaction details')]");

  
    
    
    public void clickWithdrawal() {
	    WaitUtil waitUtil = new WaitUtil(driver);

	    WebElement button =
	            waitUtil.waitForElementClickable(withdrawalLink);
        
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView(true);",
                        button);

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        button);
    }
	

    public void withdrawAmount(
            String accNo,
            String withdrawAmount,
            String remarks) {

        driver.findElement(accountNo)
                .sendKeys(accNo);

        driver.findElement(amount)
                .sendKeys(withdrawAmount);

        driver.findElement(description)
                .sendKeys(remarks);

        driver.findElement(submitBtn)
                .click();
    }

    public boolean isWithdrawalSuccessful() {

        WaitUtil waitUtil = new WaitUtil(driver);
	    return waitUtil.waitForElementVisible(successMessage).isDisplayed();
    }
    
    public void acceptAlert(String msg, SoftAssert softAssert) {

        Alert alert = WaitUtil.waitForAlert(driver);

        String alertText = alert.getText();

        System.out.println("Alert Message: " + alertText);

        softAssert.assertTrue(
                alertText.contains(msg),
                "Unexpected alert message. Expected: [" + msg + "] but got: [" + alertText + "]");

        alert.accept();
    }
	
}