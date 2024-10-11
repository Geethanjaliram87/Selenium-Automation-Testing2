package MouseHoverExample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MouseHoverExample {
    public static void main(String[] args) {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        // Initialize WebDriver and open the browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to Amazon
        driver.get("https://www.amazon.com/");

        try {
            // Explicit wait for the page to load and locate the "Account & Lists" dropdown
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement accountLists = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-link-accountList")));

            // Perform mouse hover action
            Actions actions = new Actions(driver);
            actions.moveToElement(accountLists).perform();

            // Wait for and click "Your Account" link in the dropdown
            WebElement yourAccountLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Account']")));
            yourAccountLink.click();

            // Confirmation by printing the title
            System.out.println("Navigated to: " + driver.getTitle());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
