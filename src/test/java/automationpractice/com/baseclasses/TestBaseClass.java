package automationpractice.com.baseclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBaseClass {

    //object for properties from configuration file
    //marked as private to be visible only in this class
    private Properties prop;

    //marked as protected to be available in child classes
    protected WebDriver driver;


    @BeforeClass
    public void startUpBrowser() throws Exception {

        //called method to load from configuration file specified properties
        //if configuration is not loaded throws an exception
        if (!loadConfiguration()) {
            throw new Exception("Configuration not loaded");
        }
        ;

        //gets the project directory absolute path
        String localDir = System.getProperty("user.dir");
        //find user's path to firefox driver to start up FireFox Browser
        System.setProperty("web-driver.gecko.driver", localDir + "/geckodriver.exe");

        //find user's path to chrome driver to start up Chrome Browser
        System.setProperty("web-driver.chrome.driver", localDir + "/chromedriver.exe");

        //assign browser property from config.properties file to a new string variable
        String browserName = prop.getProperty("browser");

        //depending on the name given in config.properties for browser property,
        //driver will use this browser for the tests
        if (browserName.contains("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.contains("firefox")) {
            driver = new FirefoxDriver();
        }

        //receives the url given in config.properties
        driver.get(prop.getProperty("url"));

        //calls method which verifies that page is open by finding an element from homepage
        doBasicCheck();
    }

    /**
     * loads configuration and instantiate the prop attribute
     *
     * @return true if configuration was loaded
     */
    private boolean loadConfiguration() {
        try {
            //creates an object of class Properties
            prop = new Properties();
            //open config file using file input stream
            FileInputStream ip = new FileInputStream(System
                    .getProperty("user.dir") + "/configuration/config.properties");
            //loading config.properties
            prop.load(ip);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //method which verifies that page is open by finding an element from homepage
    private void doBasicCheck() {
        driver.findElement(By.id("header_logo"));
    }

    //it will run regardless the results of tests (passed or failed)
    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        System.out.println("Closing down the browser");
        driver.close();
    }
}
