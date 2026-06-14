package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utilities.WaitUtil;

public class AccountPage {

    WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    // New Account Menu
    By newAccountLink =
            By.linkText("New Account");

    // Form Fields
    By customerId =
            By.name("cusid");

    By accountType =
            By.name("selaccount");

    By initialDeposit =
            By.name("inideposit");

    By submitBtn =
            By.name("button2");

    // Success Page
    By accountIdText =
            By.xpath("//td[text()='Account ID']/following-sibling::td");

 
    

	
	 public void clickNewAccount() {
		 
		    WaitUtil waitUtil = new WaitUtil(driver);
		    //
		    WebElement button =
		            waitUtil.waitForElementClickable(newAccountLink);

	
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].scrollIntoView(true);", button);
		    js.executeScript("arguments[0].click();", button);
		}
    
    public void clickSubmitBtn() {
    	driver.findElement(submitBtn)
        .click();
    }

    public void createAccount(
            String custId,
            String accType,
            String depositAmount) {

        driver.findElement(customerId)
                .sendKeys(custId);

        driver.findElement(accountType)
                .sendKeys(accType);

        driver.findElement(initialDeposit)
                .sendKeys(depositAmount);

        driver.findElement(submitBtn)
                .click();
    }
    

	public void acceptAccountFailedCreationAlert() {
		 Alert alert = WaitUtil.waitForAlert(driver);

	        String alertText = alert.getText();

	        System.out.println("Alert Message: " + alertText);

	        Assert.assertTrue( alertText.contains("Please fill all fields"),
	                "Unexpected alert message");

	        alert.accept();
	}

    public String getAccountId() {

        return driver.findElement(accountIdText)
                .getText();
    }
}