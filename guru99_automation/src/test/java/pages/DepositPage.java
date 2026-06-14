package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utilities.WaitUtil;

public class DepositPage {

    WebDriver driver;

    public DepositPage(WebDriver driver) {
        this.driver = driver;
    }

    By depositLink = By.linkText("Deposit");

    By accountNo = By.name("accountno");

    By amount = By.name("ammount");

    By description = By.name("desc");

    By submitBtn = By.name("AccSubmit");

    By successMessage =
            By.xpath("//p[contains(text(),'Transaction details')]");


    
    
//    public void clickDeposit() {
//
//	    ((JavascriptExecutor) driver)
//	            .executeScript("window.scrollTo(0,0)");
//
//	    WaitUtil waitUtil = new WaitUtil(driver);
//
//	    WebElement button =
//	            waitUtil.waitForElementClickable(depositLink);
//
//	    button.click();
//	}
    
    public void clickDeposit() {

	    WaitUtil waitUtil = new WaitUtil(driver);

	    WebElement button =
	            waitUtil.waitForElementClickable(depositLink);

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView(true);",
                        button);

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        button);
    }
    
    public void clickDepositSubmitBtn() {
    	  driver.findElement(submitBtn)
          .click();
    }

    public void depositAmount(
            String accNo,
            String depositAmount,
            String remarks) {

        driver.findElement(accountNo)
                .sendKeys(accNo);

        driver.findElement(amount)
                .sendKeys(depositAmount);

        driver.findElement(description)
                .sendKeys(remarks);

        driver.findElement(submitBtn)
                .click();
    }
    
    
    public void acceptDepositeAlert() {
		 Alert alert = WaitUtil.waitForAlert(driver);

	        String alertText = alert.getText();

	        System.out.println("Alert Message: " + alertText);

	        Assert.assertTrue( alertText.contains("Please fill all fields"),
	                "Unexpected alert message");

	        alert.accept();
	}

    public boolean isDepositSuccessful() {

        return driver.findElement(successMessage)
                .isDisplayed();
    }
}