package tests;

import utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CareersPage;
import pages.HomePage;
import pages.JobListingPage;

public class InsiderTest extends BaseTest {

    @Test
    public void testCareerPageAndJobFilter() {
        HomePage homePage = new HomePage(driver);
        CareersPage careersPage = new CareersPage(driver);
        JobListingPage jobListingPage = new JobListingPage(driver);

        Assert.assertTrue(homePage.isHomePageOpened(), "Home page not opened!");
        System.out.println("Home page opened.");
        homePage.goToCareersPage();

        Assert.assertTrue(careersPage.isCareersPageOpened(), "Careers page not opened!");
        System.out.println("Navigated to careers page.");

        Assert.assertTrue(careersPage.areBlocksDisplayed(), "Career page blocks are not visible!");
        System.out.println("Blocks on the careers page are visible.");
        navigateTo("https://useinsider.com/careers/quality-assurance/");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Could not navigate to quality assurance page.");
        }
        System.out.println("Navigated to the Quality Assurance careers page.");

        Assert.assertTrue(jobListingPage.isJobListingPageOpened(), "Job listing page not opened!");
        System.out.println("Job listing page opened.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        jobListingPage.filterByLocationAndDepartment();
        System.out.println("QA job listings filtered.");

        Assert.assertTrue(jobListingPage.areJobsDisplayed(), "Job listings are not visible!");
        System.out.println("Filtered job listings are visible.");

        Assert.assertTrue(jobListingPage.areJobsCorrect("Quality Assurance", "Istanbul"), "Jobs are incorrect!");
        System.out.println("Jobs are in the correct department and location.");

        jobListingPage.clickViewRoleDetail();
        Assert.assertTrue(jobListingPage.areJobsDisplayed(), "Role details could not be displayed!");
        System.out.println("Role details are displayed.");

    }
}
