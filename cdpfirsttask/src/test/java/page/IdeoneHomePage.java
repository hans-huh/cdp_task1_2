package page;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IdeoneHomePage extends BasePage {
    public IdeoneHomePage(WebDriver driver){
        super(driver);
    }

    // CONSTANTS
    private String C_PREPROCESSOR_STDIO = "<stdio.h>";
    private String STDIN_TO_PASTE = "Hello World!";
    private String[] CODE_TO_PASTE = {"#include <stdio.h>\n", "int main(void) {", "\nchar toPrint[50];",
            "\nfgets(toPrint, 50, stdin);", "\nprintf(toPrint);"};


    // @FIND_BY ANNOTATIONS
    @FindBy(xpath = "//*[@id='lang-dropdown-menu-button']/span")
    private WebElement languageOptionButton;

    @FindBy(xpath = "//a[text()='C']")
    private WebElement languageOption;

    @FindBy(className = "ace_text-input")
    private WebElement editorWindow;

    @FindBy(xpath = "//*[@id='button-input']")
    private WebElement stdinButton;

    @FindBy(xpath = "//*[@id='input']")
    private WebElement stdinWindow;

    @FindBy(xpath = "//button[@id='Submit']")
    private WebElement runCodeButton;

    // HOME PAGE INTERACTIONS ARE STORED HERE

    public String returnHomePageURL(){
        return driver.getCurrentUrl();
    }

    public String switchProgrammingLanguage(){

        new WebDriverWait(driver, 20).until(ExpectedConditions
        .elementToBeClickable(languageOptionButton)).click();

        new WebDriverWait(driver, 20).until(ExpectedConditions
        .elementToBeClickable(languageOption)).click();
        return languageOptionButton.getText();
    }

     public void pasteCode()  {

        // Make sure that the default Java code in the editor is replaced by the default C code
        new WebDriverWait(driver, 20).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver){
                Boolean result = C_PREPROCESSOR_STDIO
                        .contains(driver.findElement(By.xpath("//*[contains(text(), '<stdio.h>')]"))
                                .getText().trim());
                return result;
            }
        });

        // Select and delete all default code
        editorWindow.sendKeys(Keys.CONTROL + "a");
        editorWindow.sendKeys(Keys.DELETE);

        // and finally paste the code
        for (String codeLine:CODE_TO_PASTE) {
            editorWindow.sendKeys(codeLine);
        }
    }


    public boolean clickStdinButton(){
        stdinButton.click();
        return new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(stdinWindow)).isDisplayed();
    }


    public void pasteText() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(stdinWindow));
        stdinWindow.sendKeys(STDIN_TO_PASTE);
        System.out.println(stdinWindow.getText());
    }

    public String runCode(){
        final String pageUrlBeforeRun = driver.getCurrentUrl();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(runCodeButton)).click();

        // The method basically fails by TimeOut if the URL does not change. That makes the Assertion in the IdeoneHomePageTest class redundant. TBD how to chamge it.
        // Probably make an explicitWait based on the page's new title
        new WebDriverWait(driver, 20).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver){
                return !(pageUrlBeforeRun.equals(driver.getCurrentUrl()));
            }
        });

        return driver.getCurrentUrl();
    }

    public String getStdinToPaste(){
        return STDIN_TO_PASTE;
    }
}
