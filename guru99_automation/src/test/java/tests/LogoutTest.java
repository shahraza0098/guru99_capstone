package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LogoutPage;

public class LogoutTest extends BaseTest {

    LogoutPage logoutPage;

    @BeforeClass
    public void logoutSetup() {

     

        logoutPage = new LogoutPage(driver);
        loginToApplication(); 
    }

    @Test
    public void logoutTest() {

        logoutPage.clickLogout();

        String alertText =
                logoutPage.getLogoutAlertText();

        System.out.println(
                "Logout Alert : "
                        + alertText);

        Assert.assertTrue(
                alertText.contains("You Have Succesfully Logged Out"));

        Assert.assertTrue(
                logoutPage.isLoginPageDisplayed());

        System.out.println(
                "Logout Successful");
    }
}