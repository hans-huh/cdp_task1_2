package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IdeoneResultsPage extends BasePage {
    public IdeoneResultsPage(WebDriver driver){
        super(driver);
    }

    // CONSTANTS TO USE


    //FIND_BY annotations
    @FindBy(xpath = "//div[@id='view_status']")
    private WebElement compilationStatusBar;

    @FindBy(xpath = "//*[@id='view_status']/span/span")
    private WebElement compilationStatusText;

    @FindBy(xpath = "//*[@id='view_stdin']//pre")
    private WebElement resultPageStdinBar;

    @FindBy(xpath = "//*[@id='output-text']")
    private WebElement resultPageStdoutBar;


    // METHODS

    public String checkStatus(){
        // Waiting for the code to be compiled and run. Checking the output string.
        // If the string is empty, it means that the code is still compiling
        (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver){
                return driver.findElement(By.id("output-text")).getText().length() != 0;
            }
        });

        // Getting status
        String status = compilationStatusText.getAttribute("class");
        return status;
    }

    public String getTextFromStdin(){

        String result = resultPageStdinBar.getText();
        // Boolean result = new WebDriverWait(driver, 20).until(ExpectedConditions.textToBePresentInElement(resultPageStdinBar, new IdeoneHomePage(driver).getStdinToPaste()));
        return result;
    }

    public String getTextFromStdout(){
        String result = resultPageStdoutBar.getText();
        return result;
    }

    public String getResultPageTitle(){
        String result = driver.getTitle();
        return result;
    }
}
