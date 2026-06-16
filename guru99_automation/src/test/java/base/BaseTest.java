package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import pages.LoginPage;
import utilities.ConfigReader;

public class BaseTest {

    protected WebDriver driver;

    protected LoginPage loginPage;
    
    @Parameters("browser")
    @BeforeClass
    public void setup(@Optional("chrome")String browser) {

        driver = DriverFactory.initDriver(browser);

        driver.get(ConfigReader.getProperty("url"));
        

        loginPage = new LoginPage(driver);
        
    }

    protected void loginToApplication() {

        loginPage.enterUsername("mngr662995");

        loginPage.enterPassword("Uzutynu");

        loginPage.clickLogin();
    }


    @AfterClass
    public void tearDown() {
    	System.out.println("test completed");
//        DriverFactory.quitDriver();
    }
    
    
  
}