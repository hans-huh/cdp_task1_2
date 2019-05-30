package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.IdeoneHomePage;
import page.IdeoneResultsPage;

public class IdeoneHomePageTest extends BaseTest{

    // CONSTANTS
    private String BASE_URL = "https://ideone.com/";
    private String EXPECTED_LANGUAGE = "C";


    // TESTS
    @Test(priority = 0)
    public void checkThatHomePageOpen(){
        String actualURL = new IdeoneHomePage(driver).returnHomePageURL();
        Assert.assertEquals(actualURL, BASE_URL);
    }

    @Test(priority = 1)
    public void checkResultOfSwitchingLanguage(){
        String selectedLanguage = new IdeoneHomePage(driver).switchProgrammingLanguage();
        Assert.assertEquals(selectedLanguage, EXPECTED_LANGUAGE);
    }

    @Test(priority = 2)
    public void pasteCodeInEditor() {
        IdeoneHomePage page = new IdeoneHomePage(driver);
        page.pasteCode();
    }

    @Test(priority = 3)
    public void clickOnStdinButton(){
        Assert.assertTrue(new IdeoneHomePage(driver).clickStdinButton());
    }

    @Test(priority = 4)
    public void PasteTextInStdin() {
        IdeoneHomePage page = new IdeoneHomePage(driver);
        page.pasteText();
    }

    @Test(priority = 5)
    public void runCodeAndWaitForResultPageToLoad(){
        String newURL =  new IdeoneHomePage(driver).runCode();
        Assert.assertNotEquals(BASE_URL, newURL);
    }

}
