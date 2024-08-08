package Page;

import Base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@name='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='oxd-alert oxd-alert--error']")
    private WebElement errorMessage;

    public LoginPage(ChromeDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String strusername) {
        waitForVisibility(username);
        username.sendKeys(strusername);
    }

    public void enterPassword(String strpassword) {
        waitForVisibility(password);
        password.sendKeys(strpassword);
    }

    public void clickLogin() {
        waitForClickability(loginButton);
        loginButton.click();
    }
    public  void clearUsername() {
        waitForVisibility(username);
        username.clear();
    }
    public  void clearPassword() {
        waitForVisibility(password);
        password.clear();
    }
    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

}
