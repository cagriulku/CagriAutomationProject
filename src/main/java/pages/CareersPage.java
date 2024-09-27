package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CareersPage extends BasePage {
    private By teamsBlock = By.xpath("//a[text()='See all teams']");
    private By locationsBlock = By.xpath("//*[contains(text(),'Our Locations')]");
    private By firstElement = By.xpath("(//a[text()='Find your dream job'])[2]");
    private By lifeAtInsiderBlock = By.xpath("//h2[text()='Life at Insider']");

    public CareersPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCareersPageOpened() {
        return driver.getCurrentUrl().contains("/careers");
    }

    public boolean areBlocksDisplayed() {
        System.out.println("Checking the visibility of elements...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return scrollAndCheckElementVisibility(teamsBlock) && scrollAndCheckElementVisibility(locationsBlock)
                && scrollAndCheckElementVisibility(lifeAtInsiderBlock);
    }
}
