package Base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected ChromeDriver chromeDriver;
    protected WebDriverWait wait;

    public BasePage(ChromeDriver driver) {
        this.chromeDriver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Chờ cho phần tử hiển thị
    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Chờ cho phần tử có thể click
    protected void waitForClickability(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
