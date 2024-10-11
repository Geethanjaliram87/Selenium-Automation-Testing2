package WebTableAutomation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebTableAutomation {
    public static void main(String[] args) {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        // Initialize WebDriver and open the browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open the webpage containing the table
        driver.get("https://www.w3schools.com/html/html_tables.asp");

        // Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        try {
            // Wait for the table to be present
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customers")));

            // Locate the table
            WebElement table = driver.findElement(By.id("customers"));

            // Get all rows of the table
            List<WebElement> rows = table.findElements(By.tagName("tr"));
            int rowCount = rows.size();  // Total number of rows
            System.out.println("Total number of rows: " + rowCount);

            // Get all columns in the first row
            List<WebElement> columns = rows.get(0).findElements(By.tagName("th"));
            int columnCount = columns.size();  // Total number of columns
            System.out.println("Total number of columns: " + columnCount);

            // Extract and print data from specific rows and columns
            // Example: Print data from the second row and third column (index 1 and 2)
            WebElement specificData = rows.get(1).findElements(By.tagName("td")).get(2);
            System.out.println("Data in the second row, third column: " + specificData.getText());

            // Perform assertions on the table content
            // Example: Verify if a specific value exists in the table
            boolean valueFound = false;
            String valueToCheck = "Germany"; // Replace with any value you want to check

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                for (WebElement cell : cells) {
                    if (cell.getText().equals(valueToCheck)) {
                        valueFound = true;
                        break; // Exit the inner loop if value is found
                    }
                }
                if (valueFound) {
                    break; // Exit the outer loop if value is found
                }
            }

            // Print assertion result
            if (valueFound) {
                System.out.println("Value '" + valueToCheck + "' is present in the table.");
            } else {
                System.out.println("Value '" + valueToCheck + "' is NOT present in the table.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
