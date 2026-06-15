package tests;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utilities.ExcelUtil;
import utilities.WaitUtil;

public class LoginTest extends BaseTest {

	
	LoginPage loginPage;
	  @BeforeClass
	    public void accountSetup() {

		loginPage=new LoginPage(driver);
//		driver.navigate().to("https://demo.guru99.com/V4/");
	    }

	
	
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {

        ExcelUtil excel = new ExcelUtil(
                "src/test/resources/testdata/Guru99BankData.xlsx",
                "LoginData");

        return excel.getSheetData();
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username,
                          String password,
                          String status) {
    	driver.navigate().to("https://demo.guru99.com/V4/");

    	loginPage.loginToApplication(username, password);

        if(status.equalsIgnoreCase("valid")) {

            HomePage homePage = new HomePage(driver);

            Assert.assertTrue(
                    homePage.isManagerDisplayed(),
                    "Valid Login Failed");

            System.out.println("Valid Login Passed");

        } else {

        	
        	 try {
        		 
        		 if(password.isEmpty()) {
        			 loginPage.clickLogin();
        		 }
        		 
        	

        		 Alert alert = WaitUtil.waitForAlert(driver);

        	        String alertText = alert.getText();

        	        System.out.println("Alert Message: " + alertText);

        	        Assert.assertTrue(
        	                alertText.contains("User or Password is not valid"),
        	                "Unexpected alert message");

        	        alert.accept();

        	    } catch(Exception e) {

        	        Assert.fail("Expected alert not displayed");
        	    }
        }
    }
}