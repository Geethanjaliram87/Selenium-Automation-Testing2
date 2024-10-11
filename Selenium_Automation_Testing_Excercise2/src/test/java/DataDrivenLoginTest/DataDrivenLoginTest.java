package DataDrivenLoginTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenLoginTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, boolean expectedSuccess) {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();

        if (expectedSuccess) {
            Assert.assertTrue(isLoginSuccessful(), "Expected login to succeed for user: " + username);
        } else {
            Assert.assertFalse(isLoginSuccessful(), "Expected login to fail for user: " + username);
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() {
        return new Object[][] {
            {"standard_user", "secret_sauce", true},
            {"locked_out_user", "secret_sauce", false},
            {"problem_user", "secret_sauce", true},
            {"performance_glitch_user", "secret_sauce", true},
            {"visual_user", "secret_sauce", true},         // Assuming invalid
            {"error_user", "invalid_pass", false},          // Invalid credentials
            {"standard_user", "wrong_password", false},     // Wrong password
        };
    }

    public boolean isLoginSuccessful() {
        return driver.getCurrentUrl().contains("inventory.html");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
