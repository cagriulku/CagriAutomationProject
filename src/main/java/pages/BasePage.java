package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToElement(By elementLocator) {
        System.out.println("Starting scroll operation: " + elementLocator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(elementLocator));
        System.out.println("Scroll operation completed.");
    }

    public boolean scrollAndCheckElementVisibility(By elementLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        int scrollCount = 0;
        int maxScrolls = 10;
        while (scrollCount < maxScrolls) {
            try {
                WebElement element = driver.findElement(elementLocator);
                if (wait.until(ExpectedConditions.visibilityOf(element)) != null) {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Element is still not visible");
            }
            System.out.println("Scrolling... " + (scrollCount + 1) + " scroll");
            scrollToElement(elementLocator);
            scrollCount++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("Element did not become visible!");
    }
}
