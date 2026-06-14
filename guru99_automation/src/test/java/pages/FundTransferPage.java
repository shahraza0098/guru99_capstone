package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utilities.WaitUtil;

public class FundTransferPage {

    WebDriver driver;

    public FundTransferPage(WebDriver driver) {
        this.driver = driver;
    }

    By fundTransferLink =
            By.linkText("Fund Transfer");

    By payerAccount =
            By.name("payersaccount");

    By payeeAccount =
            By.name("payeeaccount");

    By amount =
            By.name("ammount");

    By description =
            By.name("desc");

    By submitBtn =
            By.name("AccSubmit");

    By successMessage =
            By.xpath("//p[contains(text(),'Fund Transfer Details')]");
    
    public void clickTrasferSubmitBtn() {
    	driver.findElement(submitBtn).click();
    }


    
//    public void clickFundTransfer() {
//
//	    ((JavascriptExecutor) driver)
//	            .executeScript("window.scrollTo(0,300)");
//
//	    WaitUtil waitUtil = new WaitUtil(driver);
//
//	    WebElement button =
//	            waitUtil.waitForElementClickable(fundTransferLink);
//
//	    button.click();
//	}
    
    public void clickFundTransfer() {

        WebElement button =
                driver.findElement(fundTransferLink);

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView(true);",
                        button);

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        button);
    }

    public void transferFunds(
            String payerAcc,
            String payeeAcc,
            String transferAmount,
            String remarks) {

        driver.findElement(payerAccount)
                .sendKeys(payerAcc);

        driver.findElement(payeeAccount)
                .sendKeys(payeeAcc);

        driver.findElement(amount)
                .sendKeys(transferAmount);

        driver.findElement(description)
                .sendKeys(remarks);

        driver.findElement(submitBtn)
                .click();
    }

    public boolean isTransferSuccessful() {

        return driver.findElement(successMessage)
                .isDisplayed();
    }
    

	public void acceptAccNotExist() {
		 Alert alert = WaitUtil.waitForAlert(driver);

	        String alertText = alert.getText();

	        System.out.println("Alert Message: " + alertText);

	        Assert.assertTrue( alertText.contains("does not exist!!!"),
	                "Unexpected alert message");

	        alert.accept();
	}
	
	
	
	public void acceptMissField() {
		 Alert alert = WaitUtil.waitForAlert(driver);

	        String alertText = alert.getText();

	        System.out.println("Alert Message: " + alertText);

	        Assert.assertTrue( alertText.contains("Please fill all fields"),
	                "Unexpected alert message");

	        alert.accept();
	}
	
	
	
	public void acceptLowBal() {
		 Alert alert = WaitUtil.waitForAlert(driver);

	        String alertText = alert.getText();

	        System.out.println("Alert Message: " + alertText);

	        Assert.assertTrue( alertText.contains("Transfer Failed. Account Balance low!!"),
	                "Unexpected alert message");

	        alert.accept();
	}
    
	
	public void acceptAccountNumSame() {
		 Alert alert = WaitUtil.waitForAlert(driver);

	        String alertText = alert.getText();

	        System.out.println("Alert Message: " + alertText);

	        Assert.assertTrue( alertText.contains("Payers account No and Payees account No Must Not be Same!!!"),
	                "Unexpected alert message");

	        alert.accept();
	}
	
	
    
}