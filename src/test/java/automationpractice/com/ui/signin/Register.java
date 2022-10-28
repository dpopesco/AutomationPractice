package automationpractice.com.ui.signin;

import automationpractice.com.baseclasses.TestBaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Register extends TestBaseClass {

    private final String emailId = "email_create";

    @Test
    public void withValidInputDataRegistersAndLogsInSuccessfully() {
        //identifies Sign In button and clicks on it
        WebElement signInButton = driver.findElement(By.className("login"));
        signInButton.click();

        //used implicit (30 s) and explicit wait for the web page to load and web element to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(emailId)));

        //generates random UniversallyUniqueID and extracts first 10 characters from it
        String generatedUserName = UUID.randomUUID().toString().substring(0, 10);
        //identifies email field
        WebElement emailRegister = driver.findElement(By.id(emailId));
        //randomly generated UUID is concatenated with "@sample.com"
        String generatedEmail = generatedUserName + "@sample.com";
        //generated email is written in email field
        emailRegister.sendKeys(generatedEmail);

        //identifies Create An Account Button and clicks on it
        WebElement createAnAccountButton = driver.findElement(By.id("SubmitCreate"));
        createAnAccountButton.click();

        //used implicit (60 s) and explicit wait for the web page to load and heading element to appear
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h3[text()='Your personal information']")));

        //identifies page heading element and verifies that heading text is the same as expected
        WebElement createAccountHeading = driver.findElement(By.className("page-heading"));
        Assert.assertEquals(createAccountHeading.getText(), "CREATE AN ACCOUNT");

        //identifies gender radiobutton and clicks on it
        WebElement gender = driver.findElement(By.id("id_gender2"));
        gender.click();

        //creates a list of first names
        List<String> firstNames = Arrays.asList("Ecaterina", "Beatrice", "Ilinca", "Monica");
        //size() method returns number of elements in this list
        int firstNamesSize = firstNames.size();
        //generates a random number between 0 and the size of the array
        int randomIndex = new Random().nextInt(0, firstNamesSize);
        //returns first name at the specified position from array list
        String randomFirstName = firstNames.get(randomIndex);

        //identifies first name field and writes generated first name in it
        WebElement firstName = driver.findElement(By.id("customer_firstname"));
        firstName.sendKeys(randomFirstName);

        //creates a list of last names
        List<String> lastNames = Arrays.asList("Potanga", "Popesco", "Miloshevici", "Bing");
        //size() method returns number of elements in this list
        int lastNamesSize = lastNames.size();
        //generates a random number between 0 and the size of the array
        randomIndex = new Random().nextInt(0, lastNamesSize);
        //returns first name at the specified position from array list
        String randomLastName = lastNames.get(randomIndex);

        //identifies last name field and writes generated last name in it
        WebElement lastName = driver.findElement(By.id("customer_lastname"));
        lastName.sendKeys(randomLastName);

        //identifies password field and writes in it
        WebElement password = driver.findElement(By.id("passwd"));
        password.sendKeys("Quatro");

        //identifies address field and writes in it
        WebElement address = driver.findElement(By.id("address1"));
        address.sendKeys("SUA");

        //identifies city field and writes in it
        WebElement city = driver.findElement(By.id("city"));
        city.sendKeys("Luisville");

        //selects in dropdown button specified text
        Select drpStates = new Select(driver.findElement(By.name("id_state")));
        drpStates.selectByVisibleText("Kentucky");

        //create an object of type Random with seed equal to current time in milliseconds
        //to have always different values in random
        Random r = new Random(System.currentTimeMillis());
        //converts into string a random value from 20001 to 29999
        String randomPostcode = String.valueOf(20000 + r.nextInt(10000));

        //identifies postcode field and writes in it 5 digit random number
        WebElement postcode = driver.findElement(By.id("postcode"));
        postcode.sendKeys(randomPostcode);

        //converts into string a random value from 400000001 to 499999999
        String randomMobile = String.valueOf(400000000 + r.nextInt(100000000));

        //identifies mobile phone field and writes in it 9 digit random number
        WebElement mobilePhone = driver.findElement(By.id("phone_mobile"));
        mobilePhone.sendKeys(randomMobile);

        //identifies Register button by xpath and clicks on it
        WebElement registerButton = driver.findElement(By.xpath("//button[@id='submitAccount']/span"));
        registerButton.click();

        //assigned locator xpath in a String variable
        String accountNameXPath = "//a[@class='account']/span";
        //used implicit (60 s) and explicit wait for the web page to load and web element to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(accountNameXPath)));

        //identifies account name web element after register
        WebElement accountName = driver.findElement(By.xpath(accountNameXPath));
        //verify that account name is same as generated first and last name written previously
        Assert.assertEquals(accountName.getText(), randomFirstName + " " + randomLastName);

        //identifies Sign Out button and clicks on it
        WebElement signout = driver.findElement(By.className("logout"));
        signout.click();

        //used implicit (30 s) and explicit wait for the web page to load and web element to appear
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h3[text()='Already registered?']")));

        //identifies email login field and writes in it previously registered email
        WebElement emailLogin = driver.findElement(By.id("email"));
        emailLogin.sendKeys(generatedEmail);

        //identifies password login field and writes in it the password for previously registered account
        WebElement userPass = driver.findElement(By.id("passwd"));
        userPass.sendKeys("Quatro");

        //identifies Log In button and clicks on it
        WebElement logInButton = driver.findElement(By.id("SubmitLogin"));
        logInButton.click();

        //identifies Sign Out Button and assert that user logged in successfully
        WebElement signoutOption = driver.findElement(By.className("logout"));
        Assert.assertTrue(signoutOption.isDisplayed());
    }

    // add test data set for negative testing using DataProvider annotation
    @DataProvider
    protected Object[][] invalidEmailProvider() {
        return new Object[][]{
                {" "},
                {"johnemail.com"},
                {"john@emailcom"},
        };
    }

    // the invalidEmail parameter is valorized by invalidEmailProvider
    // meaning that this test will be called 3 times with values from DataProvider
    @Test(dataProvider = "invalidEmailProvider")
    public void withInvalidEmailAddressInputShowsError(String invalidEmail) {

        //identifies Sign In button and clicks on it
        WebElement signInButton = driver.findElement(By.className("login"));
        signInButton.click();

        //used implicit (30 s) and explicit wait for the web page to load and web element to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(emailId)));

        //identifies email field and writes from data provider invalid email
        WebElement emailField = driver.findElement(By.id(emailId));
        emailField.sendKeys(invalidEmail);

        //identifies email label above the email field by xpath
        WebElement emailLabel = driver.findElement(By.xpath("//div/label[@for='email_create']"));
        //move to label element by clicking
        Actions action = new Actions(driver);
        action.moveToElement(emailLabel).click().build().perform();

        //used implicit (30 s) and explicit wait for the web page to load and css class to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".form-error")));

        //verifies that class .form-error appears when we write wrong format emails
        WebElement errorElement = driver.findElement(By.cssSelector(".form-error"));
        Assert.assertTrue(errorElement.isDisplayed());
    }
}
