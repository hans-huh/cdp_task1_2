package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.IdeoneHomePage;
import page.IdeoneResultsPage;

public class IdeoneResultsPageTest extends BaseTest {

    @Test(priority = 6)
    public void checkStatusOfYourCodeAfterRun(){
        IdeoneResultsPage resultsPage = new IdeoneResultsPage(driver);
        Assert.assertTrue(resultsPage.checkStatus().equals("info green"));
    }

    @Test(priority = 7)
    public void checkStdinInResultsEqualsStdinInHome(){
        IdeoneResultsPage resultPage = new IdeoneResultsPage(driver);
        IdeoneHomePage homePage = new IdeoneHomePage(driver);
        Assert.assertEquals(resultPage.getTextFromStdin(), homePage.getStdinToPaste());
    }

    @Test(priority = 8)
    public void checkStdoutInResultsEqualsStdinHome(){
        IdeoneResultsPage resultPage = new IdeoneResultsPage(driver);
        IdeoneHomePage homePage = new IdeoneHomePage(driver);
        Assert.assertEquals(resultPage.getTextFromStdout(), homePage.getStdinToPaste());
    }

    @Test(priority = 9)
    public void checkResultsPageTitleContainsIdeone(){
        IdeoneResultsPage resultsPage = new IdeoneResultsPage(driver);
        Assert.assertTrue(resultsPage.getResultPageTitle().toLowerCase().contains("ideone"));
    }




}
