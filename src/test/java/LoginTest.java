import Base.BaseTest;
import Page.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void initializePageObjects() {
        loginPage = new LoginPage(chromeDriver);
    }
    public void performLogin(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index"));
    }
    // Đăng nhập thành công
    @Test
    public void loginTest() {
        performLogin("Admin", "admin123");
        String currentUrl = chromeDriver.getCurrentUrl();
        assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", "KHONG DEN DUOC TRANG CHINH");
        System.out.println("DA CHUYEN HUONG DUNG TRANG CHINH " + currentUrl);
    }

    @Test
    public void errorUsername() {
        testInvalidLogin("Id1401", "admin123");
    }

    @Test
    public void errorPassword() {
        testInvalidLogin("Admin", "errorpassword");
    }

    @Test
    public  void testLoginWithLeadingTrailingSpaces()
    {
        loginPage.clearUsername();
        loginPage.clearPassword();
        loginPage.enterUsername(" Admin ");
        loginPage.enterPassword(" admin123 ");
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-alert oxd-alert--error']")));
        assertTrue(loginPage.isErrorMessageDisplayed(), "THONG BAO LOI KHONG DUOC HIEN THI");
        System.out.println("THONG BAO LOI HIEN THI THANH CONG");
    }
    private void testInvalidLogin(String username, String password) {
        loginPage.clearUsername();
        loginPage.clearPassword();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-alert oxd-alert--error']")));
        assertTrue(loginPage.isErrorMessageDisplayed(), "THONG BAO LOI KHONG DUOC HIEN THI");
        System.out.println("THONG BAO LOI HIEN THI THANH CONG");
    }
}