package tests;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CustomerPage;
import pages.LoginPage;
import utilities.ExcelUtil;
import utilities.WaitUtil;

public class CustomerTest extends BaseTest  {
	
    private CustomerPage customerPage;
    int rowNumber=1;
	  @BeforeClass
	    public void initPages() {

	        customerPage = new CustomerPage(driver);
	        loginToApplication(); 
	    }
	@DataProvider(name="customerData")
	public Object[][] getCustomerData(){

	    ExcelUtil excel =
	        new ExcelUtil(
	        "src/test/resources/testdata/CustomerData_Sample.xlsx",
	        "CustomerData");

	    return excel.getSheetData();
	}
	
	
	@Test(dataProvider="customerData")
	public void addCustomerTest(
	        String tcId,
	        String name,
	        String dob,
	        String address,
	        String city,
	        String state,
	        String pin,
	        String phone,
	        String email,
	        String password,
	        String customerId,
	        String executionStatus,
	        String expectedresult
	        ) {
		
		

	    customerPage.navigateToNewCustomer();

	    String uniqueEmail =
	            System.currentTimeMillis()
	            + email;

	    customerPage.createNewCustomer(
	            name,
	            dob,
	            address,
	            city,
	            state,
	            pin,
	            phone,
	            uniqueEmail,
	            password);

	
	    
	    if(expectedresult.equalsIgnoreCase("valid")) {
	    	
	        String generatedCustomerId =
		            customerPage.getCustomerId();
		    Assert.assertFalse(generatedCustomerId.isEmpty());
		    
		    ExcelUtil excel =
		            new ExcelUtil(
		            "src/test/resources/testdata/CustomerId.xlsx",
		            "CustomerID");
		   
//
//		    int rowNumber =
//		            excel.findRow(tcId);
		  

		    excel.setCellData(
		            rowNumber,
		            0,
		            generatedCustomerId);

//		    excel.setCellData(
//		            rowNumber,
//		            11,
//		            "CREATED");
		    
		    rowNumber++;

	    }else {
	    	 try {
        		 
        		 Alert alert = WaitUtil.waitForAlert(driver);

        	        String alertText = alert.getText();

        	        System.out.println("Alert Message: " + alertText);

        	        Assert.assertTrue( alertText.contains("please fill all fields"),
        	                "Unexpected alert message");

        	        alert.accept();

        	    } catch(Exception e) {

        	        Assert.fail("Expected alert not displayed");
        	    }
	    }


	   
	}
	
	
	
	//edit Test
//	
//	@DataProvider(name="customerId")
//	public Object[][] getCustomerId(){
//
//	    ExcelUtil excel =
//	        new ExcelUtil(
//	        "src/test/resources/testdata/CustomerId.xlsx",
//	        "CustomerID");
//
//	    return excel.getSheetData();
//	}
//	
//	@Test(dataProvider="customerId")
//	public void editCustomerTest(String custId) {
////
////	    ExcelUtil excel =
////	        new ExcelUtil(
////	        "src/test/resources/testdata/CustomerId.xlsx",
////	        "CustomerID");
////
////	    String custId =
////	            excel.getCellData(rowNumber-1,0);
////	    rowNumber--;
//
//	    customerPage.editCustomer(
//	            custId,
//	            "Updated Address",
//	            "Updated City",
//	            "Updated State");
//
//	    Assert.assertTrue(
//	            driver.getPageSource()
//	                    .contains("Customer details updated Successfully"));
//
////	    excel.setCellData(
////	            1,
////	            11,
////	            "UPDATED");
//	}
//	
	
	
	
	
	
}
