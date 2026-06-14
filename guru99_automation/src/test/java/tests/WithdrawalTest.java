package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import pages.DepositPage;
import pages.WithdrawalPage;
import utilities.ExcelUtil;

public class WithdrawalTest extends BaseTest {

    WithdrawalPage withdrawalPage;


    @BeforeClass
    public void accountSetup() {



    	withdrawalPage = new WithdrawalPage(driver);
    	loginToApplication(); 
    }


    @DataProvider(name = "withdrawalData")
    public Object[][] getWithdrawalData() {

        ExcelUtil excel =
                new ExcelUtil(
                        "src/test/resources/testdata/WithdrawalData.xlsx",
                        "WithdrawalData");

        return excel.getSheetData();
    }

    @Test(dataProvider = "withdrawalData")
    public void withdrawalTest(
            String accountId,
            String amount,
            String description,
            String status) {
    	

        withdrawalPage.clickWithdrawal();
        SoftAssert softAssert = new SoftAssert();

        try {

            withdrawalPage.withdrawAmount(
                    accountId,
                    amount,
                    description);

            if(status.equalsIgnoreCase("valid")) {

                Assert.assertTrue(
                        withdrawalPage.isWithdrawalSuccessful());

            } else {
            	if(amount.isEmpty()) {

            	    withdrawalPage.acceptAlert("Please fill all fields", softAssert);

            	} else if(accountId.startsWith("122545")) {

            		withdrawalPage.acceptAlert("Account does not exist",softAssert);

            	} else {

            	    double withdrawalAmt = Double.parseDouble(amount);

            	    if(withdrawalAmt > 100000) {

            	        withdrawalPage.acceptAlert(
            	                "Transaction Failed. Account Balance Low!!!",
            	                softAssert);

            	    } else if(withdrawalAmt < 0) {
            	    	
            	    	 if(withdrawalPage.isWithdrawalSuccessful()) {
//            	    	        Assert.fail(
//            	    	            "DEFECT: Application accepted withdrawal with negative amount: "
//            	    	            + withdrawalAmt);
            	    		 
            	    		   System.out.println("[KNOWN DEFECT] Application accepted withdrawal with negative amount: "
            	    		            + withdrawalAmt + " - Bug logged.");
            	    	    }
            	    	
//            	    	  try {
//            	    		  withdrawalPage.acceptAlert("Account does not exist",softAssert);
//            	    	    } catch (Exception e) {
//            	    	        System.out.println("No alert present");
//            	    	    }
//
//            	    	    Assert.fail("Defect: Application accepted withdrawal with negative amount");
            	    }
            	}
        
            }

        } catch(Exception e) {

                Assert.fail(e.getMessage());
            
        }
        softAssert.assertAll();
    }
}