package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class HomePage {
    WebDriver driver;
    private By companyMenu = By.xpath("//a[contains(text(), 'Company')]");
    private By careersLink = By.xpath("//a[contains(text(), 'Careers')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public boolean isHomePageOpened() {
        return driver.getTitle().contains("Insider");
    }
    public void goToCareersPage() {
        driver.findElement(companyMenu).click();
        driver.findElement(careersLink).click();
    }
}
