package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AccountPage;
import utilities.ExcelUtil;

public class AccountTest extends BaseTest {

    AccountPage accountPage;
    int rowNum=1;

    @BeforeClass
    public void accountSetup() {

//        loginToApplication(
//                "mngr662531",
//                "eguvenA");

        accountPage = new AccountPage(driver);
        loginToApplication(); 
    }

    @DataProvider(name = "accountData")
    public Object[][] getAccountData() {

        ExcelUtil excel =
                new ExcelUtil(
                        "src/test/resources/testdata/NewAccount.xlsx",
                        "accountData");
        return excel.getSheetData();
    }

    @Test(dataProvider = "accountData")
    public void createAccountTest(
    
            String customerId,
            String accountType,
            String deposit,
            String status) {

        accountPage.clickNewAccount();

        accountPage.createAccount(
                customerId,
                accountType,
                deposit);
        
        if(status.equalsIgnoreCase("valid")) {
        	  String generatedAccountId =
                      accountPage.getAccountId();

              Assert.assertFalse( generatedAccountId.isEmpty(),"Account ID not generated");

              ExcelUtil excel =
                      new ExcelUtil(
                              "src/test/resources/testdata/AccountId.xlsx",
                              "AccountID");

            

              excel.setCellData( rowNum, 0,generatedAccountId);
              rowNum++;
              
              System.out.println(
                      "Generated Account ID : "
                              + generatedAccountId);
        }else {
        	if(deposit=="") {
        		accountPage.clickSubmitBtn();
        	}
        	accountPage.acceptAccountFailedCreationAlert();
        }

      
    }
}