package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
public class BaseTest {
    protected WebDriver driver;
    private boolean closeBrowser = true;
    @BeforeMethod
    public void setUp() {
        loadChromeDriver();
        driver.get("https://useinsider.com/");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null && closeBrowser) {
            driver.quit();
        }
    }
    private void loadChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-dev-shm-usage");
        this.driver = new ChromeDriver(options);
    }
    public void navigateTo(String url) {
        driver.get("https://useinsider.com/careers/quality-assurance/");
    }
    public void preventBrowserClose() {
        this.closeBrowser = false;
    }
}
