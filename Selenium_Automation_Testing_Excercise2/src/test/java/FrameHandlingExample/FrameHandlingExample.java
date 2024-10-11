package FrameHandlingExample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FrameHandlingExample {
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
            WebElement tryItButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Try it')]")));
            tryItButton.click();

            // Interact with the alert
            // Wait for the alert to appear and switch to it
            wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = driver.switchTo().alert().getText();
            System.out.println("Alert message: " + alertMessage);
            driver.switchTo().alert().accept(); // Accept the alert

            // Switch back to the main content
            driver.switchTo().defaultContent();

            // Now you can interact with elements outside of the iframe if needed
            System.out.println("Switched back to the main content.");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
