package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;

import pages.DepositPage;
import utilities.ExcelUtil;

public class DepositTest extends BaseTest {

    DepositPage depositPage;


  
   

    @BeforeClass
    public void accountSetup() {
    	depositPage = new DepositPage(driver);
    	loginToApplication(); 
    }

    @DataProvider(name = "depositData")
    public Object[][] getDepositData() {

        ExcelUtil excel =
                new ExcelUtil(
                        "src/test/resources/testdata/DepositData.xlsx",
                        "DepositData");

        return excel.getSheetData();
    }

@Test(dataProvider = "depositData")
public void depositTest(
        String accountId,
        String amount,
        String description,
        String status) {

    SoftAssert softAssert = new SoftAssert();

    depositPage.clickDeposit();

    depositPage.depositAmount(
            accountId,
            amount,
            description);

    
    
    if(status.equalsIgnoreCase("valid")) {

        try {

            boolean success =
                    depositPage.isDepositSuccessful();

            softAssert.assertTrue(
                    success,
                    "Deposit success page not displayed");

        } catch (Exception e) {

            String pageSource = driver.getPageSource();

            softAssert.assertFalse(
                    pageSource.contains("HTTP ERROR 500"),
                    "Application defect: HTTP ERROR 500 displayed after deposit");
            if(pageSource.contains("HTTP ERROR 500")) {
            	driver.navigate().back();
            }

            System.out.println(
                    "Deposit functionality appears broken. HTTP 500 page displayed.");
        }

    }else {
    	depositPage.acceptDepositeAlert();
    }
    softAssert.assertAll();
}
}