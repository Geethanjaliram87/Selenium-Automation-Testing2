package AlertHandlingExample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertHandlingExample {
    public static void main(String[] args) {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        // Initialize WebDriver and open the browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to the alert demo page
        driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_alert");

        try {
            // Switch to the iframe that contains the button
            driver.switchTo().frame("iframeResult");

            // Wait for the button to be clickable and then click it
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Try it')]"))).click();

            // Wait for the alert to appear and switch to it
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            // Retrieve the alert message
            String alertMessage = alert.getText();
            System.out.println("Alert message: " + alertMessage);

            // Verify the alert message
            if (alertMessage.equals("I am an alert box!")) {
                System.out.println("Alert message is as expected.");
            } else {
                System.out.println("Alert message is NOT as expected.");
            }

            // Accept the alert
            alert.accept();
            System.out.println("Alert accepted.");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
