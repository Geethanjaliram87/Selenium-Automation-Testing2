package ScreenshotCaptureExample;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class ScreenshotCaptureExample {
    public static void main(String[] args) {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        // Initialize WebDriver and open the browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to the OrangeHRM login page
        driver.get("https://opensource-demo.orangehrmlive.com/");

        try {
            // Locate the username and password fields, and the login button using CSS selectors
            WebElement usernameField = driver.findElement(By.cssSelector("input[name='username']"));
            WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

            // Enter credentials and attempt to login
            usernameField.sendKeys("Admin");
            passwordField.sendKeys("admin123");
            loginButton.click();

            // Wait for the Dashboard header to become visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[contains(@class, 'oxd-topbar-header-breadcrumb-module') and text()='Dashboard']")));

            // Capture a screenshot after the page has loaded
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
         // Generate a unique name by appending a timestamp
            String timestamp = String.valueOf(System.currentTimeMillis());
            String screenshotPath = "C:\\screenshots\\login_attempt_screenshot_" + timestamp + ".png";
            File destFile = new File(screenshotPath);
        
            // Ensure the directory exists before saving the screenshot
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }

            // Save the screenshot
            Files.copy(srcFile.toPath(), destFile.toPath());

            // Verify if the screenshot exists
            if (Files.exists(Paths.get(screenshotPath))) {
                System.out.println("Screenshot captured and saved at: " + screenshotPath);
            } else {
                System.out.println("Screenshot not found at specified location.");
            }

        } catch (IOException e) {
            System.out.println("IOException occurred while saving the screenshot.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
