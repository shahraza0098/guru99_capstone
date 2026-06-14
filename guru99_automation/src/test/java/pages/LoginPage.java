package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.WaitUtil;

public class LoginPage {

    WebDriver driver;
    WaitUtil waitUtil;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);
    }

    private By username = By.name("uid");
    private By password = By.name("password");
    private By loginbtn = By.name("btnLogin");
    private By welcomeMessage = By.xpath("//marquee[@class='heading3']");

    public void enterUsername(String user1) {
        waitUtil.waitForElementVisible(username).sendKeys(user1);
    }

    public void enterPassword(String pass) {
        waitUtil.waitForElementVisible(password).sendKeys(pass);
    }

    public void clickLogin() {
        waitUtil.waitForElementClickable(loginbtn).click();
    }

    public void loginToApplication(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }

    public boolean isLoginSuccessful() {
        return waitUtil.waitForElementVisible(welcomeMessage)
                .getText()
                .equals("Welcome To Manager's Page of Guru99 Bank");
    }
}