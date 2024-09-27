package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class JobListingPage extends BasePage {
    WebDriver driver;
    private By pageHeader = By.xpath("(//*[contains(text(), 'Quality Assurance')])[2]");
    private By locationFilter = By.xpath("//*[@id='select2-filter-by-location-container']");
    private By istanbulLocationOption = By.xpath("//li[contains(text(), 'Istanbul, Turkey')]");
    private By departmentFilter = By.xpath("//*[@id='select2-filter-by-department-container']");
    private By qaDepartmentOption = By.xpath("//li[contains(text(), 'Quality Assurance')]");
    private By jobPositions = By.xpath("//p[contains(@class, 'position-title')]");
    private By jobDepartments = By.xpath("//span[contains(@class, 'department')]");
    private By jobLocations = By.xpath("//div[contains(@class, 'location')]");
    private By viewRoleButton = By.xpath("//*[contains(text(),'View Role')]");
    private By seeAllQAJobsButton = By.xpath("//a[contains(text(), 'See all QA jobs')]");
    private By applyForThisJob = By.xpath("(//a[contains(text(), 'Apply for this job')])[1]");

    public JobListingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isJobListingPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            System.out.println("Page header locator: " + pageHeader.toString());
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader));
            System.out.println("Page header is displayed");
            return header.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Timeout: Page header not found within the expected time.");
            return false;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }

    public void filterByLocationAndDepartment() {
        driver.findElement(seeAllQAJobsButton).click();
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(locationFilter).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(locationFilter).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(locationFilter).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(istanbulLocationOption).click();
        driver.findElement(departmentFilter).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(qaDepartmentOption).click();
    }

    public boolean areJobsDisplayed() {
        return !driver.findElements(jobPositions).isEmpty();
    }

    public void clickSeeAllQAJobs() {
        driver.findElement(seeAllQAJobsButton).click();
    }

    public boolean areJobsCorrect(String department, String location) {
        List<WebElement> positions = driver.findElements(jobPositions);
        List<WebElement> departments = driver.findElements(jobDepartments);
        List<WebElement> locations = driver.findElements(jobLocations);

        for (int i = 0; i < positions.size(); i++) {
            if (!departments.get(i).getText().contains(department) || !locations.get(i).getText().contains(location)) {
                return false;
            }
        }
        return true;
    }

    public void clickViewRoleDetail() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(viewRoleButton));
            button.click();
            System.out.println("View role button clicked");
            scrollAndCheckElementVisibility(applyForThisJob);
        } catch (TimeoutException e) {
            System.out.println("Timeout: View role button not visible.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
