package automationpractice.com;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestBaseClass {

    WebDriver driver;

    @BeforeSuite
    public void startUpBrowser() {

        String localDir = System.getProperty("user.dir");
        System.setProperty("web-driver.chrome.driver", localDir+"/chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");

        doBasicCheck();
    }

    @Test(description = "No exception thrown by findElement considered a successful test")
    public void checkSignInButtonIsPresent() {
        driver.findElement(By.className("login"));
    }


    private void doBasicCheck() {
        driver.findElement(By.id("header_logo"));
    }

    @AfterSuite(alwaysRun = true)
    public void closeBrowser() {
        System.out.println("Closing down the browser");
        driver.close();
    }
}
