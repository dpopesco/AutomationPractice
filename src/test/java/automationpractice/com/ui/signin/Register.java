package automationpractice.com.ui.signin;

import automationpractice.com.baseclasses.TestBaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Register extends TestBaseClass {

    private final String xpath = "//input[@data-qa='signup-email']";

    @Test
    public void withValidInputDataRegistersAndLogsInSuccessfully() {
        //identifies Sign In button and clicks on it
        WebElement signInButton = driver.findElement(By.xpath("//*[contains(text(),'Signup')]"));
        signInButton.click();

        //used implicit (30 s) and explicit wait for the web page to load and web element to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

        WebElement signupName = driver.findElement(By.xpath("//input[@data-qa='signup-name']"));
        //creates a list of first names
        List<String> firstNames = Arrays.asList("Ecaterina", "Beatrice", "Ilinca", "Monica");
        //size() method returns number of elements in this list
        int firstNamesSize = firstNames.size();
        //generates a random number between 0 and the size of the array
        int randomIndex = new Random().nextInt(0, firstNamesSize);
        //returns first name at the specified position from array list
        String randomFirstName = firstNames.get(randomIndex);
        //identifies first name field and writes generated first name in it
        signupName.sendKeys(randomFirstName);

        //generates random UniversallyUniqueID and extracts first 10 characters from it
        String generatedUserName = UUID.randomUUID().toString().substring(0, 10);
        //identifies email field
        WebElement emailRegister = driver.findElement(By.xpath(xpath));
        //randomly generated UUID is concatenated with "@sample.com"
        String generatedEmail = generatedUserName + "@sample.com";
        //generated email is written in email field
        emailRegister.sendKeys(generatedEmail);

        //identifies Create An Account Button and clicks on it
        WebElement signupButton = driver.findElement(By.xpath("//button[@data-qa='signup-button']"));
        signupButton.click();

        //used implicit (60 s) and explicit wait for the web page to load and heading element to appear
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(),'Enter Account Information')]")));

        //identifies page heading element and verifies that heading text is the same as expected
        WebElement createAccountHeading = driver.findElement(By.xpath("//h2/b[contains(text(),'Account Information')]"));
        Assert.assertEquals(createAccountHeading.getText(), "ENTER ACCOUNT INFORMATION");

        //identifies gender radiobutton and clicks on it
        WebElement gender = driver.findElement(By.id("uniform-id_gender1"));
        gender.click();

        //identifies password field and writes in it
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Quatro");

        WebElement firstName = driver.findElement(By.id("first_name"));
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
        WebElement lastName = driver.findElement(By.id("last_name"));
        lastName.sendKeys(randomLastName);

        //identifies address field and writes in it
        WebElement address = driver.findElement(By.id("address1"));
        address.sendKeys("Luisville");

        //selects in dropdown button specified text
        Select drpStates = new Select(driver.findElement(By.id("country")));
        drpStates.selectByVisibleText("United States");

        //identifies address field and writes in it
        WebElement state = driver.findElement(By.id("state"));
        state.sendKeys("Kentucky");

        //identifies city field and writes in it
        WebElement city = driver.findElement(By.id("city"));
        city.sendKeys("Luisville");

        //create an object of type Random with seed equal to current time in milliseconds
        //to have always different values in random
        Random r = new Random(System.currentTimeMillis());
        //converts into string a random value from 20001 to 29999
        String randomZipcode = String.valueOf(20000 + r.nextInt(10000));

        //identifies postcode field and writes in it 5 digit random number
        WebElement zipcode = driver.findElement(By.id("zipcode"));
        zipcode.sendKeys(randomZipcode);

        //converts into string a random value from 400000001 to 499999999
        String randomMobile = String.valueOf(400000000 + r.nextInt(100000000));

        //identifies mobile phone field and writes in it 9 digit random number
        WebElement mobilePhone = driver.findElement(By.id("mobile_number"));
        mobilePhone.sendKeys(randomMobile);

        //identifies Register button by xpath and clicks on it
        WebElement registerButton = driver.findElement(By.xpath("//button[@data-qa='create-account']"));
        registerButton.click();

        String continueXpath = "//*[contains(text(),'Continue')]";
        WebElement continueButton = driver.findElement(By.xpath(continueXpath));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(continueXpath)));
        continueButton.click();

        //assigned locator xpath in a String variable
        String accountNameXPath = "//a[contains(text(),'Logged in as')]/b";
        //used implicit (60 s) and explicit wait for the web page to load and web element to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(accountNameXPath)));

        //identifies account name web element after register
        WebElement accountName = driver.findElement(By.xpath(accountNameXPath));
        //verify that account name is same as generated first and last name written previously
        Assert.assertEquals(accountName.getText(), randomFirstName);

        //identifies Log Out button and clicks on it
        WebElement logout = driver.findElement(By.xpath("//a[@href='/logout']"));
        logout.click();

        //used implicit (30 s) and explicit wait for the web page to load and web element to appear
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h2[text()='Login to your account']")));

        //identifies email login field and writes in it previously registered email
        WebElement emailLogin = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
        emailLogin.sendKeys(generatedEmail);

        //identifies password login field and writes in it the password for previously registered account
        WebElement userPass = driver.findElement(By.xpath("//input[@data-qa='login-password']"));
        userPass.sendKeys("Quatro");

        //identifies Log In button and clicks on it
        WebElement logInButton = driver.findElement(By.xpath("//button[@data-qa='login-button']"));
        logInButton.click();

        //identifies Logout Button and assert that user logged in successfully
        WebElement logoutOption = driver.findElement(By.xpath("//a[@href='/logout']"));
        Assert.assertTrue(logoutOption.isDisplayed());
    }

}
