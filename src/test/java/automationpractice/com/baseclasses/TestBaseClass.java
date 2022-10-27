package automationpractice.com.baseclasses;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestBaseClass {

    protected WebDriver driver;

    @BeforeClass
    public void startUpBrowser() {

        String localDir = System.getProperty("user.dir");
        System.setProperty("web-driver.gecko.driver", localDir+"/geckodriver.exe");

        String localDir1 = System.getProperty("user.dir");
        System.setProperty("web-driver.chrome.driver", localDir1+"/chromedriver.exe");

        driver = new FirefoxDriver();
        driver.get("http://automationpractice.com/index.php");

//        driver1 = new ChromeDriver();
//        driver1.get("http://automationpractice.com/index.php");

        doBasicCheck();
    }

    @Test(description = "No exception thrown by findElement considered a successful test")
    public void checkSignInButtonIsPresent() {
        driver.findElement(By.className("login"));
    }


    private void doBasicCheck() {
        driver.findElement(By.id("header_logo"));
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        System.out.println("Closing down the browser");
        driver.close();
    }
}
