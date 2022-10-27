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
import java.util.*;

public class Register extends TestBaseClass {

    private final String emailId = "email_create";

    @Test
    public void withValidInputDataRegistersAndLogsInSuccessfully() {

        WebElement signInButton = driver.findElement(By.className("login"));
        signInButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(emailId)));

        String userNameGenerator = UUID.randomUUID().toString().substring(0, 10);
        WebElement emailRegister = driver.findElement(By.id(emailId));
        String generatedEmail = userNameGenerator + "@sample.com";
        emailRegister.sendKeys(generatedEmail);

        WebElement createAnAccountIcon = driver.findElement(By.id("SubmitCreate"));
        createAnAccountIcon.click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[text()='Your personal information']")));

        WebElement createAccountHeading = driver.findElement(By.className("page-heading"));
        Assert.assertEquals(createAccountHeading.getText(), "CREATE AN ACCOUNT");

        WebElement gender = driver.findElement(By.id("id_gender2"));
        gender.click();

        List<String> fName = Arrays.asList("Ecaterina", "Beatrice", "Ilinca", "Monica");
        Collections.shuffle(fName);
        int size1 = fName.size();
        int item1 = new Random().nextInt(size1);
        String generatedFName = fName.get(item1);

        WebElement firstName = driver.findElement(By.id("customer_firstname"));
        firstName.sendKeys(generatedFName);

        List<String> lName = Arrays.asList("Potanga", "Popesco", "Miloshevici", "Bing");
        int size2 = fName.size();
        int item2 = new Random().nextInt(size2);
        String generatedLName = lName.get(item2);

        WebElement lastName = driver.findElement(By.id("customer_lastname"));
        lastName.sendKeys(generatedLName);

        WebElement password = driver.findElement(By.id("passwd"));
        password.sendKeys("Quatro");

        WebElement address = driver.findElement(By.id("address1"));
        address.sendKeys("SUA");

        WebElement city = driver.findElement(By.id("city"));
        city.sendKeys("Luisville");

        Select drpStates = new Select(driver.findElement(By.name("id_state")));
        drpStates.selectByVisibleText("Kentucky");

        Random r = new Random(System.currentTimeMillis());
        String randomPostcode = String.valueOf((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
        WebElement postcode = driver.findElement(By.id("postcode"));
        postcode.sendKeys(randomPostcode);

        Random r1 = new Random(System.currentTimeMillis());
        String randomNumber = String.valueOf((1 + r.nextInt(2)) * 10000000 + r.nextInt(10000000));
        WebElement mobilePhone = driver.findElement(By.id("phone_mobile"));
        mobilePhone.sendKeys(randomNumber);

        WebElement registerButton = driver.findElement(By.xpath("//button[@id='submitAccount']/span"));
        registerButton.click();


        String accountNameXPath = "//a[@class='account']/span";
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(accountNameXPath)));

        WebElement accountName = driver.findElement(By.xpath(accountNameXPath));
        Assert.assertEquals(accountName.getText(), generatedFName + " " + generatedLName);

        WebElement signout = driver.findElement(By.className("logout"));
        signout.click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[text()='Already registered?']")));


        WebElement emailLogin = driver.findElement(By.id("email"));
        emailLogin.sendKeys(generatedEmail);

        WebElement userPass = driver.findElement(By.id("passwd"));
        userPass.sendKeys("Quatro");

        WebElement logInButton = driver.findElement(By.id("SubmitLogin"));
        logInButton.click();

        WebElement signoutOption = driver.findElement(By.className("logout"));
        Assert.assertTrue(signoutOption.isDisplayed());
    }

    @DataProvider
    protected Object[][] invalidEmailProvider() {
        return new Object[][]{
                {" "},
                {"johnemail.com"},
                {"john@emailcom"},
        };
    }

    @Test(dataProvider = "invalidEmailProvider")
    public void withInvalidEmailAddressInputShowsError(String invalidEmail) {

        WebElement signInButton = driver.findElement(By.className("login"));
        signInButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(emailId)));

        WebElement emailField = driver.findElement(By.id(emailId));
        emailField.sendKeys(invalidEmail);

        WebElement emailElement = driver.findElement(By.xpath("//div/label[@for='email_create']"));
        Actions action = new Actions(driver);
        action.moveToElement(emailField).moveToElement(emailElement).click().build().perform();

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".form-error")));

        WebElement errorElement = driver.findElement(By.cssSelector(".form-error"));
        Assert.assertTrue(errorElement.isDisplayed());
    }
}
