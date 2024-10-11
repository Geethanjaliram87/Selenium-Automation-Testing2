package ElementInteraction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementInteraction {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/login");  // Navigate to the intended page
        driver.manage().window().maximize();

        // Create an explicit wait to allow dynamic elements to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Wait and locate the username field
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
            usernameField.sendKeys("geethanjaliyokesh18");  // Enter username
            
            // Wait and locate the password field
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            passwordField.sendKeys("Geetha@123");  // Enter password

            // Locate and click the login button
            WebElement loginButton = driver.findElement(By.id("login"));
            loginButton.click();

            // Wait for a post-login element to verify login success
            WebElement profileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName-value")));
            System.out.println("Login was successful! Welcome message: " + profileElement.getText());

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Quit the driver to close the browser
            driver.quit();
        }
    }
}
