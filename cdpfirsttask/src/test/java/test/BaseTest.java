package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected static WebDriver driver;
    private String BASE_URL = "https://ideone.com/";


    @BeforeSuite
    public void setup(){
        /* === uncomment this and replace PATH_NAME with the path to chromedriver.exe ===

        System.setProperty("webdriver.chrome.driver", "PATH_NAME");
        */

        driver = new ChromeDriver();
        driver.get(BASE_URL);
   }

   @AfterSuite
   public void shutdown(){
        driver.quit();
   }
}
