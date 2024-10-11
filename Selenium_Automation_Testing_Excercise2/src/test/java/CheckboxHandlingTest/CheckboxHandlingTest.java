package CheckboxHandlingTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CheckboxHandlingTest {

    public static void main(String[] args) {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        // Initialize WebDriver and open the browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open the test page with multiple checkboxes
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        // Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Wait for the checkboxes to be present
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='checkbox']")));

            // Locate all checkbox elements
            List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
            
            // Select all checkboxes and print their status
            for (int i = 0; i < checkboxes.size(); i++) {
                WebElement checkbox = checkboxes.get(i);
                if (!checkbox.isSelected()) {  // Select only if not already selected
                    checkbox.click();
                    System.out.println("Checkbox " + (i + 1) + " selected.");
                } else {
                    System.out.println("Checkbox " + (i + 1) + " was already selected.");
                }
            }

            // Deselect the first checkbox if selected and print its status
            WebElement firstCheckbox = checkboxes.get(0);
            if (firstCheckbox.isSelected()) {
                firstCheckbox.click();
                System.out.println("First checkbox deselected.");
            } else {
                System.out.println("First checkbox was already deselected.");
            }

            // Verify if the last checkbox is selected and print its status
            WebElement lastCheckbox = checkboxes.get(checkboxes.size() - 1);  // Access the last checkbox in the list
            if (lastCheckbox.isSelected()) {
                System.out.println("The last checkbox is selected.");
            } else {
                System.out.println("The last checkbox is not selected.");
            }

            // Print final statuses of all checkboxes
            for (int i = 0; i < checkboxes.size(); i++) {
                WebElement checkbox = checkboxes.get(i);
                System.out.println("Checkbox " + (i + 1) + " final status: " + (checkbox.isSelected() ? "Selected" : "Not selected"));
            }

            System.out.println("Checkbox handling completed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser after a short delay
            try {
                Thread.sleep(2000);  // Just for observing changes, remove in production
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }
}
