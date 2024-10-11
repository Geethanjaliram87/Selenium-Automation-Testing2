package FileUploadTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Paths;

public class FileUploadTest {
    public static void main(String[] args) {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        // Initialize WebDriver and open the browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to the file upload page
        driver.get("https://www.w3schools.com/howto/howto_html_file_upload_button.asp");

        try {
            // Locate the file input element using its ID
            WebElement fileInput = driver.findElement(By.id("myFile"));

            // Specify the file path to upload
            String filePath = Paths.get("C:\\Users\\Dr. T.P. YOKESH\\OneDrive\\Desktop\\Picture1.jpg").toString(); // Change this to your file path
            fileInput.sendKeys(filePath);

            // Wait briefly for the upload process to complete
            Thread.sleep(500); // Adjust this wait time as needed

            // Locate the uploaded file name element using its ID
            WebElement uploadedFileName = driver.findElement(By.id("myFile")); // Update with the actual ID

            // Print the uploaded file name directly
            System.out.println("File Uploaded Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
